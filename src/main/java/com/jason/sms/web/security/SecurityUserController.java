package com.jason.sms.web.security;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.base.Objects;
import com.jason.framework.domain.EntityUtils;
import com.jason.framework.orm.Page;
import com.jason.framework.web.support.ControllerSupport;
import com.jason.sms.domain.security.Role;
import com.jason.sms.domain.security.User;
import com.jason.sms.service.security.RoleService;
import com.jason.sms.service.security.UserService;
import com.jason.sms.util.JsonMessage;

@Controller
@RequestMapping("/security/user")
public class SecurityUserController extends ControllerSupport {
	private static final String REDIRECT_LIST = "redirect:/security/user/list/";
	private static final String FORM = "security/user/form";
	private static final String LIST = "security/user/list";
	
	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/list/", method = GET)
	public String list(Page<User> page, Model model) {
		page = userService.queryPage(page);
		model.addAttribute("page",page);
		return LIST;
	}

	@RequestMapping(value = "/create/", method = GET)
	public String create(Model model) {
		List<Role> roles = roleService.query(new Object());
		model.addAttribute(new User()).addAttribute("roles", roles);
		return FORM;
	}

	@RequestMapping(value = "/create/", method = POST)
	public String create(@Valid User entity, BindingResult result,RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			error(redirectAttributes,"创建用户失败，请核对数据后重试");
			return REDIRECT_LIST;
		}
		
		userService.save(entity.encodePassword());
		success(redirectAttributes,"用户创建成功");
		return REDIRECT_LIST;
	}

	@RequestMapping(value = "/{id}/edit/", method = GET)
	public String edit(@PathVariable("id") Long id, Model model) {
		User entity = userService.lazyGet(id);
		List<Role> roles = roleService.query(new Object());
		model.addAttribute(entity).addAttribute("roles", roles).addAttribute("_method", "PUT");
		return FORM;
	}

	@RequestMapping(value = "/{id}/edit/", method = PUT)
	public String edit(@PathVariable("id") Long id, HttpServletRequest request,RedirectAttributes redirectAttributes) {
		try {

			User entity = userService.lazyGet(id);
			//entity.getRolesTrans().clear();

			String oldUsername = entity.getUsername();
			String oldPassword = entity.getPassword();
			
			bind(request, entity);

			Assert.isTrue(Objects.equal(id, entity.getId()), "编辑Id不相符");
			checkUsernameNotModified(oldUsername, entity.getUsername());

			if (entity.hasPassword()) {
				entity.encodePassword();
			}
			else {
				entity.setPassword(oldPassword);
			}

			userService.update(entity);
			success(redirectAttributes,"用户修改成功");
		} catch (Exception e) {
			error(redirectAttributes,"修改用户失败，请核对数据后重试",e);
		}

		return REDIRECT_LIST;
	}

	private void checkUsernameNotModified(String oldUsername, String newUsername) {
		boolean usernameNotModify = StringUtils.equals(oldUsername, newUsername);
		Assert.isTrue(usernameNotModify, "Username can't modify!");
	}

	@RequestMapping(value = "/{id}/delete/", method = DELETE)
	public String delete(@PathVariable("id") Long id) {
		userService.delete(userService.lazyGet(id));
		return REDIRECT_LIST;
	}

	@RequestMapping(value = "/delete/", method = DELETE)
	public String delete(HttpServletRequest request) {
		for (String item : EntityUtils.nullSafe(request.getParameterValues("items"), new String[] {})) {
			delete(EntityUtils.toLong(item));
		}
		return REDIRECT_LIST;
	}

	@RequestMapping(value = "/lock/", method = PUT)
	@ResponseBody
	public JsonMessage lock(HttpServletRequest request, @RequestHeader(value = "X-Requested-With", required = false) String requestWith) {
		try {
			if (!isAjax(requestWith)) {
				return onFailure("Not supported operation!");
			}

			String[] items = findItems(request);
			userService.markLocked(EntityUtils.nullSafe(items, new String[] {}));
			return onSuccess();
		} catch (Exception e) {
			return onFailure(e);
		}
	}

	@RequestMapping(value = "/evict-cache/", method = PUT)
	@ResponseBody
	public JsonMessage evictCache(HttpServletRequest request, @RequestHeader(value = "X-Requested-With", required = false) String requestWith) {
		try {
			if (!isAjax(requestWith)) {
				return onFailure("Not supported operation!");
			}

			String[] items = findItems(request);
			userService.evictCache(EntityUtils.nullSafe(items, new String[] {}));
			return onSuccess();
		} catch (Exception e) {
			return onFailure(e);
		}
	}
	public static boolean isAjax(String requestedWith) {
		return StringUtils.isNotBlank(requestedWith) ? "XMLHttpRequest".equals(requestedWith) : false;
	}
	private JsonMessage onSuccess() {
		return JsonMessage.one().success();
	}

	private JsonMessage onFailure(String msg) {
		return JsonMessage.one().error().message(msg);
	}

	private JsonMessage onFailure(Exception e) {
		return JsonMessage.one().error().message(e.getMessage());
	}

	@RequestMapping(value = "/not-lock/", method = PUT)
	@ResponseBody
	public JsonMessage notlock(HttpServletRequest request, @RequestHeader(value = "X-Requested-With", required = false) String requestWith) {
		try {
			if (!isAjax(requestWith)) {
				return JsonMessage.one().error().message("Not supported operation!");
			}

			String[] items = findItems(request);
			userService.markNotLocked(EntityUtils.nullSafe(items, new String[] {}));
			return onSuccess();
		} catch (Exception e) {
			return onFailure(e);
		}
	}

	private String[] findItems(HttpServletRequest request) {
		String itemsAsString = request.getParameter("items");
		return StringUtils.split(itemsAsString, ',');
	}

}

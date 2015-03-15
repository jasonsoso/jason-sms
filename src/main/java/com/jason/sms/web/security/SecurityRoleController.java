package com.jason.sms.web.security;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.base.Objects;
import com.jason.framework.domain.EntityUtils;
import com.jason.framework.orm.Page;
import com.jason.framework.web.support.ControllerSupport;
import com.jason.sms.domain.security.Authority;
import com.jason.sms.domain.security.Role;
import com.jason.sms.service.security.AuthorityService;
import com.jason.sms.service.security.RoleService;

@Controller
@RequestMapping("/security/role")
public class SecurityRoleController extends ControllerSupport{

	private static final String REDIRECT_LIST = "redirect:/security/role/list/";
	private static final String FORM = "security/role/form";
	private static final String LIST = "security/role/list";
	
	@Autowired
	private RoleService roleService;

	@Autowired
	private AuthorityService authorityService;

	@RequestMapping(value = "/list/", method = GET)
	public String list(Page<Role> page, Model model) {
		page = roleService.queryPage(page);
		model.addAttribute("page",page);
		return LIST;
	}

	@RequestMapping(value = "/create/", method = GET)
	public String create(Model model) {
		List<Authority> authorities = authorityService.query(new Object());
		model.addAttribute(new Role()).addAttribute("authorities", authorities);
		return FORM;
	}

	@RequestMapping(value = "/create/", method = POST)
	public String create(@Valid Role entity, BindingResult result,RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			error(redirectAttributes,"创建用户角色失败，请核对数据后重试");
			return REDIRECT_LIST;
		}
		roleService.save(entity);
		success(redirectAttributes,"用户角色创建成功");
		return REDIRECT_LIST;
	}

	@RequestMapping(value = "/{id}/edit/", method = GET)
	public String edit(@PathVariable("id") Long id, Model model) {
		Role entity = roleService.get(id);
		List<Authority> authorities = authorityService.query(new Object());
		model.addAttribute(entity.ofAuths()).addAttribute("authorities", authorities).addAttribute("_method", "PUT");
		
		Map<Long,Long> longmap = new HashMap<Long, Long>();
		longmap.put(1L, 1L);
		longmap.put(2L, 2L);
		longmap.put(3L, 3L);
		longmap.put(4L, 4L);
		
		Long longkey = 2L;
		model.addAttribute("longmap", longmap).addAttribute("longkey",longkey);
		
		Map<Long,Long> mapsAll =  entity.getAuthoritiesTrans();
/*		mapsAll.put(1L, 1L);
		mapsAll.put(2L, 2L);
		mapsAll.put(3L, 3L);
		mapsAll.put(4L, 4L);
*/		
		model.addAttribute("mapsAll", mapsAll);
		return FORM;
	}

	@RequestMapping(value = "/{id}/edit/", method = PUT)
	public String edit(@PathVariable("id") Long id, HttpServletRequest request,RedirectAttributes redirectAttributes) {
		try {

			Role entity = roleService.get(id);
			entity.getAuthoritiesTrans().clear();

			bind(request, entity);
			Assert.isTrue(Objects.equal(id, entity.getId()), "编辑Id不相符");
			
			roleService.update(entity);
			success(redirectAttributes,"用户角色修改成功，请刷新该角色关联用户缓存，以使修改生效");
		} catch (Exception e) {
			error(redirectAttributes,"用户角色修改失败，请核对数据后重试",e);
		}

		return REDIRECT_LIST;
	}

	@RequestMapping(value = "/{id}/delete/", method = DELETE)
	public String delete(@PathVariable("id") Long id) {
		roleService.delete(roleService.get(id));
		return REDIRECT_LIST;
	}

	@RequestMapping(value = "/delete/", method = DELETE)
	public String delete(HttpServletRequest request) {
		for (String item : EntityUtils.nullSafe(request.getParameterValues("items"), new String[] {})) {
			delete(EntityUtils.toLong(item));
		}

		return REDIRECT_LIST;
	}

}

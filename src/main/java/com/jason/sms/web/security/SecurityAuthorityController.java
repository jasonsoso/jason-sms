package com.jason.sms.web.security;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

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
import com.jason.sms.service.security.AuthorityService;

@Controller
@RequestMapping("/security/authority")
public class SecurityAuthorityController extends ControllerSupport {

	private static final String REDIRECT_LIST = "redirect:/security/authority/list/";
	private static final String FORM = "security/authority/form";
	private static final String LIST = "security/authority/list";

	@Autowired
	private AuthorityService authorityService;
	
	@RequestMapping(value = "/list/", method = GET)
	public String list(Page<Authority> page, Model model) {
		
		page.setOrderBy("id").setOrder(Page.DESC);
		
		page = authorityService.queryPage(page);
		model.addAttribute("page",page);
		return LIST;
	}

	@RequestMapping(value = "/create/", method = GET)
	public String create(Model model) {
		model.addAttribute(new Authority());
		return FORM;
	}

	@RequestMapping(value = "/create/", method = POST)
	public String create(@Valid Authority entity, BindingResult result,RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			error(redirectAttributes,"创建用户权限失败，请核对数据后重试");
			return REDIRECT_LIST;
		}

		authorityService.save(entity);
		success(redirectAttributes,"用户权限创建成功");
		return REDIRECT_LIST;
	}

	@RequestMapping(value = "/{id}/edit/", method = GET)
	public String edit(@PathVariable("id") Long id, Model model) {
		Authority entity = authorityService.get(id);
		model.addAttribute(entity).addAttribute("_method", "PUT");
		return FORM;
	}

	@RequestMapping(value = "/{id}/edit/", method = PUT)
	public String edit(@PathVariable("id") Long id, HttpServletRequest request,RedirectAttributes redirectAttributes) {
		try {

			Authority entity = authorityService.get(id);
			bind(request, entity);
			Assert.isTrue(Objects.equal(id, entity.getId()), "编辑Id不相符");
			
			authorityService.update(entity);
			success(redirectAttributes,"用户权限修改成功，请刷新该权限关联用户缓存，以使修改生效");
		} catch (Exception e) {
			error(redirectAttributes,"用户权限修改失败，请核对数据重试",e);
		}

		return REDIRECT_LIST;
	}

	@RequestMapping(value = "/{id}/delete/", method = DELETE)
	public String delete(@PathVariable("id") Long id) {
		authorityService.delete(authorityService.get(id));
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

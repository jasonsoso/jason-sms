package com.jason.sms.web.cms;

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
import com.jason.sms.domain.cms.Template;
import com.jason.sms.service.cms.TemplateService;
import com.jason.sms.util.Conf;
import com.jason.sms.util.ConfigureOnWeb;

/**
 * 模板 控制层
 * @author Jason
 * @date 2015-11-11
 */
@Controller
@RequestMapping(value = "/cms/template")
public class TemplateController extends ControllerSupport {
	
	private static final String REDIRECT_LIST = "redirect:/cms/template/list/";
	private static final String FORM = "cms/template/form";
	private static final String LIST = "cms/template/list";
	
	@Autowired
	private TemplateService templateService;
	
	
	@RequestMapping(value = "/list/", method = GET)
	public String list(Page<Template> page, Model model) {
		page.setOrderBy("id").setOrder(Page.DESC);
		
		page = templateService.queryPage(page);
		model.addAttribute("page",page);
		return LIST;
	}

	@RequestMapping(value = "/create/", method = GET)
	public String create(Model model) {
		model.addAttribute(new Template());
		return FORM;
	}

	@RequestMapping(value = "/create/", method = POST)
	public String create(@Valid Template entity, BindingResult result,RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			error(redirectAttributes,"创建模板失败，请核对数据后重试");
			return REDIRECT_LIST;
		}
		templateService.save(entity);
		success(redirectAttributes,"模板创建成功");
		return REDIRECT_LIST;
	}

	@RequestMapping(value = "/{id}/edit/", method = GET)
	public String edit(@PathVariable("id") Long id, Model model) {
		Template entity = templateService.get(id);
		model.addAttribute(entity).addAttribute("_method", "PUT");
		return FORM;
	}

	@RequestMapping(value = "/{id}/edit/", method = PUT)
	public String edit(@PathVariable("id") Long id, HttpServletRequest request,RedirectAttributes redirectAttributes) {
		try {

			Template entity = templateService.get(id);
			bind(request, entity);
			Assert.isTrue(Objects.equal(id, entity.getId()), "编辑Id不相符");
			
			templateService.update(entity);
			success(redirectAttributes,"模板修改成功");
		} catch (Exception e) {
			error(redirectAttributes,"模板修改失败，请核对数据重试",e);
		}
		return REDIRECT_LIST;
	}

	@RequestMapping(value = "/{id}/delete/", method = DELETE)
	public String delete(@PathVariable("id") Long id) {
		templateService.delete(templateService.get(id));
		return REDIRECT_LIST;
	}

	@RequestMapping(value = "/delete/", method = DELETE)
	public String delete(HttpServletRequest request) {
		for (String item : EntityUtils.nullSafe(request.getParameterValues("items"), new String[] {})) {
			delete(EntityUtils.toLong(item));
		}
		return REDIRECT_LIST;
	}
	@RequestMapping(value = "/sync/", method = POST)
	public String sync(HttpServletRequest request,RedirectAttributes redirectAttributes) {
		for (String item : EntityUtils.nullSafe(request.getParameterValues("items"), new String[] {})) {
			Template entity = templateService.get(EntityUtils.toLong(item));
			if(entity != null){
				templateService.sync(entity);
			}
		}
		success(redirectAttributes, "模板同步成功");
		return REDIRECT_LIST;
	}

	
}

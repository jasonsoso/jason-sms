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
import com.jason.sms.domain.cms.Category;
import com.jason.sms.service.cms.CategoryService;

/**
 * 分类 控制层
 * @author Jason
 * @date 2015-11-13
 */
@Controller
@RequestMapping(value = "/cms/category")
public class CategoryController extends ControllerSupport {
	
	private static final String REDIRECT_LIST = "redirect:/cms/category/list/";
	private static final String FORM = "cms/category/form";
	private static final String LIST = "cms/category/list";
	
	@Autowired
	private CategoryService categoryService;
	
	
	@RequestMapping(value = "/list/", method = GET)
	public String list(Page<Category> page, Model model) {
		
		page.setOrderBy("id").setOrder(Page.DESC);
		
		page = categoryService.queryPage(page);
		model.addAttribute("page",page);
		return LIST;
	}

	@RequestMapping(value = "/create/", method = GET)
	public String create(Model model) {
		model.addAttribute(new Category());
		return FORM;
	}

	@RequestMapping(value = "/create/", method = POST)
	public String create(@Valid Category entity, BindingResult result,RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			error(redirectAttributes,"创建分类失败，请核对数据后重试");
			return REDIRECT_LIST;
		}
		categoryService.save(entity);
		success(redirectAttributes,"分类创建成功");
		return REDIRECT_LIST;
	}

	@RequestMapping(value = "/{id}/edit/", method = GET)
	public String edit(@PathVariable("id") Long id, Model model) {
		Category entity = categoryService.get(id);
		model.addAttribute(entity).addAttribute("_method", "PUT");
		return FORM;
	}

	@RequestMapping(value = "/{id}/edit/", method = PUT)
	public String edit(@PathVariable("id") Long id, HttpServletRequest request,RedirectAttributes redirectAttributes) {
		try {

			Category entity = categoryService.get(id);
			bind(request, entity);
			Assert.isTrue(Objects.equal(id, entity.getId()), "编辑Id不相符");
			
			categoryService.update(entity);
			success(redirectAttributes,"分类修改成功");
		} catch (Exception e) {
			error(redirectAttributes,"分类修改失败，请核对数据重试",e);
		}
		return REDIRECT_LIST;
	}

	@RequestMapping(value = "/{id}/delete/", method = DELETE)
	public String delete(@PathVariable("id") Long id) {
		categoryService.delete(categoryService.get(id));
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

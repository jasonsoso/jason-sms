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
import com.jason.sms.domain.cms.Channel;
import com.jason.sms.service.cms.ChannelService;
import com.jason.sms.service.cms.TemplateService;

/**
 * 频道 控制层
 * @author Jason
 * @date 2015-11-11
 */
@Controller
@RequestMapping(value = "/cms/channel")
public class ChannelController extends ControllerSupport {
	
	private static final String REDIRECT_LIST = "redirect:/cms/channel/list/";
	private static final String FORM = "cms/channel/form";
	private static final String LIST = "cms/channel/list";
	
	@Autowired
	private ChannelService channelService;
	@Autowired
	private TemplateService templateService;
	
	
	@RequestMapping(value = "/list/", method = GET)
	public String list(Page<Channel> page, Model model) {
		
		page.setOrderBy("self.sort").setOrder(Page.ASC);
		
		page = channelService.queryPage(page);
		model.addAttribute("page",page);
		return LIST;
	}

	@RequestMapping(value = "/create/", method = GET)
	public String create(Model model) {
		model.addAttribute(new Channel())
			.addAttribute("channelNames", channelService.queryChannels())
			.addAttribute("template", templateService.queryTemplates());
		return FORM;
	}

	@RequestMapping(value = "/create/", method = POST)
	public String create(@Valid Channel entity, BindingResult result,HttpServletRequest request,RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			error(redirectAttributes,"创建频道失败，请核对数据后重试");
			return REDIRECT_LIST;
		}
		entity.setFather(channelService.get(EntityUtils.toLong(request.getParameter("belongChannel"))));
		entity.setTemplate(templateService.get(EntityUtils.toLong(request.getParameter("belongTemplate"))));
		
		channelService.save(entity);
		success(redirectAttributes,"频道创建成功");
		return REDIRECT_LIST;
	}

	@RequestMapping(value = "/{id}/edit/", method = GET)
	public String edit(@PathVariable("id") Long id, Model model) {
		Channel entity = channelService.get(id);
		model.addAttribute(entity)
			.addAttribute("_method", "PUT")
			.addAttribute("channelNames", channelService.queryChannels())
			.addAttribute("template", templateService.queryTemplates());
		return FORM;
	}

	@RequestMapping(value = "/{id}/edit/", method = PUT)
	public String edit(@PathVariable("id") Long id, HttpServletRequest request,RedirectAttributes redirectAttributes) {
		try {

			Channel entity = channelService.get(id);
			bind(request, entity);
			Assert.isTrue(Objects.equal(id, entity.getId()), "编辑Id不相符");
			
			entity.setFather(channelService.get(EntityUtils.toLong(request.getParameter("belongChannel"))));
			entity.setTemplate(templateService.get(EntityUtils.toLong(request.getParameter("belongTemplate"))));
			
			channelService.update(entity);
			success(redirectAttributes,"频道修改成功");
		} catch (Exception e) {
			error(redirectAttributes,"频道修改失败，请核对数据重试",e);
		}
		return REDIRECT_LIST;
	}

	@RequestMapping(value = "/{id}/delete/", method = DELETE)
	public String delete(@PathVariable("id") Long id) {
		channelService.delete(channelService.get(id));
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

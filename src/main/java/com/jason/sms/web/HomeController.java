package com.jason.sms.web;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jason.framework.util.FilesHelper;
import com.jason.framework.web.exception.ResourceNotFoundException;
import com.jason.framework.web.support.ControllerSupport;
import com.jason.sms.domain.cms.Channel;
import com.jason.sms.service.cms.ChannelService;

@Controller
public class HomeController extends ControllerSupport{
	
	@Autowired
	private ChannelService channelService;
	
	public final static String PROJECT_BUILD_NUMBER = "1.0.0";
	
	@RequestMapping(value = { "/", "/welcome", "/home", "/index" }, method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		return "WEB-INF/front/template/welcome";
	}
	
	
	
	/**
	 * 根据栏目路径进行访问
	 * @param path
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{path}", method = GET)
	public String path(@PathVariable("path") String path, Model model) {
		Channel channel = channelService.queryUniqueByPath(path);
		if (null == channel) {
			throw new ResourceNotFoundException();
		}else{
			model.addAttribute(channel);
			return FilesHelper.getMajorName(channel.getTemplate().getPhysicalUrl());
		}
	}
	
}

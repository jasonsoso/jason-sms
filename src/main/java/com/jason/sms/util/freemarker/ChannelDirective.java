package com.jason.sms.util.freemarker;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jason.framework.web.directive.freemarker.FreemarkerDirectiveSupport;
import com.jason.sms.domain.cms.Channel;
import com.jason.sms.service.cms.ChannelService;

import freemarker.core.Environment;
import freemarker.template.SimpleCollection;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateScalarModel;

/**
 * 读取栏目信息
 * 必填值path
 * eg:<@jason_properties key="photoServer.domain" default="***"/>
 * 
 * @author Jason
 * @date 2015-11-12
 */
public class ChannelDirective extends FreemarkerDirectiveSupport{

	@Autowired
	private ChannelService channelService;
	
	public final static String PATH_PARAM = "path";
	
	@Override
	protected void doExecute(Environment env, Map<String, ?> params,
			TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		String path = super.getStringValueByParams(params, PATH_PARAM, null);
		
		List<Channel> channels = channelService.queryTop();
		loopVars[0] = new SimpleCollection(channels);
		if(null!=body){
			body.render(env.getOut());
		}
	}
	
	@Override
	protected boolean beforeExecute(Environment env, Map<String, ?> params,
			TemplateModel[] loopVars) {
		if(params.containsKey(PATH_PARAM)){
			return true;
		}else{
			super.logger.debug(String.format("Must have this %s", PATH_PARAM));
		}
		return false;
	}




}

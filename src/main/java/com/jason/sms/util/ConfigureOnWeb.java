package com.jason.sms.util;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.ServletContextAware;


@Component
public final class ConfigureOnWeb implements ServletContextAware {
	private ServletContext context;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.context.ServletContextAware#setServletContext(javax.servlet.ServletContext)
	 */
	@Override
	public final void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
	}

	/**
	 * 
	 * @param conf
	 * @return
	 */
	public final Conf wrap(Conf conf) {
		String templatePath = FilesHelper.join(getContextRealPath(), conf.getTemplatePath());
		String resourcePath = FilesHelper.join(getContextRealPath(), conf.getResourcePath());
		String securityResourcePath = FilesHelper.join(getContextRealPath(), conf.getSecurityResourcePath());
		String attachmentPath = FilesHelper.join(getContextRealPath(), conf.getAttachmentPath());
		String photoPath = FilesHelper.join(getContextRealPath(), conf.getPhotoPath());

		conf.setContext(getContextPath()).setAttachmentPath(attachmentPath).setPhotoPath(photoPath);
		conf.setSecurityResourcePath(securityResourcePath).setTemplatePath(templatePath);
		return conf.setResourcePath(resourcePath).setRootPath(getContextRealPath());
	}

	private String getContextRealPath() {
		Assert.notNull(this.context);
		return context.getRealPath("/");
	}
	
	private String getContextPath() {
		Assert.notNull(this.context);
		return context.getContextPath();
	}
}

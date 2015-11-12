package com.jason.sms.util;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

public class Conf implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Conf DEFAULT = new Conf().setTemplatePath("resources/front/template")
													.setResourcePath("resources")
													.setSecurityResourcePath("WEB-INF")
													.setAttachmentPath("attachments")
													.setPhotoPath("resources/photos")
													.setAllowedPhotoTypes("jpg,jpeg,bmp,gif,png,ico,xlsx")
													.setAllowedSecurityResourceTypes("jsp,ftl,html,htm,txt,xlsx")
													.setAllowedResourceTypes("jpg,jpeg,bmp,gif,png,ico;txt,doc,docx,ppt,xls,pdf;js,css,xml;zip,rar;xlsx")
													.setMaxResourceSize(5 * 1024 * 1024);

	private Conf() {}

	/**
	 * 
	 * @return
	 */
	public static Conf defaultOne() {
		return DEFAULT;
	}

	private String context;
	private String rootPath;
	private String templatePath;
	private String resourcePath;
	private String allowedResourceTypes;
	private String securityResourcePath;
	private String allowedSecurityResourceTypes;
	private long maxResourceSize;
	private String attachmentPath;
	private String photoPath;
	private String allowedPhotoTypes;

	public String getContext() {
		return context;
	}

	public Conf setContext(String context) {
		this.context = context;
		return this;
	}

	public String getRootPath() {
		return rootPath;
	}

	public Conf setRootPath(String rootPath) {
		this.rootPath = rootPath;
		return this;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public Conf setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
		return this;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public Conf setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
		return this;
	}

	public String getSecurityResourcePath() {
		return this.securityResourcePath;
	}

	public Conf setSecurityResourcePath(String securityResourcePath) {
		this.securityResourcePath = securityResourcePath;
		return this;
	}

	public Conf setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
		return this;
	}

	public String getPhotoPath() {
		return this.photoPath;
	}

	public Conf setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
		return this;
	}

	public String getAllowedPhotoTypes() {
		return allowedPhotoTypes;
	}

	public Conf setAllowedPhotoTypes(String allowedPhotoTypes) {
		this.allowedPhotoTypes = allowedPhotoTypes;
		return this;
	}

	public String getAttachmentPath() {
		return this.attachmentPath;
	}

	public String getAllowedResourceTypes() {
		return allowedResourceTypes;
	}

	/**
	 * 
	 * @return
	 */
	public List<String> getAllowedResourceTypesAsList() {
		if (StringUtils.isBlank(getAllowedResourceTypes())) {
			return Collections.emptyList();
		}

		String[] types = getAllowedResourceTypes().toLowerCase().split("[\\s;,]");
		return Arrays.asList(types);
	}

	public Conf setAllowedResourceTypes(String allowedResourceTypes) {
		this.allowedResourceTypes = allowedResourceTypes;
		return this;
	}

	public String getAllowedSecurityResourceTypes() {
		return allowedSecurityResourceTypes;
	}

	/**
	 * 
	 * @return
	 */
	public List<String> getAllowedSecurityResourceTypesAsList() {
		if (StringUtils.isBlank(getAllowedSecurityResourceTypes())) {
			return Collections.emptyList();
		}

		String[] types = getAllowedSecurityResourceTypes().toLowerCase().split("[\\s;,]");
		return Arrays.asList(types);
	}

	/**
	 * 
	 * @return
	 */
	public List<String> getAllowedPhotoTypesAsList() {
		if (StringUtils.isBlank(getAllowedPhotoTypes())) {
			return Collections.emptyList();
		}

		String[] types = getAllowedPhotoTypes().toLowerCase().split("[\\s;,]");
		return Arrays.asList(types);
	}

	public Conf setAllowedSecurityResourceTypes(String allowedSecurityResourceTypes) {
		this.allowedSecurityResourceTypes = allowedSecurityResourceTypes;
		return this;
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	public boolean isAllowedResourceTypes(String type) {
		Assert.hasLength(type);
		return getAllowedResourceTypesAsList().contains(type.toLowerCase());
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	public boolean isAllowedSecurityResourceTypes(String type) {
		Assert.hasLength(type);
		return getAllowedSecurityResourceTypesAsList().contains(type.toLowerCase());
	}

	/**
	 * 
	 * @param type
	 * @return
	 */
	public boolean isAllowedPhotoTypes(String type) {
		Assert.hasLength(type);
		return getAllowedPhotoTypes().contains(type.toLowerCase());
	}

	public long getMaxResourceSize() {
		return maxResourceSize;
	}

	public Conf setMaxResourceSize(long maxResourceSize) {
		this.maxResourceSize = maxResourceSize;
		return this;
	}

	/**
	 * 
	 * @param bytes
	 * @return
	 */
	public boolean isOverflowResourceSize(long bytes) {
		return bytes > getMaxResourceSize();
	}

}

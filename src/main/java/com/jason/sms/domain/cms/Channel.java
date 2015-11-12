package com.jason.sms.domain.cms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.jason.framework.domain.MyBatisDomainObject;

/**
 * 频道 Entity
 * 
 * @author Jason
 * @date 2015-11-11
 */
public class Channel extends MyBatisDomainObject {
	private static final long serialVersionUID = 1L;

	private Channel father;
	private List<Channel> children = new ArrayList<Channel>();
	private String name;
	private String path;
	private int sort;
	
	private Template template;

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	private List<Channel> channelNames = new ArrayList<Channel>();

	public List<Channel> getChannelNames() {
		return channelNames;
	}

	public void setChannelNames(List<Channel> channelNames) {
		this.channelNames = channelNames;
	}

	public Channel getFather() {
		return father;
	}

	public void setFather(Channel father) {
		this.father = father;
	}

	public List<Channel> getChildren() {
		if (null == this.children) {
			return Collections.emptyList();
		}

		return children;
	}

	public void setChildren(List<Channel> children) {
		this.children = children;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public boolean hasChildren() {
		return null != getChildren() && (!getChildren().isEmpty());
	}

}



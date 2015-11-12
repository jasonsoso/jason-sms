package com.jason.sms.domain.cms;

import java.util.Date;
import com.jason.framework.domain.MyBatisDomainObject;

/**
 * 模板 Entity
 * @author Jason
 * @date 2015-11-11
 */
public class Template extends MyBatisDomainObject{
	private static final long serialVersionUID = 1L;
	
	private String name;	//模板名称
	private String fileName;	//文件名称
	private String content;	//内容
	private String physicalUrl;	//物理路径
	private Integer sort;	//排序
	private Date updateTime;	//最后更新时间
	
	public Template setName(String name) {
		this.name = name;
		return this;
	}
	public String getName() {
		return name;
	}
	
	public Template setFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}
	public String getFileName() {
		return fileName;
	}
	
	public Template setContent(String content) {
		this.content = content;
		return this;
	}
	public String getContent() {
		return content;
	}
	
	public Template setPhysicalUrl(String physicalUrl) {
		this.physicalUrl = physicalUrl;
		return this;
	}
	public String getPhysicalUrl() {
		return physicalUrl;
	}
	
	public Template setSort(Integer sort) {
		this.sort = sort;
		return this;
	}
	public Integer getSort() {
		return sort;
	}
	
	public Template setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	
}

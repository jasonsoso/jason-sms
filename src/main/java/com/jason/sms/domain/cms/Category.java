package com.jason.sms.domain.cms;

import java.util.Date;
import com.jason.framework.domain.MyBatisDomainObject;

/**
 * 分类 Entity
 * @author Jason
 * @date 2015-11-13
 */
public class Category extends MyBatisDomainObject{
	private static final long serialVersionUID = 1L;
	
	private String name;	//分类名称
	private Long createId;	//创建人
	private Date createTime;	//创建时间
	private Long updateId;	//最后更新人
	private Date updateTime;	//最后更新时间
	
	public Category setName(String name) {
		this.name = name;
		return this;
	}
	public String getName() {
		return name;
	}
	
	public Category setCreateId(Long createId) {
		this.createId = createId;
		return this;
	}
	public Long getCreateId() {
		return createId;
	}
	
	public Category setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	public Date getCreateTime() {
		return createTime;
	}
	
	public Category setUpdateId(Long updateId) {
		this.updateId = updateId;
		return this;
	}
	public Long getUpdateId() {
		return updateId;
	}
	
	public Category setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	
}

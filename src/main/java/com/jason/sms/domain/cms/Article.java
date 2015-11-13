package com.jason.sms.domain.cms;

import java.util.Date;
import com.jason.framework.domain.MyBatisDomainObject;

/**
 * 文章 Entity
 * @author Jason
 * @date 2015-11-13
 */
public class Article extends MyBatisDomainObject{
	private static final long serialVersionUID = 1L;
	
	private String title;	//标题
	private String author;	//作者
	private String cover;	//封面
	private String summary;	//摘要
	private String content;	//内容
	private Integer status;	//状态
	private Long categoryId;	//分类
	private Long createId;	//创建人
	private Date createTime;	//创建时间
	private Long updateId;	//最后更新人
	private Date updateTime;	//最后更新时间
	
	public Article setTitle(String title) {
		this.title = title;
		return this;
	}
	public String getTitle() {
		return title;
	}
	
	public Article setAuthor(String author) {
		this.author = author;
		return this;
	}
	public String getAuthor() {
		return author;
	}
	
	public Article setCover(String cover) {
		this.cover = cover;
		return this;
	}
	public String getCover() {
		return cover;
	}
	
	public Article setSummary(String summary) {
		this.summary = summary;
		return this;
	}
	public String getSummary() {
		return summary;
	}
	
	public Article setContent(String content) {
		this.content = content;
		return this;
	}
	public String getContent() {
		return content;
	}
	
	public Article setStatus(Integer status) {
		this.status = status;
		return this;
	}
	public Integer getStatus() {
		return status;
	}
	
	public Article setCreateId(Long createId) {
		this.createId = createId;
		return this;
	}
	public Long getCreateId() {
		return createId;
	}
	
	public Article setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
	public Date getCreateTime() {
		return createTime;
	}
	
	public Article setUpdateId(Long updateId) {
		this.updateId = updateId;
		return this;
	}
	public Long getUpdateId() {
		return updateId;
	}
	
	public Article setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public Article setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
		return this;
	}
	
}

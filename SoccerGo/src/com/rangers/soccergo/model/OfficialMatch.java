package com.rangers.soccergo.model;

import java.util.Date;
import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
/*
 * 官方赛 表
 */
@JsonInclude(Include.NON_DEFAULT)
public class OfficialMatch {
	/*系统自带字段*/
	private String objectId;
	private Date createdAt;
	private Date updatedAt;
	/*end*/
	
	/*自己创建的字段*/
	private Date begin_time;   //比赛开始时间
	private int type;    //赛事类型
	private String title;  //赛事标题
	private Date end_time;  //结束时间
	private String status;  //赛事状态
	private Date create_time; //发布时间
	private HashMap<String,Object> image; //赛事图片
	private HashMap<String,Object> user;  //发布者
	private String sponsor;  //主办方
	private String text;  //说明文档
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Date getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(Date begin_time) {
		this.begin_time = begin_time;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public HashMap<String, Object> getImage() {
		return image;
	}
	public void setImage(HashMap<String, Object> image) {
		this.image = image;
	}
	public HashMap<String, Object> getUser() {
		return user;
	}
	public void setUser(HashMap<String, Object> user) {
		this.user = user;
	}
	public String getSponsor() {
		return sponsor;
	}
	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "OfficialMatch [objectId=" + objectId + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + ", begin_time="
				+ begin_time + ", type=" + type + ", title=" + title
				+ ", end_time=" + end_time + ", status=" + status
				+ ", create_time=" + create_time + ", image=" + image
				+ ", user=" + user + ", sponsor=" + sponsor + ", text=" + text
				+ "]";
	}
	
}

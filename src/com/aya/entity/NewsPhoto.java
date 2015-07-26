package com.aya.entity;

import java.util.List;

public class NewsPhoto implements java.io.Serializable{
	private String cover;
	private String clientcover1;
	private String datetime;
	private String desc;
	private int imgsum;
	private String[] pics;
	private int replynum;
	private int setid;
	private String setname;
	
	private String relatedids;
	private List<Photo> photos;
	
	
	public List<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	public String getRelatedids() {
		return relatedids;
	}
	public void setRelatedids(String relatedids) {
		this.relatedids = relatedids;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getClientcover1() {
		return clientcover1;
	}
	public void setClientcover1(String clientcover1) {
		this.clientcover1 = clientcover1;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getImgsum() {
		return imgsum;
	}
	public void setImgsum(int imgsum) {
		this.imgsum = imgsum;
	}
	public String[] getPics() {
		return pics;
	}
	public void setPics(String[] pics) {
		this.pics = pics;
	}
	public int getReplynum() {
		return replynum;
	}
	public void setReplynum(int replynum) {
		this.replynum = replynum;
	}
	public int getSetid() {
		return setid;
	}
	public void setSetid(int setid) {
		this.setid = setid;
	}
	public String getSetname() {
		return setname;
	}
	public void setSetname(String setname) {
		this.setname = setname;
	}
	
}

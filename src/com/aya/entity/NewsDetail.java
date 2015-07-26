package com.aya.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class NewsDetail implements java.io.Serializable {
	private String title;
	private String source;
	private String pTime;
	private String body;
	
	private List<Photo> img;
	private List<Photo> photos;
	private String setname;
	
	private List<Map<String,String>> relative;
	private List<Map<String,String>> video;
	
	public String getSetname() {
		return setname;
	}
	public void setSetname(String setname) {
		this.setname = setname;
	}
	public List<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getpTime() {
		return pTime;
	}
	public void setpTime(String pTime) {
		this.pTime = pTime;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public List<Photo> getImg() {
		return img;
	}
	public void setImg(List<Photo> img) {
		this.img = img;
	}
	public List<Map<String, String>> getRelative() {
		return relative;
	}
	public void setRelative(List<Map<String, String>> relative) {
		this.relative = relative;
	}
	public List<Map<String, String>> getVideo() {
		return video;
	}
	public void setVideo(List<Map<String, String>> video) {
		this.video = video;
	}
	
		
 	
}

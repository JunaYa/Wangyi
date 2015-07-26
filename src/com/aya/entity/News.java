package com.aya.entity;

import java.util.List; 
import java.util.Map;

public class News {
	
	public static String PHOTO_PATH="http://c.m.163.com/photo/api/set/";
	public static String BASE_PATH="http://c.m.163.com/nc/article/";
	
	public static final int PHOTO_TYPE=1;
	public static final int NORMAL_TYPE=0;
	
	private String title;
	private String digest;
	private String source;
	private String url;
	private String imgsrc;
	private int replyCount;
	
	private int length;
	private int playCount;
	private String cover;
	
	private int order;
	private int imgType; //表示一列表显示张大图
	
	private String tags; //推广，视频；；
	private String docid; //AUD65D3C00014AED 详显页面标识   http://c.m.163.com/nc/article/AUD65D3C00014AED/full.html
	
	private String skipType; //photoset表示详显是图片浏览方式
	private String photosetID;//photosetID=54GI0096|71046  可计算出图片请求 url http://c.m.163.com/photo/api/set/0096/71046.json
	private String skipID;
	
	private String detailUrl; //详细显示页面
	private int detailType; //详细显示样式
	private int style=0; //控制看列表显示样式
	
	public String getSkipID() {
		return skipID;
	}
	public void setSkipID(String skipID) {
		this.skipID = skipID;
	}
	public static int getPhotoType() {
		return PHOTO_TYPE;
	}
	public static int getNormalType() {
		return NORMAL_TYPE;
	}
	public String getPhotosetID() {
		return photosetID;
	}
	public void setPhotosetID(String photosetID) {
		this.photosetID = photosetID;
	}
	
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	private List<Map<String,String>> imgextra;
	
	public int getPlayCount() {
		return playCount;
	}
	public void setPlayCount(int playcount) {
		this.playCount = playcount;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public List<Map<String, String>> getImgextra() {
		return imgextra;
	}
	public void setImgextra(List<Map<String, String>> imgextra) {
		this.imgextra = imgextra;
	}
	public int getStyle() {
		if(order==1){
			style=3;
		}else if(imgType==1){
			style=2;
		}else if(imgextra!=null&&imgextra.size()==2){
			style=1;
		}
		return style;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getDetailUrl() {
		switch(getDetailType()){
		case NORMAL_TYPE:
			detailUrl=BASE_PATH+docid+"/full.html";
			break;
		case PHOTO_TYPE:
			int start=photosetID.indexOf("|");
			String strs=photosetID.substring(start+1);
			detailUrl=PHOTO_PATH+"0096/"+strs+".json";
			break;
		}
		return detailUrl;
	}
	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}
	
	//判断详细显示内容的样式
	public int getDetailType() {
		if(skipType!=null && skipType.equals("photoset")){
			return this.PHOTO_TYPE;
		}else if(tags!=null){
			//再判断各种判断
		}else{
			return NORMAL_TYPE;
		}
		return 0;
	}
	
	public String getDocid() {
		return docid;
	}
	public void setDocid(String docid) {
		this.docid = docid;
	}
	public String getSkipType() {
		return skipType;
	}
	public void setSkipType(String skipType) {
		this.skipType = skipType;
	}
	public void setDetailType(int detailType) {
		this.detailType = detailType;
	}
	public void setStyle(int style) {
		this.style = style;
	}
	public int getImgType() {
		return imgType;
	}
	public void setImgType(int imgType) {
		this.imgType = imgType;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}

	
	

	
}

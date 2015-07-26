package com.aya.entity;

import java.util.ArrayList;
import java.util.List;


public class ChannelDb {
	
	private static List<Channel>   selectedChannel=new ArrayList<Channel>();
	
	static{
		selectedChannel.add(new Channel("T1348647909107","头条",0,
				"http://c.3g.163.com/nc/article/headline/T1348647909107/0-20.html",""));
		selectedChannel.add(new Channel("T1348648517839","娱乐",0,
				"http://c.3g.163.com/nc/article/list/T1348648517839/0-20.html",""));
		
		selectedChannel.add(new Channel("T1348649079062","体育",0,
				"http://c.3g.163.com/nc/article/list/T1348649079062/0-20.html",""));
		
		selectedChannel.add(new Channel("","北京",0,
				"http://c.m.163.com/nc/article/local/5YyX5Lqs/0-20.html",""));
		selectedChannel.add(new Channel("T1348648756099","财经",0,
				"http://c.3g.163.com/nc/article/list/T1348648756099/0-20.html",""));
		
		selectedChannel.add(new Channel("","热点",3,
				"http://c.m.163.com/recommend/getSubDocPic?passport=&devId=000000000000000&size=20",""));
		
		selectedChannel.add(new Channel("T1348649580692","科技",0,
				"http://c.m.163.com/nc/article/list/T1348649580692/0-20.html",""));
		selectedChannel.add(new Channel("","图片",1,
				"http://c.m.163.com/photo/api/list/0096/4GJ60096.json",""));
		selectedChannel.add(new Channel("T1348654060988","汽车",0,
				"http://c.m.163.com/nc/article/list/T1348654060988/0-20.html",""));
		selectedChannel.add(new Channel("T1348650593803","时尚",0,
				"http://c.m.163.com/nc/article/list/T1348650593803/0-20.html",""));
		selectedChannel.add(new Channel("T1348650593803","轻松一刻",0,
				"http://c.m.163.com/nc/article/list/T1350383429665/0-20.html",""));
		selectedChannel.add(new Channel("T1348648141035","军事",0,
				"http://c.m.163.com/nc/article/list/T1348648141035/0-20.html",""));
		selectedChannel.add(new Channel("T1368497029546","历史",0,
				
				"http://c.m.163.com/nc/article/list/T1368497029546/0-20.html",""));
		selectedChannel.add(new Channel("","房产",0,
				
				"http://c.m.163.com/nc/article/house/5YWo5Zu9/0-20.html",""));
		selectedChannel.add(new Channel("T1348654151579","游戏",0,
				"http://c.m.163.com/nc/article/list/T1348654151579/0-20.html",""));
		selectedChannel.add(new Channel("T1356600029035","彩票",0,
				"http://c.m.163.com/nc/article/list/T1356600029035/0-20.html",""));
		selectedChannel.add(new Channel("T1370583240249","原创",0,
				"http://c.m.163.com/nc/article/list/T1370583240249/0-20.html",""));
		selectedChannel.add(new Channel("T1414142214384","政务",0,
				"http://c.m.163.com/nc/article/list/T1414142214384/0-20.html",""));
		selectedChannel.add(new Channel("T1422935072191","画报",0,
				"http://c.m.163.com/nc/article/list/T1422935072191/0-20.html",""));
		selectedChannel.add(new Channel("T1348648037603","社会",0,
				"http://c.m.163.com/nc/article/list/T1348648037603/0-20.html",""));
		selectedChannel.add(new Channel("T1348648650048","影视",0,
				"http://c.m.163.com/nc/article/list/T1348648650048/0-20.html",""));
		selectedChannel.add(new Channel("T1348649503389","中国足球",0,
				"http://c.m.163.com/nc/article/list/T1348649503389/0-20.html",""));
		selectedChannel.add(new Channel("T1348649176279","国际足球",0,
				"http://c.m.163.com/nc/article/list/T1348649176279/0-20.html",""));
	}
	public static  List<Channel> getSelectedChannel(){
		 return selectedChannel;
	}
	
	private static List<Channel> unselectedChannel=new ArrayList<Channel>();
	static {
		unselectedChannel.add(new Channel("T1348649145984","NBA",0,
				"http://c.m.163.com/nc/article/list/T1348649145984/0-20.html",""));
		unselectedChannel.add(new Channel("T1348649475931","CBA",0,
				"http://c.m.163.com/nc/article/list/T1348649475931/0-20.html",""));
		unselectedChannel.add(new Channel("T1411113472760","跑步",0,
				"http://c.m.163.com/nc/article/list/T1411113472760/0-20.html",""));
		unselectedChannel.add(new Channel("T1348649654285","手机",0,
				"http://c.m.163.com/nc/article/list/T1348649654285/0-20.html",""));
		unselectedChannel.add(new Channel("T1348649776727","数码",0,
				"http://c.m.163.com/nc/article/list/T1348649776727/0-20.html",""));
		unselectedChannel.add(new Channel("T1351233117091","移动互联",0,
				"http://c.m.163.com/nc/article/list/T1351233117091/0-20.html",""));
		unselectedChannel.add(new Channel("T1421997195219","云课堂",0,
				"http://c.m.163.com/nc/article/list/T1421997195219/0-20.html",""));
		unselectedChannel.add(new Channel("T1348654105308","家居",0,
				"http://c.m.163.com/nc/article/list/T1348654105308/0-20.html",""));
		unselectedChannel.add(new Channel("T1348654204705","旅游",0,
				"http://c.m.163.com/nc/article/list/T1348654204705/0-20.html",""));
		unselectedChannel.add(new Channel("T1414389941036","健康",0,
				"http://c.m.163.com/nc/article/list/T1414389941036/0-20.html",""));
		unselectedChannel.add(new Channel("T1401272877187","读书",0,
				"http://c.m.163.com/nc/article/list/T1401272877187/0-20.html",""));
		unselectedChannel.add(new Channel("T1385429690972","酒香",0,
				"http://c.m.163.com/nc/article/list/T1385429690972/0-20.html",""));
		unselectedChannel.add(new Channel("T1348654225495","教育",0,
				"http://c.m.163.com/nc/article/list/T1348654225495/0-20.html",""));
		unselectedChannel.add(new Channel("T1397116135282","亲子",0,
				"http://c.m.163.com/nc/article/list/T1397116135282/0-20.html",""));
		unselectedChannel.add(new Channel("T1402031665628","葡萄酒",0,
				"http://c.m.163.com/nc/article/list/T1402031665628/0-20.html",""));
		unselectedChannel.add(new Channel("T1432711055315","你照吗",0,
				"http://c.m.163.com/nc/article/list/T1432711055315/0-20.html",""));
		unselectedChannel.add(new Channel("T1397016069906","暴雪游戏",0,
				"http://c.m.163.com/nc/article/list/T1397016069906/0-20.html",""));
		unselectedChannel.add(new Channel("T1348650839000","情感",0,
				"http://c.m.163.com/nc/article/list/T1348650839000/0-20.html",""));
		unselectedChannel.add(new Channel("T1420771091297","值得买",0,
				"http://c.m.163.com/nc/article/list/T1420771091297/0-20.html",""));
		unselectedChannel.add(new Channel("T1419315959525","跟帖",0,
				"http://c.m.163.com/nc/article/list/T1419315959525/0-20.html",""));
		unselectedChannel.add(new Channel("T1349837698345","博客",0,
				"http://c.m.163.com/nc/article/list/T1349837698345/0-20.html",""));
		unselectedChannel.add(new Channel("T1349837670307","论坛",0,
				"http://c.m.163.com/nc/article/list/T1349837670307/0-20.html",""));
		
	}
	
	public static List<Channel> getUnselectedChannel(){
		return unselectedChannel;
	}
	
}

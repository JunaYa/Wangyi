package com.aya.entity;

import java.util.ArrayList;
import java.util.List;


public class ChannelDb {
	
	private static List<Channel>   selectedChannel=new ArrayList<Channel>();
	
	static{
		selectedChannel.add(new Channel("T1348647909107","ͷ��",0,
				"http://c.3g.163.com/nc/article/headline/T1348647909107/0-20.html",""));
		selectedChannel.add(new Channel("T1348648517839","����",0,
				"http://c.3g.163.com/nc/article/list/T1348648517839/0-20.html",""));
		
		selectedChannel.add(new Channel("T1348649079062","����",0,
				"http://c.3g.163.com/nc/article/list/T1348649079062/0-20.html",""));
		
		selectedChannel.add(new Channel("","����",0,
				"http://c.m.163.com/nc/article/local/5YyX5Lqs/0-20.html",""));
		selectedChannel.add(new Channel("T1348648756099","�ƾ�",0,
				"http://c.3g.163.com/nc/article/list/T1348648756099/0-20.html",""));
		
		selectedChannel.add(new Channel("","�ȵ�",3,
				"http://c.m.163.com/recommend/getSubDocPic?passport=&devId=000000000000000&size=20",""));
		
		selectedChannel.add(new Channel("T1348649580692","�Ƽ�",0,
				"http://c.m.163.com/nc/article/list/T1348649580692/0-20.html",""));
		selectedChannel.add(new Channel("","ͼƬ",1,
				"http://c.m.163.com/photo/api/list/0096/4GJ60096.json",""));
		selectedChannel.add(new Channel("T1348654060988","����",0,
				"http://c.m.163.com/nc/article/list/T1348654060988/0-20.html",""));
		selectedChannel.add(new Channel("T1348650593803","ʱ��",0,
				"http://c.m.163.com/nc/article/list/T1348650593803/0-20.html",""));
		selectedChannel.add(new Channel("T1348650593803","����һ��",0,
				"http://c.m.163.com/nc/article/list/T1350383429665/0-20.html",""));
		selectedChannel.add(new Channel("T1348648141035","����",0,
				"http://c.m.163.com/nc/article/list/T1348648141035/0-20.html",""));
		selectedChannel.add(new Channel("T1368497029546","��ʷ",0,
				
				"http://c.m.163.com/nc/article/list/T1368497029546/0-20.html",""));
		selectedChannel.add(new Channel("","����",0,
				
				"http://c.m.163.com/nc/article/house/5YWo5Zu9/0-20.html",""));
		selectedChannel.add(new Channel("T1348654151579","��Ϸ",0,
				"http://c.m.163.com/nc/article/list/T1348654151579/0-20.html",""));
		selectedChannel.add(new Channel("T1356600029035","��Ʊ",0,
				"http://c.m.163.com/nc/article/list/T1356600029035/0-20.html",""));
		selectedChannel.add(new Channel("T1370583240249","ԭ��",0,
				"http://c.m.163.com/nc/article/list/T1370583240249/0-20.html",""));
		selectedChannel.add(new Channel("T1414142214384","����",0,
				"http://c.m.163.com/nc/article/list/T1414142214384/0-20.html",""));
		selectedChannel.add(new Channel("T1422935072191","����",0,
				"http://c.m.163.com/nc/article/list/T1422935072191/0-20.html",""));
		selectedChannel.add(new Channel("T1348648037603","���",0,
				"http://c.m.163.com/nc/article/list/T1348648037603/0-20.html",""));
		selectedChannel.add(new Channel("T1348648650048","Ӱ��",0,
				"http://c.m.163.com/nc/article/list/T1348648650048/0-20.html",""));
		selectedChannel.add(new Channel("T1348649503389","�й�����",0,
				"http://c.m.163.com/nc/article/list/T1348649503389/0-20.html",""));
		selectedChannel.add(new Channel("T1348649176279","��������",0,
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
		unselectedChannel.add(new Channel("T1411113472760","�ܲ�",0,
				"http://c.m.163.com/nc/article/list/T1411113472760/0-20.html",""));
		unselectedChannel.add(new Channel("T1348649654285","�ֻ�",0,
				"http://c.m.163.com/nc/article/list/T1348649654285/0-20.html",""));
		unselectedChannel.add(new Channel("T1348649776727","����",0,
				"http://c.m.163.com/nc/article/list/T1348649776727/0-20.html",""));
		unselectedChannel.add(new Channel("T1351233117091","�ƶ�����",0,
				"http://c.m.163.com/nc/article/list/T1351233117091/0-20.html",""));
		unselectedChannel.add(new Channel("T1421997195219","�ƿ���",0,
				"http://c.m.163.com/nc/article/list/T1421997195219/0-20.html",""));
		unselectedChannel.add(new Channel("T1348654105308","�Ҿ�",0,
				"http://c.m.163.com/nc/article/list/T1348654105308/0-20.html",""));
		unselectedChannel.add(new Channel("T1348654204705","����",0,
				"http://c.m.163.com/nc/article/list/T1348654204705/0-20.html",""));
		unselectedChannel.add(new Channel("T1414389941036","����",0,
				"http://c.m.163.com/nc/article/list/T1414389941036/0-20.html",""));
		unselectedChannel.add(new Channel("T1401272877187","����",0,
				"http://c.m.163.com/nc/article/list/T1401272877187/0-20.html",""));
		unselectedChannel.add(new Channel("T1385429690972","����",0,
				"http://c.m.163.com/nc/article/list/T1385429690972/0-20.html",""));
		unselectedChannel.add(new Channel("T1348654225495","����",0,
				"http://c.m.163.com/nc/article/list/T1348654225495/0-20.html",""));
		unselectedChannel.add(new Channel("T1397116135282","����",0,
				"http://c.m.163.com/nc/article/list/T1397116135282/0-20.html",""));
		unselectedChannel.add(new Channel("T1402031665628","���Ѿ�",0,
				"http://c.m.163.com/nc/article/list/T1402031665628/0-20.html",""));
		unselectedChannel.add(new Channel("T1432711055315","������",0,
				"http://c.m.163.com/nc/article/list/T1432711055315/0-20.html",""));
		unselectedChannel.add(new Channel("T1397016069906","��ѩ��Ϸ",0,
				"http://c.m.163.com/nc/article/list/T1397016069906/0-20.html",""));
		unselectedChannel.add(new Channel("T1348650839000","���",0,
				"http://c.m.163.com/nc/article/list/T1348650839000/0-20.html",""));
		unselectedChannel.add(new Channel("T1420771091297","ֵ����",0,
				"http://c.m.163.com/nc/article/list/T1420771091297/0-20.html",""));
		unselectedChannel.add(new Channel("T1419315959525","����",0,
				"http://c.m.163.com/nc/article/list/T1419315959525/0-20.html",""));
		unselectedChannel.add(new Channel("T1349837698345","����",0,
				"http://c.m.163.com/nc/article/list/T1349837698345/0-20.html",""));
		unselectedChannel.add(new Channel("T1349837670307","��̳",0,
				"http://c.m.163.com/nc/article/list/T1349837670307/0-20.html",""));
		
	}
	
	public static List<Channel> getUnselectedChannel(){
		return unselectedChannel;
	}
	
}

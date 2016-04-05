package com.util.documentutil.xml;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;


public class ObjectToXml {
	public static String ObjectToXMLStr(Object obj) {
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			OutputStream out = new ByteArrayOutputStream();
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
			marshaller.marshal(obj, out);
			return out.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void ObjectToXML(Object obj, String filePath) {
		try {
			JAXBContext context = JAXBContext.newInstance(obj.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
			marshaller.marshal(obj, new File(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		FootPlayer player=new FootPlayer();
		player.setAge(30);
		player.setName("kaka2");
		player.setClub("AC米兰");
		player.setCountry("brazil");
		player.setSex(Sex.BOY);
		//player.setSimpledes("卡卡是位超级球星");
		FootPlayer player2=new FootPlayer();
		player2.setAge(29);
		player2.setName("messi");
		player2.setClub("巴塞罗那");
		player2.setCountry("阿根廷");
		player2.setSex(Sex.GIRL);
		player2.setSimpledes("梅西是位超级球星ffffff");
		List<String> sonList=new ArrayList<String>();
		sonList.add("son1");
		sonList.add("son2");
		player2.setSonList(sonList);
		List<FootPlayer> team=new ArrayList<FootPlayer>();
		team.add(player);
		team.add(player2);
		SerList list=new SerList();
		list.setSoccer(team);
		ObjectToXml.ObjectToXML(list, "conf/footballplayer.xml");
	}
}

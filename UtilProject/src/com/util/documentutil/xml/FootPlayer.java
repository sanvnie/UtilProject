package com.util.documentutil.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="football")
public class FootPlayer {
	private String name;
	private String club;
	private String country;
	private Sex sex;
	private int age;
	private transient volatile String simpledes;
	private List<String> sonList;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClub() {
		return club;
	}
	public void setClub(String club) {
		this.club = club;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Sex getSex() {
		return sex;
	}
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSimpledes() {
		return simpledes;
	}
	public void setSimpledes(String simpledes) {
		this.simpledes = simpledes;
	}
	public List<String> getSonList() {
		return sonList;
	}
	public void setSonList(List<String> sonList) {
		this.sonList = sonList;
	}
	
}

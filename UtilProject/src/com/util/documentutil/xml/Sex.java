package com.util.documentutil.xml;

public enum Sex {
	BOY("��"),GIRL("Ů");
	
	String des;
	
	Sex(String des){
		this.des=des;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	
}

package com.util.documentutil.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="team")
public class SerList extends ArrayList{
	private List<FootPlayer> soccer;

	public List<FootPlayer> getSoccer() {
		return soccer;
	}

	public void setSoccer(List<FootPlayer> soccer) {
		this.soccer = soccer;
	}

	
	
}

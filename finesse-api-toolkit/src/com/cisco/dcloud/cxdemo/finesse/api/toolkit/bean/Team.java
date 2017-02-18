package com.cisco.dcloud.cxdemo.finesse.api.toolkit.bean;

import com.cisco.dcloud.cxdemo.finesse.api.toolkit.BaseApiBean;
import com.cisco.dcloud.cxdemo.finesse.api.toolkit.BaseApiListBean;

import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;


//@Path("Team")
//@XmlRootElement(name = "Team")
public class Team extends BaseApiBean {
	private String name;
	private int id;
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

//	@Path("Teams")
//	@XmlRootElement(name = "teams")
//	public static class TeamList extends BaseApiListBean<Team> {
////		@XmlElementWrapper(name = "teams")
//		@XmlElement(name = "Team")
//		@Override
//		public List<Team> getItems() {
//			return items;
//		}
//
//		@Override
//		public void setItems(List<Team> items) {
//			this.items = items;
//		}
//	}
}

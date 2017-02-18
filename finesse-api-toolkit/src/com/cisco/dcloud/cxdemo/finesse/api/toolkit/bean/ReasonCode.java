package com.cisco.dcloud.cxdemo.finesse.api.toolkit.bean;

import com.cisco.dcloud.cxdemo.finesse.api.toolkit.BaseApiBean;
import com.cisco.dcloud.cxdemo.finesse.api.toolkit.BaseApiListBean;

import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;


@Path("ReasonCode")
@XmlRootElement(name = "ReasonCode")
public class ReasonCode extends BaseApiBean {
	private String category;
	private int code;
	private String label;
	private boolean forAll;
	private int id;

	
	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getUri() {
		return uri;
	}


	public void setUri(String uri) {
		this.uri = uri;
	}


	public int getCode() {
		return code;
	}


	public void setCode(int code) {
		this.code = code;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public boolean isForAll() {
		return forAll;
	}


	public void setForAll(boolean forAll) {
		this.forAll = forAll;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	@Path("ReasonCodes")
	@XmlRootElement(name = "ReasonCodes")
	public static class UserList extends BaseApiListBean<ReasonCode> {
		@XmlElement(name = "ReasonCode")
		@Override
		public List<ReasonCode> getItems() {
			return items;
		}

		@Override
		public void setItems(List<ReasonCode> items) {
			this.items = items;
		}
	}
}

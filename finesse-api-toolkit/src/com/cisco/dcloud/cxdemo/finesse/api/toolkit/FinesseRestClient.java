package com.cisco.dcloud.cxdemo.finesse.api.toolkit;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;

import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

public class FinesseRestClient extends RestClient {
	/** Base URL for the Finesse API */
	public final static String baseUrl = "/finesse/api/";
	
	public FinesseRestClient(String hostName, String username, String password) {
		this(hostName, 443, username, password);
	}

	public FinesseRestClient(String hostName, int port, String username, String password) {
		super(hostName, port, username, password, baseUrl);
	}
//	
//
//	@SuppressWarnings("rawtypes")
//	public <T extends BaseApiListBean> T getList (
//			Class<T> beanType, 
//			String path) {
//		if(StringUtils.isEmpty(path)) {
//			path = beanType.getAnnotation(Path.class).value();
//		}
//
//		try {
//			WebResource r = service.path(baseUrl + path);
//			return r.accept(MediaType.APPLICATION_XML).get(beanType);
//		} catch(UniformInterfaceException e){
//			handleErrors(e.getResponse());
//		}
//
//		return null;
//	}

}
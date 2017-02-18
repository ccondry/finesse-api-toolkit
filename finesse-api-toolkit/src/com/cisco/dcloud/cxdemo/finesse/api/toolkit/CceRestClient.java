package com.cisco.dcloud.cxdemo.finesse.api.toolkit;

import java.util.Map;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.lang3.StringUtils;

import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

public class CceRestClient extends RestClient {
	/** Base URL for the Webconfig API */
	public final static String baseUrl = "/unifiedconfig/config/";
	
	public CceRestClient(String hostName, String username, String password) {
		this(hostName, 443, username, password);
	}

	public CceRestClient(String hostName, int port, String username, String password) {
		super(hostName, port, username, password, baseUrl);
	}
	
	/**
	 * Returns a list bean object matching the bean type containing zero or more items with a custom path to the service.
	 *
	 * @param beanType type of object to get back
	 * @param searchCriteria search parameter (ignored if null.
	 * @param path customized path to the service - eg campaign/<id>/import
	 * @return list bean object of the
	 */
	@SuppressWarnings("rawtypes")
	public <T extends BaseApiListBean> T getList (
			Class<T> beanType, 
			String searchCriteria, 
			String path, 
			Map<String, String> params) throws ApiException{
		if(StringUtils.isNotBlank(searchCriteria)) {
			params.put("q", searchCriteria);
		}

		MultivaluedMap<String, String> queryParams = RestUtils.map2MultivaluedMap(params);

		return getList(beanType, path, queryParams);
	}

	@SuppressWarnings("rawtypes")
	public <T extends BaseApiListBean> T getList (
			Class<T> beanType, 
			String path,
			MultivaluedMap<String, String> queryParams) {
		if(StringUtils.isEmpty(path)) {
			path = beanType.getAnnotation(Path.class).value();
		}

		try {
			WebResource r = service.path(baseUrl + path).queryParams(queryParams);
			return r.accept(MediaType.APPLICATION_XML).get(beanType);
		} catch(UniformInterfaceException e){
			handleErrors(e.getResponse());
		}

		return null;
	}
}

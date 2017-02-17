package com.cisco.dcloud.cxdemo.finesse.api.toolkit;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.Path;
import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.core.util.MultivaluedMapImpl;

public class RestUtils {

	public static <T extends BaseApiBean> ReferenceBean makeReferenceBean(T bean) {
		return new ReferenceBean(bean.getRefURL());
	}
	
	/**
	 * Get a base refURL string to be used to create a refURL or ReferenceBean from the input class
	 * @param beanClass the class of BaseApiBean you want to get a base refURL for 
	 * @return the base refURL string, ending with "/"
	 */
	public static <T extends BaseApiBean> String getRefUrlBase(RestClient restClient, Class<T> beanClass) {
		return restClient.getBaseUrl() + beanClass.getAnnotation(Path.class).value() + "/";
	}
	
	/**
	 * Get a ReferenceBean from input BaseApiBean class and resource id
	 * @param beanClass the class of BaseApiBean you want to get a base refURL for 
	 * @param id the id of the specific beanClass resource you are referencing
	 * @return the ReferenceBean
	 */
	public static <T extends BaseApiBean> ReferenceBean makeReferenceBean(RestClient restClient, Class<T> beanClass, String id) {
		String refUrl = getRefUrlBase(restClient, beanClass) + id;
		ReferenceBean refBean = new ReferenceBean(refUrl);
		return refBean;
	}
	
	public static <T extends BaseApiBean, L extends BaseApiListBean<T>> List<T> lookupMultiple(RestClient restClient, String query, Class<T> beanType, Class<L> beanListType) {
		L list;
		try {
			list = restClient.getList(beanListType, query);
		} catch (ApiException e) {
			return null;
		}

		if(list.getItems().size() == 0) {
			return null;
		}
		return list.getItems();
	}

	public static <T extends BaseApiBean, L extends BaseApiListBean<T>> List<T> lookupMultiple(
			RestClient restClient, 
			String query, 
			Class<T> beanType, 
			Class<L> beanListType,
			Map<String, String> params) {
		L list;
		try {
			list = restClient.getList(beanListType, query, "", params);
		} catch (ApiException e) {
			return null;
		}

		if(list.getItems().size() == 0) {
			return null;
		}
		return list.getItems();
	}

	public static Map<String, String> multivaluedMap2HashMap(MultivaluedMap<String, String> mvmap) {
		Map<String, String> map = new HashMap<String, String>();
		if (mvmap == null) {
			return map;
		}
		for (Entry<String, List<String>> entry : mvmap.entrySet()) {
			StringBuilder sb = new StringBuilder();
			for (String s : entry.getValue()) {
				if (sb.length() > 0) {
					sb.append(',');
				}
				sb.append(s);
			}
			map.put(entry.getKey(), sb.toString());
		}
		return map;
	}

	public static MultivaluedMap<String, String> map2MultivaluedMap(Map<String, String> map) {
		MultivaluedMap<String, String> mvmap = new MultivaluedMapImpl();
		// return empty mvmap if input is null
		if (map == null) {
			return mvmap;
		}
		// iterate over the input map and stuff those strings into the mvmap
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()){
			String key = (String)it.next();
			mvmap.add(key, (String) map.get(key));
		}
	
		return mvmap;
	}
}

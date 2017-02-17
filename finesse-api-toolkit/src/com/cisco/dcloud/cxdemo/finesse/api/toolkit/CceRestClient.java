package com.cisco.dcloud.cxdemo.finesse.api.toolkit;

public class CceRestClient extends RestClient {
	/** Base URL for the Webconfig API */
	public final static String baseUrl = "/unifiedconfig/config/";
	
	public CceRestClient(String hostName, String username, String password) {
		super(hostName, username, password, baseUrl);
	}

}

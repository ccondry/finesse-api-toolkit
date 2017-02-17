package com.cisco.dcloud.cxdemo.finesse.api.toolkit;

public class FinesseRestClient extends RestClient {
	/** Base URL for the Finesse API */
	public final static String baseUrl = "/finesse/api/";
	
	public FinesseRestClient(String hostName, String username, String password) {
		super(hostName, username, password, baseUrl);
	}

}

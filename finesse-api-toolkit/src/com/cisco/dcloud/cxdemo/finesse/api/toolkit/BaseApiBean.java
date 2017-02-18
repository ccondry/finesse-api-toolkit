package com.cisco.dcloud.cxdemo.finesse.api.toolkit;


/**
 * Base Bean for making REST API call.
 */
public abstract class BaseApiBean extends BaseBean{

	protected String uri;
	
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
}

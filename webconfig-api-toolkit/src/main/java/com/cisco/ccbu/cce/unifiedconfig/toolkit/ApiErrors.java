package com.cisco.ccbu.cce.unifiedconfig.toolkit;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class defines the entity attached to HTTP response in error conditions
 */
@XmlRootElement(name = "apiErrors")
public class ApiErrors extends BaseBean{

	@XmlElement(name = "apiError")
	private final List<ApiError> apiErrors;
	
	public ApiErrors(){
		apiErrors = new ArrayList<>();
	}

	public ApiErrors(List<ApiError> apiErrors){
		this.apiErrors = apiErrors;
	}

	public List<ApiError> getApiErrors(){
		return apiErrors;
	}

}

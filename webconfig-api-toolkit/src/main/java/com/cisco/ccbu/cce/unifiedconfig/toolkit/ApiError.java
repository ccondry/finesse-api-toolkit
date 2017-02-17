package com.cisco.ccbu.cce.unifiedconfig.toolkit;

import com.cisco.ccbu.cce.unifiedconfig.toolkit.BaseBean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class defines the entity attached to HTTP response in error conditions
 */
@XmlRootElement(name = "apiError")
public class ApiError extends BaseBean {
	private String errorType;
	private String errorData;
	private String errorMessage;

	/**
	 * Constructor
	 */
    public ApiError(){
    }

    public ApiError(String type, String data, String message){
        this.errorType = type;
        this.errorData = data;
        this.errorMessage = message;
    }

	/**
	 * @return the errorType
	 */
	public String getErrorType(){
		return errorType;
	}

	/**
	 * @param errorType the errorType to set
	 */
	public void setErrorType(String errorType){
		this.errorType = errorType;
	}

	/**
	 * @return the errorData
	 */
	public String getErrorData(){
		return errorData;
	}

	/**
	 * @param errorData the errorData to set
	 */
	public void setErrorData(String errorData){
		this.errorData = errorData;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage(){
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage){
		this.errorMessage = errorMessage;
	}

}

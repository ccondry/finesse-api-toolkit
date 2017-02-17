package com.cisco.dcloud.cxdemo.finesse.api.toolkit;


/**
 * Exception class that contains API Errors.
 *
 * If the errors can be unmarshalled, they are stored in the ApiErrors variable.
 * If not the returned text is found in the message variable.
 */
@SuppressWarnings("serial")
public class ApiException extends RuntimeException{

	private transient ApiErrors errors;

    private String message;

	public ApiException(){
	}

    public ApiException(String message){
        this.message = message;
    }

	public ApiException(ApiErrors errors){
	    this.errors = errors;
	}

	public ApiErrors getErrors(){
		return this.errors;
	}

    public String getMessage(){
        return message;
    }
}

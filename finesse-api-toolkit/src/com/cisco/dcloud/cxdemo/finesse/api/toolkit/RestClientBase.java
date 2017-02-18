package com.cisco.dcloud.cxdemo.finesse.api.toolkit;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

import javax.net.ssl.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

/**
 * A simple SSL REST Client wrapper to handle CRUD API calls to the webconfig service.
 */
public abstract class RestClientBase {
	/** Jersey Web Resource to handle CRUD calls */
	protected WebResource service = null;

	/** JAXB Context for handling Errors */
	protected static final JAXBContext jc = initContext();

	/** Init the context */
	protected static synchronized JAXBContext initContext(){

		try {
			return JAXBContext.newInstance(ApiError.class, ApiErrors.class);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Parameterized Constructor
	 *
	 * @param hostName fully qualified host name or IP
	 * @param port 
	 * @param username fully qualified username (with @domain)
	 * @param password users password
	 */
	public RestClientBase(String hostName, int port, String username, String password){

		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager(){
			public X509Certificate[] getAcceptedIssuers(){return null;}
			public void checkClientTrusted(X509Certificate[] certs, String authType){}
			public void checkServerTrusted(X509Certificate[] certs, String authType){}
		}};

		// Install the all-trusting trust manager
		SSLContext context;
		try {
			context=SSLContext.getInstance("SSL");
			context.init(null, trustAllCerts, null);
		} catch (NoSuchAlgorithmException | KeyManagementException e) {
			e.printStackTrace();
			throw new ApiException("couldn't initialize SSL: " + e.getMessage());
		}

		// turn the hostname verification off.
		HttpsURLConnection.setDefaultHostnameVerifier( new HostnameVerifier(){
			public boolean verify(String string,SSLSession ssls) {
				return true;
			}
		});

		ClientConfig config=new DefaultClientConfig();
		config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES, new HTTPSProperties(null,context));

		service = Client.create(config).resource("https://" + hostName + ":" + port);
		service.addFilter(new HTTPBasicAuthFilter(username,password));
		//        service.addFilter(new LoggingFilter(System.out));
	}
}

package com.cisco.dcloud.cxdemo.finesse.api.toolkit;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * A simple SSL REST Client wrapper to handle CRUD API calls to the webconfig service.
 */
public class RestClient extends RestClientBase {

	private String baseUrl;

	public RestClient(String hostName, String username, String password, String baseUrl) {
		super(hostName, username, password);
		this.setBaseUrl(baseUrl);
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	public String getBaseUrl() {
		return this.baseUrl;
	}

	/**
	 * Returns an object of the passed in class type by ID.
	 *
	 * @param beanType type of object to get back
	 * @param refURL relative URL to the object
	 * @return object of the bean type with the id
	 */
	public <T extends BaseApiBean> T get(Class<T> beanType, String refURL) throws ApiException{

		try{
			return service.path(refURL).accept(MediaType.APPLICATION_XML).get(beanType);
		}catch(UniformInterfaceException e){
			handleErrors(e.getResponse());
		}

		return null;
	}

	/**
	 * Returns the object with the specified ID.
	 */
	public <T extends BaseApiBean> T getById(Class<T> beanType, String id) throws ApiException{
		return get(beanType, baseUrl + beanType.getAnnotation(Path.class).value() + "/" + id);
	}

	/**
	 * Deletes and object of the bean type with the id.
	 *
	 * @param refURL relative URL of the object to delete
	 */
	public void delete(String refURL) throws ApiException {

		try{
			service.path(refURL).delete();
		}catch(UniformInterfaceException e){
			handleErrors(e.getResponse());
		}
	}

	/**
	 * Deletes the object with the specified ID.
	 */
	public void deleteById(Class<? extends BaseApiBean> clazz, String id) throws ApiException {
		delete(baseUrl + clazz.getAnnotation(Path.class).value() + "/" + id);
	}

	/**
	 * Returns a list bean object matching the bean type containing zero or more items.
	 *
	 * @param beanType type of object to get back
	 * @return list bean object of the
	 */
	@SuppressWarnings("rawtypes")
	public <T extends BaseApiListBean> T getList(Class<T> beanType) throws ApiException{
		return getList(beanType, null);
	}

	/**
	 * Returns a list bean object matching the bean type containing zero or more items.
	 *
	 * @param beanType type of object to get back
	 * @param searchCriteria search parameter (ignored if null.
	 * @return list bean object of the
	 */
	@SuppressWarnings("rawtypes")
	public <T extends BaseApiListBean> T getList(Class<T> beanType, String searchCriteria) throws ApiException{
		return getList(beanType, searchCriteria, "");
	}

	/**
	 * Returns a list bean object matching the bean type containing zero or more items with a custom path to the service.
	 *
	 * @param beanType type of object to get back
	 * @param searchCriteria search parameter (ignored if null.
	 * @param path customized path to the service - eg campaign/<id>/import
	 * @return list bean object of the
	 */
	public <T extends BaseApiListBean<?>> T getList(Class<T> beanType, String searchCriteria, String path) throws ApiException{

		MultivaluedMap<String, String> params = new MultivaluedMapImpl();

		if(StringUtils.isNotBlank(searchCriteria)){
			params.add("q", searchCriteria);
		}

		if(StringUtils.isEmpty(path)) {
			path = beanType.getAnnotation(Path.class).value();
		}

		try {
			WebResource r = service.path(baseUrl + path).queryParams(params);
			return r.accept(MediaType.APPLICATION_XML).get(beanType);
		} catch(UniformInterfaceException e){
			handleErrors(e.getResponse());
		}

		return null;
	}

	/**
	 * Make a post request to the bean's API.  i.e. if the bean is Agent, attempts to create an agent.
	 *
	 * @param bean bean to create
	 * @return service response
	 */
	public <T extends BaseApiBean> ClientResponse create(T bean) throws ApiException{

		return create(bean, null);
	}

	/**
	 * Make a post request to the bean's API.  i.e. if the bean is Agent, attempts to create an agent.
	 *
	 * @param bean bean to create
	 * @param path path to the service
	 * @return service response
	 */
	public <T extends BaseApiBean> ClientResponse create(T bean, String path) throws ApiException{
		if(path==null)
		{
			path = bean.getClass().getAnnotation(Path.class).value();
		}

		try{
			ClientResponse response = service.path(baseUrl + path).
					type("application/xml").post(ClientResponse.class, bean);

			// If the response was processed correctly
			if(response.getStatus() != 200 && response.getStatus() != 201 && response.getStatus() != 202){
				handleErrors(response);
			}

			return response;
		}catch(UniformInterfaceException e){
			handleErrors(e.getResponse());
		}

		return null;
	}

	/**
	 * Makes a post request and returns the newly created objects ID.
	 *
	 * @param bean object to create
	 * @return ID of newly created object
	 */
	public <T extends BaseApiBean> String createAndGet(T bean) throws ApiException{
		return createAndGet(bean, null);
	}

	/**
	 * Makes a post request with the custom path (if available) and returns the newly created object's ID.
	 *
	 * @param bean bean object to create
	 * @param path path to the service
	 * @return ID of the newly created object
	 * @throws ApiException
	 */
	public <T extends BaseApiBean> String createAndGet(T bean, String path) throws ApiException{
		ClientResponse response = (StringUtils.isBlank(path)) ? create(bean) : create(bean, path);

		String url = response.getHeaders().get("Location").get(0);

		// Make it relative
		String relativeURL = null;
		try{
			relativeURL =  new URL(url).getPath();
		}catch (MalformedURLException e){
			e.printStackTrace();
		}

		return relativeURL;
	}
	/**
	 * Makes a post request and returns the newly created object
	 *
	 * @param bean object to create
	 * @return newly created object
	 */
	public <T extends BaseApiBean> T createAndGetBean(T bean) throws ApiException{
		return createAndGetBean(bean, "");
	}

	/**
	 * Makes a post request with the custom path (if available) and returns the newly created object
	 *
	 * @param bean object to create
	 * @param path path to the service
	 * @return newly created object
	 */
	@SuppressWarnings("unchecked")
	public <T extends BaseApiBean> T createAndGetBean(T bean, String path) throws ApiException{
		return get((Class<T>)bean.getClass(), createAndGet(bean, path));
	}


	/**
	 * Makes a put request to update the object with the values in the bean.
	 *
	 * @param bean object to update
	 * @return response
	 */
	public ClientResponse update(BaseApiBean bean) throws ApiException {
		try{
			ClientResponse response = service.path(bean.getRefURL()).type("application/xml").put(ClientResponse.class, bean);

			if(response.getStatus() != 200 && response.getStatus() != 201 && response.getStatus() != 202){
				handleErrors(response);
			}

			return response;
		}catch(UniformInterfaceException e){
			handleErrors(e.getResponse());
		}

		return null;
	}

	/**
	 * Makes a put request that contains no body.
	 * @param path the path
	 * @param async whether to run as async
	 * @throws ApiException
	 */
	public void updateEmptyBody(String path, boolean async) throws ApiException {
		try {
			service.path(baseUrl + path).queryParam("async",String.valueOf(async)).type("text/plain").entity("").put();
		} catch(UniformInterfaceException e){
			handleErrors(e.getResponse());
		}
	}

	/**
	 * Makes a put request to update the object with the values in the bean and then
	 * performs a get on the object.
	 *
	 * @param bean object to update
	 * @return the object's data after update
	 */
	@SuppressWarnings("unchecked")
	public <T extends BaseApiBean> T updateAndGetBean(T bean) throws ApiException{

		update(bean);
		return (T)get(bean.getClass(), bean.getRefURL());
	}

	private void handleErrors(ClientResponse response) {
		InputStream errorStream = response.getEntityInputStream();

		String responseText;
		try {
			responseText = IOUtils.toString(errorStream);
		} catch (IOException e) {
			throw new ApiException(e.getMessage());
		}

		if (StringUtils.isNotBlank(responseText)) {
			try {
				throw new ApiException((ApiErrors)jc.createUnmarshaller().unmarshal(new StringReader(responseText)));
			} catch (JAXBException e) {
				throw new ApiException(responseText);
			}
		} else {
			ClientResponse.Status status = response.getClientResponseStatus();
			throw new ApiException(response.getStatus() + (status != null ? " " + status.getReasonPhrase() : ""));
		}
	}

	/**
	 * Returns an object of the passed in class type by ID.
	 *
	 * @param class1 type of object to get back
	 * @param refURL relative URL to the object
	 * @param paramMap Map of the URL parameters to include in the GET REST request
	 * @return object of the bean type with the id
	 */
	public <T extends BaseApiBean> T get(Class<T> class1, String refURL, Map<String, String> paramMap) throws ApiException{
		try{
			MultivaluedMap<String, String> params = RestUtils.map2MultivaluedMap(paramMap);
			return service.path(refURL).queryParams(params).accept(MediaType.APPLICATION_XML).get(class1);
		}catch(UniformInterfaceException e){
			handleErrors(e.getResponse());
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public <T extends BaseApiBean> T createAndGetBean(T bean, Map<String, String> params) {
		return get((Class<T>)bean.getClass(), createAndGet(bean), params);
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

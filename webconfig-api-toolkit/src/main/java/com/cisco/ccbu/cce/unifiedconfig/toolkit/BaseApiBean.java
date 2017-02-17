package com.cisco.ccbu.cce.unifiedconfig.toolkit;

import javax.ws.rs.Path;

/**
 * Base Bean for making REST API call.
 */
public abstract class BaseApiBean extends BaseBean{

    private String refURL;
    private Integer changeStamp;

    public Integer getChangeStamp() {
        return changeStamp;
    }

    public void setChangeStamp(Integer changeStamp) {
        this.changeStamp = changeStamp;
    }

    public String getRefURL() {
        return refURL;
    }

    public void setRefURL(String refURL) {
        this.refURL = refURL;
    }

    public void setPath(String path) {
        setRefURL(RESTClient.baseUrl + path);
    }

    public void initRefURL() {
        setPath(getClass().getAnnotation(Path.class).value());
    }

}

package com.cisco.ccbu.cce.unifiedconfig.toolkit;

/**
 * Reference Bean for associating one bean type with another.
 */
public class ReferenceBean extends BaseBean{

    private String refURL;
    private String name;

    public ReferenceBean(){

    }

    public ReferenceBean(String refURL){
        this.refURL = refURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRefURL() {
        return refURL;
    }

    public void setRefURL(String refURL) {
        this.refURL = refURL;
    }

}

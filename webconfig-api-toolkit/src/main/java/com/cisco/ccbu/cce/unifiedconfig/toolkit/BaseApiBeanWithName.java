package com.cisco.ccbu.cce.unifiedconfig.toolkit;

/**
 * Base Bean for making REST API call.
 */
public abstract class BaseApiBeanWithName extends BaseApiBean{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

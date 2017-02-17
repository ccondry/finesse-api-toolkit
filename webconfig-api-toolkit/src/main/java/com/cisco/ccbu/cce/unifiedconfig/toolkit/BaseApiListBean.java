package com.cisco.ccbu.cce.unifiedconfig.toolkit;

import java.util.List;

/**
 * Base class for all List Beans.
 */
public abstract class BaseApiListBean<T extends BaseApiBean>{

    /** List of items of the implementing type */
    protected List<T> items;

    /**
     * Override and add the  @XmlElementWrapper and @XmlElement tags.
     *
     * i.e.
     * @XmlElementWrapper(name = "agents")
     * @XmlElement(name = "agent")
     *
     * @return list of items of the implementing type
     */
    @SuppressWarnings("JavaDoc")
    public abstract List<T> getItems();

    /**
     * Sets the items into the list.
     * Needed for unmarshalling
     *
     * @param items the items to set
     */
    public abstract void setItems(List<T> items);
}
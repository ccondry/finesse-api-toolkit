// ----------------------------------------------
package com.cisco.ccbu.cce.unifiedconfig.toolkit.bean;

import com.cisco.ccbu.cce.unifiedconfig.toolkit.BaseApiBean;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import com.cisco.ccbu.cce.unifiedconfig.toolkit.ReferenceBean;
import java.util.Date;
import com.cisco.ccbu.cce.unifiedconfig.toolkit.BaseApiListBean;

import javax.xml.bind.annotation.XmlElement;

import javax.xml.bind.annotation.XmlElementWrapper;


@XmlRootElement(name="pageInfo")
public class PageInfo extends BaseApiBean {
  private String firstPage;
  private String lastPage;
  private String nextPage;
  private String prevPage;
  private Integer resultsPerPage;
  private Integer startIndex;
  private Integer totalResults;

  public String getFirstPage() {
     return this.firstPage;
  }

  public void setFirstPage(String firstPage) {
     this.firstPage = firstPage;
  }

  public String getLastPage() {
     return this.lastPage;
  }

  public void setLastPage(String lastPage) {
     this.lastPage = lastPage;
  }

  public String getNextPage() {
     return this.nextPage;
  }

  public void setNextPage(String nextPage) {
     this.nextPage = nextPage;
  }

  public String getPrevPage() {
     return this.prevPage;
  }

  public void setPrevPage(String prevPage) {
     this.prevPage = prevPage;
  }

  public Integer getResultsPerPage() {
     return this.resultsPerPage;
  }

  public void setResultsPerPage(Integer resultsPerPage) {
     this.resultsPerPage = resultsPerPage;
  }

  public Integer getStartIndex() {
     return this.startIndex;
  }

  public void setStartIndex(Integer startIndex) {
     this.startIndex = startIndex;
  }

  public Integer getTotalResults() {
     return this.totalResults;
  }

  public void setTotalResults(Integer totalResults) {
     this.totalResults = totalResults;
  }


}

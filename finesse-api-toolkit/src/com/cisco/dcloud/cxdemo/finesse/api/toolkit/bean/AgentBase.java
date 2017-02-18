package com.cisco.dcloud.cxdemo.finesse.api.toolkit.bean;

import com.cisco.dcloud.cxdemo.finesse.api.toolkit.*;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.ws.rs.Path;

@Path("agent")
@XmlSeeAlso({AgentSummary.class, User.class})
public class AgentBase extends BaseApiBean {
  private String agentId;
  private Boolean agentStateTrace;
  private ReferenceBean agentTeam;
  private String attributeValue;
  private Boolean canRemove;
  private Integer changeStamp;
  private String correlationId;
  private ReferenceBean department;
  private String description;
  private Boolean markDeletable;
//  private PeripheralRef peripheral;
//  private Person person;
  private String refURL;
  private Boolean selectedAttribute;
  private Boolean selectedSkillGroup;
  private Boolean supervisor;

  public String getAgentId() {
     return this.agentId;
  }

  public void setAgentId(String agentId) {
     this.agentId = agentId;
  }

  public Boolean getAgentStateTrace() {
     return this.agentStateTrace;
  }

  public void setAgentStateTrace(Boolean agentStateTrace) {
     this.agentStateTrace = agentStateTrace;
  }

  public ReferenceBean getAgentTeam() {
     return this.agentTeam;
  }

  public void setAgentTeam(ReferenceBean agentTeam) {
     this.agentTeam = agentTeam;
  }

  public String getAttributeValue() {
     return this.attributeValue;
  }

  public void setAttributeValue(String attributeValue) {
     this.attributeValue = attributeValue;
  }

  public Boolean getCanRemove() {
     return this.canRemove;
  }

  public void setCanRemove(Boolean canRemove) {
     this.canRemove = canRemove;
  }

  public Integer getChangeStamp() {
     return this.changeStamp;
  }

  public void setChangeStamp(Integer changeStamp) {
     this.changeStamp = changeStamp;
  }

  public String getCorrelationId() {
     return this.correlationId;
  }

  public void setCorrelationId(String correlationId) {
     this.correlationId = correlationId;
  }

  public ReferenceBean getDepartment() {
     return this.department;
  }

  public void setDepartment(ReferenceBean department) {
     this.department = department;
  }

  public String getDescription() {
     return this.description;
  }

  public void setDescription(String description) {
     this.description = description;
  }

  public Boolean getMarkDeletable() {
     return this.markDeletable;
  }

  public void setMarkDeletable(Boolean markDeletable) {
     this.markDeletable = markDeletable;
  }

//  public PeripheralRef getPeripheral() {
//     return this.peripheral;
//  }
//
//  public void setPeripheral(PeripheralRef peripheral) {
//     this.peripheral = peripheral;
//  }
//
//  public Person getPerson() {
//     return this.person;
//  }
//
//  public void setPerson(Person person) {
//     this.person = person;
//  }

  public String getRefURL() {
     return this.refURL;
  }

  public void setRefURL(String refURL) {
     this.refURL = refURL;
  }

  public Boolean getSelectedAttribute() {
     return this.selectedAttribute;
  }

  public void setSelectedAttribute(Boolean selectedAttribute) {
     this.selectedAttribute = selectedAttribute;
  }

  public Boolean getSelectedSkillGroup() {
     return this.selectedSkillGroup;
  }

  public void setSelectedSkillGroup(Boolean selectedSkillGroup) {
     this.selectedSkillGroup = selectedSkillGroup;
  }

  public Boolean getSupervisor() {
     return this.supervisor;
  }

  public void setSupervisor(Boolean supervisor) {
     this.supervisor = supervisor;
  }


}

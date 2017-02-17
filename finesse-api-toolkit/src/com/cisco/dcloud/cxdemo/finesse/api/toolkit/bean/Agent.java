package com.cisco.dcloud.cxdemo.finesse.api.toolkit.bean;
// ----------------------------------------------

import javax.xml.bind.annotation.XmlSeeAlso;

import com.cisco.dcloud.cxdemo.finesse.api.toolkit.BaseApiBean;
import com.cisco.dcloud.cxdemo.finesse.api.toolkit.ReferenceBean;

import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

import javax.xml.bind.annotation.XmlElementWrapper;


@Path("agent")
@XmlRootElement(name="agent")
public class Agent extends BaseApiBean {
  private ReferenceBean agentDeskSettings;
  private String agentId;
  private Boolean agentStateTrace;
  private ReferenceBean agentTeam;
  private String attributeValue;
//  private List<AttributeValueWithAttribute> attributes;
//  private List<AttributeValueWithAttribute> attributesAdded;
//  private List<AttributeValueWithAttribute> attributesRemoved;
  private Boolean canRemove;
  private Integer changeStamp;
  private String correlationId;
  private ReferenceBean defaultSkillGroup;
  private ReferenceBean department;
  private String description;
  private Boolean markDeletable;
//  private PeripheralRef peripheral;
//  private Person person;
  private String refURL;
  private Boolean selectedAttribute;
  private Boolean selectedSkillGroup;
  private List<ReferenceBean> skillGroups;
  private List<ReferenceBean> skillGroupsAdded;
  private List<ReferenceBean> skillGroupsRemoved;
  private Boolean supervisor;
  private List<ReferenceBean> supervisorTeams;

  public ReferenceBean getAgentDeskSettings() {
     return this.agentDeskSettings;
  }

  public void setAgentDeskSettings(ReferenceBean agentDeskSettings) {
     this.agentDeskSettings = agentDeskSettings;
  }

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

//  @XmlElementWrapper(name="agentAttributes")
//  @XmlElement(name="agentAttribute")
//  public List<AttributeValueWithAttribute> getAttributes() {
//     return this.attributes;
//  }

//  public void setAttributes(List<AttributeValueWithAttribute> attributes) {
//     this.attributes = attributes;
//  }

//  @XmlElementWrapper(name="agentAttributesAdded")
//  @XmlElement(name="agentAttribute")
//  public List<AttributeValueWithAttribute> getAttributesAdded() {
//     return this.attributesAdded;
//  }

//  public void setAttributesAdded(List<AttributeValueWithAttribute> attributesAdded) {
//     this.attributesAdded = attributesAdded;
//  }

//  @XmlElementWrapper(name="agentAttributesRemoved")
//  @XmlElement(name="agentAttribute")
//  public List<AttributeValueWithAttribute> getAttributesRemoved() {
//     return this.attributesRemoved;
//  }
//
//  public void setAttributesRemoved(List<AttributeValueWithAttribute> attributesRemoved) {
//     this.attributesRemoved = attributesRemoved;
//  }

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

  public ReferenceBean getDefaultSkillGroup() {
     return this.defaultSkillGroup;
  }

  public void setDefaultSkillGroup(ReferenceBean defaultSkillGroup) {
     this.defaultSkillGroup = defaultSkillGroup;
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

  @XmlElementWrapper(name="skillGroups")
  @XmlElement(name="skillGroup")
  public List<ReferenceBean> getSkillGroups() {
     return this.skillGroups;
  }

  public void setSkillGroups(List<ReferenceBean> skillGroups) {
     this.skillGroups = skillGroups;
  }

  @XmlElementWrapper(name="skillGroupsAdded")
  @XmlElement(name="skillGroup")
  public List<ReferenceBean> getSkillGroupsAdded() {
     return this.skillGroupsAdded;
  }

  public void setSkillGroupsAdded(List<ReferenceBean> skillGroupsAdded) {
     this.skillGroupsAdded = skillGroupsAdded;
  }

  @XmlElementWrapper(name="skillGroupsRemoved")
  @XmlElement(name="skillGroup")
  public List<ReferenceBean> getSkillGroupsRemoved() {
     return this.skillGroupsRemoved;
  }

  public void setSkillGroupsRemoved(List<ReferenceBean> skillGroupsRemoved) {
     this.skillGroupsRemoved = skillGroupsRemoved;
  }

  public Boolean getSupervisor() {
     return this.supervisor;
  }

  public void setSupervisor(Boolean supervisor) {
     this.supervisor = supervisor;
  }

  @XmlElementWrapper(name="supervisorTeams")
  @XmlElement(name="supervisorTeam")
  public List<ReferenceBean> getSupervisorTeams() {
     return this.supervisorTeams;
  }

  public void setSupervisorTeams(List<ReferenceBean> supervisorTeams) {
     this.supervisorTeams = supervisorTeams;
  }


//  @Path("agent")
//  @XmlRootElement(name = "results")
//  public static class AgentList extends BaseApiListBean<Agent> {
//    @XmlElementWrapper(name = "agents")
//    @XmlElement(name = "agent")
//    public List<Agent> getItems() {
//      return items;
//    }
//
//    public void setItems(List<Agent> items) {
//      this.items = items;
//    }
//  }
}

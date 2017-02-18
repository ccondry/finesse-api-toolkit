package com.cisco.dcloud.cxdemo.finesse.api.toolkit.bean;

import com.cisco.dcloud.cxdemo.finesse.api.toolkit.BaseApiBean;
import com.cisco.dcloud.cxdemo.finesse.api.toolkit.BaseApiListBean;
//import com.cisco.dcloud.cxdemo.finesse.api.toolkit.bean.Team.TeamList;

import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;


@Path("User")
@XmlRootElement(name = "User")
public class User extends BaseApiBean {
	private String pendingState;
	private String state;
	private String firstName;
	private String lastName;
	private String loginName;
	private AgentSettings settings;
	private String extension;
	private int loginId;
	private List<AgentRole> roles;
	private int mediaType;
	private int reasonCodeId;
	private int teamId;
	private String dialogs;
	private String teamName;
	private String stateChangeTime;
	private ReasonCode reasonCode;
	private MobileAgent mobileAgent;
	private List<Team> teams;

	public AgentSettings getSettings() {
		return this.settings;
	}

	public String getPendingState() {
		return pendingState;
	}

	public void setPendingState(String pendingState) {
		this.pendingState = pendingState;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setSettings(AgentSettings settings) {
		this.settings = settings;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public List<AgentRole> getRoles() {
		return roles;
	}

	public void setRoles(List<AgentRole> roles) {
		this.roles = roles;
	}

	public int getMediaType() {
		return mediaType;
	}

	public void setMediaType(int mediaType) {
		this.mediaType = mediaType;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public int getReasonCodeId() {
		return reasonCodeId;
	}

	public void setReasonCodeId(int reasonCodeId) {
		this.reasonCodeId = reasonCodeId;
	}

	public String getDialogs() {
		return dialogs;
	}

	public void setDialogs(String dialogs) {
		this.dialogs = dialogs;
	}

	public String getStateChangeTime() {
		return stateChangeTime;
	}

	public void setStateChangeTime(String stateChangeTime) {
		this.stateChangeTime = stateChangeTime;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public ReasonCode getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(ReasonCode reasonCode) {
		this.reasonCode = reasonCode;
	}

	public MobileAgent getMobileAgent() {
		return mobileAgent;
	}

	public void setMobileAgent(MobileAgent mobileAgent) {
		this.mobileAgent = mobileAgent;
	}

//	@XmlElementWrapper(name = "teams")
//	@XmlElement(name = "teams")
	@XmlElementWrapper(name="teams")
	@XmlElement(name="Team")
	public List<Team> getTeams() {
		return this.teams;
	}
	
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	
	@Path("Users")
	@XmlRootElement(name = "Users")
	public static class UserList extends BaseApiListBean<User> {
		//		@XmlElementWrapper(name = "Users")
		@XmlElement(name = "User")
		@Override
		public List<User> getItems() {
			return items;
		}

		@Override
		public void setItems(List<User> items) {
			this.items = items;
		}
	}

	@XmlRootElement(name = "roles")
	public static class AgentRole {
		private String role;

		@XmlElement(name = "role")
		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}
	}

	@XmlRootElement(name = "settings")
	public static class AgentSettings {
		private String wrapUpOnIncoming;

		@XmlElement(name = "wrapUpOnIncoming")
		public String getWrapUpOnIncoming() {
			return wrapUpOnIncoming;
		}

		public void setWrapUpOnIncoming(String wrapUpOnIncoming) {
			this.wrapUpOnIncoming = wrapUpOnIncoming;
		}
	}

	@XmlRootElement(name = "mobileAgent")
	public static class MobileAgent {
		private String mode;
		private String dialNumber;

		public String getMode() {
			return mode;
		}
		public void setMode(String mode) {
			this.mode = mode;
		}
		public String getDialNumber() {
			return dialNumber;
		}
		public void setDialNumber(String dialNumber) {
			this.dialNumber = dialNumber;
		}

	}
}

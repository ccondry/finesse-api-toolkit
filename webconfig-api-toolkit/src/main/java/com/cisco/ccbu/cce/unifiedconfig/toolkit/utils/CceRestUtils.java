package com.cisco.ccbu.cce.unifiedconfig.toolkit.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Path;

import com.cisco.ccbu.cce.unifiedconfig.toolkit.ApiException;
import com.cisco.ccbu.cce.unifiedconfig.toolkit.BaseApiBean;
import com.cisco.ccbu.cce.unifiedconfig.toolkit.BaseApiBeanWithName;
import com.cisco.ccbu.cce.unifiedconfig.toolkit.BaseApiListBean;
import com.cisco.ccbu.cce.unifiedconfig.toolkit.RESTClient;
import com.cisco.ccbu.cce.unifiedconfig.toolkit.ReferenceBean;
import com.cisco.ccbu.cce.unifiedconfig.toolkit.bean.Agent;
import com.cisco.ccbu.cce.unifiedconfig.toolkit.bean.Attribute;
import com.cisco.ccbu.cce.unifiedconfig.toolkit.bean.AttributeValueWithAttribute;
import com.cisco.ccbu.cce.unifiedconfig.toolkit.bean.CallType;
import com.cisco.ccbu.cce.unifiedconfig.toolkit.bean.Campaign;
import com.cisco.ccbu.cce.unifiedconfig.toolkit.bean.CampaignSkillGroupInfo;
import com.cisco.ccbu.cce.unifiedconfig.toolkit.bean.DialedNumber;
import com.cisco.ccbu.cce.unifiedconfig.toolkit.bean.DialingMode;
import com.cisco.ccbu.cce.unifiedconfig.toolkit.bean.ImportContact;
import com.cisco.ccbu.cce.unifiedconfig.toolkit.bean.MediaRoutingDomain;
import com.cisco.ccbu.cce.unifiedconfig.toolkit.bean.Person;
import com.cisco.ccbu.cce.unifiedconfig.toolkit.bean.SkillGroup;
import com.cisco.ccbu.cce.unifiedconfig.toolkit.bean.TimeZone;
import com.cisco.ccbu.cce.unifiedconfig.toolkit.bean.TimeZoneRef;
import com.cisco.ccbu.cce.unifiedconfig.toolkit.bean.CallType.CallTypeList;
import com.cisco.ccbu.cce.unifiedconfig.toolkit.bean.MediaRoutingDomain.MediaRoutingDomainList;

public class CceRestUtils {
	private static final String IMPORT_PATH = "import";

	public static <T extends BaseApiBean> ReferenceBean makeReferenceBean(T bean) {
		return new ReferenceBean(bean.getRefURL());
	}
	
	/**
	 * Get a base refURL string to be used to create a refURL or ReferenceBean from the input class
	 * @param beanClass the class of BaseApiBean you want to get a base refURL for 
	 * @return the base refURL string, ending with "/"
	 */
	public static <T extends BaseApiBean> String getRefUrlBase(Class<T> beanClass) {
		return RESTClient.baseUrl + beanClass.getAnnotation(Path.class).value() + "/";
	}
	
	/**
	 * Get a ReferenceBean from input BaseApiBean class and resource id
	 * @param beanClass the class of BaseApiBean you want to get a base refURL for 
	 * @param id the id of the specific beanClass resource you are referencing
	 * @return the ReferenceBean
	 */
	public static <T extends BaseApiBean> ReferenceBean makeReferenceBean(Class<T> beanClass, String id) {
		String refUrl = getRefUrlBase(beanClass) + id;
		ReferenceBean refBean = new ReferenceBean(refUrl);
		return refBean;
	}
	
	/**
	 * @param restClient RESTClient object to connect to CCE API
	 * @param query the agent ID to search for
	 * @return a single Agent object if an agent is found with an agent ID exactly matching the input query string
	 */
	public static Agent lookupAgentById(RESTClient restClient, String query) {
		List<Agent> agents = lookupMultiple(restClient, query, Agent.class, Agent.AgentList.class);
		if (agents == null) {
			return null;
		}
		// Look for exact matching name and return that Skill Group
		for(Agent agent : agents) {
			if(agent.getAgentId().equals(query))
				return agent;
		}

		return null;
	}

	public static Person populatePersonBean(String firstName, String lastName, String userName, String password) {
		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setUserName(userName);
		person.setPassword(password);
		person.setLoginEnabled(true);
		return person;
	}
	public static Person populatePersonBean(String firstName, String lastName, String userName, Boolean ssoEnabled) {
		Person person = new Person();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setUserName(userName);
		person.setLoginEnabled(true);
		person.setSsoEnabled(ssoEnabled ? 1 : 0);
		return person;
	}

	/**
	 * @param restClient the CCE rest client object
	 * @param skillgroup the skillgroup to add the agents to
	 * @param agents 
	 * @param useridString cxdemo lab user ID
	 * @throws ApiException when the API service failed to add agents to the skillgroup, 
	 * such as if there are too many configured PQs/Skillgroups for the deployment
	 */

	public static void addAgentsToSkillgroup(RESTClient restClient, SkillGroup skillgroup, List<Agent> agents) throws ApiException {
		// add agents to skillgroup
		List<ReferenceBean> agentsAdded = new ArrayList<ReferenceBean>();
		for(Agent agent : agents) {
			agentsAdded.add(new ReferenceBean(agent.getRefURL()));
		}
		// set the agents that should be added to this skillgroup
		skillgroup.setAgentsAdded(agentsAdded);

		// post or put changes to the CCE server
		restClient.update(skillgroup);
	}

	public static DialedNumber createAndGetDialedNumber(RESTClient restClient, String dnString, String description, Integer routingType, String mrdName, String callTypeName) {
		DialedNumber dialedNumber = new DialedNumber();
		// set properties
		dialedNumber.setDialedNumberString(dnString);
		dialedNumber.setDescription(description);
		dialedNumber.setRoutingType(routingType);
		// MRD
		ReferenceBean mrdRef = getReferenceBean(restClient, mrdName, MediaRoutingDomain.class, MediaRoutingDomainList.class);
		dialedNumber.setMediaRoutingDomain(mrdRef);
		// Call Type
		ReferenceBean callTypeRef = getReferenceBean(restClient, callTypeName, CallType.class, CallTypeList.class);
		dialedNumber.setCallType(callTypeRef);
		// create dialed number
		dialedNumber = restClient.createAndGetBean(dialedNumber);
		return dialedNumber;
	}

	public static <T extends BaseApiBeanWithName, L extends BaseApiListBean<T>> ReferenceBean getReferenceBean(RESTClient restClient, String name, Class<T> beanType, Class<L> beanTypeList) {
		BaseApiBeanWithName callType = lookupSingle(restClient, name, beanType, beanTypeList);

		ReferenceBean refBean = new ReferenceBean();
		refBean.setName(callType.getName());
		refBean.setRefURL(callType.getRefURL());
		return refBean;
	}

	public static <T extends BaseApiBean, L extends BaseApiListBean<T>> List<T> lookupMultiple(RESTClient restClient, String query, Class<T> beanType, Class<L> beanListType) {
		L list;
		try {
			list = restClient.getList(beanListType, query);
		} catch (ApiException e) {
			return null;
		}

		if(list.getItems().size() == 0) {
			return null;
		}
		return list.getItems();
	}

	public static <T extends BaseApiBeanWithName, L extends BaseApiListBean<T>> T lookupSingle(RESTClient restClient, String query, Class<T> beanType, Class<L> beanListType) {
		List<T> list = lookupMultiple(restClient, query, beanType, beanListType);
		if (list == null) {
			return null;
		}
		// Look for exact matching name and return that item
		for(T item: list) {
			if(item != null && item.getName().equals(query))
				return (T) item;
		}
		// no exact matches found
		return null;
	}

	/**
	 * Create and return an Import Bean
	 *
	 * @param delimiter Delimiter used in file content
	 * @param fileContent Records to be imported including the header
	 * @param overwriteData if existing data should be overwritten
	 * @return
	 */
	public static ImportContact populateBulkImportBean(String delimiter, String fileContent, boolean overwriteData) {

		ImportContact importContact = new ImportContact();
		importContact.setDelimiter(delimiter);
		importContact.setFileContent(fileContent);
		importContact.setOverwriteData(overwriteData);

		return importContact;
	}


	/**
	 * Construct import path from Campaign refURL
	 *
	 * @param campaign Campaign for which we need to create the importPath
	 * @return
	 */
	public static String constructImportPath(Campaign campaign) {
		String importPath = campaign.getRefURL().replace(RESTClient.baseUrl, "");
		importPath += "/" + IMPORT_PATH;
		return importPath;
	}

	/**
	 * Creates a Campaign and returns the created Campaign
	 *
	 * @param restClient Rest Client
	 * @param campaignName 
	 * @param skillGroup SkillGroup - a SkillGroup is required for creating a Campaign, this skillGroup must not be used in other Campaigns
	 * @param dialingMode 
	 * @param dialedNumber 
	 * @param ivrPorts 
	 * @param campaignPurposeType 
	 * @return Campaign
	 */
	public static Campaign createAndGetCampaign(RESTClient restClient, String campaignName, SkillGroup skillGroup, DialingMode dialingMode, String dialedNumber, Integer ivrPorts, String campaignPurposeType) {
		List<CampaignSkillGroupInfo> skillGroupInfos = createCampaignSkillGroupInfos(Arrays.asList(skillGroup), dialedNumber, ivrPorts);

		// -- Get UTC timeZone
		TimeZone timeZoneUTC = restClient.get(TimeZone.class, RESTClient.baseUrl + "timezone/UTC");

		// -- Make a new Campaign object
		Campaign campaign = populateCampaignBean(campaignName, dialingMode, skillGroupInfos, timeZoneUTC, campaignPurposeType);
		campaign = restClient.createAndGetBean(campaign);
		System.out.println("Campaign created: " + campaign.getRefURL());
		return campaign;
	}

	/**
	 * Creates a SkillGroup and returns the created SkillGroup
	 *
	 * @param restClient Rest Client
	 * @param name 
	 * @return SkillGroup
	 */
	public static SkillGroup createAndGetSkillGroup(RESTClient restClient, String name) {
		SkillGroup skillGroup = new SkillGroup();
		skillGroup.setName(name);
		skillGroup = restClient.createAndGetBean(skillGroup);
		System.out.println("Skill Group created: " + skillGroup.getRefURL());
		return skillGroup;
	}

	/**
	 * Creates a list of CampaignSkillGroupInfos from the list of SkillGroups provided
	 *
	 * @param skillGroups list of SkillGroups
	 * @param dialedNumber 
	 * @param ivrPorts 
	 * @return list of CampaignSkillGroupInfos
	 */
	public static List<CampaignSkillGroupInfo> createCampaignSkillGroupInfos(List<SkillGroup> skillGroups, String dialedNumber, Integer ivrPorts) {
		List<CampaignSkillGroupInfo> skillGroupInfos = new ArrayList<>(skillGroups.size());

		for(SkillGroup skillGroup: skillGroups) {

			// -- Setup a SkillGroupInfo for the SkillGroup
			CampaignSkillGroupInfo skillGroupInfo = new CampaignSkillGroupInfo();
			ReferenceBean skillGroupRef = new ReferenceBean();
			skillGroupRef.setName(skillGroup.getName());
			skillGroupRef.setRefURL(skillGroup.getRefURL());
			skillGroupInfo.setSkillGroupRef(skillGroupRef);
			skillGroupInfo.setDialedNumber(dialedNumber);
			skillGroupInfo.setIvrPorts(ivrPorts);
			// Add SkillGroupInfo to SkillGroupInfos
			skillGroupInfos.add(skillGroupInfo);
		}

		return skillGroupInfos;
	}

	/**
	 * Create and return a Campaign bean
	 *
	 * @param campaignName Name of the Campaign
	 * @param dialingMode Campaign's dialing mode, see <code>DialingMode</code> for acceptable values
	 * @param skillGroupInfos List of SkillGroupInfos
	 * @param timeZone Default TimeZone of the Campaign
	 * @param campaignPurposeType 
	 * @return Campaign bean
	 */
	public static Campaign populateCampaignBean(String campaignName,
			DialingMode dialingMode,
			List<CampaignSkillGroupInfo> skillGroupInfos,
			TimeZone timeZone, String campaignPurposeType) {

		Campaign campaign = new Campaign();

		campaign.setName(campaignName);
		campaign.setDialingMode(dialingMode);

		campaign.setSkillGroupInfos(skillGroupInfos);

		TimeZoneRef timeZoneRef = new TimeZoneRef();
		timeZoneRef.setRefURL(timeZone.getRefURL());
		timeZoneRef.setDisplayName(timeZone.getName());
		campaign.setTimeZone(timeZoneRef);
		campaign.setEnabled(true);
		campaign.setDialingMode(dialingMode);
		campaign.setCampaignPurposeType(campaignPurposeType);
		//		newCampaign.setStartTime("00:00");
		//		newCampaign.setEndTime("23:59");
		campaign.setStartDate(new Date());
		return campaign;
	}

	public static void addAttributesToAgents(RESTClient restClient, List<Agent> agents, List<Attribute> attributes, String attributeValue) {
		List<AttributeValueWithAttribute> avwas = new ArrayList<AttributeValueWithAttribute>();
		for (Attribute attribute : attributes) {
			AttributeValueWithAttribute avwa = new AttributeValueWithAttribute();
			ReferenceBean attributeRef = makeReferenceBean(attribute);
			avwa.setAttribute(attributeRef);
			avwa.setAttributeValue(attributeValue);
			avwas.add(avwa);
		}
		
		Iterator<Agent> i = agents.iterator();
		while (i.hasNext()) {
			Agent agent = i.next();
			agent.setAttributesAdded(avwas);
			agent.setAttributes(null);
			try {
				restClient.update(agent);
			} catch (ApiException e) {
				continue;
			}
		}
	}

	public static List<Agent> findAgentsEndingWith(RESTClient restClient, String userid) {
		List<Agent> agents = lookupMultiple(restClient, userid, Agent.class, Agent.AgentList.class);
		Iterator<Agent> i = agents.iterator();
		while (i.hasNext()) {
			Agent agent = i.next();
			if (agent.getAgentId().endsWith(userid)) {
				continue;
			} else {
				agents.remove(i);
			}
		}
		return agents;
	}

	public static <T extends BaseApiBean, L extends BaseApiListBean<T>> List<T> lookupMultiple(
			RESTClient restClient, 
			String query, 
			Class<T> beanType, 
			Class<L> beanListType,
			Map<String, String> params) {
		L list;
		try {
			list = restClient.getList(beanListType, query, "", params);
		} catch (ApiException e) {
			return null;
		}

		if(list.getItems().size() == 0) {
			return null;
		}
		return list.getItems();
	}
}

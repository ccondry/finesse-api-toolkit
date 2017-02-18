package com.cisco.dcloud.cxdemo.finesse.api.toolkit.sample;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.cisco.dcloud.cxdemo.finesse.api.toolkit.FinesseRestClient;
import com.cisco.dcloud.cxdemo.finesse.api.toolkit.bean.User;

public class Test {
	public static void main(String[] args) {
		// create REST client to connect to Finesse XML REST APIs
		FinesseRestClient finesse = new FinesseRestClient("finesse1.dcloud.cisco.com", "username", "password");
		// comment or remove this line if you have cloned this from repository
		finesse = Lab.getFinesseClient();

		// get entire Users list
		// https://finesse1.dcloud.cisco.com:443/fineses/api/Users
		// List<User> users = finesse.getList(User.UserList.class).getItems();
		// System.out.println(new JSONArray(users).toString(2));

		// get User details by agent ID
		User user = finesse.getById(User.class, "40377");
		System.out.println(new JSONObject(user).toString(2));
		//		JSONObject teams = new JSONObject(user).optJSONObject("teams");
		//		if (teams == null) {
		//			System.out.println("no supervised teams");
		//		} else {
		//			System.out.println(teams.toString(2));
		//		}
	}
}

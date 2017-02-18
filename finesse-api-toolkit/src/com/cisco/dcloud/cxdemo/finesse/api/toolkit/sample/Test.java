package com.cisco.dcloud.cxdemo.finesse.api.toolkit.sample;

import java.util.List;

import org.json.JSONArray;

import com.cisco.dcloud.cxdemo.finesse.api.toolkit.FinesseRestClient;
import com.cisco.dcloud.cxdemo.finesse.api.toolkit.bean.User;

public class Test {
	public static void main(String[] args) {
		FinesseRestClient finesse = new FinesseRestClient("finesse1.dcloud.cisco.com", "", "");
		List<User> users = finesse.getList(User.UserList.class).getItems();
		System.out.println(new JSONArray(users).toString(2));
	}
}

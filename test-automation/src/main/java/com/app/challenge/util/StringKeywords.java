package com.app.challenge.util;

import java.util.ArrayList;

import io.restassured.path.json.JsonPath;

public class StringKeywords {
	
	public StringBuilder getStringArrayList(ArrayList<String> arrayList) {
		StringBuilder myString = new StringBuilder();
		for(int i=0; i<arrayList.size();i++){
			   myString.append(arrayList.get(i).toString());
			}
		return myString;
	}
	
	public Integer getIntegerFromList(JsonPath jsonList, String element) {
		return jsonList.getInt(element);
	}
	
	public String getStringFromList(JsonPath jsonList, String element) {
		return jsonList.getString(element);
	}

}

package com.app.challenge.util;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.app.challenge.api.pojo.Character;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAPIResponse {
	
	public String getSingleValue(RequestSpecification httpRequest, String endpoint, String element) {
		StringKeywords newString = new StringKeywords();
		Response response = httpRequest.get(endpoint);
		Assert.assertEquals(response.getStatusCode(), 200);
		JsonPath jsonResponse = response.jsonPath();
		ArrayList<String> sElement = jsonResponse.get(element);
		StringBuilder elementString = newString.getStringArrayList(sElement);
		
		return elementString.toString();
	}
	
	public List<Object> getListValues(RequestSpecification httpRequest, String endpoint, Character chart) {
		Response response = httpRequest.get(endpoint);
		Assert.assertEquals(response.getStatusCode(), 200);
		JsonPath jsonPResponse = response.jsonPath();
		chart.setJsonResponse(response.jsonPath());
		List<Object> listElements = chart.getJsonResponse().getList("$");

		return listElements;
	}

}

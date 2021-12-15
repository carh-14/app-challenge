package com.app.challenge.test.api;

import org.testng.annotations.Test;
import java.io.IOException;

import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import com.app.challenge.util.GetAPIResponse;
import com.app.challenge.util.ReadConfigProperties;
import com.app.challenge.util.StringKeywords;
import com.app.challenge.api.pojo.Character;


public class BreakingBadTest {
 
	StringKeywords sKeyword = new StringKeywords();
	Character chart = new Character();
	GetAPIResponse apiResponse= new GetAPIResponse();
	RequestSpecification httpRequest;
	private final static String TEST_NAME = "Walter White";
	
	@BeforeTest
	 public void beforeTest() throws IOException {
		
		ReadConfigProperties property = new ReadConfigProperties();
		property.getPropertyValuesAPI();
		
		RestAssured.baseURI = property.getApiPath();
	  }
	
	@Test (description = "Get birthday")
	public void getBirthday() {
		Reporter.log("- Test get birthday", true);
		httpRequest = RestAssured.given();
		String birthday = apiResponse.getSingleValue(httpRequest, "/characters/?name="+TEST_NAME, "birthday");
		Reporter.log("Birthday is correct", true);
		System.out.println("My birthay is on " + birthday);
  }

	@Test (description = "Print Character info")
	  public void showCharacters() {
		Reporter.log("- Test show character info", true);
		httpRequest = RestAssured.given();
		List<Object> listValues = apiResponse.getListValues(httpRequest, "/characters", chart);
		for (int i=0; i<listValues.size();i++) {
			chart.setAllValuesByExecution(i);
			
			System.out.println(" ---------------------- ");
			System.out.printf("Character Name: %s %n", chart.getName());
			System.out.printf("Portrayed:  %s %n", chart.getPortrayed());
		  }
		System.out.println(" ---------------------- ");
	  }
  
}

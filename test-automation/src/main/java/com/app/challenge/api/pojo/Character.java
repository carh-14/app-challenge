package com.app.challenge.api.pojo;

import java.util.ArrayList;

import com.app.challenge.util.StringKeywords;

import io.restassured.path.json.JsonPath;

public class Character {
	private int id;
	private String name;
	private String birthday;
	private ArrayList<String> occupation;
	private String img;
	private String status;
	private String nickname;
	private ArrayList<String> appearance;
	private String portrayed;
	private String category;
	private ArrayList<String> betterCallSaul;
	private JsonPath jsonResponse;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getBirthday() {
		return birthday;
	}
	
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public ArrayList<String> getOccupation() {
		return occupation;
	}
	
	public void setOccupation(ArrayList<String> occupation) {
		this.occupation = occupation;
	}
	
	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img = img;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public ArrayList<String> getAppearance() {
		return appearance;
	}
	
	public void setAppearance(ArrayList<String> appearance) {
		this.appearance = appearance;
	}
	
	public String getPortrayed() {
		return portrayed;
	}
	
	public void setPortrayed(String portrayed) {
		this.portrayed = portrayed;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public ArrayList<String> getBetterCallSaul() {
		return betterCallSaul;
	}
	
	public void setBetterCallSaul(ArrayList<String> betterCallSaul) {
		this.betterCallSaul = betterCallSaul;
	}
	
	public JsonPath getJsonResponse() {
		return jsonResponse;
	}

	public void setJsonResponse(JsonPath jsonResponse) {
		this.jsonResponse = jsonResponse;
	}
	
	public void setAllValuesByExecution(Integer execution) {
		//Setting all values from a multiple execution
		
		StringKeywords sKeyword = new StringKeywords();
		
		if (jsonResponse.getList("$").size() > 0) {
			setId(sKeyword.getIntegerFromList(getJsonResponse(), "char_id["+execution+"]"));
			setName(sKeyword.getStringFromList(getJsonResponse(), "name["+execution+"]"));
			setBirthday(sKeyword.getStringFromList(getJsonResponse(), "birthday["+execution+"]"));
			setImg(sKeyword.getStringFromList(getJsonResponse(), "img["+execution+"]"));
			setStatus(sKeyword.getStringFromList(getJsonResponse(), "status["+execution+"]"));
			setNickname(sKeyword.getStringFromList(getJsonResponse(), "nickname["+execution+"]"));
			setPortrayed(sKeyword.getStringFromList(getJsonResponse(), "portrayed["+execution+"]"));
			setCategory(sKeyword.getStringFromList(getJsonResponse(), "category["+execution+"]"));
		} else {
			System.out.println("JSON Response is empty. Please check");
		}
	}

}

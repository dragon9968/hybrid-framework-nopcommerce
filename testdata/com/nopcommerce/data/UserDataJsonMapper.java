package com.nopcommerce.data;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import commons.GlobalConstants;

public class UserDataJsonMapper {
	
	@JsonProperty("firstName")
	private String firstName;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("emailAddress")
	private String emailAddress;
	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("date")
	private String date;
	
	@JsonProperty("month")
	private String month;
	
	@JsonProperty("year")
	private String year;
	
	
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public String getPassword() {
		return password;
	}
	public String getDate() {
		return date;
	}
	public String getMonth() {
		return month;
	}
	public String getYear() {
		return year;
	}
	
	public static UserDataJsonMapper getUserData() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(new File(GlobalConstants.PROJECT_PATH + "/resources/UserData.json"), UserDataJsonMapper.class);
	     	} catch (Exception ex) {
	     		ex.printStackTrace();
	     		return null;
	     	}
		
		
	}
	


}

package com.wu.ecommerce.dto;

import com.wu.ecommerce.exception.InvalidIdException;

public class User {
	
	public User(String userid, String fName, String lName, String address, String contact) throws InvalidIdException{
		super();
		Userid = userid;
		FName = fName;
		LName = lName;
		Address = address;
		Contact = contact;
	}
	private String Userid;
	public void setProductId(String userid) throws InvalidIdException {
		if(userid==null || userid.equals("") || userid.length()>3) {
			throw new InvalidIdException("id is invalid");
		}
		this.Userid = userid;
	}
	private String FName;
	private String LName;
	private String Address;
	private String Contact;
}

package com.efectivale.credenciales.models;

public class RequestAPI {

	private int user_id;
	private int api_id;
	
	public RequestAPI(int user_id, int api_id) {
		super();
		this.user_id = user_id;
		this.api_id = api_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getApi_id() {
		return api_id;
	}
	public void setApi_id(int api_id) {
		this.api_id = api_id;
	}
	
	
}

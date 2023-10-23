package com.efectivale.credenciales.models;

public class CredencialesGeneradas{

	
	private String consumerSecret;
	
	
	public CredencialesGeneradas(String consumerSecret) {
		super();
		this.consumerSecret = consumerSecret;
	}
	
	public String getConsumerSecret() {
		return consumerSecret;
	}
	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}

	
}

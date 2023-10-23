package com.efectivale.credenciales.models;

public class CredencialesResponse {
	
	private String credencialconsumersecret;
	private String roltipo;
	
	
	
	public String getCredencialconsumersecret() {
		return credencialconsumersecret;
	}
	public void setCredencialconsumersecret(String credencialconsumersecret) {
		this.credencialconsumersecret = credencialconsumersecret;
	}
	
	public String getRolTipo() {
		return roltipo;
	}
	public void setRolTipo(String tipo) {
		this.roltipo = tipo;
	}
	
	@Override
	public String toString() {
		return "CredencialesResponse [credencialconsumersecret=" + credencialconsumersecret + ", roltipo=" + roltipo
				+ "]";
	}	
}

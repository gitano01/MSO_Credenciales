package com.ext.credenciales.models;


public class CredencialDetalle {

	private int credencialDetalleId;
	private int credencial_Id;
	private int api_Id;
	private int usuario_Id;
	private int rol_Id;
	private String roltipo;
	
	public int getCredencialDetalleId() {
		return credencialDetalleId;
	}
	public void setCredencialDetalleId(int credencialDetalleId) {
		this.credencialDetalleId = credencialDetalleId;
	}
	public int getCredencial_Id() {
		return credencial_Id;
	}
	public void setCredencial_Id(int credencial_Id) {
		this.credencial_Id = credencial_Id;
	}
	public int getApi_Id() {
		return api_Id;
	}
	public void setApi_Id(int api_Id) {
		this.api_Id = api_Id;
	}
	public int getUsuario_Id() {
		return usuario_Id;
	}
	public void setUsuario_Id(int usuario_Id) {
		this.usuario_Id = usuario_Id;
	}
	public int getRol_Id() {
		return rol_Id;
	}
	public void setRol_Id(int rol_Id) {
		this.rol_Id = rol_Id;
	}
	
	public void setRolTipo(String tipo) {
		this.roltipo = tipo;
	}
	
	public String getRolTipo() {
		return roltipo;
	}
	
	@Override
	public String toString() {
		return "CredencialDetalle [credencialDetalleId=" + credencialDetalleId + ", credencial_Id=" + credencial_Id
				+ ", api_Id=" + api_Id + ", usuario_Id=" + usuario_Id + ", rol_Id=" + rol_Id + ", roltipo=" + roltipo
				+ "]";
	}
		
}

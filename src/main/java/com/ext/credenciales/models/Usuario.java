package com.ext.credenciales.models;

public class Usuario {
	private int id;
	private String nombre;
	private String password;	
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String MostrarDatos(String nombre, String password) {
		return  "nombre: "+ nombre + " password: "+  password;
	}
}

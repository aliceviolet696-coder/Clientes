package com.krakdev.clientes.entidades;

public class Cliente {
	
	private String cedula;
	private String nombre;
	private String apellido;
	private String email;
	
	public Cliente() {
	
	}
	
	
	public Cliente(String cedula, String nombre, String apellido) {
		this(cedula, nombre, apellido, null);
	}
	
	
	public Cliente(String cedula, String nombre, String apellido, String email) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
	}


	@Override
	public String toString() {
		return "Cliente [cedula=" + cedula + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email + "]";
	}


	public String getCedula() {
		return cedula;
	}


	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}



	

}

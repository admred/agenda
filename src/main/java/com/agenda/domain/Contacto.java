package com.agenda.domain;


public class Contacto implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private String apellido;
	private String telefono;
	private String email;
	
	/* Contructores */
	public Contacto() {
		this(0l,"","","","");
	}
	
	public Contacto(Long id,String nombre, String apellido, String telefono, String email) {
		this.id=id;
		this.nombre=nombre;
		this.apellido=apellido;
		this.telefono=telefono;
		this.email=email;
	}
	
	public Long getId() {
		return id;
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
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	/* miscelanias */
	
	@Override
	public String toString() {
		return "Contacto [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono
				+ ", email=" + email + "]";
	}
	
}

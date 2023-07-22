package com.agenda.domain;


public class Grupo implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private String descripcion;
	
	/* Contructores */
	public Grupo() {
		this(0l,"","");
	}
	
	public Grupo(Long id,String nombre, String descripcion) {
		this.id=id;
		this.nombre=nombre;
		this.descripcion=descripcion;
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		return "Grupo [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
	
}

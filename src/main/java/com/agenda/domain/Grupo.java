package com.agenda.domain;

import java.util.Objects;

public class Grupo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String nombre;
	private String descripcion;

	/* Contructores */
	public Grupo() {
	}

	public Grupo(String nombre, String descripcion) {
		this.id = 0l;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Grupo(Long id, String nombre, String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public void setId(Long id) {
		this.id = id;

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
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grupo other = (Grupo) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Grupo [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
}

package net.gbs.contactosdb;

public class Contacto {
	private String nombre;
	private String email;
	private Integer telefono;
	
	//Constructor para Contacto
	public Contacto(String nombre, String email, Integer telefono) {
		nombre   = this.nombre;
		email    = this.email;
		telefono = this.telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}
}

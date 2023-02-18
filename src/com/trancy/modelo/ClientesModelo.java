package com.trancy.modelo;

public class ClientesModelo {
	
	private int idCliente;
	private String nombre;
	private String domicilio;
	private String estado;
	
	public ClientesModelo(int idCliente, String nombre, String domicilio, String estado) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.estado = estado;
	}
	
	public ClientesModelo(){
		
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}

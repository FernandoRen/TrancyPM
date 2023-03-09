package com.trancy.modelo;

public class ProveedoresModelo {
	
	private int idProveedor;
	private String nombre;
	private String domicilio;
	private String estado;
	
	public ProveedoresModelo(int idProveedor, String nombre, String domicilio, String estado) {
		super();
		this.idProveedor = idProveedor;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.estado = estado;
	}
	
	public ProveedoresModelo() {
		
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
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

	@Override
	public String toString() {
		return "ProveedoresModelo [idProveedor=" + idProveedor + ", nombre=" + nombre + ", domicilio=" + domicilio
				+ ", estado=" + estado + "]";
	}
	
	

}

package com.trancy.modelo;

public class TransportistaModelo {

	private int idTransportista;
	private String nombre;
	private String domicilio;
	private String estado;
	
	public TransportistaModelo(int idTransportista, String nombre, String domicilio, String estado) {
		super();
		this.idTransportista = idTransportista;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.estado = estado;
	}
	
	public TransportistaModelo() {
		
	}

	public int getIdTransportista() {
		return idTransportista;
	}

	public void setIdTransportista(int idTransportista) {
		this.idTransportista = idTransportista;
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
		return "TransportistaModelo [idTransportista=" + idTransportista + ", nombre=" + nombre + ", domicilio="
				+ domicilio + ", estado=" + estado + "]";
	}
	
}

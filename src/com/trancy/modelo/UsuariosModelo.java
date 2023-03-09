package com.trancy.modelo;

public class UsuariosModelo {
	
	private int idUsuario;
	private String email;
	private String password;
	private String nombre;
	private String apellidos;
	private int roles;
	private String rolDescripcion;
	private boolean status;
	
	
	public UsuariosModelo(int idUsuario, String email, String password, String nombre, String apellidos, int roles, String rolDescripcion,
			boolean status) {
		super();
		this.idUsuario = idUsuario;
		this.email = email;
		this.password = password;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.roles = roles;
		this.rolDescripcion = rolDescripcion;
		this.status = status;
	}


	public UsuariosModelo() {
		// TODO Auto-generated constructor stub
	}


	public int getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public int getRoles() {
		return roles;
	}


	public void setRoles(int roles) {
		this.roles = roles;
	}


	public String getRolDescripcion() {
		return rolDescripcion;
	}


	public void setRolDescripcion(String rolDescripcion) {
		this.rolDescripcion = rolDescripcion;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "UsuariosModelo [idUsuario=" + idUsuario + ", email=" + email + ", password=" + password + ", nombre="
				+ nombre + ", apellidos=" + apellidos + ", roles=" + roles + ", rolDescripcion=" + rolDescripcion
				+ ", status=" + status + "]";
	}
	
}

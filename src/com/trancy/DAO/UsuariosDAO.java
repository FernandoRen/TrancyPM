package com.trancy.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.trancy.conexion.Conexion;
import com.trancy.modelo.UsuariosModelo;

public class UsuariosDAO {
	private Connection connection;
	private PreparedStatement st;
	private boolean estadoOperacion = false;
	private ResultSet rs;
	
	public List<UsuariosModelo> Login(String emailUsuario, String loginUsuario) throws SQLException {
		connection = obtenerConexion();
		
		List <UsuariosModelo> userList = new ArrayList<>();
		
		try {
			String queryLogin = "SELECT email, nombre, apellidos, idrol FROM usuarios WHERE email = ? and password = MD5(?) and status = 1;";
			st = connection.prepareStatement(queryLogin);
			st.setString(1, emailUsuario);
	        st.setString(2, loginUsuario);
	        
			rs = st.executeQuery();
			while (rs.next()) {
				UsuariosModelo userData = new UsuariosModelo();
				userData.setEmail(rs.getString(1));
				userData.setNombre(rs.getString(2));
				userData.setApellidos(rs.getString(3));
				userData.setRoles((rs.getInt(4)));
				userList.add(userData);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userList;
		
	}
	
	public boolean validarUsuarioExistente(String userEmail) throws SQLException {
		connection = obtenerConexion();
		try {
			
			String queryLogin = "SELECT email FROM usuarios WHERE email = ? and status = 1;";
			
			st = connection.prepareStatement(queryLogin);
			st.setString(1, userEmail);
	        
			rs = st.executeQuery();
			
			if (rs.next()) {
				estadoOperacion = true;
			}
			
			st.close();
	        connection.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return estadoOperacion;
	}
	
	
	
	public boolean agregarUsuario(UsuariosModelo usuario) throws SQLException {
		connection = obtenerConexion();
		
		try {
			connection.setAutoCommit(false);
			String sqlInsertar = "INSERT INTO usuarios (idusuario, email, password, nombre, apellidos, idrol, status) VALUES (?, ?, MD5(?), ?, ?, ?, ?);";
			st = connection.prepareStatement(sqlInsertar);
			st.setString(1, null);
			st.setString(2, usuario.getEmail());
			st.setString(3, usuario.getPassword());
			st.setString(4, usuario.getNombre());
			st.setString(5, usuario.getApellidos());
			st.setInt(6, usuario.getRoles());
			st.setBoolean(7, true);
			int rs = st.executeUpdate();
			
			if(rs > 0) {
				estadoOperacion = true;
				connection.commit();
				System.out.println("Registro insertado correctamente");
			}
			
			st.close();
			connection.close();
			
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		}
		
		return estadoOperacion;
	}
	
	public List<UsuariosModelo> mostrarUsuarios() throws SQLException {
		connection = obtenerConexion();
		
		List <UsuariosModelo> usuariosLista = new ArrayList<>();
		
		try {
			String querySelect = "select * from mostrarUsuarios;";
			st = connection.prepareStatement(querySelect);
	        
			rs = st.executeQuery();
			while (rs.next()) {
				UsuariosModelo usuariosData = new UsuariosModelo();
				usuariosData.setIdUsuario(rs.getInt(1));
				usuariosData.setEmail(rs.getString(2));
				usuariosData.setNombre(rs.getString(3));
				usuariosData.setApellidos(rs.getString(4));
				usuariosData.setRolDescripcion(rs.getString(5));
				usuariosLista.add(usuariosData);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return usuariosLista;
		
	}
	
	public boolean actualizarUsuario(UsuariosModelo usuario) throws SQLException {
		
		connection = obtenerConexion();
		
		try {
			
			connection.setAutoCommit(false);
			String queryUpdate = "UPDATE usuarios SET email = ?, nombre = ?, apellidos = ?, idrol = ? WHERE idusuario = ?";
			st = connection.prepareStatement(queryUpdate);
			st.setString(1, usuario.getEmail());
			st.setString(2, usuario.getNombre());
			st.setString(3, usuario.getApellidos());
			st.setInt(4, usuario.getRoles());
			st.setInt(5, usuario.getIdUsuario());
			
			int rs = st.executeUpdate();
			
			if (rs > 0) {
				estadoOperacion = true;
				connection.commit();
				System.out.println("Usuario actualizado correctamente");
			}
			
			st.close();
			connection.close();
			
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		}
		
		return estadoOperacion;
	}
	
	public List<UsuariosModelo> mostrarUsuarioPorId(UsuariosModelo usuario) throws SQLException {
		connection = obtenerConexion();
		
		List <UsuariosModelo> usuarioLista = new ArrayList<>();
		
		try {
			String querySelect = "call sp_seleccionarUsuarioPorId(?);";
			st = connection.prepareStatement(querySelect);
			st.setInt(1, usuario.getIdUsuario());
	        
			rs = st.executeQuery();
			while (rs.next()) {
				UsuariosModelo usuarioData = new UsuariosModelo();
				usuarioData.setIdUsuario(rs.getInt(1));
				usuarioData.setEmail(rs.getString(2));
				usuarioData.setNombre(rs.getString(3));
				usuarioData.setApellidos(rs.getString(4));
				usuarioData.setRolDescripcion(rs.getString(5));
				usuarioLista.add(usuarioData);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return usuarioLista;
		
	}
	
	public boolean eliminarUsuario(UsuariosModelo usuario) throws SQLException {
		connection = obtenerConexion();
		
		try {
			connection.setAutoCommit(false);
			String queryDelete = "UPDATE usuarios SET status = 0 WHERE idusuario = ?";
			st = connection.prepareStatement(queryDelete);
			st.setInt(1, usuario.getIdUsuario());
			
			int rs = st.executeUpdate();
			
			if (rs > 0) {
				estadoOperacion = true;
				connection.commit();
				System.out.println("Usuario eliminado correctamente");
			}
			
			st.close();
			connection.close();
			
			
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		}
		
		return estadoOperacion;
	}
	
	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();
	}
	
}

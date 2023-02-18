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
	
	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConnection();
	}
	
}

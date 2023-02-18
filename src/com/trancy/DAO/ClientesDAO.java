package com.trancy.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.trancy.conexion.Conexion;
import com.trancy.modelo.ClientesModelo;
import com.trancy.modelo.UsuariosModelo;

public class ClientesDAO {
	private Connection connection;
	private PreparedStatement st;
	private boolean estadoOperacion = false;
	private ResultSet rs;
	
	public boolean agregarCliente(ClientesModelo cliente) throws SQLException {
		connection = obtenerConexion();
		
		try {
			connection.setAutoCommit(false);
			String sqlInsertar = "INSERT INTO cliente (idCliente, nombre, domicilio, estado, estatus) VALUES (?, ?, ?, ?, ?);";
			st = connection.prepareStatement(sqlInsertar);
			st.setString(1, null);
			st.setString(2, cliente.getNombre());
			st.setString(3, cliente.getDomicilio());
			st.setString(4, cliente.getEstado());
			st.setBoolean(5, true);
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
	
	public List<ClientesModelo> mostrarClientes() throws SQLException {
		connection = obtenerConexion();
		
		List <ClientesModelo> clientesLista = new ArrayList<>();
		
		try {
			String querySelect = "SELECT idcliente, nombre, domicilio, estado FROM cliente WHERE estatus = 1;";
			st = connection.prepareStatement(querySelect);
	        
			rs = st.executeQuery();
			while (rs.next()) {
				ClientesModelo clientesData = new ClientesModelo();
				clientesData.setIdCliente(rs.getInt(1));
				clientesData.setNombre(rs.getString(2));
				clientesData.setDomicilio(rs.getString(3));
				clientesData.setEstado(rs.getString(4));
				clientesLista.add(clientesData);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return clientesLista;
		
	}
	
	public List<ClientesModelo> mostrarClientePorId(ClientesModelo modelo) throws SQLException {
		connection = obtenerConexion();
		
		List <ClientesModelo> clienteLista = new ArrayList<>();
		
		try {
			String querySelect = "SELECT idcliente, nombre, domicilio, estado FROM cliente WHERE estatus = 1 and idcliente = ?;";
			st = connection.prepareStatement(querySelect);
			st.setInt(1, modelo.getIdCliente());
	        
			rs = st.executeQuery();
			while (rs.next()) {
				ClientesModelo clienteData = new ClientesModelo();
				clienteData.setIdCliente(rs.getInt(1));
				clienteData.setNombre(rs.getString(2));
				clienteData.setDomicilio(rs.getString(3));
				clienteData.setEstado(rs.getString(4));
				clienteLista.add(clienteData);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return clienteLista;
		
	}
	
	public boolean actualizarCliente(ClientesModelo modelo) throws SQLException {
		
		connection = obtenerConexion();
		
		try {
			
			connection.setAutoCommit(false);
			String queryUpdate = "UPDATE cliente SET nombre = ?, domicilio = ?, estado = ? WHERE idcliente = ?";
			st = connection.prepareStatement(queryUpdate);
			st.setString(1, modelo.getNombre());
			st.setString(2, modelo.getDomicilio());
			st.setString(3, modelo.getEstado());
			st.setInt(4, modelo.getIdCliente());
			
			int rs = st.executeUpdate();
			
			if (rs > 0) {
				estadoOperacion = true;
				connection.commit();
				System.out.println("Cliente actualizado correctamente");
			}
			
			st.close();
			connection.close();
			
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		}
		
		return estadoOperacion;
	}
	
	public boolean eliminarCliente(ClientesModelo modelo) throws SQLException {
		connection = obtenerConexion();
		
		try {
			connection.setAutoCommit(false);
			String queryDelete = "UPDATE cliente SET estatus = 0 WHERE idcliente = ?";
			st = connection.prepareStatement(queryDelete);
			st.setInt(1, modelo.getIdCliente());
			
			int rs = st.executeUpdate();
			
			if (rs > 0) {
				estadoOperacion = true;
				connection.commit();
				System.out.println("Cliente eliminado correctamente");
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

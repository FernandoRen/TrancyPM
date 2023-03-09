package com.trancy.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.trancy.conexion.Conexion;
import com.trancy.modelo.ProveedoresModelo;

public class ProveedoresDAO {
	private Connection connection;
	private PreparedStatement st;
	private boolean estadoOperacion = false;
	private ResultSet rs;
	
	public boolean agregarCliente(ProveedoresModelo proveedor) throws SQLException {
		connection = obtenerConexion();
		
		try {
			connection.setAutoCommit(false);
			String sqlInsertar = "INSERT INTO proveedor (idProveedor, nombre, domicilio, estado, estatus) VALUES (?, ?, ?, ?, ?);";
			st = connection.prepareStatement(sqlInsertar);
			st.setString(1, null);
			st.setString(2, proveedor.getNombre());
			st.setString(3, proveedor.getDomicilio());
			st.setString(4, proveedor.getEstado());
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
	
	public List<ProveedoresModelo> mostrarProveedores() throws SQLException {
		connection = obtenerConexion();
		
		List <ProveedoresModelo> proveedoresLista = new ArrayList<>();
		
		try {
			String querySelect = "SELECT idproveedor, nombre, domicilio, estado FROM proveedor WHERE estatus = 1;";
			st = connection.prepareStatement(querySelect);
	        
			rs = st.executeQuery();
			while (rs.next()) {
				ProveedoresModelo provedoresData = new ProveedoresModelo();
				provedoresData.setIdProveedor(rs.getInt(1));
				provedoresData.setNombre(rs.getString(2));
				provedoresData.setDomicilio(rs.getString(3));
				provedoresData.setEstado(rs.getString(4));
				proveedoresLista.add(provedoresData);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return proveedoresLista;
		
	}
	
	public boolean actualizarProveedor(ProveedoresModelo modelo) throws SQLException {
		
		connection = obtenerConexion();
		
		try {
			
			connection.setAutoCommit(false);
			String queryUpdate = "UPDATE proveedor SET nombre = ?, domicilio = ?, estado = ? WHERE idproveedor = ?";
			st = connection.prepareStatement(queryUpdate);
			st.setString(1, modelo.getNombre());
			st.setString(2, modelo.getDomicilio());
			st.setString(3, modelo.getEstado());
			st.setInt(4, modelo.getIdProveedor());
			
			int rs = st.executeUpdate();
			
			if (rs > 0) {
				estadoOperacion = true;
				connection.commit();
				System.out.println("Proveedor actualizado correctamente");
			}
			
			st.close();
			connection.close();
			
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		}
		
		return estadoOperacion;
	}
	
	public List<ProveedoresModelo> mostrarProveedorPorId(ProveedoresModelo modelo) throws SQLException {
		connection = obtenerConexion();
		
		List <ProveedoresModelo> proveedorLista = new ArrayList<>();
		
		try {
			String querySelect = "SELECT idproveedor, nombre, domicilio, estado FROM proveedor WHERE estatus = 1 and idproveedor = ?;";
			st = connection.prepareStatement(querySelect);
			st.setInt(1, modelo.getIdProveedor());
	        
			rs = st.executeQuery();
			while (rs.next()) {
				ProveedoresModelo proveedorData = new ProveedoresModelo();
				proveedorData.setIdProveedor(rs.getInt(1));
				proveedorData.setNombre(rs.getString(2));
				proveedorData.setDomicilio(rs.getString(3));
				proveedorData.setEstado(rs.getString(4));
				proveedorLista.add(proveedorData);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return proveedorLista;
		
	}
	
	public boolean eliminarProveedor(ProveedoresModelo modelo) throws SQLException {
		connection = obtenerConexion();
		
		try {
			connection.setAutoCommit(false);
			String queryDelete = "UPDATE proveedor SET estatus = 0 WHERE idproveedor = ?";
			st = connection.prepareStatement(queryDelete);
			st.setInt(1, modelo.getIdProveedor());
			
			int rs = st.executeUpdate();
			
			if (rs > 0) {
				estadoOperacion = true;
				connection.commit();
				System.out.println("Proveedor eliminado correctamente");
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

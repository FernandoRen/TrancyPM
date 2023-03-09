package com.trancy.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.trancy.conexion.Conexion;
import com.trancy.modelo.ProveedoresModelo;
import com.trancy.modelo.TransportistaModelo;

public class TransportistaDAO {
	
	private Connection connection;
	private PreparedStatement st;
	private boolean estadoOperacion = false;
	private ResultSet rs;
	
	public boolean agregarTransportista(TransportistaModelo transportista) throws SQLException {
		connection = obtenerConexion();
		
		try {
			connection.setAutoCommit(false);
			String sqlInsertar = "INSERT INTO transportista (idtransportista, nombre, domicilio, estado, estatus) VALUES (?, ?, ?, ?, ?);";
			st = connection.prepareStatement(sqlInsertar);
			st.setString(1, null);
			st.setString(2, transportista.getNombre());
			st.setString(3, transportista.getDomicilio());
			st.setString(4, transportista.getEstado());
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
	
	public List<TransportistaModelo> mostrarTransportistas() throws SQLException {
		connection = obtenerConexion();
		
		List <TransportistaModelo> transportistaLista = new ArrayList<>();
		
		try {
			String querySelect = "SELECT idtransportista, nombre, domicilio, estado FROM transportista WHERE estatus = 1;";
			st = connection.prepareStatement(querySelect);
	        
			rs = st.executeQuery();
			while (rs.next()) {
				TransportistaModelo transportistaData = new TransportistaModelo();
				transportistaData.setIdTransportista(rs.getInt(1));
				transportistaData.setNombre(rs.getString(2));
				transportistaData.setDomicilio(rs.getString(3));
				transportistaData.setEstado(rs.getString(4));
				transportistaLista.add(transportistaData);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return transportistaLista;
		
	}
	
	public boolean actualizarTransportista(TransportistaModelo transportista) throws SQLException {
		
		connection = obtenerConexion();
		
		try {
			
			connection.setAutoCommit(false);
			String queryUpdate = "UPDATE transportista SET nombre = ?, domicilio = ?, estado = ? WHERE idtransportista = ?";
			st = connection.prepareStatement(queryUpdate);
			st.setString(1, transportista.getNombre());
			st.setString(2, transportista.getDomicilio());
			st.setString(3, transportista.getEstado());
			st.setInt(4, transportista.getIdTransportista());
			
			int rs = st.executeUpdate();
			
			if (rs > 0) {
				estadoOperacion = true;
				connection.commit();
				System.out.println("Transportista actualizado correctamente");
			}
			
			st.close();
			connection.close();
			
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		}
		
		return estadoOperacion;
	}
	
	
	public List<TransportistaModelo> mostrarTransportistaPorId(TransportistaModelo transportista) throws SQLException {
		connection = obtenerConexion();
		
		List <TransportistaModelo> transportistaLista = new ArrayList<>();
		
		try {
			String querySelect = "SELECT idtransportista, nombre, domicilio, estado FROM transportista WHERE estatus = 1 and idtransportista = ?;";
			st = connection.prepareStatement(querySelect);
			st.setInt(1, transportista.getIdTransportista());
	        
			rs = st.executeQuery();
			while (rs.next()) {
				TransportistaModelo transportistaData = new TransportistaModelo();
				transportistaData.setIdTransportista(rs.getInt(1));
				transportistaData.setNombre(rs.getString(2));
				transportistaData.setDomicilio(rs.getString(3));
				transportistaData.setEstado(rs.getString(4));
				transportistaLista.add(transportistaData);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return transportistaLista;
		
	}
	
	public boolean eliminarTransportista(TransportistaModelo transportista) throws SQLException {
		connection = obtenerConexion();
		
		try {
			connection.setAutoCommit(false);
			String queryDelete = "UPDATE transportista SET estatus = 0 WHERE idtransportista = ?";
			st = connection.prepareStatement(queryDelete);
			st.setInt(1, transportista.getIdTransportista());
			
			int rs = st.executeUpdate();
			
			if (rs > 0) {
				estadoOperacion = true;
				connection.commit();
				System.out.println("Transportista eliminado correctamente");
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

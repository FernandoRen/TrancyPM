package com.trancy.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.trancy.DAO.ProveedoresDAO;
import com.trancy.modelo.ProveedoresModelo;

/**
 * Servlet implementation class ProveedoresControlador
 */
@WebServlet(name = "ProviderController", urlPatterns = { "/ProviderController" })
public class ProveedoresControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProveedoresControlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		int peticion = Integer.parseInt(request.getParameter("peticion"));
		ProveedoresDAO proveedorDao = new ProveedoresDAO();
		ProveedoresModelo proveedorModelo = new ProveedoresModelo();
		

		//JSON configuration
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		//create Json Object
		JSONObject json = new JSONObject();
		
		switch (peticion) {
			case 1:
				proveedorModelo.setNombre(request.getParameter("providerName"));
				proveedorModelo.setDomicilio(request.getParameter("providerAddress"));
				proveedorModelo.setEstado(request.getParameter("providerState"));
				
				try {
					if(proveedorDao.agregarCliente(proveedorModelo)) {
	
						// put some values into the JSON object .
						json.put("insertResult", true);
	
						// finally output the json string       
						out.print(json.toString());
						
					} else {
						json.put("insertResult", false);
						out.print(json.toString());
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			break;
			
			case 2:
				try {
					
					if(proveedorDao.mostrarProveedores().size() > 0) {
						
						Gson gson = new Gson();
						String JSON = gson.toJson(proveedorDao.mostrarProveedores());
						
						// finally output the json string       
						out.print(JSON.toString());
						
					} 
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			break;
			
			case 3:
				String idProviderToUpdate = request.getParameter("numberProvider");
				String nameProvider = request.getParameter("providerNameUpdate");
				String addressProvider = request.getParameter("providerAddressUpdate");
				String stateProvider = request.getParameter("providerStateUpdate");
				
				proveedorModelo.setIdProveedor(Integer.parseInt(idProviderToUpdate));
				proveedorModelo.setNombre(nameProvider);
				proveedorModelo.setDomicilio(addressProvider);
				proveedorModelo.setEstado(stateProvider);
				
				try {
					if (proveedorDao.actualizarProveedor(proveedorModelo)) {
						// put some values into the JSON object .
						json.put("resultUpdate", true);
	
						// finally output the json string       
						out.print(json.toString());
					} else {
						json.put("resultUpdate", false);
						out.print(json.toString());
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			break;
			
			case 4:
				String idProviderToDelete = request.getParameter("idProvider").replace("r-", "");
				proveedorModelo.setIdProveedor(Integer.parseInt(idProviderToDelete));
				try {
					if (proveedorDao.eliminarProveedor(proveedorModelo)) {
						// put some values into the JSON object .
						json.put("resultDelete", true);
	
						// finally output the json string       
						out.print(json.toString());
					} else {
						json.put("resultDelete", false);
						out.print(json.toString());
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			break;
			
			case 5:
				String idProviderToCheck = request.getParameter("idProveedor").replace("e-", "");
				proveedorModelo.setIdProveedor(Integer.parseInt(idProviderToCheck));
				try {
					if(proveedorDao.mostrarProveedorPorId(proveedorModelo).size() > 0) {
	
						// put some values into the JSON object .
						json.put("isThereProvider", true);
						json.put("providerData", proveedorDao.mostrarProveedorPorId(proveedorModelo));
	
						// finally output the json string       
						out.print(json.toString());
						
					} else {
						json.put("isThereProvider", false);
						out.print(json.toString());
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			break;
			
		}
		
	}

}

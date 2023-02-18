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
import com.trancy.DAO.ClientesDAO;
import com.trancy.modelo.ClientesModelo;

/**
 * Servlet implementation class ClientesControlador
 */
@WebServlet(name = "CustomerController", urlPatterns = { "/CustomerController" })
public class ClientesControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientesControlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		int peticion = Integer.parseInt(request.getParameter("peticion"));
		ClientesDAO clientesDao = new ClientesDAO();
		ClientesModelo clientesModelo = new ClientesModelo();
		
		//JSON configuration
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		//create Json Object
		JSONObject json = new JSONObject();
		
		switch (peticion) {
			case 1:
				clientesModelo.setNombre(request.getParameter("customerName"));
				clientesModelo.setDomicilio(request.getParameter("customerAddress"));
				clientesModelo.setEstado(request.getParameter("customerState"));
				
				try {
					if(clientesDao.agregarCliente(clientesModelo)) {
	
						// put some values into the JSON object .
						json.put("insertResult", true);
	
						// finally output the json string       
						out.print(json.toString());
						
					} else {
						json.put("resultLogin", false);
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
					if(clientesDao.mostrarClientes().size() > 0) {
	
						// put some values into the JSON object .
						//json.put("areThereCustomers", true);
						//json.put("customerData", clientesDao.mostrarClientes());
						
						Gson gson = new Gson();
						String JSON = gson.toJson(clientesDao.mostrarClientes());
						
						// finally output the json string       
						out.print(JSON.toString());
						
					} else {
						json.put("areThereCustomers", false);
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
			
			case 3:
				String idClienteToUpdate = request.getParameter("numberCustomer");
				String nameCustomer = request.getParameter("customerNameUpdate");
				String addressCustomer = request.getParameter("customerAddressUpdate");
				String stateCustomer = request.getParameter("customerStateUpdate");
				
				clientesModelo.setIdCliente(Integer.parseInt(idClienteToUpdate));
				clientesModelo.setNombre(nameCustomer);
				clientesModelo.setDomicilio(addressCustomer);
				clientesModelo.setEstado(stateCustomer);
				
				try {
					if (clientesDao.actualizarCliente(clientesModelo)) {
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
				String idClienteToDelete = request.getParameter("idCustomer").replace("r-", "");
				clientesModelo.setIdCliente(Integer.parseInt(idClienteToDelete));
				try {
					if (clientesDao.eliminarCliente(clientesModelo)) {
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
				String idClienteToCheck = request.getParameter("idCustomer").replace("e-", "");
				clientesModelo.setIdCliente(Integer.parseInt(idClienteToCheck));
				try {
					if(clientesDao.mostrarClientePorId(clientesModelo).size() > 0) {
	
						// put some values into the JSON object .
						json.put("isThereCustomer", true);
						json.put("customerData", clientesDao.mostrarClientePorId(clientesModelo));
	
						// finally output the json string       
						out.print(json.toString());
						
					} else {
						json.put("isThereCustomers", false);
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

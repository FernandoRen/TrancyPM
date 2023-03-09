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
import com.trancy.DAO.TransportistaDAO;
import com.trancy.modelo.TransportistaModelo;

/**
 * Servlet implementation class TransportistaControlador
 */
@WebServlet(name = "driverController", urlPatterns = { "/driverController" })
public class TransportistaControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransportistaControlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		int peticion = Integer.parseInt(request.getParameter("peticion"));
		TransportistaDAO transportistaDao = new TransportistaDAO();
		TransportistaModelo transportistaModelo = new TransportistaModelo();
		
		//JSON configuration
				response.setContentType("application/json");
				response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();

				//create Json Object
				JSONObject json = new JSONObject();
				
				switch (peticion) {
					case 1:
						transportistaModelo.setNombre(request.getParameter("truckingCompanyName"));
						transportistaModelo.setDomicilio(request.getParameter("truckingCompanyAddress"));
						transportistaModelo.setEstado(request.getParameter("truckingCompanyState"));
						
						try {
							if(transportistaDao.agregarTransportista(transportistaModelo)) {
			
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
							
							if(transportistaDao.mostrarTransportistas().size() > 0) {
								
								Gson gson = new Gson();
								String JSON = gson.toJson(transportistaDao.mostrarTransportistas());
								
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
						String idTcToUpdate = request.getParameter("numberTC");
						String nameTC = request.getParameter("truckingCompanyNameUpdate");
						String addressTC = request.getParameter("truckingCompanyAddressUpdate");
						String stateTC = request.getParameter("truckingCompanyStateUpdate");
						
						transportistaModelo.setIdTransportista(Integer.parseInt(idTcToUpdate));
						transportistaModelo.setNombre(nameTC);
						transportistaModelo.setDomicilio(addressTC);
						transportistaModelo.setEstado(stateTC);
						
						try {
							if (transportistaDao.actualizarTransportista(transportistaModelo)) {
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
						String idTC = request.getParameter("idTC").replace("r-", "");
						transportistaModelo.setIdTransportista(Integer.parseInt(idTC));
						try {
							if (transportistaDao.eliminarTransportista(transportistaModelo)) {
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
						String idDriverToCheck = request.getParameter("idTC").replace("e-", "");
						transportistaModelo.setIdTransportista(Integer.parseInt(idDriverToCheck));
						try {
							if(transportistaDao.mostrarTransportistaPorId(transportistaModelo).size() > 0) {
			
								// put some values into the JSON object .
								json.put("isThereTC", true);
								json.put("TcData", transportistaDao.mostrarTransportistaPorId(transportistaModelo));
			
								// finally output the json string       
								out.print(json.toString());
								
							} else {
								json.put("isThereTC", false);
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

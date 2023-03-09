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
import com.trancy.DAO.UsuariosDAO;
import com.trancy.modelo.ClientesModelo;
import com.trancy.modelo.UsuariosModelo;

/**
 * Servlet implementation class UsuariosControlador
 */
@WebServlet(name = "usersController", urlPatterns = { "/usersController" })
public class UsuariosControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuariosControlador() {
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
		UsuariosDAO usuariosDao = new UsuariosDAO();
		UsuariosModelo usuariosModelo = new UsuariosModelo();
		
		//JSON configuration
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		//create Json Object
		JSONObject json = new JSONObject();
		
		switch (peticion) {
			case 1:
				usuariosModelo.setEmail(request.getParameter("newUserEmail"));
				usuariosModelo.setPassword(request.getParameter("newUserPassword2"));
				usuariosModelo.setNombre(request.getParameter("newUserName"));
				usuariosModelo.setApellidos(request.getParameter("newUserLastName"));
				
				int idRole = 0;
				
				if(request.getParameter("newUserRole").equals("application manager")) {
					idRole = 1;
				} else if (request.getParameter("newUserRole").equals("normal user")) {
					idRole = 2;
				} else if (request.getParameter("newUserRole").equals("reader")) {
					idRole = 3;
				}
	
				usuariosModelo.setRoles(idRole);
				
				try {
					if(usuariosDao.agregarUsuario(usuariosModelo)) {
	
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
					
					if(usuariosDao.mostrarUsuarios().size() > 0) {
	
						// put some values into the JSON object .
						//json.put("areThereCustomers", true);
						//json.put("customerData", clientesDao.mostrarClientes());
						
						Gson gson = new Gson();
						String JSON = gson.toJson(usuariosDao.mostrarUsuarios());
						
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
				String idUserToUpdate = request.getParameter("idUser");
				String nameUser = request.getParameter("userNameUpdate");
				String emailUser = request.getParameter("userEmailUpdate");
				String userLastName = request.getParameter("userLastNameUpdate");
				
				usuariosModelo.setIdUsuario(Integer.parseInt(idUserToUpdate));
				usuariosModelo.setEmail(emailUser);
				usuariosModelo.setNombre(nameUser);
				usuariosModelo.setApellidos(userLastName);
				
				int idRoleUpdate = 0;
				
				if(request.getParameter("userRoleUpdate").equals("application manager")) {
					idRoleUpdate = 1;
				} else if (request.getParameter("userRoleUpdate").equals("normal user")) {
					idRoleUpdate = 2;
				} else if (request.getParameter("userRoleUpdate").equals("reader")) {
					idRoleUpdate = 3;
				}
				
				usuariosModelo.setRoles(idRoleUpdate);
				
				try {
					if (usuariosDao.actualizarUsuario(usuariosModelo)) {
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
				String idUserToDelete = request.getParameter("idUser").replace("r-", "");
				usuariosModelo.setIdUsuario(Integer.parseInt(idUserToDelete));
				try {
					if (usuariosDao.eliminarUsuario(usuariosModelo)) {
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
				String idUserToCheck = request.getParameter("idUser").replace("e-", "");
				usuariosModelo.setIdUsuario(Integer.parseInt(idUserToCheck));
				try {
					if(usuariosDao.mostrarUsuarioPorId(usuariosModelo).size() > 0) {
	
						// put some values into the JSON object .
						json.put("isThereUser", true);
						json.put("userData", usuariosDao.mostrarUsuarioPorId(usuariosModelo));
	
						// finally output the json string       
						out.print(json.toString());
						
					} else {
						json.put("isThereUser", false);
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

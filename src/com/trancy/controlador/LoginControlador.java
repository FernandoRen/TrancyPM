package com.trancy.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.trancy.DAO.UsuariosDAO;
import com.trancy.modelo.UsuariosModelo;

/**
 * Servlet implementation class LoginControlador
 */
@WebServlet("/loginController")
public class LoginControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginControlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String userLogin = request.getParameter("usuario");
		String passwordLogin = request.getParameter("pass");
		
		UsuariosDAO usuarioDAO = new UsuariosDAO();
		try {
			List<UsuariosModelo> loginListResult = usuarioDAO.Login(userLogin, passwordLogin);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();

			//create Json Object
			JSONObject json = new JSONObject();
			
			if(loginListResult.size() > 0) {

				// put some values into the JSON object .
				json.put("resultLogin", true);
				json.put("userData", loginListResult);

				// finally output the json string       
				out.print(json.toString());
				
			} else {
				json.put("resultLogin", false);
				out.print(json.toString());
			}	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

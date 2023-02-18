package com.trancy.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.trancy.DAO.UsuariosDAO;
import com.trancy.modelo.UsuariosModelo;

/**
 * Servlet implementation class ValidadorControlador
 */
@WebServlet("/ValidatorController")
public class ValidadorControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidadorControlador() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		int typeOperation = Integer.parseInt(request.getParameter("operation"));
		
		switch (typeOperation) {
			case 1:
				String userSession = request.getParameter("user");
				String roleSession = request.getParameter("role");
				String nameSession = request.getParameter("name");
				
				
				//Sessions
				request.getSession().setAttribute("userSession", userSession);
				request.getSession().setAttribute("roleSession", roleSession);
				request.getSession().setAttribute("nameSession", nameSession);
				
				response.sendRedirect("vistas/validacion.jsp");
			break;
	
			case 2:
				String CheckUserStorage = request.getParameter("usuario");
				
				UsuariosDAO usuarioDAO = new UsuariosDAO();
				try {
					boolean checkResult = usuarioDAO.validarUsuarioExistente(CheckUserStorage);
					
					response.setContentType("application/json");
					response.setCharacterEncoding("utf-8");
					PrintWriter out = response.getWriter();

					//create Json Object
					JSONObject json = new JSONObject();

					// put some values into the JSON object .
					json.put("resultCheckUser", checkResult);

					// finally output the json string       
					out.print(json.toString());
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			break;
		}
		
	}

}

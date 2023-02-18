<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.trancy.DAO.UsuariosDAO" %>
<%
	String userSession = (String)session.getAttribute("userSession");
	String nameSession = (String)session.getAttribute("nameSession");
	if(userSession == null || userSession == ""){
		response.sendRedirect("../index.html");
	} else {
		UsuariosDAO usersDAO = new UsuariosDAO();
		if(usersDAO.validarUsuarioExistente(userSession) == false){
			response.sendRedirect("../index.html");
		}
	}
	
%>
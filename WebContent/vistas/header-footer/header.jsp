<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file = "./../validacion.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Ubuntu&display=swap" rel="stylesheet">
		<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap">
		<link rel="stylesheet" href="./../js/sweetalert2/dist/sweetalert2.min.css">
		<script src="./../js/sweetalert2/dist/sweetalert2.min.js"></script>
		<link rel="stylesheet" type="text/css" href="./../css/datatables.min.css">
		<link rel="stylesheet" type="text/css" href="./../css/mdb.min.css">
		<link rel="shortcut icon" href="#">
		<link rel="stylesheet" href="./../assets/lib/bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="./../css/style.css">
		
		<title>Trancy PM</title>
	</head>
	<body>
	<nav class="navbar navbar-expand-lg navbar-light login-bg">
	  <div class="collapse navbar-collapse" id="navbarText">
	    <ul class="navbar-nav mr-auto">
	    
	      <li class="nav-item">
	        <a class="nav-link text-white" href="inicioVista.jsp">Home</a>
	      </li>
	      
	      <li class="nav-item">
	        <a class="nav-link text-white" href="paquetesVista.jsp">Packages</a>
	      </li>
	      
	      <li class="nav-item">
	        <a class="nav-link text-white" href="clienteVista.jsp">Customer</a>
	      </li>
	      
	      <li class="nav-item">
	        <a class="nav-link text-white" href="proveedorVista.jsp">Providers</a>
	      </li>
	      
	      <li class="nav-item">
	        <a class="nav-link text-white" href="transportistaVista.jsp">Trucking Company</a>
	      </li>
	      
	      <li class="nav-item">
	        <a class="nav-link text-white" href="buscarPaquetesVista.jsp">Search Packages</a>
	      </li>
	      
	    </ul>
	    
	    <ul class="navbar-nav d-flex flex-wrap-reverse"">
	    	<li class="nav-item dropdown">
	    	
	    		<a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          <i class="fa fa-cog" aria-hidden="true"></i> Options
		        </a>
		        <div class="dropdown-menu dropdown-menu-right margin-0-auto" aria-labelledby="navbarDropdownMenuLink">
		          <a class="dropdown-item" href="#"><i class="fa fa-refresh" aria-hidden="true"></i>   Change your password</a>
		          <a class="dropdown-item" href="#">
				  	<i class="fa fa-sign-out" aria-hidden="true"></i> Log out
			      </a>
		        </div>
	    	
	    	</li>
	    
	    
	    </ul>
	    
	  </div>
	</nav>
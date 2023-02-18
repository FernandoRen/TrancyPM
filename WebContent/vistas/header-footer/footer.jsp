<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
    
    <script type="text/javascript">
		let body = document.querySelector("body");
		let fecha = new Date().getFullYear();
		document.addEventListener("DOMContentLoaded", () => {
			body.innerHTML += `
				<div class="footer-page footer-bg white-text mt-10">
					<p>&copy Trancy Logistics Mexico \${fecha} - All Rights Reserved</p>
				</div> `;
		});
	</script>
	
	
	<script src="./../assets/lib/bootstrap/js/jquery-3.2.1.slim.min.js"></script>
	
	<script src="./../js/jQuery/jQuery.js"></script>
	<script src="./../js/jQuery/popper.min.js"></script>
	<script src="./../js/jQuery/mdb.min.js"></script>
	<script src="./../js/DataTables/datatables.min.js" type="text/javascript"></script>
	<script src="https://kit.fontawesome.com/a2cc4a6c09.js" crossorigin="anonymous"></script>
	<script src="./../js/validateUsers.js"></script>
	<script src="./../assets/lib/bootstrap/js/bootstrap.js"></script> <!-- este es el JS de BootStrap -->
	<!-- Se recomienda poner el JS de BootStrap al final del Body del HTML, tal como indica la página oficial de BootStrap -->
	</body>
</html>
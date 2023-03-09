<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
    
<div class="modal fade" id="changePasswordModal" tabindex="-1" role="dialog" aria-labelledby="changePasswordModal" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Change your password</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<form id="changePasswordFormUpdate">
				<div class="modal-body">

					<div class="container">
						<div class="row my-2">

							<div class="col-md-12">
								<div class="form-group">
									<label for="providerNameUpdate">Type your current password</label> 
									<input autocomplete="no-complete" type="password" class="form-control"
										id="currentPasswordUpdate" name="currentPasswordUpdate"
										placeholder="Type your current password">
								</div>

								<div class="form-group">
									<label for="newPasswordUpdate">Type your new password</label> 
									<input autocomplete="no-complete" type="password" class="form-control"
										id="newPasswordUpdate" name="newPasswordUpdate"
										placeholder="Type your new password">
								</div>
								
								<div class="form-group">
									<label for="newPasswordConfirmUpdate">Confirm your new password</label> 
									<input autocomplete="no-complete" type="password" class="form-control"
										id="newPasswordConfirmUpdate" name="newPasswordConfirmUpdate"
										placeholder="Confirm your new password">
								</div>
								
							</div>

						</div>
					</div>
					<!--end container body-->

				</div>
				<!--end modal body-->
				<div class="text-center mb-3">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-success btn-send-update-password"
						id="btn-updatePasswordModal" name="btn-updatePasswordModal">Save changes</button>
				</div>
			</form>
		</div>
	</div>
</div>
    
    
    
    <script type="text/javascript">
		let body = document.querySelector("body");
		let fecha = new Date().getFullYear();
		document.addEventListener("DOMContentLoaded", () => {
			body.innerHTML += `
				<div class="footer-page footer-bg white-text">
					<p>&copy Trancy Logistics Mexico \${fecha} - All Rights Reserved</p>
				</div> `;
		});
	</script>
	
	
	<script src="./../assets/lib/bootstrap/js/jquery-3.2.1.slim.min.js"></script>
	
	<script src="./../js/jQuery/jQuery.js"></script>
	<script src="./../js/jQuery/popper.min.js"></script>
	<script src="./../js/jQuery/mdb.min.js"></script>
	<script src="./../js/DataTables/datatables.min.js" type="text/javascript"></script>
	<script src="./../assets/lib/fontawesome/js/fontawesome.js"></script>
	<script src="./../js/validateUsers.js"></script>
	<script src="./../js/validacionesGenerales.js"></script>
	<script src="./../js/headerAjax.js"></script>
	<script src="./../assets/lib/bootstrap/js/bootstrap.js"></script> <!-- este es el JS de BootStrap -->
	<!-- Se recomienda poner el JS de BootStrap al final del Body del HTML, tal como indica la página oficial de BootStrap -->
	</body>
</html>
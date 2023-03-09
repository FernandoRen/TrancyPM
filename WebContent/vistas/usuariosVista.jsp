<%@ include file = "header-footer/header.jsp" %>
	
	<div class="container">
		<div class="row text-center">
			<div class="col-md-12 my-2">
				<h2>Users Section</h2>
			</div>
		</div>
		<div class="row my-2">
			<div class="col-md-4">
				<h3>Add a new User</h3>
				<form id="usersForm">
				  <div class="form-group">
				    <label for="newUserEmail">Email</label>
				    <input type="email" class="form-control" id="newUserEmail" name="newUserEmail" placeholder="Type email" autocomplete="off">
				  </div>
				  
				  <div class="form-group">
				    <label for="newUserPassword">Password</label>
				    <input type="password" class="form-control" id="newUserPassword" name="newUserPassword" placeholder="Type password" autocomplete="new-password">
				  </div>
				  
				  <div class="form-group">
				    <label for="newUserPassword2">Comfirm Password</label>
				    <input type="password" class="form-control" id="newUserPassword2" name="newUserPassword2" placeholder="Type password" autocomplete="new-password">
				  </div>
				  
				  <div class="form-group">
				    <label for="newUserName">Name</label>
				    <input type="text" class="form-control" id="newUserName" name="newUserName" placeholder="Type Name" autocomplete="nope">
				  </div>
				  
				  <div class="form-group">
				    <label for="newUserLastName">Last Name</label>
				    <input type="text" class="form-control" id="newUserLastName" name="newUserLastName" placeholder="Type Last Name" autocomplete="off">
				  </div>
				  
				  <div class="form-group">
					<label for="newUserRole">Role</label> 
					<select class="custom-select" id="newUserRole" name="newUserRole">
						<option disabled selected value="0">--</option>
						<option value="application manager">Application Manager</option>
						<option value="normal user">Normal User</option>
						<option value="reader">Reader</option>
					</select>
				</div>
				  
				  <div class="text-center">
				  	<button id="btn-addUser" name="btn-addUser" type="submit" class="btn btn-primary mb-4">Add User</button>
				  </div>
				</form>
			</div>
		
			<div class="col-md-8">
				
				<table id="usersTable" class="table table-striped table-bordered" cellspacing="0" width="100%">
			        <thead>
			            <tr>
			                <th>Email</th>
			                <th>Name</th>
			                <th>Last Name</th>
			                <th>Role</th>
			                <th>Operations</th>
			            </tr>
			        </thead>
			            
			    </table>
				
			</div>
		</div>
	</div>
	
		<!-- Modal -->
	<div class="modal fade" id="editUserModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Edit User</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form id="userFormUpdate">
					<div class="modal-body">
	
						<div class="container">
							<div class="row my-2">
	
								<div class="col-md-12">
									<h3 class="text-center">User Data</h3>
									
									<div class="form-group">
									    <label for="userEmailUpdate">Email</label>
									    <input type="email" class="form-control" id="userEmailUpdate" name="userEmailUpdate" placeholder="Type email" autocomplete="off">
									</div>
								  
								  <div class="form-group">
								    <label for="userNameUpdate">Name</label>
								    <input type="text" class="form-control" id="userNameUpdate" name="userNameUpdate" placeholder="Type Name" autocomplete="nope">
								  </div>
								  
								  <div class="form-group">
								    <label for="userLastNameUpdate">Last Name</label>
								    <input type="text" class="form-control" id="userLastNameUpdate" name="userLastNameUpdate" placeholder="Type Last Name" autocomplete="off">
								  </div>
								  
								  <div class="form-group">
									<label for="userRoleUpdate">Role</label> 
									<select class="custom-select" id="userRoleUpdate" name="userRoleUpdate">
										<option disabled selected value="0">--</option>
										<option value="application manager">Application Manager</option>
										<option value="normal user">Normal User</option>
										<option value="reader">Reader</option>
									</select>
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
						<button type="button" class="btn btn-success btn-send-update-user"
							id="btn-updateUser" name="btn-updateUser">Save changes</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
<script src="./../js/validacionesGenerales.js"></script>
<%@ include file = "header-footer/footer.jsp" %>
<script src="./../js/usuariosAjax.js"></script>
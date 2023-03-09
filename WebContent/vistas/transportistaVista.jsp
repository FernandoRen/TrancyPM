<%@ include file = "header-footer/header.jsp" %>
	
	<div class="container">
		<div class="row text-center">
			<div class="col-md-12 my-2">
				<h2>Trucking Company Section</h2>
			</div>
		</div>
		<div class="row my-2">
			<div class="col-md-4">
				<h3>Add a new Trucking Company</h3>
				<form id="truckingCompanyForm">
				  <div class="form-group">
				    <label for="truckingCompanyName">Trucking Company name</label>
				    <input type="text" class="form-control" id="truckingCompanyName" name="truckingCompanyName" placeholder="Type name">
				  </div>
				  
				  <div class="form-group">
				    <label for="truckingCompanyAddress">Address</label>
				    <input type="text" class="form-control" id="truckingCompanyAddress" name="truckingCompanyAddress" placeholder="Type address">
				  </div>
				  
				  <div class="form-group">
					<label for="truckingCompanyState">State</label> 
					<select class="custom-select" id="truckingCompanyState" name="truckingCompanyState">
						<option disabled selected value="0">--</option>
						<option value="Aguascalientes">Aguascalientes</option>
						<option value="Baja California">Baja California</option>
						<option value="Baja California Sur">Baja California Sur</option>
						<option value="Campeche">Campeche</option>
						<option value="Chiapas">Chiapas</option>
						<option value="Chihuahua">Chihuahua</option>
						<option value="Ciudad de México">Ciudad de México</option>
						<option value="Coahuila">Coahuila</option>
						<option value="Colima">Colima</option>
						<option value="Durango">Durango</option>
						<option value="Estado de México">Estado de México</option>
						<option value="Guanajuato">Guanajuato</option>
						<option value="Guerrero">Guerrero</option>
						<option value="Hidalgo">Hidalgo</option>
						<option value="Jalisco">Jalisco</option>
						<option value="Michoacán">Michoacán</option>
						<option value="Morelos">Morelos</option>
						<option value="Nayarit">Nayarit</option>
						<option value="Nuevo León">Nuevo León</option>
						<option value="Oaxaca">Oaxaca</option>
						<option value="Puebla">Puebla</option>
						<option value="Querétaro">Querétaro</option>
						<option value="Quintana Roo">Quintana Roo</option>
						<option value="San Luis Potosí">San Luis Potosí</option>
						<option value="Sinaloa">Sinaloa</option>
						<option value="Sonora">Sonora</option>
						<option value="Tabasco">Tabasco</option>
						<option value="Tamaulipas">Tamaulipas</option>
						<option value="Tlaxcala">Tlaxcala</option>
						<option value="Veracruz">Veracruz</option>
						<option value="Yucatán">Yucatán</option>
						<option value="Zacatecas">Zacatecas</option>
					</select>
				</div>
				  
				  <div class="text-center">
				  	<button id="btn-addTC" name="btn-addTC" type="submit" class="btn btn-primary">Add Trucking Company</button>
				  </div>
				</form>
			</div>
		
			<div class="col-md-8">
				
				<table id="truckingCompanyTable" class="table table-striped table-bordered"
					cellspacing="0" width="100%">
			        <thead>
			            <tr>
			                <th class="th-sm">Name</th>
							<th class="th-sm">Address</th>
							<th class="th-sm">State</th>
							<th class="th-sm operations">Operations</th>
			            </tr>
			        </thead>
			       
			    </table>
				
			</div>
		</div>
	</div>
	
		<!-- Modal -->
	<div class="modal fade" id="editTCModal" tabindex="-1"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">Edit Trucking Company</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<form id="TCFormUpdate">
					<div class="modal-body">
	
						<div class="container">
							<div class="row my-2">
	
								<div class="col-md-12">
									<h3 class="text-center">Trucking Company Data</h3>
									<div class="form-group">
										<label for="truckingCompanyNameUpdate">Trucking Company name</label> 
										<input autocomplete="off" type="text" class="form-control"
											id="truckingCompanyNameUpdate" name="truckingCompanyNameUpdate"
											placeholder="Type name">
									</div>
	
									<div class="form-group">
										<label for="truckingCompanyAddressUpdate">Address</label> 
										<input autocomplete="off" type="text" class="form-control"
											id="truckingCompanyAddressUpdate" name="truckingCompanyAddressUpdate"
											placeholder="Type address">
									</div>
	
									<div class="form-group">
										<label for="truckingCompanyStateUpdate">State</label> <select
											class="custom-select" id="truckingCompanyStateUpdate" name="truckingCompanyStateUpdate">
											<option disabled selected value="0">--</option>
											<option value="Aguascalientes">Aguascalientes</option>
											<option value="Baja California">Baja California</option>
											<option value="Baja California Sur">Baja California Sur</option>
											<option value="Campeche">Campeche</option>
											<option value="Chiapas">Chiapas</option>
											<option value="Chihuahua">Chihuahua</option>
											<option value="Ciudad de México">Ciudad de México</option>
											<option value="Coahuila">Coahuila</option>
											<option value="Colima">Colima</option>
											<option value="Durango">Durango</option>
											<option value="Estado de México">Estado de México</option>
											<option value="Guanajuato">Guanajuato</option>
											<option value="Guerrero">Guerrero</option>
											<option value="Hidalgo">Hidalgo</option>
											<option value="Jalisco">Jalisco</option>
											<option value="Michoacán">Michoacán</option>
											<option value="Morelos">Morelos</option>
											<option value="Nayarit">Nayarit</option>
											<option value="Nuevo León">Nuevo León</option>
											<option value="Oaxaca">Oaxaca</option>
											<option value="Puebla">Puebla</option>
											<option value="Querétaro">Querétaro</option>
											<option value="Quintana Roo">Quintana Roo</option>
											<option value="San Luis Potosí">San Luis Potosí</option>
											<option value="Sinaloa">Sinaloa</option>
											<option value="Sonora">Sonora</option>
											<option value="Tabasco">Tabasco</option>
											<option value="Tamaulipas">Tamaulipas</option>
											<option value="Tlaxcala">Tlaxcala</option>
											<option value="Veracruz">Veracruz</option>
											<option value="Yucatán">Yucatán</option>
											<option value="Zacatecas">Zacatecas</option>
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
						<button type="button" class="btn btn-success btn-send-update-driver"
							id="btn-updateProvider" name="btn-updateProvider">Save changes</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	

<%@ include file = "header-footer/footer.jsp" %>
<script src="./../js/truckingCompanyAjax.js"></script>
<%@ include file = "header-footer/header.jsp" %>
	
	<div class="container">
		<div class="row text-center">
			<div class="col-md-12 my-2">
				<h2>Provider Section</h2>
			</div>
		</div>
		<div class="row my-2">
			<div class="col-md-4">
				<h3>Add a new Provider</h3>
				<form id="providerForm">
				  <div class="form-group">
				    <label for="providerName">Provider name</label>
				    <input type="text" class="form-control" id="providerName" name="providerName" placeholder="Type name">
				  </div>
				  
				  <div class="form-group">
				    <label for="providerAddress">Address</label>
				    <input type="text" class="form-control" id="providerAddress" name="providerAddress" placeholder="Type address">
				  </div>
				  
				  <div class="form-group">
					<label for="providerState">State</label> <select
						class="custom-select" id="providerState" name="providerState">
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
				  	<button type="submit" class="btn btn-primary" id="btn-addProvider" name="btn-addProvider">Add Provider</button>
				  </div>
				</form>
			</div>
		
			<div class="col-md-8">
				
				<table id="providerTable" class="table table-striped table-bordered"
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
<div class="modal fade" id="editProviderModal" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Edit Provider</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<form id="providerFormUpdate">
				<div class="modal-body">

					<div class="container">
						<div class="row my-2">

							<div class="col-md-12">
								<h3 class="text-center">Provider Data</h3>
								<div class="form-group">
									<label for="providerNameUpdate">Customer name</label> 
									<input autocomplete="off" type="text" class="form-control"
										id="providerNameUpdate" name="providerNameUpdate"
										placeholder="Type name">
								</div>

								<div class="form-group">
									<label for="providerAddressUpdate">Address</label> 
									<input autocomplete="off" type="text" class="form-control"
										id="providerAddressUpdate" name="providerAddressUpdate"
										placeholder="Type address">
								</div>

								<div class="form-group">
									<label for="providerStateUpdate">State</label> <select
										class="custom-select" id="providerStateUpdate" name="providerStateUpdate">
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
					<button type="button" class="btn btn-success btn-send-update-provider"
						id="btn-updateProvider" name="btn-updateProvider">Save changes</button>
				</div>
			</form>
		</div>
	</div>
</div>

<%@ include file = "header-footer/footer.jsp" %>
<script src="./../js/providerAjax.js"></script>
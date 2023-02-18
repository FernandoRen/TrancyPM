<%@ include file="header-footer/header.jsp"%>

<div class="container mb-30">
	<h2 class="text-center my-2">Create a new Package</h2>
	<form>
		<div class="row">
			<div class="col-md-4"> <!-- inicio del primer col 4 -->
				<div class="form-group">
					<label for="packageDescription">Package Description</label> 
					<input type="text" class="form-control" id="packageDescription"
						name="packageDescription" placeholder="Type a description"
						maxlength="150">
				</div>

				<div class="form-group">
					<label for="customerSelect">Customer</label> 
					<select class="custom-select" id="customerSelect" name="customerSelect">
						<option selected>Choose a Customer</option>
						<option value="1">One</option>
						<option value="2">Two</option>
						<option value="3">Three</option>
					</select>
				</div>

				<div class="form-group">
					<label for="filePackage">Type a File</label> 
					<input type="text"
						class="form-control" id="filePackage" name="filePackage"
						placeholder="Type a file" maxlength="30">
				</div>

				<div class="form-group">
					<label for="providerSelect">Provider</label> 
					<select class="custom-select" id="providerSelect" name="providerSelect">
						<option selected>Choose a Provider</option>
						<option value="1">One</option>
						<option value="2">Two</option>
						<option value="3">Three</option>
					</select>
				</div>
				
				<div class="form-group">
					<label for="yearSelect">Year</label> 
					<select class="custom-select" id="yearSelect" name="yearSelect">
						<option selected>Choose a year</option>
						<option value="1">One</option>
						<option value="2">Two</option>
						<option value="3">Three</option>
					</select>
				</div>

			</div> <!-- fin del primer col 4 -->
			
			<div class="col-md-4"> <!-- inicio del segundo col 4 -->
				<div class="form-group">
					<label for="AWBBL">AWB/BL</label> 
					<input type="text"
							class="form-control" id="AWBBL" name="AWBBL"
							placeholder="Type AWB/BL" maxlength="30">
				</div>
				
				<div class="form-group">
					<label for="trafficPackage">Traffic</label> 
					<input type="text"
							class="form-control" id="trafficPackage" name="trafficPackage"
							placeholder="Type a traffic number" maxlength="30">
				</div>
				
				<div class="form-group">
					<label for="guidePackage">Guide</label> 
					<input type="text"
							class="form-control" id="guidePackage" name="guidePackage"
							placeholder="Type a guide" maxlength="30">
				</div>
				
				<div class="form-group">
					<label for="customerStatus">Customer Status</label> 
					<select class="custom-select" id="customerStatus" name="customerStatus">
						<option selected>Choose a status</option>
						<option value="1">One</option>
						<option value="2">Two</option>
						<option value="3">Three</option>
					</select>
				</div>
				
				<div class="form-group">
					<label for="fileStatus">File Status</label> 
					<select class="custom-select" id="fileStatus" name="fileStatus">
						<option selected>Choose a status</option>
						<option value="1">One</option>
						<option value="2">Two</option>
						<option value="3">Three</option>
					</select>
				</div>
			
			</div> <!-- fin del segundo col 4 -->
			
			<div class="col-md-4"> <!-- inicio del tercer col 4 -->
				<div class="form-group">
					<label for="pedimentNumber">Pediment Number</label> 
					<input type="text"
							class="form-control" id="pedimentNumber" name="pedimentNumber"
							placeholder="Type a Pediment Number" maxlength="30">
				</div>
				
				<div class="form-group">
					<label for="providerInvoice">Provider Invoice</label> 
					<input type="text"
							class="form-control" id="providerInvoice" name="providerInvoice"
							placeholder="Type a Provider Invoice" maxlength="40">
				</div>
				
				<div class="form-group">
					<label for="truckingCompany">Trucking Company</label> 
					<select class="custom-select" id="truckingCompany" name="truckingCompany">
						<option selected>Choose a Trucking Company</option>
						<option value="1">One</option>
						<option value="2">Two</option>
						<option value="3">Three</option>
					</select>
				</div>
				
				<div class="form-group">
					<label for="weightPackage">KG</label> 
					<input type="number" step="0.01"
							class="form-control" id="weightPackage" name="weightPackage"
							placeholder="Type the weight">
				</div>
				
				<div class="form-group">
					<label for="notesPackage">Notes</label> 
					<textarea class="form-control" id="notesPackage" name="notesPackage" rows="2" maxlength="200"></textarea>
				</div>
				
			</div> <!-- fin del tercer col 4 -->
			<div class="row margin-0-auto">
				<div class="col-md-12">
					<button class="btn btn-success mb-2">Add Package</button>
					<button class="btn btn-primary">See my packages</button>
				</div>
			</div>
			
		</form>
	</div>
</div>

<%@ include file="header-footer/footer.jsp"%>
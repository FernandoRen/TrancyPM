(() => {
  	"use strict"
	document.addEventListener("DOMContentLoaded", () => {

        contentLoadeds();
		$('#truckingCompanyTable').DataTable();

	});  
})();

function contentLoadeds() {
	let btnAddTC= document.getElementById("btn-addTC");
	
	cargarTransportistas();
	btnAddTC.addEventListener('click', addTruckingCompany);
}

function addTruckingCompany(e){
	//variables
	let truckingCompanyName = document.getElementById("truckingCompanyName");
	let truckingCompanyAddress = document.getElementById("truckingCompanyAddress");
	let truckingCompanyState = document.getElementById("truckingCompanyState");

	//variables DOM
	let truckingCompanyForm_DOM = $("#truckingCompanyForm");
	
	e.preventDefault();
	if(truckingCompanyName.value === null ||truckingCompanyName.value === undefined || truckingCompanyName.value === ""){
		SweetAlertError("Trucking Company Name");
	} else if (truckingCompanyName.value.length < 3) {
		lengthCharacters("Trucking Company Name");
	} else if(truckingCompanyAddress.value === null || truckingCompanyAddress.value === undefined || truckingCompanyAddress.value === ""){
		SweetAlertError("Trucking Company Address");
	} else if (truckingCompanyAddress.value.length < 3) {
		lengthCharacters("Trucking Company Address");
	} else if(truckingCompanyState.value === null || truckingCompanyState.value === undefined || truckingCompanyState.value === "0"){
		SweetAlertError("Trucking Company State");
	} else {
        console.log("validations passed");
		truckingCompanyForm_DOM.append(`<input type="hidden" name="peticion" class="peticion" value="1">`);
        $.post("./../driverController", truckingCompanyForm_DOM.serialize() , function(resp) {
            
			console.log(resp);
			if (resp.insertResult === true) {
				$(".peticion").remove();
				cargarTransportistas();
				truckingCompanyForm_DOM[0].reset();
				Swal.fire("Success", "Trucking Company added succesfully", "success");
			} else {
				Swal.fire("Error", "Trucking Company could not be added, please try again", "error");
			}

		}, "json");
	}
}

function cargarTransportistas(){

	$('#truckingCompanyTable tbody').html(`
		<tr class="odd">
			<td valign="top" colspan="4" class="dataTables_empty">No data available in table</td>
		</tr>
	`);

	$.ajax({
		type: "POST",
		url: './../driverController',
		data: {peticion: "2"},
		dataSrc: "",
		success: function(data) {
			$('#truckingCompanyTable').DataTable({
				data: data,  // Get the data object
				"bDestroy": true,
				columns: [
					{ 'data': 'nombre' },
					{ 'data': 'domicilio' },
					{ 'data': 'estado' },
					{ 'data': 'idTransportista',
						"render" : function ( data, type, row, meta ){
							return `<div class="flex-buttons">
										<i class="fa fa-pencil btn btn-success btn-editing-driver" aria-hidden="true" id="e-${data}" data-toggle="modal" data-target="#editTCModal"></i>
										<i class="fa fa-trash btn btn-danger btn-removing-driver" id="r-${data}" aria-hidden="true"></i>
									</div>`;
						}

					},
				]
			});

			let btnDeleteClass = document.querySelectorAll("i.btn.btn-danger.btn-removing-driver");
			btnDeleteClass.forEach(button => {
				button.addEventListener('click', removeTC);
			});

			let btnUpdateClass = document.querySelectorAll("i.btn.btn-success.btn-editing-driver");
			btnUpdateClass.forEach(button => {
				button.addEventListener('click', seleccionarTransportista);
			});
		},
		error: function (xhr, status, error) {
			console.log(error);
		}
	});
}

function seleccionarTransportista(e){
	let tagId;
	if (e.srcElement){
		tagId = e.srcElement.id;
	} else if (e.target) {
		tagId = e.target.id;
	}

	$.post("./../driverController", {peticion: "5", idTC: tagId} , function(resp) {
		//console.log(resp);
		if (resp.isThereTC) {
			//resp
			let { idTransportista, domicilio, estado, nombre} = resp.TcData[0];

			//variables DOM
			let TCFormModal_DOM = $("#TCFormUpdate");
			let truckingCompanyNameUpdate = document.getElementById("truckingCompanyNameUpdate");
			let truckingCompanyAddressUpdate = document.getElementById("truckingCompanyAddressUpdate");
			let truckingCompanyStateUpdate = document.getElementById("truckingCompanyStateUpdate");

			truckingCompanyNameUpdate.value = nombre;
			truckingCompanyAddressUpdate.value = domicilio;
			truckingCompanyStateUpdate.value = estado;
			TCFormModal_DOM.append(`<input type="hidden" name="numberTC" id="numberTC" class="peticion" value="${idTransportista}">`);

			let btnUpdateClass = document.querySelectorAll("i.btn.btn-success.btn-editing-driver");
			btnUpdateClass.forEach(button => {
				button.addEventListener('click', seleccionarTransportista);
			});

			let btnUpdateClassSend = document.querySelectorAll(".btn-send-update-driver");
			btnUpdateClassSend.forEach(button => {
				button.addEventListener('click', updateTruckingCompany);
			});


		} else {
			Swal.fire("Error", "There was an error, try again please", "error");
		}
	}, "json");

}

function updateTruckingCompany(){
	//variables dom
	let TCFormModal_DOM = $("#TCFormUpdate");
	let TcUpdateName = document.getElementById("truckingCompanyNameUpdate");
	let TcUpdateAddress = document.getElementById("truckingCompanyAddressUpdate");
	let TcUpdateState = document.getElementById("truckingCompanyStateUpdate");

	if(TcUpdateName.value === null ||TcUpdateName.value === undefined || TcUpdateName.value === ""){
		SweetAlertError("Trucking Company Name");
	} else if (TcUpdateName.value.length < 3) {
		lengthCharacters("Trucking Company Name");
	} else if(TcUpdateAddress.value === null || TcUpdateAddress.value === undefined || TcUpdateAddress.value === ""){
		SweetAlertError("Trucking Company Address");
	} else if (TcUpdateAddress.value.length < 3) {
		lengthCharacters("Trucking Company Address");
	} else if(TcUpdateState.value === null || TcUpdateState.value === undefined || TcUpdateState.value === "0"){
		SweetAlertError("Trucking Company State");
	} else {
		TCFormModal_DOM.append(`<input type="hidden" name="peticion" class="peticion" value="3">`);
		
		$.post("./../driverController", TCFormModal_DOM.serialize(), function(resp) {

				console.log(resp);
				if (resp.resultUpdate === true) {
					$(".peticion").remove();
					$("#editTCModal").modal("hide");

					Swal.fire("Success", "Trucking Company updated succesfully", "success");
					cargarTransportistas();
					
				} else {
					Swal.fire("Error", "Trucking Company could not be updated, please try again", "error");
				}

			}, "json");
	}
}

function removeTC(e){
	let tagId;
	Swal.fire({
		title: 'Are you sure you want to remove this Trucking Company?',
		text: "You won't be able to revert this!",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: 'Yes, delete it.'
		}).then((result) => {
		if (result.isConfirmed) {
			if (e.srcElement){
				tagId = e.srcElement.id;
			} else if (e.target) {
				tagId = e.target.id;
			}

			$.post("./../driverController", {peticion: "4", idTC: tagId} , function(resp) {
				//console.log(resp);
				if (resp.resultDelete) {
					Swal.fire("Success", "Trucking Company has been removed successfully", "success");
					cargarTransportistas();
				} else {
					Swal.fire("Error", "Trucking Company could not be removed, try again please", "error");
				}
			}, "json");

		}
	});
}

/* Sweet Alerts functions to show messages */
function SweetAlertError(field){
	Swal.fire("Error", `${field} does not have to be empty`, "error");
}

function lengthCharacters(field) {
	Swal.fire("Error", `${field} must have at least 3 characters`, "error");
}

(() => {
	"use strict";
	document.addEventListener("DOMContentLoaded", () => {

		contentLoadeds();

	});  
})();

function contentLoadeds() {
	let btnAddProvider = document.getElementById("btn-addProvider");
	
	cargarProveedores();
	btnAddProvider.addEventListener('click', addProvider);
}


function addProvider(e){
	e.preventDefault();
	//variables
	let providerName = document.getElementById("providerName");
	let providerAddress = document.getElementById("providerAddress");
	let providerState = document.getElementById("providerState");

	//variables DOM
	let providerForm_DOM = $("#providerForm");
	
	
	if(providerName.value === null ||providerName.value === undefined || providerName.value === ""){
		SweetAlertError("Provider Name");
	} else if (providerName.value.length < 3) {
		lengthCharacters("Provider Name");
	} else if(providerAddress.value === null || providerAddress.value === undefined || providerAddress.value === ""){
		SweetAlertError("Provider Address");
	} else if (providerAddress.value.length < 3) {
		lengthCharacters("Provider Address");
	} else if(providerState.value === null || providerState.value === undefined || providerState.value === "0"){
		SweetAlertError("Provider State");
	} else {
		providerForm_DOM.append(`<input type="hidden" name="peticion" class="peticion" value="1">`);
        $.post("./../ProviderController", providerForm_DOM.serialize() , function(resp) {

			//console.log(resp);
			if (resp.insertResult === true) {
				$(".peticion").remove();
				cargarProveedores();
				providerForm_DOM[0].reset();
				Swal.fire("Success", "Provider added succesfully", "success");
			} else {
				Swal.fire("Error", "Provider could not be added, please try again", "error");
			}

		}, "json");
		console.log("validations passed");
	}
}

function cargarProveedores(){

	$('#providerTable tbody').html(`
		<tr class="odd">
			<td valign="top" colspan="4" class="dataTables_empty">No data available in table</td>
		</tr>
	`);
	
	$('#providerTable').DataTable();
	$.ajax({
		type: "POST",
		url: './../ProviderController',
		data: {peticion: "2"},
		dataSrc: "",
		success: function(data) {
			//console.log(data);
			$('#providerTable').DataTable({
				data: data,  // Get the data object
				"bDestroy": true,
				columns: [
					{ 'data': 'nombre' },
					{ 'data': 'domicilio' },
					{ 'data': 'estado' },
					{ 'data': 'idProveedor',
						"render" : function ( data, type, row, meta ){
							return `<div class="flex-buttons">
										<i class="fa fa-pencil btn btn-success btn-editing-provider" aria-hidden="true" id="e-${data}" data-toggle="modal" data-target="#editProviderModal"></i>
										<i class="fa fa-trash btn btn-danger btn-removing-provider" id="r-${data}" aria-hidden="true"></i>
									</div>`;
						}

					},
				]
			});

			let btnDeleteClass = document.querySelectorAll("i.btn.btn-danger.btn-removing-provider");
			btnDeleteClass.forEach(button => {
				button.addEventListener('click', removeProvider);
			});

			let btnUpdateClass = document.querySelectorAll("i.btn.btn-success.btn-editing-provider");
			btnUpdateClass.forEach(button => {
				button.addEventListener('click', seleccionarProveedor);
			});
		},
		error: function (xhr, status, error) {
			console.log(error);
			$('#providerTable').DataTable();
		}
	});
}

function seleccionarProveedor(e){
	let tagId;
	if (e.srcElement){
		tagId = e.srcElement.id;
	} else if (e.target) {
		tagId = e.target.id;
	}

	$.post("./../ProviderController", {peticion: "5", idProveedor: tagId} , function(resp) {
		//console.log(resp);
		if (resp.isThereProvider) {
			//resp
			let { idProveedor, domicilio, estado, nombre} = resp.providerData[0];

			//variables DOM
			let ProviderFormModal_DOM = $("#providerFormUpdate");
			let ProviderName = document.getElementById("providerNameUpdate");
			let ProviderAddress = document.getElementById("providerAddressUpdate");
			let ProviderState = document.getElementById("providerStateUpdate");

			ProviderName.value = nombre;
			ProviderAddress.value = domicilio;
			ProviderState.value = estado;
			ProviderFormModal_DOM.append(`<input type="hidden" name="numberProvider" id="numberProvider" class="peticion" value="${idProveedor}">`);

			let btnUpdateClass = document.querySelectorAll("i.btn.btn-success.btn-editing-provider");
			btnUpdateClass.forEach(button => {
				button.addEventListener('click', seleccionarProveedor);
			});

			let btnUpdateClassSend = document.querySelectorAll(".btn-send-update-provider");
			btnUpdateClassSend.forEach(button => {
				button.addEventListener('click', updateProvider);
			});


		} else {
			Swal.fire("Error", "There was an error, try again please", "error");
		}
	}, "json");

}

function updateProvider(){
	//variables dom
	let ProviderFormModal_DOM = $("#providerFormUpdate");
	let ProviderName = document.getElementById("providerNameUpdate");
	let ProviderAddress = document.getElementById("providerAddressUpdate");
	let ProviderState = document.getElementById("providerStateUpdate");

	if(ProviderName.value === null ||ProviderName.value === undefined || ProviderName.value === ""){
		SweetAlertError("Provider Name");
	} else if (ProviderName.value.length < 3) {
		lengthCharacters("Provider Name");
	} else if(ProviderAddress.value === null || ProviderAddress.value === undefined || ProviderAddress.value === ""){
		SweetAlertError("Provider Address");
	} else if (ProviderAddress.value.length < 3) {
		lengthCharacters("Provider Address");
	} else if(ProviderState.value === null || ProviderState.value === undefined || ProviderState.value === "0"){
		SweetAlertError("Provider State");
	} else {
		ProviderFormModal_DOM.append(`<input type="hidden" name="peticion" class="peticion" value="3">`);
		
		$.post("./../ProviderController", ProviderFormModal_DOM.serialize(), function(resp) {

				console.log(resp);
				if (resp.resultUpdate === true) {
					$(".peticion").remove();
					$("#editProviderModal").modal("hide");

					Swal.fire("Success", "Provider updated succesfully", "success");
					cargarProveedores();
					
				} else {
					Swal.fire("Error", "Provider could not be updated, please try again", "error");
				}

			}, "json");
	}
}

function removeProvider(e){
	let tagId;
	Swal.fire({
		title: 'Are you sure you want to remove this Provider?',
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

			$.post("./../ProviderController", {peticion: "4", idProvider: tagId} , function(resp) {
				//console.log(resp);
				if (resp.resultDelete) {
					Swal.fire("Success", "Provider has been removed successfully", "success");
					cargarProveedores();
				} else {
					Swal.fire("Error", "Provider could not be removed, try again please", "error");
				}
			}, "json");

		}
	});
}


function SweetAlertError(field){
	Swal.fire("Error", `${field} does not have to be empty`, "error");
}

function lengthCharacters(field) {
	Swal.fire("Error", `${field} must have at least 3 characters`, "error");
}
	 
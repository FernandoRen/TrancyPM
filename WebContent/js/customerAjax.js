(() => {
  	
	document.addEventListener("DOMContentLoaded", () => {

		contentLoadeds();

	});  
})();

let btn_editCustomer = document.createElement("button");
let btn_deleteCustomer = document.createElement("button");

function contentLoadeds() {
	let btnAddCustomer = document.getElementById("btn-addCustomer");
	
	//});
	cargarClientes();
	btnAddCustomer.addEventListener('click', addCustomer);
}


function addCustomer(e){
	//variables
	let customerName = document.getElementById("customerName");
	let customerAddress = document.getElementById("customerAddress");
	let customerState = document.getElementById("customerState");

	//variables DOM
	let customerForm_DOM = $("#customerForm");
	
	e.preventDefault();
	if(customerName.value === null ||customerName.value === undefined || customerName.value === ""){
		SweetAlertError("Customer Name");
	} else if (customerName.value.length < 3) {
		lengthCharacters("Customer Name");
	} else if(customerAddress.value === null || customerAddress.value === undefined || customerAddress.value === ""){
		SweetAlertError("Customer Address");
	} else if (customerAddress.value.length < 3) {
		lengthCharacters("Customer Address");
	} else if(customerState.value === null || customerState.value === undefined || customerState.value === "0"){
		SweetAlertError("Customer State");
	} else {
		customerForm_DOM.append(`<input type="hidden" name="peticion" class="peticion" value="1">`);
        $.post("./../CustomerController", customerForm_DOM.serialize() , function(resp) {

			console.log(resp);
			if (resp.insertResult === true) {
				$(".peticion").remove();
				cargarClientes();
				Swal.fire("Success", "Customer added succesfully", "success");
			} else {
				Swal.fire("Error", "Customer could not be added, please try again", "error");
			}

		}, "json");
	}
}

function cargarClientes(){
	let customerTableBody = document.querySelector("#customerTable tbody");

    /*$.post("./../CustomerController", {peticion: "2"} , function(resp) {

		console.log(resp);
		if (resp.areThereCustomers) {
			let dataClientes = resp.customerData;
			$(".peticion").remove();
			customerTableBody.innerHTML = "";
			
			dataClientes.forEach(element => {
				
				customerTableBody.innerHTML += `
				
					<tr>
						<td>${element.nombre}</td>
						<td>${element.domicilio}</td>
						<td>${element.estado}</td>
						<td id="${element.idCliente}"></td>
					</tr>
				
				`;

				//button to delete a customer
				btn_deleteCustomer.classList.add("btn", "btn-danger", "btn-removing-customer");
				btn_deleteCustomer.innerText = "Remove";
				btn_deleteCustomer.setAttribute("id", "r-" + element.idCliente);

				//button to update a customer
				btn_editCustomer.classList.add("btn", "btn-success", "btn-editing-customer");
				btn_editCustomer.setAttribute("data-toggle", "modal");
				btn_editCustomer.setAttribute("data-target", "#editCustomerModal");
				btn_editCustomer.innerText = "Edit";
				btn_editCustomer.setAttribute("id", "e-" + element.idCliente);

				//adding buttons to datatable
				let tdAppendButtons = document.getElementById(element.idCliente);
				tdAppendButtons.appendChild(btn_editCustomer);
				tdAppendButtons.appendChild(btn_deleteCustomer);

				let btnDeleteClass = document.querySelectorAll("button.btn.btn-danger.btn-removing-customer");
				btnDeleteClass.forEach(button => {
					button.addEventListener('click', removeCustomer);
				});

				let btnUpdateClass = document.querySelectorAll("button.btn.btn-success.btn-editing-customer");
				btnUpdateClass.forEach(button => {
					button.addEventListener('click', seleccionarCliente);
				});

			});
			
		}

	}, "json");*/

	$.ajax({
		type: "POST",
		url: './../CustomerController',
		data: {peticion: "2"},
		dataSrc: "",
		success: function(data) {
			//jsonResult = result.customerData;
			//data = JSON.parse(data);
			console.log(data);
			$('#customerTable').DataTable({
				data: data,  // Get the data object
				"bDestroy": true,
				columns: [
					{ 'data': 'nombre' },
					{ 'data': 'domicilio' },
					{ 'data': 'estado' },
					//{ 'data': 'idCliente' },
					{ 'data': 'idCliente',
						"render" : function ( data, type, row, meta ){
							return `<button type="button" class="btn btn-success btn-editing-customer" id="e-${data}" data-toggle="modal" data-target="#editCustomerModal">Edit</button>
									<button type="button" class="btn btn-danger btn-removing-customer" id="r-${data}">Remove</button>`;
						}

					},
				]
			});
			let btnDeleteClass = document.querySelectorAll("button.btn.btn-danger.btn-removing-customer");
			btnDeleteClass.forEach(button => {
				button.addEventListener('click', removeCustomer);
			});
			console.log(btnDeleteClass);

			let btnUpdateClass = document.querySelectorAll("button.btn.btn-success.btn-editing-customer");
			btnUpdateClass.forEach(button => {
				button.addEventListener('click', seleccionarCliente);
			});
		},
		error: function (xhr, status, error) {
			console.log(error)
		}
	});
}

function removeCustomer(e){
	let tagId;
	Swal.fire({
		title: 'Are you sure you want to remove this Customer?',
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

			$.post("./../CustomerController", {peticion: "4", idCustomer: tagId} , function(resp) {
				//console.log(resp);
				if (resp.resultDelete) {
					Swal.fire("Success", "Customer has been removed successfully", "success");
					cargarClientes();
				} else {
					Swal.fire("Error", "Customer could not be removed, try again please", "error");
				}
			}, "json");

		}
	});
}

function seleccionarCliente(e){
	let tagId;
	if (e.srcElement){
		tagId = e.srcElement.id;
	} else if (e.target) {
		tagId = e.target.id;
	}

	$.post("./../CustomerController", {peticion: "5", idCustomer: tagId} , function(resp) {
		console.log(resp);
		if (resp.isThereCustomer) {
			//resp
			let { idCliente, domicilio, estado, nombre} = resp.customerData[0];

			//variables DOM
			let customerFormModal_DOM = $("#customerFormUpdate");
			let customerName = document.getElementById("customerNameUpdate");
			let customerAddress = document.getElementById("customerAddressUpdate");
			let customerState = document.getElementById("customerStateUpdate");

			customerName.value = nombre;
			customerAddress.value = domicilio;
			customerState.value = estado;
			customerFormModal_DOM.append(`<input type="hidden" name="numberCustomer" id="numberCustomer" class="peticion" value="${idCliente}">`);

			let btnUpdateClass = document.querySelectorAll("button.btn.btn-success.btn-editing-customer");
			btnUpdateClass.forEach(button => {
				button.addEventListener('click', seleccionarCliente);
			});

			let btnUpdateClassSend = document.querySelectorAll(".btn-send-update");
			btnUpdateClassSend.forEach(button => {
				button.addEventListener('click', updateCustomer);
			});


		} else {
			Swal.fire("Error", "There was an error, try again please", "error");
		}
	}, "json");

}

function updateCustomer(){
	//variables dom
	let customerFormModal_DOM = $("#customerFormUpdate");
	let customerName = document.getElementById("customerNameUpdate");
	let customerAddress = document.getElementById("customerAddressUpdate");
	let customerState = document.getElementById("customerStateUpdate");

	if(customerName.value === null ||customerName.value === undefined || customerName.value === ""){
		SweetAlertError("Customer Name");
	} else if (customerName.value.length < 3) {
		lengthCharacters("Customer Name");
	} else if(customerAddress.value === null || customerAddress.value === undefined || customerAddress.value === ""){
		SweetAlertError("Customer Address");
	} else if (customerAddress.value.length < 3) {
		lengthCharacters("Customer Address");
	} else if(customerState.value === null || customerState.value === undefined || customerState.value === "0"){
		SweetAlertError("Customer State");
	} else {
		customerFormModal_DOM.append(`<input type="hidden" name="peticion" class="peticion" value="3">`);
		
        $.post("./../CustomerController", customerFormModal_DOM.serialize(), function(resp) {

				//console.log(resp);
				if (resp.resultUpdate === true) {
					$(".peticion").remove();
					$("#editCustomerModal").modal("hide");

					Swal.fire("Success", "Customer updated succesfully", "success");
					cargarClientes();
					
				} else {
					Swal.fire("Error", "Customer could not be updated, please try again", "error");
				}

			}, "json");
	}
	
}

/* Sweet Alerts functions to show messages */
function SweetAlertError(field){
	Swal.fire("Error", `${field} does not have to be empty`, "error");
}

function lengthCharacters(field) {
	Swal.fire("Error", `${field} must have at least 3 characters`, "error");
}

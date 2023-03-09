(() => {
    "use strict"
    document.addEventListener("DOMContentLoaded", () => {

      contentLoadeds();
	  $('#usersTable').DataTable();

 	 });  
})();

function contentLoadeds() {
	let btnAddUser= document.getElementById("btn-addUser");
	
	cargarUsuarios();
	btnAddUser.addEventListener('click', addUser);
}

function addUser(e){
	//variables
	let userEmail = document.getElementById("newUserEmail");
	let userPassword = document.getElementById("newUserPassword");
  	let userPasswordConfirm = document.getElementById("newUserPassword2");
	let userName = document.getElementById("newUserName");
  	let userLastName = document.getElementById("newUserLastName");
	let userRole= document.getElementById("newUserRole");

	//variables DOM
	let usersForm_DOM = $("#usersForm");
	
  	e.preventDefault();
	if(userEmail.value === null || userEmail.value === undefined || userEmail.value === ""){
		SweetAlertError("Email");
	} else if (!validarEmail(userEmail.value)) {
		Swal.fire("Error", "Email invalid, please type a valid email", "error");
	} else if(userPassword.value === null || userPassword.value === undefined || userPassword.value === ""){
		SweetAlertError("User Password");
	} else if (!validarPassword(userPassword.value)) {
		Swal.fire("Error", `<strong>Password invalid, please type a password with this format</strong>
								<ul>
									<li>Min 8 characters</li>
									<li>Max 15 characters</li>
									<li>At least 1 capital letter</li>
									<li>At least 1 lowercase letter</li>
									<li>At least 1 number</li>
									<li>No blank spaces</li>
									<li>At least 1 special character like these <strong>$@$!%*?&</strong></li>
								<ul>
        `, "error");
	} else if(userPasswordConfirm.value === null || userPasswordConfirm.value === undefined || userPasswordConfirm.value === ""){
		SweetAlertError("Confirm Password");
	} else if(userPassword.value !== userPasswordConfirm.value){
		Swal.fire("Error", "Passwords are different", "error");
	} else if(userName.value === null || userName.value === undefined || userName.value === ""){
		SweetAlertError("Name");
	} else if (userName.value.length < 3) {
		lengthCharacters("Name");
	} else if(userLastName.value === null || userLastName.value === undefined || userLastName.value === ""){
		SweetAlertError("Last Name");
	} else if (userLastName.value.length < 3) {
		lengthCharacters("Last Name");
	} else if(userRole.value === null || userRole.value === undefined || userRole.value === "0"){
		SweetAlertError("Role");
	} else {
      usersForm_DOM.append(`<input type="hidden" name="peticion" class="peticion" value="1">`);
      $.post("./../usersController", usersForm_DOM.serialize() , function(resp) {
            
			console.log(resp);
			if (resp.insertResult === true) {
				$(".peticion").remove();
				cargarUsuarios();
				usersForm_DOM[0].reset();
				Swal.fire("Success", "User added succesfully", "success");
			} else {
				Swal.fire("Error", "User could not be added, please try again", "error");
			}

		}, "json");
	}
}

function cargarUsuarios(){

	$('#usersTable tbody').html(`
		<tr class="odd">
			<td valign="top" colspan="5" class="dataTables_empty">No data available in table</td>
		</tr>
	`);

	$.ajax({
		type: "POST",
		url: './../usersController',
		data: {peticion: "2"},
		dataSrc: "",
		success: function(data) {
			$('#usersTable').DataTable({
				data: data,  // Get the data object
				"bDestroy": true,
				columns: [
					{ 'data': 'email' },
					{ 'data': 'nombre' },
					{ 'data': 'apellidos' },
          			{ 'data': 'rolDescripcion' },
					{ 'data': 'idUsuario',
						"render" : function ( data, type, row, meta ){
							return `<div class="flex-buttons">
										<i class="fa fa-pencil btn btn-success btn-editing-user" aria-hidden="true" id="e-${data}" data-toggle="modal" data-target="#editUserModal"></i>
										<i class="fa fa-trash btn btn-danger btn-removing-user" id="r-${data}" aria-hidden="true"></i>
									</div>`;
						}

					},
				]
			});

			let btnDeleteClass = document.querySelectorAll("i.btn.btn-danger.btn-removing-user");
			btnDeleteClass.forEach(button => {
				button.addEventListener('click', removeUser);
			});

			let btnUpdateClass = document.querySelectorAll("i.btn.btn-success.btn-editing-user");
			btnUpdateClass.forEach(button => {
				button.addEventListener('click', seleccionarUsuario);
			});
		},
		error: function (xhr, status, error) {
			console.log(error);
		}
	});
}

function seleccionarUsuario(e){
	let tagId;
	if (e.srcElement){
		tagId = e.srcElement.id;
	} else if (e.target) {
		tagId = e.target.id;
	}
	
	$.post("./../usersController", {peticion: "5", idUser: tagId} , function(resp) {
		//console.log(resp);
		if (resp.isThereUser) {
			//resp
			let { idUsuario, email, nombre, apellidos, rolDescripcion} = resp.userData[0];

			//variables DOM
			let userFormUpdate_DOM = $("#userFormUpdate");
			let userEmailUpdate = document.getElementById("userEmailUpdate");
			let userNameUpdate = document.getElementById("userNameUpdate");
			let userLastNameUpdate = document.getElementById("userLastNameUpdate");
      		let userRoleUpdate = document.getElementById("userRoleUpdate");

			userEmailUpdate.value = email;
			userNameUpdate.value = nombre;
			userLastNameUpdate.value = apellidos;
      		userRoleUpdate.value = rolDescripcion;

			userFormUpdate_DOM.append(`<input type="hidden" name="idUser" id="idUser" class="peticion" value="${idUsuario}">`);

			let btnUpdateClass = document.querySelectorAll("i.btn.btn-success.btn-editing-user");
			btnUpdateClass.forEach(button => {
				button.addEventListener('click', seleccionarUsuario);
			});

			let btnUpdateClassSend = document.querySelectorAll(".btn-send-update-user");
			btnUpdateClassSend.forEach(button => {
				button.addEventListener('click', updateUser);
			});


		} else {
			Swal.fire("Error", "There was an error, try again please", "error");
		}
	}, "json");

}

function updateUser(){
	//variables
	let userEmail = document.getElementById("userEmailUpdate");
	let userName = document.getElementById("userNameUpdate");
  	let userLastName = document.getElementById("userLastNameUpdate");
	let userRole= document.getElementById("userRoleUpdate");

	//variables DOM
	let userFormUpdate_DOM = $("#userFormUpdate");

	if(userEmail.value === null || userEmail.value === undefined || userEmail.value === ""){
		SweetAlertError("Email");
	} else if (!validarEmail(userEmail.value)) {
		Swal.fire("Error", "Email invalid, please type a valid email", "error");
	} else if(userName.value === null || userName.value === undefined || userName.value === ""){
		SweetAlertError("Name");
	} else if (userName.value.length < 3) {
		lengthCharacters("Name");
	} else if(userLastName.value === null || userLastName.value === undefined || userLastName.value === ""){
		SweetAlertError("Last Name");
	} else if (userLastName.value.length < 3) {
		lengthCharacters("Last Name");
	} else if(userRole.value === null || userRole.value === undefined || userRole.value === "0"){
		SweetAlertError("Role");
	} else {
		userFormUpdate_DOM.append(`<input type="hidden" name="peticion" class="peticion" value="3">`);
		$.post("./../usersController", userFormUpdate_DOM.serialize(), function(resp) {

				console.log(resp);
				if (resp.resultUpdate === true) {
					$(".peticion").remove();
					$("#editUserModal").modal("hide");

					Swal.fire("Success", "User updated succesfully", "success");
					cargarUsuarios();
					
				} else {
					Swal.fire("Error", "User could not be updated, please try again", "error");
				}

			}, "json");
	}
}

function removeUser(e){
	let tagId;
	Swal.fire({
		title: 'Are you sure you want to remove this user?',
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

			$.post("./../usersController", {peticion: "4", idUser: tagId} , function(resp) {
				//console.log(resp);
				if (resp.resultDelete) {
					Swal.fire("Success", "User has been removed successfully", "success");
					cargarUsuarios();
				} else {
					Swal.fire("Error", "User could not be removed, try again please", "error");
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
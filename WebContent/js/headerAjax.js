(() => {
    "use strict";
	document.addEventListener("DOMContentLoaded", () => {

		contentLoadedsHeader();

	});  
})();

function contentLoadedsHeader() {
    let changePasswordBtn = document.getElementById("btn-updatePasswordModal");

    changePasswordBtn.addEventListener("click", changePassword)
}

function changePassword(){
    let currentPassword = document.getElementById("currentPasswordUpdate");
    let newPassword = document.getElementById("newPasswordUpdate");
    let confirmNewPassword = document.getElementById("newPasswordConfirmUpdate");

    if (currentPassword.value === null || currentPassword.value === undefined || currentPassword.value === "") {
        SweetAlertError("Current Password");
    } else if(!validarPassword(currentPassword.value)){
        callInvalidPassword();
    } else if (newPassword.value === null || newPassword.value === undefined || newPassword.value === "") {
        SweetAlertError("New Password");
    } else if(!validarPassword(newPassword.value)){
        callInvalidPassword();
    } else if (confirmNewPassword.value === null || confirmNewPassword.value === undefined || confirmNewPassword.value === "") {
        SweetAlertError("Confirm New Password");
    } else if(!validarPassword(confirmNewPassword.value)){
        callInvalidPassword();
    } else if (confirmNewPassword.value !== newPassword.value ) {
        Swal.fire("Error", "New Password and Confirm New Password are different", "error");
    } else if (newPassword.value === currentPassword.value || confirmNewPassword.value === currentPassword.value ) {
        Swal.fire("Error", "Current Password and New Password can't be equal", "error");
    } else {
        console.log("validations passed");
    }

}

function callInvalidPassword(){
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
}
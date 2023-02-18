(() => {
    "use strict";
    
    document.addEventListener("DOMContentLoaded", () => {
    	
    	let usuario = document.getElementById("email-Login");
        let pass = document.getElementById("password-Login");
        let btn_login = document.getElementById("sendCredentials-Login");

        btn_login.addEventListener("click", login);

        function login(e) {
            e.preventDefault();
            if (usuario.value === "") {
                Swal.fire("Error", "Type an email, please", "error");
            } else if (validarEmail(usuario.value) !== true) {
                Swal.fire("Error", "Email is invalid, please type a valid email", "error");
            } else if (pass.value === "") {
                Swal.fire("Error", "Type a password, please", "error");
            } else if (pass.value.length < 4) {
                Swal.fire("Error", "Password is too short", "error");
            } else {
                $.post("loginController", { usuario: usuario.value, pass: pass.value }, function(resp) {

                    const respAjax = resp.resultLogin;
                	console.log(resp);
                    //guardar usuario y rol en local storage
                    if (respAjax === true) {
                        
                    	let user = resp.userData[0].email;
                    	let role = md5(resp.userData[0].roles);///
                    	let name = resp.userData[0].name;
                    	
                    	localStorage.setItem("user", user);
                    	localStorage.setItem("role", role);
                    	$.post("ValidatorController", { user: user, role: role, name: name, operation:"1" }, function(){
                    		window.location.replace("vistas/inicioVista.jsp");
                    	});

                        
                    } else {
                        Swal.fire("Error", "User or password incorrect, please try again", "error");
                    }


                }, "json");
            }
        }
    	
    });

})();
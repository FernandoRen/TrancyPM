(() => {
    "use strict";
    let userStorage = localStorage.getItem("user");
    let roleStorage = localStorage.getItem("role");
    
    if((userStorage === null || userStorage === "" || userStorage === undefined) || 
    		(roleStorage === null || roleStorage === "" || roleStorage === undefined)){
    	window.location.replace("../index.html");
    } else {
    	$.post("../ValidatorController", { usuario: userStorage, operation:"2" }, function(resp) {

            const respAjax = resp.resultCheckUser;
            
        	if(!respAjax){
        		window.location.replace("../index.html");
        	}

        });
    }

})();
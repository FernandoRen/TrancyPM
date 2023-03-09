(() => {
    "use strict"
  document.addEventListener("DOMContentLoaded", () => {

      contentLoadeds();
      $('#searchPackagesTable').DataTable();

  });  
})();

function contentLoadeds() {
  let btnSearchPackage= document.getElementById("btn-searchPackage");
  
  //cargarClientes();
  btnSearchPackage.addEventListener('click', searchPackage);
}

function searchPackage(e){
    e.preventDefault();

    let guideNumber = document.getElementById("btn-searchPackage");

    if (guideNumber.value === undefined || guideNumber.value === null || guideNumber.value === "") {
        Swal.fire("Error", "Guide number does not have to be empty", "error");
    } else if(guideNumber.value.length < 3){
        Swal.fire("Error", "Guide number must have at least 3 characters", "error");
    } else {
        console.log("validations passed");
        
    }

}
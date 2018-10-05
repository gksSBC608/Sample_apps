/**
 * 
 */
function validate() {
	var validated = true;

	var id = document.getElementById("id").value;
	var name = document.getElementById("name").value;
	var category = document.getElementById("category").value;
	var price = document.getElementById("price").value;
	var detail = document.getElementById("detail").value;
	var idError = document.getElementById("idError");
	var nameError = document.getElementById("nameError");
	var idError = document.getElementById("idError");
	var categoryError = document.getElementById("categoryError");
	
	if (id == ""){
		validated = false;
		idError.innerHTML= "id can't be empty"
	}
	if (name == ""){
		validated = false;
		nameError.innerHTML= "name can't be empty"
	}
	if (category == ""){
		validated = false;
		idError.innerHTML= "id can't be empty"
	}
	if (price == "") {
		validated = false;
		priceError.innerHTML = "Can't be empty";
	}
	if (price != "" && isNaN(price)) {
		validated = false;
		priceError.innerHTML = "";
		priceError.innerHTML = "Can't be text";
	}
	if (detail == "")
		validated = false;
	return validated;
}
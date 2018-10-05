/**
 * 
 */
function initialize() {
	var element = document.getElementById("msg");
	var uError = document.getElementById("idError");
	var pError = document.getElementById("pwdError");

	element.innerHTML = "";
	uError.innerHTML = "";
	pError.innerHTML = "";

	var element1 = document.getElementById("msg1");
	element1.innerHTML = "";
}
function initializeA() {
	var element = document.getElementById("msg");
	var uError = document.getElementById("adminidError");
	var pError = document.getElementById("adminpwdError");

	element.innerHTML = "";
	uError.innerHTML = "";
	pError.innerHTML = "";

	var element1 = document.getElementById("msg1");
	element1.innerHTML = "";
}
function initialize1() {
	var msgE = document.getElementById("msgAjax");
	msgE.innerHTML = "";
}
function validate() {
	var validated = true;
	var uE = document.getElementById("userid");
	var pE = document.getElementById("password");
	var uError = document.getElementById("idError");
	var pError = document.getElementById("pwdError");
	var id = uE.value;
	var password = pE.value;
	if (id == "") {
		uError.innerHTML = "User ID can't be empty";
		validated = false;
	}
	if (password == "") {
		pError.innerHTML = "password can't be blank";
		validated = false;
	}
	return validated;
}

function validateAdmin() {
	var validated = true;
	var uE = document.getElementById("adminid");
	var pE = document.getElementById("adminpwd");
	var uError = document.getElementById("adminidError");
	var pError = document.getElementById("adminpwdError");
	var id = uE.value;
	var password = pE.value;
	if (id == "") {
		uError.innerHTML = "User ID can't be empty";
		validated = false;
	}
	if (password == "") {
		pError.innerHTML = "password can't be blank";
		validated = false;
	}
	return validated;
}

function test() {
	var m = document.getElementById("test1");
	var p = document.getElementById("gks1");
	alert(m.value);
	p.innerHTML = "Gaurav";
}
function validateAjax() {

	var xmlHttp = null;
	var uE = document.getElementById("userid");
	var name = uE.value;

	var mE = document.getElementById("msgAjax");
	if (name == "") {
		mE.innerHTML = "";
	}
	if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();

	}
	var url = "validate?userid=" + name;

	xmlHttp.open("GET", url, true);
	xmlHttp.send();
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			var res = xmlHttp.responseText;

			if (res.charAt(0) == 'A') {

				mE.style.color = "green";
			} else {
				mE.style.color = "red";
			}
			// alert(res);
			mE.innerHTML = "";

			mE.innerHTML = res;

		}
	};

}
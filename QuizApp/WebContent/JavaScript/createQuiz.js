/**
 * 
 */
function validate() {
	var validated = true;

	var count = document.getElementById("optCount");
	var c = count.value;

	if (c == "select") {

		var errorCount = document.getElementById("selectionError");
		errorCount.innerHTML = "Select some value ";
		validated = false;
	}

	return validated;
}

function validateOptions(opt) {
	initialize(opt);
	var validated = true;
	var question = document.getElementById("question");
	var q = question.value;

	if (q == "") {
		question.focus();
		var errorId = document.getElementById("questionError");
		errorId.innerHTML = "Please Enter a suitable value";
		validated = false;
	}

	for (var i = 1; i <= opt; i++) {

		var v = document.getElementById("opt" + i).value;

		if (v == null || v == "") {
			var e = document.getElementById("err" + i);
			e.innerHTML = "Please Enter a suitable value";
			validated = false;
			// alert(validated);
		}
	}
	return validated;
}

function initialize(opt) {
	for (var i = 1; i <= opt; i++) {
		var e = document.getElementById("err" + i);
		e.innerHTML = "";	
		}
	return;
}
function initializeQError( ) {
	
	var errorId = document.getElementById("questionError");
	errorId.innerHTML = "";
	return;
}
function redirectHome() {
	window.location.href = "quizHome";
}
function func(){
	console.log("Going to create Question");
	window.location.href = "http://localhost:8080/M1030608/createQuiz.jsp";
}
function validate() {
		var validated = true;
		var dateE = document.getElementById("subDate")
		var date = dateE.value;

		var subscriptionId = document.getElementById("subscriptionId");
		var id = subscriptionId.value;

		if (id == "") {
			subscriptionId.focus();
			var errorId = document.getElementById("errorId");
			errorId.innerHTML = "Please Enter a suitable value";
			validated = false;
		}
		if (id != "" && id.length < 10) {
			subscriptionId.focus();
			subscriptionId.select();
			var errorId = document.getElementById("errorId");
			errorId.innerHTML = "Length should be 10";
			validated = false;
		}
		var channel = document.getElementById("channel");
		var channelValue = channel.value;

		if (channelValue == "select") {

			var errorChannel = document.getElementById("errorChannel");
			errorChannel.innerHTML = "Select some value ";
			validated = false;
		}

		if (date == "") {
			dateE.focus();
			var errorDate = document.getElementById("errorDate");
			errorDate.innerHTML = "Date shouldn't be empty ";
			validated = false;
		}

		return validated;
	}
function initialize() {
	
	var errorId = document.getElementById("errorId");
	errorId.innerHTML = "";
	var errorChannel = document.getElementById("errorChannel");
	errorChannel.innerHTML = "";
	var errorDate = document.getElementById("errorDate");
	errorDate.innerHTML = "";
	return;
}
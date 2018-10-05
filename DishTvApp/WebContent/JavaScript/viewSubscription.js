/**
 * 
 */
function getChannelsDetail() {
	var xmlHttp = null;
	var id = document.getElementById("id").value;
	if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	} else {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlHttp.open("POST", "/DishTvApp/viewDetail", true);
	xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xmlHttp.send("id="+id);
	xmlHttp.onreadystatechange = function() {
		alert(xmlHttp.readyState);
		if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
			
			var res = xmlHttp.responseText;
         document.getElementById("myDiv").innerHTML =res;
		}
	};

}
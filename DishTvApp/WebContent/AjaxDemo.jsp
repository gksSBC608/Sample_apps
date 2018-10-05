<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
	function process() {
		var uE = document.getElementById("userid");
		var name = uE.value;
		
		var mE = document.getElementById("msg");
		mE.innerHTML = name;
		if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();

		}
		var url = "validate.jsp?value=" + name;

		xmlHttp.open("GET", url, true);
		xmlHttp.send();
		xmlHttp.onreadystatechange = function() {
			alert(xmlHttp.readyState);
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				var res = xmlHttp.responseText;
				//alert(res);
				document.getElementById("msg").innerHTML = "";
				document.getElementById("msg").innerHTML = res;

			}
		};

	}
</script>
</head>
<body>
	Name
	<input type="text" name="userid" id="userid" onfocus="initialize()"
		onkeyup="process()">

	<br> Password
	<input type="text" name="password" id="password" onfocus="initialize()">
	<dd id="msg"></dd>

</body>
</html>
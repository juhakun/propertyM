<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>List houses</title>

<!-- reference our css sheet -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Hausverwaltung</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
			<table>
				<tr>
					<th>Objektname:</th>
					<th>Strasse:</th>
					<th>Hausnummer:</th>
					<th>PLZ:</th>
					<th>Stadt:</th>
				</tr>
				<c:forEach var="tempHouse" items="${ houses }">
					<tr>
						<td>${ tempHouse.getObjectName() }</td>
						<td>${ tempHouse.address.getStreet() }</td>
						<td>${ tempHouse.address.getHouseNo() }</td>
						<td>${ tempHouse.address.getPostalCode() }</td>
						<td>${ tempHouse.address.getCity() }</td>
				</c:forEach>
			</table>
		</div>
	</div>

</body>

</html>
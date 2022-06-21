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
				<!-- create an update link -->
				<c:url var="updateLink" value="/house/showFormForUpdate">
				<c:param name="idHouse" value="${ tempHouse.getIdHouse() }" />
				</c:url>
				<!-- create an delete link -->
				<c:url var="deleteLink" value="/house/deleteObject">
				<c:param name="idHouse" value="${ tempHouse.getIdHouse() }" />
				</c:url>
				<!-- create an add units link -->
				<c:url var="addUnitsLink" value="/unit/showForm">
				<c:param name="idHouse" value="${ tempHouse.getIdHouse() }" />
				</c:url>
				<!-- create an add counters link -->
				<c:url var="addCountersLink" value="/counter/showForm">
				<c:param name="idHouse" value="${ tempHouse.getIdHouse() }" />
				</c:url>
				
					<tr>
						<td>${ tempHouse.getObjectName() }</td>
						<td>${ tempHouse.address.getStreet() }</td>
						<td>${ tempHouse.address.getHouseNo() }</td>
						<td>${ tempHouse.address.getPostalCode() }</td>
						<td>${ tempHouse.address.getCity() }</td>
						
						<td>
						<!-- display update link -->
						<a href="${updateLink}">Bearbeiten</a>
						<!-- display delete link -->
						<a href="${deleteLink}">L�schen</a>
						<!-- display add units link -->
						<a href="${addUnitsLink}">Wohneinheiten hinzuf�gen</a>
						<!-- display add counters link -->
						<a href="${addCountersLink}">Z�hler hinzuf�gen</a>
				</c:forEach>
			</table>
		</div>
	</div>

</body>

</html>
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
					<th>Haus:</th>
					<th>Wohneinheit:</th>
					<th>Hausnummer:</th>
					<th>PLZ:</th>
					<th>Stadt:</th>
				</tr>
				<c:forEach var="tempUnit" items="${ units }">
				<!-- create an update link -->
				<c:url var="updateLink" value="/unit/showFormForUpdate">
				<c:param name="idHouse" value="${ tempHouse.getIdHouse() }" />
				</c:url>
				<!-- create an delete link -->
				<c:url var="deleteLink" value="/unit/deleteObject">
				<c:param name="idHouse" value="${ tempHouse.getIdHouse() }" />
				</c:url>
				<!-- create an add units link -->
				
					<tr>
						<td>${ tempUnit.house.objectName }</td>
						<td>${ tempUnit.unitName }</td>
						
						
						<td>
						<!-- display update link -->
						<a href="${updateLink}">Bearbeiten</a>
						<!-- display delete link -->
						<a href="${deleteLink}">Löschen</a>
		
				</c:forEach>
			</table>
		</div>
	</div>

</body>

</html>
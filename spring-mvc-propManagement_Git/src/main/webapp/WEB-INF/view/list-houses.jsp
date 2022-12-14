<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<title>List houses</title>

<!-- reference our css sheet -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />

</head>


<body>


	<div>
		<h2>Hausverwaltung und Nebenkostenabrechnung</h2>


		<div id="navi">
			<div id="button">
				<c:url var="toMainPage" value="/">
				</c:url>
				<a href="${toMainPage}" style="color: white"><b>Startseite</b></a>
			</div>
			<div id="button">
				<c:url var="toHouses" value="/house/listHouses">
				</c:url>
				<a href="${toHouses}" style="color: white">Meine H?user</a>
			</div>
			<div id="button">
				<c:url var="toCalc" value="/">
				</c:url>
				<a href="${toCalc}" style="color: white">Jahresabrechnung</a>
			</div>
			<br> <br>
		</div>

		<div id="container"
			style="min-height: calc(${ noOfHouses } * 240px + 200px)">
			<h3>
				<c:set var="MyHtml" value="Alle H?user" />
				<c:if test="${ noOfHouses == 1 }">
					<c:set var="MyHtml" value="" />
				</c:if>
				${ MyHtml } <br> <br>
			</h3>
			<c:forEach var="tempHouse" items="${ houses }">
				<!-- create an update link 
				<c:url var="updateLink" value="/house/showFormForUpdate">
					<c:param name="idHouse" value="${ tempHouse.getIdHouse() }" />
				</c:url>
				<!-- create an delete link -->
				<c:url var="deleteLink" value="/house/deleteObject">
					<c:param name="idHouse" value="${ tempHouse.getIdHouse() }" />
				</c:url>
				<!-- create an add units link -->
				<c:url var="showUnitsLink"
					value="/unit/showUnits/${ tempHouse.getIdHouse() }">
				</c:url>
				<!-- create an add counters link -->
				<c:url var="addCountersLink"
					value="/counter/showCounters/${ tempHouse.getIdHouse() }">
				</c:url>


				<div id="objects">
					<h3>${ tempHouse.getObjectName().toUpperCase() }</h3>
					${ tempHouse.address.getStreet() }
								${ tempHouse.address.getHouseNo() }<br>
								${ tempHouse.address.getPostalCode() } ${ tempHouse.address.getCity() }
								<br><br>
								
					<table style="border-top: 1px solid; border-color: #084B8A;">

						<tr>
							<td style="font-style:italic; padding-bottom: 0px; padding-top: 20px; width: 20%">Gesamtfl?che:</td>
							<td style="padding-bottom: 0px; width: 25%">${ tempHouse.totalAreaM2 } m2</td>

							<td style="font-style:italic;padding-bottom: 0px; width: 20%">Eigent?mer:</td>
							<td style="padding-bottom: 0px; width: 35%">${ tempHouse.owner.getFirstName() }
								${ tempHouse.owner.getLastName() }</td>
						</tr>


						<tr>
							<td style="font-style:italic;padding-bottom: 0px; width: 20%">Wohneinheiten:</td>
							<td style="padding-bottom: 0px; width: 25%">${ tempHouse.noOfUnits }</td>

							<td style="font-style:italic;padding-bottom: 0px; width: 20%">Adresse:</td>
							<td style="padding-bottom: 0px; width: 35%">${ tempHouse.owner.address.getStreet() } ${ tempHouse.owner.address.getHouseNo() }</td>
						</tr>
						
						<tr>
							<td style="padding-bottom: 0px; width: 20%"></td>
							<td style="padding-bottom: 0px; width: 25%"></td>

							<td style="padding-bottom: 0px; width: 20%"></td>
							<td style="padding-bottom: 0px; width: 35%">${ tempHouse.owner.address.getPostalCode() } ${ tempHouse.owner.address.getCity() }</td>
						</tr>
						
					</table>
				</div>

				<div id="links">
					<!-- display update link -->
					<a href="${updateLink}">Details bearbeiten</a> <br>
					<!-- display add units link -->
					<a href="${showUnitsLink}">Alle Wohneinheiten anzeigen</a> <br>
					<!-- display add counters link -->
					<a href="${addCountersLink}">Alle Z?hler anzeigen</a> <br>
					<!-- display delete link -->
					<a href="${deleteLink}">Haus l?schen</a>
				</div>



			</c:forEach>

		</div>
		<nav>
			<c:url var="toMainPage" value="/">
			</c:url>
			<c:url var="listHouses" value="/house/listHouses">
			</c:url>


			<c:set var="MyHtml"
				value="<a href='${toMainPage}'>Zur?ck zur Startseite</a>" />
			<c:if test="${ noOfHouses == 1 }">
				<c:set var="MyHtml"
					value="<a href='${listHouses}'>Zur?ck zu allen H?usern</a>" />
			</c:if>
			${ MyHtml } <br> <br>

		</nav>
</body>

</html>
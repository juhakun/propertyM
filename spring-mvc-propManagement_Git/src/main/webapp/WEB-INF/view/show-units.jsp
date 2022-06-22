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
					<th>Objekt</th>
					<th>Wohneinheit</th>
					<th>Mieter</th>
					<th></th>
				</tr>

				<c:forEach var="tempUnit" items="${ units }">
					<!-- create an update link -->
					<c:url var="updateLink" value="/unit/showFormForUpdate">
						<c:param name="idUnit" value="${ tempUnit.getIdUnit() }" />
					</c:url>
					<!-- create an delete link -->
					<c:url var="deleteLink" value="/unit/deleteObject">
						<c:param name="idUnit" value="${ tempUnit.getIdUnit() }" />
					</c:url>
					<!-- create an add units link -->

					<tr>
						<td>${ tempUnit.house.objectName }</td>
						<td>${ tempUnit.unitName }<br>${ tempUnit.sizeM2 } m2 <br>${ tempUnit.noOfRooms }
							Zimmer <br>${ tempUnit.floor }
						</td>

						<td>${ tempUnit.renter.firstName }${ tempUnit.renter.lastName }
							<br>${ tempUnit.renter.rent } EUR <br>${ tempUnit.renter.monthlyNkInAdvance  }
							EUR <br>${ tempUnit.renter.noOfPeople } Personen <br>Einzug
							am ${ tempUnit.renter.sqlDateMoveIn  } <br>Auszug am ${ tempUnit.renter.sqlDateMoveOut  }

						</td>

						<td>
							<!-- display update link --> <a href="${updateLink}">Bearbeiten</a>

							<!-- display delete link --> <br> <a href="${deleteLink}">Löschen</a>

						</td>
					</tr>
				</c:forEach>

			</table>


			<c:url var="addUnitLink" value="/unit/showForm">
				<c:param name="idUnit" value="${ tempUnit.getIdUnit() }" />
			</c:url>
			<c:set var="MyHtml" value="Es wurden alle verfügbaren Wohneinheiten angelegt."/>
			<c:if test="${ unitsUsed < noOfUnits }">
			<c:set var="MyHtml" value="<a href='${addUnitLink}'>Wohneinheit hinzufügen</a>"/>
			</c:if>
			${ MyHtml }

		</div>
	</div>

</body>

</html>
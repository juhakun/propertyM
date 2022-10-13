<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<html>


<head>
<title>List houses</title>

<!-- reference our css sheet -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />

<script>
	function sortTable(n) {

		var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
		table = document.getElementById("Counters");
		switching = true;
		// Set the sorting direction to ascending:
		dir = "asc";
		/* Make a loop that will continue until
		no switching has been done: */
		while (switching) {
			// Start by saying: no switching is done:
			switching = false;
			rows = table.rows;
			/* Loop through all table rows (except the
			first, which contains table headers): */
			for (i = 1; i < (rows.length - 1); i++) {
				// Start by saying there should be no switching:
				shouldSwitch = false;
				/* Get the two elements you want to compare,
				one from current row and one from the next: */
				x = rows[i].getElementsByTagName("TD")[n];
				y = rows[i + 1].getElementsByTagName("TD")[n];
				/* Check if the two rows should switch place,
				based on the direction, asc or desc: */
				if (dir == "asc") {
					if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
						// If so, mark as a switch and break the loop:
						shouldSwitch = true;
						break;
					}
				} else if (dir == "desc") {
					if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
						// If so, mark as a switch and break the loop:
						shouldSwitch = true;
						break;
					}
				}
			}
			if (shouldSwitch) {
				/* If a switch has been marked, make the switch
				and mark that a switch has been done: */
				rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
				switching = true;
				// Each time a switch is done, increase this count by 1:
				switchcount++;
			} else {
				/* If no switching has been done AND the direction is "asc",
				set the direction to "desc" and run the while loop again. */
				if (switchcount == 0 && dir == "asc") {
					dir = "desc";
					switching = true;
				}
			}
		}
	}
</script>

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
				<a href="${toHouses}" style="color: white">Meine Häuser</a>
			</div>
			<div id="button">
				<c:url var="toCalc" value="/">
				</c:url>
				<a href="${toCalc}" style="color: white">Jahresabrechnung</a>
			</div>
			<br> <br>
		</div>

		<div id="container"
			style="min-height: calc(${ noOfCounters } * 100px + 250px)">

			<c:url var="showHouseCountersLink"
				value="/counter/showCounters/${ house.idHouse }">
				<c:param name="idHouse" value="${ house.idHouse }" />
			</c:url>
			<c:url var="addCountersLink" value="/counter/showForm">
				<c:param name="idHouse" value="${ house.idHouse }" />
			</c:url>	
			<c:url var="sortCounters" value="/house/listHouses">
			</c:url>
			
			<c:set var="MyHtml"
				value="<h3>Alle Zähler der Wohneinheit '${ unit.unitName }'</h3> 
				<a href='${addCountersLink}'>Zähler hinzufügen</a><br><br>
				<a href='${showHouseCountersLink}'>Alle Zähler der Immobilie '${ house.objectName.toUpperCase() }' anzeigen</a>" />
			<c:if test="${ unit == null }">
				<c:set var="MyHtml"
					value="<h3>Alle Zähler der Immobilie '${ house.objectName.toUpperCase() }'</h3>
					<a href='${addCountersLink}'>Zähler hinzufügen</a>" />
			</c:if>
			${ MyHtml } 
			<br> <br>

			<c:if test="${ noOfCounters > 0 }">
				<div id="objects" style="width: 850px; padding-right: 20px;">
					<table id="Counters">
					
						<tr style="border-bottom: 1px solid #084B8A; cursor: pointer; ">
							<td style="padding-top: 10px; padding-bottom: 10px" onclick="sortTable(0)"><b>Nummer </b><span
								style="font-size: 6pt; ">&#9660;&#9650;</span></td>
							<td onclick="sortTable(1)"><b>Zählerart </b><span
								style="font-size: 6pt">&#9660;&#9650;</span></td>
							<td onclick="sortTable(2)"><b>Wohneinheit </b><span
								style="font-size: 6pt">&#9660;&#9650;</span></td>
							<td onclick="sortTable(3)"><b>Verbrauchsart </b><span
								style="font-size: 6pt">&#9660;&#9650;</span></td>
							<td onclick="sortTable(4)"><b>Standort </b><span
								style="font-size: 6pt">&#9660;&#9650;</span></td>
							<td></td>
						</tr>

						<c:forEach var="tempCounter" items="${ counters }">
							<!-- create an update link -->
							<c:url var="updateLink" value="/counter/showFormForUpdate">
								<c:param name="idUnit" value="${ tempUnit.getIdUnit() }" />
							</c:url>
							<!-- create an delete link -->
							<c:url var="deleteLink" value="/counter/deleteObject">
								<c:param name="idUnit" value="${ tempUnit.getIdUnit() }" />
							</c:url>
							<!-- create an add units link -->
							<!-- <div id="objects"> -->

							<tr style="border-top: 1px solid #084B8A">

								<td style="padding: 10px; ">${ tempCounter.counterNo }</td>
								<td style="padding: 10px; ">${ tempCounter.houseOrUnit }</td>
								<td style="padding: 10px; ">${ tempCounter.unitName }</td>
								<td style="padding: 10px; ">${ tempCounter.type }</td>
								<td style="padding: 10px; ">${ tempCounter.location }</td>
								<td style="padding: 10px; ">
									<!-- display update link --> <a href="${updateLink}">Details
										bearbeiten</a> <br> <!-- display delete link --> <a
									href="${countLink}">Zählerstände bearbeiten</a> <br> <!-- display delete link -->
									<a href="${deleteLink}">Löschen</a>
							</tr>

						</c:forEach>
					</table>

				</div>
			</c:if>
		</div>
		<nav>

			<c:url var="showHouse" value="/house/showHouse/${ house.idHouse }">
			</c:url>
			<a href="${showHouse}">Zurück zum Haus</a>
		</nav>
	</div>



</body>

</html>
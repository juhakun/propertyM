<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Costumer Confirmation</title>
</head>

<body>
	Objektname(*): ${house.objectName} 
	<br><br>
	Straﬂe: ${house.address.street} 
	<br><br>
	Hausnummer: ${house.address.houseNo} 
	<br><br>
	Postal code: ${house.address.postalCode} 
	<br><br>
	Stadt: ${house.address.city} 
	<br><br>
	Gesamtfl‰che: ${house.totalAreaM2} 
	<br><br>
	Anzahl Wohneinheiten: ${house.noOfUnits} 
	<br><br>
	
</body>

</html>
<%@taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Costumer Confirmation</title>
</head>

<body>
	Objektname(*): ${house.objectName} 
	<br><br>
	Straße: ${house.address.street} 
	<br><br>
	Hausnummer: ${house.address.houseNo} 
	<br><br>
	Postal code: ${house.address.postalCode} 
	<br><br>
	Stadt: ${house.address.city} 
	<br><br>
	Gesamtfläche: ${house.totalAreaM2} 
	<br><br>
	Anzahl Wohneinheiten: ${house.noOfUnits} 
	<br><br>
	Eigentümer:
	<br><br>
	Anrede: ${house.owner.formOfAddress} 
	<br><br>
	Vorname: ${house.owner.firstName} 
	<br><br>
	Nachname: ${house.owner.lastName} 
	<br><br>
	Telefon: ${house.owner.telephone} 
	<br><br>
	Mobil: ${house.owner.mobile} 
	<br><br>
	E-Mail: ${house.owner.eMail} 
	<br><br>
	Adresse: 
	<br><br>Straße: ${house.owner.ownerAddress.street} 
	<br><br>
	Hausnummer: ${house.owner.ownerAddress.houseNo} 
	<br><br>
	Postal code: ${house.owner.ownerAddress.postalCode} 
	<br><br>
	Stadt: ${house.owner.ownerAddress.city} 
	<br><br>
	
</body>

</html>
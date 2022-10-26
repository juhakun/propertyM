# PropertyM – WebApp for property management of smaller real estate objects

The WebApp is under development and is intended for the administration and annual cost accounting of smaller real estate objects by private owners.
 

## Description
 
The web app is meant to be used by private owners of a small number of small properties or houses.
All relevant data on the properties to be managed can be registered in the web app. These include general property information such as address, owner, size of the property, residential units, tenants, counters and counter readings as well as yearly costs. All data is saved in a MySQL database. 
The web app can then also be used to create annual invoices for extra costs for the tenants.


## Tools used

* Java
* Spring, Spring MVC 
* MySQL 
* Hibernate
* JavaScript 
* HTML
* CSS
* Version control: Git
* Development environment: Eclipse, MySQLWorkbench, Dia (Freeware UML)


## Current development status 
After setting up a Spring project and a MySQL database the following were already created and are working:

### Frontend

* Basic navigation structure for the web app 
  * for the main page
  * for the overview page showing all saved houses (in the database)
  * for the overview page showing all saved units of a house
  * for the overview page showing all saved counters of a house/unit
* Different input forms 
  * for the creation of a new property/house
  * for the creation of a new unit of an existing house including the tenants 
  * for the creation of new counters of a house/unit


### Backend

* Saving newly entered houses, address, tenants and counter data from the input forms in a MySQL database
* Getting data from the database and showing all saved data on overview pages or in changing forms 


## Improvements, additions and updates
The following functions will be refined:
### Frontend
* Web design 
* Detailed navigation in the web app (back and forth etc.)
* Input forms for counter reading and additional costs

### Backend
* Saving changes and deleting the saved data 
* Saving counter reading data
* Calculation and creation of annual invoices for extra costs
* Management of duplicates on the database 
* Output of annual invoices for extra costs as pdf files

## Screenshots diagrams
### Database
<img src="/MySQL_PropertyManagement.png" width="800">

### UML
<img src="/Diagramm_PM.png" width="800">


## Authors

Julia Häusler-Kun, mail@muulti.de


## License

This project is licensed - see the LICENSE.md file for details


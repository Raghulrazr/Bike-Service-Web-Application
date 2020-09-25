# Bike-Service-Web-Application
This application is for owners of Bike service stations. It helps the owners to list all the services they offer. Customers can choose one or more services to book

## Table of contents
* [General info](#general-info)
* [Code Details](#code-details)
* [Screenshot](#screenshot)
* [Database](#database)
* [Contact](#contact)

## General info
John owns a service station. He provides the following services:
- General service check-up
- Oil change
- Water wash
John’s customers can register for an account with their email address and mobile number.
They can choose a service. Book the service at a particular date.
Once the customer booked a service, John receives an email notification with details about the
service requested by the customer.
Once the service is completed, john will mark the specific booking (of a customer) as “ ready for
delivery”
The customer will receive an email saying that his bike is ready for delivery.
Once delivered, John will mark the booking as “completed”
Specifications:
Bike station owner:
- Should be able to create / edit / delete all his services and their details
- View a list of all bookings ( pending, ready for delivery and completed)
- View details of each booking
- Mark a booking as ready for delivery
- Mark a booking as completed
- Receive an email whenever a booking is made
Customers
- Should be able to register for an account with his email address and mobile number
- Book a service at a particular date
- See the status of his booking
- See all his previous bookings
- Receive an email as soon as his booking is ready for delivery

## HTML Code Details

/Bike service web/WebContent/home.html - Home page of the project includes title, Logo, content. where by clicking the logo we can navigate to home page.

/Bike service web/WebContent/customer_login.html - Enter you e-mail and password of customer to login of customer. send data to servlet to check for validity.

/Bike service web/WebContent/cerror.html - Print error message if e-mail and password is invalid. And ask for login again.

/Bike service web/WebContent/owner_login.html - Enter you e-mail and password of customer to login to login of center owner. send data to servlet to check for validity.

/Bike service web/WebContent/oerror.html - Print error message if e-mail and password is invalid. And ask for login again.

/Bike service web/WebContent/customer_registration.html  - registration form which get customers details and their bikes details.

/Bike service web/WebContent/owner_registration.html - registration form which get owner details and their center details.

/Bike service web/WebContent/about.html - description about the service of Bike-Service-Web-Application.

## JSP Code Details

/Bike service web/WebContent/customerProfile.jsp - Display the details about the customer who login with e-mail and password. They can edit their data by clicking edit button.
They can view their currently booked center details and can know whether the bike is ready for deliver or not status. They can also see their previous booking list. 
And they can see the service centers details list. Where they can select a date and click the book button to book a service on a day in certain center. 

/Bike service web/WebContent/ownerProfile.jsp - Display the details about the owner who login with e-mail and password. They can edit their data by clicking edit button.
They can view their booked list. and also can chage the status of booking. And they can see the service of centers and also able to delete service and add service.

## Servlet Code Details

/Bike service web/src/packageBike/Servlet.java - We check for login details from database. If valid then allow to profile page, if not then redirect to error login page. And also save the details of customer and owner in the database tables.

/Bike service web/src/packageBike/UpdateCustomerServlet.java - Displaying the details of customer for edit purpose. If data has edited, it will update in the database management system.

/Bike service web/src/packageBike/UpdateServlet.java - Displaying the details of owner for edit purpose. If data has edited, it will update in the database management system.

/Bike service web/src/packageBike/UpdateServiceServlet.java - It will delete the service provided by the owner and delete in from database.

/Bike service web/src/packageBike/PreviousServlet.java - provide the information about previous booking of customer. That is completed service list.
 
/Bike service web/src/packageBike/CurrentServlet.java - It provides information about currently booked details and its status.

/Bike service web/src/packageBike/AddServlet.java - Retrive service data from table. Display the service provided by the center. And also update that in database. Here the mail is sent to the owner about the booking of service. 

/Bike service web/src/packageBike/BookingListServlet.java - Lists the service that are booked to the current center and also its status. Owner can change the status, where it send the mail to user about the delivery of bike.

/Bike service web/src/packageBike/EmailSendingServlet.java - Get the data about email name, password, host, port number, recipient, subject, content of mail and send the details to EmailUtility.

/Bike service web/src/packageBike/EmailUtility.java - It sets SMTP server properties and creates a new session with an authenticator. Finally send the email.

/Bike service web/WebContent/WEB-INF/web.xml - to provide host, port number, sender email-id, password.

## Image in page

/Bike service web/WebContent/bike-service-logo.jpg

/Bike service web/WebContent/bike10.jpeg

/Bike service web/WebContent/bike4.jpg

/Bike service web/WebContent/BIKE6.jpg

/Bike service web/WebContent/bike7.jpg

/Bike service web/WebContent/bike8.jpg

/Bike service web/WebContent/bike9.jpg

/Bike service web/WebContent/slogan.jpg

## Screenshots
StudentClassroom/screen.pdf

## Database

Here MySQL Server version: 8.0.20 is used. Because the use of the MySQL database server that utilize low-cost commodity hardware, can achieve amazing levels of scalability and performance, all at a cost that is far less. MySQL offers exceptional quick-start capability with the average time from software download to installation completion being less than fifteen minutes. open source nature of MySQL allows complete customization to add unique requirements to the database server.

## Contact
Mail To - raghulsenthil6@gmail.com

Project Link: [https://github.com/Raghulrazr/StudentClassroom.git](https://github.com/Raghulrazr/StudentClassroom.git)

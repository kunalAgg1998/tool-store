Tool Store Application

Description:
The Tool Store Application is a software system designed to handle tool rentals. It provides functionality for checking out tools, calculating rental charges, applying discounts, and generating rental agreements.

Features:
Check out tools for a specified number of days Calculate rental charges based on various factors such as tool type, rental duration, and discounts Apply discounts to rental charges Generate rental agreements with detailed information about the rental transaction

     Technologies Used
 
Java: The core programming language used for the backend logic.

Spring Boot: A powerful framework used to build and deploy Java-based applications easily.

Maven: Dependency management tool used to handle project dependencies and build the application.

JUnit: Testing framework used for unit testing the application components.

Jackson: Library used for JSON serialization and deserialization in the application.

Tomcat: Servlet container used for deploying the Spring Boot application.

Git: Version control system used for collaborative development and managing source code.

Getting Started:

Clone the repository to your local machine.

Make sure you have Java and Maven installed.

Build the project using Maven: mvn clean install.

Run the application: java -jar toolstore.jar.

Checkout Request:

Endpoint: /checkout
Method: POST

Request Body:

{
 "toolCode": "LADW",
 "rentalDays": 10,
 "discountPercent": 20,
 "checkoutDate": "07/07/24"
 }

 Checkout Response
 Response Body:

   {
    "toolCode": "LADW",
    "toolType": "LADDER",
    "toolBrand": "Werner",
    "rentalDays": 10,
    "checkoutDate": "07/07/24",
    "dueDate": "07/17/24",
    "dailyRentalCharge": "1.99",
    "chargeDays": 8,
    "preDiscountCharge": "15.92",
    "discountPercent": 20,
    "discountAmount": "3.184",
    "finalCharge": "12.736"
  }
Additional Notes:

The toolCode represents the unique code assigned to each tool.

The toolType represents the type of tool being rented.

The toolBrand represents the brand of the tool.

The rentalDays indicate the number of days the tool will be rented for.

The checkoutDate represents the date when the tool is checked out.

The dueDate represents the date when the tool should be returned.

The dailyRentalCharge represents the daily charge for renting the tool.

The chargeDays indicate the number of days for which the tool is charged.

The preDiscountCharge represents the total charge before any discounts are applied.

The discountPercent represents the percentage discount applied.

The discountAmount represents the amount discounted from the total charge.

The finalCharge represents the final charge after applying discounts.

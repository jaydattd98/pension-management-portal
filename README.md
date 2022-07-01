# pension management system

pension management system


Pension Management System

Developed by:

Name :- Dhorsinge Jaydatt


--------------------------------------------------------------------------------------------------

About Project :-

1.Used Angualr As presentation layer
2.Created separate Microservice for Authorization and Authentication purpose
3.Every request in Process pension and Pensioner details Microservice will Authenticate by Jwt Authorization Microservice.
4.Process pension gave call to pentioner deatails service and get result.
5.Used globle exception handling to avoid multiple use of try catch for some common exceptions
5.Used Interceptor to add token in request in Angualr
6.SignUp, login function are added


Glad to work on this project, due to time containt and current project delivarable unable to expolre more on it.


--------------------------------------------------------------------------------------------------
Web Portal Access point: http://localhost:4200/ Local Angular

Database Access points:
Jwt Application Tables : http://localhost:8080/h2-console/
Process pension tables : http://localhost:9595/h2-console/
pensioner details tables : http://localhost:8585/h2-console/


Local accces point for Backent
Authorization Microservice: http://localhost:8080/
ProcessPension Microservice: http://localhost:9595/
Pensioner Detail Microservice: http://localhost:8585/

--------------------------------------------------------------------------------------------------

Example for valid details:

Login Credentials:

dummy data already added

Username : jaydatt
Password : jaydatt@123


Process Pension Form:

Aadhaar Number : 123456789   Have to provide as Inpute


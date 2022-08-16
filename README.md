# Item rent and management application
## About
Project was created for science club [CREATIVE AGH](http://creative.agh.edu.pl/) to resolve real live problems.
We are motivated to gain new skills, and we are going to improve ourselves as software developers .
### Current development stage 
We have created currently server site application with connection to database and Minio local storage .

[//]: # (DO edycji)
Current project authors are :
- [Adam Wasylewicz](https://github.com/adwas3213)
- [Patryk Janas](https://github.com/PatrykJanas27)


---
## Main goal
Our main goal is to create modern and advanced application which can be used easily to 
manage items and see where it is at interactive map in every kind of area .

We will solve the following problems : 
- Problem with share of item when it is not only personal one but 
many people can own it and rent from system.
- Problem with interpersonal communication where somebody left common item
- Problem with usurpation somebody whose item it is .
- Problem with control about item flow in e.g. laboratory
- Generate reports about item state at periodicity of time 
---
## Current technology that we are using :
- Java 17
- Spring 2.7
- MySQL server 
- H2 Database
- swagger and swagger-ui
- Spring Data JPA 
- MapStruct 
- Minio
- MySql 
- Spring Security 
- Lombok
- Java Mail Api 
- ThyMeLeaf
- Docker
---
## Version - 0.5 alpha
>At present time we have main functionalities with item (including history and sending images to localstorage) categories and places . 

### Features

- Create unique place 
- Create item with unique id 
- Assign items to categories and create new categories 
- Receive and transfer images to localstorage
- 2 dedicated profiles developer and production 
- Tracking history of item 
- Add test and start data to DB 
- Borrow and return item by moderator to some user 
- Swagger UI for development 
- Save application logs in dedicated file 
- Adding item status

  [//]: # (DO usunecia)
Temporary feature : 
- change file or image to base 64 encoding
- send image encoded in base 64 endpoint 
- send multiple images in base 64

---

### Future stage of development 
>
> 1. Create registration functionality with e-mail veryfication 
> 2. Create security filter-chain with login functionality and JWT authentication 
> 3. Refactor functionality based on authority access (Admin,Moderator,User) roles 
> 4. Create remind password endpoint 
> 5. Adjust Swagger-ui to spring security 
> 6. Adjust data model to frontend demands 
> 7. Create admin panel 
> 8. Create docker compose to pack app with frontend , minio and database 
> 9. At the end create Data base migration system with liquibase 
> 10. Generate excel/PDF report of item history or present state 

---
## Setup and run tutorial 
To run backend of our application you will need the following things : 
- Java 17 
- Maven 
- [Minio Local storage](https://docs.min.io/docs/deploy-minio-on-docker-compose.html) 
- [MYSQL Server](https://www.mysql.com/)
- Set up the following configs : application.properties , application-prod.properties
with correct credentials to database and MinIo local storage . 

In the future, we will build whole app with docker and docker-compose but at alfa version we do not provided it .
By default, application will stand at port :8080 and minio will take ports :9000 :9001, so 
I would recommend to configure our or foreign  applications for resolve conflicts 

### Build and run 
- Download app from GitHub repository

``git clone https://github.com/Creative-AGH/rental_server.git``
- Go inside folder 

``cd <folderName>``
- Setup necessary credentials and properties in application.properties and application-prod.properties / application-dev.properties

- Check out to branch master

``git checkout master``

- Build docker image inside folder


``docker build . -t rental_server``

- Run docker container with correct params 

``docker run -p 8080:8080 -d --name testName  rental_server``

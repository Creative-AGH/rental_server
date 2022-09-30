# Item rent and management application
## About
The project was created for science club - [CREATIVE AGH] (http://creative.agh.edu.pl/) to resolve real live problems.
We are strongly motivated to gain new skills, and we are going to improve ourselves as software developers.
### Current development stage 
Currently, we have created a server site application with connection to database and Minio local storage.

Current project authors are:
1) Server Side:
- [Adam Wasylewicz] (https://github.com/adwas3213)
- [Patryk Janas] (https://github.com/PatrykJanas27)
2) Client Side:
- [Przemysław Matraj] (https://github.com/przemyslawmatraj)
- [Adam Jasiński] (https://github.com/jasinskiadam)
3) DevOps:
- [Anna Gołda] (https://github.com/annagolda)

---
## Main goal
Our main goal is to create modern and advanced application which can be used easily to 
manage items, for example in the office, room etc., and see where this specific item is or get the localization of this item at interactive map in every kind of area.

We will solve the following problems: 
- Problem with sharing of the item, when it is not only personal one, but many people would like to own it and rent from the system
- Problem with interpersonal communication, question where somebody left common item
- Problem with usurpation, whose item it is
- Problem with control about item flow in e.g. laboratory
- Generate reports about item state for periodicity of time 
- Lack of management items in different places
---
## Current technology that we are using:
- Java 17
- Spring 2.7
- MySQL server 
- H2 Database
- Swagger and Swagger-UI
- Spring Data JPA 
- MapStruct 
- Minio
- MySQL
- Spring Security 
- Lombok
- Java Mail Api 
- ThyMeLeaf
- Docker
- Apache POI
---
## Version - 0.8 Beta
>At present time we have done almost all features for application. For further development, we left non-obligatory features.
>Next stage of development is to connect backend with frontend. Create developmental and production environment and pack it to docker-compose.
>We will decide if pushing docker image of backend is necessary and do it for beta version of backend to improve portable of our app.

### Features

- Create unique place 
- Create item with unique id 
- Assign items to categories and create new categories 
- Receive and transfer images to Minio Cloud Storage
- 2 dedicated profiles, developmental and production 
- Tracking history of item 
- Add test data and add it to database 
- Borrow and return item by moderator to some user 
- Swagger UI for development 
- Save application logs in dedicated file 
- Setting item status
- Generating Excel report about all items at storage
- Generating Excel report for single item history records 
- Register with email validation
- Password Encryption 
- Swagger UI adapted to Spring Security JWT authentication 
- Login with JWT token
- Expiring JWT tokens
- Inserting to Cloud Storage multiple maps as like building plans or area shapes 

Temporary feature:
- Change file or image to base 64 encoding
- Send image encoded in base 64 endpoint 
- Send multiple images in base 64

---

### Future stage of development 
>
> 1. Create registration functionality with e-mail verification `DONE`
> 2. Create security filter-chain with login functionality and JWT authentication `DONE`
> 3. Refactor functionality based on authority access (Admin,Moderator,User) roles `DONE`
> 4. Create remind password endpoint `SHIFTED TO VERSION 1.0 RELASE`
> 5. Adjust Swagger-UI to spring security `DONE`
> 6. Adjust data model to frontend demands `DONE`
> 7. Create admin panel `DONE`
> 8. Create docker compose to pack app with frontend , minio and database `IN PROGRES`
> 9. At the end create Data base migration system with liquibase `SHIFTED TO VERSION 1.0 RELASE`
> 10. Generate excel/PDF report of item history or present state `DONE`
> 11. Update docs and version of project `DONE`

---
## Setup and run tutorial 
To run the backend of our application, you will need the following things: 
- Java 17 
- Maven 
- [Minio Local storage](https://docs.min.io/docs/deploy-minio-on-docker-compose.html) 
- [MYSQL Server](https://www.mysql.com/)
- Set up the following configs: application.properties, application-prod.properties
with correct credentials to database and Minio local storage. 

In the future, we will build whole app with docker and docker-compose, but at ALFA version we do not provided it.
By default, application will stand at port :8080 and minio will take ports :9000 :9001, so 
I would recommend configuring our or foreign  applications to resolve conflicts

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

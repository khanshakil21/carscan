# User Management
#### User Management is a Spring Boot application built using Maven.

### Lombok setup
- Download lombok.jar file from here: https://projectlombok.org/download.
- Open terminal and change directory to the path where the downloaded file is located.
- In the terminal, run this command: java -jar lombok.jar
- Choose the location of sts & click on install to complete the setup.

### Database configuration
- User Management is pointing to oracle database. However you can choose any relation database of your choice.
- Create a user in the database(oracle)
   - create user app_user identified by cARSCAN#1;
   - grant create session to app_user;
   - grant all privileges to app_user;

### Running Examples

- Download the zip or clone the Git repository.
- Unzip the zip file (if you downloaded one)
- Open STS
   - File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
   - Select the right project
   - Change database property in application.yml (if required)
- Choose the Spring Boot Application file (search for @SpringBootApplication)
- Right Click on the file and Run as Java Application
- You are all Set
- Swagger can be access by visiting http://localhost:8081/swagger-ui.html in your browser
- Also Postman collection can be access from https://www.getpostman.com/collections/c5e9f69861caff8fa074
  
### Support
- Email me khanshakil21@gmail.com
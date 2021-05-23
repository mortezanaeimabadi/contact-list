# Contact List
Simple "contact list" application that allows:

Listing people (name and photo)
Searching by name
Paging
Initial list is one-time populated with people.csv. Contact addition/removal/edit is out of scope.

# Technology Stack

- [Java](https://www.java.com) ( version 1.11 )
- [Spring Boot](https://spring.io/projects/spring-boot) ( version 2.4.5 )


## Requirements 
apache maven <br/>****


## Build & Install
Run the following maven command:

mvn install

## RUN
Make sure JDK 11 is installed.

Then navigate to /target directory and run:
 
java -jar contact-list-0.0.1-SNAPSHOT.jar

In order to view the result visit: 
http://localhost:9001/v1/person/view



## Database
The h2 file is located in ./data/db<br/>
H2 console is available in http://localhost:9001/h2-console<br/>
Username: sa

Password: pass








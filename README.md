# Payconiq-stock-api
Stock api assignment
Default user pass is `admin/admin` as in `application.yml`
SprintToolSuite is used for implementation


## Config
Security can be disabled by setting management.security.enabled to "false" in application.yml file
```properties
-Dspring.config.location=file:///./application.yml
```

## DB
mySQL DB is used and it is configured in applicaton.properties

## Build
```bash
mvn clean package
```

##Run using spring boot plugin 
```bash
mvn spring-boot:run -Dspring.config.location=file:///./application.yml
```

## Code coverage
```bash
mvn clean package site
```

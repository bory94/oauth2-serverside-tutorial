## Initial module setups

### Creating Tables and Insert Sample data for Authorization Client Details 
Run SQL DDL scripts below for Authorization Client Details. 
**client_credentials** grant type will use this table dynamically.
```
create table oauth_client_details (
    client_id VARCHAR(256) PRIMARY KEY,
    resource_ids VARCHAR(256),
    client_secret VARCHAR(256),
    scope VARCHAR(256),
    authorized_grant_types VARCHAR(256),
    web_server_redirect_uri VARCHAR(256),
    authorities VARCHAR(256),
    access_token_validity INTEGER,
    refresh_token_validity INTEGER,
    additional_information VARCHAR(4096),
    autoapprove VARCHAR(256)
) ENGINE=innodb DEFAULT CHARSET=utf8mb4;;
```
Now, insert two **SAMPLE** Clients Details.
```
## password = Bcrypted "first-secret"
INSERT INTO oauth_client_details
    (client_id, client_secret, scope, authorized_grant_types,
    web_server_redirect_uri, authorities, access_token_validity,
    refresh_token_validity, additional_information, autoapprove)
VALUES
    ("first-service", "$2a$10$dO5lrs5puL/MB.BEAlNUO.jImcLJ8uI.H/8f5qzBl7uYDZT1GIq6i", 
     "USER,ADMIN",
     "authorization_code,refresh_token,client_credentials", 
     null, null, 36000, 36000, null, true);

## password = Bcrypted "second-secret"
INSERT INTO oauth_client_details
    (client_id, client_secret, scope, authorized_grant_types,
    web_server_redirect_uri, authorities, access_token_validity,
    refresh_token_validity, additional_information, autoapprove)
VALUES
    ("second-service", "$2a$10$YmDSjl/pWnI7weC9163dgu277AfVQ9rvFgc/fYBAQBTrGKpmc2y5W", 
     "USER",
     "authorization_code,refresh_token,client_credentials", 
     null, null, 36000, 36000, null, true);
```

### Creating Tables and insert data for password grant type
Run SQL DDL scripts below for User Details. 
**password** grant type will use this table dynamically.
```
CREATE TABLE `user` (
  `seq` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `enabled` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=innodb DEFAULT CHARSET=utf8mb4;

CREATE TABLE `authority` (
  `seq` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `authority` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=innodb DEFAULT CHARSET=utf8mb4;
```
Now, insert two **SAMPLE** user details.
```
## password=user!
INSERT INTO user (enabled, password, username) VALUES (1, '$2a$10$CzqIu0Vo8J3O/qtQJ9p8CehpvNz5RcGg8yMZBTa7oVyksnwoENXoW', 'user');
INSERT INTO authority (authority, username) VALUES ('ROLE_USER', 'user');

## password=admin!
INSERT INTO user (enabled, password, username) VALUES (1, '$2a$10$bUQd6czsSeP2OozH54u1JePRYvYvUdIDu0McqbCYeeHCvrSmpSbU6', 'admin');
INSERT INTO authority (authority, username) VALUES ('ROLE_USER', 'admin');
INSERT INTO authority (authority, username) VALUES ('ROLE_ADMIN', 'admin');
```

### Creating Tables and insert data for Authorizing Http Request
Run SQL DDL scripts below for authorizing Http Request.
[AuthorizationChecker](src/main/kotlin/com/bory/tutorial/oauth2/authorizationserver/config/AuthorizationChecker.kt) uses this table for authorization of Http Request.
```
CREATE TABLE `authorized_request` (
  seq int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  http_method varchar(10) DEFAULT NULL,
  uri_pattern varchar(255) DEFAULT NULL,
  authorities varchar(1000) DEFAULT NULL,
  sort int(5) NOT NULL DEFAULT 1
) ENGINE=innodb DEFAULT CHARSET=utf8mb4;
```
Now, insert four **SAMPLE** Http Request authorization data.
```
insert into authorized_request 
(http_method, uri_pattern, authorities, sort) values ('GET', '/v*/auth/**', 'authenticated', 1);
insert into authorized_request 
(http_method, uri_pattern, authorities, sort) values ('POST', '/v*/auth/**', 'ROLE_ADMIN', 1);
insert into authorized_request 
(http_method, uri_pattern, authorities, sort) values ('PUT', '/v*/auth/**', 'ROLE_ADMIN', 1);
insert into authorized_request 
(http_method, uri_pattern, authorities, sort) values ('DELETE', '/v*/auth/**', 'ROLE_ADMIN', 1);
```

> Note that only authorization server uses this created database for now. 
> You can modify resource server and resource client to use it for authentication and authorization.
## Start up Config Server
Before starting up Authorization server, you should start up the Configuration Server if you didn't.
```shell script
nohup ./gradlew :config-server:bootRun &
```

## Start up Authorization Server
You can start up authorization-server using gradle command.
```shell script
nohup ./gradlew :authorization-server:bootRun &
``` 

## Request Authorization Token by client_credentials grant type
After starting up, you can request authorization token by client_credentials grant type.
```shell script
curl first-service:first-secret@localhost:8080/oauth/token -d grant_type=client_credentials -d scope=USER+ADMIN
```
Running this command will returns access token json response like below.
```
{"access_token":"eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Imp3dC1vYXV0aC1rZXktaWQifQ.eyJzY29wZSI6WyJBRE1JTiIsIlVTRVIiXSwiZXhwIjoxNTg4MDY5Nzg4LCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiLCJST0xFX0FETUlOIl0sImp0aSI6IjYwYjRhNjcyLTUyNGQtNDY4OC1hZWZmLWY2YjAxNDViODlhMSIsImNsaWVudF9pZCI6ImZpcnN0LXNlcnZpY2UifQ.BPwDBsfZ_bjJS0DkmH947-82TstStYLGmjweV6x_kZqOmVMDsFMsuImO8CAhoJ5gEvVjiYt8J106UzHkBl4WUqXoIl6Zgus4XHq8MSG9klspq1q2WBcvwvxSpAOC62jSkHW3t_GBGoOsgeIGYGz8RoPYgMo8-vyLMy60Hf-fZB06do92TQBdjjjSeANj2i1LGcUMxxlNfve8Hwa-R4TxBZyeWs8VpYLpsHcDHkrd5ilZyp0suAcyaPivJLEa6uaxtWuo1h6ed4W8fXtm-y1QXcLkZgofYhlra05jaa-WIik7a6psEvXi0i4rbQhTmtsfoX2Eyyf5DmvIeNg-Hxe4cw","token_type":"bearer","expires_in":35999,"scope":"ADMIN USER","jti":"60b4a672-524d-4688-aeff-f6b0145b89a1"}
```

If you've installed jq on your machine, you can use command below to export authorization token as environment variable.
```shell script
export AUTH_TOKEN=$(curl first-service:first-secret@localhost:8080/oauth/token -d grant_type=client_credentials -d scope=USER+ADMIN | jq -r '.access_token')
```

You can use this variable to request some resources of resource server.
(Execute command below after starting up Resource Server)
```shell script
curl -H "Authorization: Bearer $AUTH_TOKEN" http://localhost:9090/v1/auth

## this command will return simple string message below
auth world
```

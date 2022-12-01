## Initial module setups

### Creating Config-Server Table and Insert data for Config-Client Servers' Configuration
Create Database whose name is configserver.
```
create database configserver;
```

Run SQL DDL scripts below for Config Server.
```
create table PROPERTIES (
    `seq` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    APPLICATION varchar(100) not null,
    PROFILE varchar(50) not null,
    LABEL varchar(1000) not null,
    PROPERTY_NAME varchar(1000) not null,
    PROPERTY_VALUE varchar(4000) not null
);
```

Run SQL DML script below for authorization-server configuration.
```
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'master', 'spring.profiles.active', 'local');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'master', 'spring.security.authorizeserver.signingkey', '"signingKey!"');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'master', 'spring.datasource.url' ,'jdbc:mysql://localhost:3306/authserver?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'master', 'spring.datasource.username','root');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'master', 'spring.datasource.password','root');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'master', 'spring.datasource.driver-class-name','org.mariadb.jdbc.Driver');

insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'master', 'spring.jpa.database-platform', 'org.hibernate.dialect.MySQL5Dialect');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'master', 'spring.jpa.show-sql','true');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'master', 'spring.jpa.hibernate.ddl-auto','none');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'master', 'spring.jpa.database','mysql');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'master', 'spring.jpa.properties.hibernate.format_sql','true');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'master', 'spring.jpa.open-in-view','false');

insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'master', 'logging.level.com.bory','DEBUG');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'master', 'logging.level.org.springframework','INFO');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'master', 'logging.level.org.hibernate.type.descriptor.sql','TRACE');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'master', 'logging.level.root','INFO');

```
Run SQL DML script below for resource-server configuration.
```
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('resource-server', 'default', 'master', 'server.port','9090');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('resource-server', 'default', 'master', 'spring.security.oauth2.resourceserver.jwt.jwk-set-uri', 'http://localhost:8081/as/.well-known/jwks.json');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('resource-server', 'default', 'master', 'logging.level.root','INFO');
```

Run SQL DML script below for resource-client configuration.
```
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('resource-client', 'default', 'master', 'server.port', '7070');

insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('resource-client', 'default', 'master', 'spring.security.oauth2.client.registration.first-service.client-name', 'first-service');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('resource-client', 'default', 'master', 'spring.security.oauth2.client.registration.first-service.client-id', 'first-service');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('resource-client', 'default', 'master', 'spring.security.oauth2.client.registration.first-service.client-secret', 'first-secret');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('resource-client', 'default', 'master', 'spring.security.oauth2.client.registration.first-service.scope', 'USER,ADMIN');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('resource-client', 'default', 'master', 'spring.security.oauth2.client.registration.first-service.authorization-grant-type', 'client_credentials');

insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('resource-client', 'default', 'master', 'spring.security.oauth2.client.provider.first-service.token-uri', 'http://localhost:8081/as/oauth/token');

insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('resource-client', 'default', 'master', 'webclient.resource-server-index-uri', 'http://localhost:8081/rs/');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('resource-client', 'default', 'master', 'webclient.resource-server-auth-uri', 'http://localhost:8081/rs/v1/auth');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('resource-client', 'default', 'master', 'webclient.resource-server-admin-uri','http://localhost:8081/rs/v1/admin');

```
## Start up Config Server
Before starting up Authorization server, you should start up the Configuration Server if you didn't.
```shell script
nohup ./gradlew :config-server:bootRun &
```

References
----------
* https://cloud.spring.io/spring-cloud-config/reference/html
* https://www.devglan.com/spring-cloud/jdbc-backend-spring-cloud-config

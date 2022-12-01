insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'default', 'spring.profiles.active', 'local');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'default', 'spring.security.authorizeserver.signingkey', '"signingKey!"');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'default', 'spring.datasource.url' ,'jdbc:mysql://localhost:3306/authserver?useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'default', 'spring.datasource.username','root');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'default', 'spring.datasource.password','root');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'default', 'spring.datasource.driver-class-name','org.mariadb.jdbc.Driver');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'default', 'spring.jpa.database-platform', 'org.hibernate.dialect.MySQL5Dialect');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'default', 'spring.jpa.show-sql','true');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'default', 'spring.jpa.hibernate.ddl-auto','none');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'default', 'spring.jpa.database','mysql');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'default', 'spring.jpa.properties.hibernate.format_sql','true');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'default', 'spring.jpa.open-in-view','false');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'default', 'logging.level.com.bory','DEBUG');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'default', 'logging.level.org.springframework','INFO');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'default', 'logging.level.org.hibernate.type.descriptor.sql','TRACE');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('authorization-server', 'default', 'default', 'logging.level.root','INFO');


insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('resource-server', 'default', 'master', 'server.port','9090');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('resource-server', 'default', 'master', 'spring.security.oauth2.resourceserver.jwt.jwk-set-uri', 'http://localhost:8081/as/.well-known/jwks.json');
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, `KEY`, `VALUE`) values ('resource-server', 'default', 'master', 'logging.level.root','INFO');


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

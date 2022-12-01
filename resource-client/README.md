## Start up Resource Client
Resource Client is a springboot webflux application. 
So after starting up this server, you can access HTTP 7070 port to check oauth2 token validation works properly.
 
You can start up resource-client using gradle command.
```shell script
nohup ./gradlew :resource-client:bootRun &
``` 

> To work properly, you should run Authorization server and Resource Server first.

Now you can access the urls below, and all of them returns 200 OK contents.
```
http://localhost:7070/api/
http://localhost:7070/api/auth
http://localhost:7070/api/admin
``` 
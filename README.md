# AIDentix Backend app in Spring-Boot and JDBC Template

### Application runs on port 8089

Before hiting the API you need to run the SQL script in Postgres as user 'postgres'

```
test.sql
```

```
http://localhost:8089/
```
Open postman 

Get users
```
GET http://localhost:8089/users
``` 

Get User
```
GET http://localhost:8089/users/:userId
```

Create new User
```
POST http://localhost:8089/users

Request Body
{
    "username" : "Name of User",
    "password" : "some password"
}
```

Upload new User's, you can use the file usernames.csv to upload
```
POST http://localhost:8089/users/upload
```



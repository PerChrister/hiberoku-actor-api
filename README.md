## Hiberoku, the Actor CRUD API

# About

Hiberoku is a CRUD application using actors as data and is built with Hibernate, Spring, and Postgres database. This web app is mainly backend based and can be tested using the Postman application to test different URLs with different endpoints. One might use the endpoints but only to display information and not to add. 

Deployed using heroku at: https://hiberoku-actor-api.herokuapp.com/

# Installation

1. Clone the repo (git clone https://github.com/PerChrister/hiberoku-actor-api)
2. Open the project in your IDE, go to main class and run (using right click button)

# URLs and endpoints for Postman
1. To see all actors (GET Method)
https://hiberoku-actor-api.herokuapp.com/actor/all
2. To see a specific actor (GET Method)
https://hiberoku-actor-api.herokuapp.com/actor/{id}
3. To add a new actor (POST Method)
https://hiberoku-actor-api.herokuapp.com/actor
4. To update a specific actor's information (PUT Method)
https://hiberoku-actor-api.herokuapp.com/actor/{id}
5. To replace specific information from a specific actor (PATCH Method)
https://hiberoku-actor-api.herokuapp.com/actor/{id}
6. To delete a specific actor (by id, DELETE Method)
https://hiberoku-actor-api.herokuapp.com/actor/{id}
7. To view basic logs (GET Method)
https://hiberoku-actor-api.herokuapp.com/log

# Author

https://github.com/PerChrister

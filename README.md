# FlightPlanner

[Flight planner](https://theflightplanner.herokuapp.com/) helps you keep track of your flights. It is a personal pet project to practice some backend skills (mobile developer here!).
 
* **Backend + API**: [Ktor](https://ktor.io/) and [PostGresSQL](https://www.postgresql.org/). 

* **Front-End**: [Bootstrap](https://getbootstrap.com/) and [Free Marker](https://freemarker.apache.org/).

<p align="center">
<img width="900" alt="Flight Planner" src="https://user-images.githubusercontent.com/6362660/67637968-cfd4d600-f8d7-11e9-80e9-e042648f3309.png">
</>

### API
Although the website does not use it, an API has been provided to login and add new flights. It uses [JWT](https://jwt.io/introduction/) for authentication. 

By doing a POST in */login* with your username and password you'll get your token to then do POST in */api/v1/flights* and add new flights into your account.

### Next steps
* Error handling is almost non-existent in the API side.
* No unit-tests
* The front-end isn't great
* Add more endpoints to the API (i.e POST */signup* or DELETE */flights/{userId}/{flightId}*)
* Build a mobile client (Android/iOS app). 

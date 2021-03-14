# THE PANTRY APP

> ##### Do I have carrots at home?

This app has been conceived to allow you to take note of the products you have at home. Just open your pantry, add them and then go shopping with 0 worries. 

### Features

- microservice architecture :heart_eyes: for a full indipendent :muscle: organization of work inside the program, no more bottlenecks in your workload;
- unpenetrable security :policewoman: system to garantize the security of your microservices from outsiders ( :japanese_goblin:). The services are only accesible from the edge service, and they comunicate between each other using authentication tokens.
- great customer service for all your needs ( :massage:)
- this is only the v1.0

## Installation

We have 2 databases, users and pantry. A full list of products will be updated in the upcoming weeks.
An SQL file is provided. Add permissions to the application properties files and run them. :joystick:

You need to run the microservices of the backend with 
```ruby 
mvn spring-boot:run
```
and afterwards execute the angular app to displya the front on localhost:4200 
```ruby
ng s -o
```

## Authentication :dark_sunglasses:

In order to authenticate you will need to log in into the app.

## A front developed with Angular is provided :compass:

Just log in and add a pantry to your user. Once there you can:

  -select and add products
  -modify stock
  -delete products or pantries
  -send us an email

:warning:  The gateway service uses the port: 8081  :warning:

Full rework upcoming with: dark-mode, autocomplete input for products, barcode scanner, shareable pantries...and more! 


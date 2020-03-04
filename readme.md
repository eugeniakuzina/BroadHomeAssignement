## This is my implementation of The Broad Institute home assignment

I've implemented tasks 1 and 2 and skipped task 3 as I didn't have enough time

Main.class contains methods to implement the project functionality.
Assertions in /main package are 'unit tests' and would be executed in the runtime
Tests in /test package are 'functional tests' and would be executed in CI/CD pipeline

## Answer to Question 1
There are two ways to filter results for subway-only routes. Think about the two options below
and choose:
1. https://api-v3.mbta.com/routes
2. https://api-v3.mbta.com/routes?filter[type]=0,1

Both options require only one call to the server
In the first option filtering would have to be implemented on the client side.
In the second option the result filtering is done on the server side.
In case of this project it is 3rd party provider who, we can assume, knows their business logic better and therefore can filter better.

##  Questions to MBTA endpoints:
1. Why does /stops endpoint returns null route data when it is filtered by miltiple route ids?

[https://api-v3.mbta.com/stops?filter[route]=Red&include=route](https://api-v3.mbta.com/stops?filter[route]=Red&include=route)

```json5
{
attributes: {
...
name: "Alewife",
...
},
id: "place-alfcl",
...
route: {
  data: {
    id: "Red",
    type: "route"
  }
},
...
}
```

[https://api-v3.mbta.com/stops?filter[route]=Red,Orange,Blue&include=route](https://api-v3.mbta.com/stops?filter[route]=Red,Orange,Blue&include=route)

```json5
{
attributes: {
...
name: "Alewife",
...
},
id: "place-alfcl",
...
route: {
data: null
},
...
},
```

2. 
[https://api-v3.mbta.com/stops?filter[id]=70061,70063,70065,70067,70069,70071,70073,70075,70077,70079,70081,70083,70085,70087,70089,70091,70093](https://api-v3.mbta.com/stops?filter[id]=70061,70063,70065,70067,70069,70071,70073,70075,70077,70079,70081,70083,70085,70087,70089,70091,70093) 
returns all 17 stops, including 70093

[(https://api-v3.mbta.com/stops?filter[id]=70061,70063,70065,70067,70069,70071,70073,70075,70077,70079,70081,70083,70085,70087,70089,70091,70093?&include=parent_station](https://api-v3.mbta.com/stops?filter[id]=70061,70063,70065,70067,70069,70071,70073,70075,70077,70079,70081,70083,70085,70087,70089,70091,70093?&include=parent_station)
returns 16 stops, every requested one but 70093



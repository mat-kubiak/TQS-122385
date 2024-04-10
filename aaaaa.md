// Use DBML to define your database structure
// Docs: https://dbml.dbdiagram.io/docs


Table city {
  cityId integer [primary key]
	name string
}

Table trip {
  tripId integer [primary key]
  sourceCity integer
  destinationCity integer
  weekday string
  departureTime time
  arrivalTime time
  priceEuro integer
  seatsTotal integer
  seatsAvailable integer
}

Table ticket {
  ticketId integer [primary key]
  trip trip
  date date
  firstName string
  lastName string
}

Ref: trip.sourceCity > city.cityId
Ref: trip.destinationCity > city.cityId
Ref: ticket.trip > trip.tripId


1. GET /api/cities:
Success Response (200 OK):
    Description: Returns a list of cities.
    Response Body Schema:
       [
         {
           "cityId": integer,
           "name": string
         },
         ...
       ]
Error Response (404 Not Found):
    Description: Indicates that no cities are found.
    Response Body Schema:
       
       []
       
1. GET /api/trips/{}{}:
Success Response (200 OK):
    Description: Returns a list of trips based on the provided parameters.
    Response Body Schema:
    [
        {
        "tripId": integer,
        "sourceCity": city,
        "destinationCity": city,
        "weekday": string,
        "departureTime": time,
        "arrivalTime": time,
        "priceEuro": integer,
        "seatsTotal": integer,
        "seatsAvailable": integer
        },
        ...
    ]
Error Response (400 Bad Request):
    Description: Indicates invalid request parameters (e.g., missing or invalid city IDs).
    Response Body Schema:
    {
        "error": string,
        "message": string
    }
Error Response (404 Not Found):
    Description: Indicates that no trips are found for the provided parameters.
    Response Body Schema:
    {
        "error": string,
        "message": string
    }
    

3. GET /api/ticket:
Success Response (200 OK):
    Description: Returns ticket information based on the provided ticket ID.
    Response Body Schema:
    {
        "ticketId": integer,
        "trip": ,
        date: date,
        "firstName": string,
        "lastName": string
    }
Error Response (404 Not Found):
    Description: Indicates that the requested ticket ID does not exist.
    Response Body Schema:
    {
        "error": string,
        "message": string
    }
       
4. POST /api/ticket:
Success Response (200 OK):
    Description: Indicates that the ticket reservation was successful.
    Response Body Schema:
    {
        "ticketId": integer,
        "trip": trip,
        date: date,
        "firstName": string,
        "lastName": string
    }
Error Response (400 Bad Request):
    Description: Indicates invalid request parameters (e.g., invalid trip ID or date in the past).
    Response Body Schema:
    {
        "error": string,
        "message": string
    }
Error Response (403 Forbidden):
    Description: Indicates that there are not enough available seats for the requested trip.
    Response Body Schema:
    {
        "error": string,
        "message": string
    }
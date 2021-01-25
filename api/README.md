## Description
Java backend to SIP project.

## How to run
In the root of the backend (/api), run `docker-compose up`

## Short documentation
Backend works on localhost:8080 and exposes two endpoints
/brands
  GET
    Get all supported station brands (ORLEN, BP, etc)
    Returns an array of all brands
/stations
  GET
    Get all stations in the backend
    Returns an array of all stations
  POST
    Create a station
    All values must be specified in request body or request params
    Returns id of created object
  PATCH
    Patch a station
    All non-null values will make it way to the db.
    Id must always be specified.
    Returns id of updated object.
    

How the requests should look like can be seen in the 
provided, in the same directory, postman collection.


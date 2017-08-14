# Rest
 
 [![Build Status](https://travis-ci.org/OlegKuleba/Rest.svg?branch=master)](https://travis-ci.org/OlegKuleba/Rest)
 
Spring Boot web service which return contacts list from DB using regex (returned results will not match with regex).
Example: request http://localhost:8080/hello/contacts?nameFilter=.*[aei].*$ returns contacts with names that do not contain the letters "a", "e" and "i".
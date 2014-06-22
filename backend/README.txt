GetRidOf API
============

REST API for get rid of application consists of following endpoints:

    1. Register User

        URL : http://groapi.cloudcontrolled.com/gro-api/services/registration
        METHOD : POST
        Example Request : {
                          "email" : "test1@mail.com",
                          "password" : "test123"
                          }
        Example Response : {"id":2,"firstName":null,"lastName":null,"address":null,"email":"test1@mail.com","phone":null,"password":"ecd71870d1963316a97e3ac3408c9835ad8cf0f3c1bc703527c30265534f75ae","birthday":null}
        Description : register user and persist it in the database, email must be unique, email and password are mandatory

    2. Login
        URL : http://groapi.cloudcontrolled.com/gro-api/services/authentication/login
        METHOD : POST
        Example Request : {
                          "email" : "test1@mail.com",
                          "password" : "test123"
                          }
        Example Response : {"id":2,"firstName":null,"lastName":null,"address":null,"email":"test1@mail.com","phone":null,"password":"ecd71870d1963316a97e3ac3408c9835ad8cf0f3c1bc703527c30265534f75ae","birthday":null}
        Description : check user credentials against values in database, return full user data if present and password matches,
                        return 204 and empty body otherwise

    3. Add new item
        URL : http://groapi.cloudcontrolled.com/gro-api/services/items/createItem
        METHOD : POST
        Example Request : {
                          "name"" : "testItem",
                          "image"":"ewoibmFtZSIgOiAidGVzdEl0ZW0iLAoiaW1hZ2UiOiJhc2RzYWRzYWRzYWRhc2Rhc2RhZGFzIgp9",
                          "userId"" : 16
                          }
        Example Response : {"id":2,"image":"ewoibmFtZSIgOiAidGVzdEl0ZW0iLAoiaW1hZ2UiOiJhc2RzYWRzYWRzYWRhc2Rhc2RhZGFzIgp9","name":"testItem","tags":null,"new":false}
        Description : add item to user's list of items
    4. Search items
        URL : http://groapi.cloudcontrolled.com/gro-api/services/items/find
        METHOD : PUT
        Example Request : {
                          "name" : "t1",
                          "tags" : "t"
                          }
        Example Response : [{"id":1,"image":"ewoibmFtZSIgOiAidGVzdEl0ZW0iLAoiaW1hZ2UiOiJhc2RzYWRzYWRzYWRhc2Rhc2RhZGFzIgp9","name":"testItem","tags":"test","quantity":0,"userData":null,"new":false}]
        Description : Search for users items based on name or tags associated with it
    5. Create Exchange Offer
        URL : http://groapi.cloudcontrolled.com/gro-api/services/offers/createOffer
        METHOD : POST
        Example Request : {
                          "sourceItem" : {
                          "id"" : 4
                          },
                          "destinationItem" : {
                          "id"" : 1
                          },
                          "diff" : 100
                          }
        Example Response : {"id":1,"sourceItem":{"id":4,"image":"ewoibmFtZSIgOiAidGVzdEl0ZW0iLAoiaW1hZ2UiOiJhc2RzYWRzYWRzYWRhc2Rhc2RhZGFzIgp9","name":"testItem","tags":null,"quantity":0,"userData":null,"new":false},"destinationItem":{"id":1,"image":"ewoibmFtZSIgOiAidGVzdEl0ZW0iLAoiaW1hZ2UiOiJhc2RzYWRzYWRzYWRhc2Rhc2RhZGFzIgp9","name":"testItem","tags":null,"quantity":0,"userData":null,"new":false},"diffAmount":100,"comment":null,"new":false}
        Description : create offer for user to exchange items including compensation of the difference in the value of
                    items
    6. See Offers Received
        URL : http://groapi.cloudcontrolled.com/gro-api/services/offers/getOffers/16
        METHOD : GET
        Example Request :
        Example Response : [{"id":1,"sourceItem":{"id":4,"image":"ewoibmFtZSIgOiAidGVzdEl0ZW0iLAoiaW1hZ2UiOiJhc2RzYWRzYWRzYWRhc2Rhc2RhZGFzIgp9","name":"testItem","tags":null,"quantity":0,"userData":null,"new":false},"destinationItem":{"id":1,"image":"ewoibmFtZSIgOiAidGVzdEl0ZW0iLAoiaW1hZ2UiOiJhc2RzYWRzYWRzYWRhc2Rhc2RhZGFzIgp9","name":"testItem","tags":null,"quantity":0,"userData":{"@userId":1,"id":16,"firstName":null,"lastName":null,"address":null,"email":"test7@mail.com","phone":null,"password":"ecd71870d1963316a97e3ac3408c9835ad8cf0f3c1bc703527c30265534f75ae","birthday":null,"items":null,"economicalData":{"id":16,"number":"4111111111111111","cvv":"111","expiryMonth":"11","expiryYear":"2015","userData":1,"new":false}},"new":false},"diffAmount":100,"comment":null,"new":false}]
        Description : retrieve all offers that are ceated for user based on user's id
    7. Accept an Offer
        URL : http://groapi.cloudcontrolled.com/gro-api/services/offers/confirm/1
        METHOD : GET
        Example Request :
        Example Response :
        Description : accept an offer and trigger money credit\debit operation using Braintree service


## Twitter like java client

This document has all the commands to run java client


### Steps to run the application

1. Download the code from github from the following link <https://github.com/abdulazeezsk/twitterlikeclient>
2. Make sure you have java installed on your pc and run following commands

    ```
    javac TwitterClient.java
    java TwitterClient
    
    ```
3. Run <http://localhost:8080/> in your browser. It should display current time if server is started successfully

### Commands to get/add tweets


#### POST call to add new tweet

Following POST call adds new tweet of the user (username and tweet should be passed as query parameters)

```
java TwitterClient POST user1 "This is my first tweet"

```

#### GET call to read unread tweets

Following GET call returns unread tweets of the user(It returns tweets of this particular user is following on this application ),

```
java TwitterClient GET user1 true

```


#### GET call to read all tweets

Following GET call returns all tweets of the user(It returns tweets of this particular user is following on this application ),

```
java TwitterClient GET user1 false

```
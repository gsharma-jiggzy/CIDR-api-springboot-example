CIDR-api-springboot-example
This Spring Boot Project is based on the article https://dzone.com/articles/how-to-create-rest-api-with-spring-boot.

This Project can be ran wtih Gradle 5.6.2 and openjdk 11.0.4.
The project uses apache's commons-net utils for IP generation from a CIDR block for IPv4 only.
To run the project run it with gradle bootRun.

There are 4 end points

/api/v1/list (Get Request for list of IPs)
/api/v1/create (Post Request for creating ips with a plain text input like 192.168.100.14/24)
/api/v1/acquire (Post Request for acquiring ip with a plain text input like 192.168.100.1)
/api/v1/release (Post Request for releasing an ip with a plain text input like 192.168.100.1)

For more see the ipapi.postman_collection.

This Project uses the h2 database
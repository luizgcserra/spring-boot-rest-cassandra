# spring-boot-rest-cassandra
Rest API Spring Boot with Cassandra Database example

This repository aims to show an example of how to implement a REST API using Spring Boot, persisting data in Cassandra Database.

To run this example follow these steps:

1. Install Java 8 (https://java.com/en/download/)
2. Install Eclipse IDE (http://www.eclipse.org/downloads)
3. Install Cassandra Database (http://cassandra.apache.org/download/)
4. Install Maven (https://maven.apache.org/)
5. Into Eclipse IDE, install Spring Boot Tools at Eclipse Marketplace finding by STS. (https://marketplace.eclipse.org/content/spring-tools-aka-spring-ide-and-spring-tool-suite)
6. Open project, the Maven Dependencies will be downloaded.
7. Start Cassandra service
8. Create a database:
```sql
CREATE KEYSPACE contoso WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };
```
9. Create "customer" table in "contoso" keyspace:
```sql
use contoso;

CREATE TABLE customer(
   id int PRIMARY KEY,
   firstname text,
   lastname text,
   email text
);
```
    
10. Optionally, you can create the "email" index to search:
```sql
CREATE INDEX ON contoso.customer (email);
```
11. Change environment settings in the "/spring-boot-rest-mysql/src/main/resources/application.properties" file.
12. Run project into Eclipse IDE or command line: `mvn spring-boot:run`

## Benchmarks

The load test was run Virtualized (Virtual Box) Linux Ubuntu 16.04 LTS operational system, 4vCPU, 6GB RAM and HDD. The physical machine is a Windows 10 operational system, Intel i7 7th Gen, 16GB RAM.

### JMeter Thread Group Settings

Number of threads (Users): 1000

Ramp-Up (sec): 10

Loop Count: 200

### Test Scenario

Using Apache JMeter to read a CSV file with 200.000 rows containing person attributes (Id, Firstname, Lastname and Email) to invoke the REST API.

#### Scenario #1: Insert Data (POST)

Samples: 200.000

Request AVG: 233 ms

Request MIN: 1 ms

Request MAX: 3098 ms

Thoughput: 3211.1 req/sec

Total Time: 00:01:02

#### Scenario #2: Retrieve Data (GET)

Samples: 200.000

Request AVG: 385 ms

Request MIN: 1 ms

Request MAX: 7462 ms

Thoughput: 2171.9 req/sec

Total Time: 00:01:31





# SpringDB
SpringDB is a project aimed at learning REST API using the [Spring](https://spring.io) framework.
It's linked to a MySQL database so to enable data permanence and uses JWT to allow stateless sessions.

# Database setup
The database setup can be split in two parts.
The fist part is to download, install and set up the MySQL server
([MySQL Community Server](https://dev.mysql.com/downloads/mysql/)).
Take note of the URL, username and password during the configuration process.

For the second part you can choose one of two ways.

<details>
   <summary>
      <b>Method 1</b>
   </summary>

### Create a table
1. Download and install [MySQL Workbench](https://dev.mysql.com/downloads/workbench/)
2. Connect to your database server you set up before
3. Create a new table in the database:
   1. Go to "Schemas" on the left pane
   2. Right click on "Tables" -> Create table
      (or you can just manually type in a query if you want to have some fun)
4. Use the following parameters:*
   1. Table name: Auto
   2. Add a single column "id" where "id" is also the primary key

_* Note that for the purpose of this project I used this configuration.
You can skip this step if you have other needs.
Also note that if you have a different configuration of the database tables you'll need
to adapt the corresponding classes accordingly._

### Link the database to the project
1. Create a new file in the `/src/main/resources` directory
2. Name the file as `application.properties`
3. Paste (and edit accordingly) the following:
```
spring.jpa.hibernate.ddl-auto=none
spring.datasource.url=<your_db_URL>(it looks something like this:  jdbc:mysql://127.0.0.1:3306/dbName)
spring.datasource.username=<your_username>
spring.datasource.password=<your_password>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### You are all set!
</details>

<details>
   <summary>
      <b>Method 2</b>
   </summary>

If you don't want to install the MySQL Workbench client you can follow these steps
to let Java do all the work.

### Link the database to the project
1. Create a new file in the `/src/main/resources` directory
2. Name the file as `application.properties`
3. Paste (and edit accordingly) the following:
```
spring.jpa.hibernate.ddl-auto=create
spring.datasource.url=<your_db_URL>(it looks something like this:  jdbc:mysql://127.0.0.1:3306/dbName)
spring.datasource.username=<your_username>
spring.datasource.password=<your_password>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```
Java will now create a table (or tables) based on the repository classes in the project.
After the first run, the table has been created, and you can change `create` to `none`.

If you are having issues with this configuration try using `update` instead of `create`,
and change it to `none` after the table has been created.

### You are all set!
</details>

# Quick project setup
Make sure to add a  class under `com.alessio.springdb.constants` with a static property `KEY`
to hold your key.

## Run
To run the program execute the **main** function in the SpringDbApplication class.

## Have fun

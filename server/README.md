# Backend

### Initial Setup
To be able to run the project on your local machine, you will need to install MySQL and create a new database
- First install MySQL on your machine
- Next, create a new database. The config file currently uses `eduscape_test` as the database name
- Edit `application.properties` in the `/resources` folder to use your MySQL username and password (and database if you used a different name)
- Make sure your database is running! On Windows, MySQL may not start after a system restart

Another problem you may encounter is your IDE not recognizing the Java or Maven project.
- This works for IntelliJ. I'm not sure how the process works with VSCode
- Right-click on the `java` then click `Mark Directory as -> Sources Root`
- Right-click on the `pom.xml` file and click `Add as Maven Project`

### Running the Project
First navigate to the terminal and enter the project's `/server` directory.

Set up the ssh tunnel to the server with `ssh -L 3307:localhost:3306 Admin@139.147.9.167 -N`

You can run the application by using `./mvnw spring-boot:run`.
Alternatively, you can build the JAR file with `./mvnw clean package`
and then run the JAR file, as follows: `java -jar target/{project_name}.jar`

Similarly, you can create a maven configuration `spring-boot:run` to have the IDE run your project instead. This will run exactly the same as the mvnw command from above

### Development Commands
At the moment, there are simple commands to insert and read data. You can use the `curl` command or your browser to ping your local instance
- `localhost:8080/insert/all` - Inserts the data. This may take a while (10-60 seconds) and only needs to run once to initialize the data
- `localhost:8080/schools/all` - Gets all schools as JSON
- `localhost:8080/schools/allNames` - Gets all school names
- `localhost:8080/districts/all` - Gets all districts as JSON
- `localhost:8080/districts/allNames` - Gets all district names

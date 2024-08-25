# Bug Tracking System Backend

## Table of Contents

- [About](#about)
- [Getting Started](#getting_started)
- [Usage](#usage)
- [Contributing](../CONTRIBUTING.md)

## About <a name = "about"></a>

Backend application with layered architecture.

## Getting Started <a name = "getting_started"></a>

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See [deployment](#deployment) for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them.

```
java 22
mysql
```


### Installing

<<<<<<< HEAD
1. Clone the repo
=======
Clone the repo
>>>>>>> bb5fe003d356c92b936128a17a947d7235f68253

```
git clone git@github.com:hugs-for-bugs-codefury/backend-bug-tracking-system.git
```

## Usage <a name = "usage"></a>

Edit the config file `src\main\java\org\example\util\Config.java` to setup database connection.


1. Open the cloned repo in IntelliJ IDEA or any other IDE.

2. Start the MySQL server, and if running for the first time setup the database.

3. To setup the database for first time, run the SQL script `schema.sql`

4. Edit the `src\main\java\org\example\util\Config.java` file to setup the database connection and other config.

```java
public class Config {
    public static final String URL = "jdbc:mysql://localhost:3306/bug_tracking_system";
    public static final String USER = "root";
    public static final String PASSWORD = "password";
}
```

Run the application using IntelliJ IDEA, or if you have maven installed, you can run the application using the following command:

```bash
mvn clean install
mvn exec:java -Dexec.mainClass="org.example.App"
```

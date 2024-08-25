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

Clone the repo

```
git clone git@github.com:hugs-for-bugs-codefury/backend-bug-tracking-system.git
```

Open the cloned repo in IntelliJ IDEA or any other IDE.


End with an example of getting some data out of the system or using it for a little demo.

## Usage <a name = "usage"></a>

Edit the config file `src\main\java\org\example\util\Config.java` to setup database connection.




```java
public class Config {
    public static final String URL = "jdbc:mysql://localhost:3306/bug_tracking_system";
    public static final String USER = "root";
    public static final String PASSWORD = "password";
}
```

Run the application using IntelliJ IDEA or compile the application using the following command.

```java
javac Main.java
```
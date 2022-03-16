# Project
Do a project related to Application Programming Interfaces, and write code for other programmers to use. We will start with a code base of an existing popular library written in Java, [JSON.org](https://stleary.github.io/JSON-java/), a library to convert XML to JSON and vice-versa. We will be doing it in stages as follows:
1. Milestone 1: understand how to use the JSON.org API, including how to convert from XML to JSON, and vice-versa, and how to use the query language given by JSONPointer.
2. Milestone 2: make it your own code base by forking the project and adding some service functions.
3. Milestone 3: understand functional programming by providing interfaces with higher-order functions.
4. Milestone 4: understand stream-based styles by creating and exposing streams to application code.
5. Milestone 5: understand concurrent programming by providing asynchronous methods to application code.

## Purpose

1. Concrete API styles and their efficient implementations:
* Vanilla functions and parameters, but with an eye towards good runtime performance and small memory footprint
* Functional APIs and their implementations
* Streaming APIs and their implementations
* Asynchronous APIs and their implementations

2. How to effectively contribute to a body of code that you didn´t write:
* How to extend an API with additional programmer-facing functions
* How to understand code that you didn´t write
* How to contribute in a way that maximizes reuse of what´s already there
* How to contribute in a way that matches the general style of what´s there

## MileStone1
[MileStone1 README](https://github.com/zichengshan/262P_Project)
![Screen Shot 2022-03-15 at 9 27 29 PM](https://user-images.githubusercontent.com/61951792/158516826-4685d5f9-87ac-481c-a7b8-59f04eb8b336.png)

Reference links:
* [API](https://stleary.github.io/JSON-java/)
* [Introduction to JSON-Java(org.json)](https://www.baeldung.com/java-org-json)
* [org.json - Quick Guide](https://www.tutorialspoint.com/org_json/org_json_quick_guide.htm)
* [XML sample file](https://docs.microsoft.com/en-us/previous-versions/windows/desktop/ms762271(v=vs.85))

## MileStone2
[MileStone2 README](https://github.com/zichengshan/JSON-java/blob/master/Milestone2-README.md)
![Screen Shot 2022-03-15 at 9 25 22 PM](https://user-images.githubusercontent.com/61951792/158516693-9b509609-a7b3-48f6-ad21-985dfc070a5d.png)

## MileStone3
[MileStone3 README](https://github.com/zichengshan/JSON-java/blob/master/Milestone3-README.md)
![Screen Shot 2022-03-15 at 9 25 30 PM](https://user-images.githubusercontent.com/61951792/158516726-4ab33ef7-6b4f-49a8-af2d-072fb565ee5b.png)

## MileStone4
[MileStone4 README](https://github.com/zichengshan/JSON-java/blob/master/Milestone4-README.md)
![Screen Shot 2022-03-15 at 9 25 38 PM](https://user-images.githubusercontent.com/61951792/158516738-6cedc623-737c-48ee-80b5-68f6da4a4d0d.png)

## MileStone5
[MileStone5 README](https://github.com/zichengshan/JSON-java/blob/master/MileStone5-README.md)
![Screen Shot 2022-03-15 at 9 25 46 PM](https://user-images.githubusercontent.com/61951792/158516763-49e39d5e-54b0-4c06-994c-4d4430069c5e.png)

--------

![Json-Java logo](https://github.com/stleary/JSON-java/blob/master/images/JsonJava.png?raw=true)

<sub><sup>image credit: Ismael Pérez Ortiz</sup></sub>


JSON in Java [package org.json]
===============================

[![Maven Central](https://img.shields.io/maven-central/v/org.json/json.svg)](https://mvnrepository.com/artifact/org.json/json)

**[Click here if you just want the latest release jar file.](https://search.maven.org/remotecontent?filepath=org/json/json/20211205/json-20211205.jar)**


# Overview

[JSON](http://www.JSON.org/) is a light-weight language-independent data interchange format.

The JSON-Java package is a reference implementation that demonstrates how to parse JSON documents into Java objects and how to generate new JSON documents from the Java classes.

Project goals include:
* Reliable and consistent results
* Adherence to the JSON specification 
* Easy to build, use, and include in other projects
* No external dependencies
* Fast execution and low memory footprint
* Maintain backward compatibility
* Designed and tested to use on Java versions 1.6 - 1.11

The files in this package implement JSON encoders and decoders. The package can also convert between JSON and XML, HTTP headers, Cookies, and CDL.

The license includes this restriction: ["The software shall be used for good, not evil."](https://en.wikipedia.org/wiki/Douglas_Crockford#%22Good,_not_Evil%22) If your conscience cannot live with that, then choose a different package.

# If you would like to contribute to this project

For more information on contributions, please see [CONTRIBUTING.md](https://github.com/stleary/JSON-java/blob/master/docs/CONTRIBUTING.md)

Bug fixes, code improvements, and unit test coverage changes are welcome! Because this project is currently in the maintenance phase, the kinds of changes that can be accepted are limited. For more information, please read the [FAQ](https://github.com/stleary/JSON-java/wiki/FAQ).

# Build Instructions

The org.json package can be built from the command line, Maven, and Gradle. The unit tests can be executed from Maven, Gradle, or individually in an IDE e.g. Eclipse.
 
**Building from the command line**

*Build the class files from the package root directory src/main/java*
````
javac org/json/*.java
````

*Create the jar file in the current directory*
````
jar cf json-java.jar org/json/*.class
````

*Compile a program that uses the jar (see example code below)*
````
javac -cp .;json-java.jar Test.java (Windows)
javac -cp .:json-java.jar Test.java (Unix Systems)
````

*Test file contents*

````
import org.json.JSONObject;
public class Test {
    public static void main(String args[]){
       JSONObject jo = new JSONObject("{ \"abc\" : \"def\" }");
       System.out.println(jo.toString());
    }
}
````

*Execute the Test file*
```` 
java -cp .;json-java.jar Test (Windows)
java -cp .:json-java.jar Test (Unix Systems)
````

*Expected output*

````
{"abc":"def"}
````

 
**Tools to build the package and execute the unit tests**

Execute the test suite with Maven:
```
mvn clean test
```

Execute the test suite with Gradlew:

```
gradlew clean build test
```

# Notes

For more information, please see [NOTES.md](https://github.com/stleary/JSON-java/blob/master/docs/NOTES.md)

# Files

For more information on files, please see [FILES.md](https://github.com/stleary/JSON-java/blob/master/docs/FILES.md)

# Release history:

For the release history, please see [RELEASES.md](https://github.com/stleary/JSON-java/blob/master/docs/RELEASES.md)

### Milestone3 Assignment

Group member: Zicheng Shan, Chenxu Wang

#### Parse() and toJSONObject() methods are added in src/main/java/org.json/XML.java
1. JSONObject toJSONObject(Reader reader, Function<String,String> func) [line 1281]
  - Rewrite toJSONObject() method, inputs are a reader and a lambda function
  - Purpose: Add a prefix to all of its keys
  - Main method:
    - Rewrite Parse() [line 1071]. Compared with original parse(), replace string and tagName variable with func.apply(string) and func.apply(tagName)
    - Call the new Parse() method in toJSONObject(), put reader and func as input

#### Unit Tests are added in src/test/java/org.json.junit/MileStone2Test.java
* The unit tests are based on two small test files (https://www.ics.uci.edu/~lopes/teaching/swe262p/M2Test.java and https://docs.microsoft.com/en-us/previous-versions/windows/desktop/ms762271(v=vs.85)).

1. For JSONObject toJSONObject(Reader reader, Function<String,String> func) we tested adding "SWE262_" as prefix.
    - Both of two test cases are passed
    
#### Performance comparison
* Based on the average running time between in-library and outside. The method in library performance better.
* file size: 210.4MB
  numebr of lines: 2855718
  running time: in-library = 3681023958 ns; outside = 5038369000 ns
  The efficiency increased by 27%.

#### build script
* The built jar file is in JSON-java/build/libs/.




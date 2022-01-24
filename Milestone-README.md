### Milestone2 Assignment

####Two toJsonObject() methods are added in src/main/java/org.json/XML.java
1. JSONObject toJSONObject(Reader reader, JSONPointer path) (At 760 Line)
    - Extract smaller sub-objects on a certain key path
    - Main method:   
      - Traverse the tag names in the xml file according to the path given by JSONPointer until the last one. 
      - Then call the parse() function to write the subobject to the target object.
2. JSONObject toJSONObject(Reader reader, JSONPointer path, JSONObject replacement) (At 840 Line)
    - Replace a sub-object on a certain key path with another JSON object that user constructed
    - Main Method: 
      - Call toJSONObject() to convert xml to JSONObject
      - Find the target object recursively and use put method to replace sub-object

#### Unit Tests are added in src/test/java/org.json.junit/MileStone2Test.java

### Add these answers:
* What cases are these unit tests based?
* The result of unit cases?
* Performance comparison: might base on the average running time between in-library and outside
* Intro to the build script




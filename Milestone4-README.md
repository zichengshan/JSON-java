### Milestone4 Assignment

Group member: Zicheng Shan, Chenxu Wang

#### toStream() methods is added in src/main/java/org.json/JSONObject.java at [line 487]
* toStream() method returns a stream of JSONObjects inside the input JSONObject. It returns the elements in DFS manner.

For instance, the input JSONObject is as follows:
```
{"contact": {
    "nick": "Crista",
    "address": {
        "zipcode": 92614,
        "street": "Ave of Nowhere"
    },
    "name": "Crista Lopes"
}}
```
The output stream is as follows:
```{"contact":{"nick":"Crista","address":{"zipcode":92614,"street":"Ave of Nowhere"},"name":"Crista Lopes"}}
    {"nick":"Crista"}
    {"address":{"zipcode":92614,"street":"Ave of Nowhere"}}
    {"zipcode":92614}
    {"street":"Ave of Nowhere"}
    {"name":"Crista Lopes"}
```
#### Unit Tests are added in src/test/java/org.json.junit/MileStone4Test.java
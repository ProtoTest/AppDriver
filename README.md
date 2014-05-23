appdriver
======

Java port of Golem (C#)

To build the framework:
'mvn clean install' -- Cleans, updates all dependencies using maven, builds, and installs each sub-modules JAR file to in your user .m2 directory.

To run a test suite from a sub-module:
cd \<sub-module dir\>; 
mvn test
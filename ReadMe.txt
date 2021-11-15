Instructions:

1. This project is built using Spring - 5.3.12, JAVA 1.8
2. Used STS to build this project

Assumptions:

3. "ranking-depth-charts" API expects that request always contain functional valid positions. We assume the player object will contain postions fetched from master table. This will allow to add any number of positions in future.
4. "ranking-depth-charts" assumes that for a given position only one player can be added at a depth.
5. "ranking-depth-charts" assumes that one Player can be added to multiple positions.
6. The request is in JSON format.

How to run:
Install Apache Tomcat and add the generated .WAR file to  WEBAPPS folder.
Start / stop server using startup.bat / shutdown.bat

OR

Add server in STS IDE. Add and then start the server.


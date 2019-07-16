FROM openjdk:8-jdk

ENV CATALINA_HOME  /usr/share/tomcat8/bin
RUN  apt-get update && apt-get install tomcat8 -y 
COPY  ./gameoflife.war /usr/share/tomcat8/webapps/gameoflife.war 
CMD ["catalina.sh", "run"]


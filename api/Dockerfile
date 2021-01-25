FROM openjdk:15
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN ./mvnw -Dmaven.test.skip=true clean install
ENTRYPOINT ["java", "-jar", "target/sip-1.0.0-SNAPSHOT.jar"]


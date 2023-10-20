FROM eclipse-temurin:17-jdk-alpine
VOLUME ["/tmp", "/test"]
COPY run.sh .

# plain jar는 gradle.build에 설정해주어야 없어짐
COPY build/libs/*.jar app.jar

# java -jar 로 실행하면 PID가 1로 실행된다.
# sh 파일로 실행할때 반드시! exec java -jar 를 해줘야 PID 1번을 할당받아 SIGTERM을 받을 수 있다.
# ENTRYPOINT ["java", "-jar", "/app.jar"]
ENTRYPOINT ["/run.sh"]

# java -jar JAVA_OPTS 받기
# docker run -p 8080:8080 -e "JAVA_OPTS=-Ddebug -Xmx128m -Dserver.port=9000" myorg/myapp
# ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app.jar"]

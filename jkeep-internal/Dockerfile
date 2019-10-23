FROM openjdk:8-jre-alpine
# 维护者
MAINTAINER LiangZhong.Tan<liangzhong.tan@outlook.com>
VOLUME /tmp
ADD ./target/JKeep.jar app.jar
# ENV JAVA_OPTS="-Xms512m -Xmx1536m"
ENTRYPOINT ["sh", "-c", "java -server $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]
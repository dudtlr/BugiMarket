# OpenJDK 11 기반 이미지
FROM openjdk:11

# Gradle로 빌드된 .jar 파일을 복사하기 위한 작업 디렉토리 설정
WORKDIR /app

# 로컬에서 빌드된 .jar 파일을 컨테이너로 복사 (build/libs 폴더에서 .jar 파일을 복사)
COPY build/libs/*.jar app.jar

# Spring Boot 애플리케이션 실행 명령어
ENTRYPOINT ["java", "-jar", "app.jar"]

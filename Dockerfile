# FROM 을 두번 사용하는 이유 : FROM 을 한 번 사용하면 빌드 + 실행이 포함 돼 있는 것 (이미지 크기 증가, 빌드 과정 포함)
#                           FROM 을 두 번 사용하면 빌드 실행 환경 분리 실행됨

# 빌드(build)**는 소스 코드를 컴퓨터가 이해하고 실행할 수 있는 형태로 변환하는 작업입니다.
# 빌드 작업은 주로 코드 컴파일, 의존성 다운로드, 리소스 파일 처리, 최종 실행 파일 생성 등으로 이루어지며,
# Java 프로젝트에서는 Gradle 또는 Maven과 같은 빌드 도구가 이 과정을 관리

FROM openjdk:17-jdk AS builder

WORKDIR /workspace

COPY /build/libs/*SNAPSHOT.jar chat-app.jar

RUN java -Djarmode=layertools -jar chat-app.jar extract


FROM openjdk:17-jdk

WORKDIR /chat-app

COPY --from=builder /workspace/dependencies/ ./
COPY --from=builder /workspace/spring-boot-loader/ ./
COPY --from=builder /workspace/snapshot-dependencies/ ./
COPY --from=builder /workspace/application/ ./

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
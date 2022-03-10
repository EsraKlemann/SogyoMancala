FROM gradle:latest

WORKDIR /MANCALA-JAVA-ESRA

COPY . .

EXPOSE 8080

RUN gradle build

ENTRYPOINT gradle run

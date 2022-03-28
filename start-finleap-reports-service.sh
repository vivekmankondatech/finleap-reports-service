
./mvnw clean package -DskipTests

cp build/libs/finleap-reports-service-0.0.1-SNAPSHOT.jar src/main/docker

cd src/main/docker
docker-compose down
docker rmi finleap-reports-service:latest
docker-compose up


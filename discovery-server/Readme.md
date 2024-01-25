# Comando para perfiles

mvn spring-boot:run -Dspring-boot.run.profiles=profile-01
mvn spring-boot:run -Dspring-boot.run.profiles=profile-02
mvn spring-boot:run -Dspring-boot.run.profiles=profile-03

# Comando para pasar argumentos

mvn spring-boot:run -Dspring-boot.run.arguments=--CUSTOM.SERVER.PORT=9000
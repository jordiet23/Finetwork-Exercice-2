# Finetwork-Exercice-2
Ejercicio 2 de la prueba técnica de Finetwork

Lo primero que hay que hacer para arrancar la aplicación es crearnos la BBDD llamada fi_client en un docker de postgres.

Y finalmente, para arrancar la aplicación únicamente necesitamos tener instalado una versión de gradle compatible con el proyecto SpringBoot (por ejemplo la 7.5) y lanzar estos dos comandos:

  gradle wrapper

  ./gradlew build && java -jar build/libs/Exercice2-0.0.1-SNAPSHOT.jar
  
O si bien lo preferimos ejecutar el Exercice2Application desde nuestro editor favorito.


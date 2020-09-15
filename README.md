# casino

La aplicación está estructurada en 2 proyectos:  

 - el primero de ellos, llamado **casino**, contiene los servicios correspondientes a los juegos del casino.

 - el segundo, llamado **Oauth2Server**, contiene el servidor de autenticación que simula el proveedor de usuarios.

Ambos proyectos han sido desarrollados con Spring Boot sobre Maven Spring Web y utiliza una BB.DD. H2 en memoria.
El proyecto **casino**, además, contiene el front-end de la aplicación con JavaScript. Se han incorporado al mismo las librerías Lombok y Swagger que favorecen la limpieza del código, la primera permite utilizar anotaciones para los métodos de una entidad y la segunda facilita la documentación en el código. El proyecto contiene el cliente de autenticación, pero no maneja ni almacena usuarios. Del paquete raíz del proyecto **casino** extienden siete paquetes con la configuración, el modelo, la lógica de negocio, el servicio, los data transfer object, el controlador y los repositorios.  

Las peticiones HTTP acceden al controlador, desde allí se hace la llamada al servicio y éste a su vez invoca al repositorio, encargado del acceso a BB.DD. Tanto los jugadores como las partidas se guardan en BB.DD. y de cada partida se registran las transacciones realizadas.   

El servidor de autenticación está implementado con Spring Security con OAuth para simular un proveedor de usuarios, es accedido desde el servicio al momento de login del usuario. Para ingresar al casino, el usuario deberá seleccionar el proveedor (en este caso es el servidor de autenticación), introducir su nombre y contraseña y autorizar el uso de las credenciales. El cliente de autenticación solicitará un token al servidor y al recibirlo creará un jugador. Una vez dentro del casino, el jugador podrá seleccionar el juego (se creará una partida) y realizar apuestas siempre que se cuente con el saldo suficiente y el valor esté dentro de los mínimos y máximos previstos por la configuración del juego.     

A continuación, se muestra el diagrama correspondiente a la capa de modelo, detallando las entidades que conforman la aplicación, separadas por proyecto. El cuadro de la izquierda, marcado en azul, agrupa las entidades del servidor de autenticación y el de la derecha, las pertenecientes al casino. 

![diagrama de entidades](https://github.com/FelixMarin/casino/blob/develop/casino/src/main/resources/img/DIAGRAMA_BBDD.png)

## Herramientas utilizadas:   

- Framework: Spring Tools Suite 4 (en adelante STS)  
- JDK 1.8 o superior  
- Maven  
- Spring Boot  
- BBDD: H2  
- Control de versiones: Git  

## Instrucciones para inicializar la aplicación:  

1. Arrancar la aplicación desde STS, o en su defecto, compilar ambos proyectos creando los respectivos JARs y arrancar desde línea de comandos  

2. Una vez arrancados ambos proyectos, a través del navegador, introducir la URL. 

```bash
http://localhost:8081/login.html  
```

3. En la pantalla de login, presionar el botón “Login via Oauth2 Server”. Será redirigido a la página del proveedor de usuarios, allí deberá introducir las credenciales siguientes:  

```bash
usuario: user1 
password: User1 
```
4. Será redirigido, con la sesión ya iniciada, a la página del casino

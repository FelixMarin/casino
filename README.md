# casino

La aplicación está estructurada en 2 proyectos:  

 - el primero de ellos, llamado **casino**, contiene los servicios correspondientes a los juegos del casino.

 - el segundo, llamado **Oauth2Server**, contiene el servidor de autenticación que simula el proveedor de usuarios.

Ambos proyectos han sido desarrollados con Spring Boot sobre Maven Spring Web y utiliza una BB.DD. H2 en memoria.
El proyecto **casino**, además, contiene el front-end de la aplicación con JavaScript. Se han incorporado al mismo las librerías Lombok y Swagger que favorecen la limpieza del código, la primera permite utilizar anotaciones para los métodos de una entidad y la segunda facilita la documentación en el código. El proyecto contiene el cliente de autenticación, pero no maneja ni almacena usuarios. Del paquete raíz del proyecto **casino** extienden siete paquetes con la configuración, el modelo, la lógica de negocio, el servicio, los data transfer object, el controlador y los repositorios.  

Las peticiones HTTP acceden al controlador, desde allí se hace la llamada al servicio y éste a su vez invoca al repositorio, encargado del acceso a BB.DD. Tanto los jugadores como las partidas se guardan en BB.DD. y de cada partida se registran las transacciones realizadas.   

El servidor de autenticación está implementado con Spring Security para simular un proveedor de usuarios, es accedido desde el servicio al momento de login del usuario.  

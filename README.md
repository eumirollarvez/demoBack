Proyecto hecho en java - spring boot - mongodb

Pasos previos para la ejecución de la aplicación:

- verificar la version de java instalada, en mi caso java version "1.8.0_191"
- verificar que se encuentre creada la variable de entorno JAVA_HOME con la ruta del java.
- verificar tener instalado mongodb.
- Ir a la ruta de la instalacion de mongodb, ejemplo: C:\Program Files\MongoDB\Server\4.0\bin
	- ejecutar el mongod
	- ejecutar el mongo, en esta terminar verificar que este todo correcto y muestre las dbs (ejecutar el siguiente comando: show dbs), no cerrarla para verificar mas adelante que se haya creado la db, en nustro caso se llamara demo.
	- Con estos pasos nos aseguramos que se encuentren instalada correctamente.
- Descargar el proyecto del repositorio.

Pasos para la ejecución de la aplicación:

- Entrar por consola en la raíz del proyecto, ejemplo: C:\workspace\demoBack y tendrá que ver las carpetas del proyecto.
- Ejecutar el comando mvnw package para generar el .jar
- Entrar en la ruta C:\workspace\demoBack\target.
- Ejecutar el siguiente comando para levantar el servicio: java -jar demo-0.0.1.jar
- Entrar al terminal de mongo de los pasos anteriores y ejecutar los siguientes comandos:
	- use demo
	- db.articulos.find(), con este podran visualizar los registros consultados en la db.

Nota: 

- La aplicación se estara ejecutando en el puerto 8090, y las ruta del servicio son las siguiente:

	1- GET http://localhost:8090/articulos (para obtener todos los articulos)
	2- DELETE http://localhost:8090/articulos/{id} (para eliminar el articulo segun su id)
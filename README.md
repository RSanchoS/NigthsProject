# Nights Project
## _Code Challenge_

Nights Project es una code challenge para la obtecion de datos economicos de las distintas reservas de noches.


## Caracteristicas

- REST API para una facil entrada de datos e integración con otros sistemas
- Obtencion de estadisticas de las distintas noches de hotel.
- Obtencion de la mejor combinacion de reservas de noches.


## Tecnologias

Este proyecto ha usado las siguiente tecnologias:

- [Java 17] 
- [Maven] Para la gestion de librerias.
- [Spring Boot] - Framework para Java.
- [JUnit5] - Libreria para facilitar el desarrollo de test.
- [Lombok] - Libreria para facilitar el desaarrollo de la aplicacion.


## Instalaciones necesarias

Nights Project requiere tener instalado el equipo:
- [Java 17]
- [Maven]
 
Tras la instalación de ambos se requiere ir a la carpeta donde se encuentra el proyecto
y ejecutar el siguiente comando.
```sh
mvn clean install
```

## Arranque del servidor

Para poder arrancar el servidor, una vez realizadas las instalaciones necesarias y la compilación, has de dirigir a la carpeta y ejecutar el siguiente comando:
```sh
mvn spring-boot:run
```

El servidor por defecto arranca en el puerto 8080.

## Prueba del endpoint

Ahora mismo la aplicación de Mowers tiene los siguiente endpoint:

- /stas
```sh
POST
[
{ 
        "request_id":"bookata_XY123", 
        "check_in":"2020-01-01", 
        "nights":5,
        "selling_rate":200,
        "margin":20
    },
    {
        "request_id":"kayete_PP234",
        "check_in":"2020-01-04",
        "nights":4,
        "selling_rate":156,
        "margin":22
    }
]
```
Respuesta
```sh
{
	"avg_night": 8.29,
	"min_night": 8.0,
	"max_night": 8.58
}
```

- /maximize
```sh
POST
[
{ 
		"request_id":"bookata_XY123", 
 		"check_in":"2020-01-01", 
 		"nights":5,
        "selling_rate":200,
        "margin":20
    },
    {
      "request_id":"kayete_PP234",
      "check_in":"2020-01-04",
      "nights":4,
      "selling_rate":156,
      "margin":5
		},
	{
      "request_id":"atropote_AA930",
      "check_in":"2020-01-04",
      "nights":4,
      "selling_rate":150,
      "margin":6
}, {
      "request_id":"acme_AAAAA",
      "check_in":"2020-01-10",
      "nights":4,
      "selling_rate":160,
      "margin":30
}
]
```
Respuesta
```sh
{
	"request_ids": [
		"bookata_XY123",
		"acme_AAAAA"
	],
	"total_profit": 88,
	"avg_night": 10.0,
	"min_night": 8.0,
	"max_night": 12.0
}
```








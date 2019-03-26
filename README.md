# API MUTANTE

Se requiere de recursos que permitan determinar si secuencias de ADN son mutantes o no.

## Recursos
```
POST → /mutant/ 
BODY {"dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]}
```
El recurso /mutant/ detecta si un humano es mutante o no recibiendo la secuencia de ADN mediante un HTTP POST
En caso de verificar un mutante, devuelve un HTTP 200-OK, en caso contrario un 403-Forbidden, en el caso que la muestra ya hubiera sido analizada y guardada se devuelve un HTTP 409-Conflict,si la muestra no fuera valida se retorna un HTTP 400-Bad Request, en caso de que el servicio falle devolvera un HTTP 500-Internal Server Error.

```
GET → /stats/ 
```
El recurso /stats/ regresa las estadisticas de la evaluación de muestras validas con el siguiente formato:
{“count_mutant_dna”:40, “count_human_dna”:100, “ratio”:0.4}.

## Construcción del War
- Java 1.8
- Maven 3.6.0 or later
- Corre especificando el lugar donde sera desplegado
```
mvn -DdistributionTargetDir="$HOME/app/maven/apache-maven-3.6.x-SNAPSHOT" clean package
```



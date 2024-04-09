# Instrucciones para ejecutar la aplicación

A continuación se detallan los pasos necesarios para ejecutar la aplicación correctamente.

## 1. Instalación de OpenJDK 17

Descarga e instala OpenJDK 17 siguiendo las instrucciones específicas para tu sistema operativo desde [el sitio oficial de OpenJDK](https://jdk.java.net/17/).

## 2. Ejecución de PostgreSQL en Docker

Para ejecutar PostgreSQL en Docker con la versión 15, utiliza el siguiente comando:

```bash
docker run --name postgres-db -e POSTGRES_PASSWORD=root -p 5432:5432 -d postgres:15
```

Esto iniciará un contenedor de PostgreSQL con el usuario "postgres" y la contraseña "root" en el puerto 5432 que ocupan los microservicios.

## 3. Clonar el repositorio

Esto contiene el script SQL para las tablas, constrains, indexes, e inserts para el correcto funcionamiento de los modulos.

```bash
git clone https://github.com/herreralemus03/shopping_cart.git
```

## 4. Crear un esquema en PostgreSQL
Una vez que PostgreSQL esté en ejecución, crea un nuevo esquema llamado "shopping".

```bash
CREATE SCHEMA 'shopping'
```

## 5. Ejecutar el script de inicialización
Ejecuta el script initial-script.sql en el esquema "shopping" que acabas de crear (este script se ubica en la raiz del proyecto).

## 6. Compilar los módulos con Maven
En la raíz del proyecto, ejecuta el siguiente comando para compilar cada uno de los módulos:

```bash
mvn clean install
```
## 7. Establecer variables de entorno
Establece dos variables de entorno en tu sistema: HOSTNAME=localhost y GITHUB_TOKEN=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.

## 8. Ejecutar los módulos
Finalmente, ejecuta los módulos en el siguiente orden:
1. config-server
2. eureka-server
3. auth-service
4. order-service
5. payments-service

## Los context path de los microservicios son los siguientes:

1. auth-service: /
2. order-service: /orders
3. payments-service: /payments

## NOTA: La documentación de cada endpoint está disponible a través de Swagger.
# Sistema de gestión para papelería

Aplicación de escritorio para administrar operaciones de una papelería, con módulos de inventario, ventas, clientes, proveedores, cuentas y reportes.

![Java 21](https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-build-C71A36?logo=apachemaven&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-JPA-59666C?logo=hibernate&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8-4479A1?logo=mysql&logoColor=white)

## Contexto académico

Proyecto grupal de **Ingeniería de Software I**, semestre **5**, periodo **2025-2**, en la **Universidad El Bosque**. La programación de todo el código estuvo a cargo de un integrante del grupo.

El sistema corresponde a un caso académico de gestión de una papelería. Incluye gestión de clientes, proveedores, productos e inventario, ventas, cuentas por cobrar y pagar, y reportes.

## Tecnologías

| Área | Tecnologías |
|---|---|
| Lenguaje e interfaz | Java 21, Swing |
| Persistencia | Jakarta Persistence (JPA), Hibernate, MySQL Connector/J |
| Documentos | iText |
| Construcción | Maven |

## Arquitectura

El punto de entrada `AplMain` crea el controlador, que conecta la interfaz Swing con el modelo y las clases DAO. La persistencia utiliza JPA, Hibernate y MySQL.

```mermaid
flowchart LR
    Main[AplMain] --> Controller[Controller]
    Controller --> View[Interfaz Swing]
    Controller --> Model[ModelFacade / DTO]
    Model --> DAO[DAO y persistencia JPA]
    DAO --> Hibernate[Hibernate]
    Hibernate --> DB[(MySQL)]
```

## Estructura

```text
.
├── src/main/java/co/edu/unbosque/
│   ├── controller/
│   ├── model/
│   ├── model/persistence/
│   ├── util/
│   └── view/
├── src/main/resources/META-INF/persistence.xml
└── pom.xml
```

## Requisitos

- JDK 21
- Maven 3.9 o posterior
- MySQL compatible con MySQL Connector/J 8.0

## Compilación y ejecución

1. Crea la base de datos MySQL indicada por `PAPELERIA_DB_URL` y define las variables de conexión `PAPELERIA_DB_URL`, `PAPELERIA_DB_USER` y `PAPELERIA_DB_PASSWORD`. `.env.example` contiene los nombres y valores de referencia; la aplicación lee las variables del entorno del proceso.
2. Compila el JAR ejecutable:

   ```bash
   mvn clean package
   ```

3. Inicia la aplicación:

   ```bash
   java -jar target/Proyecto_Software-0.0.1-SNAPSHOT.jar
   ```

En Eclipse, importa la carpeta como proyecto Maven, define las tres variables en la configuración de ejecución y ejecuta `co.edu.unbosque.controller.AplMain` como aplicación Java.


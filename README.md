
# 🎓 API Agenda Educativa – Spring Boot
Esta API RESTful proporciona la infraestructura necesaria para gestionar una agenda educativa completa, permitiendo administrar todos los procesos del ciclo académico.
El sistema ofrece funcionalidades para organizar aulas, registrar personas, matricular alumnos, asignar profesores, enviar mensajes informativos, administrar eventos y controlar aportes económicos.

Está construida con Spring Boot bajo una arquitectura por capas que separa controladores, servicios, repositorios y entidades, facilitando la escalabilidad, el mantenimiento y la claridad del código.

## 📌 Funcionalidades principales
📅 Gestión del año académico (apertura, cierre y estados).

🏫 Creación y administración de aulas.

👥 Registro de personas (alumnos, profesores, administrativos).

🎓 Matrícula de alumnos en aulas y cursos.

👨‍🏫 Asignación de profesores a cursos.

💬 Envío de mensajes y notificaciones relacionadas a la agenda educativa.

🎉 Gestión de eventos escolares.

💵 Control de aportes económicos (pagos, cuotas, contribuciones).

## 🛠️ Tecnologías utilizadas
| Tecnología               | Uso                                           |
| ------------------------ | --------------------------------------------- |
| **Spring Boot**          | Framework principal del backend               |
| **Spring Web**           | Construcción de endpoints REST                |
| **Spring Data JPA**      | Acceso a datos y ORM                          |
| **Hibernate**            | Implementación JPA                            |
| **PostgreSQL**           | Base de datos relacional                      |
| **Spring Security**      | Seguridad de la API                           |
| **JWT (JSON Web Token)** | Autenticación y autorización basada en tokens |
| **Lombok**               | Reducción de código repetitivo                |
| **Spring Validation**    | Validación de datos                           |
| **Maven**                | Gestión de dependencias                       |



## 🏗️ Arquitectura del proyecto
El proyecto aplica una arquitectura por capas, donde cada capa cumple una función clara y desacoplada:

```bash
/src
│
├── controller/      # Endpoints REST y manejo de solicitudes HTTP
├── dto/             # Estructuras de entrada y salida
├── entity/          # Entidades del dominio (Persona, Aula, Año Académico, etc.)
├── exception/       # Manejo de excepciones globales y personalizadas
├── repository/      # Interfaces JPA para acceso a la base de datos
└── service/         # Lógica de negocio y comunicación con repositorios

```


## Configurar base de datos y JWT
En application.properties configura:

```bash
spring.datasource.url=jdbc:mysql://localhost:3306/agenda_db
spring.datasource.username=root
spring.datasource.password=tu_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=tu_clave_secreta
jwt.expiration=3600000

```


Para iniciar un servidor de desarrollo local, ejecute:

```bash
mvn spring-boot:run
```

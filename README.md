# Logging with Spring Profiles

Proyecto de ejemplo: configuración de logging por entorno usando **Spring Profiles** y **Logback**.

> **Objetivo:** demostrar cómo usar perfiles (`dev`, `test`, `prod`) para ajustar niveles y salidas de logs según entorno, y mostrar ejemplos prácticos con una API REST simple de gestión de usuarios.

---

## 📦 Contenido principal

- `src/main/java/com/example/user_management` — código fuente (aplicación, controlador, modelo)
- `src/main/resources/logback-spring.xml` — configuración de Logback por perfil
- `src/main/resources/application-*.properties` — propiedades por entorno

---

## ✨ Características

- Endpoints REST básicos: `GET /users`, `POST /users`, `DELETE /users/{id}`
- Logging en controladores con niveles **INFO/WARN/DEBUG** según perfil
- Salidas de logs:
  - `dev` / `test`: **consola**
  - `prod`: **archivo** `logs/app.log`

---

## 🛠 Requisitos

- **Java 17+** (compatible con versión de Spring Boot usada en `pom.xml`)
- **Maven** (se incluye `mvnw` / `mvnw.cmd`)

---

## ▶️ Quick start

### Windows

```powershell
.\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=dev
```

### Unix/macOS

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

La app se ejecutará en el puerto correspondiente al perfil (ver tabla abajo).

---

## ⚙️ Seleccionar perfil

### Opción 1: pasar perfil al ejecutar

```bash
# Windows
.\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=dev
.\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=test
.\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=prod

# Unix/macOS
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
./mvnw spring-boot:run -Dspring-boot.run.profiles=test
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod
```

### Opción 2: variable de entorno

```bash
# Unix/macOS
SPRING_PROFILES_ACTIVE=prod ./mvnw spring-boot:run

# Windows (PowerShell)
$env:SPRING_PROFILES_ACTIVE="prod"
.\mvnw.cmd spring-boot:run
```

---

## 🔌 Endpoints

| Método | Ruta              | Descripción                  |
|--------|-------------------|------------------------------|
| GET    | `/users`          | Lista usuarios (JSON)        |
| POST   | `/users`          | Crea usuario                 |
| DELETE | `/users/{id}`     | Elimina usuario por ID       |

### Cuerpo para POST `/users`

```json
{
  "name": "Nombre",
  "email": "mail@ejemplo.com"
}
```

### Ejemplos con `curl`

```bash
# Listar usuarios
curl -X GET http://localhost:8081/users

# Crear usuario
curl -X POST http://localhost:8081/users \
  -H "Content-Type: application/json" \
  -d '{"name":"Juan","email":"juan@mail.com"}'

# Eliminar usuario
curl -X DELETE http://localhost:8081/users/1
```

> ⚠️ El puerto por defecto cambia según perfil:

| Perfil | Puerto |
|--------|--------|
| `dev`  | 8081   |
| `test` | 8082   |
| `prod` | 8080   |

---

## 🧩 Logging y perfiles

- **`dev`**: nivel `DEBUG` en consola. Útil para depuración local.
- **`test`**: nivel `INFO` en consola. Menos ruido durante pruebas.
- **`prod`**: nivel `WARN` en archivo `logs/app.log`. Evita saturar consola y reduce volumen.

Fichero clave: `src/main/resources/logback-spring.xml`  
Define:

- appenders `CONSOLE` y `FILE`
- bloques `<springProfile>` para activar configuraciones por perfil

> ⚠️ Si `prod` usa `FILE` appender, asegúrate de que la carpeta `logs/` exista o que el proceso tenga permisos para crearla.

---

## 📂 Estructura relevante

- `src/main/java/com/example/user_management/UserManagementApplication.java`
- `src/main/java/com/example/user_management/controller/UserController.java`
- `src/main/java/com/example/user_management/model/User.java`
- `src/main/resources/logback-spring.xml`
- `src/main/resources/application-dev.properties`
- `src/main/resources/application-test.properties`
- `src/main/resources/application-prod.properties`

---

## 🧠 Buenas prácticas y propuestas de mejora

- Añadir manejo de errores global (`@ControllerAdvice`) para respuestas JSON consistentes en errores.
- Reemplazar almacenamiento en memoria por persistencia (JPA + H2/Postgres) para pruebas realistas.
- Validaciones DTO con `@Valid` y `javax.validation` (o `jakarta.validation`) para evitar `IllegalArgumentException`.
- Añadir tests unitarios e integración (Spring Boot Test, Testcontainers si se agrega BD).
- Documentar contract API con **OpenAPI/Swagger** para facilitar consumo y pruebas automáticas.
- Añadir script de creación/rotación de logs en `prod` o usar appender con rolling policy.

---

## 🤖 Cómo usar este README para mejoras con IA

- Información clara sobre endpoints, archivos clave y comportamientos esperados facilita generación automática de tests, documentación y asistentes.
- Incluir ejemplos de requests/responses y casos de error ayuda a entrenar prompts y generar pruebas automáticas.

---

## 🚀 Próximos pasos a ejecutar

1. Integrar `ControllerAdvice` y DTO validations.
2. Añadir persistencia y pruebas de integración.
3. Generar OpenAPI spec y agregar Swagger UI.
4. Automatizar creación/rotación de logs para `prod`.

---

## 📄 Licencia

Agregar licencia si se requiere (ej. MIT).
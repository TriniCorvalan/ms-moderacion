# ms-moderacion

Microservicio de **moderación** del sistema de foros Tertulia: banear y desbanear temas o comentarios que incumplen las normas.

Generado desde el arquetipo Maven `archetype-ms-tertulia`.

- Puerto: **8083**
- Stack: Spring Boot 4, JPA, Security (valida JWT de `ms-usuarios`), Oracle Cloud
- Acceso: solo rol **ADMIN**

Comparte el schema Oracle `FORO_APP` (tablas `TEMA` y `COMENTARIO`, campo `ESTADO`: `ACTIVO` / `BANEADO`).

## Endpoints

| Método | Ruta | Acceso | Descripción |
|--------|------|--------|-------------|
| GET | `/health` | Público | Health check |
| GET | `/moderacion/temas?estado=` | ADMIN | Listar temas (`ACTIVO`, `BANEADO` o todos) |
| PUT | `/moderacion/temas/{id}/banear` | ADMIN | Marcar tema como `BANEADO` |
| PUT | `/moderacion/temas/{id}/desbanear` | ADMIN | Restaurar tema a `ACTIVO` |
| GET | `/moderacion/comentarios?estado=` | ADMIN | Listar comentarios |
| PUT | `/moderacion/comentarios/{id}/banear` | ADMIN | Banear comentario |
| PUT | `/moderacion/comentarios/{id}/desbanear` | ADMIN | Desbanear comentario |

## Configuración

1. Wallet Oracle en una ruta sin espacios (p. ej. symlink `~/oracle_wallets/foro`).
2. Propiedades locales:
   ```bash
   cp src/main/resources/application.properties.example src/main/resources/application.properties
   ```
3. `jwt.secret` idéntico al de `ms-usuarios` y `ms-foro`.

`application.properties` no se versiona.

## Ejecutar

1. Obtener JWT de admin en `ms-usuarios` (`POST /auth/login`).
2. Arrancar este servicio:
   ```bash
   ./mvnw spring-boot:run
   ```
3. Llamar con header `Authorization: Bearer <token>`.

## Tests

```bash
./mvnw test
```

## Estructura de capas

| Capa | Paquete |
|------|---------|
| Controlador | `controller` |
| Servicio | `service` |
| Repositorio | `repository` |
| Modelo | `model` |
| Seguridad | `security` |

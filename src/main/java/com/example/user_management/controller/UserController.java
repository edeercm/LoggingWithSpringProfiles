package com.example.user_management.controller;

import com.example.user_management.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/users")
public class UserController {

    // Logger para imprimir mensajes en consola
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    // Lista para almacenar usuarios en memoria (sin base de datos)
    private List<User> users = new ArrayList<>();

    // Contador para asignar IDs automáticos a los usuarios
    private AtomicLong idCounter = new AtomicLong();

    // Endpoint para obtener todos los usuarios
    @GetMapping
    public List<User> getAllUsers() {
        logger.info("Fetching all users");  // Mensaje de log INFO
        return users;
    }

    // Endpoint para agregar un nuevo usuario
    @PostMapping
    public User addUser(@RequestBody User user) {
        // Validación sencilla: nombre no puede ser vacío
        if (user.getName() == null || user.getName().isEmpty()) {
            logger.warn("Attempted to add user with empty name");  // Log WARN
            throw new IllegalArgumentException("Name is required");
        }
        // Asignar ID único y guardar usuario
        user.setId(idCounter.incrementAndGet());
        users.add(user);
        logger.info("Added new user with ID: {}", user.getId());  // Log INFO
        return user;
    }

    // Endpoint para eliminar usuario por ID
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        // Eliminar usuario si existe
        boolean removed = users.removeIf(u -> u.getId().equals(id));
        if (removed) {
            logger.info("Deleted user with ID: {}", id);  // Log INFO
            return "User deleted";
        } else {
            logger.warn("Attempted to delete non-existent user with ID: {}", id);  // Log WARN
            return "User not found";
        }
    }
}

/*
  Explicación rápida de los elementos:
    @RestController: Indica que esta clase maneja peticiones REST y responde con JSON.
    @RequestMapping("/users"): Prefijo común para todos los endpoints.
    @GetMapping: Maneja solicitudes GET para obtener datos.
    @PostMapping: Maneja solicitudes POST para crear datos.
    @DeleteMapping("/{id}"): Maneja solicitudes DELETE con parámetro ID.
    Logger: Sirve para imprimir mensajes en consola (importante para monitorear el estado y errores).
    AtomicLong: Contador seguro para IDs únicos.
    users: Lista en memoria para almacenar usuarios, simulando una base de datos.
*/

/*
  Cómo probar los endpoints
    1- Ejecuta la aplicación (botón “Run” en tu IDE).
    2- Usa Postman o curl para probar:
      Obtener usuarios (debería regresar lista vacía al inicio):
        curl -X GET http://localhost:8080/users

      Agregar usuario (envía JSON en body):
        curl -X POST http://localhost:8080/users -H "Content-Type: application/json" -d '{"name":"Juan","email":"juan@mail.com"}'
      
        Eliminar usuario por ID (por ejemplo ID=1):
          curl -X DELETE http://localhost:8080/users/1
    3- Observa los logs
      Cuando hagas estas llamadas, revisa la consola de tu IDE y verás mensajes de INFO o WARN según la operación.
*/
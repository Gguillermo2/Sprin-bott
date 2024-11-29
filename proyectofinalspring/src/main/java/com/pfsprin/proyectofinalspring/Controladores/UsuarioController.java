package com.pfsprin.proyectofinalspring.Controladores;

import com.pfsprin.proyectofinalspring.Entidadess.LoginRequest;
import com.pfsprin.proyectofinalspring.Entidadess.Usuario;
import com.pfsprin.proyectofinalspring.Servicios.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


import java.util.List;
import java.util.Optional;
// Coonexion de puertos de Vue  para que tome los  endpoints
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    // se llama a toso los usuarios
    @GetMapping
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.obtenerTodos();
    }
    // se llama al uisurio por id
    @GetMapping("/{id}")
    public Optional<Usuario> obtenerUsuario(@PathVariable Integer id) {
        return Optional.ofNullable(usuarioService.obtenerPorId(id));
    }
    // se registra un usuario
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.crearUsuario(usuario);
    }
    // Se actualiza el Usuario por id
    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        return usuarioService.actualizarUsuario(id, usuario);
    }
    // se elimina un usario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
    // se llama el origen del inicio sesion para que pueda ingresar el usuario
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/iniciarSesion")
    public ResponseEntity<Usuario> iniciarSesion(@RequestBody LoginRequest loginRequest) {
        Usuario usuario = usuarioService.iniciarSesion(loginRequest.getCorreoElectronico(), loginRequest.getContraseña());
        return ResponseEntity.ok(usuario);
    }
    // Se sube la imagen del perfil del Usuario
    @PostMapping("/{id}/imagen")
    public ResponseEntity<String> subirImagen(@PathVariable Integer id, @RequestParam("imagen") MultipartFile imagen) {
        try {
            Usuario usuarioActualizado = usuarioService.subirImagenUsuario(id, imagen);
            return ResponseEntity.ok().body("Imagen actualizada exitosamente: " + usuarioActualizado.getImagen());
        } catch (IOException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

}

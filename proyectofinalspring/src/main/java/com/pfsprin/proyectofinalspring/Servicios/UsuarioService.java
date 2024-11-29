package com.pfsprin.proyectofinalspring.Servicios;

import com.pfsprin.proyectofinalspring.Entidadess.Usuario;
import com.pfsprin.proyectofinalspring.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;


import java.util.List;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    // Obtener un usuario por su ID
    public Usuario obtenerUsuarioPorId(Integer id) {
        return usuarioRepository.findById(Integer.valueOf(id)).orElse(null);
    }

    // Guardar un nuevo usuario
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Actualizar un usuario existente
    public Usuario actualizarUsuario(Integer id, Usuario usuario) {
        Usuario existente = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("usuario no encontrado"));
        if (usuario.getContraseñaUsuario() != null){
            existente.setContraseñaUsuario(passwordEncoder.encode(usuario.getContraseñaUsuario()));
        }
        existente.setNombre(usuario.getNombre());
        existente.setNumeroCelular(usuario.getNumeroCelular());
        return usuarioRepository.save(usuario);
    }

    // Eliminar un usuario por su ID
    public void eliminarUsuario(Integer id) {
        usuarioRepository.deleteById(Integer.valueOf(id));
    }

    // Actualizar el rol de un usuario
    public Usuario actualizarRolUsuario(Integer id, boolean esAdmin) {
        Usuario usuario = obtenerUsuarioPorId(id);
        if (usuario != null) {
            usuario.setEsAdmin(esAdmin);
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    // Obtener todos los usuarios
    public List<Usuario> obtenerTodos() {
        // Retorna todos los usuarios almacenados en la base de datos
        return usuarioRepository.findAll();
    }

    // Obtener un usuario por su ID
    public Usuario obtenerPorId(Integer id) {
        // Verifica si el usuario con el ID proporcionado existe
        Optional<Usuario> usuario = usuarioRepository.findById(Integer.valueOf(id));
        if (usuario.isPresent()) {
            return usuario.get();
        } else {
            return null;
        }
    }

    // Crear un nuevo usuario
    public Usuario crearUsuario(Usuario usuario) {
        if (usuarioRepository.findByCorreoElectronico(usuario.getCorreoElectronico()).isPresent()) {
            throw new IllegalArgumentException("El correo electrónico ya está registrado");
        }
        usuario.setContraseñaUsuario(passwordEncoder.encode(usuario.getContraseñaUsuario())); // Encripta la contraseña antes de guardarla
        return usuarioRepository.save(usuario);
    }

    public Usuario iniciarSesion(String correoElectronico, String contraseña) {
        Usuario usuario = usuarioRepository.findByCorreoElectronico(correoElectronico)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        // Verificamos la contraseña usando BCrypt
        if (passwordEncoder.matches(contraseña, usuario.getContraseñaUsuario())) {
            return usuario;
        } else {
            throw new IllegalArgumentException("Contraseña incorrecta");
        }
    }

    public Usuario subirImagenUsuario(Integer id, MultipartFile imagen) throws IOException {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        // Ruta donde se guardarán las imágenes (asegúrate de que esta carpeta exista)
        String rutaBase = "imagenes";
        File directorio = new File(rutaBase);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        // Guardar la imagen
        String rutaImagen = rutaBase + imagen.getOriginalFilename();
        File archivoImagen = new File(rutaImagen);
        imagen.transferTo(archivoImagen);

        // Actualizar el usuario con la ruta de la imagen
        usuario.setImagen(rutaImagen);
        return usuarioRepository.save(usuario);
    }


}

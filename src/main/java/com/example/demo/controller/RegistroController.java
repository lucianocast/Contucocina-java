package com.example.demo.controller;

import com.example.demo.model.Rol;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistroController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/registro")
    public String mostrarFormularioRegistro() {
        return "registro";
    }

    @PostMapping("/registro")
    public String procesarRegistro(@RequestParam String nombre,
                                    @RequestParam String email,
                                    @RequestParam String contraseña,
                                    @RequestParam(required = false) String telefono,
                                    @RequestParam(required = false) String direccion) {

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setContraseña(contraseña); // En producción: ¡encriptá!
        usuario.setTelefono(telefono);
        usuario.setDireccion(direccion);
        usuario.setRol(Rol.CLIENTE); // Asigna siempre CLIENTE al registrarse

        usuarioRepository.save(usuario);
        return "redirect:/login"; // Redirige al login luego del registro
    }
}

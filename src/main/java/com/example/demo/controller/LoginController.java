package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.model.Rol;
import com.example.demo.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @PostMapping("/login")
public String procesarLogin(@RequestParam String email,
                             @RequestParam String contraseña,
                             Model model,
                             HttpSession session) {

    Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);

    if (usuarioOpt.isPresent()) {
        Usuario usuario = usuarioOpt.get();

        if (usuario.getContraseña().equals(contraseña)) {
            // 👉 Guardar en sesión
            session.setAttribute("usuario", usuario);

            if (usuario.getRol() == Rol.ADMIN) {
                return "redirect:/admin/dashboard"; // Redirige al panel de admin
            } else {
                return "redirect:/cliente/home"; // Redirige al home del cliente
            }
        } else {
            model.addAttribute("error", "Contraseña incorrecta.");
            return "login";
        }
    } else {
        model.addAttribute("error", "Usuario no encontrado.");
        return "login";
    }
}
}

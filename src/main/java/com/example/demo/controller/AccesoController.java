package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class AccesoController {
        @GetMapping("/admin/dashboard")
    public String vistaAdmin() {
        return "admin"; // crear admin.html
    }

    @GetMapping("/cliente/home")
    public String vistaCliente() {
        return "cliente"; // crear cliente.html
    }
}

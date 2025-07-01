package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.model.Producto;
import com.example.demo.service.ProductoService;
import java.util.List;
@Controller
public class HomeController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/")
    public String mostrarInicio(Model model) {
        List<Producto> destacados = productoService.obtenerDestacados();
        model.addAttribute("destacados", destacados);
        return "index";
    }
}

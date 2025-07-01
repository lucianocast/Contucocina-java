package com.example.demo.controller;

import com.example.demo.model.Producto;
import com.example.demo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class VistaProductoController {

    @Autowired
    private ProductoService productoService;
    /**
    @GetMapping("/catalogo")
    public String mostrarCatalogo(Model model) {
        List<Producto> productos = productoService.obtenerProductosVisibles();
        model.addAttribute("productos", productos);
        return "catalogo"; // busca catalogo.html en /templates
    }
    */
}

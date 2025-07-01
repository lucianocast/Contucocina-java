package com.example.demo.controller;

import com.example.demo.model.Producto;
import com.example.demo.model.Usuario;
import com.example.demo.service.ProductoService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties.Http;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CatalogoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/catalogo")
    public String verCatalogo(@RequestParam(value = "categoria", required = false) String categoria, Model model, HttpSession session) {
        List<Producto> productos;

        if (categoria != null && !categoria.isEmpty()) {
            productos = productoService.obtenerProductosPorCategoria(categoria);
        } else {
            productos = productoService.obtenerProductosVisibles();
        }

        model.addAttribute("productos", productos);
        model.addAttribute("categorias", List.of(
                "Tortas Clásicas", "Cheesecakes", "Tartas y Postres", "Personalizadas", "Mesas Dulces", "Desayunos"
        ));
            Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        return "catalogo";
    }
}

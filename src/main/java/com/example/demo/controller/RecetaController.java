package com.example.demo.controller;

import com.example.demo.model.Receta;
import com.example.demo.service.RecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {

    @Autowired
    private RecetaService recetaService;

    @PostMapping
    public Receta guardar(@RequestBody Receta receta) {
        return recetaService.guardarReceta(receta);
    }

    @GetMapping
    public List<Receta> obtenerTodas() {
        return recetaService.listarRecetas();
    }

    @GetMapping("/{id}")
    public Optional<Receta> obtenerPorId(@PathVariable Long id) {
        return recetaService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        recetaService.eliminarReceta(id);
    }
}

package com.example.demo.service;

import com.example.demo.model.Receta;
import com.example.demo.repository.RecetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaService {

    @Autowired
    private RecetaRepository recetaRepository;

    public Receta guardarReceta(Receta receta) {
        return recetaRepository.save(receta);
    }

    public List<Receta> listarRecetas() {
        return recetaRepository.findAll();
    }

    public Optional<Receta> buscarPorId(Long id) {
        return recetaRepository.findById(id);
    }

    public void eliminarReceta(Long id) {
        recetaRepository.deleteById(id);
    }
}

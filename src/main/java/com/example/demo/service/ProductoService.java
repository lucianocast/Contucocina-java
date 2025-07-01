package com.example.demo.service;

import com.example.demo.model.Producto;
import com.example.demo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public List<Producto> listarProductosVisibles() {
        return productoRepository.findByVisibleTrue();
    }

    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
    public List<Producto> obtenerProductosVisibles() {
    return productoRepository.findAll(); // más adelante se puede filtrar por visibilidad
}
    public List<Producto> obtenerProductosPorCategoria(String categoria) {
    return productoRepository.findByCategoriaAndVisibleTrue(categoria);
}
    public List<Producto> obtenerDestacados() {
        return productoRepository.findByDestacadoTrueAndVisibleTrue();
}

}

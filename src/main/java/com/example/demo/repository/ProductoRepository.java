package com.example.demo.repository;

import com.example.demo.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByVisibleTrue();
    List<Producto> findByCategoriaAndVisibleTrue(String categoria);
    List<Producto> findByDestacadoTrueAndVisibleTrue();

}

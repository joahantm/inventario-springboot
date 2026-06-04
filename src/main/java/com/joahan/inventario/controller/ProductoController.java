package com.joahan.inventario.controller;

import com.joahan.inventario.model.Producto;
import com.joahan.inventario.repository.ProductoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoRepository repository;

    public ProductoController(ProductoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Producto> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Producto guardar(@RequestBody Producto producto) {
        return repository.save(producto);
    }
}
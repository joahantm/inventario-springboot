package com.joahan.inventario.controller;

import com.joahan.inventario.model.Producto;
import com.joahan.inventario.service.ProductoService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Producto> listar() {
        return service.listar();
    }

    @PostMapping
    public Producto guardar(@Valid @RequestBody Producto producto) {
        return service.guardar(producto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @PutMapping("/{id}")
    public Producto actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Producto productoActualizado) {

        return service.actualizar(id, productoActualizado);
    }


}
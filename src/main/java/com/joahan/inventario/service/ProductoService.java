package com.joahan.inventario.service;

import com.joahan.inventario.model.Producto;
import com.joahan.inventario.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public List<Producto> listar() {
        return repository.findAll();
    }

    public Producto guardar(Producto producto) {

        if (producto.getPrecio() < 0) {
            throw new RuntimeException("El precio no puede ser negativo");
        }

        if (producto.getStock() < 0) {
            throw new RuntimeException("El stock no puede ser negativo");
        }

        return repository.save(producto);
    }

    public Producto actualizar(Long id, Producto productoActualizado) {

        Producto producto = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Producto no encontrado"));

        if (productoActualizado.getPrecio() < 0) {
            throw new RuntimeException("El precio no puede ser negativo");
        }

        if (productoActualizado.getStock() < 0) {
            throw new RuntimeException("El stock no puede ser negativo");
        }

        producto.setNombre(productoActualizado.getNombre());
        producto.setPrecio(productoActualizado.getPrecio());
        producto.setStock(productoActualizado.getStock());

        return repository.save(producto);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

}

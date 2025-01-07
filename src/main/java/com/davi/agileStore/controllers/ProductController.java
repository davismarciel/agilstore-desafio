package com.davi.agileStore.controllers;

import com.davi.agileStore.dto.ProductDTO;
import com.davi.agileStore.entities.Product;
import com.davi.agileStore.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> index() {
        var product = service.index();

        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@Valid @PathVariable UUID id) {
        var product = service.findById(id);

        return ResponseEntity.ok().body(product);
    }

    @PostMapping
    public ResponseEntity<Product> insert(@Valid @RequestBody Product obj) {
        var product = service.insert(obj);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/")
                .buildAndExpand(obj.getId())
                .toUri();

        return ResponseEntity.created(uri).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@Valid @PathVariable UUID id, @Valid @RequestBody ProductDTO obj) {
        var product = service.update(id, obj);

        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(UUID id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}

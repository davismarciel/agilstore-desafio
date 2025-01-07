package com.davi.agileStore.controllers;

import com.davi.agileStore.entities.Category;
import com.davi.agileStore.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.UUID;

public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> index() {
        var categories = service.index();
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@Valid @PathVariable UUID id) {
        var cat = service.findById(id);
        return ResponseEntity.ok().body(cat);
    }

    @PostMapping
    public ResponseEntity<Category> insert(Category obj) {
        var cat = service.insert(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(cat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@Valid @PathVariable UUID id, Category obj) {
        var cat = service.update(id, obj);
        return ResponseEntity.ok().body(cat);
    }

}

package com.davi.agileStore.controllers;

import com.davi.agileStore.entities.Category;
import com.davi.agileStore.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> index() {
        var categories = service.index();
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@Valid @PathVariable Long id) {
        var cat = service.findById(id);
        return ResponseEntity.ok().body(cat);
    }

    @PostMapping
    public ResponseEntity<Category> insert(@Valid @RequestBody Category obj) {
        var cat = service.insert(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(cat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@Valid @PathVariable Long id, Category obj) {
        var cat = service.update(id, obj);
        return ResponseEntity.ok().body(cat);
    }

}

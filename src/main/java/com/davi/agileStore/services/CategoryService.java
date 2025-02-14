package com.davi.agileStore.services;

import com.davi.agileStore.entities.Category;
import com.davi.agileStore.exceptions.domains.ResourceNotFoundException;
import com.davi.agileStore.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    @Transactional
    public Category insert(Category category) {
        return repository.save(category);
    }

    @Transactional
    public List<Category> index() {
        return repository.findAll();
    }

    @Transactional
    public Category findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    @Transactional
    public Category update(Long id, Category category) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        entity.setName(category.getName());

        return repository.save(entity);
    }
    
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Category not found.");
        }
        repository.deleteById(id);
    }

}

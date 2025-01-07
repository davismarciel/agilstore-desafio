package com.davi.agileStore.services;

import com.davi.agileStore.entities.Category;
import com.davi.agileStore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public Category insert(Category category) {
        return repository.save(category);
    }

    public List<Category> index() {
        return repository.findAll();
    }

    public Category findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found."));
    }

    public Category update(Long id, Category category) {
        try {
            var entity = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            entity.setName(category.getName());

            return repository.save(entity);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Category not found.");
        }
        repository.deleteById(id);
    }

}

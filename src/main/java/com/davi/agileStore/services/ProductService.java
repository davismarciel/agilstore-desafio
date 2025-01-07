package com.davi.agileStore.services;

import com.davi.agileStore.dto.ProductDTO;
import com.davi.agileStore.entities.Category;
import com.davi.agileStore.entities.Product;
import com.davi.agileStore.repositories.CategoryRepository;
import com.davi.agileStore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;


    public Product insert(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.name());
        product.setPrice(dto.price());
        product.setStock(dto.stock());
        product.setDescription(dto.description());

        if (dto.categories() != null && !dto.categories().isEmpty()) {
            List<Category> categories = categoryRepository.findCategoryByName(dto.categories());

            if (categories.size() != dto.categories().size()) {
                throw new RuntimeException("Category not found");
            }

            product.getCategories().addAll(categories);
        }

        return repository.save(product);
    }

    public List<Product> index() {
        return repository.findAll();
    }

    public Product findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found."));
    }

    public Product update(UUID id, ProductDTO obj) {
        try {
            Product entity = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Product not found."));

            updateData(entity, obj);

            return repository.save(entity);
        } catch (RuntimeException e) {
            throw new RuntimeException("There was an error while trying to update the product.");
        }
    }

    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Product not found.");
        }
        repository.deleteById(id);
    }

    private void updateData(Product entity, ProductDTO obj) {
        entity.setName(obj.name());
        entity.setDescription(obj.description());
        entity.setPrice(obj.price());
        entity.setStock(obj.stock());
    }
}

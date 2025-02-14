package com.davi.agileStore.services;

import com.davi.agileStore.dto.ProductDTO;
import com.davi.agileStore.entities.Category;
import com.davi.agileStore.entities.Product;
import com.davi.agileStore.exceptions.domains.ResourceNotFoundException;
import com.davi.agileStore.repositories.CategoryRepository;
import com.davi.agileStore.repositories.ProductRepository;
import com.davi.agileStore.util.SpecificationUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public Product insert(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.name());
        product.setPrice(dto.price());
        product.setStock(dto.stock());
        product.setDescription(dto.description());

        setProductCategories(dto, product);

        return repository.save(product);
    }

    @Transactional
    public List<Product> index() {
        return repository.findAll();
    }

    @Transactional
    public Product findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found."));
    }

    @Transactional
    public Product update(UUID id, ProductDTO obj) {
        Product entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found."));

        updateData(entity, obj);
        setProductCategories(obj, entity);

        return repository.save(entity);
    }

    @Transactional
    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Product not found.");
        }
        repository.deleteById(id);
    }

    public List<Product> findProductsBySpecification(String name, Double price, String category, String sort) {
        Specification<Product> spec = Specification.where(SpecificationUtil.hasName(name))
                .and(SpecificationUtil.hasCategory(category).and(SpecificationUtil.hasPrice(price)));

        if (sort != null) {
            Sort sorting = Sort.by(sort.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, "name");
            return repository.findAll(spec, sorting);
        }

        return repository.findAll(spec);
    }

    private void updateData(Product entity, ProductDTO obj) {
        entity.setName(obj.name());
        entity.setDescription(obj.description());
        entity.setPrice(obj.price());
        entity.setStock(obj.stock());
    }

    private void setProductCategories(ProductDTO dto, Product product) {
        if (dto.categories() != null && !dto.categories().isEmpty()) {
            List<Category> categories = categoryRepository.findCategoryByName(dto.categories());

            if (categories.size() != dto.categories().size()) {
                throw new ResourceNotFoundException("Category not found");
            }

            product.getCategories().clear();
            product.getCategories().addAll(categories);
        }
    }

}

package com.davi.agileStore.config;

import com.davi.agileStore.entities.Category;
import com.davi.agileStore.entities.Product;
import com.davi.agileStore.repositories.CategoryRepository;
import com.davi.agileStore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");
        Category cat4 = new Category(null, "Food");

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));

        Product p1 = new Product(null, "Celular", 200.0, 2, "Lorem ipsur");
        Product p2 = new Product(null, "Pipoca", 300.0, 2, "Lorem ipsur");
        Product p3 = new Product(null, "PC Gamer", 250.0, 3, "Lorem ipsur");
        Product p4 = new Product(null, "Geladeira", 700.0, 4, "Lorem ipsur");

        p1.getCategories().add(cat1);
        p2.getCategories().add(cat4);
        p3.getCategories().add(cat3);
        p4.getCategories().add(cat3);

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4));
    }
}


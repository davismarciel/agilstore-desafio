package com.davi.agileStore.repositories;

import com.davi.agileStore.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Query("SELECT c FROM Category c WHERE c.name IN :name")
    List<Category> findCategoryByName(@Param("name") List<String> names);
}

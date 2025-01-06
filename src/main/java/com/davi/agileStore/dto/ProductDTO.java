package com.davi.agileStore.dto;

import com.davi.agileStore.entities.Category;

public record ProductDTO(String name, Double price, Integer stock, String description, Category category) {
}

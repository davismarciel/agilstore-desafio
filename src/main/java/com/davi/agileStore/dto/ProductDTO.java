package com.davi.agileStore.dto;

import java.util.List;

public record ProductDTO(
        String name, Double price, Integer stock, String description, List<String> categories
) {
}

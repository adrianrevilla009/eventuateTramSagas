package org.example;

import org.example.common.CreateProductRequest;


public class CreateProductSagaData {
    private Long id;
    private String name;
    private String description;
    private Long stock;

    public CreateProductSagaData(CreateProductRequest createProductRequest) {
        this.name = createProductRequest.getName();
        this.description = createProductRequest.getDescription();
        this.stock = createProductRequest.getStock();
    }

    public CreateProductSagaData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }
}

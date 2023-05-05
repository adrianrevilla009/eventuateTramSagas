package org.example;

public class CreateProductResponse {
    private Long stock;

    public CreateProductResponse(Long stock) {
        this.stock = stock;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }
}

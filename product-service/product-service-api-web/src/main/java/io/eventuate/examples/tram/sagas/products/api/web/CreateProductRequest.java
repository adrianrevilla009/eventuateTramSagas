package io.eventuate.examples.tram.sagas.products.api.web;

public class CreateProductRequest {
    private String name;
    private String description;
    private Long stock;

    public CreateProductRequest(String name, String description, Long stock) {
        this.name = name;
        this.description = description;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "CreateProductRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", stock=" + stock +
                '}';
    }
}

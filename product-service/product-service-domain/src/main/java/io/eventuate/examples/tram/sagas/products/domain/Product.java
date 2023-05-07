package io.eventuate.examples.tram.sagas.products.domain;

import javax.persistence.*;

@Entity
@Table(name="products")
@Access(AccessType.FIELD)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Long stock;

    public Product(String name, String description, Long stock) {
        this.name = name;
        this.description = description;
        this.stock = stock;
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

        public static Product createProduct(String name, String description, Long stock) {
        return new Product(name, description, stock);
    }


}


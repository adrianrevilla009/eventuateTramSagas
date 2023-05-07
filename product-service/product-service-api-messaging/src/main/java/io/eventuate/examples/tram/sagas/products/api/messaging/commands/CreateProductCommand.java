package io.eventuate.examples.tram.sagas.products.api.messaging.commands;

import io.eventuate.tram.commands.common.Command;

public class CreateProductCommand implements Command {
    private String name;

    private String description;

    private Long stock;

    public CreateProductCommand(String name, String description, Long stock) {
        this.name = name;
        this.description = description;
        this.stock = stock;
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
}

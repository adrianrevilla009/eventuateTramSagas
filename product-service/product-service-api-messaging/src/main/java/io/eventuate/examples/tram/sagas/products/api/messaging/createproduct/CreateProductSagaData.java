package io.eventuate.examples.tram.sagas.products.api.messaging.createproduct;


public class CreateProductSagaData {

  private Long id;
  private String name;

  private String description;

  private Long stock;

  public CreateProductSagaData(String name, String description, Long stock) {
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
}

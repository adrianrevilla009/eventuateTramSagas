package io.eventuate.examples.tram.sagas.products.api.messaging.commands;

import io.eventuate.examples.tram.sagas.products.domain.Product;
import io.eventuate.tram.commands.common.Command;

import java.util.List;

public class GetProductsCommand implements Command {

    private List<Product> productList;

    public GetProductsCommand(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}

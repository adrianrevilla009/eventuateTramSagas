package io.eventuate.examples.tram.sagas.products.api.web;

import io.eventuate.examples.tram.sagas.products.domain.Product;

import java.util.List;

public class GetProductsRequest {
    private List<Product> productList;

    public GetProductsRequest(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}

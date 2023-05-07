package io.eventuate.examples.tram.sagas.products.api.web;

import io.eventuate.examples.tram.sagas.products.domain.Product;

import java.util.List;

public class GetProductsResponse {
    private List<Product> productList;

    public GetProductsResponse(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}

package com.example.productservice.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long productId) {
        super(String.format("Could not find product with productId {%s}", productId));
    }
}


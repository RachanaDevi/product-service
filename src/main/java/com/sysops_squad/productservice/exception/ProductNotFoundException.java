package com.sysops_squad.productservice.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Long productId) {
        super(String.format("Product with id:%s not found", productId));
    }
}

package com.example.exammd3_codegym_0810.service;

import com.example.exammd3_codegym_0810.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {
    void addProduct(Product product) throws SQLException;
    Product selectProduct(int productId) throws SQLException;
    List<Product> selectAllProducts() throws SQLException;
    boolean updateProduct(Product product) throws SQLException;
    boolean deleteProduct(int productId) throws SQLException;
}

package com.example.exammd3_codegym_0810.service;

import com.example.exammd3_codegym_0810.DBconnect.JDBCUtils;
import com.example.exammd3_codegym_0810.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private static final String INSERT_PRODUCTS_SQL =
            "INSERT INTO product (name, price, quantity,color,description, category) VALUES (?, ?, ?, ?, ?,?)";
    private static final String SELECT_PRODUCTS_SQL =
            "SELECT * FROM product WHERE id = ?";
    private static final String SELECT_ALL_PRODUCTS_SQL =
            "SELECT * FROM product";
    private static final String DELETE_PRODUCTS_SQL =
            "DELETE FROM product WHERE id = ?";
    private static final String UPDATE_PRODUCTS_SQL =
            "UPDATE product SET name = ?, price = ?,quantity =?,  color= ?, description = ?, category = ? WHERE id = ?";
    private static final String SEARCH_PRODUCTS_SQL =
            "SELECT * FROM product WHERE 1=1";
    @Override
    public void addProduct(Product product) throws SQLException {
        try(Connection conn = JDBCUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(INSERT_PRODUCTS_SQL);){
            ps.setString(1,product.getProductName());
            ps.setDouble(2,product.getPrice());
            ps.setInt(3,product.getQuantity());
            ps.setString(4,product.getColor());
            ps.setString(5,product.getDescription());
            ps.setString(6,product.getCategory());
            System.out.println(ps);
            ps.executeUpdate();
        }catch(SQLException e){
            JDBCUtils.printSQLException(e);
        }
    }

    @Override
    public Product selectProduct(int productId) throws SQLException {
        Product product = null;
        try(Connection conn = JDBCUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT_PRODUCTS_SQL);){
            ps.setInt(1,productId);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String description = rs.getString("description");
                String category = rs.getString("category");
                product = new Product(id,name,price,quantity,color, description,category);
            }
        }catch(SQLException e){
            JDBCUtils.printSQLException(e);
        }
        return product;
    }

    @Override
    public List<Product> selectAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        try(Connection conn = JDBCUtils.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT_ALL_PRODUCTS_SQL);){
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String description = rs.getString("description");
                String category = rs.getString("category");
                products.add(new Product(id,name,price,quantity,color, description,category));
            }
        }catch(SQLException e){
            JDBCUtils.printSQLException(e);
        }
        return products;
    }

    @Override
    public boolean updateProduct(Product product) throws SQLException {
        boolean rowUpdated;
        try(Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(UPDATE_PRODUCTS_SQL);){
            ps.setString(1,product.getProductName());
            ps.setDouble(2,product.getPrice());
            ps.setInt(3,product.getQuantity());
            ps.setString(4,product.getColor());
            ps.setString(5,product.getDescription());
            ps.setString(6,product.getCategory());
            ps.setInt(7,product.getProductId());
            System.out.println(ps);
            rowUpdated = ps.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    @Override
    public boolean deleteProduct(int productId) throws SQLException {
        boolean rowDeleted;
        try(Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(DELETE_PRODUCTS_SQL);){
            ps.setInt(1,productId);
            System.out.println(ps);
            rowDeleted = ps.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    @Override
    public List<Product> searchProduct(String name, Double price, String category, String color)
            throws SQLException {
        List<Product> products = new ArrayList<>();
        StringBuilder sqlBuilder = new StringBuilder(SEARCH_PRODUCTS_SQL);
        List<Object> params = new ArrayList<>();

        if(name != null && !name.trim().isEmpty()){
            sqlBuilder.append(" AND name LIKE ?");
            params.add("%"+name+"%");
        }
        if(price != null){
            sqlBuilder.append(" AND price = ?");
            params.add(price);
        }
        if(category != null && !category.trim().isEmpty()){
            sqlBuilder.append(" AND category LIKE ?");
            params.add("%" + category+"%");
        }
        if(color != null && !color.trim().isEmpty()){
            sqlBuilder.append(" AND color LIKE ?");
            params.add("%" + color + "%");
        }
        try(Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sqlBuilder.toString())){
            for(int i = 0; i < params.size(); i++){
                ps.setObject(i + 1, params.get(i));
            }
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String productName = rs.getString("name");
                double productPrice = rs.getDouble("price");
                int productQuantity = rs.getInt("quantity");
                String productColor = rs.getString("color");
                String productDescription = rs.getString("description");
                String productCategory = rs.getString("category");
                products.add(new Product(id,productName,productPrice,productQuantity,productColor, productDescription,productCategory));
            }
        }catch(SQLException e){
            JDBCUtils.printSQLException(e);
        }

        return products;
    }
}

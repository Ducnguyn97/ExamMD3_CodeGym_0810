package com.example.exammd3_codegym_0810.controller;

import com.example.exammd3_codegym_0810.DBconnect.JDBCUtils;
import com.example.exammd3_codegym_0810.model.Product;
import com.example.exammd3_codegym_0810.service.ProductDAO;
import com.example.exammd3_codegym_0810.service.ProductDAOImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductController", urlPatterns = "/products")
public class ProductController extends HttpServlet {
    private ProductDAOImpl productDAOImpl;

    @Override
    public void init() {
        productDAOImpl = new ProductDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                  showNewForm(request, response);
                    break;
                case "edit":
                    showFormEdit(request, response);
                    break;
                case "delete":
                   deleteProduct(request,response);
                    break;
                default:
                   listProducts(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    createProduct(request, response);
                    break;
                case "edit":
                    editProduct(request, response);
                    break;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String name = request.getParameter("name");
        Double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        Product product = new Product(name, price, quantity, color, description, category);
        productDAOImpl.addProduct(product);
        response.sendRedirect(request.getContextPath() + "/products");
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int productId = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        Product product = new Product(productId, name, price, quantity, color, description, category);
        productDAOImpl.updateProduct(product);
        response.sendRedirect(request.getContextPath() + "/products");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/product/create.jsp");
        rd.forward(request, response);
    }

    private void showFormEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int productId = Integer.parseInt(request.getParameter("id"));
        Product existingProduct = productDAOImpl.selectProduct(productId);
        request.setAttribute("existingProduct", existingProduct);
        RequestDispatcher rd = request.getRequestDispatcher("/product/edit.jsp");
        rd.forward(request, response);
    }
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        int productId = Integer.parseInt(request.getParameter("id"));
       productDAOImpl.deleteProduct(productId);
       response.sendRedirect(request.getContextPath() + "/products");
    }
    private void listProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        List<Product> products = productDAOImpl.selectAllProducts();
        System.out.println("Number of products retrieved: " + products.size());
        for (Product product : products) {
            System.out.println("Product: " + product.getProductName() + ", ID: " + product.getProductId());
        }
        request.setAttribute("products", products);
        RequestDispatcher rd = request.getRequestDispatcher("/product/list.jsp");
        rd.forward(request, response);
    }
}

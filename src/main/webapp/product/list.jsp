<%--
  Created by IntelliJ IDEA.
  User: DUKEI
  Date: 10/8/2025
  Time: 10:01 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .search-form {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <h1 class="text-center mb-4">Management Product</h1>

    <!-- Search Form -->
    <div class="search-form">
        <h5>Find Product</h5>
        <form action="/products" method="get" class="row g-3">
            <div class="col-md-3">
                <label class="form-label">Product Name:</label>
                <input type="text" class="form-control" name="searchName" placeholder="Enter Product Name">
            </div>
            <div class="col-md-2">
                <label class="form-label">Price:</label>
                <input type="number" class="form-control" name="searchPrice" placeholder="Enter Price">
            </div>
            <div class="col-md-2">
                <label class="form-label">Category:</label>
                <input type="text" class="form-control" name="searchCategory" placeholder="Enter Category">
            </div>
            <div class="col-md-2">
                <label class="form-label">Color:</label>
                <input type="text" class="form-control" name="searchColor" placeholder="Enter Color">
            </div>
            <div class="col-md-3 d-flex align-items-end">
                <button type="submit" class="btn btn-primary me-2">Search</button>
                <button type="reset" class="btn btn-secondary">Clear</button>
            </div>
        </form>
    </div>

    <!-- Product Table -->
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h4>Product List</h4>
        <a href="/products?action=create" class="btn btn-success">Add New Product</a>
    </div>

    <div class="table-responsive">
        <table class="table table-striped table-bordered">
            <thead class="table-dark">
            <tr>
                <th>STT</th>
                <th>Product Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Color</th>
                <th>Category</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="product" items="${products}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${product.productName}</td>
                    <td>$${product.price}</td>
                    <td>${product.quantity}</td>
                    <td>${product.color}</td>
                    <td>${product.category}</td>
                    <td>
                        <a href="/products?action=edit&id=${product.productId}" class="btn btn-warning btn-sm">Edit</a>
                        <a href="/products?action=delete&id=${product.productId}"
                           class="btn btn-danger btn-sm"
                           onclick="return confirm('Are you sure you want to delete this product?')">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
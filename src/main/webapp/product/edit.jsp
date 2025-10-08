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
    <title>Edit Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <h1 class="text-center mb-4">Edit Product</h1>

            <form action="/products?action=edit" method="post">
                <input type="hidden" name="id" value="${existingProduct.productId}">

                <div class="mb-3">
                    <label class="form-label">Name</label>
                    <input type="text" class="form-control" name="name" value="${existingProduct.productName}" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Price</label>
                    <input type="number" step="0.01" class="form-control" name="price" value="${existingProduct.price}" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Quantity</label>
                    <input type="number" class="form-control" name="quantity" value="${existingProduct.quantity}" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Color</label>
                    <input type="text" class="form-control" name="color" value="${existingProduct.color}" required>
                </div>

                <div class="mb-3">
                    <label class="form-label">Description</label>
                    <textarea class="form-control" name="description" rows="3">${existingProduct.description}</textarea>
                </div>

                <div class="mb-3">
                    <label class="form-label">Category</label>
                    <select class="form-select" name="category" required>
                        <option value="Phone" ${existingProduct.category == 'Phone' ? 'selected' : ''}>Phone</option>
                        <option value="Tivi" ${existingProduct.category == 'Tivi' ? 'selected' : ''}>Tivi</option>
                        <option value="Tủ lạnh" ${existingProduct.category == 'Tủ lạnh' ? 'selected' : ''}>Tủ lạnh</option>
                        <option value="Máy giặt" ${existingProduct.category == 'Máy giặt' ? 'selected' : ''}>Máy giặt</option>
                        <option value="Computer" ${existingProduct.category == 'Computer' ? 'selected' : ''}>Computer</option>
                    </select>
                </div>

                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                    <a href="/products" class="btn btn-secondary me-md-2">Back</a>
                    <button type="submit" class="btn btn-primary">Update</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<title>${product.title}</title>
</head>
<body>
	<div class="row">
		<div class="col-md-6">
			<img src="https://via.placeholder.com/500x500" class="img-fluid"
				alt="${product.title}">
		</div>
		<div class="col-md-6">
			<h2>${product.title}</h2>
			<p>
				<strong>Danh mục:</strong> ${product.category.categoryName}
			</p>
			<p>${product.description}</p>
			<h3 class="text-danger">${String.format("%,.0f", product.price)}đ</h3>
			<div class="mt-4">
				<button class="btn btn-primary btn-lg">Thêm vào giỏ hàng</button>
			</div>
		</div>
	</div>
</body>
</html>
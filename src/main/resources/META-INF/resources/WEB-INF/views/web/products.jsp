<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<title>Danh sách Sản phẩm</title>
</head>
<body>
	<div class="row">
		<div class="col-md-3">
			<h3>Danh mục</h3>
			<div class="list-group">
				<a href="<c:url value='/products' />"
					class="list-group-item list-group-item-action active"> Tất cả
					sản phẩm </a>
				<c:forEach items="${categories}" var="category">
					<a href="<c:url value='/products/category/${category.id}' />"
						class="list-group-item list-group-item-action">
						${category.categoryName} </a>
				</c:forEach>
			</div>
		</div>

		<div class="col-md-9">
			<h2>Tất cả sản phẩm</h2>
			<div class="row">
				<c:if test="${empty products}">
					<p class="text-center">Không tìm thấy sản phẩm nào.</p>
				</c:if>

				<c:forEach items="${products}" var="product">
					<div class="col-md-4 mb-4">
						<div class="card h-100">
							<c:choose>
								<c:when test="${not empty product.image}">
									<img src="<c:url value='/uploads/${product.image}' />"
										class="card-img-top" alt="${product.title}"
										style="height: 200px; object-fit: cover;">
								</c:when>
								<c:otherwise>
									<img src="https://via.placeholder.com/300x200"
										class="card-img-top" alt="No Image Available">
								</c:otherwise>
							</c:choose>

							<div class="card-body d-flex flex-column">
								<h5 class="card-title">${product.title}</h5>
								<h6 class="card-subtitle mt-auto mb-2 text-muted">${String.format("%,.0f", product.price)}đ</h6>
							</div>
							<div class="card-footer text-center">
								<a href="#" class="btn btn-primary">Thêm vào giỏ</a> <a
									href="<c:url value='/product/${product.id}' />"
									class="btn btn-secondary">Xem chi tiết</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<title>Trang chủ</title>
</head>
<body>

	<div class="p-5 mb-4 bg-light rounded-3">
		<div class="container-fluid py-5">
			<h1 class="display-5 fw-bold">Chào mừng đến với MyShop</h1>
			<p class="col-md-8 fs-4">Nơi bạn có thể tìm thấy mọi sản phẩm
				tuyệt vời với chất lượng hàng đầu và giá cả phải chăng.</p>
			<a class="btn btn-primary btn-lg" href="<c:url value='/products' />">Xem
				ngay</a>
		</div>
	</div>

	<div class="row">
		<h2 class="mb-4">Sản phẩm nổi bật</h2>

		<c:if test="${empty products}">
			<p class="text-center">Hiện chưa có sản phẩm nào.</p>
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
						<p class="card-text">${product.description}</p>
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

</body>
</html>
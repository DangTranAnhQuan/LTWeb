<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Awesome Shop</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

<%-- <link rel="stylesheet" href="<c:url value='/css/style.css' />"> --%>
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<div class="container">
				<a class="navbar-brand" href="<c:url value='/' />"> <i
					class="fas fa-shopping-cart"></i> MyShop
				</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNav">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav ms-auto">
						<li class="nav-item"><a class="nav-link active"
							href="<c:url value='/' />">Trang chủ</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/products' />">Sản phẩm</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/about' />">Giới thiệu</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<c:url value='/contact' />">Liên hệ</a></li>
						<li class="nav-item"><a class="btn btn-primary ms-lg-2"
							href="<c:url value='/login' />">Đăng nhập</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</header>

	<main class="container my-4">
		<%-- Đây là nơi nội dung của các trang con (home.jsp, search.jsp) sẽ được chèn vào --%>
		<decorator:body />
	</main>

	<footer class="bg-dark text-white text-center p-4 mt-auto">
		<div class="container">
			<p>&copy; 2025 My Awesome Shop. All Rights Reserved.</p>
		</div>
	</footer>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
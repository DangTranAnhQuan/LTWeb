<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product List</title>
</head>
<body>
	<section class="row">
		<div class="col mt-4">
			<div class="card">
				<div class="card-header">
					<div class="d-flex justify-content-between align-items-center">
						<h2>List Products</h2>
						<form action="<c:url value='/admin/products/searchpaginated' />"
							method="get" class="d-flex" style="max-width: 400px;">
							<input type="text" class="form-control me-2" name="title"
								placeholder="Search by title" value="${title}">
							<button class="btn btn-primary">Search</button>
						</form>
					</div>
				</div>
				<div class="card-body">
					<c:if test="${message != null}">
						<div class="alert alert-success">${message}</div>
					</c:if>

					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>ID</th>
								<th>Title</th>
								<th>Quantity</th>
								<th>Price</th>
								<th>Category</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${productPage.content}" var="product">
								<tr>
									<td>${product.id}</td>
									<td>${product.title}</td>
									<td>${product.quantity}</td>
									<td>${String.format("%.2f", product.price)}</td>
									<td>${product.category.categoryName}</td>
									<td><a
										href="<c:url value='/admin/products/edit/${product.id}' />"
										class="btn btn-outline-warning btn-sm">Edit</a> <a
										href="<c:url value='/admin/products/delete/${product.id}' />"
										class="btn btn-outline-danger btn-sm"
										onclick="return confirm('Are you sure you want to delete this product?')">Delete</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="card-footer text-muted">
					<nav>
						<ul class="pagination justify-content-center">
							<li class="page-item ${productPage.first ? 'disabled' : ''}">
								<a class="page-link"
								href="?page=${productPage.number}&size=${productPage.size}&title=${title}">Previous</a>
							</li>
							<c:forEach items="${pageNumbers}" var="pageNumber">
								<li
									class="page-item ${pageNumber == productPage.number + 1 ? 'active' : ''}">
									<a class="page-link"
									href="?page=${pageNumber}&size=${productPage.size}&title=${title}">${pageNumber}</a>
								</li>
							</c:forEach>
							<li class="page-item ${productPage.last ? 'disabled' : ''}">
								<a class="page-link"
								href="?page=${productPage.number + 2}&size=${productPage.size}&title=${title}">Next</a>
							</li>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</section>
</body>
</html>
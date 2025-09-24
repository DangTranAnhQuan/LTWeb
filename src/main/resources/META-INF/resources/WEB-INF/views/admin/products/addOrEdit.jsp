<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<title>${product.isEdit ? 'Edit Product' : 'Add New Product'}</title>
</head>
<body>
	<section class="row">
		<div class="col-8 offset-2 mt-4">
			<%-- QUAN TRỌNG: Thêm enctype="multipart/form-data" để cho phép upload file --%>
			<form:form action="/admin/products/saveOrUpdate" method="POST"
				modelAttribute="product" enctype="multipart/form-data">
				<div class="card">
					<div class="card-header">
						<h2>${product.isEdit ? 'Edit Product' : 'Add New Product'}</h2>
					</div>
					<div class="card-body">
						<form:hidden path="id" />
						<form:hidden path="isEdit" />
						<form:hidden path="image" />
						<%-- Giữ lại tên ảnh cũ khi submit form --%>

						<%-- ... Các trường title, quantity, price ... --%>
						<div class="mb-3">
							<label for="title" class="form-label">Product Title:</label>
							<form:input path="title" class="form-control"
								placeholder="Enter Product Title" />
							<form:errors path="title" cssClass="text-danger" />
						</div>

						<div class="row">
							<div class="col-6 mb-3">
								<label for="quantity" class="form-label">Quantity:</label>
								<form:input type="number" path="quantity" class="form-control" />
								<form:errors path="quantity" cssClass="text-danger" />
							</div>
							<div class="col-6 mb-3">
								<label for="price" class="form-label">Price:</label>
								<form:input type="number" step="0.01" path="price"
									class="form-control" />
								<form:errors path="price" cssClass="text-danger" />
							</div>
						</div>

						<%-- === PHẦN THÊM MỚI CHO UPLOAD ẢNH === --%>
						<div class="mb-3">
							<label for="imageFile" class="form-label">Product Image:</label>
							<form:input type="file" path="imageFile" class="form-control" />
						</div>

						<%-- Hiển thị ảnh cũ nếu đang ở chế độ edit --%>
						<c:if test="${product.isEdit && not empty product.image}">
							<div class="mb-3">
								<label>Current Image:</label><br> <img
									src="<c:url value='/uploads/${product.image}' />"
									alt="Current Image" width="150">
							</div>
						</c:if>
						<%-- === KẾT THÚC PHẦN THÊM MỚI === --%>

						<%-- ... Các trường category, user, description ... --%>
						<div class="mb-3">
							<label for="categoryId" class="form-label">Category:</label>
							<form:select path="categoryId" class="form-select">
								<form:option value="">-- Select Category --</form:option>
								<form:options items="${categories}" itemValue="id"
									itemLabel="categoryName" />
							</form:select>
							<form:errors path="categoryId" cssClass="text-danger" />
						</div>

						<div class="mb-3">
							<label for="userId" class="form-label">Created By (User):</label>
							<form:select path="userId" class="form-select">
								<form:option value="">-- Select User --</form:option>
								<form:options items="${users}" itemValue="id"
									itemLabel="username" />
							</form:select>
							<form:errors path="userId" cssClass="text-danger" />
						</div>

						<div class="mb-3">
							<label for="description" class="form-label">Description:</label>
							<form:textarea path="description" rows="3" class="form-control" />
						</div>
					</div>
					<div class="card-footer text-muted">
						<%-- ... Các nút button ... --%>
						<a href="<c:url value="/admin/products/add"/>"
							class="btn btn-secondary">New</a> <a
							href="<c:url value="/admin/products" />" class="btn btn-success">List
							Products</a>
						<button class="btn btn-primary" type="submit">
							<i class="fas fa-save"></i> Save
						</button>
					</div>
				</div>
			</form:form>
		</div>
	</section>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${user.isEdit ? 'Edit User' : 'Add New User'}</title>
</head>
<body>
	<section class="row">
		<div class="col-8 offset-2 mt-4">
			<%-- Sử dụng Spring Form tag để dễ dàng hiển thị lỗi validation --%>
			<form:form action="/admin/users/saveOrUpdate" method="POST"
				modelAttribute="user">
				<div class="card">
					<div class="card-header">
						<%-- Tiêu đề thay đổi tùy theo trạng thái edit hay add new --%>
						<h2>${user.isEdit ? 'Edit User' : 'Add New User'}</h2>
					</div>
					<div class="card-body">
						<%-- Truyền vào id của user nhưng ẩn đi --%>
						<form:hidden path="id" />
						<form:hidden path="isEdit" />

						<div class="mb-3">
							<label for="username" class="form-label">Username:</label>
							<form:input path="username" class="form-control"
								placeholder="Enter Username" />
							<form:errors path="username" cssClass="text-danger" />
						</div>

						<div class="mb-3">
							<label for="fullName" class="form-label">Full Name:</label>
							<form:input path="fullName" class="form-control"
								placeholder="Enter Full Name" />
							<form:errors path="fullName" cssClass="text-danger" />
						</div>

						<div class="mb-3">
							<label for="password" class="form-label">Password:</label>
							<form:password path="password" class="form-control" />
							<c:if test="${user.isEdit}">
								<small class="form-text text-muted">Leave blank to keep
									the current password.</small>
							</c:if>
							<form:errors path="password" cssClass="text-danger" />
						</div>

					</div>
					<div class="card-footer text-muted">
						<a href="<c:url value="/admin/users/add"/>"
							class="btn btn-secondary">New</a> <a
							href="<c:url value="/admin/users" />" class="btn btn-success">List
							Users</a>
						<button class="btn btn-primary" type="submit">
							<i class="fas fa-save"></i> <span>Save</span>
						</button>
					</div>
				</div>
			</form:form>
		</div>
	</section>
</body>
</html>
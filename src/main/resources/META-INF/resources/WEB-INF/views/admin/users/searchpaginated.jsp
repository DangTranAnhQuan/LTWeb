<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User List</title>
</head>
<body>
	<section class="row">
		<div class="col mt-4">
			<div class="card">
				<div class="card-header">
					<div class="row">
						<div class="col-md-6">
							<h2>List Users</h2>
						</div>
						<div class="col-md-6">
							<%-- Form tìm kiếm --%>
							<form action="<c:url value='/admin/users/searchpaginated' />"
								method="get">
								<div class="input-group">
									<input type="text" class="form-control" name="username"
										placeholder="Search by username" value="${username}">
									<button class="btn btn-primary">Search</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="card-body">
					<%-- Hiển thị thông báo (thêm, sửa, xóa thành công) --%>
					<c:if test="${message != null}">
						<div class="alert alert-success" role="alert">
							<i>${message}</i>
						</div>
					</c:if>

					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>User ID</th>
								<th>Username</th>
								<th>Full Name</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${userPage.content}" var="user">
								<tr>
									<td>${user.id}</td>
									<td>${user.username}</td>
									<td>${user.fullName}</td>
									<td><a
										href="<c:url value='/admin/users/edit/${user.id}' />"
										class="btn btn-outline-warning btn-sm">Edit</a> <a
										href="<c:url value='/admin/users/delete/${user.id}' />"
										class="btn btn-outline-danger btn-sm"
										onclick="return confirm('Are you sure you want to delete this user?')">Delete</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="card-footer text-muted">
					<%-- Thanh phân trang --%>
					<nav aria-label="Page navigation">
						<ul class="pagination justify-content-center">
							<%-- Nút Previous --%>
							<li class="page-item ${userPage.first ? 'disabled' : ''}"><a
								class="page-link"
								href="?page=${userPage.number}&size=${userPage.size}&username=${username}">Previous</a>
							</li>

							<%-- Các trang số --%>
							<c:forEach items="${pageNumbers}" var="pageNumber">
								<li
									class="page-item ${pageNumber == userPage.number + 1 ? 'active' : ''}">
									<a class="page-link"
									href="?page=${pageNumber}&size=${userPage.size}&username=${username}">${pageNumber}</a>
								</li>
							</c:forEach>

							<%-- Nút Next --%>
							<li class="page-item ${userPage.last ? 'disabled' : ''}"><a
								class="page-link"
								href="?page=${userPage.number + 2}&size=${userPage.size}&username=${username}">Next</a>
							</li>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</section>
</body>
</html>
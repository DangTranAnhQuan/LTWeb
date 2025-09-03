<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%
if ("1".equals(request.getParameter("logout"))) {
	jakarta.servlet.http.HttpSession s = request.getSession(false);
	if (s != null)
		s.invalidate();
	jakarta.servlet.http.Cookie ck = new jakarta.servlet.http.Cookie("username", "");
	ck.setMaxAge(0);
	ck.setPath(request.getContextPath());
	response.addCookie(ck);
	response.sendRedirect(request.getContextPath() + "/view/login.jsp");
	return;
}
%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Quản lý danh mục</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style>
body {
	background: #f0f6ff
}

.sidebar {
	width: 230px;
	background: #0284ff;
	color: #fff;
	position: fixed;
	top: 0;
	bottom: 0;
	left: 0
}

.sidebar .brand {
	font-size: 22px;
	padding: 18px 20px;
	background: #0072df;
	margin: 0
}

.sidebar .menu {
	padding: 10px 0
}

.sidebar .menu a {
	display: block;
	color: #fff;
	padding: 12px 20px;
	text-decoration: none
}

.sidebar .menu a.active, .sidebar .menu a:hover {
	background: #0065c3
}

.content {
	margin-left: 230px
}

.topbar {
	background: #0ea5ff;
	color: #fff;
	padding: 12px 16px
}

.panel-heading h3 {
	margin: 8px 0 0
}

.action a {
	margin: 0 6px
}

.form-inline .form-group {
	margin-right: 10px
}
</style>
</head>
<body>

	<!-- SIDEBAR -->
	<div class="sidebar">
		<h1 class="brand">Dashboard</h1>
		<div class="text-center" style="padding: 18px">
			<div
				style="width: 90px; height: 90px; border-radius: 50%; background: #fff; margin: 0 auto 8px"></div>
			<div>Ban là Admin</div>
		</div>
		<div class="menu">
			<a class="active" href="#">Quản lý Danh mục</a> <a href="#">Thêm
				danh mục mới</a> <a href="#">Danh sách danh mục</a> <a href="#">Quản
				lý sản phẩm</a> <a href="#">Quản lý tài khoản</a>
		</div>
	</div>

	<!-- CONTENT -->
	<div class="content">
		<!-- TOPBAR -->
		<div class="topbar clearfix">
			<div class="pull-left">
				Xin chào <strong> <c:out
						value="${sessionScope.account.fullName != null ? sessionScope.account.fullName : 'Người dùng'}" />
				</strong>
			</div>
			<div class="pull-right">
				<a class="btn btn-danger btn-xs"
					href="<c:url value='/view/home.jsp?logout=1'/>">Đăng xuất</a>
			</div>
		</div>

		<div class="container-fluid" style="padding: 18px">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3>Quản lý danh mục</h3>
					<small>Nơi bạn có thể quản lý danh mục của mình</small>
				</div>
				<div class="panel-body">

					<!-- thanh công cụ nhỏ -->
					<div class="row" style="margin-bottom: 12px">
						<div class="col-sm-6">
							<div class="form-inline">
								<label>records per page</label> <select
									class="form-control input-sm">
									<option>10</option>
									<option>25</option>
									<option>50</option>
								</select>
							</div>
						</div>
						<div class="col-sm-6 text-right">
							<div class="form-inline">
								<label>Search:</label> <input class="form-control input-sm"
									placeholder="Nhập từ khóa...">
							</div>
						</div>
					</div>

					<!-- BẢNG DANH MỤC -->
					<table class="table table-bordered table-striped">
						<thead>
							<tr>
								<th style="width: 70px">STT</th>
								<th style="width: 220px">Icon</th>
								<th>Tên danh mục</th>
								<th style="width: 160px">Hành động</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${cateList}" var="cate" varStatus="st">
								<tr>
									<td>${st.index + 1}</td>
									<td><code>${cate.icon}</code></td>
									<td>${cate.name}</td>
									<td class="action"><a
										href="<c:url value='/admin/category/edit?id=${cate.id}'/>">Sửa</a>
										| <a
										href="<c:url value='/admin/category/delete?id=${cate.id}'/>"
										onclick="return confirm('Xóa danh mục này?')">Xóa</a></td>
								</tr>
							</c:forEach>
							<c:if test="${empty cateList}">
								<tr>
									<td colspan="4" class="text-center text-muted">Chưa có
										danh mục.</td>
								</tr>
							</c:if>
						</tbody>
					</table>

					<!-- FORM THÊM NHANH -->
					<hr>
					<h4>Thêm danh mục</h4>
					<form class="form-inline"
						action="<c:url value='/admin/category/add'/>" method="post">
						<div class="form-group">
							<label class="sr-only">Tên danh mục</label> <input type="text"
								class="form-control" name="name" placeholder="Tên danh mục"
								required>
						</div>
						<div class="form-group">
							<label class="sr-only">Icon</label> <input type="text"
								class="form-control" name="icon"
								placeholder="fa-mobile / fa-book ...">
						</div>
						<button type="submit" class="btn btn-primary">Thêm</button>
					</form>

				</div>
			</div>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</body>
</html>

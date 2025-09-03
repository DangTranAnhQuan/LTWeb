<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Quên mật khẩu</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style>
body {
	background: #f7f7f7;
}

.card {
	max-width: 520px;
	margin: 40px auto;
	background: #fff;
	border: 1px solid #eee;
	border-radius: 6px;
	padding: 25px 30px
}

.title {
	margin: 0 0 18px;
	text-align: center;
	color: #666
}

.btn-full {
	width: 100%
}
</style>
</head>
<body>
	<div class="card">
		<h3 class="title">Quên mật khẩu</h3>

		<!-- Thông báo -->
		<c:if test="${not empty alert}">
			<div class="alert alert-danger">${alert}</div>
		</c:if>
		<c:if test="${not empty success}">
			<div class="alert alert-success">${success}</div>
		</c:if>

		<c:if test="${empty emailOk}">
			<form action="<c:url value='/forgot'/>" method="post"
				autocomplete="off">
				<div class="form-group">
					<label>Email đã đăng ký</label> <input type="email"
						class="form-control" name="email" value="${param.email}" required
						placeholder="nhapemail@domain.com">
				</div>
				<button type="submit" class="btn btn-primary btn-full">Xác
					nhận email</button>
			</form>
		</c:if>

		<c:if test="${emailOk}">
			<form action="<c:url value='/forgot'/>" method="post"
				autocomplete="off">
				<input type="hidden" name="email" value="${email}">
				<div class="form-group">
					<label>Mật khẩu mới</label> <input type="password"
						class="form-control" name="password" required>
				</div>
				<div class="form-group">
					<label>Nhập lại mật khẩu</label> <input type="password"
						class="form-control" name="confirm" required>
				</div>
				<button type="submit" class="btn btn-success btn-full">Lưu
					mật khẩu mới</button>
			</form>
		</c:if>

		<hr>
		<a href="${pageContext.request.contextPath}/view/login.jsp">Quay
			về đăng nhập</a>
	</div>
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Đăng ký</title>

<!-- Bootstrap 3 + Font Awesome 4 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
body {
	background: #f7f7f7;
}

.card {
	max-width: 520px;
	margin: 50px auto;
	background: #fff;
	border: 1px solid #eee;
	border-radius: 6px;
	box-shadow: 0 2px 10px rgba(0, 0, 0, .04);
	padding: 25px 30px 30px;
}

.title {
	text-align: center;
	margin: 5px 0 20px;
	color: #6a6a6a;
}

.btn-full {
	width: 100%;
}

.footer {
	text-align: center;
	margin-top: 18px;
	color: #777;
}

.footer a {
	font-weight: 600;
}
</style>
</head>
<body>

	<div class="card">
		<h3 class="title">Tạo tài khoản mới</h3>

		<c:if test="${not empty alert}">
			<div class="alert alert-danger" role="alert"
				style="margin-bottom: 18px;">${alert}</div>
		</c:if>

		<form action="<c:url value='/register'/>" method="post"
			autocomplete="off">
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-user"></i></span> <input
						type="text" class="form-control" name="username"
						placeholder="Tài khoản" required>
				</div>
			</div>

			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-user"></i></span> <input
						type="text" class="form-control" name="fullName"
						placeholder="Họ tên" required>
				</div>
			</div>

			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-envelope"></i></span>
					<input type="email" class="form-control" name="email"
						placeholder="Nhập Email" required>
				</div>
			</div>

			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-phone"></i></span>
					<input type="text" class="form-control" name="phone"
						placeholder="Số điện thoại">
				</div>
			</div>

			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-lock"></i></span> <input
						type="password" class="form-control" name="password"
						placeholder="Mật khẩu" required>
				</div>
			</div>

			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon"><i class="fa fa-lock"></i></span> <input
						type="password" class="form-control" name="confirmPassword"
						placeholder="Nhập lại mật khẩu" required>
				</div>
			</div>

			<button type="submit" class="btn btn-primary btn-full">Tạo
				tài khoản</button>

			<div class="footer">
				Nếu bạn đã có tài khoản, hãy <a
					href="<c:url value='view/login.jsp'/>">Đăng nhập</a>
			</div>

		</form>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</body>
</html>

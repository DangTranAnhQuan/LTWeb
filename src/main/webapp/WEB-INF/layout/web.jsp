<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title><c:out
		value="${requestScope.pageTitle != null ? requestScope.pageTitle : 'Trang chủ'}" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style>
body {
	background: #f7f9fc
}

.navbar-custom {
	background: #0ea5ff;
	border-color: #0ea5ff
}

.navbar-custom .navbar-brand, .navbar-custom .navbar-nav>li>a {
	color: #fff
}

.container-page {
	padding: 20px 15px
}

footer {
	padding: 18px 0;
	color: #666;
	border-top: 1px solid #e9eef5;
	margin-top: 28px
}

.nav-avatar {
	width: 26px;
	height: 26px;
	border-radius: 50%;
	object-fit: cover;
	margin-right: 6px;
	vertical-align: middle;
	background: #fff;
}
</style>
<c:if test="${not empty headExtras}">${headExtras}</c:if>
</head>
<body>
	<c:set var="ctx" value="${pageContext.request.contextPath}" />

	<nav class="navbar navbar-custom navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="${ctx}/home">MySite</a>
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navMain">
					<span class="icon-bar"></span><span class="icon-bar"></span><span
						class="icon-bar"></span>
				</button>
			</div>
			<div id="navMain" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="${ctx}/home">Trang chủ</a></li>
					<li><a href="${ctx}/about">Giới thiệu</a></li>
					<li><a href="${ctx}/contact">Liên hệ</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<c:choose>
						<c:when test="${not empty sessionScope.account}">
							<li class="navbar-text hidden-xs"><c:if
									test="${not empty sessionScope.account.avatar}">
									<img class="nav-avatar"
										src="<c:url value='${sessionScope.account.avatar}'/>"
										alt="avatar">
								</c:if> Xin chào, <strong> <c:out
										value="${sessionScope.account.fullName != null ? sessionScope.account.fullName : sessionScope.account.userName}" />
							</strong></li>
							<li><a href="${ctx}/profile">Hồ sơ</a></li>
							<li><a href="${ctx}/logout">Đăng xuất</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${ctx}/login">Đăng nhập</a></li>
							<li><a href="${ctx}/register">Đăng ký</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container container-page" style="margin-top: 70px">
		<!-- include an toàn: nếu view thiếu dấu / thì tự thêm -->
		<c:choose>
			<c:when test="${fn:startsWith(view,'/')}">
				<jsp:include page="${view}" />
			</c:when>
			<c:otherwise>
				<jsp:include page="/${view}" />
			</c:otherwise>
		</c:choose>
	</div>

	<footer>
		<div class="container">
			<div class="row">
				<div class="col-sm-6">
					©
					<script>
						document.write(new Date().getFullYear())
					</script>
					MySite
				</div>
				<div class="col-sm-6 text-right">
					<a href="${ctx}/terms">Điều khoản</a> · <a href="${ctx}/privacy">Chính
						sách</a>
				</div>
			</div>
		</div>
	</footer>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<c:if test="${not empty scriptExtras}">${scriptExtras}</c:if>
</body>
</html>

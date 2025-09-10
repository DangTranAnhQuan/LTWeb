<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="panel panel-default"
	style="max-width: 720px; margin: 0 auto">
	<div class="panel-heading">
		<h3 style="margin: 0">Cập nhật hồ sơ</h3>
	</div>
	<div class="panel-body">
		<c:if test="${not empty msg}">
			<div class="alert alert-success">${msg}</div>
		</c:if>
		<c:if test="${not empty err}">
			<div class="alert alert-danger">${err}</div>
		</c:if>

		<form method="post"
			action="${pageContext.request.contextPath}/profile"
			enctype="multipart/form-data" class="form-horizontal">

			<div class="form-group">
				<label class="col-sm-3 control-label">Họ tên</label>
				<div class="col-sm-7">
					<input class="form-control" name="fullName"
						value="${sessionScope.account.fullName}" required>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label">Điện thoại</label>
				<div class="col-sm-7">
					<input class="form-control" name="phone"
						value="${sessionScope.account.phone}">
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label">Ảnh đại diện</label>
				<div class="col-sm-7">
					<input type="file" name="avatar" accept="image/*"
						class="form-control">
					<p class="help-block">Tối đa ~2MB. JPG/PNG.</p>

					<c:if test="${not empty sessionScope.account.avatar}">
						<img src="<c:url value='${sessionScope.account.avatar}'/>"
							style="height: 80px; margin-top: 8px" alt="avatar">
					</c:if>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-3 col-sm-7">
					<button class="btn btn-primary">Lưu thay đổi</button>
				</div>
			</div>
		</form>
	</div>
</div>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="panel panel-default">
	<div class="panel-heading">
		<h3 style="margin: 8px 0 0">
			<c:choose>
				<c:when test="${not empty item}">Chỉnh sửa danh mục</c:when>
				<c:otherwise>Thêm danh mục mới</c:otherwise>
			</c:choose>
		</h3>
	</div>

	<div class="panel-body">
		<c:if test="${not empty error}">
			<div class="alert alert-danger">${error}</div>
		</c:if>

		<!-- xác định action -->
		<c:choose>
			<c:when test="${not empty item}">
				<c:url var="actionUrl" value="/admin/category/edit" />
			</c:when>
			<c:otherwise>
				<c:url var="actionUrl" value="/admin/category/add" />
			</c:otherwise>
		</c:choose>

		<form action="${actionUrl}" method="post" class="form-horizontal"
			autocomplete="off">
			<c:if test="${not empty item}">
				<input type="hidden" name="id" value="${item.cateid}" />
			</c:if>

			<div class="form-group">
				<label class="col-sm-2 control-label">Tên danh mục</label>
				<div class="col-sm-6">
					<input type="text" name="name" class="form-control"
						value="<c:out value='${item.catename}'/>" required>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-2 control-label">Icon (FontAwesome)</label>
				<div class="col-sm-6">
					<input type="text" name="icon" class="form-control"
						placeholder="fa-mobile / fa-book …"
						value="<c:out value='${item.icon}'/>">
					<p class="help-block">Nhập class FontAwesome nếu muốn hiển thị
						icon.</p>
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-6">
					<button type="submit" class="btn btn-primary">
						<c:choose>
							<c:when test="${not empty item}">Cập nhật</c:when>
							<c:otherwise>Thêm mới</c:otherwise>
						</c:choose>
					</button>
					<a class="btn btn-default"
						href="${pageContext.request.contextPath}/admin/category/list">Quay
						lại</a>
				</div>
			</div>
		</form>
	</div>
</div>

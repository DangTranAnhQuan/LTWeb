<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="panel panel-default">
	<div class="panel-heading">
		<h3 style="margin: 8px 0 0">Quản lý danh mục</h3>
		<small>Nơi bạn có thể quản lý danh mục của mình</small>
	</div>

	<div class="panel-body">

		<!-- THÔNG BÁO -->
		<c:if test="${not empty param.msg}">
			<div class="alert alert-success">
				<c:out value="${param.msg}" />
			</div>
		</c:if>
		<c:if test="${not empty param.err}">
			<div class="alert alert-danger">
				<c:out value="${param.err}" />
			</div>
		</c:if>

		<!-- TOOLBAR NHỎ -->
		<div class="row" style="margin-bottom: 12px">
			<div class="col-sm-6">
				<div class="form-inline">
					<label>Records per page</label> <select
						class="form-control input-sm">
						<option>10</option>
						<option>25</option>
						<option>50</option>
					</select>
				</div>
			</div>
			<div class="col-sm-6 text-right">
				<div class="form-inline">
					<label>Tìm:</label> <input class="form-control input-sm"
						placeholder="Nhập từ khóa...">
				</div>
			</div>
		</div>

		<!-- DANH SÁCH DANH MỤC  -->
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th style="width: 70px">STT</th>
					<th style="width: 220px">Icon</th>
					<th>Tên danh mục</th>
					<th style="width: 220px">Hành động</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${cateList}" var="cate" varStatus="st">
					<c:url var="editUrl" value="/admin/category/edit" />
					<c:url var="deleteUrl" value="/admin/category/delete">
						<c:param name="id" value="${cate.cateid}" />
					</c:url>

					<tr data-row-id="${cate.cateid}">
						<td><c:out value="${st.index + 1}" /></td>

						<td>
							<!-- Form của từng hàng để submit cập nhật -->
							<form class="js-row-form" action="${editUrl}" method="post">
								<input type="hidden" name="id"
									value="<c:out value='${cate.cateid}'/>"> <input
									class="form-control inline-input js-icon" name="icon"
									value="<c:out value='${cate.icon}'/>" readonly>
							</form>
						</td>

						<td>
							<!-- Ô tên nằm ngoài form, sẽ đẩy vào form khi bấm Lưu --> <input
							class="form-control inline-input js-name" name="name"
							value="<c:out value='${cate.catename}'/>" readonly form="noform">
						</td>

						<td class="action">
							<button type="button" class="btn btn-link btn-xs js-edit">Sửa</button>
							<button type="button" class="btn btn-success btn-xs js-save"
								disabled>Lưu</button>
							<button type="button" class="btn btn-default btn-xs js-cancel"
								disabled>Hủy</button> | <a class="btn btn-link btn-xs js-delete"
							href="${deleteUrl}" onclick="return confirm('Xóa danh mục này?')">Xóa</a>
						</td>
					</tr>
				</c:forEach>

				<c:if test="${empty cateList}">
					<tr>
						<td colspan="4" class="text-center text-muted">Chưa có danh
							mục.</td>
					</tr>
				</c:if>
			</tbody>
		</table>

		<!-- THÊM NHANH DANH MỤC -->
		<hr>
		<h4>Thêm danh mục</h4>
		<form class="form-inline"
			action="<c:url value='/admin/category/add'/>" method="post"
			autocomplete="off">
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

<style>
.table td, .table th {
	vertical-align: middle !important
}

.inline-input {
	height: 28px;
	padding: 2px 6px;
	border: 1px solid transparent;
	background: transparent
}

.inline-input[readonly] {
	background: transparent;
	border-color: transparent
}

.action .btn {
	margin: 0 2px
}
</style>

<script>

	document.addEventListener('click', function(e) {
		if (!e.target.classList.contains('js-edit'))
			return;
		var row = e.target.closest('tr');
		var name = row.querySelector('.js-name');
		var icon = row.querySelector('.js-icon');
		if (!name.dataset.orig)
			name.dataset.orig = name.value;
		if (!icon.dataset.orig)
			icon.dataset.orig = icon.value;
		name.readOnly = false;
		name.classList.add('form-control');
		name.focus();
		icon.readOnly = false;
		icon.classList.add('form-control');
		row.querySelector('.js-save').disabled = false;
		row.querySelector('.js-cancel').disabled = false;
		e.target.disabled = true;
	});

	document.addEventListener('click', function(e) {
		if (!e.target.classList.contains('js-cancel'))
			return;
		var row = e.target.closest('tr');
		var name = row.querySelector('.js-name');
		var icon = row.querySelector('.js-icon');
		if (name.dataset.orig !== undefined)
			name.value = name.dataset.orig;
		if (icon.dataset.orig !== undefined)
			icon.value = icon.dataset.orig;
		name.readOnly = true;
		name.classList.remove('form-control');
		icon.readOnly = true;
		icon.classList.remove('form-control');
		row.querySelector('.js-save').disabled = true;
		row.querySelector('.js-cancel').disabled = true;
		row.querySelector('.js-edit').disabled = false;
	});
	
	document.addEventListener('click', function(e) {
		if (!e.target.classList.contains('js-save'))
			return;
		var row = e.target.closest('tr');
		var form = row.querySelector('.js-row-form');
		var nameVal = row.querySelector('.js-name').value;
		var hiddenName = form.querySelector('input[name="name"]');
		if (!hiddenName) {
			hiddenName = document.createElement('input');
			hiddenName.type = 'hidden';
			hiddenName.name = 'name';
			form.appendChild(hiddenName);
		}
		hiddenName.value = nameVal;
		form.submit();
	});
</script>

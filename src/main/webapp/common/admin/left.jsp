<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="sidebar"
	style="width: 230px; background: #0284ff; color: #fff; position: fixed; top: 0; bottom: 0; left: 0;">
	<h1 class="brand"
		style="font-size: 22px; padding: 18px 20px; background: #0072df; margin: 0">Dashboard</h1>

	<div class="text-center" style="padding: 18px">
		<div
			style="width: 90px; height: 90px; border-radius: 50%; background: #fff; margin: 0 auto 8px"></div>
		<div>Bạn là Admin</div>
	</div>

	<div class="menu" style="padding: 10px 0">
		<a class="${currentMenu == 'cat-list' ? 'active' : ''}"
			href="${pageContext.request.contextPath}/admin/category/list"
			style="display: block; color: #fff; padding: 12px 20px; text-decoration: none;">
			Quản lý Danh mục </a> <a
			class="${currentMenu == 'cat-add' ? 'active' : ''}"
			href="${pageContext.request.contextPath}/admin/category/add"
			style="display: block; color: #fff; padding: 12px 20px; text-decoration: none;">
			Thêm danh mục mới </a>
	</div>
</div>

<style>
.sidebar .menu a.active, .sidebar .menu a:hover {
	background: #0065c3;
}
</style>

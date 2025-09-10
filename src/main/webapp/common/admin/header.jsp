<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="topbar clearfix"
	style="background: #0ea5ff; color: #fff; padding: 12px 16px;">
	<div class="pull-left">
		Xin chào <strong> <c:choose>
				<c:when test="${not empty sessionScope.account.fullName}">
					<c:out value="${sessionScope.account.fullName}" />
				</c:when>
				<c:otherwise>Người dùng</c:otherwise>
			</c:choose>
		</strong>
	</div>
	<div class="pull-right">
		<a class="btn btn-danger btn-xs"
			href="${pageContext.request.contextPath}/logout">Đăng xuất</a>
	</div>
</div>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>

<div class="jumbotron" style="padding: 18px 24px; margin-bottom: 18px">
	<h3 style="margin-top: 0">OneShop - Mỹ phẩm chính hãng</h3>
	<p class="text-muted">Tìm kiếm, lọc sản phẩm và mua sắm dễ dàng.</p>
</div>

<div class="row">
	<c:forEach items="${prodList}" var="p">
		<c:set var="imgRaw"
			value="${empty p.imageUrl ? '/assets/img/placeholder.png' : p.imageUrl}" />
		<c:choose>
	
			<c:when
				test="${fn:startsWith(imgRaw,'http://') or fn:startsWith(imgRaw,'https://')}">
				<c:set var="imgSrc" value="${imgRaw}" />
			</c:when>
	
			<c:otherwise>
				<c:url var="imgSrc" value="${imgRaw}" />
			</c:otherwise>
		</c:choose>

		<div class="col-sm-3" style="margin-bottom: 18px">
			<div class="thumbnail" style="height: 100%">
				<img src="${imgSrc}" alt="<c:out value='${p.name}'/>" loading="lazy">
				<div class="caption">
					<h4 style="margin-top: 0">
						<c:out value="${p.name}" />
					</h4>
					<p class="text-primary" style="font-weight: bold">
						<fmt:formatNumber value="${p.price}" type="number"
							minFractionDigits="0" />
						đ
					</p>
					<p>
						<c:url var="detailUrl" value="/product/detail">
							<c:param name="id" value="${p.id}" />
						</c:url>
						<c:url var="addCartUrl" value="/cart/add">
							<c:param name="id" value="${p.id}" />
						</c:url>

						<a class="btn btn-default btn-sm" href="${detailUrl}">Xem</a> <a
							class="btn btn-primary btn-sm" href="${addCartUrl}">Thêm giỏ</a>
					</p>
				</div>
			</div>
		</div>
	</c:forEach>

	<c:if test="${empty prodList}">
		<div class="col-sm-12">
			<div class="alert alert-info">Chưa có dữ liệu sản phẩm. Hãy
				thêm sample trong controller.</div>
		</div>
	</c:if>
</div>

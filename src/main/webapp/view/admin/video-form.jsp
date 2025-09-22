<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="formAction" value="${empty item ? ctx.concat('/admin/video/add') : ctx.concat('/admin/video/edit')}" />

<div class="panel panel-default">
  <div class="panel-heading">
    <h3 style="margin:8px 0 0">
      <c:choose>
        <c:when test="${not empty item}">Chỉnh sửa video</c:when>
        <c:otherwise>Thêm video</c:otherwise>
      </c:choose>
    </h3>
  </div>

  <div class="panel-body">
    <form class="form-horizontal" method="post" action="${formAction}">

      <c:if test="${not empty item}">
        <input type="hidden" name="id" value="${item.id}">
      </c:if>

      <div class="form-group">
        <label class="col-sm-2 control-label">Tiêu đề</label>
        <div class="col-sm-6">
          <input name="title" class="form-control" required value="${empty item ? '' : item.title}">
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">Href</label>
        <div class="col-sm-6">
          <input name="href" class="form-control" required value="${empty item ? '' : item.href}">
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">Poster (URL)</label>
        <div class="col-sm-6">
          <input name="poster" class="form-control" value="${empty item ? '' : item.poster}">
          <c:if test="${not empty item && not empty item.poster}">
            <div style="margin-top:8px">
              <img src="${item.poster}" style="max-height:80px">
            </div>
          </c:if>
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">Mô tả</label>
        <div class="col-sm-6">
          <textarea name="description" rows="3" class="form-control">${empty item ? '' : item.description}</textarea>
        </div>
      </div>

      <!-- Nếu controller set attribute 'cates', hiện dropdown danh mục -->
      <c:if test="${not empty cates}">
        <div class="form-group">
          <label class="col-sm-2 control-label">Danh mục</label>
          <div class="col-sm-6">
            <select name="cateid" class="form-control">
              <c:forEach var="c" items="${cates}">
                <option value="${c.id}"
                  ${not empty item && not empty item.category && item.category.id == c.id ? 'selected' : ''}>
                  ${c.name}
                </option>
              </c:forEach>
            </select>
          </div>
        </div>
      </c:if>

      <div class="form-group">
        <label class="col-sm-2 control-label">Kích hoạt</label>
        <div class="col-sm-6">
          <input type="checkbox" name="active"
                 ${not empty item && item.active ? 'checked' : ''}>
        </div>
      </div>

      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-6">
          <button type="submit" class="btn btn-primary">
            <c:out value="${empty item ? 'Thêm mới' : 'Cập nhật'}"/>
          </button>
          <a class="btn btn-default" href="${ctx}/admin/video/list">Quay lại</a>
        </div>
      </div>
    </form>
  </div>
</div>

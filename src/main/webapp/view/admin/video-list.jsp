<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="panel panel-default">
  <div class="panel-heading">
    <h3 style="margin:8px 0 0">Quản lý video</h3>
    <small>Tìm theo tiêu đề / href</small>
  </div>

  <div class="panel-body">
    <c:if test="${not empty param.msg}">
      <div class="alert alert-success"><c:out value="${param.msg}"/></div>
    </c:if>

    <form class="form-inline" method="get" action="${ctx}/admin/video/list">
      <div class="form-group">
        <input type="text" name="kw" class="form-control" placeholder="Nhập từ khóa..." value="${param.kw}">
      </div>
      <button class="btn btn-default" type="submit">Tìm kiếm</button>
      <a class="btn btn-primary" href="${ctx}/admin/video/add">+ Thêm mới</a>
    </form>

    <hr/>

    <div class="table-responsive">
      <table class="table table-striped table-hover">
        <thead>
          <tr>
            <th>#</th>
            <th>Tiêu đề</th>
            <th>Href</th>
            <th>Poster</th>
            <th>Danh mục</th>
            <th>Views</th>
            <th>Active</th>
            <th style="width:150px">Thao tác</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="v" items="${items}" varStatus="st">
            <tr>
              <td>${st.index + 1}</td>
              <td>${v.title}</td>
              <td>${v.href}</td>
              <td>
                <c:if test="${not empty v.poster}">
                  <img src="${v.poster}" style="max-height:40px">
                </c:if>
              </td>
              <td>
                <c:out value="${not empty v.category ? v.category.name : ''}"/>
              </td>
              <td>${empty v.views ? 0 : v.views}</td>
              <td>
                <span class="label ${v.active ? 'label-success' : 'label-default'}">
                  ${v.active ? 'Yes' : 'No'}
                </span>
              </td>
              <td>
                <a class="btn btn-xs btn-warning" href="${ctx}/admin/video/edit?id=${v.id}">Sửa</a>
                <a class="btn btn-xs btn-danger" href="${ctx}/admin/video/delete?id=${v.id}"
                   onclick="return confirm('Xóa video này?')">Xóa</a>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>

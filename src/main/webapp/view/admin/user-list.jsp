<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%-- Nếu createdDate là java.util.Date và muốn định dạng:
     <%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %> --%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="panel panel-default">
  <div class="panel-heading">
    <h3 style="margin:8px 0 0">Quản lý người dùng</h3>
    <small>Tìm theo username / họ tên / email / phone</small>
  </div>

  <div class="panel-body">
    <c:if test="${not empty param.msg}">
      <div class="alert alert-success"><c:out value="${param.msg}"/></div>
    </c:if>

    <form class="form-inline" method="get" action="${ctx}/admin/user/list">
      <div class="form-group">
        <input type="text" name="kw" class="form-control" placeholder="Nhập từ khóa..." value="${param.kw}">
      </div>
      <button class="btn btn-default" type="submit">Tìm kiếm</button>
      <a class="btn btn-primary" href="${ctx}/admin/user/add">+ Thêm mới</a>
    </form>

    <hr/>

    <div class="table-responsive">
      <table class="table table-striped table-hover">
        <thead>
          <tr>
            <th>#</th>
            <th>Username</th>
            <th>Họ tên</th>
            <th>Email</th>
            <th>Avatar</th>
            <th>Role</th>
            <th>Phone</th>
            <th>Ngày tạo</th>
            <th style="width:150px">Thao tác</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="u" items="${items}" varStatus="st">
            <tr>
              <td>${st.index + 1}</td>
              <td>${u.username}</td>
              <td>${u.fullName}</td>
              <td>${u.email}</td>
              <td>
                <c:if test="${not empty u.avatar}">
                  <img src="${u.avatar}" style="max-height:40px">
                </c:if>
              </td>
              <td>${u.roleId}</td>
              <td>${u.phone}</td>
              <td>
                ${u.createdDate}
                <%-- Nếu là java.util.Date:
                <fmt:formatDate value="${u.createdDate}" pattern="yyyy-MM-dd HH:mm" /> --%>
              </td>
              <td>
                <a class="btn btn-xs btn-warning" href="${ctx}/admin/user/edit?id=${u.id}">Sửa</a>
                <a class="btn btn-xs btn-danger" href="${ctx}/admin/user/delete?id=${u.id}"
                   onclick="return confirm('Xóa người dùng này?')">Xóa</a>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </div>
</div>

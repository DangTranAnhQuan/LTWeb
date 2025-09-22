<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="formAction" value="${empty item ? ctx.concat('/admin/user/add') : ctx.concat('/admin/user/edit')}" />

<div class="panel panel-default">
  <div class="panel-heading">
    <h3 style="margin:8px 0 0">
      <c:choose>
        <c:when test="${not empty item}">Chỉnh sửa người dùng</c:when>
        <c:otherwise>Thêm người dùng</c:otherwise>
      </c:choose>
    </h3>
  </div>

  <div class="panel-body">
    <form class="form-horizontal" method="post" action="${formAction}">

      <c:if test="${not empty item}">
        <input type="hidden" name="id" value="${item.id}">
      </c:if>

      <div class="form-group">
        <label class="col-sm-2 control-label">Email</label>
        <div class="col-sm-6">
          <input type="email" name="email" class="form-control" value="${item.email}">
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">Username</label>
        <div class="col-sm-6">
          <input name="username" class="form-control" required value="${item.username}">
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">Họ tên</label>
        <div class="col-sm-6">
          <input name="fullName" class="form-control" value="${item.fullName}">
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">Mật khẩu</label>
        <div class="col-sm-6">
          <input type="password" name="password" class="form-control" value="${item.password}" required>
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">Ảnh đại diện (URL)</label>
        <div class="col-sm-6">
          <input name="avatar" class="form-control" value="${item.avatar}">
          <c:if test="${not empty item.avatar}">
            <div style="margin-top:8px">
              <img src="${item.avatar}" style="max-height:80px">
            </div>
          </c:if>
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">Quyền (roleId)</label>
        <div class="col-sm-6">
          <c:set var="r" value="${empty item ? 3 : item.roleId}" />
          <select name="roleId" class="form-control">
            <option value="1" ${r == 1 ? 'selected' : ''}>1 - Admin</option>
            <option value="2" ${r == 2 ? 'selected' : ''}>2 - Manager</option>
            <option value="3" ${r == 3 ? 'selected' : ''}>3 - User</option>
          </select>
        </div>
      </div>

      <div class="form-group">
        <label class="col-sm-2 control-label">Điện thoại</label>
        <div class="col-sm-6">
          <input name="phone" class="form-control" value="${item.phone}">
        </div>
      </div>

      <c:if test="${not empty item.createdDate}">
        <div class="form-group">
          <label class="col-sm-2 control-label">Ngày tạo</label>
          <div class="col-sm-6">
            <!-- Nếu dùng java.util.Date: dùng fmt để format.
                 Nếu dùng LocalDateTime: để nguyên hoặc tự format tại Controller. -->
            <input class="form-control" value="${item.createdDate}" disabled>
          </div>
        </div>
      </c:if>

      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-6">
          <button type="submit" class="btn btn-primary">
            <c:out value="${empty item ? 'Thêm mới' : 'Cập nhật'}"/>
          </button>
          <a class="btn btn-default" href="${ctx}/admin/user/list">Quay lại</a>
        </div>
      </div>
    </form>
  </div>
</div>

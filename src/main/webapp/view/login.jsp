<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Đăng nhập</title>

  <link rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <link rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

  <style>
    body {
      background: #f7f7f7;
    }
    .login-card {
      max-width: 520px;
      margin: 50px auto;
      background: #fff;
      border: 1px solid #eee;
      border-radius: 6px;
      box-shadow: 0 2px 10px rgba(0, 0, 0, .04);
      padding: 25px 30px 30px;
    }
    .login-title {
      text-align: center;
      margin: 5px 0 20px;
      color: #6a6a6a;
    }
    .login-btn { width: 100%; }
    .login-meta {
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
    .login-footer {
      text-align: center;
      margin-top: 18px;
      color: #777;
    }
    .login-footer a { font-weight: 600; }
  </style>
</head>
<body>

  <div class="login-card">
    <h3 class="login-title">Đăng Nhập Vào Hệ Thống</h3>

    <c:if test="${not empty alert}">
      <div class="alert alert-danger" role="alert" style="margin-bottom: 18px;">
        ${alert}
      </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/login" method="post" autocomplete="off">
      <!-- Username -->
      <div class="form-group">
        <label class="control-label sr-only" for="username">Tài khoản</label>
        <div class="input-group">
          <span class="input-group-addon"><i class="fa fa-user"></i></span>
          <input type="text" class="form-control" id="username" name="username"
                 placeholder="Tài khoản" required autofocus
                 value="<c:out value='${param.username != null ? param.username : sessionScope.username}'/>" />
        </div>
      </div>

      <!-- Password -->
      <div class="form-group">
        <label class="control-label sr-only" for="password">Mật khẩu</label>
        <div class="input-group">
          <span class="input-group-addon"><i class="fa fa-lock"></i></span>
          <input type="password" class="form-control" id="password" name="password"
                 placeholder="Mật khẩu" required />
        </div>
      </div>

      <div class="form-group login-meta">
        <label class="checkbox-inline" style="margin-left: 2px;">
          <input type="checkbox" name="remember"
                 <c:if test="${param.remember == 'on' || not empty sessionScope.username}">checked</c:if> />
          Nhớ tôi
        </label>
        <a href="<c:url value='/view/ForgotPassword.jsp'/>" class="small">Quên mật khẩu?</a>
      </div>

      <!-- Submit -->
      <button type="submit" class="btn btn-primary login-btn">Đăng nhập</button>

      <!-- Footer -->
      <div class="login-footer">
        Nếu bạn chưa có tài khoản trên hệ thống, thì hãy
        <a href="<c:url value='/register'/>">Đăng ký</a>
      </div>
    </form>
  </div>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</body>
</html>

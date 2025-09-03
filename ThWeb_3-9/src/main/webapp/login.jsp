<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Trang Đăng Nhập</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        body {
            background-color: #f5f5f5;
        }
        .login-container {
            width: 500px;
            margin: 100px auto;
            padding: 25px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .form-group .fa {
            color: #999;
        }
        .form-control {
            border-left: none;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <h3 class="text-center mb-2">Đăng Nhập Vào Hệ Thống</h3>
		<c:if test="${param.message eq 'reset-success'}">
    		<div class="alert alert-success">
        		✅ Mật khẩu đã được đổi thành công! Vui lòng đăng nhập lại.
    		</div>
		</c:if>
		
        <c:if test="${not empty alert}">
            <div class="alert alert-danger">
                ${alert}
            </div>
        </c:if>
        
        <form action="login" method="post">
            <div class="form-group input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><i class="fa fa-user"></i></span>
                </div>
                <input type="text" name="username" class="form-control" placeholder="Tài khoản" required>
            </div>
            
            <div class="form-group input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><i class="fa fa-lock"></i></span>
                </div>
                <input type="password" name="password" class="form-control" placeholder="Mật khẩu" required>
            </div>

            <div class="form-group form-check">
            	<div class="d-flex justify-content-between w-100">
	                <div>
                        <input type="checkbox" class="form-check-input" id="rememberMe">
	                    <label class="form-check-label" for="rememberMe">Nhớ tôi</label>
                    </div>
	                <a href="forgotpass" class="float-right">Quên mật khẩu?</a>
	            </div>
            </div>

            <button type="submit" class="btn btn-primary btn-block">Đăng nhập</button>
        </form>

        <p class="text-center mt-3">
            Nếu bạn chưa có tài khoản trên hệ thống, hãy 
            <a href="register">Đăng ký</a>
        </p>
    </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quên Mật Khẩu</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="login-container">
        <h2 class="text-center mb-4">Quên Mật Khẩu</h2>
        <p class="text-center text-muted mb-4">
            Vui lòng nhập email để cấp mật khẩu mới.
        </p>

        <c:if test="${not empty alert}">
            <div class="alert alert-danger">
                ${alert}
            </div>
        </c:if>

        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">
                ${successMessage}
            </div>
        </c:if>
        
        <form action="forgotpass" method="post">
            <div class="form-group input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text"><i class="fa fa-envelope"></i></span>
                </div>
                <input type="text" name="email" class="form-control" 
                       placeholder="Email" required>
            </div>
            
            <button type="submit" class="btn btn-primary btn-block">Gửi</button>
        </form>

        <p class="text-center mt-3">
            <a href="login">Quay lại trang Đăng nhập</a>
        </p>
    </div>
</body>
</html>
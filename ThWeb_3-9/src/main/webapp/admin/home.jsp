
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang Admin</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #e9ecef;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 1000px;
            margin: 20px auto;
            background: #ffffff;
            border: 2px solid #2c3e50;
            border-radius: 5px;
            box-shadow: 0 0 15px rgba(0,0,0,0.2);
        }
        .header {
            background: #2c3e50;
            color: white;
            padding: 15px;
            text-align: center;
            border-bottom: 2px solid #34495e;
        }
        .content {
            padding: 20px;
        }
        .user-info {
            margin: 15px 0;
            padding: 10px;
            background: #ecf0f1;
            border-left: 4px solid #2c3e50;
        }
        .logout-btn {
            display: inline-block;
            padding: 10px 20px;
            background: #e74c3c;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .logout-btn:hover {
            background: #c0392b;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h2>Chào mừng Admin, ${sessionScope.account.fullName}!</h2>
        </div>
        <div class="content">
            <c:if test="${sessionScope.account != null}">
                <div class="user-info">
                    <p>Tên tài khoản: ${sessionScope.account.userName}</p>
                    <p>Vai trò: Admin</p>
                    <p>Email: ${sessionScope.account.email}</p>
                    <p>Ngày tạo: ${sessionScope.account.createDate}</p>
                </div>
                <a href="${pageContext.request.contextPath}/logout" class="logout-btn">Đăng xuất</a>
            </c:if>
            <c:if test="${sessionScope.account == null}">
                <p>Bạn chưa đăng nhập! <a href="${pageContext.request.contextPath}/login">Đăng nhập</a></p>
            </c:if>
        </div>
    </div>
</body>
</html>

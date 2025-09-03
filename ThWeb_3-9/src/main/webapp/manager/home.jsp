<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang Quản Lý</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f5f5f5;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 900px;
            margin: 30px auto;
            background: #fff;
            border: 1px solid #bdc3c7;
            border-radius: 8px;
            overflow: hidden;
        }
        .header {
            background: #7f8c8d;
            color: white;
            padding: 20px;
            text-align: center;
        }
        .content {
            padding: 25px;
        }
        .user-info {
            background: #f9f9f9;
            padding: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .logout-btn {
            display: inline-block;
            padding: 12px 25px;
            background: #e67e22;
            color: white;
            text-decoration: none;
            border-radius: 4px;
        }
        .logout-btn:hover {
            background: #d35400;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h2>Chào mừng Quản lý, ${sessionScope.account.fullName}!</h2>
        </div>
        <div class="content">
            <c:if test="${sessionScope.account != null}">
                <div class="user-info">
                    <p>Tên tài khoản: ${sessionScope.account.userName}</p>
                    <p>Vai trò: Quản lý</p>
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
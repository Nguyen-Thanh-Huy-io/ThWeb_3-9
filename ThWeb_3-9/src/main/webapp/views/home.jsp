<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang Người Dùng</title>
    <style>
        body {
            font-family: 'Helvetica Neue', Arial, sans-serif;
            background: #ecf0f1;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 800px;
            margin: 40px auto;
            background: #fff;
            border-radius: 10px;
            padding: 30px;
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }
        .header {
            background: #27ae60;
            color: white;
            padding: 15px;
            text-align: center;
            border-radius: 10px 10px 0 0;
        }
        .content {
            padding: 20px;
        }
        .user-info {
            background: #f1f8e9;
            padding: 15px;
            border-left: 5px solid #27ae60;
            border-radius: 5px;
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
            <h2>Chào mừng, ${sessionScope.account.fullName}!</h2>
        </div>
        <div class="content">
            <c:if test="${sessionScope.account != null}">
                <div class="user-info">
                    <p>Tên tài khoản: ${sessionScope.account.userName}</p>
                    <p>Vai trò: Người dùng</p>
                    <p>Email: ${sessionScope.account.email}</p>
                    <p>Ngày tạo: ${sessionScope.account.createdDate}</p>
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
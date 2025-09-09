<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý danh mục</title>
    <style>
        :root {
            --primary-color: #3498db;
            --secondary-color: #2c3e50;
            --success-color: #2ecc71;
            --danger-color: #e74c3c;
            --background-color: #ecf0f1;
            --card-background: #ffffff;
            --border-color: #bdc3c7;
            --text-color: #34495e;
            --font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        body {
            font-family: var(--font-family);
            background-color: var(--background-color);
            margin: 0;
            padding: 40px 20px;
            color: var(--text-color);
            display: flex;
            justify-content: center;
            align-items: flex-start;
            min-height: 100vh;
        }

        .container {
            background-color: var(--card-background);
            padding: 0;
            border-radius: 8px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 1200px;
            overflow: hidden;
        }

        .header {
            background: var(--secondary-color);
            color: white;
            padding: 20px 30px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-top-left-radius: 8px;
            border-top-right-radius: 8px;
        }

        .user-info {
            display: flex;
            align-items: center;
        }

        .avatar {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            object-fit: cover;
            margin-right: 15px;
            border: 2px solid white;
        }

        .header-actions {
            display: flex;
            gap: 10px;
        }
        
        h2 {
            font-size: 1.5rem;
            margin: 0;
        }

        .content {
            padding: 30px;
        }

        .message-container {
            margin-bottom: 20px;
        }
        .message, .error {
            padding: 12px 20px;
            border-radius: 5px;
            margin-top: 10px;
            font-weight: 600;
        }
        .message {
            background-color: #e8f5e9;
            color: var(--success-color);
        }
        .error {
            background-color: #ffebee;
            color: var(--danger-color);
        }

        .table-responsive {
            overflow-x: auto;
        }

        table {
            width: 100%;
            border-collapse: separate;
            border-spacing: 0;
            background: white;
            border-radius: 8px;
            overflow: hidden;
        }
        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid var(--border-color);
        }
        th {
            background-color: #f5f5f5;
            font-weight: 700;
            color: var(--secondary-color);
        }
        tr:last-child td {
            border-bottom: none;
        }
        tbody tr:hover {
            background-color: #f8f9fa;
        }

        .category-icon {
            max-height: 80px;
            width: auto;
            border-radius: 4px;
        }

        .action-cell {
            white-space: nowrap;
        }

        .action-btn, .add-btn, .logout-btn {
            display: inline-block;
            padding: 10px 18px;
            font-size: 0.9rem;
            font-weight: 600;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            margin-right: 8px;
            transition: all 0.3s ease;
            border: none;
            cursor: pointer;
        }
        .action-btn { background: var(--primary-color); }
        .action-btn:hover { background: #2980b9; transform: translateY(-2px); }
        
        .delete-form {
            display: inline;
        }
        .delete-btn {
            background: var(--danger-color);
            padding: 10px 18px;
            font-size: 0.9rem;
            font-weight: 600;
            color: white;
            border-radius: 5px;
            border: none;
            cursor: pointer;
            transition: all 0.3s ease;
        }
        .delete-btn:hover { background: #c0392b; transform: translateY(-2px); }

        .add-btn { background: var(--success-color); }
        .add-btn:hover { background: #27ae60; transform: translateY(-2px); }
        .logout-btn { background: var(--danger-color); }
        .logout-btn:hover { background: #c0392b; transform: translateY(-2px); }

        .action-links {
            display: flex;
            justify-content: flex-end;
            margin-top: 20px;
            gap: 15px;
        }

        .no-categories {
            text-align: center;
            font-style: italic;
            color: var(--secondary-color);
            padding: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <c:if test="${not empty sessionScope.account}">
                <div class="user-info">
                    <c:choose>
                        <c:when test="${not empty sessionScope.account.avatar}">
                            <c:url value="/image?fname=${sessionScope.account.avatar}" var="avatarUrl"></c:url>
                            <img src="${avatarUrl}" alt="Avatar" class="avatar"/>
                        </c:when>
                        <c:otherwise>
                            <img src="<c:url value='/images/default-avatar.jpg'/>" alt="Default Avatar" class="avatar"/>
                        </c:otherwise>
                    </c:choose>
                    <h2>Chào mừng Admin, ${sessionScope.account.fullName}!</h2>
                </div>
            </c:if>
            <div class="header-actions">
                 <a href="<c:url value='/admin/category/add'/>" class="add-btn">Thêm danh mục</a>
                <c:if test="${not empty sessionScope.account}">
                    <a href="<c:url value='/logout'/>" class="logout-btn">Đăng xuất</a>
                </c:if>
            </div>
        </div>
        <div class="content">
            <div class="message-container">
                <c:if test="${not empty param.message}">
                    <p class="message">${param.message == 'deleteSuccess' ? 'Xóa danh mục thành công!' : param.message}</p>
                </c:if>
                <c:if test="${not empty param.error}">
                    <p class="error">${param.error}</p>
                </c:if>
            </div>

            <div class="table-responsive">
                <table>
                    <thead>
                        <tr>
                            <th>STT</th>
                            <th>Hình ảnh</th>
                            <th>Tên danh mục</th>
                            <th>Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${not empty cateList}">
                                <c:forEach items="${cateList}" var="cate" varStatus="STT">
                                    <tr>
                                        <td>${STT.index + 1}</td>
                                        <td>
                                            <c:if test="${not empty cate.icon}">
                                                <c:url value="/image?fname=${cate.icon}" var="imgUrl"></c:url>
                                                <img class="category-icon" src="${imgUrl}" alt="Icon"/>
                                            </c:if>
                                        </td>
                                        <td>${cate.catename}</td>
                                        <td class="action-cell">
                                            <a href="<c:url value='/admin/category/edit?id=${cate.cateid}'/>" class="action-btn">Sửa</a>
                                            <form action="<c:url value='/admin/category/delete'/>" method="post" class="delete-form">
                                                <input type="hidden" name="id" value="${cate.cateid}">
                                                <button type="submit" class="delete-btn" onclick="return confirm('Bạn có chắc muốn xóa danh mục này?');">Xóa</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td colspan="4" class="no-categories">Không có danh mục nào.</td>
                                </tr>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
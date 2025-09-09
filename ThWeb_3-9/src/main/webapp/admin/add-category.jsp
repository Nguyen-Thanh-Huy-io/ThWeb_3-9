<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Thêm danh mục</title>
    <style>
        :root {
            --primary-color: #007bff;
            --secondary-color: #6c757d;
            --success-color: #28a745;
            --danger-color: #dc3545;
            --background-color: #f8f9fa;
            --card-background: #fff;
            --border-color: #dee2e6;
            --text-color: #333;
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
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 500px;
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: var(--primary-color);
            font-weight: 600;
            border-bottom: 2px solid var(--primary-color);
            padding-bottom: 10px;
            display: inline-block;
            width: 100%;
        }

        .form-group {
            margin-bottom: 20px;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
            color: var(--secondary-color);
        }

        .form-control {
            width: 100%;
            padding: 12px;
            border: 1px solid var(--border-color);
            border-radius: 6px;
            font-size: 1rem;
            transition: border-color 0.3s, box-shadow 0.3s;
            box-sizing: border-box;
        }

        .form-control:focus {
            border-color: var(--primary-color);
            outline: none;
            box-shadow: 0 0 0 3px rgba(0, 123, 255, 0.25);
        }

        .error {
            color: var(--danger-color);
            background-color: #f8d7da;
            border: 1px solid #f5c6cb;
            padding: 10px 15px;
            border-radius: 6px;
            margin-bottom: 20px;
            text-align: center;
        }

        .btn-group {
            display: flex;
            justify-content: flex-end;
            gap: 10px;
            margin-top: 25px;
        }

        .btn {
            padding: 10px 20px;
            font-size: 1rem;
            font-weight: 600;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: transform 0.2s, box-shadow 0.2s;
            text-decoration: none;
            text-align: center;
            display: inline-block;
        }

        .btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .btn-default {
            background-color: var(--success-color);
            color: white;
        }

        .btn-primary {
            background-color: var(--primary-color);
            color: white;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Thêm danh mục</h2>
        
        <c:if test="${not empty message}">
            <p class="error">${message}</p>
        </c:if>

        <form role="form" action="<c:url value='/admin/category/add'/>" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label>Tên danh mục:</label>
                <input class="form-control" placeholder="Vui lòng nhập tên danh mục" name="name" 
                       value="${not empty category ? category.catename : ''}" required/>
            </div>
            <div class="form-group">
                <label>Ảnh đại diện:</label>
                <input type="file" name="icon"/>
            </div>
            <div class="btn-group">
                <button type="submit" class="btn btn-default">Thêm</button>
                <a href="<c:url value='/admin/category/list'/>" class="btn btn-primary">Hủy</a>
            </div>
        </form>
    </div>
</body>
</html>
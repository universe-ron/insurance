<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者中心</title>
<style>
    body {
        font-family: 'Arial', sans-serif;
        background-color: #f0f8ff; /* 寶藍色 */
        margin: 0;
        padding: 0;
    }

    h1 {
        text-align: center;
        margin-top: 50px;
        color: #007bff; /* 寶藍色 */
    }

    a {
        display: block;
        width: 200px;
        margin: 20px auto;
        text-align: center;
        background-color: #007bff; /* 寶藍色 */
        color: #fff;
        text-decoration: none;
        padding: 10px;
        border-radius: 5px;
        transition: background-color 0.3s;
    }

    a:hover {
        background-color: #0056b3; /* 深寶藍色 */
    }
    .back-to-member-center {
            position: absolute;
            top: 20px;
            right: 20px;
        }

        .back-to-member-center a {
            display: inline-block;
            padding: 6px 12px;
            text-align: center;
            text-decoration: none;
            background-color: #007bff; /* 蓝色 */
            color: #fff;
            border-radius: 4px;
            transition: background-color 0.3s;
        }

        .back-to-member-center a:hover {
            background-color: #0056b3; /* 深蓝色 */
        }
</style>
</head>
 <div class="back-to-member-center">
        <a href="HomePage.jsp">回到主頁</a>
    </div>
<body>
<h1>管理者中心</h1>
<a href="products">產品</a>
<a href="users">會員</a>
<a href="opdisplayData">保單清單</a>
</body>
</html>

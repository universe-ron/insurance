<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
    <style>
      
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f0f8ff; /* 寶藍色 */
            margin: 0;
            padding: 0;
        }

        h2 {
            text-align: center;
            margin-top: 30px;
            color: #007bff; /* 寶藍色 */
        }

        table {
            width: 80%;
            margin: auto;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #007bff; /* 寶藍色 */
            color: #fff;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #ddd;
        }

        td:last-child {
            text-align: center;
        }

        a {
            display: inline-block;
            padding: 6px 12px;
            text-align: center;
            text-decoration: none;
            background-color: #007bff; /* 寶藍色 */
            color: #fff;
            border-radius: 4px;
            transition: background-color 0.3s;
        }

        a:hover {
            background-color: #0056b3; /* 深寶藍色 */
        }
        
         form {
        width: 50%; /* Adjust width as needed */
        margin: auto; /* Center the form horizontally */
        border: 1px solid #ccc; /* Add outer border */
        padding: 20px; /* Add padding for spacing */
        background-color: #fff; /* Add white background */
        border-radius: 8px; /* Add border radius for rounded corners */
        box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); /* Add box shadow for depth */
    }
     label {
        margin-top: 10px; /* Add margin between labels */
        display: block; /* Make labels block elements */
    }
    input[type="text"], input[type="submit"] {
        width: 100%; /* Make text inputs and submit button full width */
        padding: 8px; /* Add padding for better appearance */
        margin-top: 6px; /* Add margin between inputs */
        border-radius: 4px; /* Add border radius for rounded corners */
        border: 1px solid #ccc; /* Add border */
        box-sizing: border-box; /* Include padding and border in width */
    }

    input[type="submit"] {
        background-color: #007bff; /* 寶藍色 */
        color: #fff; /* White text color */
        cursor: pointer; /* Change cursor to pointer on hover */
        transition: background-color 0.3s; /* Smooth transition for background color */
    }

    input[type="submit"]:hover {
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
        <a href="OP.jsp">管理者中心</a>
    </div>
<body>
    <h2>Edit Product</h2>
    <form action="updateProduct" method="post">
        <input type="hidden" name="pid" value="${product.pid}">
        
        <label for="pname">Name:</label>
        <input type="text" id="pname" name="pname" value="${product.pname}" required><br>
        
        <label for="generations">Generations:</label>
        <input type="text" id="generations" name="generations" value="${product.generations}" required><br>
        
        <label for="type">Type:</label>
        <input type="text" id="type" name="type" value="${product.type}" required><br>
        
        <label for="price">Price:</label>
        <input type="number" id="price" name="price" value="${product.price}" required><br>
        
        <input type="submit" value="Update">
    </form>
</body>
</html>

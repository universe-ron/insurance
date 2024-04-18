<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit User</title>
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
    <h2>Edit User</h2>
    <form action="updateUser" method="post">
        <input type="hidden" name="uid" value="${user.uid}">
        
        <label for="account">Account:</label>
        <input type="text" id="account" name="account" value="${user.account}" required><br>
        
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" value="${user.password}" required><br>
        
        <label for="mail">Mail:</label>
        <input type="email" id="mail" name="mail" value="${user.mail}" required><br>
        
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${user.name}" required><br>
        
        <label for="birthDate">Birth Date:</label>
        <input type="date" id="birthDate" name="birthDate" value="${user.birthDate}" required><br>
        
        <label for="gender">Gender:</label>
        <select id="gender" name="gender">
            <option value="M" ${user.gender eq 'Male' ? 'selected' : ''}>Male</option>
            <option value="F" ${user.gender eq 'Female' ? 'selected' : ''}>Female</option>
        </select><br>
        
        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="${user.address}" required><br>
        
        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone" value="${user.phone}" required><br>
        
        <label for="phone2">Phone 2:</label>
        <input type="text" id="phone2" name="phone2" value="${user.phone2}"><br>
        
        <label for="permission">Permission:</label>
        <input type="number" id="permission" name="permission" value="${user.permission}" required><br>
        
        <input type="submit" value="Update">
    </form>
</body>
</html>

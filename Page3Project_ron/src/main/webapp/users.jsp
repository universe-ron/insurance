<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>

<html>
<head>
    <title>User List</title>
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
            margin:5px;
            width:45px; 
/*             height: 15px; */
            padding: 6px 10px;
            text-align: center;
            text-decoration: none;
            background-color: #007bff; /* 寶藍色 */
            color: #fff;
            border-radius: 2px;
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
        width:80px;
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
    <h2>User List</h2>

    <table border="1">
        <thead>
            <tr>
                <th>User ID</th>
                <th>Account</th>
                <th>Password</th>
                <th>Mail</th>
                <th>Name</th>
                <th>Birth Date</th>
                <th>Gender</th>
                <th>Address</th>
                <th>Phone</th>
                <th>Phone 2</th>
                <th>Permission</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<User> users = (List<User>) request.getAttribute("users");
            if (users != null) {
                for (User user : users) {
            %>
            <tr>
                <td><%= user.getUid() %></td>
                <td><%= user.getAccount() %></td>
                <td><%= user.getPassword() %></td>
                <td><%= user.getMail() %></td>
                <td><%= user.getName() %></td>
                <%
   				 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
   				 String birthDateStr = sdf.format(user.getBirthDate());
				%>
				<td><%= birthDateStr %></td>
                <td><%= user.getGender() %></td>
                <td><%= user.getAddress() %></td>
                <td><%= user.getPhone() %></td>
                <td><%= user.getPhone2() %></td>
                <td><%= user.getPermission() %></td>
                <td>
                    <a href="users?action=edit&id1=<%= user.getUid() %>">Edit</a>
                    <br>
                    <a href="users?action=delete&id2=<%= user.getUid() %>">Delete</a>
                </td>
             </tr>
            <% 
                    }
                } else {
                    // Handle the case when users list is null
                    // You can display a message or take appropriate action
                }
            %>
        </tbody>
    </table>
    
    </form>
</body>
</html>

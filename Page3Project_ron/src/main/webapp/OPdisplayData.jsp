<%@ page import="java.util.List"%>
<%@ page import="com.model.Totalbean"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Display Data</title>
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
    <h2>Display Data</h2>
    <table>
        <tr>
            <th>pname</th>
            <th>generations</th>
            <th>type</th>
            <th>price</th>
            <th>iname</th>
            <th>idcode</th>
            <th>endDate</th>
            <th>state</th>
            <th>Action</th>
        </tr>
        <%
        // 从请求中获取数据
        List<Totalbean> totalbeans = (List<Totalbean>) request.getAttribute("totalbeans");

        // 遍历数据并在表格中显示
        for (Totalbean item : totalbeans) {
        %>
        <tr>
            <td><%=item.getPname()%></td>
            <td><%=item.getGenerations()%></td>
            <td><%=item.getType()%></td>
            <td><%=item.getPrice()%></td>
            <td><%=item.getIname()%></td>
            <td><%=item.getIdcode()%></td>
            <td><%=item.getEndDate()%></td>
            <td>
                <%
                int state = item.getState();
                if (state == 1) {
                    out.println("有效");
                } else if (state == 0) {
                    out.println("無效");
                } else {
                    out.println("其他狀態");
                }
                %>
            </td>
            <td>
                <a href="updateState?state=1&id=<%= item.getId() %>">有效</a>
                <a href="updateState?state=0&id=<%= item.getId() %>">無效</a>
            </td>
        </tr>
        <%
        }
        %>
    </table>
</body>
</html>

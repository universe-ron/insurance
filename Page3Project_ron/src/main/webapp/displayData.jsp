<%@ page import="java.util.List"%>
<%@ page import="com.model.Totalbean"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Display Data</title>
<style>
      
        body {
    font-family: 'Arial', sans-serif;
    background-color: #fff; /* 白色 */
    margin: 0;
    padding: 0;
}

h2 {
    text-align: center;
    margin-top: 30px;
    color: #333; /* 黑色 */
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
    background-color: #333; /* 黑色 */
    color: #fff;
}

tr:nth-child(even) {
    background-color: #f2f2f2; /* 灰色 */
}

tr:hover {
    background-color: #ddd; /* 淺灰色 */
}

td:last-child {
    text-align: center;
}

a {
    display: inline-block;
    padding: 6px 12px;
    text-align: center;
    text-decoration: none;
    background-color: #333; /* 黑色 */
    color: #fff;
    border-radius: 4px;
    transition: background-color 0.3s;
}

a:hover {
    background-color: #666; /* 深灰色 */
}

form {
    width: 50%;
    margin: auto;
    border: 1px solid #ccc;
    padding: 20px;
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}

label {
    margin-top: 10px;
    display: block;
}

input[type="text"], input[type="submit"] {
    width: 100%;
    padding: 8px;
    margin-top: 6px;
    border-radius: 4px;
    border: 1px solid #ccc;
    box-sizing: border-box;
}

input[type="submit"] {
    background-color: #333; /* 黑色 */
    color: #fff;
    cursor: pointer;
    transition: background-color 0.3s;
}

input[type="submit"]:hover {
    background-color: #666; /* 深灰色 */
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
    background-color: #333; /* 黑色 */
    color: #fff;
    border-radius: 4px;
    transition: background-color 0.3s;
}

.back-to-member-center a:hover {
    background-color: #666; /* 深灰色 */
}

    </style>
</head>
 <div class="back-to-member-center">
        <a href="HomePage.jsp">主頁</a>
    </div>
<body>
	<h2>Display Data</h2>
	<table border="1">
		<tr>
			<th>pname</th>
			<th>generations</th>
			<th>type</th>
			<th>price</th>
			<th>iname</th>
			<th>idcode</th>
			<th>endDate</th>
			<th>state</th>
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
			<td><%
			int state = item.getState();
			if (state == 1) {
				out.println("有效");
			} else if (state == 0) {
				out.println("無效");
			} else {
				out.println("其他狀態");
			}
			%></td>
			
		</tr>
		<%
		}
		%>
	</table>
</body>
</html>

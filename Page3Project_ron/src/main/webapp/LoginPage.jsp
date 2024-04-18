<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<style>
@import url('https://fonts.googleapis.com/css2?family=Acme&display=swap');

body {
	background-color: #f0f0f0; /* Light grey background */
	color: #333; /* Dark grey text */
	font-family: 'Acme', sans-serif;
}

form {
	width: 400px;
	margin: 100px auto;
	text-align: center;
}

fieldset {
	background-color: #fff;
	border: 3px solid #000; /* Bold black border */
	height: auto;
	padding: 20px;
	box-sizing: border-box;
}

legend {
	font-size: x-large;
	font-weight: 900;
	padding: 5px;
	margin-bottom: 10px;
}

label {
	font-weight: 500;
	display: block;
	margin: 10px 0;
}

input[type="text"],
input[type="password"] {
	border: 1px solid #333; /* Dark grey border for inputs */
	border-radius: 5px;
	padding: 8px;
	width: 95%;
}

input[type="radio"] {
	margin-right: 5px;
}

button {
	padding: 10px 20px;
	border: 2px solid #000; /* Black border for buttons */
	background-color: #fff; /* White background for buttons */
	color: #000; /* Black text for buttons */
	cursor: pointer;
	transition: background-color 0.3s;
	font-weight: bold;
}

button:hover {
	background-color: #333;
	color: white;
}

a {
	text-decoration: none;
	color: black;
	font-weight: bold;
	display: block; /* Make links block to stack vertically */
	margin: 10px 0;
}

a:hover {
	color: #ff8000;
	text-decoration: underline;
}

a:active {
	color: red;
}
</style>
</head>

<body>
	<form action="LoginServlet.do" method="post">
		<fieldset>
			<legend>會員登入</legend>
			<p>
				<label for="acc">帳號:</label> 
				<input type="text" name="userAccount" id="acc" value="go@mail.com"><br> 
				<label for="pwd">密碼:</label>
				<input type="password" name="userPwd" id="pwd" value="gogo0000@"><br>
				<label for="">身份:</label>
                <label>
                    <input type="radio" name="permission" value="1" id="" required checked>一般會員
                </label>
                <label>
                    <input type="radio" name="permission" value="0" id="">管理員
                </label><br>
			</p>
			<button type="submit">Login</button>
		</fieldset>
		<a href="ResetPwdPage.jsp">忘記密碼?</a>
		<a href="Register.jsp">註冊會員</a>
	</form>
</body>
</html>

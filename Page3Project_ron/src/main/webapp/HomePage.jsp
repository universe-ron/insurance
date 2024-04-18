<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<style>
	* {
	margin: 0;
	padding: 0;
}

body {
	font-family: 'Arial', sans-serif;
	background-color: #fff; /* 白色 */
	color: #000; /* 黑色 */
}

header {
	background-color: #333; /* 黑色 */
	padding: 10px;
	color: #fff;
	display: flex;
	justify-content: flex-end;
}

header button {
	background-color: #666; /* 灰色 */
	color: #fff;
	border: none;
	padding: 8px 16px;
	margin-left: 10px;
	cursor: pointer;
}

button {
	padding: 20px 20px;
	border: 1px solid #ccc;
	border-radius: 5px;
	box-sizing: border-box;
	font-size: 16px;
	display: inline-block;
	background-color: #333; /* 黑色 */
	color: #fff;
	cursor: pointer;
	transition: background-color 0.3s;
}
form{
	padding:30px 0px;
}

fieldset {
	border: none;
}

legend {
	font-size: 23px;
	font-weight: 800;
	margin: 20px auto;
	color: #000; /* 黑色 */
}

.container {
	width: 880px;
	margin: auto;
	display: flex;
	justify-content: space-between;
}

.item {
	width: 260px;
	height: 150px;
	padding: 10px;
	border: 0.5px solid #666; /* 灰色 */
	border-radius: 5px;
	background-color: #fff; /* 白色 */
}

h3 {
	line-height: 2;
	color: #666; /* 灰色 */
}

h4 {
	line-height: 2.2;
	color: #000; /* 黑色 */
}

li {
	font-size: 14px;
	line-height: 1.7;
	margin-left: 20px;
	color: #000; /* 黑色 */
}

a {
    display: flex;
    justify-content: center; /* 水平置中 */
    align-items: center; /* 垂直置中 */
    color: #666; /* 灰色 */
    font-weight: 800;
}


</style>
</head>


<body>

	<header>
		<form action="LogoutServlet.do" method="post">
			<button type="submit">登出</button>
		</form>
		<form action="PermissionDeterminationServlet.do" method="post">
			<button type="submit">會員中心</button>
		</form>
	</header>	

	<br><br>

	<fieldset>
		<legend>手機碎屏險方案</legend>
		<div class="container">
			<div class="item">
				<h3>基礎版</h3>
				<h4>保費680元</h4>
				<ul>
        			<li>螢幕破裂維修</li>
					<li>一年最高累計理賠金6000元<br>且理賠次數上限為三次</li>
				</ul>
			</div>
			<div class="item">
				<h3>升級版</h3>
				<h4>保費980元</h4>
				<ul>
        			<li>螢幕破裂維修</li>
					<li>一年最高累計理賠金9000元<br>且理賠次數上限為三次</li>
				</ul>
			</div>
			<div class="item">
				<h3>尊享版</h3>
				<h4>保費1280元</h4>
				<ul>
        			<li>螢幕破裂維修</li>
					<li>一年最高累計理賠金12000元<br>且理賠次數上限為三次</li>
				</ul>
			</div>
		</div>
	</fieldset>

	<br><br>

	<fieldset>
		<legend>手機全機險方案</legend>
		<div class="container">
			<div class="item">
				<h3>基礎版</h3>
				<h4>保費1880元</h4>
				<ul>
        			<li>意外螢幕破裂、意外泡水、意外輾毀</li>
					<li>一年最高累計理賠金15000元<br>且理賠次數上限為三次</li>
				</ul>
			</div>
			<div class="item">
				<h3>升級版</h3>
				<h4>保費2580元</h4>
				<ul>
        			<li>意外螢幕破裂、意外泡水、意外輾毀</li>
					<li>一年最高累計理賠金20000元<br>且理賠次數上限為三次</li>
				</ul>
			</div>
			<div class="item">
				<h3>尊享版</h3>
				<h4>保費3280元</h4>
				<ul>
        			<li>意外螢幕破裂、意外泡水、意外輾毀</li>
					<li>一年最高累計理賠金25000元<br>且理賠次數上限為三次</li>
				</ul>
			</div>
		</div>
	</fieldset>

	<br><br>

	<a href="PDF/CautionsForMobileInsurance2.pdf">投保注意事項</a>

	<div style="text-align: center;">
		<form action="InsurePage.jsp" method="post">
			<button type="submit">我要投保</button>
		</form>
	</div>

</body>
</html>
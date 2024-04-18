<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Register Page</title>
<style>
@import
	url('https://fonts.googleapis.com/css2?family=Acme&display=swap');

body {
	background-color: #f0f8ff;
}

header {
	width: 100%;
}

.menu {
	width: 100%;
	overflow: auto;
	background-color: #336666;
	border-radius: 5px;
	list-style-type: none;
}

.menu li {
	width: 7em;
	line-height: 2.5em;
	float: left;
}

.menu li a {
	display: block;
	text-align: center;
	color: #D1E9E9;
}

.menu li a:link {
	text-decoration: none;
}

.menu li a:hover {
	background-color: #5CADAD;
	color: #ffffff;
	border-radius: 5px;
}

form {
	width: 50%;
	margin: 10px auto;
}

fieldset {
	background-color: #D2E9FF;
	border: 3px double #616130;
}

legend {
	/* color: #0080ff; */
	/* border: 1px dotted #2894ff; */
	/* background-color: #D1E9E9; */
	border-radius: 5px;
	/* text-align: center; */
	padding: 5px;
	/*隔一段距離不能用padding 但使文字跟其框線有距離可用 */
	margin-left: 30px;
	font-family: 'Acme', sans-serif;
	font-size: larger;
	font-weight: 900;
}

p {
	color: #0056b3;
	font-size: smaller;
	margin: 0 0 20px 0;
}

h2 {
	text-align: center;
}

#submitButton {
		padding: 10px;
		border: 1px solid #ccc;
		border-radius: 5px;
		box-sizing: border-box;
		font-size: 16px;
		display: inline-block;
		background-color: #007bff;
		color: #fff;
		cursor: pointer;
		transition: background-color 0.3s;
}

</style>
</head>

<body>
	<h2>註冊新會員</h2>
	<form action="RegisterServlet.do" method="post">
		<fieldset>
			<legend>會員註冊信息</legend>
			
			<label for="name">姓名:&nbsp;</label>
			<input type="text" name="userName" id="name" required /><br>
			
			<label for="date">出生年月日:&nbsp;</label>
			<input type="text" name="userbirthDate" id="date" required placeholder="2000/01/01" autocomplete="off" /><br>
			<span id="warn_date"></span>
			<p>(格式:西元年/月/日[yyyy/MM/dd])</p>

			<label for="sex">性別:&nbsp;</label> 
			<label><input type="radio" name="userGender" value="M" id="" required checked>男</label>
			<label><input type="radio" name="userGender" value="F" id="">女</label><br> 

			<label for="mail">信箱:&nbsp;</label>
			<input type="text" name="userMail" id="mail" required /><br> <span id="warn_mail"></span><br>
			
			<label for="phone">電話1:&nbsp;</label>
			<input type="text" name="phone" id="phone" required /><br> 
			
			<label for="phone2">電話2:&nbsp;</label>
			<input type="text" name="phone2" id="phone2" required /><br> 
			
			<label for="address">地址:&nbsp;</label>
			<input type="text" name="address" id="address" required /><br>
		</fieldset>
		<fieldset>
			<legend>註冊帳號密碼</legend>

			<label for="account">帳號:&nbsp;</label>
			<input type="text" name="userAccount" required /><br>
			
			<label for="pwd">密碼:&nbsp;</label>
			<input type="password" name="userPwd" id="pwd" required autocomplete="off" /><br> <span id="warn_pwd"></span>
			<p>(1.不可空白&nbsp;2.至少6個字且必須包含英數字、特殊字元[!@#$%^&*`])&nbsp;3.最多20個字</p>

			<label for="pwd">再次輸入密碼:&nbsp;</label>
			<input type="password" id="checkPassword" name="checkPassword" onblur="validateDate()" required /><br>

		</fieldset>
		<br>
		<input type="submit" id="submitButton" value="申請註冊" />
	</form>

	<script>
		////----------mail check-----------------
		document
				.getElementById("mail")
				.addEventListener(
						"blur",
						function() {
							let mailobject = document.getElementById("mail")
							let mailValue = mailobject.value
							let mailLength = mailValue.length
							console.log(mailValue)

							let printMailWarn = document
									.getElementById("warn_mail")
							let flag_mail = false;

							if (mailValue == "")
								printMailWarn.innerHTML = "<img src='images/error.png'>不可空白"
							else if (mailLength <= 50) {
								let checkmail = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/
								if (checkmail.test(mailValue)) {
									printMailWarn.innerHTML = "<img src='images/correct.png'>成功"
								} else {
									printMailWarn.innerHTML = "<img src='images/error.png'>失敗，請確認格式!"
								}
							} else if (mailLength > 50) {
								printMailWarn.innerHTML = "<img src='images/error.png'>不能超過50位"
							}

						})
		////---------------pwd check-----------------
		document
				.getElementById("pwd")
				.addEventListener(
						"blur",
						function() {
							let pwdObject = document.getElementById("pwd")
							let pwdValue = pwdObject.value;
							let pwdCount = pwdValue.length;
							console
									.log(`使用者所輸入的密碼為: ${pwdValue}，長度為: ${pwdCount}`)
							let printPwdWarn = document
									.getElementById("warn_pwd")
							let ch;
							let flag1 = false, flag2 = false, flag3 = false, flag4 = true;

							if (pwdValue == "") //當未輸入
								printPwdWarn.innerHTML = "<img src='images/error.png'>不可空白";
							else if (pwdCount >= 6 && pwdCount <= 20) {
								for (let i = 0; i < pwdCount; i++) { //將密碼中的字符一個個抓出來比對
									ch = pwdValue.charAt(i).toUpperCase();
									// console.log(ch);
									if (ch >= "A" && ch <= "Z")
										flag1 = true;
									else if (ch >= "0" && ch <= "9")
										flag2 = true;
									else if (ch == "!" || ch == "@"
											|| ch == "#" || ch == "$"
											|| ch == "%" || ch == "^"
											|| ch == "&" || ch == "`")
										flag3 = true;
									else if (ch = " ")
										flag4 = false;
									if (flag1 && flag2 && flag3)
										break;
								}//for end
								if (flag4 == true) {
									if (flag1 && flag2 && flag3)
										printPwdWarn.innerHTML = "<img src='images/correct.png'>成功"
									else if (flag1 && flag2)
										printPwdWarn.innerHTML = "<img src='images/error.png'>必須包含以下特殊符號: !@#$%^&`"
									else if (flag1 && flag3)
										printPwdWarn.innerHTML = "<img src='images/error.png'>必須包含數字"
									else if (flag2 && flag3)
										printPwdWarn.innerHTML = "<img src='images/error.png'>必須包含英文字母"
									else
										printPwdWarn.innerHTML = "<img src='images/error.png'>失敗"

								} else {
									printPwdWarn.innerHTML = "<img src='images/error.png'>密碼中不可有空白"
								}
							} else {
								printPwdWarn.innerHTML = "<img src='images/error.png'>密碼長度必須>=6或<=20";
							}
						});
		//////--------------- date-----------------
		document.getElementById("date").addEventListener("blur", check_date);

		function check_date() {
			/*---------分別取得自己輸入的年月日----------*/
			let dateObject = document.getElementById("date");
			let dateValue = dateObject.value;
			console.log(dateValue)
			let dateArray = dateValue.split("/");
			let year = parseInt(dateArray[0]);
			let month = parseInt(dateArray[1]);
			let day = parseInt(dateArray[2]);
			// console.log(typeof year)
			// console.log("Year: " + year);
			// console.log("Month: " + month);
			// console.log("Day: " + day);

			/*---用輸入值創一個date物件-並取得對應的年月日---*/
			let input_date = new Date(dateValue);
			let date_check_y = input_date.getFullYear();
			// console.log(typeof date_check_y)
			let date_check_m = input_date.getMonth() + 1;
			let date_check_d = input_date.getDate();

			/*------------找到寫警告的span------------*/
			let printDateWarm = document.getElementById("warn_date");
			/*------------開始檢查------------*/
			let check_format = /^[\d\/]+$/

			if (dateValue == "")
				printDateWarm.innerHTML = "<img src='images/error.png'>不可為空白"
			else if (check_format.test(dateValue) == false)
				printDateWarm.innerHTML = "<img src='images/error.png'>您所輸入的格式錯誤"
			else if (input_date) {
				if (year == date_check_y && month == date_check_m
						&& day == date_check_d)
					printDateWarm.innerHTML = "<img src='images/correct.png'>成功"
				else
					printDateWarm.innerHTML = "<img src='images/error.png'>沒有這個日期"
			}
		}
		///再次輸入密碼驗證
		function validateDate() {
			var pwdInput = document.getElementById('pwd').value;
			var pwdValidation = document.getElementById('checkPassword').value;
			var submitButton = document.getElementById('submitButton');

			if (pwdInput === pwdValidation) {
				submitButton.disabled = false;
			} else {
				submitButton.disabled = true;
				alert("密碼不一致，请重新输入。");
			}
		}
	</script>

</body>

</html>
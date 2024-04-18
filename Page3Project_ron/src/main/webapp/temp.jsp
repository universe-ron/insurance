<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IMIE 輸入</title>
<style>
    body {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
    padding: 0;
    font-size: 16px; /* 預設字體大小，可根據需求調整 */
}

form {
    text-align: center;
}

/* 可以分別針對標籤、類別或 ID 調整特定元素的字體大小 */
label, input, select {
    font-size: 2rem; /* 這將使表單元素的字體大小與 body 的基本字體大小相同 */
}

/* 或者，增加特定元素的字體大小 */
label {
    font-size: 2rem; /* 標籤字體稍大一些 */
}
button {
    font-size: 2rem; /* 標籤字體稍大一些 */
}
input, select {
    font-size: 2rem; /* 輸入框和選擇框使用標準字體大小 */
    margin-right: 20px;
}

</style>
</head>
<body>
<%
  
    String pname = request.getParameter("pname");
	String generations = request.getParameter("generations");
	String type = request.getParameter("type");
	String iname = request.getParameter("iname");
    
%>


    <label for="data">請輸入要保裝置識別碼：</label>
    <input type="text" id="data" name="data">
    <br>
    <button onclick="submitForm()">送出</button>


<script>
function submitForm() 
{
    var check = document.getElementById('data').value;
    var pname = '<%= pname %>';
    var generations = '<%= generations %>';
    var type = '<%= type %>';
    var iname = '<%= iname %>';
    
    if (check.length>5) 
    {
        var form = document.createElement('form');
        form.setAttribute('action', 'WriteInsurance.do');
        form.setAttribute('method', 'post');
        form.style.display = 'none'; // 隱藏表單

        var input = document.createElement('input');
        input.setAttribute('type', 'hidden');
        input.setAttribute('name', 'pname');
        input.setAttribute('value', pname);
        form.appendChild(input);
        
        var input2 = document.createElement('input');
        input2.setAttribute('type', 'hidden');
        input2.setAttribute('name', 'generations');
        input2.setAttribute('value', generations);
        form.appendChild(input2);
        
        var input3 = document.createElement('input');
        input3.setAttribute('type', 'hidden');
        input3.setAttribute('name', 'type');
        input3.setAttribute('value', type);
        form.appendChild(input3);
        
        var input4 = document.createElement('input');
        input4.setAttribute('type', 'hidden');
        input4.setAttribute('name', 'iname');
        input4.setAttribute('value', iname);
        form.appendChild(input4);
        
        var input5 = document.createElement('input');
        input5.setAttribute('type', 'hidden');
        input5.setAttribute('name', 'data');
        input5.setAttribute('value', check);
        form.appendChild(input5);
        
        // 添加表單到 body 中
        document.body.appendChild(form);

        form.submit();
    } else {
        alert('請務必輸入正確的識別碼。');
    }
}

</script>
</body>
</html>
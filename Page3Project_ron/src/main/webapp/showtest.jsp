<%@ page import="com.controller.Page3Dao" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        .outer-container {
            height: 400px;
            width: 80%;
            overflow-y: scroll;
            border: 1px solid #ccc;
            margin: 20px auto;
        }

        .inner-content {
            padding: 20px;
        }

        .additional-info {
            width: 60%;
            margin: 20px auto;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        /* 样式化 checkbox label */
        .checkbox-label {
            display: flex;
            align-items: center;
        }

        .checkbox-label input {
            margin-right: 5px;
        }

        /* 新增的樣式 */
        #amount, .checkbox-label {
            flex: 10;
            margin-right: 10px;
        }
        button {
            flex: 1;
            margin-right: 10px;
        }
        
    </style>
    <title>滾動框</title>
</head>
<body>
	<button onclick="doChange()">測試用</button>
    <div class="outer-container">
        <div class="inner-content" id="dynamicContent">
            <!-- 這裡將用 JavaScript 動態載入內容 -->
        </div>
    </div>

    <div class="additional-info">
        <div id="amount">金額：<span id="amountValue">0</span> NT</div>
        <label class="checkbox-label">
            <input type="checkbox" id="agreeCheckbox"> 我以詳閱並同意上方條列之所有事項。
        </label>
        <button onclick="submitForm()">送出</button>
    </div>








    <script>
        
        function loadContent(contentFile) {
            var xhr = new XMLHttpRequest();
            xhr.open('GET', contentFile, true);

            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    document.getElementById('dynamicContent').innerHTML = xhr.responseText;
                }
            };

            xhr.send();
            <%!int pid,price; %> 
			<%!String Itype; %> 
			<%
				Page3Dao pd = new Page3Dao();
				Itype = "全險";
				pid = pd.findPid("iphone", "15", "Pro");
				price = pd.findPrice(pid);
				
				if("全險".equals(Itype))
				{
					
				    out.println("document.getElementById('amountValue').innerText = " + price*0.15 + ";");
				    
				}else{
					
				    out.println("document.getElementById('amountValue').innerText = " + price*0.1 + ";");
				    
				}
				
			%>
            
        }

		function doChange(){
			var Itype = "險";
			if("全險"==Itype)
			{
				loadContent('InsuranceNotice1.html');
			}else{
				loadContent('InsuranceNotice2.html');
			}
			

		}
		
		function submitForm() 
		{
	        var agreeCheckbox = document.getElementById('agreeCheckbox');

	        if (agreeCheckbox.checked) 
	        {
	            var form = document.createElement('form');
	            form.setAttribute('action', 'temp.jsp');
	            form.setAttribute('method', 'post');
	            form.style.display = 'none'; // 隱藏表單

	            var input = document.createElement('input');
	            input.setAttribute('type', 'hidden');
	            input.setAttribute('name', 'pname');
	            input.setAttribute('value', 'pname1');
	            form.appendChild(input);
	            
	            var input2 = document.createElement('input');
	            input2.setAttribute('type', 'hidden');
	            input2.setAttribute('name', 'generations');
	            input2.setAttribute('value', 'generations1');
	            form.appendChild(input2);
	            
	            var input3 = document.createElement('input');
	            input3.setAttribute('type', 'hidden');
	            input3.setAttribute('name', 'type');
	            input3.setAttribute('value', 'type1');
	            form.appendChild(input3);
	            
	            var input4 = document.createElement('input');
	            input4.setAttribute('type', 'hidden');
	            input4.setAttribute('name', 'iname');
	            input4.setAttribute('value', 'name');
	            form.appendChild(input4);
	            // 添加表單到 body 中
	            document.body.appendChild(form);

	            form.submit();
	        } else {
	            alert('請同意條款再提交表單。');
	        }
	    }
    </script>

</body>
</html>
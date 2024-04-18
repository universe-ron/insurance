<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

       <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品選擇</title>
    <style>
        body {
    font-family: 'Arial', sans-serif;
    background-color: #fff; /* 白色 */
    margin: 0;
    padding: 0;
}

header {
    background-color: #333; /* 黑色 */
    padding: 10px;
    text-align: right;
    color: #fff;
}

header button {
    background-color: #666; /* 灰色 */
    color: #fff;
    border: none;
    padding: 8px 16px;
    margin-left: 10px;
    cursor: pointer;
}

#form1 {
    width: 80%;
    margin: auto;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    align-items: center;
    margin-top: 20px;
}

select {
    width: 150px;
    padding: 10px;
    margin-bottom: 15px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-sizing: border-box;
    font-size: 16px;
    display: inline-block;
}

button {
    background-color: #333; /* 黑色 */
    color: #fff;
    cursor: pointer;
    transition: background-color 0.3s;
    padding: 10px;
    margin-bottom: 15px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-sizing: border-box;
    font-size: 16px;
    display: inline-block;
}

button:hover {
    background-color: #666; /* 灰色 */
}

.data-container {
    width: 80%;
    height: 400px;
    margin: auto;
    margin-top: 20px;
    padding: 20px;
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    overflow: auto;
    max-height: 400px;
}

.slider-container {
    width: 30%;
    margin-top: 20px;
    margin-left: 10px;
    padding: 20px;
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    overflow: auto;
    max-height: 400px;
}

.checkbox-container {
    margin-top: 20px;
    display: flex;
    align-items: center;
}

.inline-elements {
    display: inline-block;
    margin-right: 100px;
}

footer {
    text-align: center;
    margin-top: 20px;
    color: #333; /* 黑色 */
}

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
#button1 {
    flex: 1;
    margin-right: 10px;
}

#headerstyle {
    display: inline-block;
}

 		
    </style>
</head>
<body>

<header>
    <form action="HomePage.jsp" method="post" id="headerstyle">
        <button type="submit">首頁</button>
    </form>
    <form action="LogoutServlet.do" method="post" id="headerstyle">
        <button type="submit">登出</button>
    </form>
    <form action="OP.jsp" method="post" id="headerstyle">
        <button type="submit">會員中心</button>
    </form>
</header>

  <form id="form1" action="SubmitServlet.do" method="post" onsubmit="return validateForm()">
  
<!-- 第一個下拉選單 -->
<select id="productSelect" name="productSelect" onclick="getModels()">
	<option value="">產品種類</option>
    <option value="iPhone" ${selectedProduct == 'iPhone' ? 'selected' : ''}>iPhone</option>
</select>
<span>產品 &nbsp;</span>

<!-- 第二個下拉選單 -->
<select id="sizeSelect" name="sizeSelect" onclick="getModelsBySize()">
    <option value="${selectedSize == '' ? '' : selectedSize}"> ${selectedSize == '' ? '請先選擇產品' : selectedSize}</option>
    
</select>
<span>代 &nbsp;</span>

<!-- 第三個下拉選單 -->
<select id="modelSelect"  name="modelSelect">
    <option value="${selectedModel == '' ? '' : selectedModel}"> ${selectedModel == '' ? '請先選擇幾代' : selectedModel}</option>
</select>
<span>機型 &nbsp;</span>

<!-- 第四個下拉選單 -->
<select id="categorySelect"  name="categorySelect">
    <option value="全險" ${selectedCategory == '全險' ? 'selected' : ''}>全險</option>
    <option value="螢幕險" ${selectedCategory == '螢幕險' ? 'selected' : ''}>螢幕險</option>
</select>
<span>投保類型 &nbsp;</span>

<button type="submit" class="bt1">查詢</button>
<label id="validationLabel" style="color: red;"></label>


</form>

<div class="outer-container">
        <div class="inner-content" id="dynamicContent">
            
        </div>
</div>

<div class="additional-info">
    <div id="amount">金額：<span id="amountValue" >${price == '' ? '0' : price}</span> NT</div>
    <label class="checkbox-label">
        <input type="checkbox" id="agreeCheckbox"> 我以詳閱並同意上方條列之所有事項。
    </label>
    <button id="button1" onclick="submitForm()">送出</button>
</div>

<footer>
    © 2024 商品選擇. All rights reserved.
</footer>

<script>
	window.onload = function() 
	{	
	    loadContent(); // 載入預設的內容
	 
	};


    function getModels() {
        var productSelect = document.getElementById("productSelect");
        var sizeSelect = document.getElementById("sizeSelect");
        
        // 清空尺寸、機型和投保類型下拉選單
        sizeSelect.innerHTML = "<option value=''>選擇幾代</option>";
        document.getElementById("modelSelect").innerHTML = "<option value=''>請先選擇幾代</option>";
        
        // 如果選擇的是iPhone
        if (productSelect.value === "iPhone") {
            var models = ["15", "14", "13", "SE"];
            for (var i = 0; i < models.length; i++) {
                sizeSelect.options.add(new Option(models[i], models[i]));
            }
           
        }
    }
    
    function getModelsBySize() {
        var sizeSelect = document.getElementById("sizeSelect");
        var modelSelect = document.getElementById("modelSelect");

        // 清空機型下拉選單
        document.getElementById("modelSelect").innerHTML = "<option value=''>選擇機型</option>";

        // 定義尺寸和相應機型的對象
        var sizeModels = {
            "15": ["Pro", "Pro Max", "Plus", "normal"],
            "14": ["Plus", "normal"],
            "13": ["normal"],
            "SE": ["normal"]
        };

        // 如果選擇的是尺寸
        var selectedSize = sizeSelect.value;
        if (sizeModels[selectedSize]) {
            var models = sizeModels[selectedSize];
            for (var i = 0; i < models.length; i++) {
                modelSelect.options.add(new Option(models[i], models[i]));
            }
        }
    }
    
    function validateForm() {
        // 驗證每個選單的值不為null
        var productValue = document.getElementById("productSelect").value;
        var sizeValue = document.getElementById("sizeSelect").value;
        var modelValue = document.getElementById("modelSelect").value;
        var categoryValue = document.getElementById("categorySelect").value;

        if (productValue === "") {
        	 // 修改label文字內容
            validationLabel.textContent = "尚未選擇產品!";
            return false; // 阻止表單提交
        } 
        if (sizeValue === "") {
       	 // 修改label文字內容
           validationLabel.textContent = "尚未選擇第幾代!";
           return false; // 阻止表單提交
        } 
       	 if (modelValue === "") {
       		// 修改label文字內容
           validationLabel.textContent = "尚未選則機型!";
           return false; // 阻止表單提交
      	 } 
        if (categoryValue === "") {
       	 // 修改label文字內容
           validationLabel.textContent = "尚未選擇種類!";
           return false; // 阻止表單提交
       } else {
           // 清空label文字內容
           validationLabel.textContent = "";
           return true; // 允許表單提交
  	 }
    }
    
    function loadContent() {
    	var Itype = document.getElementById('categorySelect').value;
    	var contentFile="";
    	if("全險"==Itype)
    	{
    		contentFile='InsuranceNotice1.html';
    		var xhr = new XMLHttpRequest();
            xhr.open('GET', contentFile, true);

            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    document.getElementById('dynamicContent').innerHTML = xhr.responseText;
                }
            };

            xhr.send();
    	}else if("螢幕險"==Itype){
    		contentFile='InsuranceNotice2.html';
    		var xhr = new XMLHttpRequest();
            xhr.open('GET', contentFile, true);

            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    document.getElementById('dynamicContent').innerHTML = xhr.responseText;
                }
            };

            xhr.send();
    	}else{}
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
            input.setAttribute('value', "${selectedProduct}");
            form.appendChild(input);
            
            var input2 = document.createElement('input');
            input2.setAttribute('type', 'hidden');
            input2.setAttribute('name', 'generations');
            input2.setAttribute('value', "${selectedSize}");
            form.appendChild(input2);
            
            var input3 = document.createElement('input');
            input3.setAttribute('type', 'hidden');
            input3.setAttribute('name', 'type');
            input3.setAttribute('value', "${selectedModel}");
            form.appendChild(input3);
            
            var input4 = document.createElement('input');
            input4.setAttribute('type', 'hidden');
            input4.setAttribute('name', 'iname');
            input4.setAttribute('value', "${selectedCategory}");
            form.appendChild(input4);
            // 添加表單到 body 中
            document.body.appendChild(form);
			if(validateForm()){
				form.submit();
			}
            
        } else {
            alert('請同意條款再提交表單。');
        }
    }
    
</script>
</body>
</html>
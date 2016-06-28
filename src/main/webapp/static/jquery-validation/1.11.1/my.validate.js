//list列表复选框全选
function checkAll() { 
	var check = document.getElementsByTagName("input");
	var index1 = 0;
	var checkArr = new Array();
	for(var m = 0;m<check.length;m++){
		if(check[m].type == "checkbox"){
			checkArr[index1] = check[m];
			index1++;
			}
		}
	if(checkArr[0].checked== true){
		for(var i = 1;i<checkArr.length;i++){
			checkArr[i].checked = true;
		}
 	}else{
  		for(var i = 1;i<checkArr.length;i++){
			checkArr[i].checked = false;
		}
    }			
}

function checkChange(){
	var check = document.getElementsByTagName("input");
	//选中
	var count1 = 0;
	//未选中
	var count2 = 0;
	var checkArr = new Array();
	for(var m = 0;m<check.length;m++){
		if(check[m].type == "checkbox"){
			checkArr[checkArr.length] = check[m];
		}
	}
	for(var i = 1;i<checkArr.length;i++){
		if(i>0 && checkArr[i].checked == true){
			count1++;
		}
		else if(i>0 && checkArr[i].checked == false){
			count2++;
		}
	}
	if(count2 == 0){
		checkArr[0].checked = true;
		checkArr[checkArr.length-1].checked = true;
	}
	else{
		checkArr[0].checked = false;
	}
	
}

//获取复选框被选择个数
function getCheckCount() {
	var checkEle;
	var checkCount=0;
	var check = document.getElementsByTagName("input");
	var index1 = 0;
	var checkArr = new Array();
	for(var m = 0;m<check.length;m++){
		if(check[m].type == "checkbox"){
			checkArr[index1] = check[m];
			index1++;
			}
		}
	for(var i = 1;i<checkArr.length;i++){
		if(checkArr[i].checked == true)
		{
		   checkEle=checkArr[i];
		   checkCount+=1;
		}
	}
	if(checkCount==1)
		checkElement=checkEle;
	return checkCount;    	
}

function trim(str){   
	return str.replace(/&nbsp;/g,"").replace(/(^\s*)|(\s*$)/g, "");
}

//去掉输入框空格
function change(obj){
	if("" != obj.value && obj.value != null){
		obj.value = trim(obj.value);
	}
}

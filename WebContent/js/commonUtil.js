
/**
 * 表单组件
 */
var FormUtil = {
	initSelect : function(tablename){
		//查询所有数据
		$.ajax({
			url:"../common/getAll",
			data:{table:tablename},
			dataType:"json",
			success:function(data){
				var content = "<option value=' '>---请选择---</option><br/>";
				if(data.length>0){
					for(var i=0; i<data.length; i++){
						content+="<option value='"+data[i].id+"'>"+data[i].name+"</option><br/>";
					}
				}
				$("#"+tablename).append(content);
			}
		});
	},
	initAllSelect : function(){
		var select = $("select");
		for(var i=0; i<select.length;i++){
			var tablename = $(select[i]).attr("id");
			this.initSelect(tablename);
		}
	},
	initTopMenuSelect : function(id){
		//查询并组装一级下拉菜单
		$.ajax({
			url:"../menu/getAllTopMenu",
			dataType:"json",
			success:function(data){
				var content = "<option value=''>---请选择---</option><br/>";
				if(data.length>0){
					for(var i=0; i<data.length; i++){
						content+="<option value='"+data[i].id+"'>"+data[i].name+"</option><br/>";
					}
				}
				$("#"+id).append(content);
			}
		});
	},
	name : "weisihua"
};

/**
 * 删除数据
 * @param url
 * @param id
 */
function deleteObject(url,id){
	var flag = confirm("确认删除该条信息？");
	if(flag){
		$.ajax({
			url:url,
			data:{id:id},
			dataType:"json",
			success:function(data){
				if(data.flag == '0'){
					alert("数据删除成功！");
					document.forms[0].submit();
				}
				else{
					alert("数据删除失败！");
				}
			}
		});
	}
}
/**
 * 编辑数据
 * @param url
 * @param id
 */
function editObject(url,id){
	window.open(url+"?id="+id, "_self");
	/*$.ajax({
		url:url,
		data:{id:id},
		dataType:"json",
		success:function(data){
			if(data.flag == '0'){
				alert("数据删除成功！");
				document.forms[0].submit();
			}
			else{
				alert("数据删除失败！");
			}
		}
	});*/
}

/**
 * 分页查询
 */
function queryPage(pageNow){
	var form = document.forms[0];
	$(form).append("<input type='hidden' name='pageNow' value='"+pageNow+"'/>");
	form.submit();
}
/**
 * json转成str
 */
function jsonToString (obj){   
	var THIS = this;      
	switch(typeof(obj)){  
	    case 'string':     
	        return '"' + obj.replace(/(["\\])/g, '\\$1') + '"';     
	    case 'array':     
	        return '[' + obj.map(THIS.jsonToString).join(',') + ']'; 
		case 'boolean':
			return obj;
	    case 'object':   
	        if(obj instanceof Array){
	            var strArr = []; 
	            var len = obj.length;
	            for(var i=0; i<len; i++){
	                strArr.push(THIS.jsonToString(obj[i]));
	            }     
	            return '[' + strArr.join(',') + ']';
	        }else if(obj==null){
	            return 'null';     
	        }else{ 
	            var string = [];     
	            for (var property in obj) {
					
					string.push(THIS.jsonToString(property) + ':' + THIS.jsonToString(obj[property]));     
				}
	            return '{' + string.join(',') + '}';     
	        }     
	    case 'number':     
	        return obj;
	    case false:     
	        return obj;
	}     
}  
/**
 * json转成str 小写
 */
function jsonToStringToLowerCase(obj){     
    var THIS = this;      
    switch(typeof(obj)){  
        case 'string':     
            return '"' + obj.replace(/(["\\])/g, '\\$1') + '"';     
        case 'array':     
            return '[' + obj.map(THIS.jsonToString).join(',') + ']';     
        case 'object':   
             if(obj instanceof Array){
                var strArr = []; 
                var len = obj.length;
                for(var i=0; i<len; i++){
                    strArr.push(THIS.jsonToString(obj[i]));
                }     
                return '[' + strArr.join(',') + ']';
            }else if(obj==null){
                return 'null';     

            }else{ 
                var string = [];     
                for (var property in obj) string.push(THIS.jsonToString(property).toLowerCase() + ':' + THIS.jsonToString(obj[property]));     
                return '{' + string.join(',') + '}';     
            }     
        case 'number':     
            return obj;     
        case false:     
            return obj;     
    }     
}

/**
 * form表单转json
 * @param str
 * @returns {Object}
 */
function formTojson(str)
{
	var strs="#"+str;
    var obj=new Object();  
    /*$.each($(strs).serializeArray(), function() {  
    	var namelow = 	this.name.toLowerCase();
        if (obj[namelow]) {  
            if (!obj[namelow].push) {  
            	obj[namelow] = [obj[namelow]];  
            }  
            obj[namelow].push(this.value || '');  
        } else {  
        	obj[namelow] = this.value || '';  
        }  
    });*/
   //alert(jsonToString(obj));
    $.each($(strs).serializeArray(),function(index,param){
    	var paramlow = 	param.name.toLowerCase();
    	
    	//IE10在input框使用“X”按钮时，隐藏的输入项的值没有清除掉的情况
//    	var pre = document.getElementsByName(param.name)[0].previousSibling;
//    	if(pre && pre.type == "text" && pre.value != document.getElementsByName(param.name)[0].value) {
//    		document.getElementsByName(param.name)[0].value = pre.value;
//    	}
    	
        if(!(paramlow in obj)){
	        //日期 正则
	        var reg=/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/; 
	        if(param.value.match(reg)){//是 yyyy-MM-dd格式的 则变成 yyyy-MM-dd 00:00:00
	        	param.value = param.value+" 00:00:00";
	        }
	        
	        obj[paramlow]=param.value;  
        }else{
        	 obj[paramlow]= obj[paramlow] + "," +param.value;  
        }
    });  
    return obj;  
}

/**
 * 字符串转JSON
*/
function strToJson(str){ 
	var json = (new Function("return " + str))(); 
	return json; 
}

/**
 * 打开弹窗
 * @param title 标题
 * @param width 宽度
 * @param height 高度
 * @param url 链接
 */
function openWin(title,width,height,url){
	$("#dialog").attr("title",title);
	$("#dialog_content").attr("src",url);
	//$("#dialog_content").attr("width",width+'px');
	//$("#dialog_content").attr("height",height+'px');
	if(!width){
		width = 250;
	}
	if(!height){
		height = 200;
	}
	$('#dialog').dialog({
		modal:true,
		width:width,
		height:height
	});
}

/**
 * 打开弹窗
 * @param title 标题
 * @param width 宽度
 * @param height 高度
 * @param url 链接
 */
function openWin4Role(title,width,height,url,roleId){
	$("#dialog").attr("title",title);
	$("#dialog_content").attr("src",url+"?roleId="+roleId);
	//$("#dialog_content").attr("width",width+'px');
	//$("#dialog_content").attr("height",height+'px');
	if(!width){
		width = 250;
	}
	if(!height){
		height = 200;
	}
	$('#dialog').dialog({
		modal:true,
		width:width,
		height:height
	});
}
function closeWin(){
	parent.$("#dialog").dialog("close");
}
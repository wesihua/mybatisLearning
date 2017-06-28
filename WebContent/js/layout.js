window.onload = function(){
	var pageHeight = document.body.scrollHeight || document.documentElement.scrollHeight;
	var bottom = document.getElementsByClassName("bottom")[0];
	var clientHeight = getViewPort();
	/*if(pageHeight > clientHeight){
		//alert('出现滚动条了');
		bottom.style.position= "static";
	}
	else{
		bottom.style.position = "fixed";
	}*/
	//iframe高度自适应
	autoFrameHeight("myframe");
	//自适应左右两个div的高度
	setSideHeight();
};

//var pageHeight = document.body.scrollHeight || document.documentElement.scrollHeight;
//var bottom = document.getElementsByClassName("bottom")[0];
//var clientHeight = getViewPort();
//if(pageHeight > clientHeight){
//	//alert('出现滚动条了');
//	bottom.style.position= "static";
//}
//else{
//	bottom.style.position = "fixed";
//}
//alert("页面内容高度："+height+"  可视区域高度："+clientHeight);

//获取页面视口大小
function getViewPort(){
	if(document.documentElement){
		return document.documentElement.clientHeight;
	}
	else{
		return document.body.clientHeight;
	}
}
//获取iframe的内容高度
function getIframePageHeight(frameId){
	var pageHeight;
	var frame = document.getElementById(frameId);
	//获取内部document对象
	var subDoc = frame.contentDocument || frame.contentWindow.document;
	if(subDoc.documentElement){
		pageHeight = subDoc.documentElement.scrollHeight;
	}
	else{
		pageHeight = subDoc.body.scrollHeight;
	}
	return pageHeight;
}

//iframe高度自适应
function autoFrameHeight(frameId){
	var pageHeight = getIframePageHeight(frameId);
 	var frame = document.getElementById(frameId);
	frame.style.height = pageHeight+"px";
}	

//在不出现滚动条的情况下，设置左右顶住上下
function setSideHeight(){
	var pageHeight = getViewPort();
	var iframeHeight = getIframePageHeight("myframe");
	var left = document.getElementsByClassName("left")[0];
	var right = document.getElementsByClassName("right")[0];
	var middle = document.getElementsByClassName("middle")[0];
	if(iframeHeight < pageHeight){
		left.style.height = pageHeight*0.9+"px";
		middle.style.height = pageHeight*0.9+"px";
		right.style.marginTop = "2px";
		document.getElementById("myframe").style.height = pageHeight*0.89+"px";
		//document.getElementById("myframe").style.border = "0px";
	}
	else{
		left.style.height = pageHeight+"px";
	}
	//right.style.height = pageHeight * 0.9;
}
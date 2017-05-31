</div>
</div>
<script type="text/javascript">


$(function (){
    $('.tabular .item').tab();
    $('.ui.checkbox').checkbox();
    setRCHeight();
});
function setRCHeight(){
	var obj=document.getElementById("middleBody");
	var rightContent=document.getElementById("rightContent");
	var style=null;
	if(window.getComputedStyle){
		style=window.getComputedStyle(obj,null);
	}
	else{
		style=obj.currentStyle;
	}
	rightContent.style.height=style.height+200;
	//alert("middleBody height:"+style.height);
}
</script>
</body>
</html>
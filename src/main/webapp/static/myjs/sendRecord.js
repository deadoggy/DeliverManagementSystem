/**
 * Created by luminous on 6/10/2017.
 */
$(function (){
    $("#seg3").addClass("active");

});

function querySend(){
    var goodId = document.getElementById("goodId").value;
    if(goodId == ""){
        $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>请输入单号</p></div>');
        return;
    }
    $.ajax({
        type: "GET",
        url: "/sendrecord/" + goodId,
        dataType: "json",
        success: function(res){
            console.log(res);
            if(res.status == "ok"){
                var str = '';
                var data = res.sendingRecordList;
                var len = (data.length > 10)? 10:data.length;
                for(var i = 0; i < len; i++){
                    str +='<tr><td>'+data[i].id+'</td>'+'<td>'+data[i].sName + '：'+data[i].sTele+'</td>'+'<td>'+data[i].sProvince + '：'+data[i].sCity+'</td>'
                        +'</td>'+'<td>'+data[i].rName + '：'+data[i].rTele+'</td>'+'<td>'+data[i].rProvince + '：'+data[i].rCity+'</td>'+'<td>'+data[i].weight+'</td></tr>';
                }
                $("#sendContent").html(str);
            }else{
                $("#sendContent").html("");
                //$.fancybox.open('<div class="message"><h2>Sorry!</h2><p>'+ res.reason + '</p></div>');
            }
        }
    });
}
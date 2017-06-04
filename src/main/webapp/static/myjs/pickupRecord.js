$(function (){
    $("#seg2").addClass("active");
});

function pickuptQuery(){
    var goodId = document.getElementById("goodId").value;
    $.ajax({
        type: "GET",
        url: "/package/all",
        dataType: "json",
        success: function(res){
            if(res.status == "ok"){
                var str = '';
                var data = res.packages;
                var len = (data.length > 10)? 10:data.length;
                for(var i = 0; i < len; i++){
                    str +='<tr><td>'+data[i].mPackageId+'</td>'+'<td>'+data[i].mPackageId+'</td>'+'<td>'+data[i].mPackageId+'</td>'+'<td>'+data[i].mTakenTime+'</td></tr>';
                }
                $("#pickupContent").html(str);
            }else{
                $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>'+ res.reason + '</p></div>');
            }
        }
    });
}

//function oninput(){}
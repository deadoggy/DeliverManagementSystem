$(function (){
    $("#seg2").addClass("active");

    getAllTaken();
});

function pickupQuery(){
    var goodId = document.getElementById("goodId").value;
    $.ajax({
        type: "GET",
        url: "/package/taken/id?id=" + goodId,
        dataType: "json",
        success: function(res){
            console.log(res);
            if(res.status == "ok"){
                var str = '';
                var data = res.packageList;
                var len = (data.length > 10)? 10:data.length;
                for(var i = 0; i < len; i++){
                    str +='<tr><td>'+data[i].packageId+'</td>'+'<td>'+data[i].position+'</td>'+'<td>'+data[i].rName+'</td>'+'<td>'+data[i].takenTime+'</td></tr>';
                }
                $("#pickupContent").html(str);
            }else{
                $("#pickupContent").html("");
                //$.fancybox.open('<div class="message"><h2>Sorry!</h2><p>'+ res.reason + '</p></div>');
            }
        }
    });
}

function pickupClickEvent(){
    var goodId = document.getElementById("goodId").value;
    if(goodId == ""){
        $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>查询条件不得为空</p></div>');
    }else{
        pickupQuery();
    }
}

function getAllTaken() {
    $.ajax({
        type: "GET",
        url: "/package/alltaken",
        dataType: "json",
        success: function (res) {
            console.log(res);
            if (res.status == "ok") {
                var str = '';
                var data = res.packageList;
                var len = (data.length > 10)? 10:data.length;
                for(var i = 0; i < len; i++){
                    str +='<tr><td>'+data[i].packageId+'</td>'+'<td>'+data[i].position+'</td>'+'<td>'+data[i].rName+'</td>'+'<td>'+data[i].takenTime+'</td></tr>';
                }
                $("#pickupContent").html(str);
            } else {
                $("#pickupContent").html("");
                //$.fancybox.open('<div class="message"><h2>Sorry!</h2><p>' + res.reason + '</p></div>');
            }
        }
    });
}
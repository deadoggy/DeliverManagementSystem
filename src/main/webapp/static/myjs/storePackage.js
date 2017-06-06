/**
 * Created by luminous on 5/30/2017.
 */
$(function (){
    $("#seg1").addClass("active");
    $("#dropdownBtn").dropdown();
});

function store(){
    var goodId = document.getElementById("goodId").value;
    var storageId = document.getElementById("storageId").value;
    var pos = document.getElementById("menu").getElementsByClassName("item active selected");
    var passCheckTrue = document.getElementById("passCheckTrue");
    var passCheckFalse = document.getElementById("passCheckFalse");

    if(pos.length == 0){
        $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>请选择存放位置!</p></div>');
    }
    else if(passCheckFalse.checked == true){
        $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>未通过安检快件不得入库!</p></div>');
    }
    else{
        var posHTML = pos[0].getElementsByClassName("text")[0];
        var posString = posHTML.innerText;
        var pos = "true";
        if(posString == "货架") pos = "false";
        $.ajax({
            type: "POST",
            // TODO
            url: "/package?companyName=顺风&receiverName=zhang&receiverTele=123465789&id=" + goodId + "&cupOrShelf="+ pos + "&storageId=" + storageId,
            // data: {
            //     id: goodId,
            //     companyName: "顺风",
            //     receiverName: "zhang",
            //     receiverTele: "123465789",
            //     cupOrShelf: pos, //true: cup
            //     storageId: storageId
            // },
            dataType: "json",
            success: function(res){
                if(res.status == "ok"){
                    $.fancybox.open('<div class="message"><h2>Success!</h2><p>成功入库</p></div>');
                }else{
                    //console.log(res.reason);
                    $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>'+ res.reason + '</p></div>');
                }
            }
        });
    }
}

function generatePos(){
    var pos = document.getElementById("menu").getElementsByClassName("item active selected");
    if(pos.length == 0){
        $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>请选择存放位置!</p></div>');
    }else{
        var posHTML = pos[0].getElementsByClassName("text")[0];
        var posString = posHTML.innerText;
        if(posString == "货架"){
            $.ajax({
                type: "GET",
                url: "/shelf/position",
                dataType: "json",
                success: function(res){
                    if(res.status == "ok"){
                        document.getElementById("storageId").value = (res.layer).toString() + (res.column).toString();
                    }else{
                        $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>'+ res.reason + '</p></div>');
                    }
                }
            });
        }else{
            $.ajax({
                type: "GET",
                url: "/smartcupboard/position",
                dataType: "json",
                success: function(res){
                    if(res.status == "ok"){
                        document.getElementById("storageId").value = (res.layer).toString() + (res.column).toString();
                    }else{
                        $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>'+ res.reason + '</p></div>');
                    }
                }
            });
        }
    }
}
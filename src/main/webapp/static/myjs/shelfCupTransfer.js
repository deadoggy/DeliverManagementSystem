$(function (){
    $(".dropdown.button").dropdown();

    $("#seg1").addClass("active");
})/**
 * Created by luminous on 5/30/2017.
 */

function shelfCupTransfer(){
    var menuFrom = document.getElementById("menuFrom").getElementsByClassName("item active selected");
    var menuTo = document.getElementById("menuTo").getElementsByClassName("item active selected");

    var fromPos = document.getElementById("fromPos").value;
    var toPos = document.getElementById("toPos").value;

    if(menuFrom.length == 0 || menuTo.length == 0){
        $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>请选择存放位置!</p></div>');
    }
    else if(fromPos == "" || toPos==""){
        $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>请输入存放位置!</p></div>');
    }
    else{
        // TODO
        $.ajax({
            type: "POST",
            url: "/transform/id?fStorageId=" + fromPos + "&tStorageId=" + toPos,
            dataType: "json",
            success: function(res){
                if(res.status == "ok"){
                    $.fancybox.open('<div class="message"><h2>Success!</h2><p>成功更换位置!</p></div>');
                    document.getElementById("fromPos").value = "";
                    document.getElementById("toPos").value = "";
                }else{
                    $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>'+ res.reason + '</p></div>');
                }
            }
        });
    }
}

function generateToPos(){
    var pos = document.getElementById("menuTo").getElementsByClassName("item active selected");
    if(pos.length == 0){
        $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>请选择转移位置!</p></div>');
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
                        document.getElementById("toPos").value = res.storageId;
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
                        document.getElementById("toPos").value = res.storageId;
                    }else{
                        $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>'+ res.reason + '</p></div>');
                    }
                }
            });
        }
    }
}
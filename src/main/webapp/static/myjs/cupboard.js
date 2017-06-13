/**
 * Created by luminous on 5/30/2017.
 */
$(function (){
    $("#seg5").addClass("active");

    getAllCup();
});

function getAllCup(){
    $.ajax({
        type: "GET",
        url: "/smartcupboard/all",
        dataType: "json",
        success: function(res){
            if(res.status == "ok"){
                str = '';
                var data = res.cupboardList;
                var len = (data.length > 20)? 20:data.length;
                for(var i = 0; i < len; i++){
                    console.log(data[i]);
                    str +='<tr><td>'+data[i].cupboardId+'</td>'+'<td>'+data[i].sum+'</td>'+'<td>'+data[i].empty+'</td></tr>';
                }
                $("#cupContent").html(str);
            }else{
                $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>'+ res.reason + '</p></div>');
            }
        }
    });
}

function getAllShelf(){
    $.ajax({
        type: "GET",
        url: "/shelf/all",
        dataType: "json",
        success: function(res){
            if(res.status == "ok"){
                str = '';
                var data = res.shelfList;
                var len = (data.length > 20)? 20:data.length;
                for(var i = 0; i < len; i++){
                    str +='<tr><td>'+data[i].shelfId+'</td>'+'<td>'+data[i].sum+'</td>'+'<td>'+data[i].empty+'</td></tr>';
                }
                $("#cupContent").html(str);
            }else{
                $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>'+ res.reason + '</p></div>');
            }
        }
    });
}

function getAllCupShelf(){
    var pos = document.getElementById("pos").value;
    if(pos=="cup"){
        getAllCup();
    }
    else if(pos=="shelf") {
        getAllShelf();
    }
}

function getCupShelf(){
    var id = document.getElementById("id").value;
    var pos = document.getElementById("pos").value;
    if(pos=="cup"){
        $.ajax({
            type: "GET",
            url: "/smartcupboard/"+id,
            dataType: "json",
            success: function(res){
                if(res.status == "ok"){
                    str = '';
                    var data = res.cupboardList;
                    for(var i = 0; i < data.length; i++){
                        console.log(data[i]);
                        str +='<tr><td>'+data[i].cupboardId+'</td>'+'<td>'+data[i].sum+'</td>'+'<td>'+data[i].empty+'</td></tr>';
                    }
                    $("#cupContent").html(str);
                }else{
                    $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>'+ res.reason + '</p></div>');
                }
            }
        });
    }
    else if(pos=="shelf"){
        $.ajax({
            type: "GET",
            url: "/shelf/"+id,
            dataType: "json",
            success: function(res){
                if(res.status == "ok"){
                    str = '';
                    var data = res.shelfList;
                    var len = (data.length > 10)? 10:data.length;
                    for(var i = 0; i < len; i++){
                        str +='<tr><td>'+data[i].shelfId+'</td>'+'<td>'+data[i].sum+'</td>'+'<td>'+data[i].empty+'</td></tr>';
                    }
                    $("#cupContent").html(str);
                }else{
                    $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>'+ res.reason + '</p></div>');
                }
            }
        });
    }
}

function addNewCupShelf(){
    var pos = document.getElementById("newPos").value;
    var point = document.getElementById("point").value;
    var column = document.getElementById("column").value;
    var layer = document.getElementById("layer").value;
    var id = Math.floor((Math.random()) * 1000);
    //console.log(point);
    id = id.toString();
    if(id.length == 1) id = "00" + id;
    else if(id.length == 2) id = "0" + id;
    else if(id.length == 0) id = "000" + id;

    if(pos=="cup"){
        id = "2" + id;
        $.ajax({
            type: "POST",
            url: "/smartcupboard?id="+id+"&columnSum="+column+"&layerSum="+layer+"&businessId="+point,
            // data: {
            //     id: id,
            //     columnSum: column,
            //     layerSum: layer
            // },
            dataType: "json",
            success: function(res){
                if(res.status == "ok"){
                    $.fancybox.open('<div class="message"><h2>Success!</h2><p>成功添加智能柜，智能柜编号为：' + id + '</p></div>');
                }else{
                    $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>'+ res.reason + '</p></div>');
                }
            }
        });
    }
    else if(pos=="shelf"){
        id = "1" + id;
        $.ajax({
            type: "POST",
            url: "/shelf?id="+id+"&columnSum="+column+"&layerSum="+layer+"&businessId="+point,
            // data: {
            //     id: id,
            //     columnSum: column,
            //     layerSum: layer
            // },
            dataType: "json",
            success: function(res){
                if(res.status == "ok"){
                    $.fancybox.open('<div class="message"><h2>Success!</h2><p>成功添加货架，货架编号为：' + id + '</p></div>');
                }else{
                    $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>'+ res.reason + '</p></div>');
                }
            }
        });
    }

}

function cleanAdd(){
    document.getElementById("newPos").value = "cup";
    document.getElementById("point").value = "0";
    document.getElementById("column").value = "";
    document.getElementById("layer").value = "";
}
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
                var data = res.smartcupboards;
                var len = (data.length > 10)? 10:data.length;
                for(var i = 0; i < len; i++){
                    console.log(data[i]);
                    str +='<tr><td>'+data[i].mCupboardId+'</td>'+'<td>'+data[i].mPositionSum+'</td>'+'<td>'+data[i].mEmptySum+'</td></tr>';
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
                var data = res.shelfs;
                var len = (data.length > 10)? 10:data.length;
                for(var i = 0; i < len; i++){
                    str +='<tr><td>'+data[i].mShelfId+'</td>'+'<td>'+data[i].mPositionSum+'</td>'+'<td>'+data[i].mEmptySum+'</td></tr>';
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
                    var data = res.smartcupboard;
                    var str ='<tr><td>'+data.mCupboardId+'</td>'+'<td>'+data.mPositionSum+'</td>'+'<td>'+data.mEmptySum+'</td></tr>';
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
                    var data = res.shelf;
                    var str ='<tr><td>'+data.mShelfId+'</td>'+'<td>'+data.mPositionSum+'</td>'+'<td>'+data.mEmptySum+'</td></tr>';
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
    var column = document.getElementById("column").value;
    var layer = document.getElementById("layer").value;
    var id = Math.floor((Math.random()) * 1000);
    if(pos=="cup"){
        $.ajax({
            type: "POST",
            url: "/smartcupboard?id="+id+"&columnSum="+column+"&layerSum="+layer,
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
        $.ajax({
            type: "POST",
            url: "/shelf?id="+id+"&columnSum="+column+"&layerSum="+layer,
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
    document.getElementById("column").value = "";
    document.getElementById("layer").value = "";
}
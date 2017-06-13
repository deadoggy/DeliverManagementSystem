/**
 * Created by luminous on 5/30/2017.
 */
$(function (){
    $("#seg1").addClass("active");
    $("#dropdownBtn").dropdown();
    $('.ui.accordion').accordion();
});

function store(){
    var goodId = document.getElementById("goodId").value;
    var fee = document.getElementById("fee").value;
    var companyName = document.getElementById("companyName").value;
    var receiverName = document.getElementById("receiverName").value;
    var receiverTele = document.getElementById("receiverTele").value;
    var storageId = document.getElementById("storageId").value;
    var pos = document.getElementById("menu").getElementsByClassName("item active selected");
    var passCheckTrue = document.getElementById("passCheckTrue");
    var passCheckFalse = document.getElementById("passCheckFalse");

    if(goodId =="" || fee == "" || companyName == "" || receiverTele == "" || receiverName == ""){
        $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>请完整填写快递信息!</p></div>');
    }
    else if(pos.length == 0){
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
            url: "/package?companyName=" + companyName + "&receiverName=" + receiverName + "&receiverTele=" + receiverTele + "&id=" + goodId + "&cupOrShelf="+ pos + "&storageId=" + storageId + "&fee=" + fee,
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
                    document.getElementById("goodId").value = "";
                    document.getElementById("fee").value = "";
                    document.getElementById("receiverName").value = "";
                    document.getElementById("receiverTele").value = "";
                    document.getElementById("storageId").value = "";
                    passCheckTrue.checked = true;
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
                        document.getElementById("storageId").value = res.storageId;
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
                        document.getElementById("storageId").value = res.storageId;
                    }else{
                        $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>'+ res.reason + '</p></div>');
                    }
                }
            });
        }
    }
}

function getAllCompany() {
    $.ajax({
        type: "GET",
        url: "/company/all",
        dataType: "json",
        success: function (res) {
            if (res.status == "ok") {
                var str = '';
                var data = res.companys;
                for (var i = 0; i < data.length; i++) {
                    str += '<tr onclick="generateCompany(\'' + data[i].mName + '\')"><td>' + data[i].mCompanyId + '</td>' + '<td>' + data[i].mName + '</td>' + '<td>' + data[i].packageNum + '</td></tr>';
                }
                $("#companyContent").html(str);
            } else {
                $("#companyContent").html("");
                //$.fancybox.open('<div class="message"><h2>Sorry!</h2><p>' + res.reason + '</p></div>');
            }
        }
    });
}

function researchCompany(){
    getAllCompany();

    $('.ui.modal')
        .modal('show')
    ;
}

function getCompanyById() {
    var companyId = document.getElementById("companyId").value;
    if(companyId == ""){
        $("#companyIdInput").addClass("error");

        getAllCompany();
        return;
    }
    $.ajax({
        type: "GET",
        url: "/company/" + companyId,
        dataType: "json",
        success: function (res) {
            if (res.status == "ok") {
                var str =  '<tr onclick="generateCompany(\'' + res.mName + '\')"><td>' + res.mCompanyId + '</td>' + '<td>' + res.mName + '</td>' + '<td>' + res.packageNum + '</td></tr>';
                $("#companyContent").html(str);
            } else {
                $("#companyContent").html("");
                //$.fancybox.open('<div class="message"><h2>Sorry!</h2><p>' + res.reason + '</p></div>');
            }
        }
    });
}

function removeError(){
    $("#companyIdInput").removeClass("error");
}

function addNewCompany(){
    var newComID = document.getElementById("newComID").value;
    var newComName = document.getElementById("newComName").value;
    if(newComID == ""){
        $("#newComIDInput").addClass("error");
        return;
    }
    if(newComName == ""){
        $("#newComNameInput").addClass("error");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/company/?id=" + newComID + "&name=" + newComName,
        dataType: "json",
        success: function (res) {
            if (res.status == "ok") {

            }
        }
    });

    getAllCompany();
}

function removeErrorNewID(){
    $("#newComIDInput").removeClass("error");
}

function removeErrorNewName(){
    $("#newComNameInput").removeClass("error");
}

function generateCompany(name){
    document.getElementById("companyName").value = name;
}
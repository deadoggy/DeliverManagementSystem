/**
 * Created by luminous on 5/30/2017.
 */
$(function (){
    $("#seg3").addClass("active");

    $("#rPos").citySelect({
        prov: "上海",
        nodata: "none"
    });

    $("#sPos").citySelect({
        prov: "上海",
        nodata: "none"
    });
});

function sendPackage(){
    var packageId = Math.floor((Math.random())*1000000);
    //s: send; r: receive
    var sName = document.getElementById("sName").value;
    var rName = document.getElementById("rName").value;
    var sTele = document.getElementById("sTele").value;
    var rTele = document.getElementById("rTele").value;
    var sAdderss = document.getElementById("sAdderss").value;
    var rAdderss = document.getElementById("rAdderss").value;
    var sCity = document.getElementById("sCity").value;
    var rCity = document.getElementById("rCity").value;
    var sProvince = document.getElementById("sProvince").value;
    var rProvince = document.getElementById("rProvince").value;
    var weight = document.getElementById("weight").value;
    // console.log(sName);
    // console.log(rName);
    // console.log(sTele);
    // console.log(rTele);
    // console.log(sAdderss);
    // console.log(rAdderss);
    // console.log(sCity);
    // console.log(rCity);
    // console.log(sProvince);
    // console.log(rProvince);
    // console.log(weight);
    if(sName == "" || rName == "" || sTele == "" || rTele == "" || sAdderss == "" ||
        rAdderss == "" || sProvince == "" || rProvince == "" || sCity == "" || rCity == "" || weight == ""){
        $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>请填完整表格</p></div>');
    }else{
        $.ajax({
            type: "POST",
            url: "/sendrecord?id=" + packageId + "&weight=" + weight
            + "&sName=" + sName + "&sTele=" + sTele + "&sAdderss=" + sAdderss + "&sProvince=" + sProvince + "&sCity=" + sCity
            + "&rName=" + rName + "&rTele=" + rTele + "&rAdderss=" + rAdderss + "&rProvince=" + rProvince + "&rCity=" + rCity,
            // data: {
            //     packageId: packageId,
            //     sName: sName,
            //     sTele: sTele,
            //     sAdderss: sAdderss,
            //     sCity: sCity,
            //     sProvince: sProvince,
            //     rName: rName,
            //     rTele: rTele,
            //     rAddress: rAdderss,
            //     rCity: rCity,
            //     rProvince: rProvince,
            //     weight: weight
            // },
            dataType: "json",
            success: function(res){
                $("#step1").removeClass("active");
                $("#step2").addClass("active");
                var str = '';
                if(res.status == "ok"){
                    //$.fancybox.open('<div class="message"><h2>Success!</h2><p>成功创建新包裹.</p></div>');
                    str = '<div class="ui attached icon info message"><h4 class="ui header " style="color:#7EE867"><i class="checkmark icon"></i>订单提交成功！</h4> </div>';
                }else{
                    //$.fancybox.open('<div class="message"><h2>Sorry!</h2><p>'+ res.reason + '</p></div>');
                    str = '<div class="ui attached icon info message"><h4 class="ui header " style="color:#E8493F"><i class="remove icon"></i>订单提交失败！</h4> </div><div class="field" style="margin-top: 10%; width: 96%; margin-left: 2%"><p>'+ res.reason + '</p></div>';
                }
                $("#sendContent").html(str);
            }
        });
    }
}
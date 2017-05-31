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
            url: "/sendrecord?packageId=" + packageId + "&weight=" + weight
            + "&sName=" + sName + "&sTele=" + sTele + "&sAdderss=" + sAdderss + "&sProvince=" + sProvince
            + "&rName=" + rName + "&rTele=" + rTele + "&rAdderss=" + rAdderss + "&rProvince=" + rProvince,
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
                if(res.status == "ok"){
                    $.fancybox.open('<div class="message"><h2>Success!</h2><p>成功创建新包裹.</p></div>');
                }else{
                    $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>'+ res.reason + '</p></div>');
                }
            }
        });
    }
}
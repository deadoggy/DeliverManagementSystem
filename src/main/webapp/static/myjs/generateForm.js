/**
 * Created by luminous on 5/31/2017.
 */
$(function (){
    $("#seg5").addClass("active");
});

function generateForm(){
    var fromTime = document.getElementById("fromTime").value;
    var toTime = document.getElementById("toTime").value;
    var company = document.getElementById("company").value;

    var taken_sum = document.getElementById("taken_sum");
    var post_fee = document.getElementById("post_fee");
    var package_info = document.getElementById("package_info");
    var send_rec = document.getElementById("send_rec");

    var opt = [];
    if(taken_sum.checked == true){
        opt += "taken_sum";
    }
    if(post_fee.checked == true){
        opt += "post_fee";
    }
    if(package_info.checked == true){
        opt += "package_info";
    }
    if(send_rec.checked == true){
        opt += "send_rec";
    }

    // $.ajax({
    //     type: "POST",
    //     url: "/form",
    //     data: JSON.stringify({
    //         beg: fromTime,
    //         end: toTime,
    //         company: company,
    //         opt: opt
    //     }),
    //     dataType: 'json',
    //     success: function(res){
    //         if(res.result == "success"){
    //             $.fancybox.open('<div class="message"><h2>Success!</h2><p>成功新建报表</div>');
    //         }else{
    //             //console.log(res.reason);
    //             $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>创建失败</p></div>');
    //         }
    //     }
    // });
}
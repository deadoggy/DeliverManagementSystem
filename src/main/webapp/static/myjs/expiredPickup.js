/**
 * Created by luminous on 6/7/2017.
 */
$(function (){
    $('.ui.checkbox').checkbox();
    setRCHeight();
    $("#seg2").addClass("active");

    getAllExpired();

    function trans(item)
    {
        $(item).toggleClass("active");
    }
    //   $("a .item").click(trans(this));
});

function getAllExpired(){
    //TODO
    // $.ajax({
    //     type: "GET",
    //     url: "",
    //     dataType: "json",
    //     success: function (res) {
    //         if (res.status == "ok") {
    //             var str = '';
    //             var data = res.packageList;
    //             var curTime = new Date();
    //             for (var i = 0; i < data.length; i++) {
    //                 var strTime = data[i].mReceiveTime;
    //                 var date = new Date(strTime);
    //                 var delayTime = ((curTime - date) / (1000 * 3600)).toFixed(2);
    //                 str += '<tr><td>' + data[i].mPackageId + '</td>' + '<td>' + data[i].mPosition + '</td>' + '<td>' + data[i].mReceiveTime + '</td>'
    //                     + '<td>' + delayTime.toString() + '小时' + '</td>' + '<td><button class="ui yellow button" onclick="forcedOpen()">强制开柜</button></td></tr>';
    //             }
    //             $("#goodContent").html(str);
    //             $("#expiredContent").html(str);
    //         } else {
    //             $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>' + res.reason + '</p></div>');
    //         }
    //     }
    // });

}

function forcedOpen(){
    //TODO 强制开柜
}
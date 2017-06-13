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
    $.ajax({
        type: "GET",
        url: "/package/overtime",
        dataType: "json",
        success: function (res) {
            if (res.status == "ok") {
                var str = '';
                var data = res.packageList;
                var curTime = new Date();
                for (var i = 0; i < data.length; i++) {
                    var strTime = data[i].rTime;
                    var date = new Date(strTime);
                    var delayTime = ((curTime - date) / (1000 * 3600)).toFixed(2);
                    str += '<tr><td>' + data[i].packageId + '</td>' + '<td>' + data[i].position + '</td>' + '<td>' + data[i].rTime + '</td>'
                        + '<td>' + delayTime.toString() + '小时' + '</td>' + '<td><button class="ui yellow button" onclick="forcedOpen(\'' + data[i].position + '\')">强制开柜</button></td></tr>';
                }
                $("#expiredContent").html(str);
            } else {
                $("#expiredContent").html("");
                //$.fancybox.open('<div class="message"><h2>Sorry!</h2><p>' + res.reason + '</p></div>');
            }
        }
    });

}

function forcedOpen(pos){
    var regex = new RegExp("[0-9]+-[0-9]+-[0-9]+");
    var id = pos.match(regex);
    $.ajax({
        type: "GET",
        url: "/package/force/"+id[0],
        dataType: "json",
        success: function (res) {
            if (res.status == "ok") {
                $.fancybox.open('<div class="message"><h2>Success!</h2><p>强制开柜成功。</p></div>');
            } else {
                $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>强制开柜失败。</p></div>');
            }
        }
    });
}
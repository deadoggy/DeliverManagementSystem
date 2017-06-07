$(function (){
    $("#seg2").addClass("active");

    getAll();
});

function goodQuery(){
    var goodId = document.getElementById("goodId").value;
    var tel = document.getElementById("tel").value;

    if(goodId == "" && tel == ""){
        $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>查询条件不得为空</p></div>');
    }
    else if(goodId != ""){
        $.ajax({
            type: "GET",
            url: "/package/"+goodId,
            //data: { id: goodId },
            dataType: "json",
            success: function(res){
                if (res.status == "ok") {
                    var str = '';
                    var data = res.packageList;
                    var curTime = new Date();
                    for (var i = 0; i < data.length; i++) {
                        var strTime = data[i].rTime;
                        var date = new Date(strTime);
                        var delayTime = ((curTime - date) / (1000 * 3600)).toFixed(2);
                        str += '<tr><td>' + data[i].packageId + '</td>' + '<td>' + data[i].position + '</td>' + '<td>' + data[i].rTime + '</td>'
                            + '<td>' + delayTime.toString() + '小时' + '</td>' + '<td>' + data[i].rName + ': ' + data[i].rTele + '</td>' + '<td><input type="button" class="ui yellow button" onclick="confirmBox()" value="取件"></input></td></tr>';
                    }
                    $("#goodContent").html(str);
                } else {
                    $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>' + res.reason + '</p></div>');
                }
            }
        });
    }
    else{
        $.ajax({
            type: "GET",
            url: "/package/tele/"+tel,
            //data: { tele: tel },
            dataType: "json",
            success: function(res){
                if (res.status == "ok") {
                    var str = '';
                    var data = res.packageList;
                    var curTime = new Date();
                    for (var i = 0; i < data.length; i++) {
                        var strTime = data[i].rTime;
                        var date = new Date(strTime);
                        var delayTime = ((curTime - date) / (1000 * 3600)).toFixed(2);
                        str += '<tr><td>' + data[i].packageId + '</td>' + '<td>' + data[i].position + '</td>' + '<td>' + data[i].rTime + '</td>'
                            + '<td>' + delayTime.toString() + '小时' + '</td>' + '<td>' + data[i].rName + ': ' + data[i].rTele + '</td>' + '<td><input type="button" class="ui yellow button" onclick="confirmBox()" value="取件"></input></td></tr>';
                    }
                    $("#goodContent").html(str);
                } else {
                    $.fancybox.open('<div class="message"><h2>Error!</h2><p>' + res.reason + '</p></div>');
                }
            }
        });
    }

}


//返回时间"yyyy/MM/dd HH:mm:ss"

function getAll() {
    $.ajax({
        type: "GET",
        url: "/package/allnotaken",
        dataType: "json",
        success: function (res) {
            if (res.status == "ok") {
                var str = '';
                var data = res.packageList;
                var curTime = new Date();
                var len = (data.length > 10) ? 10 : data.length;
                for (var i = 0; i < len; i++) {
                    var strTime = data[i].rTime;
                    var date = new Date(strTime);
                    var delayTime = ((curTime - date) / (1000 * 3600)).toFixed(2);
                    str += '<tr><td>' + data[i].packageId + '</td>' + '<td>' + data[i].position + '</td>' + '<td>' + data[i].rTime + '</td>'
                        + '<td>' + delayTime.toString() + '小时' + '</td>' + '<td>' + data[i].rName + ': ' + data[i].rTele + '</td>' + '<td><input type="button" class="ui yellow button" onclick="confirmBox()" value="取件"></input></td></tr>';
                }
                $("#goodContent").html(str);
            } else {
                $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>' + res.reason + '</p></div>');
            }
        }
    });
}

function confirmBox(){
    $.fancybox.open('<div class="ui form"> <br class="two fields"><div class="field"> <label>取件码：</label> <input type="text" name="confirmcode" id="confirmcode" placeholder="取件码" required/></div> </br><div class="field"> <button class="ui button" id="hwCxbtn" onclick="confirm()">确认取件</button> </div> </div> </div>');
}

function confirm(){
    var confirmcode = document.getElementById("confirmcode").value;
    if(confirmcode == ""){
        $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>请输入验证码!</p></div>');
    }else{
        $.ajax({
            type: "POST",
            url: "/confirm/"+confirmcode,
            //data: { Confirmcode: confirmcode},
            dataType: "json",
            success: function (res) {
                if (res.status == "ok") {
                    $.fancybox.open('<div class="message"><h2>Success!</h2><p>取件成功</p></div>');
                } else {
                    $.fancybox.open('<div class="message"><h2>Failed!</h2><p>' + res.reason + '</p></div>');
                }
            }
        });
    }
}
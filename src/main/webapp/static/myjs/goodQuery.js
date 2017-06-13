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
            console.log(res);
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
                $("#goodContent").html("");
                //$.fancybox.open('<div class="message"><h2>Sorry!</h2><p>' + res.reason + '</p></div>');
            }
        }
    });
}

function confirmBox(){
    $.fancybox.open('<div class="ui form"> ' +
        '<div class="field">' +
        ' <label>取件码：</label> ' +
        '<input type="text" name="confirmcode" id="confirmcode" placeholder="取件码" required/>' +
        '</div> ' +
        '<div class="field" id="feeInput" style="display: none">' +
        ' <label>邮费：</label> ' +
        '<input type="text" name="fee" id="fee" placeholder="实付邮费" required/>' +
        '</div> ' +
        '</br>' +
        '<div class="field"> ' +
        '<button class="ui button" id="queryFeeBtn" onclick="confirmFee()" style="width: 100%">查看邮费</button>' +
        ' </div> ' +
        '<div class="field" id="feeBtn" style="padding-top: 1em;display: none"> ' +
        '<button class="ui button" id="confirmBtn" onclick="confirm()" style="width: 100%">确认取件</button>' +
        '</div>');
}

function confirmFee(){
    var confirmcode = document.getElementById("confirmcode").value;
    document.getElementById("feeInput").style.display = 'none';
    document.getElementById("feeBtn").style.display = 'none';
    if(confirmcode == ""){
        $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>请输入验证码!</p></div>');
    }else{
        $.ajax({
            type: "POST",
            url: "/confirm/?confirmcode="+confirmcode,
            //data: { Confirmcode: confirmcode},
            dataType: "json",
            success: function (res) {
                if (res.status == "ok") {
                    document.getElementById("confirmcode").readOnly = true;
                    document.getElementById("feeInput").style.display = 'block';
                    document.getElementById("feeBtn").style.display = 'block';
                    document.getElementById("queryFeeBtn").innerText = "应付邮费：" + res.fee + "元";
                } else {
                    $.fancybox.open('<div class="message"><h2>Failed!</h2><p>' + res.reason + '</p></div>');
                }
            }
        });
    }
}

function confirm(){
    var confirmcode = document.getElementById("confirmcode").value;
    var fee = document.getElementById("fee").value;
    if(fee == ""){
        $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>请输入实付邮费!</p></div>');
    }else{
        $.ajax({
            type: "POST",
            url: "/confirm/fee?confirmcode="+confirmcode + "&fee=" + fee,
            //data: { Confirmcode: confirmcode},
            dataType: "json",
            success: function (res) {
                if (res.status == "ok") {
                    $.fancybox.open('<div class="message"><h2>Failed!</h2><p>取件成功</p></div>');
                } else {
                    $.fancybox.open('<div class="message"><h2>Failed!</h2><p>' + res.reason + '</p></div>');
                }
            }
        });
    }
}
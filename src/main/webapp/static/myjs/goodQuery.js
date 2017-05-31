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
                    var data = res.packages;
                    len = (data.length > 10) ? 10 : data.length;
                    for (var i = 0; i < len; i++) {
                        str += '<tr><td>' + data[i].mId + '</td>' + '<td>' + data[i].mPackageId + '</td>' + '<td>' + data[i].mReceiveTime + '</td>'
                            + '<td>' + data[i].mPackageId + '</td>' + '<td>' + data[i].mReceiverName + ': ' + data[i].mReceiverTele + '</td>' + '<td><button class="ui yellow button" onclick="confirmBox()">取件</button></td></tr>';
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
                    var myDate=new Date();
                    var data = res.packages;
                    for (var i = 0; i < data.length; i++) {
                        str += '<tr><td>' + data[i].mId + '</td>' + '<td>' + data[i].mPackageId + '</td>' + '<td>' + data[i].mReceiveTime + '</td>'
                            + '<td>' + data[i].mPackageId + '</td>' + '<td>' + data[i].mReceiverName + ': ' + data[i].mReceiverTele + '</td>' + '<td><button class="ui yellow button" onclick="confirmBox()">取件</button></td></tr>';
                    }
                    $("#goodContent").html(str);
                } else {
                    $.fancybox.open('<div class="message"><h2>Error!</h2><p>' + res.reason + '</p></div>');
                }
            }
        });
    }

}


//function oninput(){}

function getAll() {
    $.ajax({
        type: "GET",
        url: "/package/all",
        dataType: "json",
        success: function (res) {
            if (res.status == "ok") {
                var str = '';
                var data = res.packages;
                len = (data.length > 10) ? 10 : data.length;
                for (var i = 0; i < len; i++) {
                    str += '<tr><td>' + data[i].mId + '</td>' + '<td>' + data[i].mPackageId + '</td>' + '<td>' + data[i].mReceiveTime + '</td>'
                        + '<td>' + data[i].mPackageId + '</td>' + '<td>' + data[i].mReceiverName + ': ' + data[i].mReceiverTele + '</td>' + '<td><button class="ui yellow button" onclick="confirmBox()">取件</button></td></tr>';
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
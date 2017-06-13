/**
 * Created by luminous on 5/31/2017.
 */
$(function (){
    $("#seg5").addClass("active");

    $("#formContent").html("");
});

function generateForm(){
    var fromTime = document.getElementById("fromTime").value;
    var toTime = document.getElementById("toTime").value;
    var company = document.getElementById("company").value;

    var taken_sum = document.getElementById("taken_sum");
    var post_fee = document.getElementById("post_fee");
    var package_info = document.getElementById("package_info");
    var send_rec = document.getElementById("send_rec");

    if(fromTime == "" || toTime =="" || company == ""){
        $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>请输入查询条件</div>');
        return;
    }

    var opt = "taken_sum";
    if(taken_sum.checked == true){
        opt = "taken_sum";
    }
    else if(post_fee.checked == true){
        opt = "post_fee";
    }
    else if(package_info.checked == true){
        opt = "package_info";
    }
    else if(send_rec.checked == true){
        opt = "send_rec";
    }

    fromTime = fromTime.replace(/-/g,"/");
    toTime = toTime.replace(/-/g,"/");

    $.ajax({
        type: "POST",
        url: "/form",
        data: JSON.stringify({
            beg: fromTime,
            end: toTime,
            company: company,
            opt: opt
        }),
        dataType: 'json',
        success: function(res){
            if(res.result == "success"){
                if(opt == "taken_sum"){
                    var str = '<thead> <tr> <th>日期</th> <th>取件总量</th> </tr> </thead> <tbody>';
                    var data = res.data;
                    for (var i = 0; i < data.length; i++) {
                        console.log(data[i]);
                        str += '<tr><td>'+data[i].time+'</td><td>'+data[i].sum+'</td></tr>';
                    }
                    str += '</tbody>';
                    $("#formContent").html(str);
                }
                else if(opt =="post_fee"){
                    var str = '<thead> <tr> <th>单号</th> <th>日期</th> <th>费用</th> </tr> </thead> <tbody>';
                    var data = res.data;
                    for (var i = 0; i < data.length; i++) {
                        console.log(data[i]);
                        str += '<tr><td>'+data[i].package_id+'</td><td>'+data[i].date+'</td><td>'+data[i].fee+'</td></tr>';
                    }
                    str += '</tbody>';
                    $("#formContent").html(str);
                }
                else if(opt == "send_rec"){
                    var str = '<thead> <tr> <th>单号</th> <th>物流公司</th> <th>寄件人</th> <th>寄件人电话</th> <th>寄件时间</th> </tr> </thead> <tbody>';
                    var data = res.data;
                    for (var i = 0; i < data.length; i++) {
                        console.log(data[i]);
                        str += '<tr><td>'+data[i].package+'</td><td>'+data[i].company+'</td><td>'+data[i].sender+'</td><td>'+data[i].sender_phone+'</td><td>'+data[i].send_time+'</td></tr>';
                    }
                    str += '</tbody>';
                    $("#formContent").html(str);
                }
                else if(opt == "package_info"){
                    console.log(res.url);
                    $.fancybox.open('<div class="message"><h2>Success!</h2><p>成功新建报表，点击<a href="ftp://139.129.18.35/' + res.url + '" download="包裹情况.csv">此处</a>下载。</div>');
                }
            }else{
                $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>创建失败</p></div>');
            }
        }
    });
}
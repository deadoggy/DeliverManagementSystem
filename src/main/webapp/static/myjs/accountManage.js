/**
 * Created by luminous on 5/31/2017.
 */
$(function (){
    $("#seg5").addClass("active");

    getAllAccount();
});

function getAllAccount(){
    $.ajax({
        type: "GET",
        url: "/list_em",
        dataType: 'json',
        success: function(res){
            //console.log(res);
            if(res.result == "success"){
                var data = res.data;
                var str = "";
                for(var i = 0; i < data.length; i++){
                    var gender = (data[i].gender == "female")? "女" : "男";
                    str += '<tr><td>' + data[i].id + '</td>' + '<td>' + data[i].name + '</td>' + '<td>' + data[i].pos + '</td>' + '<td>' + gender + '</td>'
                        + '<td>' + data[i].age + '</td>' + '<td>' + data[i].phone + '</td>' + '<td>' + data[i].salary + '</td>' + '<td>' + data[i].point + '</td>'
                        + '<td><input type="button" class="ui yellow button" onclick="editAccount(' + data[i].id + ',' + data[i].name + ',' + data[i].salary + ',' + data[i].point + ')" value="编辑">' +
                        '<input type="button" class="ui red button" onclick="rmAccount(' + data[i].id + ')" value="删除"></td></tr>';
                }
                $("#accountContent").html(str);
            }else{
                $("#accountContent").html("");
            }
        }
    });
}

function searchAccount(){
    var condition = document.getElementById("condition").value;
    var queryValue = document.getElementById("queryValue").value;
    if(queryValue == ""){
        $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>请输入查询条件</p></div>');
        return;
    }

    if(condition == "id"){
        $.ajax({
            type: "GET",
            url: "/findEmById/" + queryValue,
            dataType: 'json',
            success: function(res){
                if(res.result == "success"){
                    var data = res.data;
                    var str = "";
                    for(var i = 0; i < data.length; i++){
                        var gender = (data[i].gender == "female")? "女" : "男";
                        str += '<tr><td>' + data[i].id + '</td>' + '<td>' + data[i].name + '</td>' + '<td>' + data[i].pos + '</td>' + '<td>' + gender + '</td>'
                            + '<td>' + data[i].age + '</td>' + '<td>' + data[i].phone + '</td>' + '<td>' + data[i].salary + '</td>' + '<td>' + data[i].point + '</td>'
                            + '<td><input type="button" class="ui yellow button" onclick="editAccount(' + data[i].id + ',' + data[i].name + ',' + data[i].salary + ',' + data[i].point + ')" value="编辑">' +
                            '<input type="button" class="ui red button" onclick="rmAccount(' + data[i].id + ')" value="删除"></td></tr>';
                    }
                    $("#accountContent").html(str);

                    if((res.data).length <= 0){
                        $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>未查询到相关员工</p></div>');
                    }
                }else{
                    $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>查找失败</p></div>');
                    $("#accountContent").html("");
                }
            }
        });
    }
    else if(condition == "name"){
        $.ajax({
            type: "GET",
            url: "/findEmByName/" + queryValue,
            dataType: 'json',
            success: function(res){
                console.log(res);
                if(res.result == "success"){
                    var data = res.data;
                    var str = "";
                    for(var i = 0; i < data.length; i++){
                        var gender = (data[i].gender == "female")? "女" : "男";
                        str += '<tr><td>' + data[i].id + '</td>' + '<td>' + data[i].name + '</td>' + '<td>' + data[i].pos + '</td>' + '<td>' + gender + '</td>'
                            + '<td>' + data[i].age + '</td>' + '<td>' + data[i].phone + '</td>' + '<td>' + data[i].salary + '</td>' + '<td>' + data[i].point + '</td>'
                            + '<td><input type="button" class="ui yellow button" onclick="editAccount(' + data[i].id + ',' + data[i].name + ',' + data[i].salary + ',' + data[i].point + ')" value="编辑">' +
                            '<input type="button" class="ui red button" onclick="rmAccount(' + data[i].id + ')" value="删除"></td></tr>';
                    }
                    $("#accountContent").html(str);

                    if((res.data).length <= 0){
                        $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>未查询到相关员工</p></div>');
                    }
                }else{
                    $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>查找失败</p></div>');
                }
            }
        });

    }
}

function rmAccount(id){
    $.ajax({
        type: "GET",
        url: "/remove_em/" + id,
        dataType: 'text',
        success: function(res){
            if(res == "true"){
                $.fancybox.open('<div class="message"><h2>Success!</h2><p>成功删除<strong>ID:'+ id + ' </strong>员工</p></div>');
                cancelAdd();
            }else{
                //console.log(res.reason);
                $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>删除失败</p></div>');
            }
        }
    });

    getAllAccount();
}

function editAccount(id, name, salary, point){
    $.fancybox.open('<div><h2 class="ui header"> <i class="file image outline icon blue"></i> <div class="content">修改个人信息</div> </h2>' +
        '<form class="ui form">' +
        '<div class="field inline">' +
        '<label>用户名: </label>' +
        '<input type="text" id="name" value="' + name +'" oninput="updateInfo("Name")"/>' +
        '<div class="ui left pointing green basic label">Saved!</div>' +
        '</div>' +
        '<div class="field"> ' +
        '<input type="button" class="ui button primary"  onclick="pickupClickEvent()" value="修改密码">' +
        '</div>' +
        '</form>');

    getAllAccount();
}

function updateInfo(attribute) {
    if(attribute == "Name"){

    }
    else{

    }
}

function addAccount(){
    var passwd = document.getElementById("passwd").value;
    var passwd2 = document.getElementById("passwd2").value;
    if(passwd == "" || passwd != passwd2){
        $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>两次输入的密码不一致，请重新输入！</p></div>');
        document.getElementById("passwd").value = "";
        document.getElementById("passwd2").value = "";
    }else{
        var name = document.getElementById("name").value;
        var gender = document.getElementById("gender").value;
        var position = document.getElementById("position").value;
        var ageStr = document.getElementById("age").value;
        var tele = document.getElementById("tele").value;
        var salaryStr = document.getElementById("salary").value;
        var point = document.getElementById("point").value;
        //console.log(name, gender, position, age, tele, salary, point, passwd);

        var age = parseInt(ageStr);
        var salary = parseFloat(salaryStr);
        if(isNaN(age) || age <= 0){
            $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>年龄必须为正整数，请重新输入！</p></div>');
            document.getElementById("age").value = "";
        }
        else if(isNaN(salary) || salary < 0){
            $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>工资必须为正数，请重新输入！</p></div>');
            document.getElementById("salary").value = "";
        }
        else{
            $.ajax({
                type: "POST",
                url: "/save_em",
                data: JSON.stringify({
                    name: name,
                    gender: gender,
                    position: position,
                    age: age,
                    tele: tele,
                    salary: salary,
                    point: point,
                    passwd: passwd
                }),
                dataType: 'json',
                success: function(res){
                    if(res.result == "success"){
                        $.fancybox.open('<div class="message"><h2>Success!</h2><p>成功新建<strong>ID:'+ res.id + ' </strong>员工</p></div>');
                        cancelAdd();
                    }else{
                        //console.log(res.reason);
                        $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>创建失败</p></div>');
                    }
                }
            });
        }

    }

}

function cancelAdd(){
    document.getElementById("name").value = "";
    document.getElementById("gender").value = "male";
    document.getElementById("age").value = "";
    document.getElementById("position").value = "";
    document.getElementById("tele").value = "";
    document.getElementById("salary").value = "";
    document.getElementById("point").value = "";
    document.getElementById("passwd").value = "";
    document.getElementById("passwd2").value = "";
}
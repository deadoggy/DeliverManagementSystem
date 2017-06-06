/**
 * Created by luminous on 5/31/2017.
 */
$(function (){
    $("#seg5").addClass("active");
});

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
                    var str = '<tr><td>' + res.id + '</td>' + '<td>' + res.name + '</td>' + '<td>' + res.gender + '</td>'
                        + '<td>' + (res.age).toString() + '</td>' + '<td>' + res.phone + '</td>' + '<td>' + (res.salary).toString() + '</td>' + '<td>' + res.point + '</td>'
                        + '<td><input type="button" class="ui yellow button" onclick="editAccount(' + res.id + ')" value="编辑">' +
                        '<input type="button" class="ui red button" onclick="rmAccount(' + res.id + ')" value="删除"></td></tr>';
                    $("#accountContent").html(str);
                }else{
                    $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>' + res.reason + '</p></div>');
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
                console.log(res.data);
                if(res.result == "success"){
                    // if((res.data).length <= 0){
                    //     $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>未查询到相关员工</p></div>');
                    //     return;
                    // }

                    var str = '<tr><td>' + res.id + '</td>' + '<td>' + res.name + '</td>' + '<td>' + res.gender + '</td>'
                        + '<td>' + res.age + '</td>' + '<td>' + res.phone + '</td>' + '<td>' + res.salary + '</td>' + '<td>' + res.point + '</td>'
                        + '<td><input type="button" class="ui yellow button" onclick="editAccount(' + res.id + ')" value="编辑">' +
                        '<input type="button" class="ui red button" onclick="rmAccount(' + res.id + ')" value="删除"></td></tr>';
                    $("#accountContent").html(str);
                }else{
                    $.fancybox.open('<div class="message"><h2>Sorry!</h2><p>' + res.reason + '</p></div>');
                }
            }
        });

    }
}

function editAccount(id){

}

function rmAccount(id){

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
                    //position: position,
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
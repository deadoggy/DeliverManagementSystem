/**
 * Created by luminous on 5/31/2017.
 */
$(function (){
    $("#seg5").addClass("active");
    $('.ui.accordion').accordion();

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
                        + '<td><input type="button" class="ui yellow button" onclick="editAccount(\'' + data[i].id + '\',\'' + data[i].name + '\',\'' + data[i].salary + '\',\'' + data[i].point + '\')" value="编辑">' +
                        '<input type="button" class="ui red button" onclick="rmAccount(\'' + data[i].id + '\')" value="删除"></td></tr>';
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
        getAllAccount();
        return;
    }

    if(condition == "id"){
        $.ajax({
            type: "GET",
            url: "/findEmById/" + queryValue,
            dataType: 'json',
            success: function(res){
                //console.log(res);
                if(res.result == "success"){
                    var gender = (res.gender == "female")? "女" : "男";
                    var str = '<tr><td>' + res.id + '</td>' + '<td>' + res.name + '</td>' + '<td>' + res.pos + '</td>' + '<td>' + gender + '</td>'
                            + '<td>' + res.age + '</td>' + '<td>' + res.phone + '</td>' + '<td>' + res.salary + '</td>' + '<td>' + res.point + '</td>'
                            + '<td><input type="button" class="ui yellow button" onclick="editAccount(\'' + res.id + '\',\'' + res.name + '\',\'' + res.salary + '\',\'' + res.point + '\')" value="编辑">' +
                            '<input type="button" class="ui red button" onclick="rmAccount(\'' + res.id + '\')" value="删除"></td></tr>';

                    $("#accountContent").html(str);

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
                //console.log(res);
                if(res.result == "success"){
                    var data = res.data;
                    var str = "";
                    for(var i = 0; i < data.length; i++){
                        var gender = (data[i].gender == "female")? "女" : "男";
                        str += '<tr><td>' + data[i].id + '</td>' + '<td>' + data[i].name + '</td>' + '<td>' + data[i].pos + '</td>' + '<td>' + gender + '</td>'
                            + '<td>' + data[i].age + '</td>' + '<td>' + data[i].phone + '</td>' + '<td>' + data[i].salary + '</td>' + '<td>' + data[i].point + '</td>'
                            + '<td><input type="button" class="ui yellow button" onclick="editAccount(\'' + data[i].id + '\',\'' + data[i].name + '\',\'' + data[i].salary + '\',\'' + data[i].point + '\')" value="编辑">' +
                            '<input type="button" class="ui red button" onclick="rmAccount(\'' + data[i].id + '\')" value="删除"></td></tr>';
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
    document.getElementById("accountId").value = id;
    document.getElementById("newName").value = name;
    document.getElementById("newSalary").value = salary;
    document.getElementById("newPoint").value = point;

    $('.ui.modal')
        .modal('show')
    ;
}

function updateName() {
    var id = document.getElementById("accountId").value;
    var name = document.getElementById("newName").value;
    $.ajax({
        type: "POST",
        url: "/update_info",
        data: JSON.stringify({
            id: id,
            attribute: "Name",
            val: name
        }),
        dataType: 'text',
        success: function(res){
            if(res == "true"){
                getAllAccount();
            }else{
                console.log(res);
            }
        }
    });
}

function updateSalary() {
    var id = document.getElementById("accountId").value;
    var salary = document.getElementById("newSalary").value;
    $.ajax({
        type: "POST",
        url: "/update_info",
        data: JSON.stringify({
            id: id,
            attribute: "Salary",
            val: salary
        }),
        dataType: 'text',
        success: function(res){
            if(res == "true"){
                getAllAccount();
            }else{
                console.log(res);
            }
        }
    });
}

function updatePoint() {
    var id = document.getElementById("accountId").value;
    var point = document.getElementById("newPoint").value;
    $.ajax({
        type: "POST",
        url: "/update_info",
        data: JSON.stringify({
            id: id,
            attribute: "Point",
            val: point
        }),
        dataType: 'text',
        success: function(res){
            if(res == "true"){
                getAllAccount();
            }else{
                console.log(res);
            }
        }
    });
}

function updatePwd() {
    var id = document.getElementById("accountId").value;
    var newPwd1 = document.getElementById("newPwd1").value;
    var newPwd2 = document.getElementById("newPwd2").value;

    if(newPwd1 == ""){
        $("#newPwd1Input").addClass("error");
        return;
    }

    if(newPwd1 != newPwd2){
        $("#newPwd2Input").addClass("error");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/update_info",
        data: JSON.stringify({
            id: id,
            attribute: "Pwd",
            val: name
        }),
        dataType: 'text',
        success: function(res){
            if(res == "true"){
                getAllAccount();
            }else{
                console.log(res);
            }
        }
    });
}

function removeErrorPwd1(){
    $("#newPwd1Input").removeClass("error");
}

function removeErrorPwd2(){
    $("#newPwd2Input").removeClass("error");
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
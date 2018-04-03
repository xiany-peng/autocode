// 以下为官方示例
$().ready(function() {
    validateRule();
});

$.validator.setDefaults({
    submitHandler : function() {
        update();
    }
});
function update() {
    $.ajax({
        type : "POST",
        url : "/config/db/update",
        data : $('#exampleForm').serialize(),// 你的formid
        async : false,
        error : function(request) {
            layer.alert("网络连接超时");
        },
        success : function(data) {
            if (data.code == 0) {
                layer.msg(data.msg);

            } else {
                layer.msg(data.msg);
            }

        }
    });

}
function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#exampleForm").validate({
        rules : {
            driverClassName : {
                required : true
            },
            url : {
                required : true,
            },
            username : {
                required : true,
            },
            password : {
                required : true,
            }
        },
        messages : {
            driverClassName : {
                required : icon + "不能为空"
            },
            url : {
                required : icon + "不能为空",
            },
            username : {
                required : icon + "不能为空",
            },
            password : {
                required : icon + "不能为空",
            }
        }
    })
}

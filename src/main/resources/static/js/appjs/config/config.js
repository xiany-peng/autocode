// 以下为官方示例
$().ready(function() {
    validateRule();

    //给所有的必填添加 *
    $(".required").each(function(){
        var $this = $(this);
        $(this).html($this.html()+"<font color='red'>*</font>");
    });

});

$.validator.setDefaults({
    submitHandler : function() {
        update();
    }
});
function update() {
    $.ajax({
        type : "POST",
        url : "/config/generator/update",
        data : JSON.stringify($('#exampleForm').serializeJSON()),// 你的formid
        contentType: "application/json",
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
            packageName : {
                required : true
            }
        },
        messages : {
            packageName : {
                required : icon + "不能为空"
            }
        }
    })
}

$(function () {
    //加载弹出层
    layui.use(['form','element'],
    function() {
        layer = layui.layer;
        element = layui.element;
    });

});


function T_POST(url,data,success) {
    $.ajax({
        url: url,
        type: 'post',
        dataType: 'json',
        data: data,
        success : success,
        error: function(error) {
            console.log(error);
            let msg = error.status;
            // console.log(error.responseJSON);
            if(error.responseJSON.message != null){
                msg += " " + error.responseJSON.message;
            }
            layer.msg(msg, {icon: 2});
        }
    });
}

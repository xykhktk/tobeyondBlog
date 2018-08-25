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

var T_MSG_ACTION = {
    closeMeAndRefreshParent : 1,
    refreshMe : 2,
    goto : 3

};

function T_Msg(msg,action,url,time) {
    if(time === null) time = 800;
    if(action === T_MSG_ACTION.closeMeAndRefreshParent){
        layer.msg(msg,{
            time : time,
            end : function(){
                window.parent.location.reload();
                var index=parent.layer.getFrameIndex(window.name);
                parent.layer.close(index);
            }
        });
    }

    if(action === T_MSG_ACTION.refreshMe){
        layer.msg(msg,{
            time : time,
            anim: 0,
            end : function(){
                window.location.reload();
            }
        });
    }

    if(action === T_MSG_ACTION.goto){
        layer.msg(msg,{
            time : 1000,
            end : function(){
                location.href = url
            }
        });
    }
}
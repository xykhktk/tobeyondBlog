var textarea = $('#text'),
    toolbar = $('<div class="markdown-editor" id="md-button-bar" />').insertBefore(textarea.parent())
preview = $('<div id="md-preview" class="md-hidetab" />').insertAfter('.markdown-editor');
markdown(textarea, toolbar, preview);

$(function () {
    layui.use(['form','upload'], function () {
        var form = layui.form;
        var upload = layui.upload;

        var _csrf = $("#_csrf").val();
        var uploadInst = upload.render({
            elem: '#upload_file' //绑定元素
            ,url: '/admin/upload/uploadFile' //上传接口
            ,data : { _csrf : _csrf}
            ,done: function(res){
                //上传完毕回调
                if(res.code == 200){
                    $("#page_image").val(res.data.key);
                    $("#upload_image_show").attr('src','http://pwydz7hrk.bkt.clouddn.com/' + res.data.key);
                }
                layer.msg(res.message);

            }
            ,error: function(){
                //请求异常回调
            }
        });

        //监听提交
        form.on('submit(commit)', function (data) {
            var new_data = data.field;
            var tagIds = '';
            for (var key in data.field){
                if(key.indexOf("tagId") != -1){
                    console.log(data.field[key]);
                    tagIds += data.field[key] + ','
                }
            }
            if(tagIds.length > 0) tagIds = tagIds.slice(0 ,tagIds.length -1);
            new_data.tagIds = tagIds;

            T_POST('add',new_data,
                function (data) {
                    if (data.success == true) {
                        T_Msg(data.message,T_MSG_ACTION.closeMeAndRefreshParent)
                    }else {
                        layer.msg(data.message, {icon: 2});
                    }
                }
            );
            return false;
        });
    });
})
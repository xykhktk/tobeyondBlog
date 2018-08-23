var textarea = $('#text'),
    toolbar = $('<div class="markdown-editor" id="md-button-bar" />').insertBefore(textarea.parent())
preview = $('<div id="md-preview" class="md-hidetab" />').insertAfter('.markdown-editor');
markdown(textarea, toolbar, preview);



$(function () {
    layui.use('form', function () {
        var form = layui.form;
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

            T_POST('/admin/article/add',new_data,
                function (data) {
                    if (data.success == true) {
                        layer.msg(data.message,function(){
                            window.parent.location.reload();
                            var index=parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        });
                    } else {
                        layer.msg(data.message, {icon: 2});
                    }
                }
            );
            return false;
        });
    });
})
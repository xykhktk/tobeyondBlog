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
            console.log(new_data);

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


// layui.use(['form','layer'], function(){
//     $ = layui.jquery;
//     var form = layui.form
//         ,layer = layui.layer;
//
//     //自定义验证规则
//     form.verify({
//         nikename: function(value){
//             if(value.length < 5){
//                 return '昵称至少得5个字符啊';
//             }
//         }
//         ,pass: [/(.+){6,12}$/, '密码必须6到12位']
//         ,repass: function(value){
//             if($('#L_pass').val()!=$('#L_repass').val()){
//                 return '两次密码不一致';
//             }
//         }
//     });
//
//     //监听提交
//     form.on('submit(add)', function(data){
//         console.log(data);
//         //发异步，把数据提交给php
//         layer.alert("增加成功", {icon: 6},function () {
//             // 获得frame索引
//             var index = parent.layer.getFrameIndex(window.name);
//             //关闭当前frame
//             parent.layer.close(index);
//         });
//         return false;
//     });
//
//
// });
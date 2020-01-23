/**
 * 提交回复
 */
function post(){
    var questionId = $("question_id").val();
    var comment_content = $("comment_content").val();

    if(!content){
        alert("评论不能为空");
        return;
    }
    $.ajax({
        type:"POST",
        url:"/comment",
        contentType:"application/json",
        data:JSON.stringify({
            "parent_id":1,
            "content":content,
            type: 1
        }),
        success:function (response) {
            if (response.code == 200){
                window.location.reload();
                $("#comment_section").hide()
            }else{
                if(response.code == 2003){
                    var isAccept = confirm(response.message);
                    if(isAccept){
                        window.open("https://github.com/login/oauth/authorize?client_id=8897d5437c00e01f2b5d&redirect_uri=http://localhost:8887/callback&scope=user&state=1")
                        window.localStorage.setItem("closable",true);
                    }
                }else{
                    alert(response.message)
                }

            }
                console.log("sssss");
        }
    });

}

/**
 * 展开二级评论
 */

function collapseComments(){
    var data = $.data(this,"id");
    console.log(data);
}

/**
* 选定标签添加内容
 */
function selectTag(e) {
    var value = e.getAttribute("data-tag");
    var previous = $("#tag").val();
    if(previous.indexOf(value) == -1){
        if(previous){
            $("#tag").val(previous+','+value);
        }else{
            $("#tag").val(value);
        }
    }
}

/**
 * 展示标签选择页
 */
function showSelectTag() {

}
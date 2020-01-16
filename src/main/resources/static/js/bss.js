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
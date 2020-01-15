function post(){
    var questionId = $("question_id").val();
    var comment_content = $("comment_content").val();
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
                $("#comment_section").hide()
            }else{
                alert(response.message())
            }
                console.log("sssss");
        }
    });

}
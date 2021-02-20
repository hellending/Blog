$(function(){
    $("#tel").blur(function (){
        let a = $("#tel").val();
        if(a.length==11){
            if(!isNaN(a)){
                $("#_tel").text("");
                return;
            }
            else{
                $("#_tel").text("含有非法字符");
                $("#_tel").css("color","#bb0707");
            }
        }
        else {
            if(a.length==0){
                $("#_tel").text("");
                return;
            }
            $("#_tel").text("手机号码应为11位");
            $("#_tel").css("color","#bb0707");
        }
    }),
    $("#repass").blur(function() {
    let a = $("#pass").val();
    let b = $("#repass").val();
    if(b.length==0){
        $("#_repass").text("");
        return;
    }
    if(a===b){
        $("#_repass").text("");
        return;
} else{
    $("#_repass").text("密码不相符");
    $("#_repass").css("color","#bb0707");
}
}),
    $("#submit").click(function(){
    let a = $("#_repass").text();
    let b = $("#_tel").text();
    let c = $("#if_repeat").text();
    if((a=='undefined'||a==null||a=="")&&(b=='undefined'||b==null||b=="")&&(c=='undefined'||c==null||c==""))return true;
    else return false;
}),
    $("#name").blur(function(){
        let a = $("#name").val();
        if(a.length==0){
            $("#if_repeat").text("");
            return;
        }
         $.ajax({
             url: "/if_repeat",
             method: "post",
             data: {
                 "name": a
             },
             success: function(x){
                 $("#if_repeat").text(x);
                 $("#if_repeat").css("color","#bb0707");
             }
         })
    })
})

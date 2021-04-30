$(function(){
    $("#toRegister").click(function (){
        $.ajax({
            url: "/register",
            method: "post",
            success: function(){
                window.location.href="/register";
            }
        })
    });
    $("#password").click(function(){
        if($("#password").val()===''&&$("#msg").text()!==''){
            $("#msg").text('');
        }
    });
})

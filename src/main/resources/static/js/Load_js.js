$(function(){
    $("#toRegister").click(function (){
        $.ajax({
            url: "/register",
            method: "post",
            success: function(){
                window.location.href="/register";
            }
        })
    })
})

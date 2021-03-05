var if_collect=0;
var if_thumbs=0;
let a = new Vue({
    el: "#top-head",
    data: {
        list_name: [],
        list_master: [],
        list_address: [],
        show2: true,
        theme: ""
    },
    methods: {
        update() {
            this.$forceUpdate();
        },
        re(address,master) {
            $.ajax({
               url: "/getComments",
               data: {
                    "master": master,
                    "article": $("#article_name").text()
               },
               method: "post",
               success: function(list){
                   for(let i=0;i<list[0].length;i++){
                       d.list_reviewer.push(list[0][i]);
                       d.list_comment.push(list[1][i]);
                       d.list_address.push("/headPortrait/"+list[0][i]+"/userLogo.jpg");
                   }
                   d.update();
               }
            });
            $.ajax({
                url: "/loadArticle",
                data: {
                    "address": address
                },
                method: "post",
                success: function(msg){
                    b.show1 = true;
                    a.show2 = false;
                    c.show3 = true;
                    d.show4 = true;
                    a.theme = address;
                    a.update();
                    b.update();
                    c.update();
                    d.update();
                    $("#sec-head").html(msg);
                    $("#m_logo_o").attr("src","/headPortrait/"+master+"/userLogo.jpg");
                    $("#m_name").text(master);
                    $.ajax({
                        url: "/ifCollectAndThumbs",
                        data: {
                            "address": a.theme
                        },
                        method: "POST",
                        success: function(msg){
                            if_collect = msg[0];
                            if_thumbs = msg[1];
                            if(if_collect===1){
                                $("#collect").css("background-color","#e7ac7a");
                            }
                            else $("#collect").css("background-color","#fdf4ea");
                            if(if_thumbs===1){
                                $("#thumbs_up").css("background-color","#e7ac7a");
                            }
                            else $("#thumbs_up").css("background-color","#fdf4ea");
                        }
                    });
                    $.ajax({
                        url: "getCollectAndLikesNum",
                        data: {
                            "address": a.theme
                        },
                        method: "POST",
                        success: function(msg){
                            $("#likesNum").text(msg[0]);
                            $("#collectionNum").text(msg[1]);
                        }
                    });
                }
            });
            $.ajax({
                url: "/ifFocus",
                data: {
                    "userName": $("#userName").text(),
                    "focus_who": master
                },
                method: "post",
                success: function(msg){
                    if(msg===false){
                        $("#follow").css("background-color", "#959595")
                        $("#follow").text("Focused");
                    }
                    else {
                        $("#follow").css("background-color","#e487fa");
                        $("#follow").text("Focus");
                    }
                }
            });

        }
    }
});
let c = new Vue({
    el: "#c",
    data: {
        show3: false
    },
    methods: {
        update() {
            this.$forceUpdate();
        }
    }
});
let b = new Vue({
    el: "#b",
    data: {
        show1: false
    },
    methods: {
        update() {
            this.$forceUpdate();
        }
    }
});

let d = new Vue({
    el: "#comment",
    data: {
        show4: false,
        list_reviewer: [],
        list_comment: [],
        list_address: []
    },
    methods: {
        update() {
            this.$forceUpdate();
        }
    }
});
$(function() {
    layui.use('layedit', function(){
        var layedit = layui.layedit
            ,$ = layui.jquery;

        //构建一个默认的编辑器
        var index = layedit.build('LAY_demo1');
        $("#comment_submit").click(function(){
             $.ajax({
                url: "saveComments",
                data: {
                    "content": layedit.getContent(index),
                    "master": $("#article_master").text(),
                    "article": $("#article_name").text()
                },
                method: "POST",
                success: function(){
                    $("#text_comment").hide();
                    comment_if_show = false;
                }
             });
            d.list_comment.push(layedit.getContent(index));
            d.list_address.push("/headPortrait/"+$("#userName").text()+"/userLogo.jpg");
            d.list_reviewer.push($("#userName").text());
            layedit.setContent(index,"",false);
        });
    });
    $.ajax({
        url: "/findAllArticles",
        method: "POST",
        data: {},
        success: function(list){
            for(let i=0;i<list.length;i++){
                a.list_name.push(list[i].name);
                a.list_master.push(list[i].master);
                a.list_address.push(list[i].address);
            }
            a.update();
        },
    });
    $("#h1").click(function() {
        a.show2 = true;
        b.show1 = false;
        c.show3 = false;
        d.show4 = false;
        d.list_comment.splice(0,d.list_comment.length);
        d.list_reviewer.splice(0,d.list_reviewer.length);
        d.list_address.splice(0,d.list_address.length);
        $("#sec-head").html("");
        a.update();
        b.update();
        c.update();
        d.update();
        $("#text_comment").hide();
        comment_if_show = false;
    });
    $("#search").keyup(function() {
        let text = $("#search").val();
        $.ajax({
            url: "/allArticlesSearch",
            data: {
                "text": text
            },
            method: "POST",
            success: function(list){
                a.list_name.splice(0,a.list_name.length);
                a.list_address.splice(0,a.list_address.length);
                a.list_master.splice(0,a.list_master.length);
                for(let i=0;i<list.length;i++){
                    a.list_name.push(list[i].name);
                    a.list_master.push(list[i].master);
                    a.list_address.push(list[i].address);
                }
                a.update();
            }
        })
    });
    $("#about").click(function() {
        let html = " <div class=\"my-3 p-3 bg-white rounded box-shadow\" id=\"sec-head\" style=\"overflow-y: scroll;overflow-x: hidden;\">\n" +
            "         <p>\n" +
            "             <font face=\"华文行楷\" size=\"100\">About</font>\n" +
            "         </p>\n" +
            "        <ul>\n" +
            "            <li>\n" +
            "                <a id=\"introduction\">\n" +
            "                   <font face=\"华文行楷\" size=\"60\">Website introduction</font>\n" +
            "                </a>\n" +
            "            </li>\n" +
            "            <li>\n" +
            "                <a id=\"developer\">\n" +
            "                   <font face=\"华文行楷\" size=\"60\">Developer</font>\n" +
            "                </a>\n" +
            "            </li>\n" +
            "            <li>\n" +
            "                <a href=\"https://www.runoob.com/markdown/md-tutorial.html\" target=\"_blank\">\n" +
            "                   <font face=\"华文行楷\" size=\"60\">Learn about markdown</font>\n" +
            "                </a>\n" +
            "            </li>\n" +
            "        </ul>\n" +
            "        <style>\n" +
            "            #sec-head::-webkit-scrollbar {\n" +
            "                display: none;/*隐藏滚动条*/\n" +
            "            }\n" +
            "        </style>\n" +
            "    </div>";
        $("#top-head").html(html);
    });
    $("#iframe").load(function(){
        alert("Change successfully!");
        location.reload();
    });
    $("#collect").mouseover(function(){
        if(if_collect===0)
            $("#collect").css("background-color","#e7ac7a");
    });
    $("#collect").mouseout(function(){
        if(if_collect===0)
            $("#collect").css("background-color","#fdf4ea");
    });
    $("#thumbs_up").mouseover(function(){
        if(if_thumbs===0)
            $("#thumbs_up").css("background-color","#e7ac7a");
    });
    $("#thumbs_up").mouseout(function(){
        if(if_thumbs===0)
            $("#thumbs_up").css("background-color","#fdf4ea");
    });
    $("#collect").click(function(){
        if(if_collect===0){
            $.ajax({
                url: "/collectIncrease",
                method: "POST",
                data: {
                    "address": a.theme
                },
                success: function(){
                    $("#collectionNum").text(parseInt($("#collectionNum").text())+1);
                }
            });
            if_collect = 1;
        }
        else{
            $.ajax({
                url: "/collectDecrease",
                method: "POST",
                data: {
                    "address": a.theme
                },
                success: function(){
                    $("#collectionNum").text(parseInt($("#collectionNum").text())-1);
                }
            });
            $("#collect").css("background-color","#fdf4ea");
            if_collect = 0;
        }
    });
    $("#thumbs_up").click(function(){
        if(if_thumbs===0){
            $.ajax({
                url: "/thumbsIncrease",
                method: "POST",
                data: {
                    "address": a.theme
                },
                success: function(){
                    $("#likesNum").text(parseInt($("#likesNum").text())+1);
                }
            });
            if_thumbs = 1;
        }
        else{
            $.ajax({
                url: "/thumbsDecrease",
                method: "POST",
                data: {
                    "address": a.theme
                },
                success: function(){
                    $("#likesNum").text(parseInt($("#likesNum").text())-1);
                }
            });
            $("#thumbs_up").css("background-color","#fdf4ea");
            if_thumbs = 0;
        }
    });
    $("#topic").click(function(){
        window.open("/toTopic?master="+$("#m_name").text()+"&userName="+$("#userName").text());
        a.show2 = true;
        b.show1 = false;
        c.show3 = false;
        $("#sec-head").html("");
        a.update();
        b.update();
        c.update();
    });
    $("#follow").click(function(){
        if($("#follow").text()==="Focus"){
            $.ajax({
               url: "/focusIncrease",
               data: {
                   "userName": $("#userName").text(),
                   "focus_who": $("#m_name").text()
               },
               method: "post",
               success: function(){
                   $("#follow").css("background-color", "#959595")
                   $("#follow").text("Focused");
               }
            });
        }
        else{
            $.ajax({
                url: "/focusDecrease",
                data: {
                    "userName": $("#userName").text(),
                    "focus_who": $("#m_name").text()
                },
                method: "post",
                success: function(){
                    $("#follow").css("background-color","#e487fa");
                    $("#follow").text("Focus");
                }
            });
        }
    });
    $("#write_comment").mouseover(function(){
       $("#write_comment").css("background", "radial-gradient(circle at 70px 70px,#6facec,#054b9a)")
    });
    $("#write_comment").mouseout(function(){
       $("#write_comment").css("background","radial-gradient(circle at 70px 70px,#b1cce8,#2e84e8)");
    });
    let write_comment_if_mouse_down = false;
    let disX;
    let disY;
    let startTime;
    let endTime;
    $("#write_comment").mousedown(function(e){
        e.stopPropagation();
        e.preventDefault();
        //算出鼠标相对元素的位置
        write_comment_if_mouse_down = true
        disX = e.clientX - $("#write_comment")[0].offsetLeft;
        disY = e.clientY - $("#write_comment")[0].offsetTop;
        startTime = new Date().getTime();
    });
    $("#write_comment").mouseup(function(){
        $("#write_comment").attr("data-target","");
        endTime = new Date().getTime()
        write_comment_if_mouse_down = false
        let left = $("#write_comment")[0].offsetLeft;
        if(left <= 840){
            let x = $("#write_comment")[0].offsetLeft;
            while(x>254){
                $("#write_comment").css("left",x--);
            }
        }
        else{
            let x = $("#write_comment")[0].offsetLeft;
            while(x<1460){
                $("#write_comment").css("left",x++);
            }
        }
        return false
    });
    let comment_if_show = false;
    $("#write_comment").click(function(){
        if(endTime-startTime<=180){
            if(comment_if_show==false) {
                $("#text_comment").show();
                $('html, body').animate({scrollTop: $('#box').offset().top}, 1000);
            }
            else {
                $("#text_comment").hide();
            }
            comment_if_show = !comment_if_show;
        }
    });
    $("#write_comment").mousemove(function(e){
        if (write_comment_if_mouse_down) {
            // 用鼠标的位置减去鼠标相对元素的位置，得到元素的位置
            let left = e.clientX - disX;
            let top = e.clientY - disY;
            // 设置按钮移动的边界
            if (top < 0) {
                top = 0
            } else if (top > document.body.clientHeight - $("#write_comment")[0].offsetHeight) {
                top = document.body.clientHeight - $("#write_comment")[0].offsetHeight
            }
            if (left < 0) {
                left = 0
            } else if (left > document.body.clientWidth - $("#write_comment")[0].offsetWidth) {
                left = document.body.clientWidth - $("#write_comment")[0].offsetWidth
            }
            // 移动当前元素
            $("#write_comment").css("left",left+"px");
            $("#write_comment").css("top",top+"px");
        }
    });
})
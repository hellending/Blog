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
                url: "/loadArticle",
                data: {
                    "address": address
                },
                method: "post",
                success: function(msg){
                    b.show1 = true;
                    a.show2 = false;
                    c.show3 = true;
                    a.theme = address;
                    a.update();
                    b.update();
                    c.update();
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
                            console.log(if_collect);
                            console.log(if_thumbs);
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
$(function() {
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
        $("#sec-head").html("");
        a.update();
        b.update();
        c.update();
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
    });
})
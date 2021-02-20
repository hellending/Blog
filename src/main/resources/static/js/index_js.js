let a = new Vue({
    el: "#top-head",
    data: {
        list_name: [],
        list_master: [],
        list_address: [],
        show2: true
    },
    methods: {
        update() {
            this.$forceUpdate();
        },
        re(address) {
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
                    a.update();
                    b.update();
                    c.update();
                    $("#sec-head").html(msg);
                }
            })
        }
    }
});
let c = new Vue({
    el: "#sec-head",
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
    el: "#h1",
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
    })
})
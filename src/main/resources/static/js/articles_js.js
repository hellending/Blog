var a = new Vue({
    el: "#top-head",
    data: {
        list_name: [],
        list_master: [],
        list_address: [],
        show2: true,
        check_all: false,
        list_del: [],
        list_checked: [],
        checked_num: 0
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
        },
        checkbox(index){
            if(event.target.checked){
                this.list_del.push(index);
                this.checked_num++;
            }
            else{
                for(let i=0;i<this.list_del.length;i++){
                    if(this.list_del[i]==index){
                        this.list_del.splice(i,1);
                    }
                }
                this.checked_num--;
            }
            a.update();
        },
        control_all(){
            if(event.target.checked){
                this.list_del.splice(0,this.list_del.length);
                for(let i=0;i<this.list_checked.length;i++){
                    this.list_checked[i] = true;
                    this.list_del.push(i);
                }
                this.checked_num = this.list_checked.length;
            }
            else{
                for(let i=0;i<this.list_checked.length;i++){
                    this.list_checked[i] = false;
                }
                this.checked_num = 0;
                this.list_del.splice(0,this.list_del.length);
            }
            a.update();
        }
    }
});

var c = new Vue({
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

var b = new Vue({
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

var d = new Vue({
    el: "#decide",
    data: {},
    methods: {
        if_show(){
            return a.checked_num>0;
        }
    }
});

$(function() {
    let flag = 0;
    $.ajax({
        url: "/getArticles",
        method: "POST",
        data: {},
        success: function(list){
            for(let i=0;i<list.length;i++){
                a.list_name.push(list[i].name);
                a.list_master.push(list[i].master);
                a.list_address.push(list[i].address);
                a.list_checked.push(false);
            }
            console.log()
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
            url: "/personalSearch",
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

    $("#delete").click(function() {
        if(flag===0){
            flag = 1;
            a.check_all = true;
            a.update();
            $("#delete").css("background-color","#74de57");
        }
        else{
            flag = 0;
            a.check_all = false;
            for(let i=0;i<a.list_checked.length;i++){
                a.list_checked[i] = false;
            }
            a.list_del.splice(0,a.list_del.length);
            a.checked_num = 0;
            a.update();
            $("#delete").css("background-color","#ffffff");
        }
    });
    $("#decide").click(function() {
        let list_address = [];
        for(let i=0;i<a.list_del.length;i++){
            list_address.push(a.list_address[a.list_del[i]]);
        }
        $.ajax({
            url: "/deleteArticles",
            data: {
                list_address: list_address
            },
            method: "POST",
            success: function(){}
        });
        let set = new Set();
        for(let i in list_address){
             set.add(i);
        }
        for(let i=0;i<a.list_address.length;){
            if(a.list_address[i] in set){
                a.list_address.splice(i,1);
                a.list_name.splice(i,1);
                a.list_master.splice(i,1);
                a.list_del.splice(i,1);
                a.list_checked.splice(i,1);
            }
            else i++;
        }
        a.checked_num = 0;
        a.update();
        alert("Successfully delete!");
        window.location.reload();
    })
});

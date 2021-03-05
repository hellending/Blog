var a = new Vue({
    el: "#top-head",
    data: {
        list_name: [],
        list_address: []
    },
    methods: {
        update() {
            this.$forceUpdate();
        },
        re(name) {
            window.open("/toTopic2?master="+name);
        },
        del(name){
            $.ajax({
               url: "/cancelFocus",
               data: {
                   "focus_who": name
               },
                method: "POST",
                success: function (){
                    location.reload();
                }
            });
        }
    }
});
$(function(){
   $.ajax({
       url: "/getFocus",
       method: "post",
       success: function(list){
            for(let i=0;i<list.length;i++){
                a.list_name.push(list[i]);
                let s = "/headPortrait/"+list[i]+"/userLogo.jpg"
                a.list_address.push(s);
            }
            a.update();
       }
   });
});
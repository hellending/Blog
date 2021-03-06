var a = new Vue({
    el: ".row",
    data: {
        list: ["河北","山西","内蒙古","辽宁","吉林","黑龙江","江苏","浙江","安徽",
            "福建","江西","山东","河南","湖北","湖南","广东","广西","海南","四川","贵州","云南","西藏","陕西","甘肃","青海","宁夏","新疆"],
        list_河北: ["石家庄", "保定市", "秦皇岛", "唐山市", "邯郸市", "邢台市", "沧州市", "承德市", "廊坊市", "衡水市", "张家口"],
        list_山西: ["太原市", "大同市", "阳泉市", "长治市", "临汾市", "晋中市", "运城市" ,"晋城市", "忻州市" ,"朔州市" ,"吕梁市"],
        list_内蒙古: [ "呼和浩特", "呼伦贝尔" ,"包头市" ,"赤峰市" ,"乌海市" ,"通辽市", "鄂尔多斯", "乌兰察布", "巴彦淖尔"],
        list_辽宁:["盘锦市", "鞍山市", "抚顺市", "本溪市", "铁岭市", "锦州市", "丹东市", "辽阳市", "葫芦岛","阜新市","朝阳市","营口市"],
        list_吉林:["吉林市" ,"通化市", "白城市", "四平市", "辽源市" ,"松原市", "白山市"],
        list_黑龙江: ["伊春市", "牡丹江" ,"大庆市", "鸡西市", "鹤岗市", "绥化市", "双鸭山", "七台河", "佳木斯", "黑河市", "齐齐哈尔市"],
        list_江苏: [ "无锡市", "常州市", "扬州市", "徐州市" ,"苏州市", "连云港" ,"盐城市" ,"淮安市", "宿迁市" ,"镇江市" ,"南通市", "泰州市"],
        list_浙江: ["绍兴市", "温州市" ,"湖州市" ,"嘉兴市", "台州市", "金华市", "舟山市" ,"衢州市" ,"丽水市"],
        list_安徽: ["合肥市", "芜湖市", "亳州市", "马鞍山" ,"池州市", "淮南市" ,"淮北市", "蚌埠市" ,"巢湖市" ,"安庆市" ,"宿州市" ,"宣城市" ,"滁州市" ,"黄山市" ,"六安市" ,"阜阳市", "铜陵市"],
        list_福建: [ "福州市", "泉州市", "漳州市", "南平市", "三明市", "龙岩市", "莆田市", "宁德市"],
        list_江西: [ "南昌市", "赣州市", "景德镇", "九江市", "萍乡市", "新余市", "抚州市", "宜春市", "上饶市", "鹰潭市", "吉安市"],
        list_山东: ["潍坊市", "淄博市", "威海市", "枣庄市" ,"泰安市" ,"临沂市" ,"东营市" ,"济宁市", "烟台市", "菏泽市", "日照市", "德州市" ,"聊城市" ,"滨州市" ,"莱芜市"],
        list_河南: ["郑州市", "洛阳市", "焦作市", "商丘市", "信阳市", "新乡市", "安阳市","开封市", "漯河市", "南阳市", "鹤壁市", "平顶山", "濮阳市", "许昌市", "周口市","三门峡", "驻马店"],
        list_湖北: [ "荆门市", "咸宁市", "襄樊市", "荆州市", "黄石市", "宜昌市", "随州市", "鄂州市", "孝感市", "黄冈市","十堰市"],
        list_湖南: [ "长沙市", "郴州市", "娄底市", "衡阳市", "株洲市", "湘潭市", "岳阳市", "常德市", "邵阳市", "益阳市", "永州市", "张家界", "怀化市"],
        list_广东: ["江门市", "佛山市", "汕头市", "湛江市", "韶关市", "中山市", "珠海市", "茂名市", "肇庆市", "阳江市", "惠州市", "潮州市", "揭阳市", "清远市", "河源市", "东莞市","汕尾市", "云浮市"],
        list_广西: ["南宁市", "贺州市", "柳州市", "桂林市", "梧州市", "北海市","玉林市", "钦州市", "百色市", "防城港", "贵港市", "河池市", "崇左市", "来宾市"],
        list_海南: ["海口市", "三亚市"],
        list_四川: ["乐山市" ,"雅安市", "广安市", "南充市", "自贡市", "泸州市", "内江市", "宜宾市", "广元市", "达州市", "资阳市", "绵阳市", "眉山市", "巴中市", "攀枝花", "遂宁市", "德阳市"],
        list_贵州: ["贵阳市", "安顺市", "遵义市", "六盘水"],
        list_云南: ["昆明市", "玉溪市", "大理市", "曲靖市", "昭通市", "保山市", "丽江市", "临沧市"],
        list_西藏: ["拉萨市", "阿里"],
        list_陕西: ["咸阳市", "榆林市", "宝鸡市", "铜川市", "渭南市", "汉中市", "安康市", "商洛市", "延安市"],
        list_甘肃: ["兰州市", "白银市", "武威市", "金昌市", "平凉市", "张掖市", "嘉峪关", "酒泉市", "庆阳市", "定西市", "陇南市", "天水市"],
        list_青海: ["西宁市"],
        list_宁夏: ["银川市", "固原市", "青铜峡市", "石嘴山市", "中卫市"],
        list_新疆: ["乌鲁木齐", "克拉玛依市"],
        map: new Map(),
        target: ""
    },
    methods: {
        update(){
            this.$forceUpdate();
        }
    }
});
$(function(){
    a.map.set("河北",a.list_河北);
    a.map.set("山西",a.list_山西);
    a.map.set("内蒙古",a.list_内蒙古);
    a.map.set("辽宁",a.list_辽宁);
    a.map.set("吉林",a.list_吉林);
    a.map.set("黑龙江",a.list_黑龙江);
    a.map.set("江苏",a.list_江苏);
    a.map.set("浙江",a.list_浙江);
    a.map.set("安徽",a.list_安徽);
    a.map.set("福建",a.list_福建);
    a.map.set("江西",a.list_江西);
    a.map.set("山东",a.list_山东);
    a.map.set("河南",a.list_河南);
    a.map.set("湖北",a.list_湖北);
    a.map.set("湖南",a.list_湖南);
    a.map.set("广东",a.list_广东);
    a.map.set("广西",a.list_广西);
    a.map.set("海南",a.list_海南);
    a.map.set("四川",a.list_四川);
    a.map.set("贵州",a.list_贵州);
    a.map.set("云南",a.list_云南);
    a.map.set("西藏",a.list_西藏);
    a.map.set("陕西",a.list_陕西);
    a.map.set("甘肃",a.list_甘肃);
    a.map.set("青海",a.list_青海);
    a.map.set("宁夏",a.list_宁夏);
    a.map.set("新疆",a.list_新疆);
    $("#state").blur(function() {
        let b = $("#state").val();
        a.target = b;
        a.update();
    }),
    $("#ID").blur(function() {
        let a = $("#ID").val();
        if(a.length==0){
            $("#_ID").text("");
            return;
        }
        if(a.length!=18){
            $("#_ID").text("身份证格式有误");
            $("#_ID").css("color","#bb0707");
        }
        else{
            if(!isNaN(a))
             $("#_ID").text("");
            else{
                if(a.charAt(a.length-1)=='X'&&!
                    isNaN(a.substring(0,a.length-1))){
                    $("#_ID").text("");
                }
                else{
                    console.log(11111);
                    $("#_ID").text("身份证格式有误");
                    $("#_ID").css("color","#bb0707");
                }
            }
        }
    }),
    $("#email").blur(function() {
        let b = $("#email").val();
        let format = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
        if(b.length==0){
            $("#_email").text("");
            return;
        }
        if(!format.test(b)){
            $("#_email").text("邮箱格式有误");
            $("#_email").css("color","#bb0707");
        }
        else{
            $("#_email").text("");
        }
    }),
    $("#submit").click(function() {
            if($("#_email").text().length==0&&$("#_ID").text().length==0){
                return true;
            }
            else return false;
    })
})

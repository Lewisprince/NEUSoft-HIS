new Vue({
    el:'#app',
    data:{
        user: {
            ID:"",
            userName:"",
            password:"",
            realname:"",
            office:"",
            title:""}
    },

    methods: {
        show_registration: function () {
            var url = document.getElementById("url");
            var src = document.createAttribute("src");
            src.value = 'member-add.html';
            url.attributes.setNamedItem(src);
        },
        show_send_out_medicine: function () {
            var url = document.getElementById("url");
            var src = document.createAttribute("src");
            src.value = 'sendOutMedicine.html';
            url.attributes.setNamedItem(src);
        },
        show_money_collect: function () {
            var url = document.getElementById("url");
            var src = document.createAttribute("src");
            src.value = 'money-collect.html';
            url.attributes.setNamedItem(src);
        },
        show_give_back: function () {
            var url = document.getElementById("url");
            var src = document.createAttribute("src");
            src.value = 'giveBackNumbers.html';
            url.attributes.setNamedItem(src);
        }
    },
    created:function(){
        var userName = location.search.split("=")[1];
        var _this = this;
        axios.get("/webdesign/user/findCollectorByName.do", {
            params: {
                userName: userName
            }
        }).then(function (response) {
            _this.user = response.data;
        }).catch(function (err) {
        });
    }
});
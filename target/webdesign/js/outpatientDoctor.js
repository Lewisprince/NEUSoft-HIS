
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
        show_doctor_category: function () {
            var url = document.getElementById("url");
            var src = document.createAttribute("src");
            src.value = 'doctor-category.html';
            url.attributes.setNamedItem(src);
        },
        show_product_category: function () {
            var url = document.getElementById("url");
            var src = document.createAttribute("src");
            src.value = 'product-category.html';
            url.attributes.setNamedItem(src);
        }
    },
    created:function(){
        var userName = location.search.split("=")[1];
        var _this = this;
        axios.get("/webdesign/user/findByName.do", {
            params: {
                userName: userName
            }
        }).then(function (response) {
            _this.user = response.data;
        }).catch(function (err) {
        });
    }
});
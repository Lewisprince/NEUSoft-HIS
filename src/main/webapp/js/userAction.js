var vue = new Vue({
    el: "#app",
    data: {
        user: {
            ID:"",
            userName:"",
            password:"",
            realname:"",
            office:"",
            title:""},
        userList: []
    },
    methods: {
        findAll: function () {
            var _this = this;
            axios.get("/webdesign/user/findAll.do").then(function (response) {
                _this.userList = response.data;
                console.log(_this.userList);
            }).catch(function (err) {
                console.log(err);
            });
        },
        findByID: function (userid) {
            var _this = this;
            axios.get("/webdesign/user/findByID.do", {
                params: {
                    userID: userid
                }
            }).then(function (response) {
                _this.user = response.data;
                console.log(response.data);
                $('#myModal').modal("show");
            }).catch(function (err) {
            });

        },
        update: function (user) {
            var _this = this;
            axios.post("/webdesign/user/update.do",_this.user).then(function (response) {
                _this.findAll();
            }).catch(function (err) {
            });
        }
    },
    created:function(){
        this.findAll();
    }
});
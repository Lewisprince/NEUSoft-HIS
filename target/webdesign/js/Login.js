var vue = new Vue({
    el:'#app',
    data:{
        userName: "",
        password: "",
        loginLoading: false,
        siteName:"东软云HIS医疗系统",
        options: [{
            value: '1',
            label: '门诊医生'
        }, {
            value: '2',
            label: '医技医生'
        }, {
            value: '3',
            label: '药房操作员'
        }, {
            value: '4',
            label: '挂号收费员'
        }, {
            value: '5',
            label: '财务管理员'
        }, {
            value: '6',
            label: '医院管理员'
        }],
        value: ''
    },

    methods: {
        Login: function(){
            APP = this;
            APP.loginLoading = true;
            if(Number(APP.value) == 1) {
                axios.post('/webdesign/user/login.do', {
                    kind: APP.value,
                    userName: APP.userName,
                    password: APP.password
                }).then(function (response) {
                    if (response.data.realname != null) {
                        window.location = "outpatientDoctor.html?userName="+APP.userName;
                    }
                    else{
                        APP.loginLoading = false;
                        APP.userName = "";
                        APP.password = "";
                        alert("用户名或密码错误！");
                    }
                })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
            else if(Number(APP.value) == 4){
                axios.post('/webdesign/user/moneyCollectorLogin.do', {
                    kind: APP.value,
                    userName: APP.userName,
                    password: APP.password
                }).then(function (response) {
                    console.log(response);
                    if (response.data.realname != null) {
                        window.location = "moneyCollector.html?userName="+APP.userName;
                    }
                    else{
                        APP.loginLoading = false;
                        APP.userName = "";
                        APP.password = "";
                        alert("用户名或密码错误！");
                    }
                })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
            else if(Number(APP.value) == 6){
                axios.post('/webdesign/user/managerLogin.do', {
                    kind: APP.value,
                    userName: APP.userName,
                    password: APP.password
                }).then(function (response) {
                    console.log(response);
                    if (response.data.realname != null) {
                        window.location = "user.html";
                    }
                    else{
                        APP.loginLoading = false;
                        APP.userName = "";
                        APP.password = "";
                        alert("用户名或密码错误！");
                    }
                })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        }
    }
});

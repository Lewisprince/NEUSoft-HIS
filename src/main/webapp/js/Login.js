var vue = new Vue({
    el:'#app',
    data:{
        userName: "",
        password: "",
        loginLoading: false,
        siteName:"������HISҽ��ϵͳ",
        options: [{
            value: '1',
            label: '����ҽ��'
        }, {
            value: '2',
            label: 'ҽ��ҽ��'
        }, {
            value: '3',
            label: 'ҩ������Ա'
        }, {
            value: '4',
            label: '�Һ��շ�Ա'
        }, {
            value: '5',
            label: '�������Ա'
        }, {
            value: '6',
            label: 'ҽԺ����Ա'
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
                        alert("�û������������");
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
                        alert("�û������������");
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
                        alert("�û������������");
                    }
                })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        }
    }
});

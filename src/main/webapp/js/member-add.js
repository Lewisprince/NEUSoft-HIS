var vue =new Vue({
    el:'#app',
    data:{
        officeList:[],
        doctorList:[],
        options1: [{
            value: '男'
        }, {
            value: '女'
        }],
        options2: [{
            value: '是'
        }, {
            value: '否'
        }],
        kindOptions: [{
            value: '主任医师'
        }, {
            value: '主治医师'
        }],
        doctorOptions: [],
        officeOptions: [],
        ruleForm: {
            medical_history_number:'',
            name:'',
            sex:'',
            id_number:'',
            home_address:'',
            need_medical_book:'',
            office:'',
            kind:'',
            doctor:'',
            birthday:'',
            age:''
        },

        rules: {
            medical_history_number: [
                {validator:this.checkAge, trigger: 'blur'},
                { min: 6, max: 6, message: '病历号是 6 位数字', trigger: 'blur' }
            ],
            name: [
                { required: true, message: '请填患者姓名', trigger: 'blur' }
            ],
            sex: [
                { required: true, message: '请选择性别', trigger: 'change' }
            ],
            id_number: [
                { required: true, message: '请填写身份证号', trigger: 'blur' }
            ],
            home_address: [
                { required: true, message: '请填写家庭住址', trigger: 'blur' }
            ],
            need_medical_book: [
                { required: true, message: '请选择是否需要病历本', trigger: 'change' }
            ],
            office: [
                { required: true, message: '请选择科室', trigger: 'change' }
            ],
            kind: [
                { required: true, message: '请选择医生类别', trigger: 'change' }
            ],
            doctor: [
                { required: true, message: '请选择医生', trigger: 'change' }
            ],
            birthday: [
                { type: 'date', required: true, message: '请选择出生日期', trigger: 'change'}
            ]

        }
    },

    methods: {
        autoFill:function() {
            var _this = this;
            axios.get("/webdesign/registrationInformation/findByMedicalNum.do", {
                params: {
                    medicalNumber: _this.ruleForm.medical_history_number
                }
            }).then(function (response) {
                if(response.data.length!=0) {
                    _this.ruleForm.name = response.data.name;
                    if (response.data.gender == 1) {
                        _this.ruleForm.sex = "男";
                    } else if (response.data.gender == 2) {
                        _this.ruleForm.sex = "女";
                    }
                    _this.ruleForm.id_number = response.data.id_card_number;
                    _this.ruleForm.home_address = response.data.home_address;
                    _this.ruleForm.birthday = response.data.birthday;

                    var year = 1000 * 60 * 60 * 24 * 365;
                    var now = new Date();
                    var birthday = new Date(_this.ruleForm.birthday);
                    _this.ruleForm.age = parseInt((now - birthday) / year);
                }
                else
                {
                    alert("病历号输入错误!");
                }
            }).catch(function (err) {
            });
        },

        renewDoctor:function(){
            if(this.ruleForm.kind.length>0 &&this.ruleForm.office.length>0)
            {
                this.ruleForm.doctor='';
                this.doctorOptions=[];
                var _this = this;
                axios.get("/webdesign/user/findDoctors.do", {
                    params: {
                        officeName: _this.ruleForm.office,
                        title: _this.ruleForm.kind
                    }
                }).then(function (response) {
                    _this.doctorList = response.data;
                    var i;
                    for(i=0;i<_this.doctorList.length;i++) {
                        _this.doctorOptions.push({value: _this.doctorList[i]});
                    }
                    console.log(response.data);
                }).catch(function (err) {
                    console.log(err);
                });
            }

        },

        findOffice: function () {
            var _this = this;
            axios.get("/webdesign/registrationInformation/findOffice.do").then(function (response) {
                _this.userList = response.data;
                console.log(_this.userList);
            }).catch(function (err) {
                console.log(err);
            });
        },

        calculateAge: function(){
            var year = 1000 * 60 * 60 * 24 * 365;
            var now = new Date();
            var birthday = new Date(this.ruleForm.birthday);
            this.ruleForm.age = parseInt((now - birthday) / year);
        },


        registration:function () {
            var _this = this;
            axios.get("/webdesign/user/registration.do", {
                params: {
                    ID_card_number: _this.ruleForm.id_number,
                    name: _this.ruleForm.name,
                    gender: _this.ruleForm.sex,
                    birthday: _this.ruleForm.birthday,
                    age: _this.ruleForm.age,
                    doctorName: _this.ruleForm.doctor,
                    home_address: _this.ruleForm.home_address,
                    need_medical_history_booklet:_this.ruleForm.need_medical_book
                }
            }).then(function (response) {
                var responseList = response.data;
                var alertData = "挂号成功！\n\t您的病历号为："+responseList[0]+"\n\t您的挂号号为："+responseList[1]+
                "\n\t您的发票号为："+responseList[2]+"\n\t收取金额："+responseList[3]+"元";
                alert(alertData);
                console.log(response.data);
            }).catch(function (err) {
                console.log(err);
            });
        },

        submitForm:function(formName) {
            var _this = this;
            this.$refs[formName].validate(function(valid){
                if (valid) {
                    _this.registration();
                } else {
                    console.log('error submit!!');
                    return false;
                        }
        });
        },
        resetForm:function(formName) {
            this.$refs[formName].resetFields();
        }
    },
    mounted:function(){
        var _this = this;
        axios.get("/webdesign/user/findAllOffice.do").then(function (response) {
            _this.officeList = response.data;
            var i;
            for(i=0;i<_this.officeList.length;i++) {
                _this.officeOptions.push({value: _this.officeList[i]});
            }
        }).catch(function (err) {
            console.log(err);
        });
    }
});

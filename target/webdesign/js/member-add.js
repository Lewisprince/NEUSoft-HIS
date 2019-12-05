var vue =new Vue({
    el:'#app',
    data:{
        officeList:[],
        doctorList:[],
        options1: [{
            value: '��'
        }, {
            value: 'Ů'
        }],
        options2: [{
            value: '��'
        }, {
            value: '��'
        }],
        kindOptions: [{
            value: '����ҽʦ'
        }, {
            value: '����ҽʦ'
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
                { min: 6, max: 6, message: '�������� 6 λ����', trigger: 'blur' }
            ],
            name: [
                { required: true, message: '���������', trigger: 'blur' }
            ],
            sex: [
                { required: true, message: '��ѡ���Ա�', trigger: 'change' }
            ],
            id_number: [
                { required: true, message: '����д���֤��', trigger: 'blur' }
            ],
            home_address: [
                { required: true, message: '����д��ͥסַ', trigger: 'blur' }
            ],
            need_medical_book: [
                { required: true, message: '��ѡ���Ƿ���Ҫ������', trigger: 'change' }
            ],
            office: [
                { required: true, message: '��ѡ�����', trigger: 'change' }
            ],
            kind: [
                { required: true, message: '��ѡ��ҽ�����', trigger: 'change' }
            ],
            doctor: [
                { required: true, message: '��ѡ��ҽ��', trigger: 'change' }
            ],
            birthday: [
                { type: 'date', required: true, message: '��ѡ���������', trigger: 'change'}
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
                        _this.ruleForm.sex = "��";
                    } else if (response.data.gender == 2) {
                        _this.ruleForm.sex = "Ů";
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
                    alert("�������������!");
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
                var alertData = "�Һųɹ���\n\t���Ĳ�����Ϊ��"+responseList[0]+"\n\t���ĹҺź�Ϊ��"+responseList[1]+
                "\n\t���ķ�Ʊ��Ϊ��"+responseList[2]+"\n\t��ȡ��"+responseList[3]+"Ԫ";
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

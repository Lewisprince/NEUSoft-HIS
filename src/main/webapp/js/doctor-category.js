var vue = new Vue({
    el: "#app",
    data: {
        input:'',
        ruleForm: {
            mainClaim: '',
            currentDisease: '',
            treatment: '',
            diseaseHistory: '',
            allergy:'',
            bodyExam: '',
            advice: '',
            notice:'',
            diseases: [],
            dateAndTime: ''
        },
        rules: {
            dateAndTime: [
                {type: 'date', required: true, message: '请选择发病日期与时间', trigger: 'change'}
            ],
            diseases: [
                {type: 'array', required: true, message: '请至少选择一种疾病', trigger: 'change'}
            ]
        },
        ruleForm123: {
            name:'',
            method:'',
            use_amount:'',
            frequency:'',
            amount:''
        },
        rules123:{
            name: [ { required: true, message: '请选择药品', trigger: 'change' }

            ],
            amount: [
                { required: true, message: '请输入药品数量', trigger: 'blur' }
            ]
        },
        props: {
            label: 'name',
            children: 'zones',
            isLeaf: 'leaf'
        },
        diseaseOptions: [],
        diseaseList:[],
        value1: [],
        value2: [],
        medicines:[],
        textarea1: '',
        textarea2: '',
        registrationID: '',
        date: '',
        name_edit:'',
        method_edit:'',
        use_amount_edit:'',
        frequency_edit:'',
        amount_edit:'',
        current_editing_row_num:'',
        medicine_model:[],
        model_detail:[],
        prescription:[],
        waitingList:[],
        waited: [],
        currentRow: null,
        finished: [],
        currentRow1:null,
        currentRow2:null,
        modelName:'',
        prescriptionName:'',
        submitDialogVisible:false,
        centerDialogVisible: false,
        addDialogVisible: false
    },
    methods: {
        setCurrent:function(row) {
            this.$refs.singleTable.setCurrentRow(row);
        },
        handleCurrentChange:function(val) {
            this.currentRow = val;
            this.registrationID = val.registrationID;
        },
        submitForm123(formName) {
            var _this = this;
            if(_this.ruleForm123.name!="" && _this.ruleForm123.amount != "")
            {
                this.prescription.push({number:this.prescription.length+1,medicine_name:this.ruleForm123.name,method:this.ruleForm123.method,use_amount:this.ruleForm123.use_amount,
                            frequency:this.ruleForm123.frequency,amount:this.ruleForm123.amount});
                this.ruleForm123.name = '';
                this.ruleForm123.use_amount = '';
                this.ruleForm123.frequency = '';
                this.ruleForm123.method = '';
                this.ruleForm123.amount = '';
                this.addDialogVisible = false;

            }
            else {
                alert('药品名称及数量为必填项');
                return false;
            }
        },
        resetForm123(formName) {
            this.ruleForm123.name = '';
            this.ruleForm123.use_amount = '';
            this.ruleForm123.frequency = '';
            this.ruleForm123.method = '';
            this.ruleForm123.amount = ''
        },
        selectModel:function(val) {
            console.log(val);
            this.currentRow2 = val;
            this.modelName = val.name;
            var _this = this;
            axios.get("/webdesign/medicineInformation/getModelDetail.do",{
                params:{
                    modelName:_this.modelName
                }
            }).then(function (response) {
                _this.model_detail = [];
                console.log(response);
                var tempList = response.data;
                var i;
                for(i=0;i<tempList.length;i++) {
                    _this.model_detail.push({medicine_name: tempList[i].name, method:tempList[i].use_method, use_amount:tempList[i].use_amount
                    , frequency: tempList[i].use_frequency, amount:tempList[i].amount});
                }
            }).catch(function (err){console.log(err);});
        },
        useModel:function(){
            var i ;
            this.prescription = [];
            for(i=0;i<this.model_detail.length;i++)
            {
                this.prescription.push({number:i+1,medicine_name:this.model_detail[i].medicine_name, method:this.model_detail[i].method, use_amount:this.model_detail[i].use_amount
                    , frequency: this.model_detail[i].frequency, amount:this.model_detail[i].amount})
            }
        },
        handleEdit:function(row){
            this.name_edit = row.medicine_name;
            this.method_edit = row.method;
            this.use_amount_edit = row.use_amount;
            this.frequency_edit = row.frequency;
            this.amount_edit = row.amount;
            this.current_editing_row_num = row.number;
            this.centerDialogVisible = true;
        },
        editTable:function(){
            this.prescription.splice(this.current_editing_row_num-1,1);
            this.prescription.push({number:this.current_editing_row_num,medicine_name:this.name_edit, method:this.method_edit, use_amount:this.use_amount_edit,
            frequency:this.frequency_edit, amount:this.amount_edit});

            this.prescription = this.prescription.sort(function(obj1,obj2){
                var val1 = obj1.number;
                var val2 = obj2.number;
                return obj1.number-obj2.number;
            });
            this.centerDialogVisible = false;
        },
        open:function(row){
            this.$confirm('是否确定要删除该药品?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.deleteMedecine(row);
                this.$message({
                    type: 'success',
                    message: '删除成功!'
                });
        }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
        });
        },
        deleteAll:function(){
            this.$confirm('此操作会清空目前处方单，是否确定?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.prescription = [];
            this.$message({
                type: 'success',
                message: '删除成功!'
            });
        }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
        });
        },
        showAddDialog:function(){
            console.log(this.ruleForm123.name);
            this.addDialogVisible = true;
        },
        deleteMedecine:function(row){
            this.prescription.splice(row.number-1,1);
            var i ;
            for(i=row.number-1;i<this.prescription.length;i++)
            {
                this.prescription[i].number = this.prescription[i].number-1;
            }
        },
        submitPrescription:function(){
            if(this.registrationID.length!=0) {

                if (this.prescription.length != 0) {
                    var i;
                    var _this = this;
                    var all_use_method = '';
                    var all_medicines = '';
                    var all_use_amount = '';
                    var all_frequency = '';
                    var all_amount = '';
                    for (i = 0; i < _this.prescription.length; i++) {

                        if (i != 0) {
                            all_medicines = all_medicines + "," + _this.prescription[i].medicine_name;
                            all_use_method = all_use_method + "," + _this.prescription[i].method;
                            all_use_amount = all_use_amount + "," + _this.prescription[i].use_amount;
                            all_frequency = all_frequency + "," + _this.prescription[i].frequency;
                            all_amount = all_amount + "," + _this.prescription[i].amount;
                        } else {
                            all_medicines = all_medicines + _this.prescription[i].medicine_name;
                            all_use_method = all_use_method + _this.prescription[i].method;
                            all_use_amount = all_use_amount + _this.prescription[i].use_amount;
                            all_frequency = all_frequency + _this.prescription[i].frequency;
                            all_amount = all_amount + _this.prescription[i].amount;
                        }
                    }


                    axios.get("/webdesign/medicineInformation/submitPrescription.do", {
                        params: {
                            registrationID: _this.registrationID,
                            prescription_name: _this.prescriptionName,
                            medicine_number: _this.prescription.length,
                            medicine_list1: all_medicines,
                            use_method_list: all_use_method,
                            use_amount_list: all_use_amount,
                            use_frequency_list: all_frequency,
                            amount_list: all_amount
                        }
                    }).then(function (response) {
                    }).catch(function (err) {
                        console.log(err);
                    });
                    _this.prescription = [];
                    _this.submitDialogVisible = false;
                    alert("开立成功！");
                } else {
                    alert("请选择药品！");
                }
            }
            else {
                alert("请选择待诊患者！");
            }
        },


        handleClick:function(){},
        handleSelectionChange:function(){},



        setCurrent1:function(row) {
            this.$refs.singleTable1.setCurrentRow(row);
        },
        handleCurrentChange1:function(val) {
            this.currentRow1 = val;
        },
        submitForm: function (formName) {
            var _this = this;
            if(_this.registrationID.length!=0)
            {
            this.$refs[formName].validate(function (valid) {
                if (valid) {

                    axios.get("/webdesign/registrationInformation/doctorSeePatient.do", {
                        params: {
                            registrationID: _this.registrationID,
                            main_problem: _this.ruleForm.mainClaim,
                            current_disease_condition: _this.ruleForm.currentDisease,
                            current_disease_treatment: _this.ruleForm.treatment,
                            disease_history: _this.ruleForm.diseaseHistory,
                            allergy_history: _this.ruleForm.allergy,
                            physical_examination: _this.ruleForm.bodyExam,
                            advise:_this.ruleForm.advice,
                            notice: _this.ruleForm.notice,
                            East_West: 1,
                            disease_numbers: _this.ruleForm.diseases.length,
                            diseaseName: _this.ruleForm.diseases,
                            disease_time: _this.ruleForm.dateAndTime,
                            statement:2
                        },
                        paramsSerializer: params => {
                        return Qs.stringify(params, { indices: false })
                    } }).then(function (response) {
                             alert("保存成功");
                    }).catch(function (err) {
                         console.log(err);});
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });} else {alert("请选择待诊患者");}

            _this.waited=[];
            _this.finished=[];
            var userName = window.parent.location.search.split("=")[1];

            axios.get("/webdesign/registrationInformation/getWaitingList.do",{
                params:{
                    doctorName:userName
                }
            }).then(function(response){
                _this. waitingList = response.data;
                var i;
                for(i=0;i<_this.waitingList.length;i++)
                {
                    _this.waited.push({medicalNumber:_this.waitingList[i].medical_history_number,name:_this.waitingList[i].name,registrationID:_this.waitingList[i].id});
                }
            }).catch(function (err){console.log(err);});

            _this.waitingList = [];
            axios.get("/webdesign/registrationInformation/getFinishedList.do",{
                params:{
                    doctorName:userName
                }
            }).then(function(response){
                _this. waitingList = response.data;
                var i;
                for(i=0;i<_this.waitingList.length;i++)
                {
                    _this.finished.push({medicalNumber:_this.waitingList[i].medical_history_number,name:_this.waitingList[i].name,registrationID:_this.waitingList[i].id});
                }
            }).catch(function (err){console.log(err);});

        },
        resetForm: function (formName) {
            this.ruleForm.mainClaim='';
            this.ruleForm.currentDisease='';
            this.ruleForm.treatment='';
            this.ruleForm.diseaseHistory='';
            this.ruleForm.allergy='';
            this.ruleForm.bodyExam='';
            this.ruleForm.advice='';
            this.ruleForm.notice='';
            this.ruleForm.diseases=[];
            this.ruleForm.dateAndTime='';
        }

    },
    mounted:function(){
        var userName = window.parent.location.search.split("=")[1];
        var _this = this;
        axios.get("/webdesign/registrationInformation/getWaitingList.do",{
            params:{
                doctorName:userName
            }
        }).then(function(response){
           _this. waitingList = response.data;
           var i;
           for(i=0;i<_this.waitingList.length;i++)
           {
               _this.waited.push({medicalNumber:_this.waitingList[i].medical_history_number,name:_this.waitingList[i].name,registrationID:_this.waitingList[i].id});
           }
        }).catch(function (err){console.log(err);});

        _this.waitingList = [];
        axios.get("/webdesign/registrationInformation/getFinishedList.do",{
            params:{
                doctorName:userName
            }
        }).then(function(response){
            _this. waitingList = response.data;
            var i;
            for(i=0;i<_this.waitingList.length;i++)
            {
                _this.finished.push({medicalNumber:_this.waitingList[i].medical_history_number,name:_this.waitingList[i].name,registrationID:_this.waitingList[i].id});
            }
        }).catch(function (err){console.log(err);});


        axios.get("/webdesign/user/findAllDisease.do").then(function (response) {
            _this.diseaseList = response.data;
            var i;
            for(i=0;i<_this.diseaseList.length;i++) {
                _this.diseaseOptions.push({value: _this.diseaseList[i]});
            }
        }).catch(function (err) {
            console.log(err);
        });

        axios.get("/webdesign/medicineInformation/getAllMedicine.do").then(function (response) {

            var tempList = response.data;
            var i;

            for(i=0;i<tempList.length;i++) {
                _this.medicines.push({value: tempList[i]});
            }
        }).catch(function (err) {
            console.log(err);
        });

        axios.get("/webdesign/medicineInformation/getMedicineModel.do",{
            params:{
                doctorName:userName
            }
        }).then(function(response){
            var list = response.data;
            var i;
            var range;
            for(i=0;i<list.length;i++)
            {
                if(list[i].use_range == 1)
                    range = "个人";
                else if(list[i].use_range == 2)
                    range = "科室";
                else
                    range = "全院";
                _this.medicine_model.push({name:list[i].name,range:range});
            }
        }).catch(function (err){console.log(err);});

    }

});
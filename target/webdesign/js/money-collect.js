new Vue({
    el:'#app',
    data:{
        medicalNumber:'',
        centerDialogVisible: false,
        name:'',
        IDCardNumber:'',
        homeAddress:'',
        shouldCollect:'',
        realCollect:'',
        exchange:'',
        backInformation:[],
        tableData: [],
        multipleSelection: []
    },
    methods:{
        toggleSelection(rows) {
            if (rows) {
                rows.forEach(row => {
                    this.$refs.multipleTable.toggleRowSelection(row);
            });
            } else {
                this.$refs.multipleTable.clearSelection();
            }
        },
        handleSelectionChange:function(val) {
            this.multipleSelection = val;
        },
        settleAccount:function() {
            var _this = this;
            if(this.multipleSelection.length!=0)
            {
                axios.get("/webdesign/user/getTotalMoney.do", {
                    params: {
                        medicalNum: _this.medicalNumber
                    }
                }).then(function (response) {
                    _this.shouldCollect = response.data;
                }).catch(function (err) {
                    console.log(err);
                });
                this.centerDialogVisible = true;
            }
            else
                alert("请选择结算药品");
        },
        loadInformation:function(){
            var _this = this;

            axios.get("/webdesign/user/findPatientByMedicalNum.do", {
                params: {
                    medicalNum: _this.medicalNumber
                }
            }).then(function (response) {
                var responser = response.data;
                _this.name = responser.name;
                _this.IDCardNumber = responser.id_card_number;
                _this.homeAddress = responser.home_address;
            }).catch(function (err) {
                console.log(err);
            });



            axios.get("/webdesign/medicineInformation/getMedicineInformationByMedicalNum.do", {
                params: {
                    MedicalNumber: _this.medicalNumber
                }
            }).then(function (response) {
                var responseList = response.data;
                _this.tableData = [];
                for(i=0;i<responseList.length;i++)
                {
                    _this.tableData.push({medicalHisNum:_this.medicalNumber,
                        name:_this.name,medicalName:responseList[i].name,price:responseList[i].unit_price,
                        amount:responseList[i].amount,time:responseList[i].write_time});
                }
                console.log(response.data);
            }).catch(function (err) {
                console.log(err);
            });
        },
        cancelDialog:function () {
            this.centerDialogVisible = false;
            this.shouldCollect = '';
            this.realCollect = '';
            this.exchange = '';
        },
        countChange:function(){
            if(this.realCollect.length>0)
            {
                var temp = parseFloat(this.realCollect)-parseFloat(this.shouldCollect);
                if( parseFloat(this.realCollect)<parseFloat(this.shouldCollect))
                {
                    alert("实收金额应大于应收金额！");
                    temp = 0;
                }
                this.exchange = temp.toFixed(2);
            }
        },
        submitDialog:function(){

            var _this = this;

            axios.get("/webdesign/user/payMedicine.do", {
                params: {
                    medicalNumber: _this.medicalNumber
                }
            }).then(function (response) {
                console.log(response.data);
            }).catch(function (err) {
                console.log(err);
            });

            this.multipleSelection = [];
            this.tableData = [];
            this.loadInformation();
            this.multipleSelection = [];
            this.tableData = [];
            this.loadInformation();

            this.centerDialogVisible = false;
            this.shouldCollect = '';
            this.realCollect = '';
            this.exchange = '';

        }
    }

});

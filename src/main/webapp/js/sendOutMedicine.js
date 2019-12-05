new Vue({
    el:'#app',
    data:{
        pickerOptions: {
            disabledDate(time) {
                return time.getTime() > Date.now();
            },
            shortcuts: [{
                text: '今天',
                onClick(picker) {
                    picker.$emit('pick', new Date());
                }
            }, {
                text: '昨天',
                onClick(picker) {
                    const date = new Date();
                    date.setTime(date.getTime() - 3600 * 1000 * 24);
                    picker.$emit('pick', date);
                }
            }, {
                text: '一周前',
                onClick(picker) {
                    const date = new Date();
                    date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                    picker.$emit('pick', date);
                }
            }]
        },
        tableData: [],
        multipleSelection: [],
        value2: '',
        medicalNum:''
    },
    methods:{
        logInfo:function(){
        var _this = this;
         _this.tableData = [];
            axios.get("/webdesign/medicineInformation/getAllMedicineToSend.do",{
                params:{
                    medicalNum:_this.medicalNum,
                    selectedDate:_this.value2
                }
            }).then(function(response){
                var tempList = response.data;
                var i;
                for(i=0;i<tempList.length;i++)
                {
                    if(tempList[i].statement == 0)
                    {
                        _this.tableData.push({ID:tempList[i].id,name:tempList[i].name,unit_price:tempList[i].unit_price,amount:tempList[i].amount,
                            statement:"已退药",doctor:tempList[i].doctor_name,prescriptionName:tempList[i].prescription_name,
                            time:tempList[i].formated_time});
                    }
                    else if(tempList[i].statement == 1)
                    {
                        _this.tableData.push({ID:tempList[i].id,name:tempList[i].name,unit_price:tempList[i].unit_price,amount:tempList[i].amount,
                            statement:"已开立",doctor:tempList[i].doctor_name,prescriptionName:tempList[i].prescription_name,
                            time:tempList[i].formated_time});
                    }
                    else if(tempList[i].statement == 2)
                    {
                        _this.tableData.push({ID:tempList[i].id,name:tempList[i].name,unit_price:tempList[i].unit_price,amount:tempList[i].amount,
                            statement:"已付款",doctor:tempList[i].doctor_name,prescriptionName:tempList[i].prescription_name,
                            time:tempList[i].formated_time});
                    }
                    else
                    {
                        _this.tableData.push({ID:tempList[i].id,name:tempList[i].name,unit_price:tempList[i].unit_price,amount:tempList[i].amount,
                            statement:"已发药",doctor:tempList[i].doctor_name,prescriptionName:tempList[i].prescription_name,
                            time:tempList[i].formated_time});
                    }

                }
            }).catch(function (err){console.log(err);});
        },
    handleSelectionChange(val) {
        this.multipleSelection = val;
    },
        sendOutMedicine:function() {
            var _this = this;
            var i;
                var check = 0;
                for (i = 0; i < _this.multipleSelection.length; i++) {
                    if (_this.multipleSelection[i].statement != "已付款")
                        check = 1;
                }
                if (check != 1) {
                    var info = '';
                    for (i = 0; i < _this.multipleSelection.length; i++) {
                        if (i != 0)
                            info = info + "," + _this.multipleSelection[i].ID;
                        else
                            info = info + _this.multipleSelection[i].ID;
                    }

                    axios.get("/webdesign/medicineInformation/sendOutMedicine.do", {
                        params: {
                            informations: info
                        }
                    }).then(function (response) {
                        alert("发药成功！");
                        for (i = 0; i < _this.multipleSelection.length; i++) {
                            _this.multipleSelection[i].statement = "已发药";
                        }
                    }).catch(function (err) {
                        console.log(err);
                    });
                } else {
                    alert("请确保所有选中药品均为“已付款”状态！");
                }
        }

    }});

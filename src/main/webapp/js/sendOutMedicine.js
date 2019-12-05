new Vue({
    el:'#app',
    data:{
        pickerOptions: {
            disabledDate(time) {
                return time.getTime() > Date.now();
            },
            shortcuts: [{
                text: '����',
                onClick(picker) {
                    picker.$emit('pick', new Date());
                }
            }, {
                text: '����',
                onClick(picker) {
                    const date = new Date();
                    date.setTime(date.getTime() - 3600 * 1000 * 24);
                    picker.$emit('pick', date);
                }
            }, {
                text: 'һ��ǰ',
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
                            statement:"����ҩ",doctor:tempList[i].doctor_name,prescriptionName:tempList[i].prescription_name,
                            time:tempList[i].formated_time});
                    }
                    else if(tempList[i].statement == 1)
                    {
                        _this.tableData.push({ID:tempList[i].id,name:tempList[i].name,unit_price:tempList[i].unit_price,amount:tempList[i].amount,
                            statement:"�ѿ���",doctor:tempList[i].doctor_name,prescriptionName:tempList[i].prescription_name,
                            time:tempList[i].formated_time});
                    }
                    else if(tempList[i].statement == 2)
                    {
                        _this.tableData.push({ID:tempList[i].id,name:tempList[i].name,unit_price:tempList[i].unit_price,amount:tempList[i].amount,
                            statement:"�Ѹ���",doctor:tempList[i].doctor_name,prescriptionName:tempList[i].prescription_name,
                            time:tempList[i].formated_time});
                    }
                    else
                    {
                        _this.tableData.push({ID:tempList[i].id,name:tempList[i].name,unit_price:tempList[i].unit_price,amount:tempList[i].amount,
                            statement:"�ѷ�ҩ",doctor:tempList[i].doctor_name,prescriptionName:tempList[i].prescription_name,
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
                    if (_this.multipleSelection[i].statement != "�Ѹ���")
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
                        alert("��ҩ�ɹ���");
                        for (i = 0; i < _this.multipleSelection.length; i++) {
                            _this.multipleSelection[i].statement = "�ѷ�ҩ";
                        }
                    }).catch(function (err) {
                        console.log(err);
                    });
                } else {
                    alert("��ȷ������ѡ��ҩƷ��Ϊ���Ѹ��״̬��");
                }
        }

    }});

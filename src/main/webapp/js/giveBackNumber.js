new Vue({
    el:'#app',
    data:{
        medicalNumber:'',
        centerDialogVisible: false,
        name:'',
        IDCardNumber:'',
        currentRow: null,
        homeAddress:'',
        currentPage:1,   //Ĭ��ҳ��Ϊ1
        pagesize:5,  //Ĭ��һҳ��ʾ5��
        tableData: [],
        registrationID:[],
        multipleSelection: []
    },
    methods:{

        setCurrent:function(row) {
            this.$refs.singleTable.setCurrentRow(row);
        },
        handleCurrentChange:function(val) {
            this.currentRow = val;
        },
        loadInformation:function() {
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

            _this.tableData = [];
            axios.get("/webdesign/registrationInformation/getAllRegistrationInfoToday.do", {
                params: {
                    medicalNumber: _this.medicalNumber
                }
            }).then(function (response) {
                var responser = response.data;
                for(i=0;i<responser.length;i++)
                {
                    _this.registrationID.push(responser[i].id);
                    if(responser[i].statement == 0) {
                        _this.tableData.push({
                            number:i+1,
                            medicalHisNum: responser[i].medical_history_number,
                            name: responser[i].name,
                            IDNumber: responser[i].id,
                            date: responser[i].registration_onlyDay,
                            time: responser[i].registration_onlyTime,
                            office: responser[i].office,
                            statement: "���˺�"
                        });
                    }
                    else if(responser[i].statement == 1){
                        _this.tableData.push({
                            number:i+1,
                            medicalHisNum: responser[i].medical_history_number,
                            name: responser[i].name,
                            IDNumber: responser[i].id,
                            date: responser[i].registration_onlyDay,
                            time: responser[i].registration_onlyTime,
                            office: responser[i].office,
                            statement: "����"
                        });
                    }
                    else{
                        _this.tableData.push({
                            number:i+1,
                            medicalHisNum: responser[i].medical_history_number,
                            name: responser[i].name,
                            IDNumber: responser[i].id,
                            date: responser[i].registration_onlyDay,
                            time: responser[i].registration_onlyTime,
                            office: responser[i].office,
                            statement: "����"
                        });
                    }
                }
            }).catch(function (err) {
                console.log(err);
            });
        },
        handleSizeChange:function(size){   //һҳ��ʾ������
            this.pagesize = size;
        },
        handleCurrentChange1:function(currentPage){  //ҳ����ķ���
            this.currentPage = currentPage;
        },
        
        giveBackNum:function () {
            var _this = this;
            if(this.currentRow.statement=="����")
            {

                axios.get("/webdesign/user/givebackNo.do", {
                    params: {
                        registrationID: _this.registrationID[_this.currentRow.number-1]
                    }
                }).then(function (response) {
                    var responser = response.data;
                    alert("�˺ųɹ������ĳ�췢Ʊ��Ϊ��"+responser);
                }).catch(function (err) {
                    console.log(err);
                });

                this.currentRow.statement = "���˺�";
            }
            else {
                alert("ֻ�д�������µĹҺ���Ϣ�����˺ţ�");
            }
        }
    }

});

package com.forwawrd.web.controller;

import com.forwawrd.domain.Medicine;
import com.forwawrd.domain.MedicineInformation;
import com.forwawrd.domain.MedicineModel;
import com.forwawrd.domain.ModelDetail;
import com.forwawrd.service.MedicineInformationService;
import com.forwawrd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/medicineInformation")
@ResponseBody
public class MedicineInformationController {
    @Autowired
    private MedicineInformationService medicineInformationService;
    @Autowired
    private UserService userService;

    @RequestMapping("/getMedicineInformationByMedicalNum")
    public List<MedicineInformation> getMedicineInformationByMedicalNum(Integer MedicalNumber) {
        return medicineInformationService.getMedicineInformationByMedicalNum(MedicalNumber);
    }

    @RequestMapping("/getMedicineModel")
    public List<MedicineModel> getMedicineModel(String doctorName){
        Integer doctorID = userService.findIDByUserName(doctorName);
        return medicineInformationService.getMedicineModel(doctorID);
    }

    @RequestMapping("/getModelDetail")
    public List<ModelDetail> getModelDetail(String modelName){
        return medicineInformationService.findModelDetail(modelName);
    }

    @RequestMapping("/getAllMedicine")
    public List<String> getAllMedicine(){
        return medicineInformationService.getAllMedicine();
    }

    @RequestMapping("/submitPrescription")
    public void submitPrescription(Integer registrationID, String prescription_name, Integer medicine_number, String medicine_list1, String use_method_list, String use_amount_list, String use_frequency_list, String amount_list) {
        String medicine_list = "";
        String[] temp = medicine_list1.split(",");
        for(int i = 0;i < temp.length;i++)
        {
            if(i!=0)
                medicine_list = medicine_list + "," + medicineInformationService.findMedicineIDByName(temp[i]);
            else
                medicine_list = medicine_list + medicineInformationService.findMedicineIDByName(temp[i]);
        }
        medicineInformationService.submitPrescription(registrationID,prescription_name,medicine_number,medicine_list,use_method_list,use_amount_list,use_frequency_list,amount_list);

    }

    @RequestMapping("/getAllMedicineToSend")
    public List<Medicine> getAllMedicineToSend(Integer medicalNum, String selectedDate) {
        return medicineInformationService.getAllMedicineToSend(medicalNum,selectedDate);
    }

    @RequestMapping("/findIDByName")
    public Integer findIDByName(String name)
    {
        return medicineInformationService.findMedicineIDByName(name);
    }

    @RequestMapping("/sendOutMedicine")
    public void sendOutMedicine(String informations){
       medicineInformationService.updateSendOut(informations);
    }
}

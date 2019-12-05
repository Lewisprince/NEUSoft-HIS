package com.forwawrd.service.impl;

import com.forwawrd.dao.MedicineInformationDao;
import com.forwawrd.domain.Medicine;
import com.forwawrd.domain.MedicineInformation;
import com.forwawrd.domain.MedicineModel;
import com.forwawrd.domain.ModelDetail;
import com.forwawrd.service.MedicineInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class MedicineInformationServiceImpl implements MedicineInformationService {

    @Autowired
    private MedicineInformationDao medicineInformationDao;

    @Override
    public List<MedicineInformation> getMedicineInformationByMedicalNum(Integer MedicalNumber) {
        return medicineInformationDao.getMedicineInformationByMedicalNum(MedicalNumber);
    }

    @Override
    public List<MedicineModel> getMedicineModel(Integer doctorID) {
        return medicineInformationDao.getMedicineModel(doctorID);
    }

    @Override
    public List<ModelDetail> findModelDetail(String name) {
        Integer ID = medicineInformationDao.findModelIDByName(name);
        return medicineInformationDao.findModelDetail(ID);
    }

    @Override
    public List<String> getAllMedicine() {
        return medicineInformationDao.getAllMedicine();
    }

    @Override
    public void submitPrescription(Integer registrationID, String prescription_name, Integer medicine_number, String medicine_list, String use_method_list, String use_amount_list, String use_frequency_list, String amount_list) {
        medicineInformationDao.submitPrescription(registrationID,prescription_name,medicine_number,medicine_list,use_method_list,use_amount_list,use_frequency_list,amount_list);
    }

    @Override
    public Integer findMedicineIDByName(String name) {
        return medicineInformationDao.findMedicineIDByName(name);
    }

    @Override
    public List<Medicine> getAllMedicineToSend(Integer medicalNum, String selectDate) {
        List<Medicine> tempList = medicineInformationDao.getAllMedicineToSend(medicalNum, selectDate);
        Medicine[] array = tempList.toArray(new Medicine[tempList.size()]);

        String dayFormatModel = "yyyy-MM-dd HH:mm";

        SimpleDateFormat dayFormat = new SimpleDateFormat(dayFormatModel);

        String date = "";

        for(int i = 0 ;i<array.length;i++) {
            Date tempDate = array[i].getTime();
            date = dayFormat.format(tempDate);
            array[i].setFormated_time(date);
        }

        return Arrays.asList(array);

    }

    @Override
    public void updateSendOut(String informations) {
        String[] temp = informations.split(",");
        for(int i=0;i<temp.length;i++)
        {
            medicineInformationDao.updateSendOut(Integer.parseInt(temp[i]));
        }

    }
}

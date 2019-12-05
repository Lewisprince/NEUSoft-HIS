package com.forwawrd.service;

import com.forwawrd.domain.Medicine;
import com.forwawrd.domain.MedicineInformation;
import com.forwawrd.domain.MedicineModel;
import com.forwawrd.domain.ModelDetail;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MedicineInformationService {

    List<MedicineInformation> getMedicineInformationByMedicalNum(Integer MedicalNumber);

    List<MedicineModel> getMedicineModel(Integer doctorID);

    List<ModelDetail> findModelDetail(String name);

    List<String> getAllMedicine();

    void submitPrescription(@Param("registrationID") Integer registrationID, @Param("prescription_name") String prescription_name,
                      @Param("medicine_number") Integer medicine_number, @Param("medicine_list") String medicine_list,
                      @Param("use_method_list") String use_method_list, @Param("use_amount_list") String use_amount_list,
                      @Param("use_frequency_list") String use_frequency_list, @Param("amount_list") String amount_list);

    Integer findMedicineIDByName(String name);

    List<Medicine> getAllMedicineToSend(Integer medicalNum, String selectDate);

    public void updateSendOut(String informations);
}

package com.forwawrd.dao;

import com.forwawrd.domain.Medicine;
import com.forwawrd.domain.MedicineInformation;
import com.forwawrd.domain.MedicineModel;
import com.forwawrd.domain.ModelDetail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface MedicineInformationDao {

    @Select("select m.name,unit_price,amount,write_time from (prescription p join prescription_detail pd on p.ID=pd.prescriptionID) join medicine m on pd.medicineID = m.ID  where p.medical_history_number = #{MedicalNumber} and pd.statement = 1;")
    List<MedicineInformation> getMedicineInformationByMedicalNum(Integer MedicalNumber);

    @Select("select name, use_range from prescription_model where (doctor = #{doctorID} and use_range = 1) or use_range = 3 or ((select office from outpatient_doctor where ID = #{doctorID}) = (select office from outpatient_doctor where ID = doctor) and use_range = 2);")
    List<MedicineModel> getMedicineModel(Integer doctorID);

    @Select("select m.name, use_method, use_amount, use_frequency, amount from prescription_model_detail p join medicine m on p.medicineID = m.ID\n" +
            "where p.modelID = #{modelID};")
    List<ModelDetail> findModelDetail(Integer modelID);

    @Select("select ID from prescription_model where name = #{name}")
    Integer findModelIDByName(String name);

    @Select("select name from medicine limit 500")
    List<String> getAllMedicine();


    @Select( "call GiveMedicine(#{registrationID,mode=IN,jdbcType=INTEGER}," +
            "#{prescription_name,mode=IN,jdbcType=VARCHAR}," +
            "#{medicine_number,mode=IN,jdbcType=INTEGER}," +
            "#{medicine_list,mode=IN,jdbcType=VARCHAR}," +
            "#{use_method_list,mode=IN,jdbcType=VARCHAR}," +
            "#{use_amount_list,mode=IN,jdbcType=VARCHAR}," +
            "#{use_frequency_list,mode=IN,jdbcType=VARCHAR}," +
            "#{amount_list,mode=IN,jdbcType=VARCHAR})")
    void submitPrescription(@Param("registrationID") Integer registrationID, @Param("prescription_name") String prescription_name,
                      @Param("medicine_number") Integer medicine_number, @Param("medicine_list") String medicine_list,
                      @Param("use_method_list") String use_method_list, @Param("use_amount_list") String use_amount_list,
                      @Param("use_frequency_list") String use_frequency_list, @Param("amount_list") String amount_list);

    @Select("Select ID from medicine where name = #{name} limit 1")
    Integer findMedicineIDByName(String name);

    @Select("SELECT pd.ID, m.name, m.unit_price, pd.amount, pd.statement, d.realname as doctor_name, p.name as prescription_name, p.write_time as time\n" +
            "FROM prescription_detail pd join medicine m on pd.medicineID = m.ID join prescription p on p.ID = pd.prescriptionID join outpatient_doctor d on p.doctor = d.ID \n" +
            "where p.medical_history_number = #{medicalNum} and date(p.write_time) between #{selectDate} and #{selectDate};")
    List<Medicine> getAllMedicineToSend(@Param("medicalNum") Integer medicalNum, @Param("selectDate") String selectDate);

    @Update("update prescription_detail set statement = 3 where ID = #{ID};")
    void updateSendOut(Integer ID);
}

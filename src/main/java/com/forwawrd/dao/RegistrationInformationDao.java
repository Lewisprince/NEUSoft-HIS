package com.forwawrd.dao;

import com.forwawrd.domain.RegistrationInformation;
import com.forwawrd.domain.patient;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface RegistrationInformationDao {
    /**
     *  根据病历号查询
     */
    @Select("select * from registration_information where medical_history_number = #{medicalNumber} limit 1")
    RegistrationInformation findByMedicalNum(Integer medicalNumber);

    @Select("select * from registration_information where medical_history_number = #{medicalNumber} and date(registration_time) between #{currentDate} and #{currentDate};")
    List<RegistrationInformation> findTodayByMedicalNum(@Param("medicalNumber") Integer medicalNumber, @Param("currentDate") String currentDate);

    @Select("select medical_history_number,name,ID from registration_information where registration_doctor = #{doctorID} " +
            "and statement = 1 and date(registration_time) between #{currentDate} and #{currentDate};")
    List<patient> getWaitingList(@Param("doctorID") Integer doctorID, @Param("currentDate") String currentDate);

    @Select("select medical_history_number,name,ID from registration_information where registration_doctor = #{doctorID} " +
            "and statement = 2 and date(registration_time) between #{currentDate} and #{currentDate};")
    List<patient> getFinishedList(@Param("doctorID") Integer doctorID, @Param("currentDate") String currentDate);

    @Select( "call DoctorSeePatient(#{registrationID_in,mode=IN,jdbcType=INTEGER}," +
            "#{main_problem_in,mode=IN,jdbcType=VARCHAR}," +
            "#{current_disease_condition_in,mode=IN,jdbcType=VARCHAR}," +
            "#{current_disease_treatment_in,mode=IN,jdbcType=VARCHAR}," +
            "#{disease_history_in,mode=IN,jdbcType=VARCHAR}," +
            "#{allergy_history_in,mode=IN,jdbcType=VARCHAR}," +
            "#{physical_examination_in,mode=IN,jdbcType=VARCHAR}," +
            "#{advise_in,mode=IN,jdbcType=VARCHAR}," +
            "#{notice_in,mode=IN,jdbcType=VARCHAR}," +
            "#{East_West,mode=IN,jdbcType=INTEGER}," +
            "#{disease_numbers,mode=IN,jdbcType=INTEGER}," +
            "#{diseaseID_in,mode=IN,jdbcType=VARCHAR}," +
            "#{disease_time_in,mode=IN,jdbcType=VARCHAR}," +
            "#{statement_in,mode=IN,jdbcType=INTEGER})")
    void DoctorSeePatient(@Param("registrationID_in") String registrationID, @Param("main_problem_in") String main_problem,
                      @Param("current_disease_condition_in") String current_disease_condition, @Param("current_disease_treatment_in") String current_disease_treatment,
                      @Param("disease_history_in") String disease_history, @Param("allergy_history_in") String allergy_history,
                      @Param("physical_examination_in") String physical_examination,@Param("advise_in") String advise,
                      @Param("notice_in") String notice, @Param("East_West") Integer East_West,
                      @Param("disease_numbers") Integer disease_numbers, @Param("diseaseID_in") String diseaseID,
                      @Param("disease_time_in") String disease_time, @Param("statement_in") Integer statement);

    @Select("select ID from disease where name = #{Name}")
    Integer findDiseaseIDByName(String Name);

}

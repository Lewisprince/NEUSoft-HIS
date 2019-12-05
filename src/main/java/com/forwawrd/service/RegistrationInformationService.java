package com.forwawrd.service;

import com.forwawrd.domain.RegistrationInformation;
import com.forwawrd.domain.patient;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface RegistrationInformationService {
    /**
     *  根据病历号查询
     */
    RegistrationInformation findByMedicalNum(Integer medicalNumber);

    List<patient> getWaitingList(Integer doctorID, String currentDate);

    List<patient> getFinishedList(Integer doctorID, String currentDate);

    void DoctorSeePatient(@Param("registrationID_in") String registrationID, @Param("main_problem_in") String main_problem,
                          @Param("current_disease_condition_in") String current_disease_condition, @Param("current_disease_treatment_in") String current_disease_treatment,
                          @Param("disease_history_in") String disease_history, @Param("allergy_history_in") String allergy_history,
                          @Param("physical_examination_in") String physical_examination, @Param("advise_in") String advise,
                          @Param("notice_in") String notice, @Param("East_West") Integer East_West,
                          @Param("disease_numbers") Integer disease_numbers, @Param("diseaseID_in") String diseaseID,
                          @Param("disease_time_in") String disease_time, @Param("statement_in") Integer statement);

    Integer findDiseaseIDByName(String Name);

    List<RegistrationInformation> findTodayByMedicalNum(@Param("medicalNumber") Integer medicalNumber, @Param("currentDate") String currentDate);
}

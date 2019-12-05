package com.forwawrd.service.impl;

import com.forwawrd.dao.RegistrationInformationDao;
import com.forwawrd.dao.UserDao;
import com.forwawrd.domain.RegistrationInformation;
import com.forwawrd.domain.patient;
import com.forwawrd.service.RegistrationInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class RegistrationInformationServiceImpl implements RegistrationInformationService {

    @Autowired
    private RegistrationInformationDao registrationInformationDao;
    @Autowired
    private UserDao userDao;

    @Override
    public RegistrationInformation findByMedicalNum(Integer medicalNumber) {
        return registrationInformationDao.findByMedicalNum(medicalNumber);
    }

    @Override
    public List<patient> getWaitingList(Integer doctorID, String currentDate) {
        return registrationInformationDao.getWaitingList(doctorID,currentDate);
    }

    @Override
    public List<patient> getFinishedList(Integer doctorID, String currentDate) {
        return registrationInformationDao.getFinishedList(doctorID,currentDate);
    }

    @Override
    public void DoctorSeePatient(String registrationID, String main_problem, String current_disease_condition, String current_disease_treatment, String disease_history, String allergy_history, String physical_examination, String advise, String notice, Integer East_West, Integer disease_numbers, String diseaseID, String disease_time, Integer statement) {
        registrationInformationDao.DoctorSeePatient(registrationID,main_problem,current_disease_condition,current_disease_treatment,disease_history,allergy_history,physical_examination,advise,notice,East_West,disease_numbers,diseaseID,disease_time,statement);
    }

    @Override
    public Integer findDiseaseIDByName(String Name) {
        return registrationInformationDao.findDiseaseIDByName(Name);
    }


    @Override
    public List<RegistrationInformation> findTodayByMedicalNum(Integer medicalNumber, String currentDate) {
        List<RegistrationInformation> tempList = registrationInformationDao.findTodayByMedicalNum(medicalNumber,currentDate);
        RegistrationInformation[] array = tempList.toArray(new RegistrationInformation[tempList.size()]);

        String dayFormatModel = "yyyy-MM-dd";
        String timeFormatModel = "HH:mm:ss";

        SimpleDateFormat dayFormat = new SimpleDateFormat(dayFormatModel);
        SimpleDateFormat timeFormat = new SimpleDateFormat(timeFormatModel);

        String date = "";
        String time = "";
        for(int i = 0 ;i<array.length;i++) {
            Date tempDate = array[i].getRegistration_time();
            date = dayFormat.format(tempDate);
            time = timeFormat.format(tempDate);
            array[i].setRegistration_onlyDay(date);
            array[i].setRegistration_onlyTime(time);
            array[i].setOffice(userDao.getOfficeNameByID(userDao.findByID(array[i].getRegistration_doctor()).getOffice()));
        }

          return Arrays.asList(array);
    }
}

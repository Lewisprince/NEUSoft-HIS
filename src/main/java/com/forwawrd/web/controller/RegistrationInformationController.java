package com.forwawrd.web.controller;

import com.forwawrd.domain.RegistrationInformation;
import com.forwawrd.domain.patient;
import com.forwawrd.service.RegistrationInformationService;
import com.forwawrd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/registrationInformation")
@ResponseBody
public class RegistrationInformationController {
    @Autowired
    private RegistrationInformationService registrationInformationService;
    @Autowired
    private UserService userService;

    @RequestMapping("/findByMedicalNum")
    public RegistrationInformation findByMedicalNum(Integer medicalNumber) {return registrationInformationService.findByMedicalNum(medicalNumber);}

    @RequestMapping("/getWaitingList")
    public List<patient> getWaitingList(String doctorName)
    {
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        Integer doctorID = userService.findIDByUserName(doctorName);
        return registrationInformationService.getWaitingList(doctorID,f.format(new Date()));
    }

    @RequestMapping("/getFinishedList")
    public List<patient> getFinishedList(String doctorName)
    {
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        Integer doctorID = userService.findIDByUserName(doctorName);
        return registrationInformationService.getFinishedList(doctorID,f.format(new Date()));
    }

    @RequestMapping("/doctorSeePatient")
    public void DoctorSeePatient(String registrationID, String main_problem, String current_disease_condition, String current_disease_treatment, String disease_history, String allergy_history, String physical_examination, String advise, String notice, Integer East_West, Integer disease_numbers, String[] diseaseName, String disease_time, Integer statement) {
        String allDiseases="";
        for(int i=0;i<disease_numbers;i++)
        {
            if(i==0)
                allDiseases = allDiseases + registrationInformationService.findDiseaseIDByName(diseaseName[i]);
            else
                allDiseases = allDiseases + ","+registrationInformationService.findDiseaseIDByName(diseaseName[i]);
        }
        disease_time = disease_time.replaceAll("T"," ");
        String[] temp = disease_time.split("\\.");
        disease_time = temp[0];
        registrationInformationService.DoctorSeePatient(registrationID,main_problem,current_disease_condition,current_disease_treatment,disease_history,allergy_history,physical_examination,advise,notice,East_West,disease_numbers,allDiseases,disease_time,statement);
    }


    @RequestMapping("getAllRegistrationInfoToday")
    public List<RegistrationInformation> findTodayByMedicalNum(Integer medicalNumber) {
        String dayFormatModel = "yyyy-MM-dd";
        SimpleDateFormat dayFormat = new SimpleDateFormat(dayFormatModel);
        String currentDate = dayFormat.format(new Date());
        return registrationInformationService.findTodayByMedicalNum(medicalNumber,currentDate);
    }
}

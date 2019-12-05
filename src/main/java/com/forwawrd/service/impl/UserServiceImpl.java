package com.forwawrd.service.impl;

import com.forwawrd.dao.UserDao;
import com.forwawrd.domain.Patient1;
import com.forwawrd.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.forwawrd.service.UserService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findByID(Integer userID) {
        return userDao.findByID(userID);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public User loginOutpatientDoc(String userName, String password) {
        return userDao.findOutpatientDoctor(userName,password);
    }

    @Override
    public User loginManager(String userName, String password) {
        return userDao.findManager(userName,password);
    }

    @Override
    public  User findByName(String userName)
    {
        return userDao.findByName(userName);
    }

    @Override
    public User findCollectorByName(String userName) {
        return userDao.findCollectorByName(userName);
    }

    @Override
    public Integer findIDByName(String userName) {
        return userDao.findIDByName(userName);
    }

    @Override
    public Patient1 findPatientByMedicalNum(String medicalNum) {
        return userDao.findPatientByMedicalNum(medicalNum);
    }

    @Override
    public Float getTotalMoney(String medicalNum) {
        return userDao.getTotalMoney(medicalNum);
    }

    @Override
    public Integer findIDByUserName(String name) {
        return userDao.findIDByUserName(name);
    }

    @Override
    public Integer getPrescriptionID(String medicalNumber) {
        return userDao.getPrescriptionID(medicalNumber);
    }

    @Override
    public void payMedicine(Integer prescriptionNumber) {
        userDao.payMedicine(prescriptionNumber,1);
    }

    @Override
    public User loginMoneyCollector(String userName, String password) {
        return userDao.findMoneyCollector(userName,password);
    }
    @Override
    public List<String> findAllOffice() {
        return userDao.findAllOffice();
    }

    @Override
    public List<String> findAllDisease() {
        return userDao.findAllDisease();
    }


    @Override
    public List<String> findDoctors(String officeName, String title) {
        return userDao.findDoctors(officeName, title);
    }

    @Override
    public List<String> registration(String ID_card_number, String name, String gender1, String birthday1, Integer age, String doctorName, String home_address, String need_medical_history_booklet1) {

        Integer gender;
        if(gender1.equals("ÄÐ"))
            gender = 1;
        else
            gender = 0;
        Integer doctorID = this.findIDByName(doctorName);
        Integer need_medical_history_booklet;
        if(need_medical_history_booklet1.equals("ÊÇ"))
            need_medical_history_booklet = 1;
        else
            need_medical_history_booklet = 0;


        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = null;
        try{
            birthday = format1.parse(birthday1);}
        catch (ParseException e){}
        if(birthday == null) {
            birthday = new Date();
            birthday.setTime(Long.parseLong(birthday1));
        }
        userDao.registration(ID_card_number,name,gender,birthday,age,"Ëê",doctorID,home_address,need_medical_history_booklet,1);
        List<String> result = new ArrayList<String>();
        Integer medical_history_num = userDao.findLastMedicalNumber();
        Integer registrationID = userDao.findLastRegistrationID();
        Integer invoiceNumber = userDao.findInvoiceNumber(registrationID);
        Float price = userDao.findPrice(invoiceNumber);

        result.add(medical_history_num.toString());
        result.add(registrationID.toString());
        result.add(invoiceNumber.toString());
        result.add(price.toString());

        return result;
    }

    @Override
    public Integer giveBackNum(Integer registrationID) {
        userDao.giveBackNum(registrationID);
        return userDao.findInvoiceNumber(registrationID);
    }
}

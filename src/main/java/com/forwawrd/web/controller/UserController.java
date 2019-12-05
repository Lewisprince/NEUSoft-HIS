package com.forwawrd.web.controller;

import com.forwawrd.domain.Patient1;
import com.forwawrd.domain.User;
import com.forwawrd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
@ResponseBody
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAll")
    public List<User> findAll()
    {
        return userService.findAll();
    }

    @RequestMapping("/findByID")
    public User findByID(Integer userID)
    {
        return userService.findByID(userID);
    }

    @RequestMapping("/update")
    public void updateUser(@RequestBody User user)
    {
        userService.updateUser(user);
    }

    @RequestMapping("/login")
    public User loginOutpatientDoc(@RequestBody Map map)
    {
        Integer kind1 = Integer.valueOf((String)map.get("kind"));
        String userName = (String)map.get("userName");
        String password = (String)map.get("password");
        if(kind1 == 1)
        {
            User user = userService.loginOutpatientDoc(userName,password);
            if(user!=null)
            {
                return user;
            }
        }
        return null;
    }

    @RequestMapping("/managerLogin")
    public User loginManager(@RequestBody Map map)
    {
        Integer kind1 = Integer.valueOf((String)map.get("kind"));
        String userName = (String)map.get("userName");
        String password = (String)map.get("password");
        if(kind1 == 6)
        {
            User user = userService.loginManager(userName,password);
            if(user!=null)
            {
                return user;
            }
        }
        return null;
    }

    @RequestMapping("/moneyCollectorLogin")
    public User loginMoneyCollector(@RequestBody Map map)
    {
        Integer kind1 = Integer.valueOf((String)map.get("kind"));
        String userName = (String)map.get("userName");
        String password = (String)map.get("password");
        if(kind1 == 4)
        {
            User user = userService.loginMoneyCollector(userName,password);
            if(user!=null)
            {
                return user;
            }
        }
        return null;
    }

    @RequestMapping("/findByName")
    public User findByName(String userName){
        return userService.findByName(userName);
    }

    @RequestMapping("/findCollectorByName")
    public User findCollectorByName(String userName){
        return userService.findCollectorByName(userName);
    }

    @RequestMapping("/findByUserName")
    public Integer findByUserName(String name){
        return userService.findIDByUserName(name);
    }

    @RequestMapping("/findAllOffice")
    public List<String> findAllOffice() {
        return userService.findAllOffice();
    }

    @RequestMapping("/findAllDisease")
    public List<String> findAllDisease() {
        return userService.findAllDisease();
    }

    @RequestMapping("/findDoctors")
    public List<String> findDoctors(String officeName, String  title){
        return userService.findDoctors(officeName,title);
    }

    @RequestMapping("/registration")
    public List<String> registration(String ID_card_number, String name, String gender, String birthday, Integer age, String doctorName, String home_address, String need_medical_history_booklet){

        birthday = birthday.replaceAll("T",".");
        String[] temp = birthday.split("\\.");
        birthday = temp[0];
        return userService.registration(ID_card_number,name,gender,birthday,age,doctorName,home_address,need_medical_history_booklet);
    }

    @RequestMapping("/findPatientByMedicalNum")
    public Patient1 findPatientByMedicalNum(String medicalNum){
        return  userService.findPatientByMedicalNum(medicalNum);
    }

    @RequestMapping("/getTotalMoney")
    public Float getTotalMoney(String medicalNum) {
        return userService.getTotalMoney(medicalNum);
    }

    @RequestMapping("/payMedicine")
    void PayMedicine(String medicalNumber)
    {
        userService.payMedicine(userService.getPrescriptionID(medicalNumber));
    }

    @RequestMapping("/givebackNo")
    Integer giveBackNo(Integer registrationID)
    {
        return userService.giveBackNum(registrationID);
    }
}

package com.forwawrd.service;

import com.forwawrd.domain.Patient1;
import com.forwawrd.domain.User;
import org.apache.ibatis.annotations.Param;


import java.util.Date;
import java.util.List;

// 用户的业务层接口
public interface UserService {
    /**
     * 查询用户列表
     * @return
     */
    List<User> findAll();


    /**
     * 根据ID查询
     * @param userID
     * @return
     */
    User findByID (Integer userID);


    /**
     * 更新用户
     * @param user
     */
    void updateUser (User user);


    /**
     * 医生登录
     * @param userName
     * @param password
     * @return
     */
    public User loginOutpatientDoc(@Param("userName") String userName, @Param("password") String password);


    /**
     * 管理员登录
     * @param userName
     * @param password
     * @return
     */
    public User loginManager(@Param("userName") String userName, @Param("password") String password);


    /**
     * 挂号收费员登录
     * @param userName
     * @param password
     * @return
     */
    public User loginMoneyCollector(@Param("userName") String userName, @Param("password") String password);


    /**
     * 根据用户名查询
     * @param userName
     * @return
     */
    public User findByName(String userName);

    public Integer findIDByName(String userName);

    public Integer findIDByUserName(String name);

    User findCollectorByName(String userName);

    Patient1 findPatientByMedicalNum(String medicalNum);

    public List<String> findAllOffice();

    public List<String> findAllDisease();

    List<String> findDoctors(@Param("officeName") String officeName, @Param("title") String title);

    List<String> registration(@Param("ID_card_number") String ID_card_number, @Param("name") String name,
                              @Param("gender") String gender1, @Param("birthday") String birthday1,
                              @Param("age") Integer age,
                              @Param("doctorID") String doctorName,@Param("home_address") String home_address,
                              @Param("need_medical_history_booklet") String need_medical_history_booklet1);

    Float getTotalMoney(String medicalNum);

    Integer getPrescriptionID(String medicalNumber);

    void payMedicine(Integer prescriptionNumber);

    Integer giveBackNum(Integer registrationID);
}

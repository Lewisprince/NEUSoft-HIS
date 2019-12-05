package com.forwawrd.dao;

import com.forwawrd.domain.Patient1;
import com.forwawrd.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

// 用户的持久层接口
public interface UserDao {

    /**
     * 查询用户列表
     * @return
     */
    @Select("select * from outpatient_doctor")
    List<User> findAll();


    /**
     * 根据ID查询
     * @param userID
     * @return
     */
    @Select("select * from outpatient_doctor where id = #{userID}")
    User findByID (Integer userID);


    /**
     * 更新用户
     * @param user
     */
    @Select("update outpatient_doctor set user_name = #{user_name}, password = #{password}, realname = #{realname}," +
            " office = #{office}, title = #{title}, registration_level = #{registration_level} where ID = #{ID}")
    void updateUser (User user);

    @Select("select * from outpatient_doctor where user_name = #{userName} and password = #{password}")
    User findOutpatientDoctor(@Param("userName") String userName, @Param("password") String password);


    @Select("select * from hospital_manager where user_name = #{userName} and password = #{password}")
    User findManager(@Param("userName") String userName, @Param("password") String password);

    @Select("select * from money_collector where user_name = #{userName} and password = #{password}")
    User findMoneyCollector(@Param("userName") String userName, @Param("password") String password);

    @Select("select * from outpatient_doctor where user_name = #{userName}")
    User findByName(String userName);

    @Select("select * from money_collector where user_name = #{userName}")
    User findCollectorByName(String userName);

    @Select("select ID from outpatient_doctor where realname = #{realname}")
    Integer findIDByName(String realname);

    @Select("select ID from outpatient_doctor where user_name = #{name}")
    Integer findIDByUserName(String name);

    @Select("select name from office where office_kind = 1")
    List<String> findAllOffice();

    @Select("select name from disease limit 1000")
    List<String> findAllDisease();

    @Select("select realname from outpatient_doctor where office = (select ID from office where name = #{officeName}) and title = #{title}")
    List<String> findDoctors(@Param("officeName") String officeName, @Param("title") String title);


    @Select("select name,ID_card_number,home_address from registration_information where medical_history_number = #{medicalNum} limit 1;")
    Patient1 findPatientByMedicalNum(String medicalNum);

    @Select( "call Registration(#{ID_card_number,mode=IN,jdbcType=VARCHAR}," +
            "#{name,mode=IN,jdbcType=VARCHAR}," +
            "#{gender,mode=IN,jdbcType=INTEGER}," +
            "#{birthday,mode=IN,jdbcType=DATE}," +
            "#{age,mode=IN,jdbcType=INTEGER}," +
            "#{age_type,mode=IN,jdbcType=VARCHAR}," +
            "#{home_address,mode=IN,jdbcType=VARCHAR}," +
            "#{doctorID,mode=IN,jdbcType=INTEGER}," +
            "#{need_medical_history_booklet,mode=IN,jdbcType=INTEGER}," +
            "#{registor,mode=IN,jdbcType=INTEGER})")
    void registration(@Param("ID_card_number") String ID_card_number, @Param("name") String name,
                              @Param("gender") Integer gender, @Param("birthday") Date birthday,
                              @Param("age") Integer age, @Param("age_type") String age_type,
                              @Param("doctorID") Integer doctorID,@Param("home_address") String home_address,
                              @Param("need_medical_history_booklet") Integer need_medical_history_booklet,
                              @Param("registor") Integer registor);

    @Select("select medical_history_number from registration_information where ID=(select max(ID) from registration_information)")
    Integer findLastMedicalNumber();

    @Select("select max(ID) from registration_information")
    Integer findLastRegistrationID();

    @Select("select max(invoice_number) from invoice where registrationID = #{RegistrationID}")
    Integer findInvoiceNumber(Integer RegistrationID);

    @Select("select money_amount from invoice where invoice_number = #{invoiceNumber}")
    Float findPrice(Integer invoiceNumber);

    @Select("select sum(unit_price*amount) from (prescription p join prescription_detail pd on p.ID=pd.prescriptionID) join medicine m on pd.medicineID = m.ID where p.ID = (select max(ID) from prescription p1 where p1.medical_history_number = #{medicalNumber} and statement = 1);")
    Float getTotalMoney(String medicalNum);

    @Select("select max(ID) from prescription p1 where p1.medical_history_number = #{medicalNumber} and statement = 1")
    Integer getPrescriptionID(String medicalNumber);

    @Select("call PayMedicine(#{prescriptionNumber,mode=IN,jdbcType=INTEGER}, #{registrator,mode=IN,jdbcType=INTEGER});")
    List<Integer> payMedicine(@Param("prescriptionNumber") Integer prescriptionNumber, @Param("registrator")Integer registrator);

    @Select("select name from office where ID = #{officeID}")
    String getOfficeNameByID(Integer officeID);

    @Select("call GiveBackNo(#{registrationID,mode=IN,jdbcType=INTEGER});")
    void giveBackNum(Integer registrationID);
}

package test;

import com.forwawrd.domain.MedicineInformation;
import com.forwawrd.domain.MedicineModel;
import com.forwawrd.domain.ModelDetail;
import com.forwawrd.service.MedicineInformationService;
import com.forwawrd.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MedicineInformationTest {

    @Autowired
    private MedicineInformationService medicineInformationService;
    @Autowired
    private  UserService userService;

    @Test
    public void testGetMedicineInfo()
    {
        List<MedicineInformation> tempList = new ArrayList<>();
        tempList = medicineInformationService.getMedicineInformationByMedicalNum(600620);

        System.out.println(tempList);

    }

    @Test
    public void testGetMedicineModel()
    {
        List<MedicineModel> tempList = new ArrayList<>();
        tempList = medicineInformationService.getMedicineModel(4);
        System.out.println(tempList);
    }


    @Test
    public void findDetail()
    {
        List<ModelDetail> tempList = new ArrayList<>();
        tempList = medicineInformationService.findModelDetail("ÄîÖé¾úÐÔÆ¤Ñ×");
        System.out.println(tempList);
    }

    @Test
    public void TestSubmitPrescription()
    {
        medicineInformationService.submitPrescription(214,"testSubmit",2,"22,33","123,123","123,234","234,345","2,3");
    }

    @Test
    public void TestFindID()
    {
        System.out.println(medicineInformationService.findMedicineIDByName("¾ÆÊ¯Ëá·¥Äá¿ËÀ¼Æ¬£¨³©Åæ£©"));
    }

    @Test
    public void TestGetAllMedicineToSend(){

        System.out.println(medicineInformationService.getAllMedicineToSend(600620,"2019-06-25"));
    }
}

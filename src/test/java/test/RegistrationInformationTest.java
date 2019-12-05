package test;

import com.forwawrd.domain.RegistrationInformation;
import com.forwawrd.domain.patient;
import com.forwawrd.service.RegistrationInformationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class RegistrationInformationTest {


    @Autowired
    private RegistrationInformationService registrationInformationService;

    @Test
    public void testFindByMedicalNum()
    {
        RegistrationInformation registrationInformation = registrationInformationService.findByMedicalNum(600704);
        System.out.println(registrationInformation);
    }

    @Test
    public void testGetWaitingList()
    {
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        List<patient> tempList = registrationInformationService.getWaitingList(1,f.format(new Date()));
        System.out.println(tempList);
    }

    @Test
    public void testFindAllMedicalNum()
    {
        List<RegistrationInformation> tempList;
        tempList = registrationInformationService.findTodayByMedicalNum(600704,"2019-09-16");
        System.out.println(tempList);
    }
}

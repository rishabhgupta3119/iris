package A2.DemoQATest;

import A2.BaseTest.BaseTest;
import org.example.Pages.WebTablePage;
import org.example.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebTablePageTest extends BaseTest {

    @Test
    public void addData() throws InterruptedException {
        WebTablePage tablePage=new WebTablePage(driver);

        tablePage.openUrl(ConfigReader.getProperty("base.url") + "/webtables");
        tablePage.doClickAdd();
        tablePage.addData
                ("Rishabh",
                "Gupta", "test@gmail.com", "30", "20000", "QA");
        tablePage.doClickSubmit();
        //Direct validation (NO extra variable)
        Assert.assertEquals(
                tablePage.getLastAddedFirstName(),
                "Rishabh",
                "First Name not matching, Record Is Not Found, Add Fail");

        System.out.println("First Name validation successful, Record Is Found, Add Pass");

        //click on edit button
        tablePage.editData("30000");
        Assert.assertEquals(tablePage.getLastEditSalary(),
                "2000030000",
                "Updated Salary not matching, Edit Failed");

        System.out.println("Salary Field is Successfully Updated, Edit Pass");
        tablePage.deleteData();
        System.out.println("Data Is Successfully Deleted, Delete Pass");

    }



    }





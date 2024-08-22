import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestCases {
    WebDriver driver1;
@BeforeClass
public void login(){
    driver1= new ChromeDriver();
    driver1.get("https://curious-halva-9294ed.netlify.app");
    driver1.manage().window().maximize();
}
    @Test(priority = 1)
    public void ManualModeCalculatorTestCase1() throws InterruptedException {
        System.out.println("Test Case 01 Results.....................");
        CalculatorHomePage caclObj1 = new CalculatorHomePage(driver1);
        String startN="A";
        String endN="F";
        caclObj1.startDropdownSelect(startN);
        caclObj1.endDropdownSelect(endN);
        caclObj1.clickCalcButtonManual();
        String appValue=caclObj1.findCalcValueManual();
        Thread.sleep(5000);
        DijikstraCalculator dijcal1=new DijikstraCalculator(startN,endN);
        String dijkstraValue= dijcal1.calcDistance();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(appValue, dijkstraValue);
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void ManualModeClearNodesTestCase2() throws InterruptedException {
        System.out.println("Test Case 02 Results.....................");
        CalculatorHomePage caclObj2 = new CalculatorHomePage(driver1);
        caclObj2.clickClearButton();
        Thread.sleep(2000);
        }

        @Test(priority = 3)
    public void RandomModeTestCase3() throws InterruptedException {
            System.out.println("Test Case 03 Results.....................");
        CalculatorHomePage caclObj = new CalculatorHomePage(driver1);
        caclObj.enableRandomMode();
        String startN;
        String endN;
            SoftAssert softAssert1 = new SoftAssert();
        for (int i = 3; i <= 4; i++) {
            System.out.println("Test Case :" + i);
            startN=caclObj.findStartNode();
            endN=caclObj.findEndNode();
            caclObj.clickClacButton();
            String appCalValue=caclObj.findCalcValue();
            DijikstraCalculator dijcalObj=new DijikstraCalculator(startN,endN);
            String dijVal= DijikstraCalculator.calcDistance();
            softAssert1.assertEquals(appCalValue, dijVal);
            softAssert1.assertAll();
            caclObj.clickRefreshButton();
        }

    }
@AfterClass
public void tearDown()
{
    driver1.quit();
}
}

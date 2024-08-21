import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.sql.Driver;
import java.util.List;

public class CalculatorHomePage {

   public String startNodeName;
    public String destinationNodeName;

    private WebDriver driver;

    public CalculatorHomePage(WebDriver driver) {
        this.driver=driver;
    }
//Locations in Manual Mode
    private By startDropDwnParent=By.xpath("//div[@id='fromNode']");
    private By startDropDwnChild=By.xpath("//div[@id='fromNode']//div[contains(text(),'A')]");

    private By endDropDwnParent=By.xpath("//div[@id='toNode']");
    private By endDropDwnChild=By.xpath("//div[@id='toNode']//div[contains(text(),'F')]");


    private By calcbuttonManual=By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[1]/div[3]/button[2]");
    private By clearButtonManual=By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[1]/div[3]/button[1]");

    private By findCalcValueManualApp= By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[2]/div/div/p[2]");

    private By dataClearedImg=By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[2]/div/img");


    //Locations in RandomMode
    private By toggleLoc= By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[1]/div/label/div");
    private By startNodeLoc=By.xpath("//*[@id=\"fromNode\"]");
    private By destNodeLoc=By.xpath("//*[@id=\"toNode\"]/div/div[1]");
    private By calcbuttonLoc=By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[1]/div[3]/button");
    private By calcPathLoc=By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[2]/div/div/p[2]");
    private By refreshButtonLoc=By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[1]/div/button/img");

    //WebElement toggleSwitch = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[1]/div/label/div"));
    //WebElement startNode = driver.findElement(By.xpath("//*[@id=\"fromNode\"]"));
    //WebElement destinationNode = driver.findElement(By.xpath("//*[@id=\"toNode\"]/div/div[1]"));
    //WebElement calcbutton= driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[1]/div[3]/button"));
    //WebElement calcPath = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[2]/div/div/div[2]/div/div/p[2]"));
    //WebElement refreshButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div/div[1]/div/button/img"));

       //Enable Toggle
    public void enableRandomMode() throws InterruptedException {
        Thread.sleep(3000);
        driver.findElement(toggleLoc).click();
    }

    //Find the start node in Random Mode
    public String findStartNode() throws InterruptedException {
        Thread.sleep(3000);
        startNodeName = driver.findElement(startNodeLoc).getText();
        return startNodeName;
    }

    //Find the Destination node in Random Mode
    public String findEndNode() throws InterruptedException {
        Thread.sleep(3000);
        destinationNodeName = driver.findElement(destNodeLoc).getText();
        return destinationNodeName;
    }

    //Click Calculate button in Random Mode
    public void clickClacButton() throws InterruptedException {
        driver.findElement(calcbuttonLoc).click();
        Thread.sleep(5000);

    }
    //Find the Calculate value in Random Mode
    public String findCalcValue() throws InterruptedException {
        String distanceTotal = driver.findElement(calcPathLoc).getText();
        String[] total = distanceTotal.split(":", 2); // Limit to 2 parts
        System.out.println("Distance From source Node " + startNodeName + " to " + destinationNodeName + " from application calc:" + total[1]);
        return total[1];
    }
 // click on Refresh button in Random Mode
    public void clickRefreshButton() {
        driver.findElement(refreshButtonLoc).click();

    }

    //Find & select the start node in Manual Mode
    public void startDropdownSelect(String startNode) throws InterruptedException {
        startNodeName=startNode;
        Thread.sleep(3000);
       // WebElement startDropdownElement=driver.findElement(startNodeDropdown);
        //Select startDropdown = new Select(startDropdownElement);
        //startDropdown.selectByVisibleText(startNodeName);
       // return startNodeName;

        WebElement fromNodeDropdown = driver.findElement(startDropDwnParent);
        fromNodeDropdown.click(); // Open the dropdown

        // Locate the option "A" and click it
        WebElement optionA = driver.findElement(startDropDwnChild);
        optionA.click(); // Select option "A"
        }



    //Find & select the end node in Manual Mode
    public void endDropdownSelect(String endnode) throws InterruptedException {
        destinationNodeName= endnode;
        Thread.sleep(3000);
       // WebElement endDropdownElement=driver.findElement(endNodeDropdown);
        //Select endDropdown = new Select(endDropdownElement);
        //endDropdown.selectByVisibleText(endNodeName);
       // return endNodeName;
        // Locate and open the "To node" dropdown
        WebElement toNodeDropdown =driver.findElement(endDropDwnParent);
        toNodeDropdown.click(); // Open the dropdown

        // Wait for the options to be visible and then select "F"
        WebElement optionF = driver.findElement(endDropDwnChild);
        optionF.click(); // Select option "F"

    }
    // click on calc button in Manual Mode
    public void clickCalcButtonManual() throws InterruptedException {
        driver.findElement(calcbuttonManual).click();
        Thread.sleep(5000);
    }

    // click on clear butn in Manual Mode
    public void clickClearButton() throws InterruptedException {
        driver.findElement(clearButtonManual).click();
        WebElement dataClearedImage=driver.findElement(dataClearedImg);
        if(dataClearedImage.isDisplayed())
        {
            System.out.println("Data is cleared succesfully");
    }}
    public String findCalcValueManual() throws InterruptedException {
        Thread.sleep(3000);
        String manualDistanceTotal = driver.findElement(findCalcValueManualApp).getText();
        String[] totalManual = manualDistanceTotal.split(": ", 2); // Limit to 2 parts
        System.out.println("Distance From source Node " + startNodeName + " to " + destinationNodeName + " from application calc in Manual Mode :" + totalManual[1]);
        return totalManual[1];
    }

}

package miksrok.testing.pages;

import miksrok.testing.util.CustomReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Залізний Мозок on 01.06.2017.
 */
public class MainPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private String country;


    private By dropDownMenu = By.xpath("//div[@class='menu_header_dropdown header_lang_dd lang-drop-down']");

    private By textField = By.id("availbox_search_dest_0");
    private By cityBlock = By.id("ui-id-3");

    //private By city = By.xpath("//ul[@id='ui-id-3']/li[2]");
    //private By city = By.linkText("Rome, Italy");
    private By firstDate = By.id("d_from0_0");
    private By secondDate = By.id("d_to0_0");
    private By room = By.xpath("//div[@data-show='hotels']/div");


    private By roomQty = By.xpath("//div[@class='cell mrm']");
    private By roomOne = By.xpath("//div[@id='select2-drop']/ul/li[1]");
    private By roomTwo = By.xpath("//div[@id='select2-drop']/ul/li[2]");

    private By adults = By.xpath("//div[@data-room='1']/div[2]/div/a");
    private By adultsSecondRoom = By.xpath("//div[@data-room='2']/div[2]/div/a");
    private By adultsNum1 = By.xpath("//div[@id='select2-drop']/ul/li[1]");
   // private By adultsNum2 = By.xpath("//div[@id='select2-drop']/ul/li[2]");

    private By firstRoomChildren = By.xpath("//div[@data-room='1']/div[3]/div/a");
    private By secondRoomChildren = By.xpath("//div[@data-room='2']/div[3]/div/a");
    private By firstRoomChildrenQty = By.xpath("//div[@id='select2-drop']/ul/li[2]");
    private By twoChildren = By.xpath("//div[@id='select2-drop']/ul/li[3]");

    private By childrenAge = By.xpath("//div[@data-room='1']/div[4]/div/div/a");
    private By secondChildAge = By.xpath("//div[@data-room='2']/div[4]/div[2]/div/a");
    private By childrenAgeSecondRoom = By.xpath("//div[@data-room='2']/div[4]/div/div/a");
    private By childrenAgeValue = By.xpath("//div[@id='select2-drop']/ul/li[2]");

    private By okButton = By.xpath("//button[@class='btn xmed-btn blue-g-btn js-ok']");


    public MainPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    public void setCountryCode(String country){
        this.country = country;
    }

    public void openMainPage() {

        driver.get("http://www.booked.net/");

    }

    public void emptyField(){
        makeScreenShot("placeholder" + country);
        CustomReporter.log("<br><img src='screens/placeholder"+country+".png' width='388' height='216'/>");
        CustomReporter.log("<br><p>Field status: 1</p>");
        CustomReporter.log("<br><p>Expected result:</p>");
        CustomReporter.log("<br><p>There is text under mask in the text field</p>");
    }

    public void fieldWithThreeLeters(){
        enterCityName("rom");
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.cityBlock));
        makeScreenShot("dropDown"+country);
        CustomReporter.log("<br><img src='screens/dropDown"+country+".png' width='388' height='216'/>");
        CustomReporter.log("<br><p>Field status: 2</p>");
        CustomReporter.log("<br><p>Expected result:</p>");
        CustomReporter.log("<br><p>Dropdown menu is visible</p>");
    }

    public void fieldWithFullCityName(String cit){
        //wait.until(ExpectedConditions.visibilityOfElementLocated(this.cityBlock));
        enterCityName("rome");
        WebElement city = driver.findElement(By.linkText(cit));
        city.click();
        makeScreenShot("fullName"+country);
        CustomReporter.log("<br><img src='screens/fullName"+country+".png' width='388' height='216'/>");
        CustomReporter.log("<br><p>Field status: 3</p>");
        CustomReporter.log("<br><p>Expected result:</p>");
        CustomReporter.log("<br><p>There is city name in text field</p>");
    }

    private void enterCityName(String value) {
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.textField));
        WebElement textField = driver.findElement(this.textField);
        textField.sendKeys(value);
    }

   public void enterDateOneDay(){
       Calendar cal = Calendar.getInstance();
       cal.add(Calendar.DAY_OF_YEAR, 1);
       String firstDate = calendarToString(cal, "yyyy-MM-dd");
       cal.add(Calendar.DAY_OF_YEAR, 1);
       String secondDate = calendarToString(cal, "yyyy-MM-dd");
       enterDate(firstDate, secondDate);
       makeScreenShot("oneDay"+country);
       CustomReporter.log("<br><img src='screens/oneDay"+country+".png' width='388' height='216'/>");
       CustomReporter.log("<br><p>Field status: 1</p>");
       CustomReporter.log("<br><p>Expected result:</p>");
       CustomReporter.log("<br><p>Tomorrow's date and the day after tomorrow </p>");

   }

   public void enterDateMonth(){
       Calendar cal = Calendar.getInstance();
       String firstDate = calendarToString(cal, "yyyy-MM-dd");
       cal.add(Calendar.DAY_OF_YEAR, 30);
       String secondDate = calendarToString(cal, "yyyy-MM-dd");
       enterDate(firstDate, secondDate);
       makeScreenShot("month"+country);
       CustomReporter.log("<br><img src='screens/month"+country+".png' width='388' height='216'/>");
       CustomReporter.log("<br><p>Field status: 2</p>");
   }

    private void enterDate(String first, String second){

        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.firstDate));
        WebElement firstDate = driver.findElement(this.firstDate);
        WebElement secondDate = driver.findElement(this.secondDate);
        firstDate.sendKeys(first);
        firstDate.sendKeys(Keys.ENTER);
        for (int i = 0; i<10; i++){
            secondDate.sendKeys(Keys.BACK_SPACE);
        }
        secondDate.sendKeys(second);
        secondDate.sendKeys(Keys.ENTER);
    }

    public void addRoom(){

        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.room));
        WebElement room = driver.findElement(this.room);
        room.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.adults));
        WebElement roomQty = driver.findElement(this.roomQty);
        roomQty.click();
        WebElement roomOne = driver.findElement(this.roomOne);
        roomOne.click();
        WebElement adult = driver.findElement(this.adults);
        adult.click();
        WebElement adultsNum1 = driver.findElement(this.adultsNum1);
        adultsNum1.click();
        WebElement firstRoomChildren = driver.findElement(this.firstRoomChildren);
        firstRoomChildren.click();
        WebElement tmp = driver.findElement(this.firstRoomChildrenQty);
        tmp.click();
        WebElement childrenAge = driver.findElement(this.childrenAge);
        childrenAge.click();
        WebElement childrenAgeValue = driver.findElement(this.childrenAgeValue);
        childrenAgeValue.click();
        WebElement okButton = driver.findElement(this.okButton);
        okButton.click();
        makeScreenShot("oneRoom"+country);
        CustomReporter.log("<br><img src='screens/oneRoom"+country+".png' width='388' height='216'/>");
        CustomReporter.log("<br><p>Field status: 1</p>");
        CustomReporter.log("<br><p>Expected result:</p>");
        CustomReporter.log("<br><p>selected: 1 room, 1 adult, 1 child </p>");
    }

    public void addSecondRoom() {

        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.room));
        WebElement room = driver.findElement(this.room);
        room.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.adults));
        WebElement roomQty = driver.findElement(this.roomQty);
        roomQty.click();
        WebElement roomTwo = driver.findElement(this.roomTwo);
        roomTwo.click();

        WebElement adult = driver.findElement(this.adults);
        adult.click();
        WebElement adultsNum1 = driver.findElement(this.adultsNum1);
        adultsNum1.click();
        WebElement firstRoomChildren = driver.findElement(this.firstRoomChildren);
        firstRoomChildren.click();
        WebElement tmp = driver.findElement(this.firstRoomChildrenQty);
        tmp.click();
        WebElement childrenAge = driver.findElement(this.childrenAge);
        childrenAge.click();
        WebElement childrenAgeValue = driver.findElement(this.childrenAgeValue);
        childrenAgeValue.click();

        WebElement adultsSecondRoom = driver.findElement(this.adultsSecondRoom);
        adultsSecondRoom.click();
        WebElement adultsNum2 = driver.findElement(this.adultsNum1);
        adultsNum2.click();
        WebElement secondRoomChildren = driver.findElement(this.secondRoomChildren);
        secondRoomChildren.click();
        WebElement twoChildren = driver.findElement(this.twoChildren);
        twoChildren.click();
        WebElement firstChildSecondRoom = driver.findElement(this.childrenAgeSecondRoom);
        firstChildSecondRoom.click();
        childrenAgeValue = driver.findElement(this.childrenAgeValue);
        childrenAgeValue.click();
        WebElement seconChildSecondRoom = driver.findElement(this.secondChildAge);
        seconChildSecondRoom.click();
        childrenAgeValue = driver.findElement(this.childrenAgeValue);
        childrenAgeValue.click();

        WebElement okButton = driver.findElement(this.okButton);
        okButton.click();
        makeScreenShot("twoRooms"+country);
        CustomReporter.log("<br><img src='screens/twoRooms"+country+".png' width='388' height='216' />");
        CustomReporter.log("<br><p>Field status: 2</p>");
        CustomReporter.log("<br><p>Expected result:</p>");
        CustomReporter.log("<br><p>selected: 2 room, 2 adults, 3 children </p>");
    }

    public void country(String code){

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='booked_menu booked_header_user_nav']/li[3]")));
        Actions action = new Actions(driver);
        WebElement d = driver.findElement(By.xpath("//ul[@class='booked_menu booked_header_user_nav']/li[3]"));
        action.moveToElement(d).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.dropDownMenu));
        WebElement country = driver.findElement(By.xpath("//span[@data-code='"+code+"']"));
        action.moveToElement(country).click().build().perform();

    }

    public void makeScreenShot(String name){
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File("target\\surefire-reports\\screens\\"+name+".png"));
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static String calendarToString(Calendar calendar, String dateFormat){
        if (calendar == null || dateFormat == null) {
            return null;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(calendar.getTime());
    }

}

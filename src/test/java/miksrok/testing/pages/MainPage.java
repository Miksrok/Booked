package miksrok.testing.pages;

import miksrok.testing.util.CustomReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Залізний Мозок on 01.06.2017.
 */
public class MainPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By textField = By.id("availbox_search_dest_0");
    private By cityBlock = By.id("ui-id-3");
    private By city = By.xpath("//ul[@id='ui-id-3']/li[2]");
    private By firstDate = By.id("d_from0_0");
    private By room = By.xpath("//div[@data-show='hotels']/div");
    //private By adults = By.id("s2id_autogen23");
    private By adults = By.xpath("//div[@data-room='1']/div[2]/div/a");
    private By adultsNum1 = By.xpath("//div[@id='select2-drop']/ul/li[1]");

    private By firstRoomChildren = By.xpath("//div[@data-room='1']/div[3]/div/a");
    private By firstRoomChildrenQty = By.xpath("//div[@id='select2-drop']/ul/li[2]");

    private By childrenAge = By.xpath("//div[@data-room='1']/div[4]/div/div/a");
    private By childrenAgeValue = By.xpath("//div[@id='select2-drop']/ul/li[2]");


    public MainPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 30);
    }

    public void openMainPage() {

        driver.get("http://www.booked.net/");

    }

    public void enterCityName() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.textField));
        WebElement textField = driver.findElement(this.textField);
        makeScreenShot("first");
        textField.sendKeys("Rom");
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.cityBlock));
        makeScreenShot("second");
        textField.sendKeys("a");
        WebElement city = driver.findElement(this.city);
        city.click();
        makeScreenShot("third");



    }

    public void enterDate(){

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        String date = calendarToString(cal, "yyyy-MM-dd");

        WebElement firstDate = driver.findElement(this.firstDate);
        firstDate.sendKeys(date);
        firstDate.sendKeys(Keys.ENTER);
        makeScreenShot("4th");

        //driver.navigate().refresh();

    }

    public void addRoom(){

        //wait.until(ExpectedConditions.visibilityOfElementLocated(this.room));
        WebElement room = driver.findElement(this.room);
        room.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(this.adults));
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

    }

    public void makeScreenShot(String name){
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            // now copy the  screenshot to desired location using copyFile //method
            FileUtils.copyFile(src, new File("D://reports/"+ name + ".png"));
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
//===============
    public static String calendarToString(Calendar calendar, String dateFormat){
        if (calendar == null || dateFormat == null) {
            return null;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static Calendar stringToCalendar(String dateString, String dateFormat){
        if (dateString == null || dateFormat == null) {
            return null;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

        try {
            simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            return null;
        }

        return simpleDateFormat.getCalendar();
    }
}

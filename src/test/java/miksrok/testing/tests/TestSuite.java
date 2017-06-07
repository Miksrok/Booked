package miksrok.testing.tests;

import miksrok.testing.BaseScript;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Залізний Мозок on 01.06.2017.
 */
public class TestSuite extends BaseScript {

        @Test(priority = 2)
        public void emptyFieldTest(){
            mainPage.emptyField();
        }
        @Test(priority = 3)
        public void fieldWithThreeLetersTest(){
                mainPage.fieldWithThreeLeters();
        }
        @Parameters("city")
        @Test(priority = 4)
        public void fieldWithFullCityNameTest(String city){
                mainPage.fieldWithFullCityName(city);
        }
        @Test(priority = 5)
        public void enterDateOneDayTest(){
            mainPage.enterDateOneDay();
        }
        @Test(priority = 6)
        public void enterDateMonthTest(){
                mainPage.enterDateMonth();
        }
        @Test(priority = 7)
        public void addRoomTest(){
                mainPage.addRoom();
        }
        @Test(priority = 8)
        public void addSecondRoomTest(){
                mainPage.addSecondRoom();
        }





}

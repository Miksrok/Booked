package miksrok.testing.tests;

import miksrok.testing.BaseScript;
import miksrok.testing.pages.MainPage;
import org.testng.annotations.Test;

/**
 * Created by Залізний Мозок on 01.06.2017.
 */
public class TestSuite extends BaseScript {

        //private MainPage mainPage;

        @Test
        public void test(){

            mainPage.openMainPage();
            mainPage.enterCityName();
            mainPage.enterDate();
            mainPage.addRoom();

        }

}

package miksrok.testing.util;

import org.testng.Reporter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Залізний Мозок on 01.06.2017.
 */
public class CustomReporter {

    private CustomReporter() {
    }

    public static void logAction(String message) {
        Reporter.log(String.format("[%-12s] ACTION: %s", LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME), message));
    }

    public static void log(String message) {
        Reporter.log(String.format("[%-12s] %s", LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME), message));
    }

}

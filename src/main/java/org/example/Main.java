import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class Main {
    static Properties prop = new Properties();
    public static AndroidDriver<AndroidElement> driver;

    public static void main(String[] args) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Redmi Note 7 Pro");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");
        capabilities.setCapability(MobileCapabilityType.UDID, "a6dfccca");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "180000");
        capabilities.setCapability("appPackage", "com.cogenteservices.cogent_ems");
        capabilities.setCapability("appActivity", "com.cogenteservices.cogent_ems.MainActivity");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");

        try {
            URL url = new URL("http://127.0.0.1:4723/");
            driver = new AndroidDriver<>(url, capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            propertiesFile("loginID");
        } catch (IOException e) {
            e.printStackTrace();
        }

        loginScreen();
        
    }

    static Properties propertiesFile(String key) throws IOException {
        FileInputStream file = new FileInputStream("src/test/java/config/test.properties");
        prop.load(file);
        return prop;
    }

    public static void loginScreen() {
        WebElement loginScreen = driver.findElement(By.xpath("//android.widget.Button[@content-desc='Login Screen']"));
        loginScreen.click();
        WebElement login = driver.findElement(By.xpath("//android.widget.EditText[@hint='Employee ID']"));
        login.click();
        login.sendKeys(prop.getProperty("username"));
        driver.hideKeyboard();

        WebElement password = driver.findElement(By.xpath("//android.widget.EditText[@hint='Password']"));
        password.click();
        password.sendKeys(prop.getProperty("password"));

        WebElement submit = driver.findElement(By.xpath("//android.widget.Button[@content-desc='Sign in']"));
        submit.click();

    }
}

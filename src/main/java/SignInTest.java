import com.sun.jna.Platform;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest {

    WebDriver driver = new ChromeDriver();

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

        setDriverPath();

        driver.get("https://www.cleartrip.com/");
        waitFor(2000);

        driver.findElement(By.linkText("Your trips")).click();
        driver.findElement(By.id("SignIn")).click();
        waitFor(4000);
        
        //This signin-popup is in iframe so we need to switch to iframe
        driver.switchTo().frame("modal_window");
        
        driver.findElement(By.id("signInButton")).click();
        
        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        driver.quit();
    }

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private void setDriverPath() {
        if (Platform.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (Platform.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (Platform.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }


}

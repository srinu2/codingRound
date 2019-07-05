import com.sun.jna.Platform;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class HotelBookingTest {

    WebDriver driver = new ChromeDriver();

    //'Hotels' was repeated so many times in the source(Duplication)
    @FindBy(xpath = "//aside/nav/ul/li[2]/a")
    public WebElement hotelLink;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

 /*   This happens because of the order in which member variables are initialized and constructors are called.

    You are calling from the constructor of the superclass.

    When this method is called, it will set member. But after that, the subclass part of the object initialization is performed, and the initializer for member is executed, which sets member to null.*/


    
    @Test
    public void shouldBeAbleToSearchForHotels() throws Throwable {
        setDriverPath();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.get("https://www.cleartrip.com/");
        
        //System.out.println(this.getHotelLink());
        
        WebDriverWait wait = new WebDriverWait(driver,20);
        
        Thread.sleep(6000);
        
        WebElement hotels = driver.findElement(By.xpath("//aside/nav/ul/li[2]/a"));
        
        wait.until(ExpectedConditions.visibilityOf(hotels));
        
        hotels.click();
        
        Thread.sleep(4000);

        WebElement localityTextBox = driver.findElement(By.xpath("//span/input"));
        
        localityTextBox.sendKeys("Indiranagar, Bangalore");

        WebElement travellerSelection = driver.findElement(By.id("travellersOnhome"));
        
        new Select(travellerSelection).selectByVisibleText("1 room, 2 adults");
        
        WebElement searchButton = driver.findElement(By.id("SearchHotelsButton"));
        
        searchButton.click();

        driver.quit();

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

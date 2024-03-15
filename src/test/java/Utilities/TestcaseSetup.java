package Utilities;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestcaseSetup {
    public static WebDriver driver;
    @BeforeEach
    public void setup() {
        String dir = System.getProperty("user.dir");
//        Depending on runtime variable, launches different browser for cross browser testing
        switch(System.getProperty("browser")) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", dir+"\\src\\test\\resources\\webdrivers\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", dir+"\\src\\test\\resources\\webdrivers\\geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "msedge":
                System.setProperty("webdriver.edge.driver", dir+"\\src\\test\\resources\\webdrivers\\msedgedriver.exe");
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + System.getProperty("browser"));
        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://todomvc.com/examples/react/dist/");
    }

    @AfterEach
    public void teardown() {
        driver.close();
    }
}
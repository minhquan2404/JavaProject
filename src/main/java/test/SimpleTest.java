package test;

import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class SimpleTest {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @Test
    public void searchTest() {
        // Đợi đến khi ô tìm kiếm xuất hiện và nhập từ khóa "viblo"
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='q']")));
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys("viblo");
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys(Keys.ENTER);

        // Đợi trang kết quả tải và kiểm tra tiêu đề có chứa từ "viblo"
        wait.until(ExpectedConditions.titleContains("viblo"));
        Assert.assertTrue(driver.getTitle().toLowerCase().contains("viblo"));
    }

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        // Khởi tạo WebDriverWait với thời gian chờ 10 giây, sử dụng Duration trong Selenium 4
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.google.com/");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

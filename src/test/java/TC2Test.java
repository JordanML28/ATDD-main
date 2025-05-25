import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;
import org.testng.annotations.*;

public class TC2Test{
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void verificarInicioGoogle() {
        driver.get("https://www.google.com");
        WebElement boton = driver.findElement(By.name("btnK"));
        Assert.assertEquals(boton.getAttribute("value"), "Buscar con Google");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}


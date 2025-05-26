import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import io.github.bonigarcia.wdm.WebDriverManager;
public class TC3Test{
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

   @Test
    public void testFormularioDashboard() throws InterruptedException {
        // Paso 1: Acceder a la página principal
        driver.get("http://localhost:5173/");

        // Paso 2: Realizar login válido
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("1234");
        driver.findElement(By.id("login-button")).click();

        // Paso 3: Esperar a que redireccione
        Thread.sleep(1000);
        assertTrue(driver.getCurrentUrl().contains("/dashboard"));

        // Paso 4: Llenar el formulario
        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement ageInput = driver.findElement(By.id("age"));
        WebElement phoneInput = driver.findElement(By.id("phone"));

        nameInput.sendKeys("Juan Pérez");
        ageInput.sendKeys("30");
        phoneInput.sendKeys("123456789");

        // Paso 5: Verificar que los campos contienen los datos
        assertEquals(nameInput.getAttribute("value"), "Juan Pérez");
        assertEquals(ageInput.getAttribute("value"), "30");
        assertEquals(phoneInput.getAttribute("value"), "123456789");

        // Paso 6: Enviar el formulario
        driver.findElement(By.id("submit-btn")).click();
        Thread.sleep(500);

        // Paso 7: Verificar salida mostrada
        WebElement output = driver.findElement(By.id("output"));
        assertTrue(output.isDisplayed());

        // Paso 8: Validar contenido de la salida
        String outputText = output.getText();
        assertTrue(outputText.contains("Juan Pérez"));
        assertTrue(outputText.contains("30"));
        assertTrue(outputText.contains("123456789"));
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}


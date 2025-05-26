import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC2Test {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void testLoginFailure() throws InterruptedException {
        // Paso 1: Ir a la página de inicio
        driver.get("http://localhost:5173/");

        // Paso 2: Capturar los elementos de entrada
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));

        // Paso 3: Ingresar datos inválidos
        username.sendKeys("usuarioIncorrecto");
        password.sendKeys("claveMala");

        // Paso 4: Verificar que los valores se ingresaron
        assertEquals(username.getAttribute("value"), "usuarioIncorrecto");
        assertEquals(password.getAttribute("value"), "claveMala");

        // Paso 5: Hacer clic en el botón de login
        WebElement loginBtn = driver.findElement(By.id("login-button"));
        loginBtn.click();

        // Paso 6: Esperar respuesta
        Thread.sleep(1000);

        // Paso 7: Verificar que no se redirige
        assertTrue(driver.getCurrentUrl().contains("/"));

        // Paso 8: Verificar mensaje de error
        WebElement errorMsg = driver.findElement(By.id("error-msg"));
        assertTrue(errorMsg.isDisplayed());
        assertEquals(errorMsg.getText(), "Credenciales incorrectas");

        // Paso 9: Validar que aún se puede ver el formulario de login
        assertTrue(driver.findElement(By.id("login-button")).isDisplayed());
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

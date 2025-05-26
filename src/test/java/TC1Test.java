import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC1Test {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        // Configura ChromeDriver automáticamente
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void testLoginSuccess() throws InterruptedException {
        // Paso 1: Navegar a la página principal
        driver.get("http://localhost:5173/");

        // Paso 2: Verificar que el título de la página sea el esperado
        assertEquals(driver.getTitle(), "Vue App");

        // Paso 3: Obtener los campos de usuario y contraseña
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));

        // Paso 4: Ingresar el usuario y la contraseña válidos
        username.sendKeys("admin");
        password.sendKeys("1234");

        // Paso 5: Verificar que los valores fueron ingresados correctamente
        assertEquals(username.getAttribute("value"), "admin");
        assertEquals(password.getAttribute("value"), "1234");

        // Paso 6: Hacer clic en el botón de login
        WebElement loginBtn = driver.findElement(By.id("login-button"));
        loginBtn.click();

        // Paso 7: Esperar que se redireccione
        Thread.sleep(1000);

        // Paso 8: Verificar que se redirige al dashboard
        assertTrue(driver.getCurrentUrl().contains("/dashboard"));

        // Paso 9: Verificar contenido de bienvenida en el dashboard
        WebElement heading = driver.findElement(By.tagName("h1"));
        assertEquals(heading.getText(), "Dashboard");

        // Paso 10: Validar existencia del formulario
        assertTrue(driver.findElement(By.id("name")).isDisplayed());
    }

    @AfterTest
    public void tearDown() {
        driver.quit(); // Cierra el navegador al finalizar
    }
}

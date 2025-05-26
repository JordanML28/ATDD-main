import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

/****************************************
 * Historia de Usuario:
 * Como usuario registrado,
 * quiero iniciar sesión en la aplicación web local,
 * para acceder al panel principal (dashboard).
 *
 * Prueba de Aceptación:
 * Verificar que el sistema permita el inicio de sesión con credenciales válidas,
 * redirigiendo correctamente al dashboard con los elementos visibles.
 *
 * Pasos:
 * 1. Ingresar a la aplicación web: http://localhost:5173/
 * 2. Verificar que el título sea "login"
 * 3. Ingresar nombre de usuario: "admin"
 * 4. Ingresar contraseña: "adminpass"
 * 5. Hacer clic en el botón de login
 * 6. Verificar redirección a la URL que contiene "/dashboard"
 * 7. Verificar que el encabezado diga "Dashboard"
 * 8. Verificar que el formulario de ingreso de datos esté visible
 *
 * Resultado Esperado:
 * El usuario debe ser redirigido correctamente al dashboard, 
 * y debe visualizar el título "Dashboard" y el formulario con ID "name".
 ****************************************/

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
        Thread.sleep(2000);

        // Paso 2: Verificar que el título de la página sea el esperado
        assertEquals(driver.getTitle(), "login");

        // Paso 3: Obtener los campos de usuario y contraseña
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));

        // Paso 4: Ingresar el usuario y la contraseña válidos
        username.sendKeys("admin");
        Thread.sleep(2000);

        password.sendKeys("adminpass");
        Thread.sleep(2000);
        // Paso 5: Verificar que los valores fueron ingresados correctamente
        assertEquals(username.getAttribute("value"), "admin");
        assertEquals(password.getAttribute("value"), "adminpass");

        // Paso 6: Hacer clic en el botón de login
        WebElement loginBtn = driver.findElement(By.id("login-button"));
        loginBtn.click();

        // Paso 7: Esperar que se redireccione
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Paso 8: Verificar que se redirige al dashboard
        
        wait.until(ExpectedConditions.urlContains("/dashboard"));
        assertTrue(driver.getCurrentUrl().contains("/dashboard"));

        // Paso 9: Verificar contenido de bienvenida en el dashboard
        WebElement heading = driver.findElement(By.tagName("h1"));
        assertEquals(heading.getText(), "Dashboard");

        // Paso 10: Validar existencia del formulario
        assertTrue(driver.findElement(By.id("name")).isDisplayed());
        Thread.sleep(4000);
    }

    @AfterTest
    public void tearDown() {
        driver.quit(); // Cierra el navegador al finalizar
    }
}

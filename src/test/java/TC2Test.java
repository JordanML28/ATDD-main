import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import io.github.bonigarcia.wdm.WebDriverManager;

/****************************************
 * Historia de Usuario:
 * Como usuario no registrado,
 * quiero recibir un mensaje de error al intentar iniciar sesión con credenciales incorrectas,
 * para saber que debo corregir mis datos de acceso.
 *
 * Prueba de Aceptación:
 * Verificar que el sistema muestre un mensaje de error
 * y no redirija al dashboard cuando se ingresan credenciales inválidas.
 *
 * Pasos:
 * 1. Ingresar a la aplicación web: http://localhost:5173/
 * 2. Verificar que el título sea "login"
 * 3. Ingresar nombre de usuario: "usuarioIncorrecto"
 * 4. Ingresar contraseña: "claveMala"
 * 5. Hacer clic en el botón de login
 * 6. Verificar que la URL no cambia (no redirige al dashboard)
 * 7. Verificar que se muestra el mensaje "Credenciales incorrectas"
 * 8. Verificar que el botón de login sigue visible
 *
 * Resultado Esperado:
 * El sistema debe permanecer en la página de login,
 * mostrar un mensaje de error visible con el texto "Credenciales incorrectas",
 * y mantener el botón de login accesible.
 ****************************************/

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
        Thread.sleep(2000);

        // Paso 2: Verificar que el título de la página sea el esperado
        assertEquals(driver.getTitle(), "login");

        // Paso 3: Capturar los elementos de entrada
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));

        // Paso 4: Ingresar datos inválidos
        username.sendKeys("usuarioIncorrecto");
        Thread.sleep(2000);

        password.sendKeys("claveMala");
         Thread.sleep(2000);

        // Paso 5: Verificar que los valores se ingresaron
        assertEquals(username.getAttribute("value"), "usuarioIncorrecto");
        assertEquals(password.getAttribute("value"), "claveMala");

        // Paso 6: Hacer clic en el botón de login
        WebElement loginBtn = driver.findElement(By.id("login-button"));
        loginBtn.click();

        // Paso 7: Esperar respuesta
        Thread.sleep(2000);

        // Paso 8: Verificar que no se redirige (la URL debe seguir siendo la de login)
        // Aquí asumimos que la URL sigue conteniendo "/" y no cambia a "/dashboard"
        assertTrue(driver.getCurrentUrl().endsWith("/"));

        // Paso 9: Verificar mensaje de error
        // Asegúrate que en tu Vue el mensaje de error tenga id="error-msg"
        WebElement errorMsg = driver.findElement(By.id("error-msg"));
        assertTrue(errorMsg.isDisplayed());
        assertEquals(errorMsg.getText(), "Credenciales incorrectas");

        // Paso 10: Validar que aún se puede ver el botón de login
        assertTrue(driver.findElement(By.id("login-button")).isDisplayed());
        Thread.sleep(4000);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

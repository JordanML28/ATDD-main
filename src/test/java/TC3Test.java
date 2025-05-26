import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import io.github.bonigarcia.wdm.WebDriverManager;

/****************************************
 * Historia de Usuario:
 * Como usuario autenticado,
 * quiero llenar un formulario en el dashboard,
 * para registrar mis datos personales en la plataforma.
 *
 * Prueba de Aceptación:
 * Verificar que, luego de iniciar sesión se pueda ingresar información en el formulario
 * y visualizar los datos ingresados como salida en pantalla.
 *
 * Pasos:
 * 1. Ingresar a la aplicación web: http://localhost:5173/
 * 2. Realizar login válido con usuario: "admin" y contraseña: "adminpass"
 * 3. Verificar redirección a "/dashboard"
 * 4. Llenar el formulario con nombre, edad y teléfono
 * 5. Verificar que los campos contienen los datos correctos
 * 6. Hacer clic en el botón de enviar
 * 7. Verificar que se muestra el bloque de salida
 * 8. Verificar que la salida contiene los datos ingresados
 *
 * Resultado Esperado:
 * El formulario debe aceptar los datos ingresados y mostrarlos correctamente
 * en un bloque de salida visible con nombre, edad y teléfono.
 ****************************************/

public class TC3Test {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void testFormularioDashboard() throws InterruptedException {
        // Paso 1: Acceder a la página principal
        driver.get("http://localhost:5173/");
        Thread.sleep(2000);

        // Paso 2: Realizar login válido
        driver.findElement(By.id("username")).sendKeys("admin");
        Thread.sleep(1000);
        driver.findElement(By.id("password")).sendKeys("adminpass");
        Thread.sleep(1000);
        driver.findElement(By.id("login-button")).click();

        // Paso 3: Esperar a que redireccione
        Thread.sleep(2000);
        assertTrue(driver.getCurrentUrl().contains("/dashboard"));
        Thread.sleep(1000);

        // Paso 4: Llenar el formulario
        WebElement nameInput = driver.findElement(By.id("name"));
        WebElement ageInput = driver.findElement(By.id("edad"));     
        WebElement phoneInput = driver.findElement(By.id("telefono"));  

        nameInput.sendKeys("Juan Perez");
        Thread.sleep(1000);
        ageInput.sendKeys("30");
        Thread.sleep(1000);
        phoneInput.sendKeys("79561255");
        Thread.sleep(1000);

        // Paso 5: Verificar que los campos contienen los datos
        assertEquals(nameInput.getAttribute("value"), "Juan Perez");
        assertEquals(ageInput.getAttribute("value"), "30");
        assertEquals(phoneInput.getAttribute("value"), "79561255");

        // Paso 6: Enviar el formulario
        WebElement submitBtn = driver.findElement(By.id("submit-btn"));  
        submitBtn.click();
        Thread.sleep(1000);

        // Paso 7: Verificar salida mostrada
        WebElement output = driver.findElement(By.id("output")); 
        assertTrue(output.isDisplayed());

        // Paso 8: Validar contenido de la salida
        String outputText = output.getText();
        assertTrue(outputText.contains("Juan Perez"));
        assertTrue(outputText.contains("30"));
        assertTrue(outputText.contains("79561255"));
        Thread.sleep(4000);
    }


    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

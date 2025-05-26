# ATDD con Selenium y TestNG

Este proyecto es una implementación de **Desarrollo Guiado por Pruebas de Aceptación (ATDD)** usando **Selenium**, **TestNG** y **Maven**. Incluye una aplicación web simple desarrollada en Vue 3 y un conjunto de pruebas automatizadas que validan su funcionalidad.

## 📦 Estructura del proyecto
ATDD-main/
├── src/
│ ├── main/ # Aplicación (Vue 3)
│ └── test/
│ └── java/
│ └── tests/
│ ├── TC1Test.java
│ ├── TC2Test.java
│ └── TC3Test.java
├── testng.xml # Suite general de pruebas
├── pom.xml # Configuración de Maven
## 🚀 Requisitos

- Java 17 o superior
- Maven 3.8+
- Google Chrome
- ChromeDriver compatible con tu versión de Chrome

## 🧪 Ejecutar pruebas
```bash
mvn -Dtest=tests.TC1Test test
```
```bash
mvn -Dtest=tests.TC2Test test
```
```bash
mvn -Dtest=tests.TC3Test test
```
### Ejecutar todas las pruebas
```bash
mvn clean test
```

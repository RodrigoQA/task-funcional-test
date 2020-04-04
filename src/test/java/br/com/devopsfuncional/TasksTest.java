package br.com.devopsfuncional;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TasksTest {

    public WebDriver setup() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to("http://localhost:8001/tasks/");
        return driver;
    }


    @Test
    public void criarTasksComSucesso() throws InterruptedException {
        WebDriver driver = setup();
        try {
            driver.findElement(By.id("addTodo")).click();
            driver.findElement(By.id("task")).sendKeys("Funcional");
            driver.findElement(By.id("dueDate")).sendKeys("10/02/2033");
            driver.findElement(By.id("saveButton")).click();
            String msg = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("success", msg);
        } finally {
            driver.close();
        }
    }


    @Test
    public void naoDeveCriarTasksComDataPassada() throws InterruptedException {
        WebDriver driver = setup();
        try {
            driver.findElement(By.id("addTodo")).click();
            driver.findElement(By.id("task")).sendKeys("Funcional");
            driver.findElement(By.id("dueDate")).sendKeys("10/02/2013");
            driver.findElement(By.id("saveButton")).click();
            String msg = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Due date must not be in past", msg);
        } finally {
            driver.close();
        }
    }

    @Test
    public void naoDeveCriarTasksSemDescricao() throws InterruptedException {
        WebDriver driver = setup();
        try {
            driver.findElement(By.id("addTodo")).click();
            // driver.findElement(By.id("task")).sendKeys("Funcional");
            driver.findElement(By.id("dueDate")).sendKeys("10/02/2023");
            driver.findElement(By.id("saveButton")).click();
            String msg = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the task description", msg);
        } finally {
            driver.close();
        }
    }

    @Test
    public void naoDeveCriarTasksSemData() {
        WebDriver driver = setup();
        try {
            driver.findElement(By.id("addTodo")).click();
            driver.findElement(By.id("task")).sendKeys("Funcional");
            //driver.findElement(By.id("dueDate")).sendKeys("10/02/2023");
            driver.findElement(By.id("saveButton")).click();
            String msg = driver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the due date", msg);
        } finally {
            driver.close();
        }
    }
}


package com.qa.qasampletest;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class App {
    public static void main(String[] args) {
        String fromLocation = "Coimbatore", toLocation = "Cochin";
        String fromDate = "Dec 2023", toDate = "Dec 2023";
        int fromD = 27, toD = 30;

        ChromeDriver driver = new ChromeDriver();
        driver.get("https://in.via.com/");
        driver.manage().window().maximize();

        try {
            driver.findElement(By.xpath("//button[@id='wzrk-cancel']")).click();
        } catch (Exception exception) {
            System.out.println("Exception caught: " + exception);
        }

        WebElement navElement = waitForEnableCondition(driver, By.xpath("//div[@id='Bus']/a[@class='product  ']"));
        Home.selectNavBus(driver, navElement);

        try {
            Thread.sleep(2000);
            WebElement fromElement = waitForEnableCondition(driver, By.xpath("//input[@id='src']"));
            Home.selectFromLoc(driver, fromElement, fromLocation);
        } catch (InterruptedException exception) {
            System.out.println("Exception: " + exception);
        }

        try {
            Thread.sleep(2000);
            WebElement toElement = waitForEnableCondition(driver, By.xpath("//input[@id='dest']"));
            Home.selectToLoc(driver, toElement, toLocation);
        } catch (InterruptedException exception) {
            System.out.println("Exception: " + exception);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            System.out.println("Exception: " + exception);
        }

        WebElement fromDateEle = waitForEnableCondition(driver, By.xpath("//input[@id='departure']"));

        try {
            Home.selectFromDate(driver, fromDateEle, fromDate, fromD);
        } catch (InterruptedException exception) {
            System.out.println("Exception: " + exception);
        }

        // Uncomment the following block if needed
        /*
        try {
            Thread.sleep(1000);
            WebElement toDateEle = waitForEnableCondition(driver, By.xpath("//input[@id='return']"));
            Home.selectFromDate(driver, toDateEle, toDate, toD);
        } catch (InterruptedException exception) {
            System.out.println("Exception: " + exception);
        }
        */

        try {
            Home.submitCall(driver);
        } catch (InterruptedException exception) {
            System.out.println("Exception: " + exception);
        }

        try {
            Thread.sleep(40000);
            Search.getList(driver);
            Thread.sleep(2000);
            Search.seatView1(driver);
            Thread.sleep(2000);
            Search.getSeatNo(driver);
        } catch (Exception exception) {
            System.out.println("---- " + exception);
        }

        driver.quit();
    }

    public static WebElement waitForEnableCondition(WebDriver driver, By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = null;

        try {
            element = wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(by)));
        } catch (StaleElementReferenceException e) {
            element = wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(by)));
        }

        if (element != null) {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }

        return element;
    }
}

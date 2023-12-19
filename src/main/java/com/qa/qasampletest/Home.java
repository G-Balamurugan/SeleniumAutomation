package com.qa.qasampletest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Home {
    public static void selectNavBus(ChromeDriver driver , WebElement navElement){
        navElement.click();
    }
    public static void selectFromLoc(ChromeDriver driver , WebElement fromElement , String fromLocation) throws InterruptedException{
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", fromElement);
        fromElement.sendKeys(fromLocation);
        Thread.sleep(1000);
        WebElement element = driver.findElement(By.xpath("//ul[@id='ui-id-1']/li"));
        Thread.sleep(1000);
        element.click();
    }
    public static void selectToLoc(ChromeDriver driver , WebElement toElement , String toLocation) throws InterruptedException{
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", toElement);
        toElement.sendKeys(toLocation);
        Thread.sleep(1000);
        WebElement element = driver.findElement(By.xpath("//ul[@id='ui-id-2']/li"));
        Thread.sleep(1000);
        element.click();

    }
    public static void selectFromDate(ChromeDriver driver , WebElement fromDateEle ,String monthYear , int fromD ) throws InterruptedException{
        fromDateEle.click();

        WebElement next = driver.findElement(By.xpath("//span[@class='vc-month-box-head-cell vc-month-controls icon-Rightarrowthin vc-month-control-active js-next-month'][@style='text-align: right;']"));
//        Home.waitForEnableCondition(driver , next);

        fromDateEle = driver.findElement(By.xpath("//span[@class='vc-month-box-head-cell ']"));
        System.out.println(fromDateEle.getText());
/*
        while (!fromDateEle.getText().equals(monthYear)){
            fromDateEle = driver.findElement(By.xpath("//span[@class='vc-month-box-head-cell ']"));
            Thread.sleep(2000);
            next.click();
        }*/
        WebElement fromDa = driver.findElement(By.xpath("//div[@class='vc-month-box']//div[@class='vc-row']/div[@class='vc-cell '][@data-date='" + fromD + "']"));
        Thread.sleep(2000);
        fromDa.click();
    }
    public static void submitCall(ChromeDriver driver) throws InterruptedException{
        WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit'][@class='search-btn search-journey']"));
        Thread.sleep(2000);
        submitButton.click();
    }
}

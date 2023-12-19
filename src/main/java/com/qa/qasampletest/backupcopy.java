package com.qa.qasampletest;

public class backupcopy {
    /*
    package com.qa.qasampletest;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.makemytrip.com/");          // Initialize the url

        WebDriverWait w = new WebDriverWait(driver ,  Duration.ofSeconds(20));

        driver.manage().window().maximize();

        try{
            Thread.sleep(3000);
            WebElement element = driver.findElement(By.id("webklipper-publisher-widget-container-notification-frame"));
            Home.handlePopUp(driver , element);

            List<WebElement> listOfNames = driver.findElements(By.xpath("//span[@class='headerIconTextAlignment chNavText darkGreyText']"));
            Home.selectCategory(driver , listOfNames);

            Thread.sleep(2000);

            Thread.sleep(1000);


            String fromMon = "January2024" , toMon = "January2024";
            String fromDate = "21" , toDate = "25";
            Search.chkMonth(fromMon , fromDate , driver);
            Search.chkMonth(toMon , toDate , driver);


            try {
                driver.findElement(By.xpath("//button[@class='primaryBtn btnApplyNew pushRight capText']")).click();
                //button[@class='primaryBtn btnApplyNew pushRight capText']
                Thread.sleep(1000);
            }catch (Exception c)
            {
                System.out.println("Not clicked");
            }
            driver.findElement(By.xpath("//button[@class='primaryBtn font24 latoBold widgetSearchBtn']")).click();
            //button[@class='primaryBtn font24 latoBold widgetSearchBtn']
            Thread.sleep(1000);

//            driver.findElement(By.xpath("//div[@class='priceBucketFilter']/ul/li/span/input")).click();
//            Thread.sleep(1000);


            WebElement element1 = driver.findElement((By.xpath("//div[@class='priceBucketFilter']/ul/li/span/input")));
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element1);
            Thread.sleep(4000);

            //WebElement name = driver.findElement(By.xpath("//div[@class='infinite-scroll-component ']/div[@class='listingRowOuter hotelTileDt makeRelative ']/a/div/div/div/div[@class='flexOne appendLeft20']/div[@class='makeFlex spaceBetween']/div/p/span"));
            //div[@class='infinite-scroll-component ']/div[@class='listingRowOuter hotelTileDt makeRelative ']/a/div/div/div/div[@class='flexOne appendLeft20']/div[@class='makeFlex spaceBetween']/div/p/span

            List<WebElement> name = driver.findElements(By.xpath("//span[starts-with(@id, 'htl_id_seo_')]"));
            List<WebElement> price = driver.findElements(By.xpath("//p[starts-with(@id, 'hlistpg_hotel_shown_price')]"));
            List<WebElement> rating = driver.findElements(By.xpath("//span[starts-with(@itemprop,\"ratingValue\")]"));
            //p[starts-with(@id, 'hlistpg_hotel_shown_price')]

            int count = 0;
            for(WebElement webElement : rating) {
                if(Float.parseFloat(webElement.getText()) > 4){
                    count = rating.indexOf(webElement);
                    System.out.println("Rating : " + webElement.getText());
                    break;
                }
            }

            int temp = 0;
            for(WebElement webElement : price) {
               if(temp++ == count)
                System.out.println("Price : " + webElement.getText());
            }

            temp = 0;
            for(WebElement webElement : name) {
                if(temp++ == count)
                    System.out.println("Name : " + webElement.getText());
            }
            Thread.sleep(3000);

        }catch (AssertionError catched)
        {
            System.out.println("---> " + catched);
            System.out.println("Hello All");
        }
        catch (InterruptedException catchExc)
        {
            System.out.println(catchExc);
        }

        driver.quit();

    }


}

package com.qa.qasampletest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Home {
    public static void handlePopUp(ChromeDriver driver , WebElement element){
        driver.switchTo().frame(element);
        WebElement element1 = driver.findElement((By.xpath("//a[@class = 'close']")));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element1);

        driver.switchTo().defaultContent();

    }

    public static void selectCategory(ChromeDriver driver , List<WebElement> listOfNames){
        for(WebElement webElement : listOfNames){
            String text = webElement.getText();
            if(text.equals("Hotels")){
                webElement.click();
            }
        }

        driver.findElement(By.id("city")).click();

    }


}
package com.qa.qasampletest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Search {
    public static void setName(ChromeDriver driver){
        try{
        WebElement nameElement = driver.findElement(By.xpath("//input[@id='city']"));
        nameElement.click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[@class=\"hw__searchInputWrapper\"]/input")).sendKeys("Bengaluru");
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[@class='hw__recentSearchTextWrapper']")).click();
//            nameElement.sendKeys("Bengaluru");
           }catch (Exception c){
            System.out.println(c);
        }
    }
    public static void chkMonth (String monthYear , String date, ChromeDriver driver) {
        try{
            Thread.sleep(1000);
            WebElement monthEle = driver.findElement(By.xpath("//div[@role=\"heading\"]/div"));
            WebElement next = driver.findElement(By.xpath("//span[@aria-label=\"Next Month\"]"));
            while(!monthEle.getText().equals(monthYear)) {
                Thread.sleep(1000);
                next.click();
            }

            if(!next.isDisplayed())

                Thread.sleep(1000);

            List<WebElement> days = driver.findElements(By.xpath("//div[@class=\"DayPicker-Body\"]/div[@class=\"DayPicker-Week\"]/div[@class=\"DayPicker-Day\"]"));

            for (WebElement ele: days ) {
                if(ele.getText().equals(date) && ele.getAttribute("aria-disabled").equals("false")) {
                    ele.click();
                    break;
                }
            }
        }catch (InterruptedException c){
            System.out.println(c);
        }
    }

}






public static void getList(ChromeDriver driver) throws InterruptedException{
        List<String> name = new ArrayList<>();
        List<WebElement> list = driver.findElements(By.xpath("//section[@class='flex direction-column justify-start']/h6[@class='u_txtEllipsis u_padB10 u_font16 u_fontW600 u_cl333 u_widthAbs250']"));
        Thread.sleep(40000);
        int count = 0;
        System.out.println("Good ..."+list);
        for(WebElement webElement : list){
            System.out.println("11.");
            String text = webElement.getText();
            System.out.println(text);
            name.add(text);
            count++;
            if(count == 10)
                break;
        }
        System.out.println("Good ...");
        List<String> price = new ArrayList<>();
        List<WebElement> priceList = driver.findElements(By.xpath("//section[@class='flex direction-column justify-start']/p[@class='u_clViaRed u_fontW600 u_font20 u_marB0']"));
        Thread.sleep(2000);
        count = 0;
        System.out.println("Good ...");
        for(WebElement webElement : priceList){
            price.add(webElement.getText());
            count++;
            if(count == 10)
                break;
        }

        System.out.println("Name : " + name.get(0) + "\n" + "Price : " + price.get(0) );

        WebElement sub = driver.findElement(By.xpath("//button[@class='CTA-red h-s-btn']"));
        Thread.sleep(2000);
        sub.click();
    }
    public static void getList1(ChromeDriver driver) throws InterruptedException {
        List<String> name = new ArrayList<>();
        List<String> price = new ArrayList<>();

        int count = 0;

        do {
            List<WebElement> list = driver.findElements(By.xpath("//section[@class='flex direction-column justify-start']/h6[@class='u_txtEllipsis u_padB10 u_font16 u_fontW600 u_cl333 u_widthAbs250']"));

            for (WebElement webElement : list) {
                String text = webElement.getText();
                name.add(text);
                count++;
                if (count == 10)
                    break;
            }

            List<WebElement> priceList = driver.findElements(By.xpath("//section[@class='flex direction-column justify-start']/p[@class='u_clViaRed u_fontW600 u_font20 u_marB0']"));

            count = 0;
            for (WebElement webElement : priceList) {
                price.add(webElement.getText());
                count++;
                if (count == 10)
                    break;
            }

            // If the lists are not populated as expected, refresh the page or take appropriate action
            if (name.size() < 10 || price.size() < 10) {
                driver.navigate().refresh();
                Thread.sleep(5000); // Adjust sleep time as needed
            }

        } while (name.size() < 10 || price.size() < 10);

        System.out.println("Name : " + name.get(0) + "\n" + "Price : " + price.get(0));

        WebElement sub = driver.findElement(By.xpath("//button[@class='CTA-red h-s-btn']"));
        Thread.sleep(2000);
        sub.click();
    }    */
}

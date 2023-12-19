package com.qa.qasampletest;

import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Search {
    public static void getList(ChromeDriver driver) throws InterruptedException {
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

            priceList = driver.findElements(By.xpath("//section[@class='flex direction-column justify-start']/p[@class='u_clViaRed u_fontW600 u_font20 u_marB0']"));

            count = 0;
            for (WebElement webElement : priceList) {
                price.add(webElement.getText());
                count++;
                if (count == 10)
                    break;
            }

            if (name.size() < 10 || price.size() < 10) {
                driver.navigate().refresh();
                Thread.sleep(5000);
            }

        } while (name.size() < 10 || price.size() < 10);

        System.out.println("Name : " + name.get(0) + "\n" + "Price : " + price.get(0));
    }

    public static void seatView1(ChromeDriver driver) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        WebElement sub = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='CTA-red h-s-btn']")));
        sub.click();
    }

    public static void getSeatNo(ChromeDriver driver) {
        List<WebElement> seatList = driver.findElements(By.xpath("//i[@class='flex item-center justify-center u_font25 icon-Berth-Horizontal']"));
        List<String> seatNumbers = new ArrayList<>();

        for (WebElement webElement : seatList) {
            String titleAttribute = webElement.getAttribute("data-original-title");
            String seatNo = parseSeatNo(titleAttribute);

            seatNumbers.add(seatNo);
        }

        List<String> filteredSeats = filterEvenSeatsWithAdjacent(seatNumbers);
        List<String> finalFilteredSeats = eliminateSeatsWithS(filteredSeats);
        System.out.println("Final Filtered Seats: " + finalFilteredSeats);
    }

    private static String parseSeatNo(String titleAttribute) {
        String seatNo = "";
        if (titleAttribute != null && !titleAttribute.isEmpty()) {
            String[] parts = titleAttribute.split("<span>Seat No:</span> <span>");
            if (parts.length > 1) {
                seatNo = parts[1].split("</span>")[0];
            }
        }
        return seatNo;
    }

    private static List<String> filterEvenSeatsWithAdjacent(List<String> seatNumbers) {
        List<String> filteredSeats = new ArrayList<>();

        for (String currentSeat : seatNumbers) {
            if (isEvenSeat(currentSeat) && isAdjacentOddSeatPresent(seatNumbers, currentSeat)) {
                filteredSeats.add(currentSeat);
            }
        }

        return filteredSeats;
    }

    private static List<String> eliminateSeatsWithS(List<String> seatNumbers) {
        List<String> filteredSeats = new ArrayList<>();

        for (String seat : seatNumbers) {
            if (!seat.toUpperCase().contains("S")) {
                filteredSeats.add(seat);
            }
        }

        return filteredSeats;
    }

    private static boolean isEvenSeat(String seat) {
        int seatNumber = Integer.parseInt(seat.replaceAll("\\D", ""));
        return seatNumber % 2 == 0;
    }

    private static boolean isAdjacentOddSeatPresent(List<String> seatNumbers, String currentSeat) {
        String adjacentOddSeat = getAdjacentOddSeat(currentSeat);
        return seatNumbers.contains(adjacentOddSeat) || seatNumbers.contains(adjacentOddSeat.toUpperCase());
    }

    private static String getAdjacentOddSeat(String seat) {
        int seatNumber = Integer.parseInt(seat.replaceAll("\\D", ""));
        int adjacentOddSeatNumber = seatNumber % 2 == 0 ? seatNumber - 1 : seatNumber + 1;
        return seat.startsWith("L") ? "L" + adjacentOddSeatNumber : "U" + adjacentOddSeatNumber;
    }

}

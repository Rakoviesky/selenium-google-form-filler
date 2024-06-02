package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.List;
import java.util.Map;
import java.time.Duration;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();


        try {
            int i = 0;
            while(i < 50) {
                if(driver == null) driver = new FirefoxDriver();
                driver.get("YOUR LINK HERE...");
                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
                List<WebElement> headings = driver.findElements(By.xpath("/html/body/div/div[3]/form/div[2]/div/div[2]/*"));


                Map<WebElement, List<WebElement>> headingToRadiosMap = headings.stream()
                        .collect(Collectors.toMap(
                                heading -> heading,
                                heading -> heading.findElements(By.xpath(".//child::div[@role='radiogroup' or @role='list']"))
                        ));


                headingToRadiosMap.forEach((key, value) -> {


                    value.forEach(it -> {
                        Random random = new Random();
                        List<WebElement> temp = it.findElements(By.xpath(".//child::div[(@role='radio' or @role='checkbox') and not(@data-answer-value='__other_option__' or @data-value='Inna' or @data-value='__other_option__')]"));
                        int randomNum = random.nextInt((temp.size() - 1) + 1) + 1;
                        temp.get(randomNum - 1).click();
                    });


                });

                driver.findElement(By.xpath("/html/body/div/div[3]/form/div[2]/div/div[3]/div[1]/div[1]/div/span/span")).click();
                driver.close();
                driver = null;
                i++;
            }

        }catch (Exception e){
            System.err.println(e.getMessage());
            driver.close();
        }
    }
}
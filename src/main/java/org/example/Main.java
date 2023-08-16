package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://ithillel.ua/");

        WebElement programmingCourse = getElementByXpath(driver,
                "//*[@id='body']/div[1]/main/section[2]/div/div/div[1]/div/ul/li[1]");
        programmingCourse.click();

        WebElement frontEndBasicCourse = getElementByXpath(driver,
                "//*[@id='categories']/div[3]/div/ul/li[1]/div/div[1]/ul/li[1]/a");
        frontEndBasicCourse.click();

        WebElement reactCourse = getElementByXpath(driver,
                "//*[@id='body']/div[1]/main/section[5]/div/div/div[2]/ul/li");

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, 300);");
        Thread.sleep(1000);

//        FluentWait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        WebElement showAllCoachesButton = wait.until(
//                (webDriver) -> {
//                    jsExecutor.executeScript("window.scrollBy(0, 100);");
//                    return driver.findElement(By.xpath("//*[@id='coachesShowAllButton']"));
//                }
//        );
//
//        System.out.println(showAllCoachesButton.isEnabled());
//
//        new WebDriverWait(driver, Duration.ofSeconds(3)).ignoring(ElementClickInterceptedException.class).until(
//                (webdriver) -> {
//                    jsExecutor.executeScript("window.scrollBy(0, 100);");
//                    showAllCoachesButton.click();
//                    return true;
//                }
//        );

        WebElement showAllCoachesButton = getElementByXpath(driver, "//*[@id='coachesShowAllButton']");
        jsExecutor.executeScript("window.scrollBy(0, 300);");
        showAllCoachesButton.click();

        List<WebElement> coaches = driver.findElements(By.cssSelector(".coach-card_name"));

        for (WebElement coach : coaches) {
            System.out.println(coach.getText());
        }
        driver.quit();
    }

    public static WebElement getElementByXpath(WebDriver driver, String xpath) throws InterruptedException {
        WebElement webElement = driver.findElement(By.xpath(xpath));
        Actions scrollToWebElement = new Actions(driver);
        scrollToWebElement.moveToElement(webElement).perform();
        Thread.sleep(1000);
        return webElement;
    }
}
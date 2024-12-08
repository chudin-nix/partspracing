package com.example.partspracing.service;

import com.example.partspracing.entity.PartDto;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("mikado")
public class MikadoServiceImpl implements PartService {
    private WebDriver driver;

    @Value("${mikado.login}")
    private String login;
    @Value("${mikado.password}")
    private String password;

    @Value("${mikado.url}")
    private String url;

    public MikadoServiceImpl() {
        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
      //  options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @Override
    public List<PartDto> getParts(String partNumber) {
        driver.get(url);
        WebElement loginField = driver.findElement(By.xpath("//input[@name='CODE']"));
        loginField.sendKeys(login);
        WebElement passwordField = driver.findElement(By.xpath("//input[@name='PASSWORD']"));
        passwordField.sendKeys(password);
        WebElement submitButton = driver.findElement(By.xpath("//form[@class='login__form ']//button"));
        submitButton.click();

        WebElement searchField = driver.findElement(By.xpath("//input[@id='searchcode']"));
        searchField.sendKeys(partNumber);
        searchField.submit();

        List<PartDto> parts = new ArrayList<>();
        List<WebElement> partElements = driver.findElements(By.xpath("//table[@id='maintbl']"));
        for (WebElement partElement : partElements) {
            String partArticle = partElement.findElement(By.xpath("//tr[@onclick='if(!working) pClick(this)']//a[contains(@href, 'galleyp.asp')]")).getText();
            String partName = partElement.findElement(By.xpath("//tr[@onclick='if(!working) pClick(this)']/td[3]")).getText();
            String partCompany = partElement.findElement(By.xpath("//tr[@onclick='if(!working) pClick(this)']/td[2]")).getText();
            String partCountString = partElement.findElement(By.xpath("")).getText();
            int partCount = Integer.parseInt(partCountString);
            String shippingDateString = partElement.findElement(By.xpath("")).getText();
            int shippingDate = Integer.parseInt(shippingDateString);
            String priceString = partElement.findElement(By.xpath("//tr[@onclick='if(!working) pClick(this)']//td[@class='rt price']")).getText();
            BigDecimal price = new BigDecimal(priceString);

            PartDto partDto = PartDto.builder().id(partArticle).name(partName).company(partCompany).count(0).price(price).shippingDate(shippingDate).source("Emex").build();

            parts.add(partDto);
        }

        return parts;
    }

    public void closeBrowser() {
        driver.quit();
    }
}

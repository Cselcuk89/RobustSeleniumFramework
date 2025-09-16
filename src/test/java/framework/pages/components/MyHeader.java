package framework.pages.components;

import framework.pages.StorePage;
import framework.projectbase.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyHeader extends BasePage {
    private final By storeMenuLink = By.cssSelector("#menu-item-1227 > a");
    public MyHeader(WebDriver driver) {
        super(driver);
    }
    @Step
    public StorePage navigateToStoreUsingMenu(){
        wait.until(ExpectedConditions.elementToBeClickable(storeMenuLink)).click();
        return new StorePage(driver);
    }
}

package com.app.challenge.mobile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

import java.util.List;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;

public class FastShoppingListPage {
	
	private AndroidDriver driver;
	
	public FastShoppingListPage() {
		
	}
	
    public FastShoppingListPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    
    @AndroidFindBy(xpath = "//android.widget.ImageView/android.view.View/android.view.View")
    public AndroidElement listMenuElement;
    
    @AndroidFindBy(xpath = "//android.widget.Button[@index=2]")
    public AndroidElement newListButton;
    
    @AndroidFindBy(xpath = "//android.widget.EditText")
    public AndroidElement newListTextBox;
    
    @AndroidFindBy(xpath = "//android.widget.EditText")
    public AndroidElement updateListTextBox;
    
    @AndroidFindBy(xpath = "//android.widget.Button[@index=3]")
    public AndroidElement addListButton;
    
    @AndroidFindBy(xpath = "//android.widget.Button[@index=4]")
    public AndroidElement addNewItemButton;
    
    @AndroidFindBy(xpath = "//android.widget.Button[@index=4]")
    public AndroidElement saveUpdateItemItemButton;
    
    @AndroidFindBy(xpath = "//android.widget.Button[@index=3]")
    public AndroidElement addItemButton;
    
    @AndroidFindBy(xpath = "//android.widget.Button[@index=3 and string-length(@content-desc)>1]")
    public AndroidElement editItemButton;
    
    @AndroidFindBy(xpath = "//android.widget.Button[@index=2]")
    public AndroidElement removeItemButton;
    
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"####\"]/android.widget.CheckBox")
    public AndroidElement itemCheckBoxElement;
    
    public List itemsSelected() {
    	return driver.findElementsByXPath("//android.view.View/android.widget.CheckBox[@checked=\"true\"]");
    }
    
    @AndroidFindBy(xpath = "//android.widget.Button[@index=1 and @content-desc=\"ARCHIVE\"]")
    public  AndroidElement archiveButton;
    
    @AndroidFindBy(xpath = "//android.view.View[@index=0 and string-length(@content-desc)>1]")
    public AndroidElement listElement;
    
    @AndroidFindBy(xpath = "//android.view.View[@index=3]")
    public AndroidElement archivedMenuElement;
    
    @AndroidFindBy(xpath = "//android.view.View[@index=2 and @clickable=\"true\"]")
    public AndroidElement currentMenuElement;
    
    @AndroidFindBy(xpath = "//android.widget.Button[contains(@content-desc,'Mobile')]")
    public AndroidElement archivedListNameElement;
    
    public WebElement custItemElement (String element) { 
    	return driver.findElement(By.xpath("//android.view.View[contains(@content-desc,'"+element+"')]"));
    }
    
    public List custItemElements (String element) { 
    	return driver.findElements(By.xpath("//android.view.View[contains(@content-desc,'"+element+"')]"));
    }
    
    public WebElement custItemCheckboxElement (String element) { 
    	return driver.findElement(By.xpath("//android.view.View[@content-desc=\""+element+"\"]/android.widget.CheckBox"));
    }

    public WebElement custarchivedListElement (String element) { 
    	return driver.findElement(By.xpath("//android.widget.Button[contains(@content-desc,'"+element+"')]"));
    }
    
    public void addNewValue( String itemName) {
		newListTextBox.click();
        Actions action = new Actions(driver);
        action.sendKeys(itemName).perform();
        addItemButton.click();
	}
	
	public void updateAmountItem(String itemName, Integer amount) {
		custItemElement(itemName).click();
		editItemButton.click();
		updateListTextBox.clear();
		updateListTextBox.click();
        Actions action = new Actions(driver);
        action.sendKeys(" "+amount).perform();
        saveUpdateItemItemButton.click();
	}
	
	public Boolean isDisplayed(WebElement element) {
		return element.isDisplayed();
	}
	
	public void verifyItemExist(String element) {
		Assert.assertEquals(custItemElements(element).size(),0, 
				"The element "+ element +" does not exists anymore");
	}
	
	public void verifyItemsSelected(int itemAmount) {
		Assert.assertEquals(itemsSelected().size(),itemAmount 
				,"All items are selected");
	}
    
	public void tapMoreOptions(int x, int y) {
		new TouchAction(driver).tap(PointOption.point(x, y)).waitAction(waitOptions(ofMillis(250)));
	}
    
}

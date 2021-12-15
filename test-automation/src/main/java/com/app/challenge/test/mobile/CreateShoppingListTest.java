package com.app.challenge.test.mobile;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.app.challenge.mobile.pages.FastShoppingListPage;
import com.app.challenge.util.ReadConfigProperties;

public class CreateShoppingListTest {
	
	private final static String MY_LIST = "My List";
	private final static String PRODUCT_1 = "Apples";
	private final static String PRODUCT_2 = "Apricots";
	private final static String PRODUCT_3 = "Bananas";
	private final static String PRODUCT_4 = "Blueberries";
	private final static String PRODUCT_5 = "Cantaloupe";
	private final static String PRODUCT_6 = "Cherries";
	
	private final static Integer PROD_AMOUNT_2 = 15;
	private final static Integer PROD_AMOUNT_3 = 5;
	private final static Integer PROD_AMOUNT_4 = 15;
	private final static Integer PROD_AMOUNT_5 = 3;
	private final static Integer PROD_AMOUNT_6 = 8;
	
	AndroidDriver driver;
	FastShoppingListPage fastShoppingListPage;
	
	  @BeforeTest
	  public void beforeTest() throws IOException {
		  ReadConfigProperties property = new ReadConfigProperties();
		  DesiredCapabilities capabilities = new DesiredCapabilities();
		  
		  property.getPropertyValues();
	      capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
	      capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, property.getDeviceName());
	      capabilities.setCapability(MobileCapabilityType.APP, property.getBsAppUrl());
	      capabilities.setCapability("unicodeKeyboard", property.getKeyboardUnicode());
	      capabilities.setCapability("resetKeyboard", property.getKeyboardReset());
	      
	      AndroidDriver driver = new AndroidDriver(new URL(property.getBsAppServer()), capabilities);
	      fastShoppingListPage = new FastShoppingListPage(driver);
	  }
	
	@Test
	public void testShoppingList() {
		System.out.println("Test Shopping List Creation");
		
		// Create New List
		Reporter.log("Creating new List", true);
		fastShoppingListPage.listMenuElement.click();
		fastShoppingListPage.newListButton.click();
		fastShoppingListPage.addNewValue(MY_LIST);
		
		// Add Items
		Reporter.log("Adding new items to the list", true);
		fastShoppingListPage.addNewItemButton.click();
		fastShoppingListPage.addNewValue(PRODUCT_1);
		fastShoppingListPage.addItemButton.click();
		fastShoppingListPage.addNewValue(PRODUCT_2);
		fastShoppingListPage.addItemButton.click();
		fastShoppingListPage.addNewValue(PRODUCT_3);
		fastShoppingListPage.addItemButton.click();
		fastShoppingListPage.addNewValue(PRODUCT_4);
		fastShoppingListPage.addItemButton.click();
		fastShoppingListPage.addNewValue(PRODUCT_5);
		fastShoppingListPage.addItemButton.click();
		fastShoppingListPage.addNewValue(PRODUCT_6);
		
		// Update Items with quantity
		Reporter.log("Updating items with quantity", true);
		fastShoppingListPage.updateAmountItem(PRODUCT_2, PROD_AMOUNT_2);
		fastShoppingListPage.updateAmountItem(PRODUCT_3, PROD_AMOUNT_3);
		fastShoppingListPage.updateAmountItem(PRODUCT_4, PROD_AMOUNT_4);
		fastShoppingListPage.updateAmountItem(PRODUCT_5, PROD_AMOUNT_5);
		fastShoppingListPage.updateAmountItem(PRODUCT_6, PROD_AMOUNT_6);
		
		// Delete Items
		Reporter.log("Deleting two items", true);
		fastShoppingListPage.custItemElement(String.format("%s %s", PRODUCT_4, PROD_AMOUNT_4)).click();
		fastShoppingListPage.removeItemButton.click();	
		fastShoppingListPage.verifyItemExist(String.format("%s %s", PRODUCT_4, PROD_AMOUNT_4));
		
		fastShoppingListPage.custItemElement(String.format("%s %s", PRODUCT_5, PROD_AMOUNT_5)).click();
		fastShoppingListPage.removeItemButton.click();
		fastShoppingListPage.verifyItemExist(String.format("%s %s", PRODUCT_5, PROD_AMOUNT_5));
		
		// Mark Items as completed
		Reporter.log("Marking items as completed", true);
		fastShoppingListPage.custItemCheckboxElement(String.format("%s %s", PRODUCT_2, PROD_AMOUNT_2)).click();
		fastShoppingListPage.custItemCheckboxElement(String.format("%s %s", PRODUCT_3, PROD_AMOUNT_3)).click();
		fastShoppingListPage.custItemCheckboxElement(String.format("%s %s", PRODUCT_6, PROD_AMOUNT_6)).click();
		fastShoppingListPage.custItemCheckboxElement(PRODUCT_1).click();
		fastShoppingListPage.verifyItemsSelected(4);
		
		//Archive List
		Reporter.log("Archiving list", true);
		fastShoppingListPage.archiveButton.click();
		
		fastShoppingListPage.listElement.click();
		fastShoppingListPage.archivedMenuElement.click();
		fastShoppingListPage.currentMenuElement.click();
		fastShoppingListPage.verifyItemExist(MY_LIST);
	}

}

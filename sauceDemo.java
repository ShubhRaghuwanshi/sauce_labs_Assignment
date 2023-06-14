package MavenPractice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class sauceDemo {

	public static void main(String[] args) throws InterruptedException {
		//setup of chrome driver with web driver
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();
		
		//given the input id after locating web element
		WebElement username=driver.findElement(By.xpath("//input[@id=\"user-name\"]"));
		username.sendKeys("standard_user");
		Thread.sleep(2000);
		
		//given the input password after locating web element
		WebElement password=driver.findElement(By.xpath("//input[@id=\"password\"]"));
		password.sendKeys("Shubh@123");
		Thread.sleep(2000);
		
		
		//click on login button
		WebElement loginbt=driver.findElement(By.xpath("//input[@id=\"login-button\" and @name=\"login-button\"]"));
		loginbt.click();
		Thread.sleep(2000);
		
		WebElement errorMsg=driver.findElement(By.xpath("//h3[@data-test=\"error\"]"));
		System.out.println("error msg:"+errorMsg.getText());
		String ActualErrorMsg=errorMsg.getText();
		String ExpectedErrorMsg= "Epic sadface: Username and password do not match any user in this service";
		Assert.assertEquals(ActualErrorMsg, ExpectedErrorMsg, "Epic sadface: Username and password do not match any user in this service");
		
		String ActualfirstPage=driver.getCurrentUrl();
		String ExpectedfirstPage="https://www.saucedemo.com/";
		Assert.assertEquals(ActualfirstPage, ExpectedfirstPage, "https://www.saucedemo.com/");
		System.out.println("First Page URL:"+ActualfirstPage);
		
		password.clear();
		password.sendKeys("secret_sauce");
		Thread.sleep(2000);
		
		
		WebElement loginbt1=driver.findElement(By.xpath("//input[@id=\"login-button\" and @name=\"login-button\"]"));
		loginbt1.click();
		Thread.sleep(2000);
		
		String ActualsecondPage=driver.getCurrentUrl();
		String ExpectedsecondPage="https://www.saucedemo.com/inventory.html";
		Assert.assertEquals(ActualsecondPage, ExpectedsecondPage, "https://www.saucedemo.com/inventory.html");
		System.out.println("Second Page URL:"+ActualsecondPage);
		
		
		
		
		//sort out the products in low to high order
		WebElement sortBt=driver.findElement(By.xpath("//select[@class=\"product_sort_container\"]"));
		Select sel=new Select(sortBt);
		sel.selectByValue("lohi");
		Thread.sleep(2000);
		
		//adding products to the cart
		WebElement addcartOnesisbt=driver.findElement(By.xpath("//button[@name=\"add-to-cart-sauce-labs-onesie\"]"));
		addcartOnesisbt.click();
		Thread.sleep(2000);
		
		WebElement addcartBikelighgtbt=driver.findElement(By.xpath("//button[@name=\"add-to-cart-sauce-labs-bike-light\"]"));
		addcartBikelighgtbt.click();
		Thread.sleep(2000);
		
		WebElement addcartTshirtbt=driver.findElement(By.xpath("//button[@name=\"add-to-cart-sauce-labs-bolt-t-shirt\"]"));
		addcartTshirtbt.click();
		Thread.sleep(2000);
		
		WebElement addcartTshirtRbt=driver.findElement(By.xpath("//button[@name=\"add-to-cart-test.allthethings()-t-shirt-(red)\"]"));
		addcartTshirtRbt.click();
		Thread.sleep(2000);
		
		WebElement addocartBagbt=driver.findElement(By.xpath("//button[@name=\"add-to-cart-sauce-labs-backpack\"]"));
		addocartBagbt.click();
		Thread.sleep(2000);
		
		WebElement addcartJacketbt=driver.findElement(By.xpath("//button[@name=\"add-to-cart-sauce-labs-fleece-jacket\"]"));
		addcartJacketbt.click();
		Thread.sleep(2000);
		
		//going to cart window by clicking on cart button
		WebElement cartBt=driver.findElement(By.xpath("//a[@class=\"shopping_cart_link\"]"));
		cartBt.click();
		Thread.sleep(2000);
		
		driver.navigate().refresh();
//		driver.navigate().refresh();
		
		//listing the products by their price and removing the products which are below the price $15
		List<WebElement> cartItems = driver.findElements(By.xpath("//div[@class='cart_item']"));
		
		try {
        // Iterate through the cart items
        for (WebElement item : cartItems) {
            // Extract the price
          WebElement priceElement = item.findElement(By.cssSelector("div[class*='_item_price']"));
//          WebDriverWait wait=new WebDriverWait(driver, 10);
//          wait.until(ExpectedConditions.visibilityOfAllElements(null));
//        	WebElement priceElement = item.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]/div[2]/div"));
            
          String priceString = priceElement.getText().replaceAll("[^0-9.]", "");
            double price = Double.parseDouble(priceString);
            
            // Compare the price
            if (price < 15.0) {
                // Remove the product
                WebElement removeButton = item.findElement(By.xpath("//button[@class=\"btn btn_secondary btn_small cart_button\"]"));
                removeButton.click();
            }
        }}
        catch (Exception e) {
			// TODO: handle exception
		}
            
        
        //checkout with selected items        
        WebElement checkoutBt=driver.findElement(By.xpath("//button[@class=\"btn btn_action btn_medium checkout_button\"]"));
        checkoutBt.isSelected();
        checkoutBt.click();
        Thread.sleep(3000);
        
        //filling the personal information
        WebElement firstName=driver.findElement(By.xpath("//input[@id=\"first-name\" and @name=\"firstName\"]"));
        firstName.sendKeys("Shubham");
        Thread.sleep(3000);

        
        WebElement lastName=driver.findElement(By.xpath("//input[@id=\"last-name\" and @name=\"lastName\"]"));
        lastName.sendKeys("Raghuwanshi");
        Thread.sleep(3000);

        
        WebElement postCode=driver.findElement(By.xpath("//input[@id=\"postal-code\" and @name=\"postalCode\"]"));
        postCode.sendKeys("411038");
        Thread.sleep(3000);

       
        //proceed by continue button
        WebElement continueBt=driver.findElement(By.xpath("//input[@id=\"continue\" and @name=\"continue\"]"));
        continueBt.click();
        Thread.sleep(3000);

        
        driver.navigate().forward();
       
        //click on finish button
        WebElement finishBt=driver.findElement(By.xpath("//button[@id=\"finish\"]"));
        finishBt.click();
        Thread.sleep(3000);

        
        driver.navigate().forward();
        
        //click on home button to log out
        WebElement homeBt=driver.findElement(By.xpath("//button[text()='Back Home']"));
        homeBt.click();
        Thread.sleep(3000);

        
        driver.navigate().refresh();
        
        //click on menu button for log out option
        WebElement menuBt=driver.findElement(By.xpath("//button[@id=\"react-burger-menu-btn\"]"));
        menuBt.click();
        Thread.sleep(3000);

        
        driver.navigate().forward();
        
        //log out with log out link
        WebElement logoutLink=driver.findElement(By.xpath("//a[@id=\"logout_sidebar_link\"]"));
        logoutLink.click();
        Thread.sleep(2000);

        
        
        
         
		}

		}
				
	



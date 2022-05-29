
package lambdatest; 

import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import java.net.MalformedURLException;
import org.junit.Assert;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.openqa.selenium.support.ui.Select;  
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SeleniumTest {

  public RemoteWebDriver driver = null;
  public JavascriptExecutor jse = null;
  String username = "user_name";
  String accessKey = "access_key";
  
   
  @BeforeTest
  @Parameters(value= {"browserName","version","platform"})
  public void setUp(String browserName, String version, String platform) throws Exception {
   System.out.println("Browser Name - "+browserName+", Version - "+version+", Platform - "+platform);	  
   DesiredCapabilities capabilities = new DesiredCapabilities();
   capabilities.setCapability("browserName", browserName);
   capabilities.setCapability("version", version);
   capabilities.setCapability("platform", platform);
   capabilities.setCapability("resolution","1024x768");
   capabilities.setCapability("build", "First Test");
   capabilities.setCapability("name", "Sample Test");
   capabilities.setCapability("network", true); 
   capabilities.setCapability("visual", true); 
   capabilities.setCapability("video", true); 
   capabilities.setCapability("console", true);    
   try {
     driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub"), capabilities);
     jse  = (JavascriptExecutor) driver;
   } catch (MalformedURLException e) {
     System.out.println("Invalid grid URL");
   }
 }

 @Test(timeOut = 20000)
 public void simpleFormsDemo() throws Exception {
    
	 String welcomeText = "Welcome to LambdaTest";
     
     System.out.println("<---Test Scenario 1--->");  
     
     driver.navigate().to("https://www.lambdatest.com/selenium-playground");
     
     jse.executeScript("window.scrollBy(0,100)","");
     
     driver.findElement(By.xpath("//a[text() = 'Simple Form Demo']")).click();
     
     if(driver.getCurrentUrl().contains("simple-form-demo")) {
   
      System.out.println("Current URL contains 'simple-form-demo' text"); // Print if url contains required text
     }
     else {

      System.out.println("URL doesn't contain 'simple-form-demo' text"); // Print if url doesn't contain required text
         
     }
     
     driver.findElement(By.id("user-message")).sendKeys(welcomeText); // send user input 
   
     driver.findElement(By.cssSelector("button#showInput")).click(); // click 'Get Checked value' button
     
     if(driver.findElement(By.xpath("//p[@id='message']")).getText().equals(welcomeText)) 
     {
      System.out.println("Message box contains user input");
     }
     else {
       System.out.println("Message box doesn't contain user input");
     }
   
 }
 
 @Test(timeOut = 20000)
 public void sliderDemo(){
   
   System.out.println("<---Test Scenario 2--->");
   
   driver.navigate().to("https://www.lambdatest.com/selenium-playground");
   
   driver.findElement(By.xpath("//a[text() = 'Drag & Drop Sliders']")).click();
   
   WebElement slider = driver.findElement(By.xpath("//input[@value = '15']"));
     
   jse.executeScript("window.scrollBy(0,100)","");
   
   int xCord = slider.getLocation().getX(); 
  
   int moveTo = 172;
   
   Actions builder = new Actions(driver);
   
   builder.moveToElement(slider).click().dragAndDropBy(slider,xCord-moveTo,0).build().perform();
    
   WebElement sliderVal = driver.findElement(By.xpath("//input[@value = '15']/following-sibling::output"));
  
  
   while(!sliderVal.getText().equals("95"))
   {
	   System.out.println(sliderVal.getText());
	   if(Integer.valueOf(sliderVal.getText()) > 95 ) {
		  
		   moveTo = moveTo + 1;
		   builder.moveToElement(slider).click().dragAndDropBy(slider,xCord-moveTo,0).build().perform();
	   }
	   else {
		   
		   moveTo = moveTo - 1;
		   builder.moveToElement(slider).click().dragAndDropBy(slider,xCord-moveTo,0).build().perform();
	   }
   }
   if(sliderVal.getText().equals("95"))
    {
       System.out.println("Slider is moved to range 95");
    }
      else {
          System.out.println("Slider is not moved to range 95");
      }
     
   } 

@Test (timeOut = 20000)
 public void inputFormsDemo(){
    
	  System.out.println("<---Test Scenario 3--->");
      
	  driver.navigate().to("https://www.lambdatest.com/selenium-playground"); 
	  
	  jse.executeScript("window.scrollBy(0,50)","");
      
	  driver.findElement(By.xpath("//a[text() = 'Input Form Submit']")).click();       
      
	  driver.findElement(By.xpath("//button[text()='Submit']")).click();
      
       String alertMsg = driver.findElement(By.id("name")).getAttribute("validationMessage");
      
       Assert.assertEquals(alertMsg,"Please fill out this field.");
       
       driver.findElement(By.id("name")).sendKeys("John");
       
       driver.findElement(By.id("inputEmail4")).sendKeys("john475@mailinator.com");
       
       driver.findElement(By.id("inputPassword4")).sendKeys("abc@123");
       
       driver.findElement(By.id("company")).sendKeys("abc@123");
       
       jse.executeScript("window.scrollBy(0,50)","");
      
       Select country = new Select(driver.findElement(By.xpath("//label[text()='Country*']/following-sibling::select")));
       
       country.selectByVisibleText("United States");
             
       driver.findElement(By.id("websitename")).sendKeys("https://google.com");      
       
       driver.findElement(By.id("inputCity")).sendKeys("New York");
       
       driver.findElement(By.id("inputAddress1")).sendKeys("123 xyz st");
       
       driver.findElement(By.id("inputAddress2")).sendKeys("123 xyz st");
       
       driver.findElement(By.id("inputState")).sendKeys("New York");
       
       driver.findElement(By.id("inputZip")).sendKeys("12001");
       
       driver.findElement(By.xpath("//button[text()='Submit']")).click();
       
       if(driver.findElement(By.cssSelector("p.success-msg")).getText().equals("Thanks for contacting us, we will get back to you shortly.")
    	    && driver.findElement(By.cssSelector("p.success-msg")).isDisplayed() )
       {
              System.out.println("Success message displayed");      
       } else {
               System.out.println("Success message not displayed");
       }
       
}

 @AfterTest
 public void tearDown() {
	driver.quit();
 }

}

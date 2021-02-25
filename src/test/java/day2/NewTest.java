package day2;

import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class NewTest {
	public AndroidDriver driver;
	
	  @Test(priority=1)
	  public void login(){
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		     driver.findElement(By.xpath("//*[@id='Email']")).sendKeys("sanskriti949@gmail.com");
		     driver.findElement(By.xpath("//*[@id='Password']")).sendKeys("mehek@123");
		     driver.hideKeyboard();
		     driver.findElement(By.xpath("//input[@value='Log in']")).click();
		     String expected="sanskriti949@gmail.com";
		     String actual=driver.findElement(By.xpath("//a[text()='sanskriti949@gmail.com']")).getText();
		     Assert.assertEquals(actual, expected);
	  }
  @Test(priority=2)
  public void Camera() throws IOException {
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.findElement(By.xpath("//span[@class='icon']")).click();
      driver.findElement(By.xpath("//a[contains(text(),'Electronics')]//following-sibling::span")).click();
      driver.findElement(By.xpath("//li[@class='active']//ul//a[contains(text(),'Camera, photo')]")).click();
      File file= new File("C:\\Users\\Sanskriti\\Documents\\SDET\\TestData\\Mobile.xls");
      FileInputStream fis= new FileInputStream(file);
      HSSFWorkbook wb= new HSSFWorkbook(fis);
      HSSFSheet sheet=wb.getSheetAt(0);
      int rc= sheet.getLastRowNum();
      System.out.println(rc);
      for (int i=1;i<=rc;i++) {
          String display=sheet.getRow(i).getCell(0).getStringCellValue();
          System.out.println(display);
          driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
          WebElement drop=driver.findElement(By.xpath("//select[@id='products-pagesize']"));
          Select sel = new Select(drop);
          sel.selectByVisibleText(display);
          String expected="Digital SLR Camera 12.2 Mpixel";
          JavascriptExecutor je = (JavascriptExecutor) driver;
          
          WebElement element = driver.findElement(By.xpath("((//div[@class='details'])[3]//following::a)[1]"));
           je.executeScript("arguments[0].scrollIntoView(true);",element); 
          String actual=element.getText();
          Assert.assertEquals(actual, expected, "Passed");
          
      }

  }
  @BeforeClass
  public void beforeClass() throws MalformedURLException {
      DesiredCapabilities capability = new DesiredCapabilities();
      capability.setCapability(MobileCapabilityType.DEVICE_NAME,"Sanskriti");
     capability.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
     capability.setCapability(MobileCapabilityType.BROWSER_NAME,"Chrome");
     driver=new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capability);
     driver.get("http://demowebshop.tricentis.com/login");
    
  }

  @AfterClass
  public void afterClass() throws InterruptedException {
	  Thread.sleep(5000);
	  driver.close(); 
	  
  }

}

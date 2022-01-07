package demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import Resources.baseclass;
import Resources.Loactors;

public class basic extends baseclass {
	
	
	public static Logger log=LogManager.getLogger("basic");
	@Test
	public void basictestcase() throws InterruptedException 
	  
	{
		test=extent.createTest("basictestcase");
		WebElement ele=driver.findElement(By.xpath(Loactors.textbox1));
		ele.sendKeys("Akshata");
	    log.info("This is first testcase");
	    Thread.sleep(9000);
	    
		
	}
	@Test
	public void dropdown() throws InterruptedException 
	  
	{
		test=extent.createTest("dropdown");
		WebElement ele=driver.findElement(By.xpath(Loactors.dropdown1));
		Select s=new Select(ele);
		s.selectByIndex(2);
		 log.info("This is second testcase");
		 Thread.sleep(9000);
	}
	@Test
	public void democaseforfailure() throws InterruptedException 
	  
	{
		test=extent.createTest("democaseforfailure");
		WebElement ele=driver.findElement(By.xpath("abc"));
		ele.click();
		 log.info("This is Third testcase");
		 Thread.sleep(9000);
	}

}

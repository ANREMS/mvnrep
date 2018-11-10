package REMS;

import org.openqa.selenium.By;

public class Home extends MainClass
{
  public void presriberDashboard() throws Exception 
  {
	  System.out.println("new line added in pres dashboard");
	  System.out.println("write code to click on the button and validate");
	  driver.findElement(By.xpath(HP.presdeashboardbtn)).click();
	  if (driver.findElement(By.xpath("//span[@class='icon findBtn']")).isDisplayed())
		{
			System.out.println("Prescriber Dashboard page displayed");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//span[@class='icon mainHomeBtn']")).click();
			driver.close();
		}
  }
  public void manageMyAccount() throws Exception
  {
	  driver.findElement(By.xpath(HP.managemyaccount)).click();
	  System.out.println("Manage my account page is displayed");
	
	} 
  public void logout() throws Exception
  {
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//span[@class='icon mainLogoutBtn']")).click();
	  driver.close();
  }
  }



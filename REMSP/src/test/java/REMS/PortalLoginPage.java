package REMS;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;

public class PortalLoginPage extends MainClass
{
		
	
	public void login() throws InterruptedException 
	{
		driver.manage().window().maximize();
		driver.get(PLPP.url);
		Thread.sleep(3000);
		
		driver.findElement(By.id(PLPP.userid)).sendKeys("Inorbit");
		driver.findElement(By.id(PLPP.userpwd)).sendKeys("Celgene1");
		Thread.sleep(2000);
		driver.findElement(By.xpath(PLPP.loginbtn)).click(); 		
	}
	
	
	public void login_validate() throws Exception
	{
		FileInputStream ID =new FileInputStream("C:\\Selenium\\Class Notes\\Input_data.xlsx");  //get the excel file
		XSSFWorkbook wb=new XSSFWorkbook(ID);    //get workbook
		XSSFSheet ws=wb.getSheet("sheet1_validation");     //get the sheet in workbook
		
		String str;
		Row row;
		Cell cell;
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get(PLPP.url);
		
		for(int r=1;r<=ws.getLastRowNum();r++)  //for all the rows
		{
			row=ws.getRow(r);
			
			
			
			driver.findElement(By.id(PLPP.userid)).sendKeys(row.getCell(0).getStringCellValue());
			cell=row.getCell(1,MissingCellPolicy.RETURN_BLANK_AS_NULL);
			if(cell==null)
				driver.findElement(By.id(PLPP.userpwd)).sendKeys("");
			else 
			    driver.findElement(By.id(PLPP.userpwd)).sendKeys(row.getCell(1).getStringCellValue());
			
			driver.findElement(By.xpath(PLPP.loginbtn)).click(); 
			Thread.sleep(3000);		
						
			try
			{
				if(driver.findElement(By.xpath("//span[@class='icon mainLogoutBtn']")).isDisplayed());
				{
					//row.createCell(2).setCellValue("Login is success - Iteration"+ r);
					driver.findElement(By.xpath("//span[@class='icon mainLogoutBtn']")).click();
					
					log= extent.createTest("passTest");
					log.log(Status.PASS, "Login is success");
					takescreenshot("Loginsuccess.png");
				}
			}
			
			catch(Exception e)
			{
				if(cell==null)
				{
					try
					{
					driver.switchTo().frame("popupContent");
					str=driver.findElement(By.tagName("li")).getText();
					//row.createCell(2).setCellValue("Login failed   :"+str);
					
					log= extent.createTest("passTest");
					log.log(Status.PASS, "For blank password alert is displayed "+str);
					takescreenshot("blankpwd.png");
					
					}
					catch(Exception e1)
					{
						//System.out.println("For blank pwd, there is no alert messages asking to enter pwd");
						log= extent.createTest("failTest");
						log.log(Status.FAIL, "For blank password alert NOT displayed asking for password ");
						takescreenshot("blankpwd.png");
					}
				}
				else
				{
					try
					{
						str=driver.findElement(By.xpath("//div[@class='errorText']")).getText();
						//row.createCell(2).setCellValue("Login failed   :"+str);
						
						log= extent.createTest("passTest");
						log.log(Status.PASS, "Invalid pwd displayed ERROR  "+str);
						takescreenshot("invalidpwd.png");
						
					}
					catch(Exception e2)
					{
						//System.out.println("For invalid pwd, there is not error message displayed");
						log= extent.createTest("failTest");
						log.log(Status.FAIL, "Invalid pwd NOT displayed ERROR  ");
						takescreenshot("invalidpwd.png");
					}
				}
			}
			
		}
		FileOutputStream fout=new FileOutputStream("C:\\Selenium\\Class Notes\\Input_data.xlsx"); //file for writing
		wb.write(fout); 
		wb.close();
		
	}
	
	public void forgotpwd() throws Exception
	{
		driver.manage().window().maximize();
		driver.get(PLPP.url);
		Thread.sleep(3000);
		driver.findElement(By.xpath(PLPP.forgotpwd)).click();
		Thread.sleep(3000);
		
		try
		{
			if(driver.findElement(By.xpath(PLPP.continuebtn)).isDisplayed())
			{
				System.out.println("Forgot pwd page  Continue button is available");
			}
		}
		catch(Exception e)
		{
			System.out.println("Forgot pwd page, Continue button not displayed");
		}
		
		try
		{
			if(driver.findElement(By.xpath(PLPP.cancelbtn)).isDisplayed())
			{
				System.out.println("Forgot pwd page  Cancel button is available");
			}
		}
		catch(Exception e)
		{
			System.out.println("Forgot pwd page, Cancel button not displayed");
		}
		
		try
		{
			int flag=0;
			if(driver.findElement(By.id(PLPP.userid)).isDisplayed()==false)
			{
			   flag=1;
			}
			if(driver.findElement(By.id(PLPP.userid)).isEnabled()==false)
			{
			   flag=1;
			}
			if(flag==0)
			{
				System.out.println("forgot pwd page,  username editbox is displayed & enabled");
			}
			if(flag==1)
			{
				System.out.println("forgot pwd page, username editbox is either not displayed / not enabled");
			}
		}
		catch(Exception e)
		{
			System.out.println("Forgot pwd page, username editbox not available");
		}
		
		
	}
	
}

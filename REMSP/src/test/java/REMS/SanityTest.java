package REMS;

import java.io.FileInputStream;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SanityTest extends MainClass
{
 @Test
 @Parameters({"browser"})
 public void sanitytesting(String str) throws Exception
 {
	 if(str.matches("chrome"))
	 {
		 System.setProperty("webdriver.chrome.driver","C:\\Selenium\\mylocalproj\\Anna_Selenium\\chromedriver.exe");
		 driver=new ChromeDriver();
	 }
	 FileInputStream fin =new FileInputStream("C:\\Selenium\\Driver_Input.xlsx");  //get the excel file
	 XSSFWorkbook wb=new XSSFWorkbook(fin);    //get workbook
	 XSSFSheet ws=wb.getSheet("SanityTest");     //get the sheet in workbook
	 Row row;
	 String classname,methodname;
	 for(int r=1;r<=ws.getLastRowNum();r++)
	 {
		 row=ws.getRow(r);
		 if(row.getCell(5).getStringCellValue().matches("yes"))
		 {
			 classname=row.getCell(3).getStringCellValue();
			 methodname=row.getCell(4).getStringCellValue();
			 
			 Class c=Class.forName(classname); //load the class into memory
		     Method m= c.getMethod(methodname, null); //get the method in the class
		     Object obj=c.newInstance(); //create instance /object for the class
		     m.invoke(obj, null);  //call the method
		 }
	 }
	 fin.close();
 }
}

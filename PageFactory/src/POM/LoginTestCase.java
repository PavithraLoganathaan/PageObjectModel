package POM;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class LoginTestCase {
	
	@DataProvider
	public String[][] LoginDataProvider() throws BiffException, IOException
	{
		FileInputStream excel=new FileInputStream("C:\\Users\\Boopathi\\Documents\\Inputcredentioals.xls");
		Workbook workbook=Workbook.getWorkbook(excel);
		Sheet sheet=workbook.getSheet(0);
		int rowcount=sheet.getRows();
		int columncount=sheet.getColumns();
		
		String data[][]=new String[rowcount-1][columncount];
		
		for(int i=1;i<rowcount;i++)
		{
			for(int j=0;j<columncount;j++)
			{
				data[i-1][j]=sheet.getCell(j, i).getContents();
			}
		}
		return data;
	}
	
	
	@Test(dataProvider = "LoginDataProvider")
	public void login(String Uname,String Pass)
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Boopathi\\Documents\\Selenium\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();     
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		PageFactory.initElements(driver, LoginPageObjects.class);
		LoginPageObjects.txtUsername.sendKeys(Uname);
		LoginPageObjects.txtPassword.sendKeys(Pass);
		LoginPageObjects.Submit.click();
		driver.close();
		
	}
}

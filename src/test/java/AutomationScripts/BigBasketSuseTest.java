package AutomationScripts;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class BigBasketSuseTest {
	@Test
	public void watermelon() throws EncryptedDocumentException, IOException, InterruptedException
	{
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://www.bigbasket.com/");
		WebElement search = driver.findElement(By.xpath("//div[@class=\"grid place-content-start grid-flow-col gap-x-6\"]//input[@placeholder='Search for Products...']"));
		search.sendKeys("watermelon",Keys.ENTER);
		Thread.sleep(10000);
		List<WebElement> Watermelon = driver.findElements(By.xpath("//h3[@class=\"block m-0 line-clamp-2 font-regular text-base leading-sm text-darkOnyx-800 pt-0.5 h-full\"]"));
        int size = Watermelon.size();
        System.out.println(size);
        for(int i=0;i<size;i++)
        {
        	String melon=Watermelon.get(i).getText();
        	FileInputStream fi=new FileInputStream(".\\src\\test\\resources\\BB.xlsx");
        	Workbook wb = WorkbookFactory.create(fi);
        	Sheet sh = wb.getSheet("Sheet1");
        	sh.createRow(i).createCell(0).setCellValue(melon);
        	FileOutputStream fo=new FileOutputStream(".\\src\\test\\resources\\BB.xlsx");
        	wb.write(fo);
        	wb.close(); 
        }
        FileInputStream fi=new FileInputStream(".\\src\\test\\resources\\BB.xlsx");
    	Workbook wb = WorkbookFactory.create(fi);
    	Sheet sh = wb.getSheet("Sheet1");
    	String wmelon = sh.getRow(1).getCell(0).getStringCellValue();
        WebElement name = driver.findElement(By.xpath("//h3[text()='"+wmelon+"']/ancestor::div[@class=\"SKUDeck___StyledDiv-sc-1e5d9gk-0 eA-dmzP\"]//span[@class=\"Label-sc-15v1nk5-0 Pricing___StyledLabel-sc-pldi2d-1 gJxZPQ AypOi\"]"));
	    String price = name.getText();
	    System.out.println(wmelon+"------"+price);
	
	}
}

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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class bigbasketsenario {

	@Test
	public void test() throws EncryptedDocumentException, IOException, InterruptedException
	{
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.bigbasket.com/");
		WebElement stf = driver.findElement(By.xpath("//div[@class='Header___StyledQuickSearch2-sc-19kl9m3-0 gzbZOD']/descendant::input[@type='text']"));
	stf.sendKeys("Watermelon");
	int count=0;
	stf.sendKeys(Keys.ENTER);
	Thread.sleep(3000);
	WebElement Ele= driver.findElement(By.xpath("//div[text()='- Thats all folks -']"));
	JavascriptExecutor js=(JavascriptExecutor)driver;
	int height = Ele.getLocation().getY();
	for (int i = 0; i <height; i++) {
		
		js.executeScript("window.scrollBy(0,"+i+")");
	}
List<WebElement> fruteName = driver.findElements(By.xpath("//div[@class='break-words h-10 w-full']"));
	
	for (int i = 0; i <fruteName.size() ; i++) {
	
		String s=fruteName.get(i).getText();
	//System.out.println(s);	
	FileInputStream fis=new FileInputStream("./src/test/resources/Bigbasket.xlsx");
	Workbook wb=WorkbookFactory.create(fis);
	Sheet sh=wb.getSheet("wetermilon");
	sh.createRow(i).createCell(0).setCellValue(s);
	FileOutputStream fos=new FileOutputStream("./src/test/resources/Bigbasket.xlsx");
	wb.write(fos);
	wb.close();
	
	}
	FileInputStream fis=new FileInputStream("./src/test/resources/Bigbasket.xlsx");
	Workbook wb=WorkbookFactory.create(fis);
	Sheet sh=wb.getSheet("wetermilon");
String key=sh.getRow(3).getCell(0).getStringCellValue();

	
	WebElement price = driver.findElement(By.xpath("//div[@class='SKUDeck___StyledDiv-sc-1e5d9gk-0 eA-dmzP']/descendant::h3[text()='"+key+"']/ancestor::div[@class='SKUDeck___StyledDiv-sc-1e5d9gk-0 eA-dmzP']/descendant::span[@class='Label-sc-15v1nk5-0 Pricing___StyledLabel-sc-pldi2d-1 gJxZPQ AypOi']"));
	String p=price.getText();
	
	System.out.println(key+" "+p);
	}}
	
	

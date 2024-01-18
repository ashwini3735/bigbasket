package AutomationScripts;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class bigBasketPrasadTest {

	@Test
	public void bigBasketTest() throws Throwable
	{
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://www.bigbasket.com/");
		driver.findElement(By.xpath("//input[@placeholder]/ancestor::div[@class='Header___StyledQuickSearch2-sc-19kl9m3-0 gzbZOD']//input")).sendKeys("watermelon");
		driver.findElement(By.xpath("//input[@placeholder]/ancestor::div[@class='Header___StyledQuickSearch2-sc-19kl9m3-0 gzbZOD']//input")).sendKeys(Keys.ENTER);
		Thread.sleep(10000);
	
		List<WebElement> allname = driver.findElements(By.xpath("//div[@class='break-words h-10 w-full']//h3[contains(text(),watermelon)]"));
		
		for (int i = 0; i <allname.size() ; i++) {
		    String melon = allname.get(i).getText();
			FileInputStream fi = new FileInputStream("./src/test/resources//bigBasketPrasad.xlsx");
			Workbook wb = WorkbookFactory.create(fi);
			Sheet sh = wb.getSheet("Sheet1");
			sh.createRow(i).createCell(0).setCellValue(melon);
			FileOutputStream fout= new FileOutputStream("./src/test/resources//bigBasketPrasad.xlsx");
			wb.write(fout);
			wb.close();
		}
		
		FileInputStream fis1 = new FileInputStream("./src/test/resources//bigBasket.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("Sheet1");
		String value = sh.getRow(6).getCell(0).getStringCellValue();
		Thread.sleep(2000);

		WebElement pri = driver.findElement(By.xpath("//h3[text()='"+value+"']/ancestor::div[@class='SKUDeck___StyledDiv-sc-1e5d9gk-0 eA-dmzP']/descendant::span[@class='Label-sc-15v1nk5-0 Pricing___StyledLabel-sc-pldi2d-1 gJxZPQ AypOi']"));
		String p1 = pri.getText();
		
		System.out.println(value+"-->"+p1);
		driver.close();
	}
	

}

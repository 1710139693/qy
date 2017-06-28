package cloud.test;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.base.Predicate;

import common.Base;
import common.Login;

public class TestMe {

	/**
	 * 登录安畅云
	 * @author yangw
	 * @version 1.00
	 */
	WebDriver driver;
	Base pubMeth = new Base();

	
	
	@Test
	public void testMe() throws Exception {
	//  pubMeth.beforeTest(driver);
		driver = pubMeth.beforeTest(driver);
	// 左侧云服务器
		//WebElement cloudServer = driver.findElement(By.xpath("//a[@data-testid = 'sidebarNav-cloud-instance']"));
		//cloudServer.click();
		//Thread.sleep(3000);
		
	//	WebElement cloudServer = driver.findElement(By.xpath("//a[@data-testid = 'sidebarNav-cloud-instance']"));
	//	WebDriverWait wait = new WebDriverWait(driver, 10);
	//	wait.until(ExpectedConditions.elementToBeSelected(cloudServer));	
	//	cloudServer.click();
		
		// 左侧快照备份
		WebElement bk = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-volume']"));
		bk.click();
		Thread.sleep(5000);
		System.out.println("左侧快照备份");

		// 选择华东一区
		WebElement yatai = driver.findElement(By.xpath("//span[@data-testid='region-select-ac1']"));
		yatai.click();
		Thread.sleep(3000);
		System.out.println("华东一区");

		// 选择这个快照备份
		WebElement bkid = driver.findElement(By.xpath("//a[@data-testid='table-row-0-id']"));
		bkid.click();
		Thread.sleep(3000);
			
		//取属性中的价格
		WebElement pricelast = driver.findElement(By.xpath("//div[@class='description']"));
		java.util.List<WebElement> pricelast1 = pricelast.findElements(By.xpath("//div[@class='detail-item']"));
	//	int pricesize pricelast1.size();
		WebElement pricelast2=pricelast1.get(2);
		String priceValue = pricelast2.getText();
	//	WebElement pricelast3 = driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[1]/div[2]/div[3]/div[2]/div/div[1]/div[1]/div[6]/div/dl[1]/dd[3]"));
		//java.util.List<WebElement> pricelast3 = driver.findElements(By.xpath("//div/dl/dd[not(@*)]']"));
		//int pricesize= pricelast3.size();
	//	String priceValue = pricelast3.getText();
		
		//System.out.println("priceValue=" + priceValue);
		
		String pricevalue11 = priceValue.substring(41, priceValue.length() - 32);
		System.out.println("pricevalue11=" + pricevalue11);
		
		// 截取前面，后面元/小时截掉
		//String priceValueQu = priceValue.substring(0, priceValue.length() - 4);
		//Thread.sleep(3000);
		//System.out.println("页面取得的价格为=" + priceValue);
		
	//WebDriverWait(driver, 10).until(new ExpectedCondition<WebElement>() {
	//	public WebElement apply(WebDriver d) {
	//			return d.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-instance']"));
	//		}
	//}).click();
		
		//WebElement cloudServer = driver.findElement(By.xpath("//a[@data-testid = 'sidebarNav-cloud-instance']"));
	//	cloudServer.click();
	//	Thread.sleep(3000);
	System.out.println("helloworld");
		

	}

	private FluentWait<WebDriver> WebDriverWait(WebDriver driver2, int i) {
		
		return null;
	}

}

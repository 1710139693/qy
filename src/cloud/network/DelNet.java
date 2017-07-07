package cloud.network;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import common.Base;

public class DelNet {

	/**
	 * 删除SDN网络
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void delAllSdn() throws Exception {
		driver = pubMeth.beforeTest(driver);
		delAutoSdn("region-select-ac1");
		delAutoSdn("region-select-ac2");
		delAutoSdn("region-select-ac3");
		driver.quit();
	}

	private void delAutoSdn(String ac) throws Exception {

		// sdn
		WebElement sdnnet = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-network']"));
		sdnnet.click();
		Thread.sleep(2000);
		
		// 亚太一区
		WebElement yatai = driver.findElement(By.xpath("//span[@data-testid='" + ac + "']"));
		yatai.click();
		Thread.sleep(2000);
		System.out.println(ac);

		// 判断有没有建出来，取得返回值re
		Base pubMeth = new Base();
		By locator = By.xpath("//tbody[@data-testid='table-row-1']");
		boolean reValue = pubMeth.isElementExsit(driver, locator);
		System.out.println("reValue=" + reValue);

		while (pubMeth.isElementExsit(driver, locator)) {

			System.out.println("next");
			if (reValue = false) {
				break;
			}

			WebElement serverok = driver.findElement(By.xpath("//i[@data-testid='table-head-checkbox']"));
			serverok.click();
			Thread.sleep(2000);
			
			WebElement more = driver.findElement(By.xpath("//span[@data-testid='moreOpts']"));
			more.click();
			Thread.sleep(2000);
			
			WebElement moredel = driver.findElement(By.xpath("//li[@data-testid='moreOpts-deleteSdn']"));
			moredel.click();
			Thread.sleep(5000);
			
			//删除提交
			pubMeth.submit(driver);

		} // while

		// 用例运行结果写入LOG文件
		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
		
		// 如果验证没有定位到这台机器证明已经删除，Pass的结果写入文件
		boolean reValue1 = pubMeth.isElementExsit(driver, locator);
		pubMeth.writeLogDel("DelNet",reValue1);
		
	} // 函数结束

} // 类结束

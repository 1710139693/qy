package cloud.router;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import common.Base;

public class DelRouter {

	/**
	 * 删除云路由器
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void delAllRouter() throws Exception {
		driver = pubMeth.beforeTest(driver);
		delAutoRouter("region-select-ac1");
		delAutoRouter("region-select-ac2");
		delAutoRouter("region-select-ac3");
		driver.close();
	}

	private void delAutoRouter(String ac) throws Exception {

		// router
		WebElement routernet = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-router']"));
		routernet.click();
		Thread.sleep(2000);
		

		// 选择地区
		WebElement yatai = driver.findElement(By.xpath("//span[@data-testid='" + ac + "']"));
		yatai.click();
		Thread.sleep(2000);
		System.out.println(ac);

		// 判断有没有建出来，取得返回值re
		Base pubMeth = new Base();
		By locator = By.xpath("//tbody[@data-testid='table-row-0']");
		boolean reValue = pubMeth.isElementExsit(driver, locator);
		System.out.println("reValue=" + reValue);

		while (pubMeth.isElementExsit(driver, locator)) {

			if (reValue = false) {
				break;
			}

			WebElement serverok = driver.findElement(By.xpath("//i[@data-testid='table-head-checkbox']"));
			serverok.click();
			Thread.sleep(2000);
			

			WebElement more = driver.findElement(By.xpath("//span[@data-testid='moreObt']"));
			more.click();
			Thread.sleep(2000);
			

			WebElement moredel = driver.findElement(By.xpath("//li[@data-testid='moreOpts-deleteRouter']"));
			moredel.click();
			Thread.sleep(5000);
			

			//删除提交
			pubMeth.submit(driver);

		} // while

		// 用例运行结果写入LOG文件
		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
		
		// 如果验证没有定位到这台机器证明已经删除，Pass的结果写入文件
		boolean reValue1 = pubMeth.isElementExsit(driver, locator);
		pubMeth.writeLogDel("DelRouter",reValue1);
		
	} // 函数结束

} // 类结束

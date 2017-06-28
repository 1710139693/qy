package cloud.keypair;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import common.Base;

public class DelKeypair {

	/**
	 * 删除Keypair
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void delKeypairAll() throws Exception {
		driver = pubMeth.beforeTest(driver);
		delAutoKeypair("region-select-ac1");
		delAutoKeypair("region-select-ac2");
		delAutoKeypair("region-select-ac3");
		driver.close();
	}

	private void delAutoKeypair(String ac) throws Exception {

		// Keypair
		WebElement sdnnet = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-keyPair']"));
		sdnnet.click();
		Thread.sleep(2000);

		// 选择区
		WebElement yatai = driver.findElement(By.xpath("//span[@data-testid='" + ac + "']"));
		yatai.click();
		Thread.sleep(2000);
		System.out.println(ac);

		// 判断有没有建出来，取得返回值re
		By locator = By.xpath("//tbody[@data-testid='table-row-0']");
		boolean reValue = pubMeth.isElementExsit(driver, locator);
		System.out.println("reValue=" + reValue);

		while (reValue) {

			if (reValue = false) {
				break;
			}

			WebElement serverok = driver.findElement(By.xpath("//i[@data-testid='table-head-checkbox']"));
			serverok.click();
			Thread.sleep(2000);

			// 删除操作
			pubMeth.moreOptsDel(driver);

			// 删除提交
			pubMeth.submit(driver);

		} // while

		// 用例运行结果写入LOG文件
		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");

		// 如果验证没有定位到这台机器证明已经删除，Pass的结果写入文件
		boolean reValue1 = pubMeth.isElementExsit(driver, locator);
		pubMeth.writeLogDel("DelKeypair", reValue1);

	} // 函数结束

} // 类结束

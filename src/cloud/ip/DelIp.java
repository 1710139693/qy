package cloud.ip;

import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

//import org.apache.bcel.generic.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import common.Login;
import common.Base;

public class DelIp {

	/**
	 * 创建公网IP
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void delAllIp() throws Exception {
		driver = pubMeth.beforeTest(driver);
		delAutoIp("region-select-ac1");
		delAutoIp("region-select-ac2");
		delAutoIp("region-select-ac3");
		driver.close();

	}

	private void delAutoIp(String ac) throws Exception {

		// 左侧公网IP
		WebElement pubip = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-ip']"));
		pubip.click();
		Thread.sleep(2000);
		
		// 选择区
		WebElement yatai = driver.findElement(By.xpath("//span[@data-testid='" + ac + "']"));
		yatai.click();
		Thread.sleep(2000);
		
		// 判断有没有建出来，取得返回值re
		Base pubMeth = new Base();
		By locator = By.xpath("//tbody[@data-testid='table-row-0']");
		boolean reValue = pubMeth.isElementExsit(driver, locator);
		System.out.println("reValue=" + reValue);

		// 如果有公网IP就进行删除操作
		while (pubMeth.isElementExsit(driver, locator)) {

			if (reValue = false) {
				break;
			}

			WebElement serverOk = driver.findElement(By.xpath("//i[@data-testid='table-head-checkbox']"));
			serverOk.click();
			Thread.sleep(2000);
			System.out.println("选择ALL IP");
			
			//删除操作
			pubMeth.DelNoCheckNum(driver);
			
			//删除提交
			pubMeth.submit(driver);

		} // while
		
		// 用例运行结果写入LOG文件
		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
		// 如果验证没有定位到这台机器证明已经删除，Pass的结果写入文件
		boolean reValue1 = pubMeth.isElementExsit(driver, locator);
		pubMeth.writeLogDel("DelIp",reValue1);

		

	}// delautopubip

} // 类结束

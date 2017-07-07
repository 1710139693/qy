package cloud.instance;

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
import org.testng.annotations.Parameters;
import common.Login;
import common.Base;

public class DelInstance {

	/**
	 * 删除云服务器
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void delAllServer() throws Exception {
		driver = pubMeth.beforeTest(driver);
		delServer("region-select-ac1");
		delServer("region-select-ac2");
		driver.quit();
	}

	private void delServer(String ac) throws Exception {

		// 左侧云服务器
		WebElement cloudServer = driver.findElement(By.xpath("//a[@data-testid = 'sidebarNav-cloud-instance']"));
		cloudServer.click();
		Thread.sleep(2000);

		// 选择地区
		WebElement yatai = driver.findElement(By.xpath("//span[@data-testid = '" + ac + "']"));
		yatai.click();
		Thread.sleep(2000);
		System.out.println("亚太一区");

		// 判断有没有建出来，取得返回值re
		Base pubMeth = new Base();
		By locator = By.xpath("//i[@data-testid = 'table-row-0-checkbox']");
		boolean reValue = pubMeth.isElementExsit(driver, locator);
		System.out.println("reValue = " + reValue);

		// 如果有服务器就进行删除操作

		while (reValue) {

			if (reValue = false) {
				break;
			}

			// 如果存在就点击这个资源
			WebElement serverOk = driver.findElement(locator);
			serverOk.click();
			Thread.sleep(2000);
			
			//更多操作
			
			WebElement more = driver.findElement(By.xpath("//span[@data-testid = 'moreOpts']"));
			more.click();
			Thread.sleep(2000);
			
			WebElement moreDel = driver.findElement(By.xpath("//span[@data-testid = 'moreOpts-delete']"));
			moreDel.click();
			Thread.sleep(5000);
			
			WebElement checkNum = driver.findElement(By.xpath("//input[@placeholder = '请输入验证码']"));
			checkNum.clear();
			checkNum.sendKeys("51idc");
			
			// 删除提交
			pubMeth.submit(driver);

		}

		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
		// 用例运行结果写入LOG文件

		// 如果验证没有定位到这台机器证明已经删除，Pass的结果写入文件
		boolean reValue1 = pubMeth.isElementExsit(driver, locator);
		pubMeth.writeLogDel("DelInstance", reValue1);

	} // 函数结束
} // 类结束

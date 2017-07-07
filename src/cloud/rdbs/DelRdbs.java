package cloud.rdbs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import common.Base;

public class DelRdbs {

	/**
	 * 删除云数据库
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void delAllRdbs() throws Exception {
		driver = pubMeth.beforeTest(driver);
		delRdbs("region-select-ac1");
		delRdbs("region-select-ac2");
		delRdbs("region-select-ac3");
		driver.quit();
	}

	private void delRdbs(String ac) throws Exception {

		// 左侧云数据库
		WebElement hdd = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-rdbs']"));
		hdd.click();
		Thread.sleep(2000);
		
		// 华东，亚太一区
		WebElement yatai = driver.findElement(By.xpath("//span[@data-testid='" + ac + "']"));
		yatai.click();
		Thread.sleep(2000);
		System.out.println(ac + "区");

		// 判断有没有建出来，取得返回值re
		Base pubMeth = new Base();
		By locator = By.xpath("//tbody[@data-testid='table-row-0']");
		boolean reValue = pubMeth.isElementExsit(driver, locator);
		System.out.println("reValue=" + reValue);

		// 如果存在就进行删除操作

		while (pubMeth.isElementExsit(driver, locator)) {

			if (reValue = false) {
				break;
			}
			// 选择全选框
			WebElement serverok = driver.findElement(By.xpath("//i[@data-testid='table-head-checkbox']"));
			serverok.click();
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
			

			//删除提交
			pubMeth.submit(driver);

			// 用例运行结果写入LOG文件
			pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
			
			// 如果验证没有定位到这台机器证明已经删除，Pass的结果写入文件
			boolean reValue1 = pubMeth.isElementExsit(driver, locator);
			pubMeth.writeLogDel("delRdbs",reValue1);
			
		} // while结束

	}// 函数结束

} // 类结束

package cloud.group;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import common.Base;

public class CreateGroup {

	/**
	 * 创建防火墙网络
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	WebDriver driver;
	Base pubMeth = new Base();
	int seleArea;
	@Test
	public void createGroup() throws Exception {
		
		driver = pubMeth.beforeTest(driver);
		for (int i = 1; i < 3; i++) {
			pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");

			// 左侧防火墙
			WebElement cloudserver = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-group']"));
			cloudserver.click();
			Thread.sleep(2000);
			
			// 三个区封装调用
			Random RandomseleArea = new Random();
			seleArea = RandomseleArea.nextInt(3);// 为0-1个数
			pubMeth.seleAreaall(driver, seleArea);

			// 新建
			pubMeth.newOne(driver);
			
			// 输入防火墙名称
			pubMeth.inputName(driver, "autogroup");
			
			//选择协议
			WebElement protocol1 = driver.findElement(By.xpath("//span[@data-testid='selectBox-0']"));
			protocol1.click();
			WebElement protocol2 = driver.findElement(By.xpath("//span[@data-testid='selectBox-1']"));
			protocol2.click();
			WebElement protocol3 = driver.findElement(By.xpath("//span[@data-testid='selectBox-2']"));
			protocol3.click();
			WebElement protocol4 = driver.findElement(By.xpath("//span[@data-testid='selectBox-3']"));
			protocol4.click();
			WebElement protocol5 = driver.findElement(By.xpath("//span[@data-testid='selectBox-4']"));
			protocol5.click();
			WebElement protocol6 = driver.findElement(By.xpath("//span[@data-testid='selectBox-5']"));
			protocol6.click();
			WebElement protocol7 = driver.findElement(By.xpath("//span[@data-testid='selectBox-6']"));
			protocol7.click();
			WebElement protocol8 = driver.findElement(By.xpath("//span[@data-testid='selectBox-7']"));
			protocol8.click();
			
			// 提交
			pubMeth.submit(driver);

			// 判断有没有建出来，取得返回值re
			By locator = By.xpath("//tbody[@data-testid='table-row-0']");
			boolean reValue = pubMeth.isElementExsit(driver, locator);
			System.out.println(reValue);

			// 用例运行结果写入LOG文件
		//	boolean valueOk = true;
			pubMeth.writeLog("CreateGroup",reValue);

		} // for

	}// create防火墙

}// class

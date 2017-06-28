package cloud.network;

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

public class CreateNetwork {

	/**
	 * 创建SDN网络
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	WebDriver driver;
	Base pubMeth = new Base();
	int seleArea;
	@Test
	public void createNet() throws Exception {
		driver = pubMeth.beforeTest(driver);
		for (int i = 1; i < 3; i++) {
			pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");

			// 左侧SDN
			WebElement cloudserver = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-network']"));
			cloudserver.click();
			Thread.sleep(2000);
			
			// 三个区封装调用
			Random RandomseleArea = new Random();
			seleArea = RandomseleArea.nextInt(3);// 为0-1个数
			pubMeth.seleAreaall(driver, seleArea);

			// 新建
			pubMeth.newOne(driver);
			
			// 输入SDN名称
			pubMeth.inputName(driver, "autosdn" + i);
			
			// 提交
			pubMeth.submit(driver);

			// 判断有没有建出来，取得返回值re
			By locator = By.xpath("//tbody[@data-testid='table-row-0']");
			boolean reValue = pubMeth.isElementExsit(driver, locator);
			System.out.println(reValue);

			// 用例运行结果写入LOG文件
			boolean valueOk = true;
			pubMeth.writeLog("CreateNetwork",reValue);

		} // for

	}// createsdn

}// class

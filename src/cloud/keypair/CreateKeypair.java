package cloud.keypair;

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

public class CreateKeypair {

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
	public void CreateKeypair() throws Exception {
		
		driver = pubMeth.beforeTest(driver);
		for (int i = 1; i < 3; i++) {
			pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");

			// 左侧Keypair
			WebElement cloudserver = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-keyPair']"));
			cloudserver.click();
			Thread.sleep(2000);
			
			// 三个区封装调用
			Random RandomseleArea = new Random();
			seleArea = RandomseleArea.nextInt(3);// 为0-1个数
			pubMeth.seleAreaall(driver, seleArea);

			// 新建
			pubMeth.newOne(driver);
			
			// 输入Keypair
			//pubMeth.inputName(driver, "autoKeypair");
			WebElement inputName = driver.findElement(By.xpath("//input[@data-testid='name']"));
			inputName.clear();
			inputName.sendKeys("autoKeypair");
			Thread.sleep(2000);
			
			//选择加密方法
			
			WebElement sshkey = driver.findElement(By.xpath("//div[@data-testid='singleChoice-encryptWay-default']"));
			sshkey.click();
			
			int intSshkey = RandomseleArea.nextInt(2);
			if (intSshkey == 1) { 
				WebElement sshkeydown = driver.findElement(By.xpath("//li[@data-testid='singleChoice-encryptWay-ssh-dss']"));
				sshkeydown.click();
				Thread.sleep(2000);
			} else {
				System.out.println("ssh-rsa");
			}
			
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

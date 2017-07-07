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

public class GroupSetting {

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
	public void GroupSetAll() throws Exception {
		
		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
		driver = pubMeth.beforeTest(driver);
		seleArea = 0;
		CreateGroup(seleArea);
		GroupSet(seleArea);
		
		driver.quit();
	}
		
	private void GroupSet(int seleArea) throws Exception {
		// 左侧防火墙
		WebElement cloudserver = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-group']"));
		cloudserver.click();
		Thread.sleep(10000);
		
		//选择华东一区
		
		pubMeth.seleAreaall(driver, seleArea);
		
		// 判断有没有建出来，取得返回值re
		By locator = By.xpath("//a[@data-testid='table-row-0-id']");
		boolean reValueSetting = pubMeth.isElementExsit(driver, locator);
		System.out.println(reValueSetting);
		
		if (reValueSetting) {
		// 选择云路由器
		WebElement selectrouter = driver.findElement(locator);
		selectrouter.click();
		Thread.sleep(2000);
		
		} else {
			System.out.println("没有防火墙");
		}
		//下行规则
		// 新建
		pubMeth.newOnecreat(driver);
		// 输入名称
		WebElement inputname = driver.findElement(By.xpath("//input[@data-testid='name']"));
		inputname.clear();
		inputname.sendKeys("autorule");
		Thread.sleep(2000);
		
		//上行规则或者下行规则
		Random numRule = new Random();
		int numRuleOk = numRule.nextInt(2);
		String[] testid = new String[2]; 	
		testid[0]="radio-direction-0";
		testid[1]="radio-direction-1";
		
		WebElement rule = driver.findElement(By.xpath("//span[@data-testid='"+testid[numRuleOk]+"']"));
		rule.click();
		Thread.sleep(2000);
		
		//允许或者拒绝
		Random numAllow = new Random();
		int numAllowOk = numAllow.nextInt(2);
		String[] testida = new String[2]; 	
		testida[0]="radio-action-0";
		testida[1]="radio-action-1";
		
		WebElement action = driver.findElement(By.xpath("//span[@data-testid='"+testida[numAllowOk]+"']"));
		action.click();
		Thread.sleep(2000);
		//起端口	
		WebElement startPort = driver.findElement(By.xpath("//input[@data-testid='startPort']"));
		startPort.clear();
		startPort.sendKeys("27016");
		//止端口	
		WebElement endPort = driver.findElement(By.xpath("//input[@data-testid='endPort']"));
		endPort.clear();
		endPort.sendKeys("27016");
		
		//提交
		pubMeth.submit(driver);
		System.out.println("规则提交");
			
		// 应用修改
		pubMeth.appMdf(driver);
		
	}
	
	private void CreateGroup(int seleArea) throws Exception {
		
		for (int i = 1; i < 2; i++) {
			pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");

			// 左侧防火墙
			WebElement cloudserver = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-group']"));
			cloudserver.click();
			Thread.sleep(2000);
			
			// 三个区封装调用
			//Random RandomseleArea = new Random();
			//seleArea = RandomseleArea.nextInt(3);// 为0-1个数
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

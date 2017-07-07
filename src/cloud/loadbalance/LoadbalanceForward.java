package cloud.loadbalance;

import org.openqa.selenium.support.ui.Select;

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

public class LoadbalanceForward {

	/**
	 * 创建云负载均衡
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	//此脚本计算了公网IP的价格，所以不能有未使用的公网IP资源存在
	//String priceValue;
	//boolean valueOk = false;
	int seleArea = 0; // seleArea=1时亚太一区

	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void LoadbalanceForward() throws Exception {
		
		driver = pubMeth.beforeTest(driver);
		
		// 左侧云负载均衡
		WebElement cloudLoadbalance = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-loadBalancer']"));
		cloudLoadbalance.click();
		Thread.sleep(2000);
		
		// 选择三个区中的一个
		Random RandomseleArea = new Random();
		seleArea = RandomseleArea.nextInt(3);// 为0-1个数
		pubMeth.seleAreaall(driver, seleArea);

		// 选择转发策略
		WebElement forward = driver.findElement(By.xpath("//a[@data-testid='tabs-1']"));
		forward.click();
		Thread.sleep(2000);
		
		//新建转发策略
		pubMeth.newOnecreat(driver);
		
		// 输入名称
		WebElement forwardname = driver.findElement(By.xpath("//input[@data-testid='name']"));
		forwardname.clear();
		forwardname.sendKeys("autoforward");
		Thread.sleep(2000);
		
		//提交
		pubMeth.submit(driver);
		System.out.println("提交转发策略");
		
		// 判断有没有建出来，取得返回值re
		By locator = By.xpath("//a[@data-testid='table-row-0-id']");
		boolean reValue = pubMeth.isElementExsit(driver, locator);
		System.out.println(reValue);
		
		if (reValue) {
			//选择这个转发策略
			WebElement autoforward = driver.findElement(locator);
			autoforward.click();
			//新建规则
			pubMeth.newOnecreat(driver);
			//输入规则名称
			WebElement rulename = driver.findElement(By.xpath("//input[@data-testid='val']"));
			rulename.clear();
			rulename.sendKeys("autorule");
			Thread.sleep(2000);
			//提交
			pubMeth.submit(driver);
			System.out.println("提交规则");
			
			// 应用修改
			pubMeth.appMdf(driver);
			
		} else {
			System.out.println("转发策略不存在");
		}
		
		driver.quit();	
		
	}
	

}

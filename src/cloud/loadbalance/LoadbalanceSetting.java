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

public class LoadbalanceSetting {

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
	int maxConnNum = 0; // 并发数
	int seleNet = 0; // 网络
	int size = 1; // 带宽
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void LoadbalanceSet() throws Exception {
		driver = pubMeth.beforeTest(driver);
		CreateLoadbalance();
		Thread.sleep(20000);
		// 判断有没有建出来，取得返回值re
		By locator = By.xpath("//a[@data-testid='table-row-0-id']");
		boolean reValue = pubMeth.isElementExsit(driver, locator);
		System.out.println(reValue);

		// 选择这个负载
		if (reValue) {
			
			WebElement selectrouter = driver.findElement(locator);
			selectrouter.click();
			Thread.sleep(2000);
		
		} else {
		
			System.out.println("没有负载");
			
		}
		
		// 选择添加监听器
		WebElement add = driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[1]/div[2]/div[3]/div[2]/div[1]/div/div[2]/div[2]/div/span/span"));
		add.click();
		Thread.sleep(2000);
		
		// 监听器name
		WebElement listen = driver.findElement(By.xpath("//input[@data-testid='name']"));
		listen.clear();
		listen.sendKeys("autohttp");
		Thread.sleep(2000);
				
		pubMeth.submit(driver);
		
		// 应用修改
		pubMeth.appMdf(driver);
		
	}
	
	
	private void CreateLoadbalance() throws Exception {
		
		for (int i = 1; i < 2; i++) {
			pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
			
			// 左侧云负载均衡
			WebElement cloudLoadbalance = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-loadBalancer']"));
			cloudLoadbalance.click();
			Thread.sleep(2000);
			
			// 选择区
			Random RandomseleArea = new Random();
			seleArea = RandomseleArea.nextInt(3);// 为0-1个数
			pubMeth.seleAreaall(driver, seleArea);

			// 新建
			WebElement creat = driver.findElement(By.xpath("//span[@class='color-primary']"));
			creat.click();
			Thread.sleep(3000);
			
			// 按需
			pubMeth.anXu(driver);
			
			// 输入负载均衡名称
			pubMeth.inputName(driver, "autoloadbalance" + i);
			
			// 最大连接数
			Random RandommaxConnNum = new Random();
			maxConnNum = RandommaxConnNum.nextInt(3);// 为0-1个数
			switch (maxConnNum) {
			case 0:
				WebElement seleType = driver.findElement(By.xpath("//span[@data-testid='radio-num-0']"));
				seleType.click();
				Thread.sleep(3000);
				System.out.println("20000");
				pubMeth.rwFile("并发数=", "20000", "");
				break;
			case 1:
				WebElement seleType1 = driver.findElement(By.xpath("//span[@data-testid='radio-num-1']"));
				seleType1.click();
				Thread.sleep(3000);
				System.out.println("40000");
				pubMeth.rwFile("并发数=", "40000", "");
				break;
			case 2:
				WebElement seleType2 = driver.findElement(By.xpath("//span[@data-testid='radio-num-2']"));
				seleType2.click();
				Thread.sleep(3000);
				System.out.println("100000");
				pubMeth.rwFile("并发数=", "100000", "");
				break;
			}

			/*/ 网络
			Random RandomseleNet = new Random();
			seleNet = RandomseleNet.nextInt(2);// 为0-1个数
			if (seleNet == 0) {
				WebElement seletype = driver.findElement(By.xpath("//span[@data-testid='radio-type-0']"));
				seletype.click();
				Thread.sleep(3000);
				pubMeth.rwFile("网络为", "公网", "");

				// 带宽
				WebElement storage = driver.findElement(By.xpath("//input[@data-testid='customSlideNum']"));
				storage.clear();
				// 1-5M
				int max1 = 5;
				int min1 = 1;
				Random randoma = new Random();
				size = randoma.nextInt(max1) % (max1 - min1 + 1) + min1;
				storage.clear();

				storage.sendKeys(+size + "");
				Thread.sleep(3000);
				System.out.println("输入带宽" + size + "M");
				String strsize = String.valueOf(size);
				pubMeth.rwFile("带宽=", strsize, "M");

			} else {
				WebElement seletype = driver.findElement(By.xpath("//span[@data-testid='radio-type-1']"));
				seletype.click();
				Thread.sleep(3000);
				pubMeth.rwFile("网络为", "路由子网", "");
			}

			// 取页面显示的价格
			//pubMeth.UIprice(driver);
			*/
			// 提交
			pubMeth.submit(driver);

			// 判断有没有建出来，取得返回值re
			By locator = By.xpath("//i[@data-testid='table-row-0-checkbox']");
			boolean reValue = pubMeth.isElementExsit(driver, locator);
			System.out.println(reValue);

			// 用例运行结果写入LOG文件
			
			pubMeth.writeLog("Createloadbalance",reValue);


		} // for

	}// 创建服务器函数结束
	
	

}

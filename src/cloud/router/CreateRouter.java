package cloud.router;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import common.Login;
import common.Base;

public class CreateRouter {

	/**
	 * 创建云路由器
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	// 此脚本计算了公网IP的价格，所以不能有未使用的公网IP资源存在
	//String priceValue;
	//boolean valueOk = false;
	int seleArea = 0; // seleArea=1时亚太一区
	int typeSmall = 0; // 类型选择
	int size = 1; // 带宽
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void createRouterAll() throws Exception {
		
		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
		driver = pubMeth.beforeTest(driver);
		//createRouter(0);
		seleArea = 2;
		createRouter(seleArea);
		//createRouter(2);
		driver.close();
		
	}
	
	private void createRouter(int seleArea) throws Exception {
		
		
		for (int i = 1; i < 2; i++) {
			// 左侧云路由器
			WebElement cloudserver = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-router']"));
			cloudserver.click();
			Thread.sleep(2000);

			// 新建
			WebElement creat = driver.findElement(By.xpath("//span[@class='color-primary']"));
			creat.click();
			Thread.sleep(2000);
			
			// 按需
			// 先定位div ul
			WebElement anxu = driver.findElement(By.xpath("//div[@class='mt-20 tabs-item']"));
			WebElement anxu1 = anxu.findElement(By.xpath("//li[@class='main-item']"));
			anxu1.click();
			Thread.sleep(3000);
			pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");

			// 选择三个区中的一个
			//Random RandomseleArea = new Random();
			//seleArea = RandomseleArea.nextInt(3);// 为0-1个数
			pubMeth.seleAreaall(driver, seleArea);
			System.out.println("seleArea=" + seleArea);
			// 输入路由器名称
			
			pubMeth.inputName(driver, "autorouter" + i);
			
			// 类型
			Random RandomtypeSmall = new Random();
			typeSmall = RandomtypeSmall.nextInt(2);// 为0-1个数
			if (typeSmall == 0) {
				WebElement seletype = driver.findElement(By.xpath("//span[@data-testid='radio-type-0']"));
				seletype.click();
				Thread.sleep(3000);
				System.out.println("类型");
			} else {
				WebElement seletype = driver.findElement(By.xpath("//span[@data-testid='radio-type-1']"));
				seletype.click();
				Thread.sleep(3000);
			}

			// 带宽
			WebElement storage = driver.findElement(By.xpath("//input[@data-testid='customSlideNum']"));
			storage.clear();
			// 1-5M
			int max1 = 5;
			int min1 = 1;
			Random randoma = new Random();
			size = randoma.nextInt(max1) % (max1 - min1 + 1) + min1;
			System.out.println("size=" + size);
			storage.clear();

			storage.sendKeys(+size + "");
			Thread.sleep(3000);
			System.out.println("输入带宽" + size + "M");
			String strsize = String.valueOf(size);
			pubMeth.rwFile("带宽=", strsize, "M");

			// 取页面显示的价格
			pubMeth.UIprice(driver);
			
			// 提交
			WebElement submit = driver.findElement(By.xpath("//div[@data-testid='buy-button']"));
			submit.click();
			Thread.sleep(10000);
			System.out.println("提交");

			calculateSum();

			// 判断有没有建出来，取得返回值re
			By locator = By.xpath("//i[@data-testid='table-row-0-checkbox']");
			boolean reValue = pubMeth.isElementExsit(driver, locator);
			System.out.println(reValue);

			// 用例运行结果写入LOG文件
			
			pubMeth.writeLog("CreateRouter",reValue);


		} // for

	}// 创建服务器函数结束

	/**
	 * 根据所选计算价格
	 * 
	 * @author yangw
	 * @version 1.00
	 * @throws Exception 
	 */

	private void calculateSum() throws Exception {// c表示单价 ，e表示单位

		double sum = 0;
		switch (seleArea) {
		case 0://华东一区
			if (typeSmall == 0) {
				sum = 0.0412 + (0.0278 * (size - 1));
				System.out.println("一"); 
			} else {
				sum = 0.4078 + (0.0278 * (size - 1));
				System.out.println("二"); 
			}
			break;
		case 1:// 亚太一区
			if (typeSmall == 0) {
				sum = 0.0403 + (0.0269 * (size - 1));
				System.out.println("三"); 
			} else {
				sum = 0.4069 + (0.0269 * (size - 1));
				System.out.println("四"); 
			}
			break;
		case 2: // 华东二区
			if (typeSmall == 0) {
				sum = 0.0537 + (0.0403 * (size - 1));
				System.out.println("五"); 
			} else {
				sum = 0.4203 + (0.0403 * (size - 1));
				System.out.println("六"); 
			}
			break;
		}

		// 取小数点后四位
		String sumTo = String.format("%.4f", sum);
		pubMeth.calcuCheck(sumTo);
	}// 计算函数
}

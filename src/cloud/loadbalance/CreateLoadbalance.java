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

public class CreateLoadbalance {

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
	public void createLoadbalance() throws Exception {
		driver = pubMeth.beforeTest(driver);
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

			// 网络
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
			pubMeth.UIprice(driver);
			
			// 提交
			pubMeth.submit(driver);

			calculateSum();

			// 判断有没有建出来，取得返回值re
			By locator = By.xpath("//i[@data-testid='table-row-0-checkbox']");
			boolean reValue = pubMeth.isElementExsit(driver, locator);
			System.out.println(reValue);

			// 用例运行结果写入LOG文件
			
			pubMeth.writeLog("Createloadbalance",reValue);


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
		case 0: // 华东一区
			if (seleNet == 0) {// 公网
				switch (maxConnNum) {// 并发数不同
				case 0:
					sum = 0.0278 + (0.0278 * (size - 1));
					break;
				case 1:
					sum = 0.5838 + (0.0278 * (size - 1));
					break;
				case 2:
					sum = 1.4178 + (0.0278 * (size - 1));
					break;
				}

			} else {// 路由子网
				switch (maxConnNum) {// 并发数不同
				case 0:
					sum = 0.0700;
					break;
				case 1:
					sum = 0.5560;
					break;
				case 2:
					sum = 1.3900;
					break;
				}
			}
			break;
		case 1:// 亚太一区
			if (seleNet == 0) {// 公网
				switch (maxConnNum) {// 并发数不同
				case 0:
					sum = 0.0269 + (0.0269 * (size - 1));
					break;
				case 1:
					sum = 0.5829 + (0.0269 * (size - 1));
					break;
				case 2:
					sum = 1.4169 + (0.0269 * (size - 1));
					break;
				}

			} else {// 路由子网
				switch (maxConnNum) {// 并发数不同
				case 0:
					sum = 0.0700;
					break;
				case 1:
					sum = 0.5560;
					break;
				case 2:
					sum = 1.3900;
					break;
				}
			}
			break;
		case 2: // 华东二区
			if (seleNet == 0) {// 公网
				switch (maxConnNum) {// 并发数不同
				case 0:
					sum = 0.0403 + (0.0403 * (size - 1));
					break;
				case 1:
					sum = 0.5963 + (0.0403 * (size - 1));
					break;
				case 2:
					sum = 1.4303 + (0.0403 * (size - 1));
					break;
				}

			} else {// 路由子网
				switch (maxConnNum) {// 并发数不同
				case 0:
					sum = 0.0700;
					break;
				case 1:
					sum = 0.5560;
					break;
				case 2:
					sum = 1.3900;
					break;
				}
			}
			break;
		}

		// 取小数点后四位
		String sumTo = String.format("%.4f", sum);
		pubMeth.calcuCheck(sumTo);
	}// 计算函数
}

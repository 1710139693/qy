package cloud.prepay;

import org.openqa.selenium.support.ui.Select;
import common.Login;
import common.Base;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

//import org.apache.bcel.generic.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

public class CreateIpPrepay {

	/**
	 * 创建公网IP
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	WebDriver driver;
	Base pubMeth = new Base();
	int numIp = 0;// 数量
	int sizeIp = 0;// 容量
	int netYatai = 0;// c = 1选择国际大陆优化
	int seleArea = 2;// seleArea = 1时亚太一区
	//String priceValue;
	//boolean valueOk = false;

	@Test
	public void cloudIp() throws Exception {
		driver = pubMeth.beforeTest(driver);
		for (int i = 1; i < 2; i++) {
			pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
			// 左侧公网IP
			WebElement cloudIp = driver.findElement(By.xpath("//a[@data-testid = 'sidebarNav-cloud-ip']"));
			cloudIp.click();
			Thread.sleep(2000);

			// 选择区
			Random RandomseleArea = new Random();
			seleArea = RandomseleArea.nextInt(3);// 为0-2个数
			pubMeth.seleAreaall(driver, seleArea);
			System.out.println("seleArea为第" + seleArea + "个区");

			// 新建
			WebElement creat = driver.findElement(By.xpath("//span[@class = 'color-primary']"));
			creat.click();
			Thread.sleep(2000);

			// 按需
			//pubMeth.anXu(driver);

			// IP数量
			WebElement num = driver.findElement(By.xpath("//input[@class = 'num-wrapper']"));
			num.clear();

			int max = 4;
			int min = 1;
			Random randomIp = new Random();
			numIp = randomIp.nextInt(max) % (max - min + 1) + min;

			num.sendKeys(+numIp + "");
			Thread.sleep(2000);
			System.out.println("选择" + numIp + "个IP");
			String strnumIp = String.valueOf(numIp);
			pubMeth.rwFile("IP =", strnumIp, "个");

			// 容量

			WebElement storage = driver.findElement(By.xpath("//input[@data-testid = 'customSlideNum']"));
			storage.clear();
			// 1-5M
			int max1 = 5;
			int min1 = 1;
			Random randoma = new Random();
			sizeIp = randoma.nextInt(max1) % (max1 - min1 + 1) + min1;
			System.out.println("sizeIp =" + sizeIp);
			storage.clear();

			storage.sendKeys(+sizeIp + "");
			Thread.sleep(3000);
			System.out.println("输入容量" + sizeIp + "M");
			String strsizeIp = String.valueOf(sizeIp);
			pubMeth.rwFile("容量 =", strsizeIp, "M");

			if (seleArea == 1) {
				Random randomcc = new Random();
				netYatai = randomcc.nextInt(2);
				if (netYatai == 1) {

					// 下拉框
					WebElement lineValue = driver.findElement(By.xpath("//div[@data-testid = 'singleChoice-default']"));
					lineValue.click();
					Thread.sleep(2000);

					// 选择国际大陆优化
					WebElement selectLine = driver
							.findElement(By.xpath("//li[@data-testid = 'singleChoice-eipg-aofiim2z']"));
					selectLine.click();
					Thread.sleep(2000);
					System.out.println("选择带宽");

				} else {
					System.out.println("国际带宽");
				}
			}

			// 取页面显示的价格
			pubMeth.UIprice(driver);
			
			// 提交
			pubMeth.submit(driver);
			
			// 确认提交
			pubMeth.subcfm(driver);
			
			// 确认付款
			pubMeth.paycfm(driver);
			
			//返回列表
			WebElement returnList = driver.findElement(By.xpath("//span[@class='single-button primary']"));
			returnList.click();
			Thread.sleep(10000);
			System.out.println("返回列表");

			// 判断有没有建出来，取得返回值re
			By locator = By.xpath("//i[@data-testid = 'table-row-0-checkbox']");
			boolean reValue = pubMeth.isElementExsit(driver, locator);
			System.out.println(reValue);

			calculateSum();

			// 用例运行结果写入LOG文件
			
			pubMeth.writeLog("createIpId",reValue);

		} // for

	}

	/**
	 * 计算价格
	 * 
	 * @author yangw
	 * @version 1.00
	 * @throws Exception 
	 */

	private void calculateSum() throws Exception {

		double sum = 0;
		if (seleArea == 0) {// 华东一区的算法

			sum = numIp * sizeIp * 0.0278;

		} else if (seleArea == 1) {// 亚太国际带宽的价格
			if (netYatai == 0) {
				sum = numIp * sizeIp * 0.0269;
			} else {
				sum = numIp * sizeIp * 0.0672;// 亚太国际大陆优化的价格

			}

		} else if (seleArea == 2) {// 华东二区的算法
			sum = numIp * sizeIp * 0.0403;
		}

		double sumPrepay = sum*24*30;
		//取小数点后四位
		String sumTo = String.format("%.4f", sumPrepay); 
		pubMeth.calcuCheck(sumTo);

	}// 计算结束

}// 类结束

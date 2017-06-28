package cloud.volume;

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

public class CreateVolumeId {
	/**
	 * 创建云硬盘
	 * 
	 * @author yangw
	 * @version 1.00
	 * @throws Exception
	 */
	WebDriver driver;
	Base pubMeth = new Base();
//	String priceValue;
	//boolean valueOk = false;
	int seleArea = 0; // seleArea=1时亚太一区
	int size = 1;
	int numRandom = 0;

	@Test
	public void createAllVolume() throws Exception {
		driver = pubMeth.beforeTest(driver);
		createVolume("radio-0", "0", "auto_perf");
		createVolume("radio-1", "00", "auto_cap");
		// driver.close();
	}

	/**
	 * 创建云硬盘
	 * 
	 * @author yangw
	 * @version 1.00
	 * @param 类型
	 * @param 单位
	 * @param 名字
	 * @throws Exception 
	 */
	private void createVolume(String type, String digit, String hddname) throws Exception {

		for (int i = 1; i < 2; i++) {
			// 左侧云硬盘
			WebElement cloudVolume = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-volume']"));
			cloudVolume.click();
			Thread.sleep(2000);
			

			// 华东一区或者亚太一区
			pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");

			Random RandomseleArea = new Random();
			seleArea = RandomseleArea.nextInt(2);// 为0-1个数
			if (seleArea == 0) {
				WebElement yatai = driver.findElement(By.xpath("//span[@data-testid='region-select-ac1']"));
				yatai.click();
				Thread.sleep(3000);
				System.out.println("华东一区");
				pubMeth.rwFile("华东一区", "", "");
			} else {
				WebElement huadong = driver.findElement(By.xpath("//span[@data-testid='region-select-ac2']"));
				huadong.click();
				Thread.sleep(3000);
				System.out.println("亚太一区");
				pubMeth.rwFile("亚太一区", "", "");
			}

			// 新建
			// WebElement
			// creat=driver.findElement(By.xpath("//span[@class='color-primary']"));
			pubMeth.newOne(driver);
			
			// 按需
			pubMeth.anXu(driver);

			// 输入硬盘名称
			pubMeth.inputName(driver, hddname + i);
			

			// 硬盘数量
			WebElement num = driver.findElement(By.xpath("//input[@data-testid='selectNum-input']"));
			num.clear();

			int max = 1;
			int min = 1;
			Random randomhdd = new Random();
			numRandom = randomhdd.nextInt(max) % (max - min + 1) + min;

			num.sendKeys(+numRandom + "");
			Thread.sleep(2000);
			System.out.println("选择" + numRandom + "个硬盘");
			String strnumRandom = String.valueOf(numRandom);
			pubMeth.rwFile("硬盘=", strnumRandom, "个");

			// 类型
			WebElement seletype = driver.findElement(By.xpath("//span[@data-testid='" + type + "']"));
			seletype.click();
			Thread.sleep(3000);
			System.out.println("type=" +type);

			// 容量

			WebElement storage = driver.findElement(By.xpath("//input[@data-testid='customSlideNum']"));
			storage.clear();

			int max1 = 10;
			int min1 = 3;
			Random randoma = new Random();
			size = randoma.nextInt(max1) % (max1 - min1 + 1) + min1;
			storage.clear();

			System.out.println("d1=" + digit);
			storage.sendKeys(+size + digit);
			Thread.sleep(3000);
			System.out.println("输入容量" + size + digit);
			String stra = String.valueOf(size);
			pubMeth.rwFile("容量=", stra, digit);

			// 取页面显示的价格
			pubMeth.UIprice(driver);
			
			// 提交
			WebElement submit = driver.findElement(By.xpath("//span[@data-testid='pop-model-confirm']"));
			submit.click();
			Thread.sleep(10000);
			System.out.println("提交");

			// 判断有没有建出来，取得返回值re
			By locator = By.xpath("//i[@data-testid='table-row-0-checkbox']");
			boolean reValue = pubMeth.isElementExsit(driver, locator);
			System.out.println("reValue=" + reValue);
			int digitMul = 0;
			double basePrice = 0;
			if (type == "radio-0") {
				basePrice = 0.0134;
				digitMul = 1;
				

			} else if (type == "radio-1") {
				basePrice = 0.004;
				digitMul = 10;

			}
			calculateSum(basePrice, digitMul);
			pubMeth.writeLog("createVolume",reValue);

		} // for

	}// 创建服务器函数结束

	/**
	 * 根据选择计算硬盘的价格
	 * 
	 * @author yangw
	 * @version 1.00
	 * @throws Exception 
	 */
	private void calculateSum(double basePrice, int digitMul) throws Exception {// basePrice表示单价
																			// ，digitMul表示单位

		double sum = 0;
		// numRandom是数量，size是容量
		sum = basePrice * size * numRandom * digitMul;

		// 取小数点后四位
		String sumTo = String.format("%.4f", sum);
		pubMeth.calcuCheck(sumTo);
		
	}// 计算函数
}

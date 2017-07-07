package cloud.recycle;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import common.Base;

public class RecycleVolume {

	/**
	 * 创建云服务器
	 * 
	 * @author yangw
	 * @version 1.00
	 */

	int seleArea = 0; // seleArea = 1时亚太一区
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void createRecycle() throws Exception {
		
		driver = pubMeth.beforeTest(driver);
		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");

		CreateVolumeForRecycle();

		// 左侧云硬盘
		WebElement cloudserver = driver.findElement(By.xpath("//a[@data-testid = 'sidebarNav-cloud-volume']"));
		cloudserver.click();
		Thread.sleep(3000);
		
		// 选择华东一区
		WebElement yatai = driver.findElement(By.xpath("//span[@data-testid = 'region-select-ac1']"));
		yatai.click();
		Thread.sleep(3000);
		System.out.println("华东一区");
		pubMeth.rwFile("区域为", "华东一区", "");
		
		// 判断服务器有没有建出来，取得返回值re
		
		By locator = By.xpath("//i[@data-testid = 'table-row-0-checkbox']");
		boolean reValue = pubMeth.isElementExsit(driver, locator);
		System.out.println("reValue = " + reValue);

		if (reValue) {
			// if (re = false) {break;}

			// 选择建好硬盘
			WebElement serverok = driver.findElement(locator);
			serverok.click();
			Thread.sleep(2000);

			//更多操作
	
			pubMeth.moreOptsDel(driver);
			
			// 提交
			pubMeth.submit(driver);
			
			// 左侧回收站
			WebElement image = driver.findElement(By.xpath("//a[@data-testid = 'sidebarNav-cloud-recyclebin']"));
			image.click();
			Thread.sleep(2000);
			System.out.println("左侧回收站");

			// 选择华东一区
			WebElement huadong2 = driver.findElement(By.xpath("//span[@data-testid = 'region-select-ac1']"));
			huadong2.click();
			Thread.sleep(2000);
			System.out.println("华东一区");
			
			// 选择云硬盘
			WebElement hdd = driver.findElement(By.xpath("//a[@data-testid = 'tabs-1']"));
			hdd.click();
			Thread.sleep(2000);
			System.out.println("华东一区");
			
			
			// 判断有没有建出来，取得返回值rebk
			By locatorbk = By.xpath("//tbody[@data-testid = 'table-row-0']");
			boolean reValue1 = Base.isElementExsit(driver, locatorbk);
			System.out.println("reValue1=" + reValue1);

			// 用例运行结果写入LOG文件
			String cases = "Recycle";
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置日期格式
			// 如果已经建出来并且核对的价格正确，Pass的结果写入文件
			if (reValue1) {
				pubMeth.rwFile(cases, df.format(new Date()), "Pass");
			} else {
				pubMeth.rwFile(cases, df.format(new Date()), "Fail");
			}
		} else {
			System.out.println("回收站没有云硬盘");
		}

		driver.quit();
	}

	/**
	 * 创建云硬盘
	 * 
	 * @author yangw
	 * @version 1.00
	 * @throws Exception 
	 */
	private void CreateVolumeForRecycle() throws Exception {

		for (int i = 1; i < 2; i++) {
			// 左侧云硬盘
			WebElement cloudVolume = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-volume']"));
			cloudVolume.click();
			Thread.sleep(2000);
			

			// 选择区域
			pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
		//	Random RandomseleArea = new Random();
		//	seleArea = RandomseleArea.nextInt(3);// 为0-2个数
			seleArea = 0;
			pubMeth.seleAreaall(driver, seleArea);	

			// 新建
			// WebElement
			// creat=driver.findElement(By.xpath("//span[@class='color-primary']"));
			pubMeth.newOne(driver);
			
			// 按需
			pubMeth.anXu(driver);

			// 输入硬盘名称
			pubMeth.inputName(driver, "autohddforRecycle");
			
			// 硬盘数量
			WebElement num = driver.findElement(By.xpath("//input[@data-testid='selectNum-input']"));
			num.clear();

			int max = 1;
			int min = 1;
			Random randomhdd = new Random();
			int numRandom = randomhdd.nextInt(max) % (max - min + 1) + min;

			num.sendKeys(+numRandom + "");
			Thread.sleep(2000);
			System.out.println("选择" + numRandom + "个硬盘");
			String strnumRandom = String.valueOf(numRandom);
			pubMeth.rwFile("硬盘=", strnumRandom, "个");

			// 类型
			WebElement seletype = driver.findElement(By.xpath("//span[@data-testid='radio-1']"));
			seletype.click();
			Thread.sleep(3000);
			
	
			// 容量
			WebElement storage = driver.findElement(By.xpath("//input[@data-testid='customSlideNum']"));
			storage.clear();
			int size = 1;
			if (seleArea == 0) {
				int max1 = 10;
				int min1 = 3;
				Random randoma = new Random();
				size = randoma.nextInt(max1) % (max1 - min1 + 1) + min1;
			} else if (seleArea == 1){
				int max1 = 50;
				int min1 = 1;
				Random randoma = new Random();
				size = randoma.nextInt(max1) % (max1 - min1 + 1) + min1;
			} else if (seleArea == 2){
				int max1 = 100;
				int min1 = 1;
				Random randoma = new Random();
				size = randoma.nextInt(max1) % (max1 - min1 + 1) + min1;
			}
			
			storage.clear();
			String digit = "0";
			System.out.println("d1=" + digit);
			storage.sendKeys(+size + digit);
			Thread.sleep(5000);
			
			
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
			
			pubMeth.writeLog("createVolume",reValue);

		} // for

	}

	

}

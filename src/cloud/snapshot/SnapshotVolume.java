package cloud.snapshot;

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
@Test
public class SnapshotVolume {
	
	/**
	 * 创建快照备份
	 * 
	 * @author yangw
	 * @version 1.00
	 */

	String priceValue;
	String priceValueQu;
	boolean valueOk = false;
	int typeRandom = 0; // 选择类型
	int seleArea = 0; // seleArea=1时亚太一区
	int size = 1;// 容量选择3-10
	int numRandom = 0;// 数量选择，目前选1
	String digit; // 选择是0还是00
	String type;// 类型
	WebDriver driver;
	Base pubMeth = new Base();

	
	public SnapshotVolume() throws Throwable {

		driver = pubMeth.beforeTest(driver);
		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
		createVolume();

		// 左侧云硬盘
		WebElement cloudrbs = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-volume']"));
		cloudrbs.click();
		Thread.sleep(3000);
		
		// 选择华东一区
		WebElement yatai = driver.findElement(By.xpath("//span[@data-testid='region-select-ac1']"));
		yatai.click();
		Thread.sleep(3000);
		System.out.println("华东一区");
		pubMeth.rwFile("区域为", "华东一区", "");

		// 判断有没有建出来，取得返回值re
		Base pubMeth = new Base();
		By locator = By.xpath("//i[@data-testid='table-row-0-checkbox']");
		boolean reValue = pubMeth.isElementExsit(driver, locator);
		System.out.println("reValue=" + reValue);

		if (pubMeth.isElementExsit(driver, locator)) {
			// if (reValue = false){break;}

			// 选择建好的硬盘
			WebElement serverok = driver.findElement(locator);
			serverok.click();
			Thread.sleep(2000);
			
			// 更多操作
			WebElement more = driver.findElement(By.xpath("//span[@data-testid='moreOpts']"));
			more.click();
			Thread.sleep(2000);
			
			// 创建快照备份
			WebElement createbk = driver.findElement(By.xpath("//li[@data-testid='moreOpts-createSnapshot']"));
			createbk.click();
			Thread.sleep(2000);
			

			// 输入快照备份名称
			WebElement servername = driver.findElement(By.xpath("//input[@data-testid='createSnapShot-name']"));
			servername.sendKeys("hddbk");
			Thread.sleep(3000);
			

			// 提交
			WebElement submit = driver.findElement(By.xpath("//span[@data-testid='pop-model-confirm']"));
			submit.click();
			Thread.sleep(20000);
			System.out.println("提交");

			// 左侧快照备份
			WebElement bk = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-snapshot']"));
			bk.click();
			Thread.sleep(5000);
			System.out.println("左侧快照备份");

			// 判断有没有建出来，取得返回值rebk

			By locatorbk = By.xpath("//tbody[@data-testid='table-row-0']");
			boolean reValue1 = Base.isElementExsit(driver, locatorbk);
			System.out.println("reValue1=" + reValue1);

			// 选择这个快照备份
			WebElement bkid = driver.findElement(By.xpath("//span[@data-testid='table-row-0-id']"));
			bkid.click();
			Thread.sleep(5000);
			

			// 取属性中的价格
			WebElement pricelast = driver.findElement(By.xpath("//div[@class='description']"));
			java.util.List<WebElement> pricelast1 = pricelast.findElements(By.xpath("//div[@class='detail-item']"));
		//	int pricesize pricelast1.size();
			WebElement pricelast2=pricelast1.get(2);
			priceValue = pricelast2.getText();
			priceValueQu = priceValue.substring(41, priceValue.length() - 32);
			System.out.println("页面取得的价格为 =" + priceValueQu);
			pubMeth.rwFile("页面价格 =", priceValueQu, "");
			
			
			// 选择增备份
			WebElement addbk = driver.findElement(By.xpath("//span[@data-testid='createSnapshot-btn']"));
			addbk.click();
			Thread.sleep(3000);
			

			// 输入增备份名称
			WebElement bkname = driver.findElement(By.xpath("//input[@data-testid='createSnapShot-name']"));
			bkname.sendKeys("rbsbkadd");
			Thread.sleep(3000);
			

			// 提交
			WebElement submitbk = driver.findElement(By.xpath("//span[@data-testid='pop-model-confirm']"));
			submitbk.click();
			Thread.sleep(10000);
			System.out.println("提交");
			
			calculateSum();

			// 用例运行结果写入LOG文件
			String cases = "SnapshotVolume";
			
			//取得当前日期
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			
			//如果已经建出来并且核对的价格正确，Pass的结果写入文件
			if (reValue1 && valueOk){
				
				pubMeth.rwFile(cases,df.format(new Date()),"Pass");
			
			} else { 
				pubMeth.rwFile(cases,df.format(new Date()),"Fail");
			}
		} else {
			System.out.println("没有硬盘");
		}

		driver.close();
	}

	/**
	 * 创建云硬盘
	 * 
	 * @author yangw
	 * @version 1.00
	 * @throws Throwable 
	 */
	private void createVolume() throws Throwable {

		// 左侧云硬盘
		WebElement cloudserver = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-volume']"));
		cloudserver.click();
		Thread.sleep(2000);
		System.out.println("左侧云硬盘");

		// 华东一区或者亚太一区

		Random RandomseleArea = new Random();
		seleArea = RandomseleArea.nextInt(1);// 为0-1个数
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
		WebElement newhdd = driver.findElement(By.xpath("//span[@data-testid='create']"));
		newhdd.click();
		Thread.sleep(2000);
		System.out.println("新建");

		// 按需
		pubMeth.anXu(driver);

		// 输入硬盘名称
		WebElement servername = driver.findElement(By.xpath("//input[@class='input-text input-long']"));
		servername.clear();
		servername.sendKeys("autohddforbk");
		Thread.sleep(2000);
		System.out.println("输入硬盘名称autohdd");

		// 硬盘数量
		WebElement num = driver.findElement(By.xpath("//input[@data-testid='selectNum-input']"));
		num.clear();

		int max = 1;
		int min = 1;
		Random randomcpu = new Random();
		numRandom = randomcpu.nextInt(max) % (max - min + 1) + min;

		num.sendKeys(+numRandom + "");
		Thread.sleep(2000);
		System.out.println("选择" + numRandom + "个硬盘");
		String strnumRandom = String.valueOf(numRandom);
		pubMeth.rwFile("硬盘=", strnumRandom, "个");

		// 类型
		Random RandomtypeRandom = new Random();
		typeRandom = RandomtypeRandom.nextInt(2);// 一种情况是性能型，单位是10的，一种情况是容量型，单位是100的
		if (typeRandom == 1) {
			type = "radio-0";
			digit = "0";

		} else {
			type = "radio-1";
			digit = "00";
		}

		WebElement seletype = driver.findElement(By.xpath("//span[@data-testid='" + type + "']"));
		seletype.click();
		Thread.sleep(3000);
		System.out.println("按需");

		// 容量
		WebElement storage = driver.findElement(By.xpath("//input[@data-testid='customSlideNum']"));
		storage.clear();

		int max1 = 10;
		int min1 = 3;
		Random randoma = new Random();
		size = randoma.nextInt(max1) % (max1 - min1 + 1) + min1;
		storage.clear();

		System.out.println("digit=" + digit);
		storage.sendKeys(+size + digit);
		Thread.sleep(3000);
		System.out.println("输入容量" + size + digit + "G");
		String strsize = String.valueOf(size);
		pubMeth.rwFile("容量=", strsize + digit, "G");

		// 提交
		WebElement submit = driver.findElement(By.xpath("//span[@data-testid='pop-model-confirm']"));
		submit.click();
		Thread.sleep(10000);
		System.out.println("提交");

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

		if (typeRandom == 1) {
			sum = 0.004 * size * numRandom;
		} else {
			sum = 0.004 * size * numRandom * 10;
		}

		// 取小数点后四位
		// String sumTo = String.format("%.4f", sum);
		String sumTo = String.valueOf(sum);
		System.out.println("计算值=" + sumTo); 
		pubMeth.rwFile("计算值=", sumTo, ""); 
		//算出的值与页面取值比较是否相等
		valueOk = sumTo.equals(priceValueQu); 
		if (valueOk) {
			System.out.println("price sum is correctly"); 
			pubMeth.rwFile("结果 = ", "price sum is correctly", ""); 
		} else {
			System.out.println("no correctly"); 
			pubMeth.rwFile("结果 = ", "not correctly", ""); 
		}
	}// 计算结束

}// 类结束

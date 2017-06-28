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

public class SnapshotRdbs {

	/**
	 * 创建数据库快照备份
	 * 
	 * @author yangw
	 * @version 1.00
	 */

	String priceValue;
	String priceValueQu;
	boolean valueOk = false;
	int intRandomOs = 0;// 选择操作系统需要的随机数
	int seleCpuMem = 0; // seleCpuMem选择CPU内存
	int seleArea = 0; // seleArea=1时亚太一区
	int size = 0; // size=1容量取20G
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void backupRdbs() throws Exception {

		driver = pubMeth.beforeTest(driver);
		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
		createRdbs();

		// 左侧云数据库
		WebElement cloudrbs = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-rdbs']"));
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
			// if (re = false){break;}

			// 选择建好的数据库
			WebElement serverok = driver.findElement(locator);
			serverok.click();
			Thread.sleep(2000);
			
			// 更多操作
			WebElement more = driver.findElement(By.xpath("//span[@data-testid='moreOpts']"));
			more.click();
			Thread.sleep(2000);
			
			// 创建快照备份
			WebElement createbk = driver.findElement(By.xpath("//span[@data-testid='moreOpts-createSnapshot']"));
			createbk.click();
			Thread.sleep(2000);
			

			// 输入快照备份名称
			WebElement servername = driver.findElement(By.xpath("//input[@data-testid='createSnapShot-name']"));
			servername.sendKeys("rdbbk");
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

			// 取页面显示的价格
			WebElement pricelast = driver.findElement(By.xpath("//div[@class='description']"));
			java.util.List<WebElement> pricelast1 = pricelast.findElements(By.xpath("//div[@class='detail-item']"));
		//	int pricesize pricelast1.size();
			WebElement pricelast2=pricelast1.get(2);
			priceValue = pricelast2.getText();
			priceValueQu = priceValue.substring(41, priceValue.length() - 32);
			System.out.println("页面取得的价格为 = " + priceValueQu);
			pubMeth.rwFile("页面价格 = ", priceValueQu, "");
			
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
			String cases = "SnapshotRdbs";
			
			//取得当前日期
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			
			//如果已经建出来并且核对的价格正确，Pass的结果写入文件
			if (reValue1 && valueOk){
				
				pubMeth.rwFile(cases,df.format(new Date()),"Pass");
			
			} else { 
				pubMeth.rwFile(cases,df.format(new Date()),"Fail");
			}

		} else {
			System.out.println("没有数据库");
		}

		driver.close();
	}

	/**
	 * 创建数据库
	 * 
	 * @author yangw
	 * @version 1.00
	 */

	private void createRdbs() throws InterruptedException {
		// 左侧数据库
		WebElement cloudserver = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-rdbs']"));
		cloudserver.click();
		Thread.sleep(2000);

		// 华东一区或者亚太一区
		// 此区需要已经建好路由器
		Random RandomseleArea = new Random();
		seleArea = RandomseleArea.nextInt(1);// 为0-1个数
		if (seleArea == 0) {
			WebElement yatai = driver.findElement(By.xpath("//span[@data-testid='region-select-ac1']"));
			yatai.click();
			Thread.sleep(5000);
			System.out.println("华东一区");
		} else {
			WebElement huadong = driver.findElement(By.xpath("//span[@data-testid='region-select-ac1']"));
			huadong.click();
			Thread.sleep(5000);
			System.out.println("亚太一区");
		}
		// 新建
		WebElement creat = driver.findElement(By.xpath("//span[@class='color-primary']"));
		creat.click();
		Thread.sleep(2000);
		

		// 点DB操作系统下拉
		WebElement dbos = driver.findElement(By.xpath("//div[@class='single-label single-line']"));
		dbos.click();
		Thread.sleep(2000);
		

		// 选取下拉列表中的操作系统
		// 先得到一种class写入list,get(0)看要第几个,使用value的方法click

		Random randomos = new Random();
		intRandomOs = randomos.nextInt(4);// 为0-3个数

		java.util.List<WebElement> element = driver.findElements(By.xpath("//li[@class='dropdown-item single-line']"));
		WebElement value = element.get(intRandomOs);
		value.click();
		Thread.sleep(2000);
		System.out.println("intRandomOs=" + intRandomOs);

		String str = intRandomOs + ""; // 整数转换成字符串
		pubMeth.rwFile("DBOS为第", str, "个数据库");

		// 选择CPU和内存
		// 生成随机数
		Random randomocpu = new Random();
		seleCpuMem = randomocpu.nextInt(5);// 为0-3个数
		String Strdb = String.valueOf(Math.pow(2, seleCpuMem));

		System.out.println("cpu随机数为ll=" + seleCpuMem);
		pubMeth.rwFile("cpu为", Strdb, "核");

		String[] testid1 = new String[5];
		testid1[0] = "tabSwitch-config-K1G2";
		testid1[1] = "tabSwitch-config-K2G4";
		testid1[2] = "tabSwitch-config-K4G8";
		testid1[3] = "tabSwitch-config-K8G16";
		testid1[4] = "tabSwitch-config-K8G32";

		WebElement db = driver.findElement(By.xpath("//span[@data-testid='" + testid1[seleCpuMem] + "']"));
		db.click();
		Thread.sleep(3000);

		// 容量
		Random Randomsize = new Random();
		size = Randomsize.nextInt(2);// 为0-1个数
		if (size == 1) {
			WebElement storage = driver.findElement(By.xpath("//input[@class='num-wrapper']"));
			storage.clear();
			storage.sendKeys("20");
			Thread.sleep(2000);
			
			pubMeth.rwFile("容量为", "", "20");
		} else {
			
			pubMeth.rwFile("容量为", "", "10");
		}

		// 输入服务器名称
		WebElement servername = driver.findElement(By.xpath("//input[@class='input-text input-long']"));
		servername.sendKeys("autodb");
		Thread.sleep(2000);
		

		// 用户名
		WebElement username = driver.findElement(By.xpath("//input[@class='input-text input-long mr10']"));
		username.sendKeys("sa123456");
		Thread.sleep(2000);
		

		// 输入pwd
		java.util.List<WebElement> pwd = driver.findElements(By.xpath("//input[@class='input-text input-long mr10']"));
		// int num = pwd.size();
		// System.out.println(num);
		WebElement pwdvalue = pwd.get(1);
		pwdvalue.sendKeys("Anchang123");
		Thread.sleep(2000);
		

		// 再次输入pwd
		java.util.List<WebElement> repwd = driver.findElements(By.xpath("//input[@class='input-text input-long']"));
		// int num1 = repwd.size();
		// System.out.println(num1);
		WebElement repwdvalue = repwd.get(1);
		repwdvalue.sendKeys("Anchang123");
		Thread.sleep(2000);
		

		// 提交
		WebElement submit = driver.findElement(By.xpath("//div[@class='btn-big']"));
		submit.click();
		Thread.sleep(40000);
		System.out.println("立即开通");

	}

	/**
	 * 计算快照备份价格
	 * 
	 * @author yangw
	 * @version 1.00
	 * @throws Exception 
	 */
	private void calculateSum() throws Exception {

		double sum = 0;
		// CreateRbs_id calcu = new CreateRbs_id();
		if (size == 1) {

			sum = 0.004 * 2;

		} else {

			sum = 0.004;
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

	}

}

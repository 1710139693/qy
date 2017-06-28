package cloud.rdbs;

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

public class CreateRdbsArea {
	/**
	 * 创建云数据库
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	int intRandomCpu = 0;
	int intRandomMem = 0;
	int intRandomOs = 0;// 选择操作系统需要的随机数
	//String priceValue;
	boolean valueOk = false;

	int seleCpuMem = 0; // seleCpuMem选择CPU内存
	int seleArea = 0; // seleArea=1时亚太一区
	int size = 0; // size=1容量取20G
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void cloud2Rdbs() throws Exception {
		
		driver = pubMeth.beforeTest(driver);
		
		for(int i=1;i<3;i++){
			pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
			// 左侧数据库
			WebElement cloudserver = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-rdbs']"));
			cloudserver.click();
			Thread.sleep(2000);
			
			// 华东一区或者亚太一区
			// 此区需要已经建好路由器
			
			Random RandomseleArea = new Random();
			seleArea = RandomseleArea.nextInt(3);// 为0-1个数
			//seleArea = 2;
			pubMeth.seleAreaall(driver,seleArea);
	
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
	
			//华东二区选择超高性能型
			
			if (seleArea == 2) {
				System.out.println("enter=" + seleArea);
				WebElement typesuper = driver.findElement(By.xpath("//span[@data-testid='tabSwitch-HIGH_PERFORMANCE']"));
				typesuper.click();
				Thread.sleep(2000);
			} 	
			
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
	
			/*
			 * /生成5-8随机数 int max1=8; int min1=5; Random randomcpu= new Random();
			 * intRandomCpu = randomcpu.nextInt(max1)%(max1-min1+1) + min1;
			 * System.out.println("intRandomCpu="+intRandomCpu);
			 * 
			 * java.util.List<WebElement> selectcpu = driver.findElements(By.xpath(
			 * "//span[@class='tab-switch w90']")); // int selectcpunum =
			 * selectcpu.size(); // System.out.println("size="+selectcpunum);
			 * WebElement cpumem = selectcpu.get(intRandomCpu); cpumem.click();
			 * Thread.sleep(2000); System.out.println(value.getText());
			 * pubMeth.rwFile("dbcpumem=",value.getText(),"");
			 */
	
			// 容量
			Random Randomsize = new Random();
			size = Randomsize.nextInt(2);// 为0-1个数
			if (size == 1) {
				WebElement storage = driver.findElement(By.xpath("//input[@class='num-wrapper']"));
				storage.clear();
				storage.sendKeys("20");
				Thread.sleep(2000);
				System.out.println("输入DB存储大小20");
			} else {
				System.out.println("存储大小10");
			}
	
			// 输入服务器名称
			
			pubMeth.inputName(driver, "autodb");
			
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
			
			// 取页面显示的价格
			pubMeth.UIprice(driver);
		
			// 提交
			WebElement submit = driver.findElement(By.xpath("//div[@class='btn-big']"));
			submit.click();
			Thread.sleep(25000);
			System.out.println("立即开通");
	
			// 判断有没有建出来，取得返回值re
			By locator = By.xpath("//i[@data-testid='table-row-0-checkbox']");
			boolean reValue = pubMeth.isElementExsit(driver, locator);
			System.out.println(reValue);
	
			calculateSum();
	
			// 用例运行结果写入LOG文件
			
			pubMeth.writeLog("CreateRdbsId",reValue);


		 }//for

		// driver.close();
	}// 创建服务器函数结束

	/**
	 * 根据选择的CPU和内存计算总价格
	 * 
	 * @author yangw
	 * @version 1.00
	 * @throws Exception 
	 */

	private void calculateSum() throws Exception {
		double cpuMemPrice = 0;
		double storageSize = 0;
		double sum = 0;
		System.out.println("size=" + size);
		if (seleArea != 2) {
			switch (seleCpuMem) {
			case 0:
				cpuMemPrice = 0.2285;
				break;
			case 1:
				cpuMemPrice = 0.5914;
				break;
			case 2:
				cpuMemPrice = 1.1694;
				break;
			case 3:
				cpuMemPrice = 2.1371;
				break;
			case 4:
				cpuMemPrice = 3.2124;
				break;
			}
			
			if (size == 1) {

				storageSize = 0.0134 * 2;

			} else {

				storageSize = 0.0134;
			}
			
			
		} else {
			
			switch (seleCpuMem) {
			case 0:
				cpuMemPrice = 0.3065;
				break;
			case 1:
				cpuMemPrice = 0.7964;
				break;
			case 2:
				cpuMemPrice = 1.5767;
				break;
			case 3:
				cpuMemPrice = 2.8831;
				break;
			case 4:
				cpuMemPrice = 4.3348;
				break;
			}
				
			if (size == 1) {

				storageSize = 0.02 * 2;

			} else {

				storageSize = 0.02;
			}
		}
		
		System.out.println("storageSize=" + storageSize);
		System.out.println("cpuMemPrice=" + cpuMemPrice);
		sum = cpuMemPrice + storageSize;

		// 取小数点后四位
		String sumTo = String.format("%.4f", sum);
		pubMeth.calcuCheck(sumTo);
	}// 计算函数
}

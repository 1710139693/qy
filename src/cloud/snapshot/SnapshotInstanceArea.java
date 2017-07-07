package cloud.snapshot;

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

public class SnapshotInstanceArea {

	/**
	 * 创建云服务器快照备份
	 * 
	 * @author yangw
	 * @version 1.00
	 */

	int intRandomOs = 0;// 选择操作系统需要的随机数
	String priceValue;
	String priceValueQu;
	boolean valueOk = false;
	int osWindows = 0;  //osWindows = 0即windows操作系统
	int seleArea = 0;  //seleArea = 1时亚太一区
	int seleCpu = 0; 	//cpu选择
	int memOneK = 0;  //CPU为1K时，内存
	int memTwoK = 0;  //CPU为2K时，内存
	int memFourK = 0;  //CPU为4K时，内存
	int memEightK = 0;  //CPU为8K时，内存
	int seleNet = 0;  //公网内肉随机安数
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void backupServer() throws Exception {
		
		driver = pubMeth.beforeTest(driver);
		cloud2Instance();
		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
		// 左侧云服务器
		WebElement cloudserver = driver.findElement(By.xpath("//a[@data-testid = 'sidebarNav-cloud-instance']"));
		cloudserver.click();
		Thread.sleep(3000);	

		// 选择华东一区
		WebElement yatai = driver.findElement(By.xpath("//span[@data-testid = 'region-select-ac1']"));
		yatai.click();
		Thread.sleep(3000);
		System.out.println("华东一区");
		pubMeth.rwFile("区域为", "华东一区", "");
		
		// 判断服务器有没有建出来，取得返回值re
		Base pubMeth = new Base();
		By locator = By.xpath("//i[@data-testid = 'table-row-0-checkbox']");
		boolean reValue = pubMeth.isElementExsit(driver, locator);
		System.out.println("reValue = " + reValue);

		if (reValue) {
			// if (re = false) {break;}

			// 选择建好的服务器
			WebElement serverok = driver.findElement(locator);
			serverok.click();
			Thread.sleep(2000);
			
			// 更多操作
			WebElement more = driver.findElement(By.xpath("//span[@data-testid = 'moreOpts']"));
			more.click();
			Thread.sleep(2000);
			
			// 创建快照备份
			WebElement createbk = driver.findElement(By.xpath("//span[@data-testid = 'moreOpts-createSnapshot']"));
			createbk.click();
			Thread.sleep(2000);
			System.out.println("创建快照备份");

			// 输入快照备份名称
			WebElement servername = driver.findElement(By.xpath("//input[@data-testid = 'createSnapShot-name']"));
			servername.sendKeys("serverbk");
			Thread.sleep(3000);
			
			// 提交
			WebElement submit = driver.findElement(By.xpath("//span[@data-testid = 'pop-model-confirm']"));
			submit.click();
			Thread.sleep(10000);
			System.out.println("提交");

			// 左侧快照备份
			WebElement bk = driver.findElement(By.xpath("//a[@data-testid = 'sidebarNav-cloud-snapshot']"));
			bk.click();
			Thread.sleep(3000);
			System.out.println("左侧快照备份");

			// 判断快照备份有没有建出来，取得返回值rebk

			By locatorbk = By.xpath("//tbody[@data-testid = 'table-row-0']");
			boolean reValue1 = Base.isElementExsit(driver, locatorbk);
			System.out.println("reValue1 =" + reValue1);

			// 选择这个快照备份
			WebElement bkid = driver.findElement(By.xpath("//span[@data-testid = 'table-row-0-id']"));
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
			WebElement atwoGiOrFourGibk = driver.findElement(By.xpath("//span[@data-testid = 'createSnapshot-btn']"));
			atwoGiOrFourGibk.click();
			Thread.sleep(3000);

			// 输入增备份名称
			WebElement bkname = driver.findElement(By.xpath("//input[@data-testid = 'createSnapShot-name']"));
			bkname.sendKeys("serverbkatwoGiOrFourGi");
			Thread.sleep(3000);
			
			// 提交
			WebElement submitbk = driver.findElement(By.xpath("//span[@data-testid = 'pop-model-confirm']"));
			submitbk.click();
			Thread.sleep(10000);
			System.out.println("提交");

			calculateSum();

			// 用例运行结果写入LOG文件
			String cases = "SnapshotInstance";
			
			//取得当前日期
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			
			//如果已经建出来并且核对的价格正确，Pass的结果写入文件
			if (reValue1 && valueOk){
				
				pubMeth.rwFile(cases,df.format(new Date()),"Pass");
			
			} else { 
				pubMeth.rwFile(cases,df.format(new Date()),"Fail");
			}

		} else {
			System.out.println("没有服务器");
		}

		driver.quit();
	}

	/**
	 * 创建云服务器
	 * 
	 * @author yangw
	 * @version 1.00
	 * @throws Exception 
	 */
	private void cloud2Instance() throws Exception {
		
		for (int i = 1; i <2; i++) {
			// 左侧云服务器
			WebElement cloudServer = driver.findElement(By.xpath("//a[@data-testid = 'sidebarNav-cloud-instance']"));
			cloudServer.click();
			Thread.sleep(3000);
			pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");

			// 华东一区或者亚太一区

			//Random RandomseleArea = new Random();
			//seleArea = RandomseleArea.nextInt(2); // 为0-1个数
			seleArea = 0;
			pubMeth.seleAreaall(driver, seleArea);
			
			// 新建	
			driver = pubMeth.newOne(driver);
	
			// 按需
			pubMeth.anXu(driver);

			// 系统
			WebElement systemOs = driver.findElement(By.xpath("//span[@data-testid = 'tabSwitch-image-system']"));
			systemOs.click();
			//Thread.sleep(3000);
			
			// 选取下拉列表中的操作系统
			Random randomosWindows = new Random();
			osWindows = randomosWindows.nextInt(2);
			System.out.println("操作系统osWindows=" + osWindows);
			if (osWindows == 1) {

				pubMeth.rwFile("OS=", "", "liunx操作系统");
				// 点系统下拉列表
				WebElement osDown = driver.findElement(By.xpath("//i[@class = 'triangle-down abs-right8 abs-middle']"));
				osDown.click();
				Thread.sleep(2000);
				System.out.println("系统下拉");

				// 生成随机数
				Random randomos = new Random();
				intRandomOs = randomos.nextInt(7); // 为0-6个数

				java.util.List<WebElement> os = driver
						.findElements(By.xpath("//li[@class = 'dropdown-item single-line']"));
				WebElement indexOs = os.get(intRandomOs);
				indexOs.click();
				Thread.sleep(2000);
				System.out.println("选择第" + intRandomOs + "个操作系统");

			} else {

				System.out.println("windows操作系统");
				pubMeth.rwFile("OS= ", "", "windows操作系统");

				WebElement osVersion = driver
						.findElement(By.xpath("//div[@data-testid = 'singleChoice-imageOSChoice1-default']"));
				osVersion.click();
				Thread.sleep(2000);
				System.out.println("操作系统版本下拉");
				int kk = 0;
				Random randomOsVersion = new Random();
				kk = randomOsVersion.nextInt(8); // 为0-14个数
				System.out.println("操作系统版本kk=" + kk);

				String[] testid = new String[8];
				
				testid[0] = "singleChoice-imageOSChoice1-img-janm9hli";
				testid[1] = "singleChoice-imageOSChoice1-img-pczrme12";
				testid[2] = "singleChoice-imageOSChoice1-winsrv2012r2chsa";
				testid[3] = "singleChoice-imageOSChoice1-winsrv2012r2chsa";
				testid[4] = "singleChoice-imageOSChoice1-win2k8r2eechsg";
				testid[5] = "singleChoice-imageOSChoice1-winsrv2012r2chskmsa";
				testid[6] = "singleChoice-imageOSChoice1-win2k3r2eechskms";
				testid[7] = "singleChoice-imageOSChoice1-win2k8r2eeenkms";	
				
				WebElement osList = driver.findElement(By.xpath("//li[@data-testid = '" + testid[kk] + "']"));

				osList.click();
				Thread.sleep(2000);

				System.out.println(Arrays.toString(testid));
			}
			
			//华东二区选择超高性能型
			if (seleArea == 2) {
				System.out.println("enter=" + seleArea);
				WebElement typesuper = driver.findElement(By.xpath("//span[@data-testid='tabSwitch-insType-HIGH_PERFORMANCE']"));
				typesuper.click();
				Thread.sleep(2000);
			}
			
			// 选择CPU
			// 生成随机数
			Random randomocpu = new Random();
			seleCpu = randomocpu.nextInt(4); // 为0-3个数
			String Strcpu = String.valueOf(Math.pow(2, seleCpu));

			System.out.println("cpu随机数为seleCpu=" + seleCpu);
			pubMeth.rwFile("cpu为", Strcpu, "核");

			String[] testid1 = new String[4];
			testid1[0] = "tabSwitch-cpu-1";
			testid1[1] = "tabSwitch-cpu-2";
			testid1[2] = "tabSwitch-cpu-4";
			testid1[3] = "tabSwitch-cpu-8";

			WebElement cpu = driver.findElement(By.xpath("//span[@data-testid='" + testid1[seleCpu] + "']"));
			cpu.click();
			//Thread.sleep(3000);

			// 选择内存
			if (seleCpu == 0) {

				Random randomomem = new Random();
				memOneK = randomomem.nextInt(3); // 为0-3个数
				System.out.println("memOneK = " + memOneK);
				String strmemOneK = String.valueOf(Math.pow(2, memOneK));
				pubMeth.rwFile("mem为", strmemOneK, "G");

				String[] testid2 = new String[3];

				testid2[0] = "tabSwitch-memory-1";
				testid2[1] = "tabSwitch-memory-2";
				testid2[2] = "tabSwitch-memory-4";

				WebElement mem = driver.findElement(By.xpath("//span[@data-testid = '" + testid2[memOneK] + "']"));
				mem.click();
				//Thread.sleep(3000);

			} else {
				if (seleCpu == 1) {

					Random randomomem = new Random();
					memTwoK = randomomem.nextInt(4); // 为0-3个数
					System.out.println("memTwoK = " + memTwoK);
					String strmemTwoK = String.valueOf(Math.pow(2, memTwoK));
					pubMeth.rwFile("mem为", strmemTwoK, "G");

					String[] testid3 = new String[4];

					testid3[0] = "tabSwitch-memory-1";
					testid3[1] = "tabSwitch-memory-2";
					testid3[2] = "tabSwitch-memory-4";
					testid3[3] = "tabSwitch-memory-8";

					WebElement mem = driver.findElement(By.xpath("//span[@data-testid = '" + testid3[memTwoK] + "']"));
					mem.click();
					//Thread.sleep(3000);

				} else {
					if (seleCpu == 2) {

						Random randomomem = new Random();
						memFourK = randomomem.nextInt(4); // 为0-3个数
						System.out.println("memFourK = " + memFourK);
						String strmemFourK = String.valueOf(Math.pow(2, memFourK + 1));
						pubMeth.rwFile("mem为", strmemFourK, "G");
						String[] testid4 = new String[4];

						testid4[0] = "tabSwitch-memory-2";
						testid4[1] = "tabSwitch-memory-4";
						testid4[2] = "tabSwitch-memory-8";
						testid4[3] = "tabSwitch-memory-16";

						WebElement mem = driver.findElement(By.xpath("//span[@data-testid = '" + testid4[memFourK] + "']"));
						mem.click();
						//Thread.sleep(3000);

					} else if (seleCpu == 3) {

						Random randomomem = new Random();
						memEightK = randomomem.nextInt(4); // 为0-3个数
						System.out.println("memEightK = " + memEightK);
						String strmemEightK = String.valueOf(Math.pow(2, memEightK + 2));
						pubMeth.rwFile("mem为", strmemEightK, "G");
						String[] testid5 = new String[4];

						testid5[0] = "tabSwitch-memory-4";
						testid5[1] = "tabSwitch-memory-8";
						testid5[2] = "tabSwitch-memory-16";
						testid5[3] = "tabSwitch-memory-32";

						WebElement mem = driver
								.findElement(By.xpath("//span[@data-testid = '" + testid5[memEightK] + "']"));
						mem.click();
						//Thread.sleep(3000);

					}

				}

			}

			// 创建HDD
			// addhddip();

			// 内网公网随机选

			Random randomnet = new Random();
			seleNet = randomnet.nextInt(2); // 为0-1个数
			System.out.println("seleNet = " + seleNet);

			String[] testid6 = new String[2];
			testid6[0] = "tabSwitch-netWork-public";
			testid6[1] = "tabSwitch-netWork-internal";

			WebElement net = driver.findElement(By.xpath("//span[@data-testid = '" + testid6[seleNet] + "']"));
			net.click();
			//Thread.sleep(3000);

			if (seleNet == 1) {
				
				pubMeth.rwFile("网络为", "", "内网");
				// 新建SDN网络
				//WebElement createSdn = driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[1]/div[2]/div[3]/div[7]/div/div[3]/div[1]/div[3]/div[2]/div[2]/div[5]/span[2]"));
				java.util.List<WebElement> element = driver.findElements(By.xpath("//span[@class = 'link']"));
				WebElement createSdn = element.get(1);
				createSdn.click();
				Thread.sleep(3000);
				WebElement sdnName = driver.findElement(By.xpath("//input[@class = 'input-text input-long']"));
				sdnName.sendKeys("autosdn");
				Thread.sleep(3000);
				WebElement sdnSub = driver.findElement(By.xpath("//span[@class = 'button button-primary']"));
				sdnSub.click();
				Thread.sleep(10000);
				System.out.println("创建SDN网络");

			} else {

				pubMeth.rwFile("网络为", "", "公网");

			}

			// 输入服务器名称
			pubMeth.inputName(driver, "autoserverforbk");
			
			// 输入pwd
			WebElement pwd = driver.findElement(By.xpath("//input[@class = 'input-text input-long mr10']"));
			pwd.sendKeys("Ac781598");
			//Thread.sleep(3000);
			
			// 再次输入pwd
			java.util.List<WebElement> rePwd = driver
					.findElements(By.xpath("//input[@class = 'input-text input-long']"));
			WebElement rePwdValue = rePwd.get(2);
			rePwdValue.sendKeys("Ac781598");
			Thread.sleep(2000);
			
			// 提交
			WebElement submit = driver.findElement(By.xpath("//div[@class = 'btn-big']"));
			submit.click();
			Thread.sleep(20000);
			System.out.println("立即开通");

			// 判断有没有建出来，取得返回值re
			By locator = By.xpath("//i[@data-testid = 'table-row-0-checkbox']");
			boolean reValue = pubMeth.isElementExsit(driver, locator);
			System.out.println(reValue);

			pubMeth.writeLog("createInstanceId",reValue);

		} // for

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
		if (osWindows == 1) {// 当选择多个操作系统时
			if (intRandomOs == 0) {// 当选择第0个操作系统，即windows时
				sum = 0.004 * 5;
			} else {
				sum = 0.004 * 2;
			}
		} else {// 当不选时为ubuntu
			sum = 0.004 * 2;
		}

		// 取小数点后四位
		//String sumTo = String.format("%.4f", sum);
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

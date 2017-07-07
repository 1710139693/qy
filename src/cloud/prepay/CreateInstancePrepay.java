package cloud.prepay; 
import java.text.SimpleDateFormat; 
import java.util.Arrays; 
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
import common.Login;
import common.Base;

/**
* 创建云服务器
* @author yangw
* @version 1.00
*/
	@Test
	public class CreateInstancePrepay {
	
		/**
		* 创建云服务器
		* @author yangw
		* @version 1.00
		*/
		int intRandomOs = 0; //选择操作系统需要的随机数
		//String priceValue; 
		//boolean valueOk = false; 
		int osWindows = 0;  //osWindows = 0即windows操作系统
		int seleArea = 0;  //seleArea = 1时亚太一区
		int seleCpu = 0; 	//cpu选择
		int memOneK = 0;  //CPU为1K时，内存
		int memTwoK = 0;  //CPU为2K时，内存
		int memFourK = 0;  //CPU为4K时，内存
		int memEightK = 0;  //CPU为8K时，内存
		int seleNet = 0;  //公网内肉随机安数
		WebDriver driver ; 
		Base pubMeth = new Base(); 
		
	public void CreateInstance() throws Throwable {
		driver = pubMeth.beforeTest(driver);
		for (int i = 1; i < 3; i++) {
			// 左侧云服务器
			WebElement cloudServer = driver.findElement(By.xpath("//a[@data-testid = 'sidebarNav-cloud-instance']"));
			cloudServer.click();
			Thread.sleep(3000);
			pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");

			// 华东一区或者亚太一区

			//Random RandomseleArea = new Random();
			//seleArea = RandomseleArea.nextInt(2); // 为0-1个数
			seleArea = 2;
			pubMeth.seleAreaall(driver, seleArea);
			
			// 新建	
			driver = pubMeth.newOne(driver);
	
			// 按需
			//pubMeth.anXu(driver);

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
				
				
				
				WebElement osList = driver.findElement(
						By.xpath("//li[@data-testid = '" + testid[kk] + "']"));

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

						WebElement mem = driver
								.findElement(By.xpath("//span[@data-testid = '" + testid4[memFourK] + "']"));
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
			pubMeth.inputName(driver, "autoserver" + i);
			
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
			
			// 取页面显示的价格
			pubMeth.UIprice(driver);

			// 提交
			WebElement submit = driver.findElement(By.xpath("//div[@class = 'btn-big']"));
			submit.click();
			Thread.sleep(20000);
			System.out.println("立即开通");

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

			if (seleArea != 2) {
				calculateSum();
			} else {
				calculateSumHuadong();
			}
			

			pubMeth.writeLog("createInstanceId",reValue);

		} // for

		driver.quit();

	}// 创建服务器函数结束
		
		
		/*/选择创建HDD
		private void addhddip() throws InterruptedException {
	
			WebElement createhdd = driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[1]/div[2]/div[3]/div[6]/div/div[3]/div[1]/div[2]/div[2]/div[7]/span[2]")); 		
			createhdd.click(); 
			Thread.sleep(5000); 
			WebElement  hddname =  driver.findElement(By.xpath("//input[@class = 'input-text input-long']")); 
			hddname.sendKeys("autohdd"); 
			Thread.sleep(5000); 
			WebElement  hddsub =  driver.findElement(By.xpath("//span[@class = 'button button-primary']")); 
			hddsub.click(); 
			Thread.sleep(10000); 
			System.out.println("创建HDD"); 	
		}
		*/
		
		/**
		* 计算价格，即根据选择的CPU，内存等得出总价格
		* @author yangw
		* @version 1.00
		* @throws Throwable 
		*/
		private void calculateSum() throws Throwable {
			
			double cpuPrice = 0; 
			double memPrice = 0; 
			
			switch (seleCpu) { 	
			
			    case 0:cpuPrice = 0.0269; break; 
				case 1:cpuPrice = 0.1075; break; 
				case 2:cpuPrice = 0.2419; break; 
				case 3:cpuPrice = 0.4570; break; 
			}

			
			//当CPU不同核数时，内存的价格	
					if (seleCpu == 0) { //1核，内存价格
						switch (memOneK) {
						case 0:memPrice = 0.0269; break; 
						case 1:memPrice = 0.0806; break; 
						case 2:memPrice = 0.1747; break; 
						 }
						} else if (seleCpu == 1) {//其它核数，内存价格
						
							switch (memTwoK) {
								case 0:memPrice = 0.0269; break; 
								case 1:memPrice = 0.0806; break; 
								case 2:memPrice = 0.1747; break; 
								case 3:memPrice = 0.3360; break; 
							}
							System.out.println("mem1 = " + memPrice); 
						} else if (seleCpu == 2) {

							switch (memFourK) {
								case 0:memPrice = 0.0806; break; 
								case 1:memPrice = 0.1747; break; 
								case 2:memPrice = 0.3360; break; 
								case 3:memPrice = 0.6048; break; 
							}
							System.out.println("mem2 = " + memPrice); 
						} else if (seleCpu == 3) {
						
							switch (memEightK) {
								case 0:memPrice = 0.1747; break; 
								case 1:memPrice = 0.3360; break; 
								case 2:memPrice = 0.6048; break; 
								case 3:memPrice = 1.0349; break; 
							}
							System.out.println("mem3 = " + memPrice); 
						} else {
								System.out.println("无值"); 
						
						}	
					
					System.out.println("cpu = " + cpuPrice); 
					System.out.println("mem4 = " + memPrice); 
					
					String strcpuPrice = String.valueOf(cpuPrice); 
					String strmemPrice = String.valueOf(memPrice); 
					pubMeth.rwFile("cpu价格 = ", strcpuPrice, ""); 
					pubMeth.rwFile("mem价格 = ", strmemPrice, ""); 
					
					//判断选择的如果是windows系统，计算按硬盘50G（0.02）来算
					double sum = 0; 
					
					//如果是公网
					if (seleNet  ==  0) {
						//如果seleArea == 0为华东一区，seleArea == 1为亚太一区，公网价格不同
						double a = 0; 
						if (seleArea == 0) { 
							a = 0.0278; 
							} else { 
							a = 0.0269; 
						}
						if (osWindows  ==  0) {//公网CPU + 内存 + 系统硬盘价 + 亚太-国际带宽1M默认价格
						//windows
						sum = cpuPrice + memPrice + 0.02 + a; 
						} else {
						//linux
						sum = cpuPrice + memPrice + 0.008 + a; 
						}
						
					} else {
					//如果是内网	
						if (osWindows  ==  0) {//内网CPU + 内存 + 系统硬盘价
						 //windows
						sum = cpuPrice + memPrice + 0.02; 
						} else {
						 //linux
						sum = cpuPrice + memPrice + 0.008; 
					    }
					}
							
					//取小数点后四位
					String sumTo = String.format("%.4f", sum); 
					pubMeth.calcuCheck(sumTo);
			    }//计算结束			

		private void calculateSumHuadong() throws Throwable {

			
			double cpuPrice = 0; 
			double memPrice = 0; 
			
			switch (seleCpu) { 	
			
			    case 0:cpuPrice = 0.0363; break; 
				case 1:cpuPrice = 0.1451; break; 
				case 2:cpuPrice = 0.3265; break; 
				case 3:cpuPrice = 0.6169; break; 
			}

			
			//当CPU不同核数时，内存的价格	
					if (seleCpu == 0) { //1核，内存价格
						switch (memOneK) {
						case 0:memPrice = 0.0363; break; 
						case 1:memPrice = 0.1088; break; 
						case 2:memPrice = 0.2358; break; 
						 }
						} else if (seleCpu == 1) {//其它核数，内存价格
						
							switch (memTwoK) {
								case 0:memPrice = 0.0363; break; 
								case 1:memPrice = 0.1088; break; 
								case 2:memPrice = 0.2358; break;
								case 3:memPrice = 0.4455; break; 
							}
							System.out.println("mem1 = " + memPrice); 
						} else if (seleCpu == 2) {

							switch (memFourK) {
								case 0:memPrice = 0.1088; break; 
								case 1:memPrice = 0.2358; break;
								case 2:memPrice = 0.4455; break; 
								case 3:memPrice = 0.8164; break; 
							}
							System.out.println("mem2 = " + memPrice); 
						} else if (seleCpu == 3) {
						
							switch (memEightK) {
								case 0:memPrice = 0.2358; break;
								case 1:memPrice = 0.4455; break; 
								case 2:memPrice = 0.8164; break; 
								case 3:memPrice = 1.3971; break; 
							}
							System.out.println("mem3 = " + memPrice); 
						} else {
								System.out.println("无值"); 
						
						}	
					
					System.out.println("cpu = " + cpuPrice); 
					System.out.println("mem4 = " + memPrice); 
					
					String strcpuPrice = String.valueOf(cpuPrice); 
					String strmemPrice = String.valueOf(memPrice); 
					pubMeth.rwFile("cpu价格 = ", strcpuPrice, ""); 
					pubMeth.rwFile("mem价格 = ", strmemPrice, ""); 
					
					//判断选择的如果是windows系统，计算按硬盘50G（0.02）来算
					double sum = 0; 
					
					//如果是公网
					if (seleNet  ==  0) {
						
						double a = 0.0403; 
						
						if (osWindows  ==  0) {//公网CPU + 内存 + 系统硬盘价 + 亚太-国际带宽1M默认价格
						//windows
						sum = cpuPrice + memPrice + 0.02 + a; 
						} else {
						//linux
						sum = cpuPrice + memPrice + 0.008 + a; 
						}
						
					} else {
					//如果是内网	
						if (osWindows  ==  0) {//内网CPU + 内存 + 系统硬盘价
						 //windows
						sum = cpuPrice + memPrice + 0.02; 
						} else {
						 //linux
						sum = cpuPrice + memPrice + 0.008; 
					    }
					}
					
					double sumPrepay = sum*24*30;
					//取小数点后四位
					String sumTo = String.format("%.4f", sumPrepay); 
					pubMeth.calcuCheck(sumTo);
			    
			
		}
	}//类结束 
		
		
	
				
			
			
		

		
	
	
	
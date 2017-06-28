package cloud.instance; 
import org.openqa.selenium.support.ui.Select; 
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
import common.Base;

/**
* 此类创建云服务器包括价格核算
* @author yangw
* @version 1.00
*/
	public class CreateInstance {
	
		/**
		* 创建云服务器
		* @author yangw
		* @version 1.00
		*/
		int intRandomCpu = 0; 
		int intRandomMem = 0; 
		int intRandomOs = 0; //选择操作系统需要的随机数
		//String priceValue; 
		//boolean valueOk = false; 
		int oneKernel = 0;  //oneKernel=0时为1核，oneKernel=1时选择其它核数intRandomCpu
		int memOneGi = 0;  //oneKernel=0时（memOneGi=0时为1G,  memOneGi=1时为其它内存twoGiOrFourGi）
		int otherKernelOneGi = 0;  //oneKernel=1时（otherKernelOneGi=0时为1G，otherKernelOneGi=1时选择其它内存intRandomMem）
		int twoGiOrFourGi = 0;  //oneKernel=0即CPU为1核，memOneGi=1 时，twoGiOrFourGi为2G或者4G
		int osWindows = 0;  //osWindows=0即windows操作系统
		int seleNet = 0;  //seleNet=1时为内网
		int seleArea = 0;  //seleArea=1时亚太一区
		WebDriver driver ; 
		Base pubMeth = new Base(); 
			
	@Test
	public void createServer() throws Exception {
		
		driver = pubMeth.beforeTest(driver);
		
		for (int i = 1; i < 3; i++) {
			// 左侧云服务器
			WebElement cloudServer = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-instance']"));
			cloudServer.click();
			Thread.sleep(3000);

			// 华东一区或者亚太一区

			Random RandomseleArea = new Random();
			seleArea = RandomseleArea.nextInt(2); // 为0-1个数
			if (seleArea == 0) {
				WebElement yaTai = driver.findElement(By.xpath("//span[@data-testid='region-select-ac1']"));
				yaTai.click();
				Thread.sleep(3000);
				System.out.println("华东一区");
			} else {
				WebElement huaDong = driver.findElement(By.xpath("//span[@data-testid='region-select-ac2']"));
				huaDong.click();
				Thread.sleep(3000);
				System.out.println("亚太一区");
			}

			Thread.sleep(3000);
			System.out.println("亚太一区");

			// 新建
			WebElement creat = driver.findElement(By.xpath("//span[@class='color-primary']"));
			creat.click();
			Thread.sleep(3000);

			// 按需
			// 先定位div ul
			WebElement anXu = driver.findElement(By.xpath("//div[@class='mt-20 tabs-item']"));
			WebElement anXu1 = anXu.findElement(By.xpath("//li[@class='main-item']"));
			anXu1.click();
			Thread.sleep(3000);
			pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");

			// 系统
			// 先得到一种class写入list, get(0)看要第几个, 使用value的方法click
			java.util.List<WebElement> element = driver.findElements(By.xpath("//span[@class='tab-switch tab-switch-111 w90']"));

			WebElement valueOs = element.get(0);
			valueOs.click();
			Thread.sleep(3000);
			System.out.println(valueOs.getText());

			// 选取下拉列表中的操作系统

			Random randomosWindows = new Random();
			osWindows = randomosWindows.nextInt(2);
			System.out.println("osWindows=" + osWindows);
			if (osWindows == 1) {

				// 点系统下拉列表
				WebElement osDown = driver
						.findElement(By.xpath("//i[@class='triangle-down abs-right8 abs-mitwoGiOrFourGile']"));
				osDown.click();
				Thread.sleep(3000);

				// 生成随机数
				Random randomos = new Random();
				intRandomOs = randomos.nextInt(7); // 为0-6个数

				java.util.List<WebElement> os = driver
						.findElements(By.xpath("//li[@class='dropdown-item single-line']"));
				WebElement indexOs = os.get(intRandomOs);
				indexOs.click();
				Thread.sleep(3000);
				System.out.println("选择第" + intRandomOs + "个操作系统");

			} else {
				System.out.println("ubuntu操作系统");
				pubMeth.rwFile("OS=", "ubuntu操作系统", "");
			}

			Random randomoneKernel = new Random();
			oneKernel = randomoneKernel.nextInt(2);
			// oneKernel=0时为1核，oneKernel=1时选择其它核数intRandomCpu
			if (oneKernel == 1) {
				// 生成4-6随机数
				int max = 6;
				int min = 4;
				Random randomcpu = new Random();
				intRandomCpu = randomcpu.nextInt(max) % (max - min + 1) + min;
				System.out.println("CPU随机数intRandomCpu为：" + intRandomCpu);
				// 选取2.4.8核中的一个
				// driver
				java.util.List<WebElement> cpuList = driver
						.findElements(By.xpath("//span[@class='tab-switch tab-switch-111 w90']"));
				int tab111num = cpuList.size();
				WebElement cpuValue = cpuList.get(intRandomCpu);
				cpuValue.click();
				Thread.sleep(3000);
				System.out.println(cpuValue.getText());
				pubMeth.rwFile("cpu=", cpuValue.getText(), "");
				// 选取内存
				Random randomotherKernelOneGi = new Random();
				otherKernelOneGi = randomotherKernelOneGi.nextInt(2);
				System.out.println("otherKernelOneGi=" + otherKernelOneGi);
				if (otherKernelOneGi == 1) {
					// 生成7-9随机数
					int max1 = 9;
					int min1 = 7;
					Random randommem = new Random();
					intRandomMem = randommem.nextInt(max1) % (max1 - min1 + 1) + min1;
					System.out.println("内存随机数intRandomMem为：" + intRandomMem);
					// driver
					java.util.List<WebElement> memList = driver
							.findElements(By.xpath("//span[@class='tab-switch tab-switch-111 w90']"));
					WebElement memValue = memList.get(intRandomMem);
					memValue.click();
					Thread.sleep(3000);
					pubMeth.rwFile("mem=", memValue.getText(), "");
				} else {
					pubMeth.rwFile("mem=", "第一个位置G", "");
				}

			} else {
				// 不选即为1核，此时内存为1，2，4G三种情况
				
				pubMeth.rwFile("CPU=", "1核", "");

				Random randommemOneGi = new Random();
				memOneGi = randommemOneGi.nextInt(2);
				if (memOneGi == 1) {
					// 生成7-8随机数
					int max1 = 8;
					int min1 = 7;
					Random randommem1 = new Random();
					twoGiOrFourGi = randommem1.nextInt(max1) % (max1 - min1 + 1) + min1;
					System.out.println("内存随机数为：" + twoGiOrFourGi);
					// 选取2，4G中的一个
					// driver
					java.util.List<WebElement> memList = driver
							.findElements(By.xpath("//span[@class='tab-switch tab-switch-111 w90']"));
					WebElement memValue1 = memList.get(twoGiOrFourGi);
					memValue1.click();
					Thread.sleep(3000);
					System.out.println(memValue1.getText());
					pubMeth.rwFile("mem=", memValue1.getText(), "");
				} else {
					
					pubMeth.rwFile("mem=", "第一个位置G", "");
					// 不选即为1G
				}
			}

			// 创建HDD
			// atwoGiOrFourGihtwoGiOrFourGseleNetp();

			// 内网公网随机选

			Random randomseleNet = new Random();
			seleNet = randomseleNet.nextInt(2);
			System.out.println("seleNet=" + seleNet);
			if (seleNet == 1) {
				// 选择内网有问题java.lang.IndexOutOfBoundsException
				System.out.println("内网");
				// driver
				if (oneKernel == 1) {
					java.util.List<WebElement> netList = driver
							.findElements(By.xpath("//span[@class='tab-switch tab-switch-111 w90']"));
					int num = netList.size();
					System.out.println("个数" + num);
					WebElement netValue = netList.get(10);
					netValue.click();
					Thread.sleep(3000);
					System.out.println(netValue.getText());
				} else {
					java.util.List<WebElement> netList = driver
							.findElements(By.xpath("//span[@class='tab-switch tab-switch-111 w90']"));
					int num1 = netList.size();
					System.out.println("个数" + num1);
					WebElement netValue = netList.get(9);
					netValue.click();
					Thread.sleep(3000);
					System.out.println(netValue.getText());
				}

				pubMeth.rwFile("网络为", "内网", "");

				// 新建SDN网络
				WebElement createSdn = driver.findElement(By.xpath(
						"/html/body/div/div/div[1]/div/div[1]/div[2]/div[3]/div[6]/div/div[3]/div[1]/div[3]/div[2]/div[2]/div[5]/span[2]"));
				createSdn.click();
				Thread.sleep(3000);
				WebElement sdnName = driver.findElement(By.xpath("//input[@class='input-text input-long']"));
				sdnName.sendKeys("autosdn");
				Thread.sleep(3000);
				WebElement sdnSub = driver.findElement(By.xpath("//span[@class='button button-primary']"));
				sdnSub.click();
				Thread.sleep(10000);
				System.out.println("创建SDN网络");
			} else {
				
				pubMeth.rwFile("网络为", "公网", "");
			}

	
			// 输入服务器名称
			pubMeth.inputName(driver, "autoserver" + i);
			
		
			// 输入pwd
			WebElement pwd = driver.findElement(By.xpath("//input[@class='input-text input-long mr10']"));
			pwd.sendKeys("Anchang123");
			Thread.sleep(3000);
		
			// 再次输入pwd
			java.util.List<WebElement> rePwd = driver.findElements(By.xpath("//input[@class='input-text input-long']"));
			WebElement rePwdValue = rePwd.get(2);
			rePwdValue.sendKeys("Anchang123");
			// int num = repwd.size();
			// System.out.println(num);
			Thread.sleep(3000);
			
			// 取页面显示的价格
			pubMeth.UIprice(driver);
			
			// 提交
			WebElement submit = driver.findElement(By.xpath("//div[@class='btn-big']"));
			submit.click();
			Thread.sleep(30000);
			System.out.println("立即开通");

			// 判断有没有建出来，取得返回值re

			By locator = By.xpath("//i[@data-testid='table-row-0-checkbox']");
			boolean reValue = pubMeth.isElementExsit(driver, locator);
			System.out.println(reValue);

			calculateSum();

			// 用例运行结果写入LOG文件
			
			pubMeth.writeLog("createVolume",reValue);
		} // for

		driver.close();
	}// 创建服务器函数结束
		
		
		/*/选择创建HDD
		private void atwoGiOrFourGihtwoGiOrFourGseleNetp() throws InterruptedException {
	
			WebElement createhtwoGiOrFourGi=driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[1]/div[2]/div[3]/div[6]/div/div[3]/div[1]/div[2]/div[2]/div[7]/span[2]")); 		
			createhtwoGiOrFourGi.click(); 
			Thread.sleep(3000); 
			WebElement  htwoGiOrFourGiname= driver.findElement(By.xpath("//input[@class='input-text input-long']")); 
			htwoGiOrFourGiname.sendKeys("autohtwoGiOrFourGi"); 
			Thread.sleep(3000); 
			WebElement  htwoGiOrFourGisub= driver.findElement(By.xpath("//span[@class='button button-primary']")); 
			htwoGiOrFourGisub.click(); 
			Thread.sleep(10000); 
			System.out.println("创建HDD"); 	
		}
		*/
		
		/**
		* 计算价格，即根据选择的CPU，内存等得出总价格
		* @author yangw
		* @version 1.00
		 * @throws Exception 
		*/	
		private void calculateSum() throws Exception {
			double cpuPrice = 0; 
			double memPrice = 0; 
			
			//当CPU为1核时的CPU和内存的单价
			if (oneKernel == 0) {
					cpuPrice = 0.0269; 
					if (memOneGi == 0) {
						memPrice = 0.0269; 
						} else {
							switch (twoGiOrFourGi) {
								case 7:memPrice = 0.0806; break; 
								case 8:memPrice = 0.1747; break; 
						}	
					}		
				} else {
			//当CPU核数不为1核时的CPU单价	
					switch (intRandomCpu) {
						case 4:cpuPrice = 0.1075; break; 
						case 5:cpuPrice = 0.2419; break; 
						case 6:cpuPrice = 0.4570; break; 
					 }
			//当CPU核数不为1核时的内存单价		
					if (otherKernelOneGi == 0) { //其它核数，第一个位置内存
						switch (intRandomCpu) {
						case 4:memPrice=0.0269; break; 
						case 5:memPrice=0.0806; break; 
						case 6:memPrice=0.1747; break; 
						}
					 } else {
						//其它核数，其它位置内存
						if (intRandomCpu == 4) {
						
							switch (intRandomMem) {
							
								case 7:memPrice = 0.0806; break; 
								case 8:memPrice = 0.1747; break; 
								case 9:memPrice = 0.3360; break; 
							}
						} else if (intRandomCpu == 5) {
							
							switch (intRandomMem) {
							
								case 7:memPrice = 0.1747; break; 
								case 8:memPrice = 0.3360; break; 
								case 9:memPrice = 0.6048; break; 
							}
						} else if (intRandomCpu == 6) {
							switch (intRandomMem) {
								case 7:memPrice = 0.3360; break; 
								case 8:memPrice = 0.6048; break; 
								case 9:memPrice = 1.0349; break; 
							}
						} else {
							System.out.println("intRandomCpu不等于4.5.6"); 
						}
						
					}
			
				}
	
			//判断选择的如果是windows系统，计算按硬盘50G（0.02）来算
			double sum = 0; 
			System.out.println("cpu=" + cpuPrice); 
			System.out.println("mem=" + memPrice); 
			
			//如果是公网
			if (seleNet == 0) {
				if(osWindows == 1) {
				//如果seleArea==0为华东一区，seleArea==1为亚太一区，公网价格不同
				double a = 0; 
				if (seleArea == 0) { 
					a = 0.0278; 
				} else { 
					a = 0.0269; 
				}
				if (intRandomOs == 0) {//公网CPU + 内存 + 系统硬盘价 + 亚太公网国际带宽1M默认价格
				//windows
				sum = cpuPrice + memPrice + 0.02 + a; 
				} else {
				//linux
				sum = cpuPrice + memPrice + 0.008 + a; }
				} else if (osWindows == 0) {
						//linux,  ubuntu
						double a = 0; 
						if(seleArea == 0) { 
							a = 0.0278; 
						} else { 
							a = 0.0269; 
						}
						sum = cpuPrice + memPrice + 0.008 + a; 
	
				}
			} else {
			//如果是内网	
			if (osWindows == 1) {	//因为window操作系统位置变为第二个而改动
				if (intRandomOs == 0) {//内网CPU + 内存 + 系统硬盘价
				 //windows
				sum = cpuPrice + memPrice + 0.02; 
				} else {
				 //linux
					sum = cpuPrice + memPrice + 0.008; 
					}
				} else if (osWindows == 0) {
			
					sum = cpuPrice + memPrice + 0.008; 
				}
			}		
			//取小数点后四位
			String sumTo = String.format("%.4f", sum); 
			
			pubMeth.calcuCheck(sumTo);
			
	    }//计算函数结束
		

  }
		
	
				
			
			
		

		
	
	
	
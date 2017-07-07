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
* �����Ʒ�����
* @author yangw
* @version 1.00
*/
	@Test
	public class CreateInstancePrepay {
	
		/**
		* �����Ʒ�����
		* @author yangw
		* @version 1.00
		*/
		int intRandomOs = 0; //ѡ�����ϵͳ��Ҫ�������
		//String priceValue; 
		//boolean valueOk = false; 
		int osWindows = 0;  //osWindows = 0��windows����ϵͳ
		int seleArea = 0;  //seleArea = 1ʱ��̫һ��
		int seleCpu = 0; 	//cpuѡ��
		int memOneK = 0;  //CPUΪ1Kʱ���ڴ�
		int memTwoK = 0;  //CPUΪ2Kʱ���ڴ�
		int memFourK = 0;  //CPUΪ4Kʱ���ڴ�
		int memEightK = 0;  //CPUΪ8Kʱ���ڴ�
		int seleNet = 0;  //���������������
		WebDriver driver ; 
		Base pubMeth = new Base(); 
		
	public void CreateInstance() throws Throwable {
		driver = pubMeth.beforeTest(driver);
		for (int i = 1; i < 3; i++) {
			// ����Ʒ�����
			WebElement cloudServer = driver.findElement(By.xpath("//a[@data-testid = 'sidebarNav-cloud-instance']"));
			cloudServer.click();
			Thread.sleep(3000);
			pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");

			// ����һ��������̫һ��

			//Random RandomseleArea = new Random();
			//seleArea = RandomseleArea.nextInt(2); // Ϊ0-1����
			seleArea = 2;
			pubMeth.seleAreaall(driver, seleArea);
			
			// �½�	
			driver = pubMeth.newOne(driver);
	
			// ����
			//pubMeth.anXu(driver);

			// ϵͳ
			WebElement systemOs = driver.findElement(By.xpath("//span[@data-testid = 'tabSwitch-image-system']"));
			systemOs.click();
			//Thread.sleep(3000);
			
			// ѡȡ�����б��еĲ���ϵͳ
			Random randomosWindows = new Random();
			osWindows = randomosWindows.nextInt(2);
			System.out.println("����ϵͳosWindows=" + osWindows);
			if (osWindows == 1) {

				pubMeth.rwFile("OS=", "", "liunx����ϵͳ");
				// ��ϵͳ�����б�
				WebElement osDown = driver.findElement(By.xpath("//i[@class = 'triangle-down abs-right8 abs-middle']"));
				osDown.click();
				Thread.sleep(2000);
				System.out.println("ϵͳ����");

				// ���������
				Random randomos = new Random();
				intRandomOs = randomos.nextInt(7); // Ϊ0-6����

				java.util.List<WebElement> os = driver
						.findElements(By.xpath("//li[@class = 'dropdown-item single-line']"));
				WebElement indexOs = os.get(intRandomOs);
				indexOs.click();
				Thread.sleep(2000);
				System.out.println("ѡ���" + intRandomOs + "������ϵͳ");

			} else {

				System.out.println("windows����ϵͳ");
				pubMeth.rwFile("OS= ", "", "windows����ϵͳ");

				WebElement osVersion = driver
						.findElement(By.xpath("//div[@data-testid = 'singleChoice-imageOSChoice1-default']"));
				osVersion.click();
				Thread.sleep(2000);
				System.out.println("����ϵͳ�汾����");
				int kk = 0;
				Random randomOsVersion = new Random();
				kk = randomOsVersion.nextInt(8); // Ϊ0-14����
				System.out.println("����ϵͳ�汾kk=" + kk);

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

			
			//��������ѡ�񳬸�������
			if (seleArea == 2) {
				System.out.println("enter=" + seleArea);
				WebElement typesuper = driver.findElement(By.xpath("//span[@data-testid='tabSwitch-insType-HIGH_PERFORMANCE']"));
				typesuper.click();
				Thread.sleep(2000);
			}
			
			
			
			// ѡ��CPU
			// ���������
			Random randomocpu = new Random();
			seleCpu = randomocpu.nextInt(4); // Ϊ0-3����
			String Strcpu = String.valueOf(Math.pow(2, seleCpu));

			System.out.println("cpu�����ΪseleCpu=" + seleCpu);
			pubMeth.rwFile("cpuΪ", Strcpu, "��");

			String[] testid1 = new String[4];
			testid1[0] = "tabSwitch-cpu-1";
			testid1[1] = "tabSwitch-cpu-2";
			testid1[2] = "tabSwitch-cpu-4";
			testid1[3] = "tabSwitch-cpu-8";

			WebElement cpu = driver.findElement(By.xpath("//span[@data-testid='" + testid1[seleCpu] + "']"));
			cpu.click();
			//Thread.sleep(3000);

			// ѡ���ڴ�
			if (seleCpu == 0) {

				Random randomomem = new Random();
				memOneK = randomomem.nextInt(3); // Ϊ0-3����
				System.out.println("memOneK = " + memOneK);
				String strmemOneK = String.valueOf(Math.pow(2, memOneK));
				pubMeth.rwFile("memΪ", strmemOneK, "G");

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
					memTwoK = randomomem.nextInt(4); // Ϊ0-3����
					System.out.println("memTwoK = " + memTwoK);
					String strmemTwoK = String.valueOf(Math.pow(2, memTwoK));
					pubMeth.rwFile("memΪ", strmemTwoK, "G");

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
						memFourK = randomomem.nextInt(4); // Ϊ0-3����
						System.out.println("memFourK = " + memFourK);
						String strmemFourK = String.valueOf(Math.pow(2, memFourK + 1));
						pubMeth.rwFile("memΪ", strmemFourK, "G");
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
						memEightK = randomomem.nextInt(4); // Ϊ0-3����
						System.out.println("memEightK = " + memEightK);
						String strmemEightK = String.valueOf(Math.pow(2, memEightK + 2));
						pubMeth.rwFile("memΪ", strmemEightK, "G");
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

			// ����HDD
			// addhddip();

			// �����������ѡ

			Random randomnet = new Random();
			seleNet = randomnet.nextInt(2); // Ϊ0-1����
			System.out.println("seleNet = " + seleNet);

			String[] testid6 = new String[2];
			testid6[0] = "tabSwitch-netWork-public";
			testid6[1] = "tabSwitch-netWork-internal";

			WebElement net = driver.findElement(By.xpath("//span[@data-testid = '" + testid6[seleNet] + "']"));
			net.click();
			//Thread.sleep(3000);

			if (seleNet == 1) {
				
				pubMeth.rwFile("����Ϊ", "", "����");
				// �½�SDN����
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
				System.out.println("����SDN����");

			} else {

				pubMeth.rwFile("����Ϊ", "", "����");

			}

			// �������������
			pubMeth.inputName(driver, "autoserver" + i);
			
			// ����pwd
			WebElement pwd = driver.findElement(By.xpath("//input[@class = 'input-text input-long mr10']"));
			pwd.sendKeys("Ac781598");
			//Thread.sleep(3000);
			
			// �ٴ�����pwd
			java.util.List<WebElement> rePwd = driver
					.findElements(By.xpath("//input[@class = 'input-text input-long']"));
			WebElement rePwdValue = rePwd.get(2);
			rePwdValue.sendKeys("Ac781598");
			Thread.sleep(2000);
			
			// ȡҳ����ʾ�ļ۸�
			pubMeth.UIprice(driver);

			// �ύ
			WebElement submit = driver.findElement(By.xpath("//div[@class = 'btn-big']"));
			submit.click();
			Thread.sleep(20000);
			System.out.println("������ͨ");

			// ȷ���ύ
			pubMeth.subcfm(driver);
			
			// ȷ�ϸ���
			pubMeth.paycfm(driver);
			
			//�����б�
			WebElement returnList = driver.findElement(By.xpath("//span[@class='single-button primary']"));
			returnList.click();
			Thread.sleep(10000);
			System.out.println("�����б�");

			
			// �ж���û�н�������ȡ�÷���ֵre
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

	}// ������������������
		
		
		/*/ѡ�񴴽�HDD
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
			System.out.println("����HDD"); 	
		}
		*/
		
		/**
		* ����۸񣬼�����ѡ���CPU���ڴ�ȵó��ܼ۸�
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

			
			//��CPU��ͬ����ʱ���ڴ�ļ۸�	
					if (seleCpu == 0) { //1�ˣ��ڴ�۸�
						switch (memOneK) {
						case 0:memPrice = 0.0269; break; 
						case 1:memPrice = 0.0806; break; 
						case 2:memPrice = 0.1747; break; 
						 }
						} else if (seleCpu == 1) {//�����������ڴ�۸�
						
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
								System.out.println("��ֵ"); 
						
						}	
					
					System.out.println("cpu = " + cpuPrice); 
					System.out.println("mem4 = " + memPrice); 
					
					String strcpuPrice = String.valueOf(cpuPrice); 
					String strmemPrice = String.valueOf(memPrice); 
					pubMeth.rwFile("cpu�۸� = ", strcpuPrice, ""); 
					pubMeth.rwFile("mem�۸� = ", strmemPrice, ""); 
					
					//�ж�ѡ��������windowsϵͳ�����㰴Ӳ��50G��0.02������
					double sum = 0; 
					
					//����ǹ���
					if (seleNet  ==  0) {
						//���seleArea == 0Ϊ����һ����seleArea == 1Ϊ��̫һ���������۸�ͬ
						double a = 0; 
						if (seleArea == 0) { 
							a = 0.0278; 
							} else { 
							a = 0.0269; 
						}
						if (osWindows  ==  0) {//����CPU + �ڴ� + ϵͳӲ�̼� + ��̫-���ʴ���1MĬ�ϼ۸�
						//windows
						sum = cpuPrice + memPrice + 0.02 + a; 
						} else {
						//linux
						sum = cpuPrice + memPrice + 0.008 + a; 
						}
						
					} else {
					//���������	
						if (osWindows  ==  0) {//����CPU + �ڴ� + ϵͳӲ�̼�
						 //windows
						sum = cpuPrice + memPrice + 0.02; 
						} else {
						 //linux
						sum = cpuPrice + memPrice + 0.008; 
					    }
					}
							
					//ȡС�������λ
					String sumTo = String.format("%.4f", sum); 
					pubMeth.calcuCheck(sumTo);
			    }//�������			

		private void calculateSumHuadong() throws Throwable {

			
			double cpuPrice = 0; 
			double memPrice = 0; 
			
			switch (seleCpu) { 	
			
			    case 0:cpuPrice = 0.0363; break; 
				case 1:cpuPrice = 0.1451; break; 
				case 2:cpuPrice = 0.3265; break; 
				case 3:cpuPrice = 0.6169; break; 
			}

			
			//��CPU��ͬ����ʱ���ڴ�ļ۸�	
					if (seleCpu == 0) { //1�ˣ��ڴ�۸�
						switch (memOneK) {
						case 0:memPrice = 0.0363; break; 
						case 1:memPrice = 0.1088; break; 
						case 2:memPrice = 0.2358; break; 
						 }
						} else if (seleCpu == 1) {//�����������ڴ�۸�
						
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
								System.out.println("��ֵ"); 
						
						}	
					
					System.out.println("cpu = " + cpuPrice); 
					System.out.println("mem4 = " + memPrice); 
					
					String strcpuPrice = String.valueOf(cpuPrice); 
					String strmemPrice = String.valueOf(memPrice); 
					pubMeth.rwFile("cpu�۸� = ", strcpuPrice, ""); 
					pubMeth.rwFile("mem�۸� = ", strmemPrice, ""); 
					
					//�ж�ѡ��������windowsϵͳ�����㰴Ӳ��50G��0.02������
					double sum = 0; 
					
					//����ǹ���
					if (seleNet  ==  0) {
						
						double a = 0.0403; 
						
						if (osWindows  ==  0) {//����CPU + �ڴ� + ϵͳӲ�̼� + ��̫-���ʴ���1MĬ�ϼ۸�
						//windows
						sum = cpuPrice + memPrice + 0.02 + a; 
						} else {
						//linux
						sum = cpuPrice + memPrice + 0.008 + a; 
						}
						
					} else {
					//���������	
						if (osWindows  ==  0) {//����CPU + �ڴ� + ϵͳӲ�̼�
						 //windows
						sum = cpuPrice + memPrice + 0.02; 
						} else {
						 //linux
						sum = cpuPrice + memPrice + 0.008; 
					    }
					}
					
					double sumPrepay = sum*24*30;
					//ȡС�������λ
					String sumTo = String.format("%.4f", sumPrepay); 
					pubMeth.calcuCheck(sumTo);
			    
			
		}
	}//����� 
		
		
	
				
			
			
		

		
	
	
	
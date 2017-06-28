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
* ���ഴ���Ʒ����������۸����
* @author yangw
* @version 1.00
*/
	public class CreateInstance {
	
		/**
		* �����Ʒ�����
		* @author yangw
		* @version 1.00
		*/
		int intRandomCpu = 0; 
		int intRandomMem = 0; 
		int intRandomOs = 0; //ѡ�����ϵͳ��Ҫ�������
		//String priceValue; 
		//boolean valueOk = false; 
		int oneKernel = 0;  //oneKernel=0ʱΪ1�ˣ�oneKernel=1ʱѡ����������intRandomCpu
		int memOneGi = 0;  //oneKernel=0ʱ��memOneGi=0ʱΪ1G,  memOneGi=1ʱΪ�����ڴ�twoGiOrFourGi��
		int otherKernelOneGi = 0;  //oneKernel=1ʱ��otherKernelOneGi=0ʱΪ1G��otherKernelOneGi=1ʱѡ�������ڴ�intRandomMem��
		int twoGiOrFourGi = 0;  //oneKernel=0��CPUΪ1�ˣ�memOneGi=1 ʱ��twoGiOrFourGiΪ2G����4G
		int osWindows = 0;  //osWindows=0��windows����ϵͳ
		int seleNet = 0;  //seleNet=1ʱΪ����
		int seleArea = 0;  //seleArea=1ʱ��̫һ��
		WebDriver driver ; 
		Base pubMeth = new Base(); 
			
	@Test
	public void createServer() throws Exception {
		
		driver = pubMeth.beforeTest(driver);
		
		for (int i = 1; i < 3; i++) {
			// ����Ʒ�����
			WebElement cloudServer = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-instance']"));
			cloudServer.click();
			Thread.sleep(3000);

			// ����һ��������̫һ��

			Random RandomseleArea = new Random();
			seleArea = RandomseleArea.nextInt(2); // Ϊ0-1����
			if (seleArea == 0) {
				WebElement yaTai = driver.findElement(By.xpath("//span[@data-testid='region-select-ac1']"));
				yaTai.click();
				Thread.sleep(3000);
				System.out.println("����һ��");
			} else {
				WebElement huaDong = driver.findElement(By.xpath("//span[@data-testid='region-select-ac2']"));
				huaDong.click();
				Thread.sleep(3000);
				System.out.println("��̫һ��");
			}

			Thread.sleep(3000);
			System.out.println("��̫һ��");

			// �½�
			WebElement creat = driver.findElement(By.xpath("//span[@class='color-primary']"));
			creat.click();
			Thread.sleep(3000);

			// ����
			// �ȶ�λdiv ul
			WebElement anXu = driver.findElement(By.xpath("//div[@class='mt-20 tabs-item']"));
			WebElement anXu1 = anXu.findElement(By.xpath("//li[@class='main-item']"));
			anXu1.click();
			Thread.sleep(3000);
			pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");

			// ϵͳ
			// �ȵõ�һ��classд��list, get(0)��Ҫ�ڼ���, ʹ��value�ķ���click
			java.util.List<WebElement> element = driver.findElements(By.xpath("//span[@class='tab-switch tab-switch-111 w90']"));

			WebElement valueOs = element.get(0);
			valueOs.click();
			Thread.sleep(3000);
			System.out.println(valueOs.getText());

			// ѡȡ�����б��еĲ���ϵͳ

			Random randomosWindows = new Random();
			osWindows = randomosWindows.nextInt(2);
			System.out.println("osWindows=" + osWindows);
			if (osWindows == 1) {

				// ��ϵͳ�����б�
				WebElement osDown = driver
						.findElement(By.xpath("//i[@class='triangle-down abs-right8 abs-mitwoGiOrFourGile']"));
				osDown.click();
				Thread.sleep(3000);

				// ���������
				Random randomos = new Random();
				intRandomOs = randomos.nextInt(7); // Ϊ0-6����

				java.util.List<WebElement> os = driver
						.findElements(By.xpath("//li[@class='dropdown-item single-line']"));
				WebElement indexOs = os.get(intRandomOs);
				indexOs.click();
				Thread.sleep(3000);
				System.out.println("ѡ���" + intRandomOs + "������ϵͳ");

			} else {
				System.out.println("ubuntu����ϵͳ");
				pubMeth.rwFile("OS=", "ubuntu����ϵͳ", "");
			}

			Random randomoneKernel = new Random();
			oneKernel = randomoneKernel.nextInt(2);
			// oneKernel=0ʱΪ1�ˣ�oneKernel=1ʱѡ����������intRandomCpu
			if (oneKernel == 1) {
				// ����4-6�����
				int max = 6;
				int min = 4;
				Random randomcpu = new Random();
				intRandomCpu = randomcpu.nextInt(max) % (max - min + 1) + min;
				System.out.println("CPU�����intRandomCpuΪ��" + intRandomCpu);
				// ѡȡ2.4.8���е�һ��
				// driver
				java.util.List<WebElement> cpuList = driver
						.findElements(By.xpath("//span[@class='tab-switch tab-switch-111 w90']"));
				int tab111num = cpuList.size();
				WebElement cpuValue = cpuList.get(intRandomCpu);
				cpuValue.click();
				Thread.sleep(3000);
				System.out.println(cpuValue.getText());
				pubMeth.rwFile("cpu=", cpuValue.getText(), "");
				// ѡȡ�ڴ�
				Random randomotherKernelOneGi = new Random();
				otherKernelOneGi = randomotherKernelOneGi.nextInt(2);
				System.out.println("otherKernelOneGi=" + otherKernelOneGi);
				if (otherKernelOneGi == 1) {
					// ����7-9�����
					int max1 = 9;
					int min1 = 7;
					Random randommem = new Random();
					intRandomMem = randommem.nextInt(max1) % (max1 - min1 + 1) + min1;
					System.out.println("�ڴ������intRandomMemΪ��" + intRandomMem);
					// driver
					java.util.List<WebElement> memList = driver
							.findElements(By.xpath("//span[@class='tab-switch tab-switch-111 w90']"));
					WebElement memValue = memList.get(intRandomMem);
					memValue.click();
					Thread.sleep(3000);
					pubMeth.rwFile("mem=", memValue.getText(), "");
				} else {
					pubMeth.rwFile("mem=", "��һ��λ��G", "");
				}

			} else {
				// ��ѡ��Ϊ1�ˣ���ʱ�ڴ�Ϊ1��2��4G�������
				
				pubMeth.rwFile("CPU=", "1��", "");

				Random randommemOneGi = new Random();
				memOneGi = randommemOneGi.nextInt(2);
				if (memOneGi == 1) {
					// ����7-8�����
					int max1 = 8;
					int min1 = 7;
					Random randommem1 = new Random();
					twoGiOrFourGi = randommem1.nextInt(max1) % (max1 - min1 + 1) + min1;
					System.out.println("�ڴ������Ϊ��" + twoGiOrFourGi);
					// ѡȡ2��4G�е�һ��
					// driver
					java.util.List<WebElement> memList = driver
							.findElements(By.xpath("//span[@class='tab-switch tab-switch-111 w90']"));
					WebElement memValue1 = memList.get(twoGiOrFourGi);
					memValue1.click();
					Thread.sleep(3000);
					System.out.println(memValue1.getText());
					pubMeth.rwFile("mem=", memValue1.getText(), "");
				} else {
					
					pubMeth.rwFile("mem=", "��һ��λ��G", "");
					// ��ѡ��Ϊ1G
				}
			}

			// ����HDD
			// atwoGiOrFourGihtwoGiOrFourGseleNetp();

			// �����������ѡ

			Random randomseleNet = new Random();
			seleNet = randomseleNet.nextInt(2);
			System.out.println("seleNet=" + seleNet);
			if (seleNet == 1) {
				// ѡ������������java.lang.IndexOutOfBoundsException
				System.out.println("����");
				// driver
				if (oneKernel == 1) {
					java.util.List<WebElement> netList = driver
							.findElements(By.xpath("//span[@class='tab-switch tab-switch-111 w90']"));
					int num = netList.size();
					System.out.println("����" + num);
					WebElement netValue = netList.get(10);
					netValue.click();
					Thread.sleep(3000);
					System.out.println(netValue.getText());
				} else {
					java.util.List<WebElement> netList = driver
							.findElements(By.xpath("//span[@class='tab-switch tab-switch-111 w90']"));
					int num1 = netList.size();
					System.out.println("����" + num1);
					WebElement netValue = netList.get(9);
					netValue.click();
					Thread.sleep(3000);
					System.out.println(netValue.getText());
				}

				pubMeth.rwFile("����Ϊ", "����", "");

				// �½�SDN����
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
				System.out.println("����SDN����");
			} else {
				
				pubMeth.rwFile("����Ϊ", "����", "");
			}

	
			// �������������
			pubMeth.inputName(driver, "autoserver" + i);
			
		
			// ����pwd
			WebElement pwd = driver.findElement(By.xpath("//input[@class='input-text input-long mr10']"));
			pwd.sendKeys("Anchang123");
			Thread.sleep(3000);
		
			// �ٴ�����pwd
			java.util.List<WebElement> rePwd = driver.findElements(By.xpath("//input[@class='input-text input-long']"));
			WebElement rePwdValue = rePwd.get(2);
			rePwdValue.sendKeys("Anchang123");
			// int num = repwd.size();
			// System.out.println(num);
			Thread.sleep(3000);
			
			// ȡҳ����ʾ�ļ۸�
			pubMeth.UIprice(driver);
			
			// �ύ
			WebElement submit = driver.findElement(By.xpath("//div[@class='btn-big']"));
			submit.click();
			Thread.sleep(30000);
			System.out.println("������ͨ");

			// �ж���û�н�������ȡ�÷���ֵre

			By locator = By.xpath("//i[@data-testid='table-row-0-checkbox']");
			boolean reValue = pubMeth.isElementExsit(driver, locator);
			System.out.println(reValue);

			calculateSum();

			// �������н��д��LOG�ļ�
			
			pubMeth.writeLog("createVolume",reValue);
		} // for

		driver.close();
	}// ������������������
		
		
		/*/ѡ�񴴽�HDD
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
			System.out.println("����HDD"); 	
		}
		*/
		
		/**
		* ����۸񣬼�����ѡ���CPU���ڴ�ȵó��ܼ۸�
		* @author yangw
		* @version 1.00
		 * @throws Exception 
		*/	
		private void calculateSum() throws Exception {
			double cpuPrice = 0; 
			double memPrice = 0; 
			
			//��CPUΪ1��ʱ��CPU���ڴ�ĵ���
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
			//��CPU������Ϊ1��ʱ��CPU����	
					switch (intRandomCpu) {
						case 4:cpuPrice = 0.1075; break; 
						case 5:cpuPrice = 0.2419; break; 
						case 6:cpuPrice = 0.4570; break; 
					 }
			//��CPU������Ϊ1��ʱ���ڴ浥��		
					if (otherKernelOneGi == 0) { //������������һ��λ���ڴ�
						switch (intRandomCpu) {
						case 4:memPrice=0.0269; break; 
						case 5:memPrice=0.0806; break; 
						case 6:memPrice=0.1747; break; 
						}
					 } else {
						//��������������λ���ڴ�
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
							System.out.println("intRandomCpu������4.5.6"); 
						}
						
					}
			
				}
	
			//�ж�ѡ��������windowsϵͳ�����㰴Ӳ��50G��0.02������
			double sum = 0; 
			System.out.println("cpu=" + cpuPrice); 
			System.out.println("mem=" + memPrice); 
			
			//����ǹ���
			if (seleNet == 0) {
				if(osWindows == 1) {
				//���seleArea==0Ϊ����һ����seleArea==1Ϊ��̫һ���������۸�ͬ
				double a = 0; 
				if (seleArea == 0) { 
					a = 0.0278; 
				} else { 
					a = 0.0269; 
				}
				if (intRandomOs == 0) {//����CPU + �ڴ� + ϵͳӲ�̼� + ��̫�������ʴ���1MĬ�ϼ۸�
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
			//���������	
			if (osWindows == 1) {	//��Ϊwindow����ϵͳλ�ñ�Ϊ�ڶ������Ķ�
				if (intRandomOs == 0) {//����CPU + �ڴ� + ϵͳӲ�̼�
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
			//ȡС�������λ
			String sumTo = String.format("%.4f", sum); 
			
			pubMeth.calcuCheck(sumTo);
			
	    }//���㺯������
		

  }
		
	
				
			
			
		

		
	
	
	
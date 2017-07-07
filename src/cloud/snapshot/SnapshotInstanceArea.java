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
	 * �����Ʒ��������ձ���
	 * 
	 * @author yangw
	 * @version 1.00
	 */

	int intRandomOs = 0;// ѡ�����ϵͳ��Ҫ�������
	String priceValue;
	String priceValueQu;
	boolean valueOk = false;
	int osWindows = 0;  //osWindows = 0��windows����ϵͳ
	int seleArea = 0;  //seleArea = 1ʱ��̫һ��
	int seleCpu = 0; 	//cpuѡ��
	int memOneK = 0;  //CPUΪ1Kʱ���ڴ�
	int memTwoK = 0;  //CPUΪ2Kʱ���ڴ�
	int memFourK = 0;  //CPUΪ4Kʱ���ڴ�
	int memEightK = 0;  //CPUΪ8Kʱ���ڴ�
	int seleNet = 0;  //���������������
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void backupServer() throws Exception {
		
		driver = pubMeth.beforeTest(driver);
		cloud2Instance();
		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
		// ����Ʒ�����
		WebElement cloudserver = driver.findElement(By.xpath("//a[@data-testid = 'sidebarNav-cloud-instance']"));
		cloudserver.click();
		Thread.sleep(3000);	

		// ѡ�񻪶�һ��
		WebElement yatai = driver.findElement(By.xpath("//span[@data-testid = 'region-select-ac1']"));
		yatai.click();
		Thread.sleep(3000);
		System.out.println("����һ��");
		pubMeth.rwFile("����Ϊ", "����һ��", "");
		
		// �жϷ�������û�н�������ȡ�÷���ֵre
		Base pubMeth = new Base();
		By locator = By.xpath("//i[@data-testid = 'table-row-0-checkbox']");
		boolean reValue = pubMeth.isElementExsit(driver, locator);
		System.out.println("reValue = " + reValue);

		if (reValue) {
			// if (re = false) {break;}

			// ѡ�񽨺õķ�����
			WebElement serverok = driver.findElement(locator);
			serverok.click();
			Thread.sleep(2000);
			
			// �������
			WebElement more = driver.findElement(By.xpath("//span[@data-testid = 'moreOpts']"));
			more.click();
			Thread.sleep(2000);
			
			// �������ձ���
			WebElement createbk = driver.findElement(By.xpath("//span[@data-testid = 'moreOpts-createSnapshot']"));
			createbk.click();
			Thread.sleep(2000);
			System.out.println("�������ձ���");

			// ������ձ�������
			WebElement servername = driver.findElement(By.xpath("//input[@data-testid = 'createSnapShot-name']"));
			servername.sendKeys("serverbk");
			Thread.sleep(3000);
			
			// �ύ
			WebElement submit = driver.findElement(By.xpath("//span[@data-testid = 'pop-model-confirm']"));
			submit.click();
			Thread.sleep(10000);
			System.out.println("�ύ");

			// �����ձ���
			WebElement bk = driver.findElement(By.xpath("//a[@data-testid = 'sidebarNav-cloud-snapshot']"));
			bk.click();
			Thread.sleep(3000);
			System.out.println("�����ձ���");

			// �жϿ��ձ�����û�н�������ȡ�÷���ֵrebk

			By locatorbk = By.xpath("//tbody[@data-testid = 'table-row-0']");
			boolean reValue1 = Base.isElementExsit(driver, locatorbk);
			System.out.println("reValue1 =" + reValue1);

			// ѡ��������ձ���
			WebElement bkid = driver.findElement(By.xpath("//span[@data-testid = 'table-row-0-id']"));
			bkid.click();
			Thread.sleep(5000);

			// ȡҳ����ʾ�ļ۸�
			WebElement pricelast = driver.findElement(By.xpath("//div[@class='description']"));
			java.util.List<WebElement> pricelast1 = pricelast.findElements(By.xpath("//div[@class='detail-item']"));
		//	int pricesize pricelast1.size();
			WebElement pricelast2=pricelast1.get(2);
			priceValue = pricelast2.getText();
			priceValueQu = priceValue.substring(41, priceValue.length() - 32);
			System.out.println("ҳ��ȡ�õļ۸�Ϊ = " + priceValueQu);
			pubMeth.rwFile("ҳ��۸� = ", priceValueQu, "");
			
			// ѡ��������
			WebElement atwoGiOrFourGibk = driver.findElement(By.xpath("//span[@data-testid = 'createSnapshot-btn']"));
			atwoGiOrFourGibk.click();
			Thread.sleep(3000);

			// ��������������
			WebElement bkname = driver.findElement(By.xpath("//input[@data-testid = 'createSnapShot-name']"));
			bkname.sendKeys("serverbkatwoGiOrFourGi");
			Thread.sleep(3000);
			
			// �ύ
			WebElement submitbk = driver.findElement(By.xpath("//span[@data-testid = 'pop-model-confirm']"));
			submitbk.click();
			Thread.sleep(10000);
			System.out.println("�ύ");

			calculateSum();

			// �������н��д��LOG�ļ�
			String cases = "SnapshotInstance";
			
			//ȡ�õ�ǰ����
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
			
			//����Ѿ����������Һ˶Եļ۸���ȷ��Pass�Ľ��д���ļ�
			if (reValue1 && valueOk){
				
				pubMeth.rwFile(cases,df.format(new Date()),"Pass");
			
			} else { 
				pubMeth.rwFile(cases,df.format(new Date()),"Fail");
			}

		} else {
			System.out.println("û�з�����");
		}

		driver.quit();
	}

	/**
	 * �����Ʒ�����
	 * 
	 * @author yangw
	 * @version 1.00
	 * @throws Exception 
	 */
	private void cloud2Instance() throws Exception {
		
		for (int i = 1; i <2; i++) {
			// ����Ʒ�����
			WebElement cloudServer = driver.findElement(By.xpath("//a[@data-testid = 'sidebarNav-cloud-instance']"));
			cloudServer.click();
			Thread.sleep(3000);
			pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");

			// ����һ��������̫һ��

			//Random RandomseleArea = new Random();
			//seleArea = RandomseleArea.nextInt(2); // Ϊ0-1����
			seleArea = 0;
			pubMeth.seleAreaall(driver, seleArea);
			
			// �½�	
			driver = pubMeth.newOne(driver);
	
			// ����
			pubMeth.anXu(driver);

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
				
				WebElement osList = driver.findElement(By.xpath("//li[@data-testid = '" + testid[kk] + "']"));

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

						WebElement mem = driver.findElement(By.xpath("//span[@data-testid = '" + testid4[memFourK] + "']"));
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
			pubMeth.inputName(driver, "autoserverforbk");
			
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
			
			// �ύ
			WebElement submit = driver.findElement(By.xpath("//div[@class = 'btn-big']"));
			submit.click();
			Thread.sleep(20000);
			System.out.println("������ͨ");

			// �ж���û�н�������ȡ�÷���ֵre
			By locator = By.xpath("//i[@data-testid = 'table-row-0-checkbox']");
			boolean reValue = pubMeth.isElementExsit(driver, locator);
			System.out.println(reValue);

			pubMeth.writeLog("createInstanceId",reValue);

		} // for

	}

	/**
	 * ������ձ��ݼ۸�
	 * 
	 * @author yangw
	 * @version 1.00
	 * @throws Exception 
	 */
	private void calculateSum() throws Exception {

		double sum = 0;
		if (osWindows == 1) {// ��ѡ��������ϵͳʱ
			if (intRandomOs == 0) {// ��ѡ���0������ϵͳ����windowsʱ
				sum = 0.004 * 5;
			} else {
				sum = 0.004 * 2;
			}
		} else {// ����ѡʱΪubuntu
			sum = 0.004 * 2;
		}

		// ȡС�������λ
		//String sumTo = String.format("%.4f", sum);
		String sumTo = String.valueOf(sum);
		System.out.println("����ֵ=" + sumTo); 
		pubMeth.rwFile("����ֵ=", sumTo, ""); 
		//�����ֵ��ҳ��ȡֵ�Ƚ��Ƿ����
		valueOk = sumTo.equals(priceValueQu); 
		if (valueOk) {
			System.out.println("price sum is correctly"); 
			pubMeth.rwFile("��� = ", "price sum is correctly", ""); 
		} else {
			System.out.println("no correctly"); 
			pubMeth.rwFile("��� = ", "not correctly", ""); 
		}	

	}

}

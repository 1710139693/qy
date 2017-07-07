package cloud.router;
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
import common.Login;
import common.Base;

public class RouterSetting {

	/**
	 * ������·����
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	// �˽ű������˹���IP�ļ۸����Բ�����δʹ�õĹ���IP��Դ����
	//String priceValue;
	//boolean valueOk = false;
	int seleArea = 0; // seleArea=1ʱ��̫һ��
	int typeSmail = 0; // ����ѡ��
	int size = 1; // ����
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void RouterSettingall() throws Exception {
		
		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
		driver = pubMeth.beforeTest(driver);
		seleArea = 0;
		CreateRouter(seleArea);
		RouterSetting();
	//	RouterSetting(1);
	//	RouterSetting(2);
		driver.quit();
		
	}
	
		private void RouterSetting() throws Exception {
			
			// �����·����
			WebElement cloudserver = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-router']"));
			cloudserver.click();
			Thread.sleep(10000);
			
			//ѡ�񻪶�һ��
			seleArea = 0;
			pubMeth.seleAreaall(driver, seleArea);
			
			// �ж���û�н�������ȡ�÷���ֵre
			By locator = By.xpath("//a[@data-testid='table-row-0-id']");
			boolean reValueSetting = pubMeth.isElementExsit(driver, locator);
			System.out.println(reValueSetting);
			
			if (reValueSetting) {
			// ѡ����·����
			WebElement selectrouter = driver.findElement(locator);
			selectrouter.click();
			Thread.sleep(2000);
			
			} else {
				System.out.println("û��·����");
			}
			//·������
			// ѡ��·������
			WebElement network = driver.findElement(By.xpath("//a[@data-testid='tabs-1']"));
			network.click();
			Thread.sleep(2000);
						
			// �½�·������
			pubMeth.newOnecreat(driver);
	
			// ����������
			WebElement inputnet = driver.findElement(By.xpath("//input[@data-testid='pop-network']"));
			inputnet.clear();
			inputnet.sendKeys("99");
			Thread.sleep(2000);
		
			//�ύ
			pubMeth.submit(driver);
			System.out.println("·�������ύ");
		
		
		
		
			//�˿�ת��
			// ѡ��˿�ת��
			WebElement portsend = driver.findElement(By.xpath("//a[@data-testid='tabs-2']"));
			portsend.click();
			Thread.sleep(2000);
		
			pubMeth.newOnecreat(driver);
			
			// ����˿�����
			WebElement inputname = driver.findElement(By.xpath("//input[@data-testid='pop-name']"));
			inputname.clear();
			inputname.sendKeys("autoport");
			Thread.sleep(2000);
			
			// ����˿ں� 
			WebElement inputport = driver.findElement(By.xpath("//input[@data-testid='pop-sourcePort']"));
			inputport.clear();
			inputport.sendKeys("22");
			Thread.sleep(2000);
			
			// ����Ŀ��IP
			WebElement inputip = driver.findElement(By.xpath("//input[@data-testid='pop-ip']"));
			inputip.clear();
			inputip.sendKeys("192.168.99.2");
			Thread.sleep(2000);
			
			// ����˿ں� 
			WebElement inputport2 = driver.findElement(By.xpath("//input[@data-testid='pop-targetPort']"));
			inputport2.clear();
			inputport2.sendKeys("22");
			Thread.sleep(2000);
			
			//�ύ
			pubMeth.submit(driver);
			System.out.println("�˿�ת���ύ");
			
			
			
			
			
			//�������
			// ѡ���������
			WebElement tunnel = driver.findElement(By.xpath("//a[@data-testid='tabs-3']"));
			tunnel.click();
			Thread.sleep(2000);
		
			pubMeth.newOne(driver);
			
			// ����˿�����
			WebElement inputtunname = driver.findElement(By.xpath("//input[@data-testid='pop-name']"));
			inputtunname.clear();
			inputtunname.sendKeys("autotunnel");
			Thread.sleep(2000);
			
			// ����Զ��·��
			WebElement inputrouter = driver.findElement(By.xpath("//input[@data-testid='pop-router']"));
			inputrouter.clear();
			inputrouter.sendKeys("10.20.30.2");
			Thread.sleep(2000);
			
			// ���뱾�ص�Ե�IP
			WebElement localip = driver.findElement(By.xpath("//input[@data-testid='pop-local_ip']"));
			localip.clear();
			localip.sendKeys("1");
			Thread.sleep(2000);
			
			// ����Զ˵�Ե�IP
			WebElement peerip = driver.findElement(By.xpath("//input[@data-testid='pop-targetIP']"));
			peerip.clear();
			peerip.sendKeys("2");
			Thread.sleep(2000);
						
			// ����Ŀ������
			WebElement peernet = driver.findElement(By.xpath("//input[@data-testid='pop-targetIPGroup-0']"));
			peernet.clear();
			peernet.sendKeys("192.168.200.0/24");
			Thread.sleep(2000);
			
			//�ύ
			pubMeth.submit(driver);
			
			// Ӧ���޸�
			pubMeth.appMdf(driver);
								
			//VPN����
			// ѡ��VPN����
			WebElement vpn = driver.findElement(By.xpath("//a[@data-testid='tabs-4']"));
			vpn.click();
			Thread.sleep(2000);
		
			//pubMeth.newOne(driver);
			
			WebElement newcreate = driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[1]/div[2]/div[3]/div[2]/div[1]/div/div[2]/div[2]/div[2]/div/span[2]/span"));
			newcreate.click();
			Thread.sleep(2000);
			
			// ����vpn
			WebElement vpnname = driver.findElement(By.xpath("//input[@data-testid='pop-name']"));
			vpnname.clear();
			vpnname.sendKeys("autopptp");
			Thread.sleep(2000);
						
			// ����vpn����
			WebElement vpnpwd = driver.findElement(By.xpath("//input[@data-testid='pop-password']"));
			vpnpwd.clear();
			vpnpwd.sendKeys("Anchang123");
			Thread.sleep(2000);
			
			//�ύ
			pubMeth.submit(driver);
			System.out.println("vpn�ύ");
			
			
			
			

			//���˿���
			// ѡ����˿���
			WebElement filter = driver.findElement(By.xpath("//a[@data-testid='tabs-5']"));
			filter.click();
			Thread.sleep(2000);
		
			pubMeth.newOnecreat(driver);
			
			// ��������
			WebElement filtername = driver.findElement(By.xpath("//input[@data-testid='pop-name']"));
			filtername.clear();
			filtername.sendKeys("autofilter");
			Thread.sleep(2000);
			
			// �������ȼ�
			WebElement detail = driver.findElement(By.xpath("//input[@data-testid='pop-level']"));
			detail.clear();
			detail.sendKeys("1");
			Thread.sleep(2000);
			
			// ����ԴIP
			WebElement sourceip = driver.findElement(By.xpath("//input[@data-testid='pop-sourceIP']"));
			sourceip.clear();
			sourceip.sendKeys("190.168.100.2");
			Thread.sleep(2000);
						
			//�ύ
			pubMeth.submit(driver);
			System.out.println("���˿����ύ");
			
			
			
			//�߼�����
			// ѡ��߼�����
			WebElement highsetting = driver.findElement(By.xpath("//a[@data-testid='tabs-6']"));
			highsetting.click();
			Thread.sleep(2000);
		
			// �޸ĸ߼�����
			WebElement modify = driver.findElement(By.xpath("//span[@data-testid='table-modify']"));
			modify.click();
			Thread.sleep(2000);
			
			// ��������
			WebElement inputnumber = driver.findElement(By.xpath("//input[@data-testid='pop-mss']"));
			inputnumber.clear();
			inputnumber.sendKeys("1200");
			Thread.sleep(2000);
			
			//�ύ
			pubMeth.submit(driver);
			System.out.println("�߼������ύ");
			
			// Ӧ���޸�
			pubMeth.appMdf(driver);
		}	
		
		
		private void CreateRouter(int seleArea) throws Exception {	
		
		for (int i = 1; i < 2; i++) {
			// �����·����
			WebElement cloudserver = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-router']"));
			cloudserver.click();
			Thread.sleep(2000);

			// �½�
			WebElement creat = driver.findElement(By.xpath("//span[@class='color-primary']"));
			creat.click();
			Thread.sleep(2000);
			
			// ����
			// �ȶ�λdiv ul
			WebElement anxu = driver.findElement(By.xpath("//div[@class='mt-20 tabs-item']"));
			WebElement anxu1 = anxu.findElement(By.xpath("//li[@class='main-item']"));
			anxu1.click();
			Thread.sleep(3000);
			pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");

			// ѡ���������е�һ��
			//Random RandomseleArea = new Random();
			//seleArea = RandomseleArea.nextInt(3);// Ϊ0-1����
			pubMeth.seleAreaall(driver, seleArea);
			
			// ����·��������
			
			pubMeth.inputName(driver, "autorouter" + i);
			
			// ����
			Random RandomtypeSmail = new Random();
			typeSmail = RandomtypeSmail.nextInt(2);// Ϊ0-1����
			if (typeSmail == 0) {
				WebElement seletype = driver.findElement(By.xpath("//span[@data-testid='radio-type-0']"));
				seletype.click();
				Thread.sleep(2000);
				System.out.println("����");
			} else {
				WebElement seletype = driver.findElement(By.xpath("//span[@data-testid='radio-type-1']"));
				seletype.click();
				Thread.sleep(2000);
			}

			// ����
			WebElement storage = driver.findElement(By.xpath("//input[@data-testid='customSlideNum']"));
			storage.clear();
			// 1-5M
			int max1 = 5;
			int min1 = 1;
			Random randoma = new Random();
			size = randoma.nextInt(max1) % (max1 - min1 + 1) + min1;
			System.out.println("size=" + size);
			storage.clear();

			storage.sendKeys(+size + "");
			Thread.sleep(2000);
			System.out.println("�������" + size + "M");
			String strsize = String.valueOf(size);
			pubMeth.rwFile("����=", strsize, "M");

			// ȡҳ����ʾ�ļ۸�
			//pubMeth.UIprice(driver);
			
			// �ύ
			WebElement submit = driver.findElement(By.xpath("//div[@data-testid='buy-button']"));
			submit.click();
			Thread.sleep(20000);
			System.out.println("�ύ");

			// �ж���û�н�������ȡ�÷���ֵre
			By locator = By.xpath("//i[@data-testid='table-row-0-checkbox']");
			boolean reValue = pubMeth.isElementExsit(driver, locator);
			System.out.println(reValue);

			// �������н��д��LOG�ļ�
			
			pubMeth.writeLog("CreateRouter",reValue);


		} // for

	}// ������������������



	
}

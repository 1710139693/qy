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
	 * �������ݿ���ձ���
	 * 
	 * @author yangw
	 * @version 1.00
	 */

	String priceValue;
	String priceValueQu;
	boolean valueOk = false;
	int intRandomOs = 0;// ѡ�����ϵͳ��Ҫ�������
	int seleCpuMem = 0; // seleCpuMemѡ��CPU�ڴ�
	int seleArea = 0; // seleArea=1ʱ��̫һ��
	int size = 0; // size=1����ȡ20G
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void backupRdbs() throws Exception {

		driver = pubMeth.beforeTest(driver);
		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
		createRdbs();

		// ��������ݿ�
		WebElement cloudrbs = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-rdbs']"));
		cloudrbs.click();
		Thread.sleep(3000);
		
		// ѡ�񻪶�һ��
		WebElement yatai = driver.findElement(By.xpath("//span[@data-testid='region-select-ac1']"));
		yatai.click();
		Thread.sleep(3000);
		System.out.println("����һ��");
		pubMeth.rwFile("����Ϊ", "����һ��", "");

		// �ж���û�н�������ȡ�÷���ֵre
		Base pubMeth = new Base();
		By locator = By.xpath("//i[@data-testid='table-row-0-checkbox']");
		boolean reValue = pubMeth.isElementExsit(driver, locator);
		System.out.println("reValue=" + reValue);

		if (pubMeth.isElementExsit(driver, locator)) {
			// if (re = false){break;}

			// ѡ�񽨺õ����ݿ�
			WebElement serverok = driver.findElement(locator);
			serverok.click();
			Thread.sleep(2000);
			
			// �������
			WebElement more = driver.findElement(By.xpath("//span[@data-testid='moreOpts']"));
			more.click();
			Thread.sleep(2000);
			
			// �������ձ���
			WebElement createbk = driver.findElement(By.xpath("//span[@data-testid='moreOpts-createSnapshot']"));
			createbk.click();
			Thread.sleep(2000);
			

			// ������ձ�������
			WebElement servername = driver.findElement(By.xpath("//input[@data-testid='createSnapShot-name']"));
			servername.sendKeys("rdbbk");
			Thread.sleep(3000);
			

			// �ύ
			WebElement submit = driver.findElement(By.xpath("//span[@data-testid='pop-model-confirm']"));
			submit.click();
			Thread.sleep(20000);
			System.out.println("�ύ");

			// �����ձ���
			WebElement bk = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-snapshot']"));
			bk.click();
			Thread.sleep(5000);
			System.out.println("�����ձ���");

			// �ж���û�н�������ȡ�÷���ֵrebk

			By locatorbk = By.xpath("//tbody[@data-testid='table-row-0']");
			boolean reValue1 = Base.isElementExsit(driver, locatorbk);
			System.out.println("reValue1=" + reValue1);

			// ѡ��������ձ���
			WebElement bkid = driver.findElement(By.xpath("//span[@data-testid='table-row-0-id']"));
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
			WebElement addbk = driver.findElement(By.xpath("//span[@data-testid='createSnapshot-btn']"));
			addbk.click();
			Thread.sleep(3000);
			
			// ��������������
			WebElement bkname = driver.findElement(By.xpath("//input[@data-testid='createSnapShot-name']"));
			bkname.sendKeys("rbsbkadd");
			Thread.sleep(3000);

			// �ύ
			WebElement submitbk = driver.findElement(By.xpath("//span[@data-testid='pop-model-confirm']"));
			submitbk.click();
			Thread.sleep(10000);
			System.out.println("�ύ");

			calculateSum();

			// �������н��д��LOG�ļ�
			String cases = "SnapshotRdbs";
			
			//ȡ�õ�ǰ����
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
			
			//����Ѿ����������Һ˶Եļ۸���ȷ��Pass�Ľ��д���ļ�
			if (reValue1 && valueOk){
				
				pubMeth.rwFile(cases,df.format(new Date()),"Pass");
			
			} else { 
				pubMeth.rwFile(cases,df.format(new Date()),"Fail");
			}

		} else {
			System.out.println("û�����ݿ�");
		}

		driver.close();
	}

	/**
	 * �������ݿ�
	 * 
	 * @author yangw
	 * @version 1.00
	 */

	private void createRdbs() throws InterruptedException {
		// ������ݿ�
		WebElement cloudserver = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-rdbs']"));
		cloudserver.click();
		Thread.sleep(2000);

		// ����һ��������̫һ��
		// ������Ҫ�Ѿ�����·����
		Random RandomseleArea = new Random();
		seleArea = RandomseleArea.nextInt(1);// Ϊ0-1����
		if (seleArea == 0) {
			WebElement yatai = driver.findElement(By.xpath("//span[@data-testid='region-select-ac1']"));
			yatai.click();
			Thread.sleep(5000);
			System.out.println("����һ��");
		} else {
			WebElement huadong = driver.findElement(By.xpath("//span[@data-testid='region-select-ac1']"));
			huadong.click();
			Thread.sleep(5000);
			System.out.println("��̫һ��");
		}
		// �½�
		WebElement creat = driver.findElement(By.xpath("//span[@class='color-primary']"));
		creat.click();
		Thread.sleep(2000);
		

		// ��DB����ϵͳ����
		WebElement dbos = driver.findElement(By.xpath("//div[@class='single-label single-line']"));
		dbos.click();
		Thread.sleep(2000);
		

		// ѡȡ�����б��еĲ���ϵͳ
		// �ȵõ�һ��classд��list,get(0)��Ҫ�ڼ���,ʹ��value�ķ���click

		Random randomos = new Random();
		intRandomOs = randomos.nextInt(4);// Ϊ0-3����

		java.util.List<WebElement> element = driver.findElements(By.xpath("//li[@class='dropdown-item single-line']"));
		WebElement value = element.get(intRandomOs);
		value.click();
		Thread.sleep(2000);
		System.out.println("intRandomOs=" + intRandomOs);

		String str = intRandomOs + ""; // ����ת�����ַ���
		pubMeth.rwFile("DBOSΪ��", str, "�����ݿ�");

		// ѡ��CPU���ڴ�
		// ���������
		Random randomocpu = new Random();
		seleCpuMem = randomocpu.nextInt(5);// Ϊ0-3����
		String Strdb = String.valueOf(Math.pow(2, seleCpuMem));

		System.out.println("cpu�����Ϊll=" + seleCpuMem);
		pubMeth.rwFile("cpuΪ", Strdb, "��");

		String[] testid1 = new String[5];
		testid1[0] = "tabSwitch-config-K1G2";
		testid1[1] = "tabSwitch-config-K2G4";
		testid1[2] = "tabSwitch-config-K4G8";
		testid1[3] = "tabSwitch-config-K8G16";
		testid1[4] = "tabSwitch-config-K8G32";

		WebElement db = driver.findElement(By.xpath("//span[@data-testid='" + testid1[seleCpuMem] + "']"));
		db.click();
		Thread.sleep(3000);

		// ����
		Random Randomsize = new Random();
		size = Randomsize.nextInt(2);// Ϊ0-1����
		if (size == 1) {
			WebElement storage = driver.findElement(By.xpath("//input[@class='num-wrapper']"));
			storage.clear();
			storage.sendKeys("20");
			Thread.sleep(2000);
			
			pubMeth.rwFile("����Ϊ", "", "20");
		} else {
			
			pubMeth.rwFile("����Ϊ", "", "10");
		}

		// �������������
		WebElement servername = driver.findElement(By.xpath("//input[@class='input-text input-long']"));
		servername.sendKeys("autodb");
		Thread.sleep(2000);
		

		// �û���
		WebElement username = driver.findElement(By.xpath("//input[@class='input-text input-long mr10']"));
		username.sendKeys("sa123456");
		Thread.sleep(2000);
		

		// ����pwd
		java.util.List<WebElement> pwd = driver.findElements(By.xpath("//input[@class='input-text input-long mr10']"));
		// int num = pwd.size();
		// System.out.println(num);
		WebElement pwdvalue = pwd.get(1);
		pwdvalue.sendKeys("Anchang123");
		Thread.sleep(2000);
		

		// �ٴ�����pwd
		java.util.List<WebElement> repwd = driver.findElements(By.xpath("//input[@class='input-text input-long']"));
		// int num1 = repwd.size();
		// System.out.println(num1);
		WebElement repwdvalue = repwd.get(1);
		repwdvalue.sendKeys("Anchang123");
		Thread.sleep(2000);
		

		// �ύ
		WebElement submit = driver.findElement(By.xpath("//div[@class='btn-big']"));
		submit.click();
		Thread.sleep(40000);
		System.out.println("������ͨ");

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
		// CreateRbs_id calcu = new CreateRbs_id();
		if (size == 1) {

			sum = 0.004 * 2;

		} else {

			sum = 0.004;
		}

		// ȡС�������λ
		// String sumTo = String.format("%.4f", sum);
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

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
@Test
public class SnapshotVolume {
	
	/**
	 * �������ձ���
	 * 
	 * @author yangw
	 * @version 1.00
	 */

	String priceValue;
	String priceValueQu;
	boolean valueOk = false;
	int typeRandom = 0; // ѡ������
	int seleArea = 0; // seleArea=1ʱ��̫һ��
	int size = 1;// ����ѡ��3-10
	int numRandom = 0;// ����ѡ��Ŀǰѡ1
	String digit; // ѡ����0����00
	String type;// ����
	WebDriver driver;
	Base pubMeth = new Base();

	
	public SnapshotVolume() throws Throwable {

		driver = pubMeth.beforeTest(driver);
		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
		createVolume();

		// �����Ӳ��
		WebElement cloudrbs = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-volume']"));
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
			// if (reValue = false){break;}

			// ѡ�񽨺õ�Ӳ��
			WebElement serverok = driver.findElement(locator);
			serverok.click();
			Thread.sleep(2000);
			
			// �������
			WebElement more = driver.findElement(By.xpath("//span[@data-testid='moreOpts']"));
			more.click();
			Thread.sleep(2000);
			
			// �������ձ���
			WebElement createbk = driver.findElement(By.xpath("//li[@data-testid='moreOpts-createSnapshot']"));
			createbk.click();
			Thread.sleep(2000);
			

			// ������ձ�������
			WebElement servername = driver.findElement(By.xpath("//input[@data-testid='createSnapShot-name']"));
			servername.sendKeys("hddbk");
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
			

			// ȡ�����еļ۸�
			WebElement pricelast = driver.findElement(By.xpath("//div[@class='description']"));
			java.util.List<WebElement> pricelast1 = pricelast.findElements(By.xpath("//div[@class='detail-item']"));
		//	int pricesize pricelast1.size();
			WebElement pricelast2=pricelast1.get(2);
			priceValue = pricelast2.getText();
			priceValueQu = priceValue.substring(41, priceValue.length() - 32);
			System.out.println("ҳ��ȡ�õļ۸�Ϊ =" + priceValueQu);
			pubMeth.rwFile("ҳ��۸� =", priceValueQu, "");
			
			
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
			String cases = "SnapshotVolume";
			
			//ȡ�õ�ǰ����
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
			
			//����Ѿ����������Һ˶Եļ۸���ȷ��Pass�Ľ��д���ļ�
			if (reValue1 && valueOk){
				
				pubMeth.rwFile(cases,df.format(new Date()),"Pass");
			
			} else { 
				pubMeth.rwFile(cases,df.format(new Date()),"Fail");
			}
		} else {
			System.out.println("û��Ӳ��");
		}

		driver.close();
	}

	/**
	 * ������Ӳ��
	 * 
	 * @author yangw
	 * @version 1.00
	 * @throws Throwable 
	 */
	private void createVolume() throws Throwable {

		// �����Ӳ��
		WebElement cloudserver = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-volume']"));
		cloudserver.click();
		Thread.sleep(2000);
		System.out.println("�����Ӳ��");

		// ����һ��������̫һ��

		Random RandomseleArea = new Random();
		seleArea = RandomseleArea.nextInt(1);// Ϊ0-1����
		if (seleArea == 0) {
			WebElement yatai = driver.findElement(By.xpath("//span[@data-testid='region-select-ac1']"));
			yatai.click();
			Thread.sleep(3000);
			System.out.println("����һ��");
			pubMeth.rwFile("����һ��", "", "");
		} else {
			WebElement huadong = driver.findElement(By.xpath("//span[@data-testid='region-select-ac2']"));
			huadong.click();
			Thread.sleep(3000);
			System.out.println("��̫һ��");
			pubMeth.rwFile("��̫һ��", "", "");
		}

		// �½�
		// WebElement
		// creat=driver.findElement(By.xpath("//span[@class='color-primary']"));
		WebElement newhdd = driver.findElement(By.xpath("//span[@data-testid='create']"));
		newhdd.click();
		Thread.sleep(2000);
		System.out.println("�½�");

		// ����
		pubMeth.anXu(driver);

		// ����Ӳ������
		WebElement servername = driver.findElement(By.xpath("//input[@class='input-text input-long']"));
		servername.clear();
		servername.sendKeys("autohddforbk");
		Thread.sleep(2000);
		System.out.println("����Ӳ������autohdd");

		// Ӳ������
		WebElement num = driver.findElement(By.xpath("//input[@data-testid='selectNum-input']"));
		num.clear();

		int max = 1;
		int min = 1;
		Random randomcpu = new Random();
		numRandom = randomcpu.nextInt(max) % (max - min + 1) + min;

		num.sendKeys(+numRandom + "");
		Thread.sleep(2000);
		System.out.println("ѡ��" + numRandom + "��Ӳ��");
		String strnumRandom = String.valueOf(numRandom);
		pubMeth.rwFile("Ӳ��=", strnumRandom, "��");

		// ����
		Random RandomtypeRandom = new Random();
		typeRandom = RandomtypeRandom.nextInt(2);// һ������������ͣ���λ��10�ģ�һ������������ͣ���λ��100��
		if (typeRandom == 1) {
			type = "radio-0";
			digit = "0";

		} else {
			type = "radio-1";
			digit = "00";
		}

		WebElement seletype = driver.findElement(By.xpath("//span[@data-testid='" + type + "']"));
		seletype.click();
		Thread.sleep(3000);
		System.out.println("����");

		// ����
		WebElement storage = driver.findElement(By.xpath("//input[@data-testid='customSlideNum']"));
		storage.clear();

		int max1 = 10;
		int min1 = 3;
		Random randoma = new Random();
		size = randoma.nextInt(max1) % (max1 - min1 + 1) + min1;
		storage.clear();

		System.out.println("digit=" + digit);
		storage.sendKeys(+size + digit);
		Thread.sleep(3000);
		System.out.println("��������" + size + digit + "G");
		String strsize = String.valueOf(size);
		pubMeth.rwFile("����=", strsize + digit, "G");

		// �ύ
		WebElement submit = driver.findElement(By.xpath("//span[@data-testid='pop-model-confirm']"));
		submit.click();
		Thread.sleep(10000);
		System.out.println("�ύ");

	}

	/**
	 * ����۸�
	 * 
	 * @author yangw
	 * @version 1.00
	 * @throws Exception 
	 */
	private void calculateSum() throws Exception {

		double sum = 0;

		if (typeRandom == 1) {
			sum = 0.004 * size * numRandom;
		} else {
			sum = 0.004 * size * numRandom * 10;
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
	}// �������

}// �����

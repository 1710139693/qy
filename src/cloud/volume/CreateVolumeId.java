package cloud.volume;

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

public class CreateVolumeId {
	/**
	 * ������Ӳ��
	 * 
	 * @author yangw
	 * @version 1.00
	 * @throws Exception
	 */
	WebDriver driver;
	Base pubMeth = new Base();
//	String priceValue;
	//boolean valueOk = false;
	int seleArea = 0; // seleArea=1ʱ��̫һ��
	int size = 1;
	int numRandom = 0;

	@Test
	public void createAllVolume() throws Exception {
		driver = pubMeth.beforeTest(driver);
		createVolume("radio-0", "0", "auto_perf");
		createVolume("radio-1", "00", "auto_cap");
		// driver.close();
	}

	/**
	 * ������Ӳ��
	 * 
	 * @author yangw
	 * @version 1.00
	 * @param ����
	 * @param ��λ
	 * @param ����
	 * @throws Exception 
	 */
	private void createVolume(String type, String digit, String hddname) throws Exception {

		for (int i = 1; i < 2; i++) {
			// �����Ӳ��
			WebElement cloudVolume = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-volume']"));
			cloudVolume.click();
			Thread.sleep(2000);
			

			// ����һ��������̫һ��
			pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");

			Random RandomseleArea = new Random();
			seleArea = RandomseleArea.nextInt(2);// Ϊ0-1����
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
			pubMeth.newOne(driver);
			
			// ����
			pubMeth.anXu(driver);

			// ����Ӳ������
			pubMeth.inputName(driver, hddname + i);
			

			// Ӳ������
			WebElement num = driver.findElement(By.xpath("//input[@data-testid='selectNum-input']"));
			num.clear();

			int max = 1;
			int min = 1;
			Random randomhdd = new Random();
			numRandom = randomhdd.nextInt(max) % (max - min + 1) + min;

			num.sendKeys(+numRandom + "");
			Thread.sleep(2000);
			System.out.println("ѡ��" + numRandom + "��Ӳ��");
			String strnumRandom = String.valueOf(numRandom);
			pubMeth.rwFile("Ӳ��=", strnumRandom, "��");

			// ����
			WebElement seletype = driver.findElement(By.xpath("//span[@data-testid='" + type + "']"));
			seletype.click();
			Thread.sleep(3000);
			System.out.println("type=" +type);

			// ����

			WebElement storage = driver.findElement(By.xpath("//input[@data-testid='customSlideNum']"));
			storage.clear();

			int max1 = 10;
			int min1 = 3;
			Random randoma = new Random();
			size = randoma.nextInt(max1) % (max1 - min1 + 1) + min1;
			storage.clear();

			System.out.println("d1=" + digit);
			storage.sendKeys(+size + digit);
			Thread.sleep(3000);
			System.out.println("��������" + size + digit);
			String stra = String.valueOf(size);
			pubMeth.rwFile("����=", stra, digit);

			// ȡҳ����ʾ�ļ۸�
			pubMeth.UIprice(driver);
			
			// �ύ
			WebElement submit = driver.findElement(By.xpath("//span[@data-testid='pop-model-confirm']"));
			submit.click();
			Thread.sleep(10000);
			System.out.println("�ύ");

			// �ж���û�н�������ȡ�÷���ֵre
			By locator = By.xpath("//i[@data-testid='table-row-0-checkbox']");
			boolean reValue = pubMeth.isElementExsit(driver, locator);
			System.out.println("reValue=" + reValue);
			int digitMul = 0;
			double basePrice = 0;
			if (type == "radio-0") {
				basePrice = 0.0134;
				digitMul = 1;
				

			} else if (type == "radio-1") {
				basePrice = 0.004;
				digitMul = 10;

			}
			calculateSum(basePrice, digitMul);
			pubMeth.writeLog("createVolume",reValue);

		} // for

	}// ������������������

	/**
	 * ����ѡ�����Ӳ�̵ļ۸�
	 * 
	 * @author yangw
	 * @version 1.00
	 * @throws Exception 
	 */
	private void calculateSum(double basePrice, int digitMul) throws Exception {// basePrice��ʾ����
																			// ��digitMul��ʾ��λ

		double sum = 0;
		// numRandom��������size������
		sum = basePrice * size * numRandom * digitMul;

		// ȡС�������λ
		String sumTo = String.format("%.4f", sum);
		pubMeth.calcuCheck(sumTo);
		
	}// ���㺯��
}

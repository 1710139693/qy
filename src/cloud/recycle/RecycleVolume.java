package cloud.recycle;

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

public class RecycleVolume {

	/**
	 * �����Ʒ�����
	 * 
	 * @author yangw
	 * @version 1.00
	 */

	int seleArea = 0; // seleArea = 1ʱ��̫һ��
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void createRecycle() throws Exception {
		
		driver = pubMeth.beforeTest(driver);
		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");

		CreateVolumeForRecycle();

		// �����Ӳ��
		WebElement cloudserver = driver.findElement(By.xpath("//a[@data-testid = 'sidebarNav-cloud-volume']"));
		cloudserver.click();
		Thread.sleep(3000);
		
		// ѡ�񻪶�һ��
		WebElement yatai = driver.findElement(By.xpath("//span[@data-testid = 'region-select-ac1']"));
		yatai.click();
		Thread.sleep(3000);
		System.out.println("����һ��");
		pubMeth.rwFile("����Ϊ", "����һ��", "");
		
		// �жϷ�������û�н�������ȡ�÷���ֵre
		
		By locator = By.xpath("//i[@data-testid = 'table-row-0-checkbox']");
		boolean reValue = pubMeth.isElementExsit(driver, locator);
		System.out.println("reValue = " + reValue);

		if (reValue) {
			// if (re = false) {break;}

			// ѡ�񽨺�Ӳ��
			WebElement serverok = driver.findElement(locator);
			serverok.click();
			Thread.sleep(2000);

			//�������
	
			pubMeth.moreOptsDel(driver);
			
			// �ύ
			pubMeth.submit(driver);
			
			// ������վ
			WebElement image = driver.findElement(By.xpath("//a[@data-testid = 'sidebarNav-cloud-recyclebin']"));
			image.click();
			Thread.sleep(2000);
			System.out.println("������վ");

			// ѡ�񻪶�һ��
			WebElement huadong2 = driver.findElement(By.xpath("//span[@data-testid = 'region-select-ac1']"));
			huadong2.click();
			Thread.sleep(2000);
			System.out.println("����һ��");
			
			// ѡ����Ӳ��
			WebElement hdd = driver.findElement(By.xpath("//a[@data-testid = 'tabs-1']"));
			hdd.click();
			Thread.sleep(2000);
			System.out.println("����һ��");
			
			
			// �ж���û�н�������ȡ�÷���ֵrebk
			By locatorbk = By.xpath("//tbody[@data-testid = 'table-row-0']");
			boolean reValue1 = Base.isElementExsit(driver, locatorbk);
			System.out.println("reValue1=" + reValue1);

			// �������н��д��LOG�ļ�
			String cases = "Recycle";
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // �������ڸ�ʽ
			// ����Ѿ����������Һ˶Եļ۸���ȷ��Pass�Ľ��д���ļ�
			if (reValue1) {
				pubMeth.rwFile(cases, df.format(new Date()), "Pass");
			} else {
				pubMeth.rwFile(cases, df.format(new Date()), "Fail");
			}
		} else {
			System.out.println("����վû����Ӳ��");
		}

		driver.quit();
	}

	/**
	 * ������Ӳ��
	 * 
	 * @author yangw
	 * @version 1.00
	 * @throws Exception 
	 */
	private void CreateVolumeForRecycle() throws Exception {

		for (int i = 1; i < 2; i++) {
			// �����Ӳ��
			WebElement cloudVolume = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-volume']"));
			cloudVolume.click();
			Thread.sleep(2000);
			

			// ѡ������
			pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
		//	Random RandomseleArea = new Random();
		//	seleArea = RandomseleArea.nextInt(3);// Ϊ0-2����
			seleArea = 0;
			pubMeth.seleAreaall(driver, seleArea);	

			// �½�
			// WebElement
			// creat=driver.findElement(By.xpath("//span[@class='color-primary']"));
			pubMeth.newOne(driver);
			
			// ����
			pubMeth.anXu(driver);

			// ����Ӳ������
			pubMeth.inputName(driver, "autohddforRecycle");
			
			// Ӳ������
			WebElement num = driver.findElement(By.xpath("//input[@data-testid='selectNum-input']"));
			num.clear();

			int max = 1;
			int min = 1;
			Random randomhdd = new Random();
			int numRandom = randomhdd.nextInt(max) % (max - min + 1) + min;

			num.sendKeys(+numRandom + "");
			Thread.sleep(2000);
			System.out.println("ѡ��" + numRandom + "��Ӳ��");
			String strnumRandom = String.valueOf(numRandom);
			pubMeth.rwFile("Ӳ��=", strnumRandom, "��");

			// ����
			WebElement seletype = driver.findElement(By.xpath("//span[@data-testid='radio-1']"));
			seletype.click();
			Thread.sleep(3000);
			
	
			// ����
			WebElement storage = driver.findElement(By.xpath("//input[@data-testid='customSlideNum']"));
			storage.clear();
			int size = 1;
			if (seleArea == 0) {
				int max1 = 10;
				int min1 = 3;
				Random randoma = new Random();
				size = randoma.nextInt(max1) % (max1 - min1 + 1) + min1;
			} else if (seleArea == 1){
				int max1 = 50;
				int min1 = 1;
				Random randoma = new Random();
				size = randoma.nextInt(max1) % (max1 - min1 + 1) + min1;
			} else if (seleArea == 2){
				int max1 = 100;
				int min1 = 1;
				Random randoma = new Random();
				size = randoma.nextInt(max1) % (max1 - min1 + 1) + min1;
			}
			
			storage.clear();
			String digit = "0";
			System.out.println("d1=" + digit);
			storage.sendKeys(+size + digit);
			Thread.sleep(5000);
			
			
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
			
			pubMeth.writeLog("createVolume",reValue);

		} // for

	}

	

}

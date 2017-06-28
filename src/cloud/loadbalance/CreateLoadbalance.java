package cloud.loadbalance;

import org.openqa.selenium.support.ui.Select;

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

public class CreateLoadbalance {

	/**
	 * �����Ƹ��ؾ���
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	//�˽ű������˹���IP�ļ۸����Բ�����δʹ�õĹ���IP��Դ����
	//String priceValue;
	//boolean valueOk = false;
	int seleArea = 0; // seleArea=1ʱ��̫һ��
	int maxConnNum = 0; // ������
	int seleNet = 0; // ����
	int size = 1; // ����
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void createLoadbalance() throws Exception {
		driver = pubMeth.beforeTest(driver);
		for (int i = 1; i < 2; i++) {
			pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
			
			// ����Ƹ��ؾ���
			WebElement cloudLoadbalance = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-loadBalancer']"));
			cloudLoadbalance.click();
			Thread.sleep(2000);
			
			// ѡ����
			Random RandomseleArea = new Random();
			seleArea = RandomseleArea.nextInt(3);// Ϊ0-1����
			pubMeth.seleAreaall(driver, seleArea);

			// �½�
			WebElement creat = driver.findElement(By.xpath("//span[@class='color-primary']"));
			creat.click();
			Thread.sleep(3000);
			
			// ����
			pubMeth.anXu(driver);
			
			// ���븺�ؾ�������
			pubMeth.inputName(driver, "autoloadbalance" + i);
			
			// ���������
			Random RandommaxConnNum = new Random();
			maxConnNum = RandommaxConnNum.nextInt(3);// Ϊ0-1����
			switch (maxConnNum) {
			case 0:
				WebElement seleType = driver.findElement(By.xpath("//span[@data-testid='radio-num-0']"));
				seleType.click();
				Thread.sleep(3000);
				System.out.println("20000");
				pubMeth.rwFile("������=", "20000", "");
				break;
			case 1:
				WebElement seleType1 = driver.findElement(By.xpath("//span[@data-testid='radio-num-1']"));
				seleType1.click();
				Thread.sleep(3000);
				System.out.println("40000");
				pubMeth.rwFile("������=", "40000", "");
				break;
			case 2:
				WebElement seleType2 = driver.findElement(By.xpath("//span[@data-testid='radio-num-2']"));
				seleType2.click();
				Thread.sleep(3000);
				System.out.println("100000");
				pubMeth.rwFile("������=", "100000", "");
				break;
			}

			// ����
			Random RandomseleNet = new Random();
			seleNet = RandomseleNet.nextInt(2);// Ϊ0-1����
			if (seleNet == 0) {
				WebElement seletype = driver.findElement(By.xpath("//span[@data-testid='radio-type-0']"));
				seletype.click();
				Thread.sleep(3000);
				pubMeth.rwFile("����Ϊ", "����", "");

				// ����
				WebElement storage = driver.findElement(By.xpath("//input[@data-testid='customSlideNum']"));
				storage.clear();
				// 1-5M
				int max1 = 5;
				int min1 = 1;
				Random randoma = new Random();
				size = randoma.nextInt(max1) % (max1 - min1 + 1) + min1;
				storage.clear();

				storage.sendKeys(+size + "");
				Thread.sleep(3000);
				System.out.println("�������" + size + "M");
				String strsize = String.valueOf(size);
				pubMeth.rwFile("����=", strsize, "M");

			} else {
				WebElement seletype = driver.findElement(By.xpath("//span[@data-testid='radio-type-1']"));
				seletype.click();
				Thread.sleep(3000);
				pubMeth.rwFile("����Ϊ", "·������", "");
			}

			// ȡҳ����ʾ�ļ۸�
			pubMeth.UIprice(driver);
			
			// �ύ
			pubMeth.submit(driver);

			calculateSum();

			// �ж���û�н�������ȡ�÷���ֵre
			By locator = By.xpath("//i[@data-testid='table-row-0-checkbox']");
			boolean reValue = pubMeth.isElementExsit(driver, locator);
			System.out.println(reValue);

			// �������н��д��LOG�ļ�
			
			pubMeth.writeLog("Createloadbalance",reValue);


		} // for

	}// ������������������

	/**
	 * ������ѡ����۸�
	 * 
	 * @author yangw
	 * @version 1.00
	 * @throws Exception 
	 */

	private void calculateSum() throws Exception {// c��ʾ���� ��e��ʾ��λ

		double sum = 0;
		switch (seleArea) {
		case 0: // ����һ��
			if (seleNet == 0) {// ����
				switch (maxConnNum) {// ��������ͬ
				case 0:
					sum = 0.0278 + (0.0278 * (size - 1));
					break;
				case 1:
					sum = 0.5838 + (0.0278 * (size - 1));
					break;
				case 2:
					sum = 1.4178 + (0.0278 * (size - 1));
					break;
				}

			} else {// ·������
				switch (maxConnNum) {// ��������ͬ
				case 0:
					sum = 0.0700;
					break;
				case 1:
					sum = 0.5560;
					break;
				case 2:
					sum = 1.3900;
					break;
				}
			}
			break;
		case 1:// ��̫һ��
			if (seleNet == 0) {// ����
				switch (maxConnNum) {// ��������ͬ
				case 0:
					sum = 0.0269 + (0.0269 * (size - 1));
					break;
				case 1:
					sum = 0.5829 + (0.0269 * (size - 1));
					break;
				case 2:
					sum = 1.4169 + (0.0269 * (size - 1));
					break;
				}

			} else {// ·������
				switch (maxConnNum) {// ��������ͬ
				case 0:
					sum = 0.0700;
					break;
				case 1:
					sum = 0.5560;
					break;
				case 2:
					sum = 1.3900;
					break;
				}
			}
			break;
		case 2: // ��������
			if (seleNet == 0) {// ����
				switch (maxConnNum) {// ��������ͬ
				case 0:
					sum = 0.0403 + (0.0403 * (size - 1));
					break;
				case 1:
					sum = 0.5963 + (0.0403 * (size - 1));
					break;
				case 2:
					sum = 1.4303 + (0.0403 * (size - 1));
					break;
				}

			} else {// ·������
				switch (maxConnNum) {// ��������ͬ
				case 0:
					sum = 0.0700;
					break;
				case 1:
					sum = 0.5560;
					break;
				case 2:
					sum = 1.3900;
					break;
				}
			}
			break;
		}

		// ȡС�������λ
		String sumTo = String.format("%.4f", sum);
		pubMeth.calcuCheck(sumTo);
	}// ���㺯��
}

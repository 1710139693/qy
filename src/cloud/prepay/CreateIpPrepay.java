package cloud.prepay;

import org.openqa.selenium.support.ui.Select;
import common.Login;
import common.Base;
import java.text.SimpleDateFormat;
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
import org.openqa.selenium.support.ui.Select;

public class CreateIpPrepay {

	/**
	 * ��������IP
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	WebDriver driver;
	Base pubMeth = new Base();
	int numIp = 0;// ����
	int sizeIp = 0;// ����
	int netYatai = 0;// c = 1ѡ����ʴ�½�Ż�
	int seleArea = 2;// seleArea = 1ʱ��̫һ��
	//String priceValue;
	//boolean valueOk = false;

	@Test
	public void cloudIp() throws Exception {
		driver = pubMeth.beforeTest(driver);
		for (int i = 1; i < 2; i++) {
			pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
			// ��๫��IP
			WebElement cloudIp = driver.findElement(By.xpath("//a[@data-testid = 'sidebarNav-cloud-ip']"));
			cloudIp.click();
			Thread.sleep(2000);

			// ѡ����
			Random RandomseleArea = new Random();
			seleArea = RandomseleArea.nextInt(3);// Ϊ0-2����
			pubMeth.seleAreaall(driver, seleArea);
			System.out.println("seleAreaΪ��" + seleArea + "����");

			// �½�
			WebElement creat = driver.findElement(By.xpath("//span[@class = 'color-primary']"));
			creat.click();
			Thread.sleep(2000);

			// ����
			//pubMeth.anXu(driver);

			// IP����
			WebElement num = driver.findElement(By.xpath("//input[@class = 'num-wrapper']"));
			num.clear();

			int max = 4;
			int min = 1;
			Random randomIp = new Random();
			numIp = randomIp.nextInt(max) % (max - min + 1) + min;

			num.sendKeys(+numIp + "");
			Thread.sleep(2000);
			System.out.println("ѡ��" + numIp + "��IP");
			String strnumIp = String.valueOf(numIp);
			pubMeth.rwFile("IP =", strnumIp, "��");

			// ����

			WebElement storage = driver.findElement(By.xpath("//input[@data-testid = 'customSlideNum']"));
			storage.clear();
			// 1-5M
			int max1 = 5;
			int min1 = 1;
			Random randoma = new Random();
			sizeIp = randoma.nextInt(max1) % (max1 - min1 + 1) + min1;
			System.out.println("sizeIp =" + sizeIp);
			storage.clear();

			storage.sendKeys(+sizeIp + "");
			Thread.sleep(3000);
			System.out.println("��������" + sizeIp + "M");
			String strsizeIp = String.valueOf(sizeIp);
			pubMeth.rwFile("���� =", strsizeIp, "M");

			if (seleArea == 1) {
				Random randomcc = new Random();
				netYatai = randomcc.nextInt(2);
				if (netYatai == 1) {

					// ������
					WebElement lineValue = driver.findElement(By.xpath("//div[@data-testid = 'singleChoice-default']"));
					lineValue.click();
					Thread.sleep(2000);

					// ѡ����ʴ�½�Ż�
					WebElement selectLine = driver
							.findElement(By.xpath("//li[@data-testid = 'singleChoice-eipg-aofiim2z']"));
					selectLine.click();
					Thread.sleep(2000);
					System.out.println("ѡ�����");

				} else {
					System.out.println("���ʴ���");
				}
			}

			// ȡҳ����ʾ�ļ۸�
			pubMeth.UIprice(driver);
			
			// �ύ
			pubMeth.submit(driver);
			
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

			calculateSum();

			// �������н��д��LOG�ļ�
			
			pubMeth.writeLog("createIpId",reValue);

		} // for

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
		if (seleArea == 0) {// ����һ�����㷨

			sum = numIp * sizeIp * 0.0278;

		} else if (seleArea == 1) {// ��̫���ʴ���ļ۸�
			if (netYatai == 0) {
				sum = numIp * sizeIp * 0.0269;
			} else {
				sum = numIp * sizeIp * 0.0672;// ��̫���ʴ�½�Ż��ļ۸�

			}

		} else if (seleArea == 2) {// �����������㷨
			sum = numIp * sizeIp * 0.0403;
		}

		double sumPrepay = sum*24*30;
		//ȡС�������λ
		String sumTo = String.format("%.4f", sumPrepay); 
		pubMeth.calcuCheck(sumTo);

	}// �������

}// �����

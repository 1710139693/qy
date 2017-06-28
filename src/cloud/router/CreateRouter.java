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

public class CreateRouter {

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
	int typeSmall = 0; // ����ѡ��
	int size = 1; // ����
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void createRouterAll() throws Exception {
		
		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
		driver = pubMeth.beforeTest(driver);
		//createRouter(0);
		seleArea = 2;
		createRouter(seleArea);
		//createRouter(2);
		driver.close();
		
	}
	
	private void createRouter(int seleArea) throws Exception {
		
		
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
			System.out.println("seleArea=" + seleArea);
			// ����·��������
			
			pubMeth.inputName(driver, "autorouter" + i);
			
			// ����
			Random RandomtypeSmall = new Random();
			typeSmall = RandomtypeSmall.nextInt(2);// Ϊ0-1����
			if (typeSmall == 0) {
				WebElement seletype = driver.findElement(By.xpath("//span[@data-testid='radio-type-0']"));
				seletype.click();
				Thread.sleep(3000);
				System.out.println("����");
			} else {
				WebElement seletype = driver.findElement(By.xpath("//span[@data-testid='radio-type-1']"));
				seletype.click();
				Thread.sleep(3000);
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
			Thread.sleep(3000);
			System.out.println("�������" + size + "M");
			String strsize = String.valueOf(size);
			pubMeth.rwFile("����=", strsize, "M");

			// ȡҳ����ʾ�ļ۸�
			pubMeth.UIprice(driver);
			
			// �ύ
			WebElement submit = driver.findElement(By.xpath("//div[@data-testid='buy-button']"));
			submit.click();
			Thread.sleep(10000);
			System.out.println("�ύ");

			calculateSum();

			// �ж���û�н�������ȡ�÷���ֵre
			By locator = By.xpath("//i[@data-testid='table-row-0-checkbox']");
			boolean reValue = pubMeth.isElementExsit(driver, locator);
			System.out.println(reValue);

			// �������н��д��LOG�ļ�
			
			pubMeth.writeLog("CreateRouter",reValue);


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
		case 0://����һ��
			if (typeSmall == 0) {
				sum = 0.0412 + (0.0278 * (size - 1));
				System.out.println("һ"); 
			} else {
				sum = 0.4078 + (0.0278 * (size - 1));
				System.out.println("��"); 
			}
			break;
		case 1:// ��̫һ��
			if (typeSmall == 0) {
				sum = 0.0403 + (0.0269 * (size - 1));
				System.out.println("��"); 
			} else {
				sum = 0.4069 + (0.0269 * (size - 1));
				System.out.println("��"); 
			}
			break;
		case 2: // ��������
			if (typeSmall == 0) {
				sum = 0.0537 + (0.0403 * (size - 1));
				System.out.println("��"); 
			} else {
				sum = 0.4203 + (0.0403 * (size - 1));
				System.out.println("��"); 
			}
			break;
		}

		// ȡС�������λ
		String sumTo = String.format("%.4f", sum);
		pubMeth.calcuCheck(sumTo);
	}// ���㺯��
}

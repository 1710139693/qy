package cloud.rdbs;

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

public class CreateRdbs {

	/**
	 * ���������ݿ�
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	// û��1��1G��ѡ��
	int intRandomCpu = 0;// ѡȡ�����õ������
	int intRandomOs = 0;// ѡ�����ϵͳ��Ҫ�������
	//String priceValue; // ҳ��ȡ�ü۸�
	//boolean valueOk = false;// �۸�ȽϽ��

	int seleArea = 0; // seleArea=1ʱ��̫һ��
	int size = 0; // size=1����ȡ20G
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void createRdbs() throws Exception {
		driver = pubMeth.beforeTest(driver);
		for (int i = 1; i < 2; i++) {
			// ������ݿ�
			WebElement cloudserver = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-rdbs']"));
			cloudserver.click();
			Thread.sleep(2000);
			
			// ����һ��������̫һ��
			Random RandomseleArea = new Random();
			seleArea = RandomseleArea.nextInt(1);// Ϊ0-1����
			if (seleArea == 0) {
				WebElement yatai = driver.findElement(By.xpath("//span[@data-testid='region-select-ac1']"));
				yatai.click();
				Thread.sleep(5000);
				System.out.println("����һ��");
				pubMeth.rwFile("����Ϊ", "����һ��", "");
			} else {
				WebElement huadong = driver.findElement(By.xpath("//span[@data-testid='region-select-ac2']"));
				huadong.click();
				Thread.sleep(5000);
				System.out.println("��̫һ��");
				pubMeth.rwFile("����Ϊ", "��̫һ��", "");
			}

			// �½�
			WebElement creat = driver.findElement(By.xpath("//span[@class='color-primary']"));
			creat.click();
			Thread.sleep(2000);
			
			pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");

			// ��DB����ϵͳ����
			WebElement dbos = driver.findElement(By.xpath("//div[@class='single-label single-line']"));
			dbos.click();
			Thread.sleep(2000);
			
			// ѡȡ�����б��еĲ���ϵͳ
			// �ȵõ�һ��classд��list,get(0)��Ҫ�ڼ���,ʹ��value�ķ���click

			Random randomos = new Random();
			intRandomOs = randomos.nextInt(4);// Ϊ0-3����

			java.util.List<WebElement> element = driver
					.findElements(By.xpath("//li[@class='dropdown-item single-line']"));
			WebElement value = element.get(intRandomOs);
			value.click();
			Thread.sleep(2000);
			System.out.println("intRandomOs=" + intRandomOs);

			String str = intRandomOs + 2 + ""; // ����ת�����ַ���

			pubMeth.rwFile("DBOSΪ��", str, "�����ݿ�");

			// ѡ��CPU���ڴ�
			// ����5-8�����
			int max1 = 8;
			int min1 = 5;
			Random randomcpu = new Random();
			intRandomCpu = randomcpu.nextInt(max1) % (max1 - min1 + 1) + min1;

			java.util.List<WebElement> selectcpu = driver.findElements(By.xpath("//span[@class='tab-switch w90']"));
			// int selectcpunum = selectcpu.size();
			// System.out.println("size="+selectcpunum);
			WebElement cpumem = selectcpu.get(intRandomCpu);
			cpumem.click();
			Thread.sleep(2000);

			System.out.println("intRandomCpu=" + intRandomCpu);

			switch (intRandomCpu) {

			case 5:
				pubMeth.rwFile("cpuΪ", "2", "��");
				break;
			case 6:
				pubMeth.rwFile("cpuΪ", "4", "��");
				break;
			case 7:
				pubMeth.rwFile("cpuΪ", "8", "��16G");
				break;
			case 8:
				pubMeth.rwFile("cpuΪ", "8", "��32G");
				break;
			}

			// ����
			Random Randomsize = new Random();
			size = Randomsize.nextInt(2);// Ϊ0-1����
			if (size == 1) {
				WebElement storage = driver.findElement(By.xpath("//input[@class='num-wrapper']"));
				storage.clear();
				storage.sendKeys("20");
				Thread.sleep(2000);
				System.out.println("����DB�洢��С20");
			} else {
				System.out.println("�洢��С10");
			}

			// �������������
			pubMeth.inputName(driver, "autodb" + i);
			
			
			// �û���
			WebElement username = driver.findElement(By.xpath("//input[@class='input-text input-long mr10']"));
			username.sendKeys("sa123456");
			Thread.sleep(2000);
			
			// ����pwd
			java.util.List<WebElement> pwd = driver
					.findElements(By.xpath("//input[@class='input-text input-long mr10']"));
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
			
			// ȡҳ����ʾ�ļ۸�
			pubMeth.UIprice(driver);
			
			// �ύ
			WebElement submit = driver.findElement(By.xpath("//div[@class='btn-big']"));
			submit.click();
			Thread.sleep(25000);
			System.out.println("������ͨ");

			// �ж���û�н�������ȡ�÷���ֵre
			By locator = By.xpath("//i[@data-testid='table-row-0-checkbox']");
			boolean reValue = pubMeth.isElementExsit(driver, locator);
			System.out.println(reValue);

			calculateSum();

			// �������н��д��LOG�ļ�
			
			pubMeth.writeLog("createRdbs",reValue);


		} // for

		driver.quit();
	}// ������������������

	/**
	 * ����ѡ���CPU���ڴ�����ܼ۸�
	 * 
	 * @author yangw
	 * @version 1.00
	 * @throws Exception 
	 */

	private void calculateSum() throws Exception {
		
		double cpuMemPrice = 0;
		double storageSize = 0;
		double sum = 0;

		switch (intRandomCpu) {

		case 5:
			cpuMemPrice = 0.5914;
			break;
		case 6:
			cpuMemPrice = 1.1694;
			break;
		case 7:
			cpuMemPrice = 2.1371;
			break;
		case 8:
			cpuMemPrice = 3.2124;
			break;

		}

		if (size == 1) {

			storageSize = 0.0134 * 2;

		} else {
			storageSize = 0.0134;
		}

		System.out.println("storageSize=" + storageSize);
		System.out.println("cpuMemPrice=" + cpuMemPrice);
		sum = cpuMemPrice + storageSize;

		// ȡС�������λ
		String sumTo = String.format("%.4f", sum);
		pubMeth.calcuCheck(sumTo);
	}// ���㺯��
}

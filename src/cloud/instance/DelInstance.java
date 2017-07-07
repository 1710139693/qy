package cloud.instance;

import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

//import org.apache.bcel.generic.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import common.Login;
import common.Base;

public class DelInstance {

	/**
	 * ɾ���Ʒ�����
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void delAllServer() throws Exception {
		driver = pubMeth.beforeTest(driver);
		delServer("region-select-ac1");
		delServer("region-select-ac2");
		driver.quit();
	}

	private void delServer(String ac) throws Exception {

		// ����Ʒ�����
		WebElement cloudServer = driver.findElement(By.xpath("//a[@data-testid = 'sidebarNav-cloud-instance']"));
		cloudServer.click();
		Thread.sleep(2000);

		// ѡ�����
		WebElement yatai = driver.findElement(By.xpath("//span[@data-testid = '" + ac + "']"));
		yatai.click();
		Thread.sleep(2000);
		System.out.println("��̫һ��");

		// �ж���û�н�������ȡ�÷���ֵre
		Base pubMeth = new Base();
		By locator = By.xpath("//i[@data-testid = 'table-row-0-checkbox']");
		boolean reValue = pubMeth.isElementExsit(driver, locator);
		System.out.println("reValue = " + reValue);

		// ����з������ͽ���ɾ������

		while (reValue) {

			if (reValue = false) {
				break;
			}

			// ������ھ͵�������Դ
			WebElement serverOk = driver.findElement(locator);
			serverOk.click();
			Thread.sleep(2000);
			
			//�������
			
			WebElement more = driver.findElement(By.xpath("//span[@data-testid = 'moreOpts']"));
			more.click();
			Thread.sleep(2000);
			
			WebElement moreDel = driver.findElement(By.xpath("//span[@data-testid = 'moreOpts-delete']"));
			moreDel.click();
			Thread.sleep(5000);
			
			WebElement checkNum = driver.findElement(By.xpath("//input[@placeholder = '��������֤��']"));
			checkNum.clear();
			checkNum.sendKeys("51idc");
			
			// ɾ���ύ
			pubMeth.submit(driver);

		}

		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
		// �������н��д��LOG�ļ�

		// �����֤û�ж�λ����̨����֤���Ѿ�ɾ����Pass�Ľ��д���ļ�
		boolean reValue1 = pubMeth.isElementExsit(driver, locator);
		pubMeth.writeLogDel("DelInstance", reValue1);

	} // ��������
} // �����

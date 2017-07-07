package cloud.rdbs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import common.Base;

public class DelRdbs {

	/**
	 * ɾ�������ݿ�
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void delAllRdbs() throws Exception {
		driver = pubMeth.beforeTest(driver);
		delRdbs("region-select-ac1");
		delRdbs("region-select-ac2");
		delRdbs("region-select-ac3");
		driver.quit();
	}

	private void delRdbs(String ac) throws Exception {

		// ��������ݿ�
		WebElement hdd = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-rdbs']"));
		hdd.click();
		Thread.sleep(2000);
		
		// ��������̫һ��
		WebElement yatai = driver.findElement(By.xpath("//span[@data-testid='" + ac + "']"));
		yatai.click();
		Thread.sleep(2000);
		System.out.println(ac + "��");

		// �ж���û�н�������ȡ�÷���ֵre
		Base pubMeth = new Base();
		By locator = By.xpath("//tbody[@data-testid='table-row-0']");
		boolean reValue = pubMeth.isElementExsit(driver, locator);
		System.out.println("reValue=" + reValue);

		// ������ھͽ���ɾ������

		while (pubMeth.isElementExsit(driver, locator)) {

			if (reValue = false) {
				break;
			}
			// ѡ��ȫѡ��
			WebElement serverok = driver.findElement(By.xpath("//i[@data-testid='table-head-checkbox']"));
			serverok.click();
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
			

			//ɾ���ύ
			pubMeth.submit(driver);

			// �������н��д��LOG�ļ�
			pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
			
			// �����֤û�ж�λ����̨����֤���Ѿ�ɾ����Pass�Ľ��д���ļ�
			boolean reValue1 = pubMeth.isElementExsit(driver, locator);
			pubMeth.writeLogDel("delRdbs",reValue1);
			
		} // while����

	}// ��������

} // �����

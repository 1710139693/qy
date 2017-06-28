package cloud.ip;

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
import common.Login;
import common.Base;

public class DelIp {

	/**
	 * ��������IP
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void delAllIp() throws Exception {
		driver = pubMeth.beforeTest(driver);
		delAutoIp("region-select-ac1");
		delAutoIp("region-select-ac2");
		delAutoIp("region-select-ac3");
		driver.close();

	}

	private void delAutoIp(String ac) throws Exception {

		// ��๫��IP
		WebElement pubip = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-ip']"));
		pubip.click();
		Thread.sleep(2000);
		
		// ѡ����
		WebElement yatai = driver.findElement(By.xpath("//span[@data-testid='" + ac + "']"));
		yatai.click();
		Thread.sleep(2000);
		
		// �ж���û�н�������ȡ�÷���ֵre
		Base pubMeth = new Base();
		By locator = By.xpath("//tbody[@data-testid='table-row-0']");
		boolean reValue = pubMeth.isElementExsit(driver, locator);
		System.out.println("reValue=" + reValue);

		// ����й���IP�ͽ���ɾ������
		while (pubMeth.isElementExsit(driver, locator)) {

			if (reValue = false) {
				break;
			}

			WebElement serverOk = driver.findElement(By.xpath("//i[@data-testid='table-head-checkbox']"));
			serverOk.click();
			Thread.sleep(2000);
			System.out.println("ѡ��ALL IP");
			
			//ɾ������
			pubMeth.DelNoCheckNum(driver);
			
			//ɾ���ύ
			pubMeth.submit(driver);

		} // while
		
		// �������н��д��LOG�ļ�
		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
		// �����֤û�ж�λ����̨����֤���Ѿ�ɾ����Pass�Ľ��д���ļ�
		boolean reValue1 = pubMeth.isElementExsit(driver, locator);
		pubMeth.writeLogDel("DelIp",reValue1);

		

	}// delautopubip

} // �����

package cloud.volume;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import common.Base;

public class DelVolume {

	/**
	 * ɾ����Ӳ��
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void delAllVolume() throws Exception {

		driver = pubMeth.beforeTest(driver);
		delVolume("region-select-ac1");
		delVolume("region-select-ac2");
		delVolume("region-select-ac3");
		driver.quit();
	}

	private void delVolume(String ac) throws Exception {

		// �����Ӳ��
		WebElement hdd = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-volume']"));
		hdd.click();
		Thread.sleep(2000);
		

		// ����������̫һ��
		WebElement yatai = driver.findElement(By.xpath("//span[@data-testid='" + ac + "']"));
		yatai.click();
		Thread.sleep(2000);
		System.out.println(ac + "��");

		// �ж���û�н�������ȡ�÷���ֵre
		Base pubMeth = new Base();
		By locator = By.xpath("//i[@data-testid='table-row-0-checkbox']");
		boolean reValue = pubMeth.isElementExsit(driver, locator);

		System.out.println("reValue=" + reValue);

		// ����оͽ���ɾ������

		while (pubMeth.isElementExsit(driver, locator)) {

			if (reValue = false) {
				break;
			}
			// ѡ��ȫѡ��
			WebElement serverok = driver.findElement(By.xpath("//i[@data-testid='table-head-checkbox']"));
			serverok.click();
			Thread.sleep(2000);
			
			pubMeth.moreOptsDel(driver);
			

			//ɾ���ύ
			pubMeth.submit(driver);

			// �������н��д��LOG�ļ�
			pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
			
			// �����֤û�ж�λ����̨����֤���Ѿ�ɾ����Pass�Ľ��д���ļ�
			boolean reValue1 = pubMeth.isElementExsit(driver, locator);
			pubMeth.writeLogDel("DelVolume",reValue1);
			
		} // while����

	}// ��������

} // �����

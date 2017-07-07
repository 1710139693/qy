package cloud.snapshot;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import common.Base;

public class DelSnapshot {
	/**
	 * ɾ�����ձ���
	 * 
	 * @author yangw
	 * @version 1.00
	 */

	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void delAllBackup() throws Exception {
		driver = pubMeth.beforeTest(driver);
		delBackup("region-select-ac1");
		driver.quit();
	}

	private void delBackup(String ac) throws Exception {

		// �����ձ���
		WebElement hdd = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-snapshot']"));
		hdd.click();
		Thread.sleep(2000);

		// ����������̫һ��
		WebElement yatai = driver.findElement(By.xpath("//span[@data-testid='" + ac + "']"));
		yatai.click();
		Thread.sleep(2000);
		System.out.println(ac + "��");
		pubMeth.rwFile("����Ϊ", ac, "");
		
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

			// ɾ���ύ
			pubMeth.submit(driver);

			// }

			// �������н��д��LOG�ļ�
			pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
			
			// �����֤û�ж�λ����̨����֤���Ѿ�ɾ����Pass�Ľ��д���ļ�
			boolean reValue1 = pubMeth.isElementExsit(driver, locator);
			pubMeth.writeLogDel("DelSnapshot",reValue1);
			
		} // while����

	}// ��������

} // �����

package cloud.image;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import common.Base;

public class DelImage {
	/**
	 * ɾ������
	 * 
	 * @author yangw
	 * @version 1.00
	 */

	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void delAllImage() throws Exception {
		driver = pubMeth.beforeTest(driver);
		delImage("region-select-ac2");
		driver.close();
	}

	
	private void delImage(String ac) throws Exception {
		
		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
		
		// ��ྵ��
		WebElement hdd = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-image']"));
		hdd.click();
		Thread.sleep(2000);

		// ѡ����
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

		while (reValue) {

			if (reValue = false) {
				break;
			}
			// ѡ��ȫѡ��
			WebElement serverok = driver.findElement(By.xpath("//i[@data-testid='table-head-checkbox']"));
			serverok.click();
			Thread.sleep(2000);

			pubMeth.DelNoCheckNum(driver);

			// ɾ���ύ
			pubMeth.submit(driver);

			// }

			// �����֤û�ж�λ����̨����֤���Ѿ�ɾ����Pass�Ľ��д���ļ�
			boolean reValue1 = pubMeth.isElementExsit(driver, locator);
			// �������н��д��LOG�ļ�
			pubMeth.writeLogDel("DelSnapshot",reValue1);
			
		} // while����

	}// ��������

} // �����

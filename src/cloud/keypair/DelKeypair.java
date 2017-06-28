package cloud.keypair;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import common.Base;

public class DelKeypair {

	/**
	 * ɾ��Keypair
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void delKeypairAll() throws Exception {
		driver = pubMeth.beforeTest(driver);
		delAutoKeypair("region-select-ac1");
		delAutoKeypair("region-select-ac2");
		delAutoKeypair("region-select-ac3");
		driver.close();
	}

	private void delAutoKeypair(String ac) throws Exception {

		// Keypair
		WebElement sdnnet = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-keyPair']"));
		sdnnet.click();
		Thread.sleep(2000);

		// ѡ����
		WebElement yatai = driver.findElement(By.xpath("//span[@data-testid='" + ac + "']"));
		yatai.click();
		Thread.sleep(2000);
		System.out.println(ac);

		// �ж���û�н�������ȡ�÷���ֵre
		By locator = By.xpath("//tbody[@data-testid='table-row-0']");
		boolean reValue = pubMeth.isElementExsit(driver, locator);
		System.out.println("reValue=" + reValue);

		while (reValue) {

			if (reValue = false) {
				break;
			}

			WebElement serverok = driver.findElement(By.xpath("//i[@data-testid='table-head-checkbox']"));
			serverok.click();
			Thread.sleep(2000);

			// ɾ������
			pubMeth.moreOptsDel(driver);

			// ɾ���ύ
			pubMeth.submit(driver);

		} // while

		// �������н��д��LOG�ļ�
		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");

		// �����֤û�ж�λ����̨����֤���Ѿ�ɾ����Pass�Ľ��д���ļ�
		boolean reValue1 = pubMeth.isElementExsit(driver, locator);
		pubMeth.writeLogDel("DelKeypair", reValue1);

	} // ��������

} // �����

package cloud.network;

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

public class CreateNetwork {

	/**
	 * ����SDN����
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	WebDriver driver;
	Base pubMeth = new Base();
	int seleArea;
	@Test
	public void createNet() throws Exception {
		driver = pubMeth.beforeTest(driver);
		for (int i = 1; i < 3; i++) {
			pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");

			// ���SDN
			WebElement cloudserver = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-network']"));
			cloudserver.click();
			Thread.sleep(2000);
			
			// ��������װ����
			Random RandomseleArea = new Random();
			seleArea = RandomseleArea.nextInt(3);// Ϊ0-1����
			pubMeth.seleAreaall(driver, seleArea);

			// �½�
			pubMeth.newOne(driver);
			
			// ����SDN����
			pubMeth.inputName(driver, "autosdn" + i);
			
			// �ύ
			pubMeth.submit(driver);

			// �ж���û�н�������ȡ�÷���ֵre
			By locator = By.xpath("//tbody[@data-testid='table-row-0']");
			boolean reValue = pubMeth.isElementExsit(driver, locator);
			System.out.println(reValue);

			// �������н��д��LOG�ļ�
			boolean valueOk = true;
			pubMeth.writeLog("CreateNetwork",reValue);

		} // for

	}// createsdn

}// class

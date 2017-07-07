package cloud.loadbalance;

import org.openqa.selenium.support.ui.Select;

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

public class LoadbalanceForward {

	/**
	 * �����Ƹ��ؾ���
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	//�˽ű������˹���IP�ļ۸����Բ�����δʹ�õĹ���IP��Դ����
	//String priceValue;
	//boolean valueOk = false;
	int seleArea = 0; // seleArea=1ʱ��̫һ��

	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void LoadbalanceForward() throws Exception {
		
		driver = pubMeth.beforeTest(driver);
		
		// ����Ƹ��ؾ���
		WebElement cloudLoadbalance = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-loadBalancer']"));
		cloudLoadbalance.click();
		Thread.sleep(2000);
		
		// ѡ���������е�һ��
		Random RandomseleArea = new Random();
		seleArea = RandomseleArea.nextInt(3);// Ϊ0-1����
		pubMeth.seleAreaall(driver, seleArea);

		// ѡ��ת������
		WebElement forward = driver.findElement(By.xpath("//a[@data-testid='tabs-1']"));
		forward.click();
		Thread.sleep(2000);
		
		//�½�ת������
		pubMeth.newOnecreat(driver);
		
		// ��������
		WebElement forwardname = driver.findElement(By.xpath("//input[@data-testid='name']"));
		forwardname.clear();
		forwardname.sendKeys("autoforward");
		Thread.sleep(2000);
		
		//�ύ
		pubMeth.submit(driver);
		System.out.println("�ύת������");
		
		// �ж���û�н�������ȡ�÷���ֵre
		By locator = By.xpath("//a[@data-testid='table-row-0-id']");
		boolean reValue = pubMeth.isElementExsit(driver, locator);
		System.out.println(reValue);
		
		if (reValue) {
			//ѡ�����ת������
			WebElement autoforward = driver.findElement(locator);
			autoforward.click();
			//�½�����
			pubMeth.newOnecreat(driver);
			//�����������
			WebElement rulename = driver.findElement(By.xpath("//input[@data-testid='val']"));
			rulename.clear();
			rulename.sendKeys("autorule");
			Thread.sleep(2000);
			//�ύ
			pubMeth.submit(driver);
			System.out.println("�ύ����");
			
			// Ӧ���޸�
			pubMeth.appMdf(driver);
			
		} else {
			System.out.println("ת�����Բ�����");
		}
		
		driver.quit();	
		
	}
	

}

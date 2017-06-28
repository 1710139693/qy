package cloud.group;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import common.Base;

public class DelGroup {

	/**
	 * ɾ������ǽ
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void delAllFirewall() throws Exception {
		driver = pubMeth.beforeTest(driver);
		delAutoFirewall("region-select-ac1");
		delAutoFirewall("region-select-ac2");
		delAutoFirewall("region-select-ac3");
		driver.close();
	}

	private void delAutoFirewall(String ac) throws Exception {

		//Firewall
		WebElement sdnnet = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-group']"));
		sdnnet.click();
		Thread.sleep(2000);
		
		// ѡ����
		WebElement yatai = driver.findElement(By.xpath("//span[@data-testid='" + ac + "']"));
		yatai.click();
		Thread.sleep(2000);
		System.out.println(ac);
	
		// �ж���û�н�����
		WebElement groupId = driver.findElement(By.xpath("//a[@data-testid='table-row-0-id']"));
		String groupname = groupId.getText();
		Thread.sleep(2000);
		String groupnamesub = groupname.substring(11);
		System.out.println("groupnamesub=" + groupnamesub);
		
		String autoname = "autogroup";
		if (autoname.equals(groupnamesub)) {	
		
			WebElement serverok = driver.findElement(By.xpath("//td[@data-testid='table-row-0-checkbox']"));
			serverok.click();
			Thread.sleep(2000);
			
			WebElement more = driver.findElement(By.xpath("//span[@data-testid='moreOpts']"));
			more.click();
			Thread.sleep(2000);
			
			WebElement moredel = driver.findElement(By.xpath("//li[@data-testid='moreOpts-delete']"));
			moredel.click();
			Thread.sleep(5000);
			
			//ɾ���ύ
			pubMeth.submit(driver);
			
		} else {
			
			System.out.println("����autogroup�������ǽ");
			//boolean reValue1 = false;
			//pubMeth.writeLogDel("DelGroup",reValue1);
		}
		//} // while

		// �������н��д��LOG�ļ�
		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
		
		// �����֤û�ж�λ����̨����֤���Ѿ�ɾ����Pass�Ľ��д���ļ�
		By locator1 = By.xpath("//tbody[@data-testid='table-row-1']");
		boolean reValue1 = pubMeth.isElementExsit(driver, locator1);
		pubMeth.writeLogDel("DelGroup",reValue1);
		
	} // ��������

} // �����

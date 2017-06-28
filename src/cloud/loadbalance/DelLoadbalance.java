package cloud.loadbalance;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import common.Base;

public class DelLoadbalance {
	/**
	 * ɾ����·����
	 * @author yangw
	 * @version 1.00
	 */
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void delAllLoadbalance() throws Exception {
		driver = pubMeth.beforeTest(driver);
		delAutoLoadbalance("region-select-ac1");
		delAutoLoadbalance("region-select-ac2");
		delAutoLoadbalance("region-select-ac3");
		driver.close();
	}

	private void delAutoLoadbalance(String ac) throws Exception {
		
		// Loadbalance
		WebElement routernet = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-loadBalancer']"));
		routernet.click();
		Thread.sleep(2000);
		
		// ѡ�����
		WebElement yatai = driver.findElement(By.xpath("//span[@data-testid='" + ac + "']"));
		yatai.click();
		Thread.sleep(2000);
		
		// �ж���û�н�������ȡ�÷���ֵre
		Base pubMeth = new Base();
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
			
			WebElement more = driver.findElement(By.xpath("//span[@data-testid='moreOpts']"));
			more.click();
			Thread.sleep(2000);
			
			WebElement moredel = driver.findElement(By.xpath("//li[@data-testid='moreOpts-deleteLoadbalancer']"));
			moredel.click();
			Thread.sleep(5000);
			
			//ɾ���ύ
			pubMeth.submit(driver);

		} // while

		// �������н��д��LOG�ļ�
		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
		
		// �����֤û�ж�λ����̨����֤���Ѿ�ɾ����Pass�Ľ��д���ļ�
		boolean reValue1 = pubMeth.isElementExsit(driver, locator);
		pubMeth.writeLogDel("DelLoadbalance",reValue1);
		
	} // ��������

} // �����

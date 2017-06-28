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
	 * 删除防火墙
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
		
		// 选择区
		WebElement yatai = driver.findElement(By.xpath("//span[@data-testid='" + ac + "']"));
		yatai.click();
		Thread.sleep(2000);
		System.out.println(ac);
	
		// 判断有没有建出来
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
			
			//删除提交
			pubMeth.submit(driver);
			
		} else {
			
			System.out.println("不是autogroup这个防火墙");
			//boolean reValue1 = false;
			//pubMeth.writeLogDel("DelGroup",reValue1);
		}
		//} // while

		// 用例运行结果写入LOG文件
		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
		
		// 如果验证没有定位到这台机器证明已经删除，Pass的结果写入文件
		By locator1 = By.xpath("//tbody[@data-testid='table-row-1']");
		boolean reValue1 = pubMeth.isElementExsit(driver, locator1);
		pubMeth.writeLogDel("DelGroup",reValue1);
		
	} // 函数结束

} // 类结束

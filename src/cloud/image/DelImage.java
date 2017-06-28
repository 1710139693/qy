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
	 * 删除镜像
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
		
		// 左侧镜像
		WebElement hdd = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-image']"));
		hdd.click();
		Thread.sleep(2000);

		// 选择区
		WebElement yatai = driver.findElement(By.xpath("//span[@data-testid='" + ac + "']"));
		yatai.click();
		Thread.sleep(2000);
		System.out.println(ac + "区");
		pubMeth.rwFile("区域为", ac, "");
		
		// 判断有没有建出来，取得返回值re
		Base pubMeth = new Base();
		By locator = By.xpath("//i[@data-testid='table-row-0-checkbox']");
		boolean reValue = pubMeth.isElementExsit(driver, locator);
		System.out.println("reValue=" + reValue);

		// 如果有就进行删除操作

		while (reValue) {

			if (reValue = false) {
				break;
			}
			// 选择全选框
			WebElement serverok = driver.findElement(By.xpath("//i[@data-testid='table-head-checkbox']"));
			serverok.click();
			Thread.sleep(2000);

			pubMeth.DelNoCheckNum(driver);

			// 删除提交
			pubMeth.submit(driver);

			// }

			// 如果验证没有定位到这台机器证明已经删除，Pass的结果写入文件
			boolean reValue1 = pubMeth.isElementExsit(driver, locator);
			// 用例运行结果写入LOG文件
			pubMeth.writeLogDel("DelSnapshot",reValue1);
			
		} // while结束

	}// 函数结束

} // 类结束

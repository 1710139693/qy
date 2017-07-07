package cloud.router;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import common.Login;
import common.Base;

public class RouterSetting {

	/**
	 * 创建云路由器
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	// 此脚本计算了公网IP的价格，所以不能有未使用的公网IP资源存在
	//String priceValue;
	//boolean valueOk = false;
	int seleArea = 0; // seleArea=1时亚太一区
	int typeSmail = 0; // 类型选择
	int size = 1; // 带宽
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void RouterSettingall() throws Exception {
		
		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
		driver = pubMeth.beforeTest(driver);
		seleArea = 0;
		CreateRouter(seleArea);
		RouterSetting();
	//	RouterSetting(1);
	//	RouterSetting(2);
		driver.quit();
		
	}
	
		private void RouterSetting() throws Exception {
			
			// 左侧云路由器
			WebElement cloudserver = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-router']"));
			cloudserver.click();
			Thread.sleep(10000);
			
			//选择华东一区
			seleArea = 0;
			pubMeth.seleAreaall(driver, seleArea);
			
			// 判断有没有建出来，取得返回值re
			By locator = By.xpath("//a[@data-testid='table-row-0-id']");
			boolean reValueSetting = pubMeth.isElementExsit(driver, locator);
			System.out.println(reValueSetting);
			
			if (reValueSetting) {
			// 选择云路由器
			WebElement selectrouter = driver.findElement(locator);
			selectrouter.click();
			Thread.sleep(2000);
			
			} else {
				System.out.println("没有路由器");
			}
			//路由子网
			// 选择路由子网
			WebElement network = driver.findElement(By.xpath("//a[@data-testid='tabs-1']"));
			network.click();
			Thread.sleep(2000);
						
			// 新建路由子网
			pubMeth.newOnecreat(driver);
	
			// 输入新网段
			WebElement inputnet = driver.findElement(By.xpath("//input[@data-testid='pop-network']"));
			inputnet.clear();
			inputnet.sendKeys("99");
			Thread.sleep(2000);
		
			//提交
			pubMeth.submit(driver);
			System.out.println("路由子网提交");
		
		
		
		
			//端口转发
			// 选择端口转发
			WebElement portsend = driver.findElement(By.xpath("//a[@data-testid='tabs-2']"));
			portsend.click();
			Thread.sleep(2000);
		
			pubMeth.newOnecreat(driver);
			
			// 输入端口名称
			WebElement inputname = driver.findElement(By.xpath("//input[@data-testid='pop-name']"));
			inputname.clear();
			inputname.sendKeys("autoport");
			Thread.sleep(2000);
			
			// 输入端口号 
			WebElement inputport = driver.findElement(By.xpath("//input[@data-testid='pop-sourcePort']"));
			inputport.clear();
			inputport.sendKeys("22");
			Thread.sleep(2000);
			
			// 输入目标IP
			WebElement inputip = driver.findElement(By.xpath("//input[@data-testid='pop-ip']"));
			inputip.clear();
			inputip.sendKeys("192.168.99.2");
			Thread.sleep(2000);
			
			// 输入端口号 
			WebElement inputport2 = driver.findElement(By.xpath("//input[@data-testid='pop-targetPort']"));
			inputport2.clear();
			inputport2.sendKeys("22");
			Thread.sleep(2000);
			
			//提交
			pubMeth.submit(driver);
			System.out.println("端口转发提交");
			
			
			
			
			
			//隧道服务
			// 选择隧道服务
			WebElement tunnel = driver.findElement(By.xpath("//a[@data-testid='tabs-3']"));
			tunnel.click();
			Thread.sleep(2000);
		
			pubMeth.newOne(driver);
			
			// 输入端口名称
			WebElement inputtunname = driver.findElement(By.xpath("//input[@data-testid='pop-name']"));
			inputtunname.clear();
			inputtunname.sendKeys("autotunnel");
			Thread.sleep(2000);
			
			// 输入远端路由
			WebElement inputrouter = driver.findElement(By.xpath("//input[@data-testid='pop-router']"));
			inputrouter.clear();
			inputrouter.sendKeys("10.20.30.2");
			Thread.sleep(2000);
			
			// 输入本地点对点IP
			WebElement localip = driver.findElement(By.xpath("//input[@data-testid='pop-local_ip']"));
			localip.clear();
			localip.sendKeys("1");
			Thread.sleep(2000);
			
			// 输入对端点对点IP
			WebElement peerip = driver.findElement(By.xpath("//input[@data-testid='pop-targetIP']"));
			peerip.clear();
			peerip.sendKeys("2");
			Thread.sleep(2000);
						
			// 输入目标网络
			WebElement peernet = driver.findElement(By.xpath("//input[@data-testid='pop-targetIPGroup-0']"));
			peernet.clear();
			peernet.sendKeys("192.168.200.0/24");
			Thread.sleep(2000);
			
			//提交
			pubMeth.submit(driver);
			
			// 应用修改
			pubMeth.appMdf(driver);
								
			//VPN服务
			// 选择VPN服务
			WebElement vpn = driver.findElement(By.xpath("//a[@data-testid='tabs-4']"));
			vpn.click();
			Thread.sleep(2000);
		
			//pubMeth.newOne(driver);
			
			WebElement newcreate = driver.findElement(By.xpath("/html/body/div/div/div[1]/div/div[1]/div[2]/div[3]/div[2]/div[1]/div/div[2]/div[2]/div[2]/div/span[2]/span"));
			newcreate.click();
			Thread.sleep(2000);
			
			// 输入vpn
			WebElement vpnname = driver.findElement(By.xpath("//input[@data-testid='pop-name']"));
			vpnname.clear();
			vpnname.sendKeys("autopptp");
			Thread.sleep(2000);
						
			// 输入vpn密码
			WebElement vpnpwd = driver.findElement(By.xpath("//input[@data-testid='pop-password']"));
			vpnpwd.clear();
			vpnpwd.sendKeys("Anchang123");
			Thread.sleep(2000);
			
			//提交
			pubMeth.submit(driver);
			System.out.println("vpn提交");
			
			
			
			

			//过滤控制
			// 选择过滤控制
			WebElement filter = driver.findElement(By.xpath("//a[@data-testid='tabs-5']"));
			filter.click();
			Thread.sleep(2000);
		
			pubMeth.newOnecreat(driver);
			
			// 输入名称
			WebElement filtername = driver.findElement(By.xpath("//input[@data-testid='pop-name']"));
			filtername.clear();
			filtername.sendKeys("autofilter");
			Thread.sleep(2000);
			
			// 输入优先级
			WebElement detail = driver.findElement(By.xpath("//input[@data-testid='pop-level']"));
			detail.clear();
			detail.sendKeys("1");
			Thread.sleep(2000);
			
			// 输入源IP
			WebElement sourceip = driver.findElement(By.xpath("//input[@data-testid='pop-sourceIP']"));
			sourceip.clear();
			sourceip.sendKeys("190.168.100.2");
			Thread.sleep(2000);
						
			//提交
			pubMeth.submit(driver);
			System.out.println("过滤控制提交");
			
			
			
			//高级配置
			// 选择高级配置
			WebElement highsetting = driver.findElement(By.xpath("//a[@data-testid='tabs-6']"));
			highsetting.click();
			Thread.sleep(2000);
		
			// 修改高级配置
			WebElement modify = driver.findElement(By.xpath("//span[@data-testid='table-modify']"));
			modify.click();
			Thread.sleep(2000);
			
			// 输入数字
			WebElement inputnumber = driver.findElement(By.xpath("//input[@data-testid='pop-mss']"));
			inputnumber.clear();
			inputnumber.sendKeys("1200");
			Thread.sleep(2000);
			
			//提交
			pubMeth.submit(driver);
			System.out.println("高级配置提交");
			
			// 应用修改
			pubMeth.appMdf(driver);
		}	
		
		
		private void CreateRouter(int seleArea) throws Exception {	
		
		for (int i = 1; i < 2; i++) {
			// 左侧云路由器
			WebElement cloudserver = driver.findElement(By.xpath("//a[@data-testid='sidebarNav-cloud-router']"));
			cloudserver.click();
			Thread.sleep(2000);

			// 新建
			WebElement creat = driver.findElement(By.xpath("//span[@class='color-primary']"));
			creat.click();
			Thread.sleep(2000);
			
			// 按需
			// 先定位div ul
			WebElement anxu = driver.findElement(By.xpath("//div[@class='mt-20 tabs-item']"));
			WebElement anxu1 = anxu.findElement(By.xpath("//li[@class='main-item']"));
			anxu1.click();
			Thread.sleep(3000);
			pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");

			// 选择三个区中的一个
			//Random RandomseleArea = new Random();
			//seleArea = RandomseleArea.nextInt(3);// 为0-1个数
			pubMeth.seleAreaall(driver, seleArea);
			
			// 输入路由器名称
			
			pubMeth.inputName(driver, "autorouter" + i);
			
			// 类型
			Random RandomtypeSmail = new Random();
			typeSmail = RandomtypeSmail.nextInt(2);// 为0-1个数
			if (typeSmail == 0) {
				WebElement seletype = driver.findElement(By.xpath("//span[@data-testid='radio-type-0']"));
				seletype.click();
				Thread.sleep(2000);
				System.out.println("类型");
			} else {
				WebElement seletype = driver.findElement(By.xpath("//span[@data-testid='radio-type-1']"));
				seletype.click();
				Thread.sleep(2000);
			}

			// 带宽
			WebElement storage = driver.findElement(By.xpath("//input[@data-testid='customSlideNum']"));
			storage.clear();
			// 1-5M
			int max1 = 5;
			int min1 = 1;
			Random randoma = new Random();
			size = randoma.nextInt(max1) % (max1 - min1 + 1) + min1;
			System.out.println("size=" + size);
			storage.clear();

			storage.sendKeys(+size + "");
			Thread.sleep(2000);
			System.out.println("输入带宽" + size + "M");
			String strsize = String.valueOf(size);
			pubMeth.rwFile("带宽=", strsize, "M");

			// 取页面显示的价格
			//pubMeth.UIprice(driver);
			
			// 提交
			WebElement submit = driver.findElement(By.xpath("//div[@data-testid='buy-button']"));
			submit.click();
			Thread.sleep(20000);
			System.out.println("提交");

			// 判断有没有建出来，取得返回值re
			By locator = By.xpath("//i[@data-testid='table-row-0-checkbox']");
			boolean reValue = pubMeth.isElementExsit(driver, locator);
			System.out.println(reValue);

			// 用例运行结果写入LOG文件
			
			pubMeth.writeLog("CreateRouter",reValue);


		} // for

	}// 创建服务器函数结束



	
}

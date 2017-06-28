package cloud.snapshot;

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

public class SnapshotInstance {

	/**
	 * 创建云服务器快照备份
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	int intRandomCpu = 0;
	int intRandomMem = 0;
	int intRandomOs = 0;// 选择操作系统需要的随机数
	String priceValueSnapshot;
	String priceValueQu;
	boolean valueOk = false;
	int oneKernel = 0; // oneKernel = 0时为1核，oneKernel = 1时选择其它核数intRandomCpu
	int memOneGi = 0; // oneKernel = 0时（memOneGi = 0时为1G, memOneGi = 1时为其它内存twoGiOrFourGi）
	int otherKernelOneGi = 0; // oneKernel = 1时（otherKernelOneGi = 0时为1G，otherKernelOneGi = 1时选择其它内存intRandomMem）
	int twoGiOrFourGi = 0; // oneKernel = 0即CPU为1核，memOneGi = 1 时，twoGiOrFourGi为2G或者4G
	int osWindows = 0; // osWindows = 0即windows操作系统
	int seleNet = 0; // seleNet = 1时为内网
	int seleArea = 0; // seleArea = 1时亚太一区
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void backupServer() throws Exception {
		driver = pubMeth.beforeTest(driver);
		cloud2Instance();

		// 左侧云服务器
		WebElement cloudserver = driver.findElement(By.xpath("//a[@data-testid = 'sidebarNav-cloud-instance']"));
		cloudserver.click();
		Thread.sleep(3000);
		

		// 选择华东一区
		WebElement yatai = driver.findElement(By.xpath("//span[@data-testid = 'region-select-ac1']"));
		yatai.click();
		Thread.sleep(3000);
		System.out.println("华东一区");
		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
		pubMeth.rwFile("区域为", "华东一区", "");
		
		// 判断服务器有没有建出来，取得返回值re
		Base pubMeth = new Base();
		By locator = By.xpath("//i[@data-testid = 'table-row-0-checkbox']");
		boolean reValue = pubMeth.isElementExsit(driver, locator);
		System.out.println("reValue = " + reValue);

		if (pubMeth.isElementExsit(driver, locator)) {
			// if (re = false) {break;}

			// 选择建好的服务器
			WebElement serverok = driver.findElement(locator);
			serverok.click();
			Thread.sleep(2000);
			
			// 更多操作
			WebElement more = driver.findElement(By.xpath("//span[@data-testid = 'moreOpts']"));
			more.click();
			Thread.sleep(2000);
			
			// 创建快照备份
			WebElement createbk = driver.findElement(By.xpath("//span[@data-testid = 'moreOpts-createSnapshot']"));
			createbk.click();
			Thread.sleep(2000);
			System.out.println("创建快照备份");

			// 输入快照备份名称
			WebElement servername = driver.findElement(By.xpath("//input[@data-testid = 'createSnapShot-name']"));
			servername.sendKeys("serverbk");
			Thread.sleep(3000);
			

			// 提交
			WebElement submit = driver.findElement(By.xpath("//span[@data-testid = 'pop-model-confirm']"));
			submit.click();
			Thread.sleep(10000);
			System.out.println("提交");

			// 左侧快照备份
			WebElement bk = driver.findElement(By.xpath("//a[@data-testid = 'sidebarNav-cloud-snapshot']"));
			bk.click();
			Thread.sleep(3000);
			System.out.println("左侧快照备份");

			// 判断快照备份有没有建出来，取得返回值rebk

			By locatorbk = By.xpath("//tbody[@data-testid = 'table-row-0']");
			boolean reValue1 = Base.isElementExsit(driver, locatorbk);
			System.out.println("reValue1 =" + reValue1);

			// 选择这个快照备份
			WebElement bkid = driver.findElement(By.xpath("//span[@data-testid = 'table-row-0-id']"));
			bkid.click();
			Thread.sleep(5000);

			// 取页面显示的价格
			WebElement pricelast = driver.findElement(By.xpath(
					"/html/body/div/div/div[1]/div/div[1]/div[2]/div[3]/div[2]/div[1]/div/div[1]/div[2]/dl[1]/twoGiOrFourGi[3]"));
			priceValueSnapshot = pricelast.getText();
			priceValueQu = priceValueSnapshot.substring(0, priceValueSnapshot.length() - 4);
			Thread.sleep(3000);
			System.out.println("页面取得的价格为 = " + priceValueQu);
			pubMeth.rwFile("页面价格 = ", priceValueQu, "");

			// 选择增备份
			WebElement atwoGiOrFourGibk = driver.findElement(By.xpath("//span[@data-testid = 'createSnapshot-btn']"));
			atwoGiOrFourGibk.click();
			Thread.sleep(3000);

			// 输入增备份名称
			WebElement bkname = driver.findElement(By.xpath("//input[@data-testid = 'createSnapShot-name']"));
			bkname.sendKeys("serverbkatwoGiOrFourGi");
			Thread.sleep(3000);
			
			// 提交
			WebElement submitbk = driver.findElement(By.xpath("//span[@data-testid = 'pop-model-confirm']"));
			submitbk.click();
			Thread.sleep(10000);
			System.out.println("提交");

			calculateSum();

			// 用例运行结果写入LOG文件
			String cases = "SnapshotInstance";
			
			//取得当前日期
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			
			//如果已经建出来并且核对的价格正确，Pass的结果写入文件
			if (reValue1 && valueOk){
				
				pubMeth.rwFile(cases,df.format(new Date()),"Pass");
			
			} else { 
				pubMeth.rwFile(cases,df.format(new Date()),"Fail");
			}

		} else {
			System.out.println("没有服务器");
		}

		driver.close();
	}

	/**
	 * 创建云服务器
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	private void cloud2Instance() throws InterruptedException {

		// 左侧云服务器
		WebElement cloudserver = driver.findElement(By.xpath("//a[@data-testid = 'sidebarNav-cloud-instance']"));
		cloudserver.click();
		Thread.sleep(3000);
		

		// 华东一区或者亚太一区

		Random RandomseleArea = new Random();
		seleArea = RandomseleArea.nextInt(1);// 只有一个随机数0
		if (seleArea == 0) {
			WebElement yatai = driver.findElement(By.xpath("//span[@data-testid = 'region-select-ac1']"));
			yatai.click();
			Thread.sleep(3000);
			System.out.println("华东一区");
		} else {
			WebElement huadong = driver.findElement(By.xpath("//span[@data-testid = 'region-select-ac2']"));
			huadong.click();
			Thread.sleep(3000);
			System.out.println("亚太一区");
		}
	

		// 新建
		WebElement creat = driver.findElement(By.xpath("//span[@class = 'color-primary']"));
		creat.click();
		Thread.sleep(3000);
		

		// 按需
		// 先定位div ul
		WebElement anxu = driver.findElement(By.xpath("//div[@class = 'mt-20 tabs-item']"));
		WebElement anxu1 = anxu.findElement(By.xpath("//li[@class = 'main-item']"));
		anxu1.click();
		Thread.sleep(3000);
		
		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");

		// 系统
		// 先得到一种class写入list, get(0)看要第几个, 使用value的方法click
		java.util.List<WebElement> element = driver
				.findElements(By.xpath("//span[@class = 'tab-switch tab-switch-111 w90']"));

		WebElement value = element.get(0);
		value.click();
		Thread.sleep(3000);
		System.out.println(value.getText());

		// 选取下拉列表中的操作系统

		Random randomosWindows = new Random();
		osWindows = randomosWindows.nextInt(2);
		System.out.println("osWindows = " + osWindows);
		if (osWindows == 1) {

			// 点系统下拉列表
			WebElement oneKernela = driver.findElement(By.xpath("//i[@class = 'triangle-down abs-right8 abs-mitwoGiOrFourGile']"));
			oneKernela.click();
			Thread.sleep(3000);
			

			// 生成随机数
			Random randomos = new Random();
			intRandomOs = randomos.nextInt(7);// 为0-6个数

			java.util.List<WebElement> os = driver.findElements(By.xpath("//li[@class = 'dropdown-item single-line']"));
			WebElement indexos = os.get(intRandomOs);
			indexos.click();
			Thread.sleep(3000);
			System.out.println("选择第" + intRandomOs + "个操作系统");

		} else {

			System.out.println("ubuntu操作系统");
			pubMeth.rwFile("OS = ", "ubuntu操作系统", "");

		}

		Random randomoneKernel = new Random();
		oneKernel = randomoneKernel.nextInt(2);
		System.out.println("oneKernel = " + oneKernel);
		// oneKernel = 0时为1核，oneKernel = 1时选择其它核数intRandomCpu
		if (oneKernel == 1) {
			// 生成4-6随机数
			int max = 6;
			int min = 4;
			Random randomcpu = new Random();
			intRandomCpu = randomcpu.nextInt(max) % (max - min + 1) + min;
			System.out.println("CPU随机数intRandomCpu为：" + intRandomCpu);
			// 选取2.4.8核中的一个
			// driver
			java.util.List<WebElement> cpulist = driver
					.findElements(By.xpath("//span[@class = 'tab-switch tab-switch-111 w90']"));
			int tab111num = cpulist.size();
			System.out.println("tab111num = " + tab111num);
			WebElement cpuvalue = cpulist.get(intRandomCpu);
			cpuvalue.click();
			Thread.sleep(3000);
			System.out.println(cpuvalue.getText());
			// pubMeth.rwFile("cpu = ", cpuvalue.getText(), "");
			// 选取内存
			Random randomotherKernelOneGi = new Random();
			otherKernelOneGi = randomotherKernelOneGi.nextInt(2);
			System.out.println("otherKernelOneGi = " + otherKernelOneGi);
			if (otherKernelOneGi == 1) {
				// 生成7-9随机数
				int max1 = 9;
				int min1 = 7;
				Random randommem = new Random();
				intRandomMem = randommem.nextInt(max1) % (max1 - min1 + 1) + min1;
				System.out.println("内存随机数intRandomMem为：" + intRandomMem);
				// driver
				java.util.List<WebElement> memlist = driver
						.findElements(By.xpath("//span[@class = 'tab-switch tab-switch-111 w90']"));
				WebElement memvalue = memlist.get(intRandomMem);
				memvalue.click();
				Thread.sleep(3000);
				System.out.println(memvalue.getText());
				pubMeth.rwFile("mem = ", memvalue.getText(), "");
			} else {
				System.out.println("1G");
				// pubMeth.rwFile("mem = ", "第一个位置G", "");
			}

		} else {
			// 不选即为1核，此时内存为1，2，4G三种情况
			System.out.println("1核");
			// pubMeth.rwFile("CPU = ", "1核", "");

			Random randommemOneGi = new Random();
			memOneGi = randommemOneGi.nextInt(2);
			if (memOneGi == 1) {
				// 生成7-8随机数
				int max1 = 8;
				int min1 = 7;
				Random randommem1 = new Random();
				twoGiOrFourGi = randommem1.nextInt(max1) % (max1 - min1 + 1) + min1;
				System.out.println("内存随机数为：" + twoGiOrFourGi);
				// 选取2，4G中的一个
				// driver
				java.util.List<WebElement> memlist = driver
						.findElements(By.xpath("//span[@class = 'tab-switch tab-switch-111 w90']"));
				WebElement memvalue1 = memlist.get(twoGiOrFourGi);
				memvalue1.click();
				Thread.sleep(3000);
				System.out.println(memvalue1.getText());
				pubMeth.rwFile("mem = ", memvalue1.getText(), "");
			} else {
				System.out.println("1G");
				pubMeth.rwFile("mem = ", "第一个位置G", "");
				// 不选即为1G
			}
		}

		// 内网公网随机选
		Random randomseleNet = new Random();
		seleNet = randomseleNet.nextInt(2);
		System.out.println("seleNet = " + seleNet);
		if (seleNet == 1) {
			// 选择内网有问题java.lang.IndexOutOfBoundsException
			System.out.println("内网");
			// driver
			if (oneKernel == 1) {
				java.util.List<WebElement> netlist = driver
						.findElements(By.xpath("//span[@class = 'tab-switch tab-switch-111 w90']"));
				int num = netlist.size();
				System.out.println("个数" + num);
				WebElement netvalue = netlist.get(10);
				netvalue.click();
				Thread.sleep(3000);
				System.out.println(netvalue.getText());
			} else {
				java.util.List<WebElement> netlist = driver
						.findElements(By.xpath("//span[@class = 'tab-switch tab-switch-111 w90']"));
				int num1 = netlist.size();
				System.out.println("个数" + num1);
				WebElement netvalue = netlist.get(9);
				netvalue.click();
				Thread.sleep(3000);
				System.out.println(netvalue.getText());

			}

			pubMeth.rwFile("网络为", "内网", "");
			// 新建SDN网络
			WebElement createsdn = driver.findElement(By.xpath(
					"/html/body/div/div/div[1]/div/div[1]/div[2]/div[3]/div[6]/div/div[3]/div[1]/div[3]/div[2]/div[2]/div[5]/span[2]"));
			createsdn.click();
			Thread.sleep(3000);
			WebElement sdnname = driver.findElement(By.xpath("//input[@class = 'input-text input-long']"));
			
			Thread.sleep(3000);
			WebElement sdnsub = driver.findElement(By.xpath("//span[@class = 'button button-primary']"));
			sdnsub.click();
			Thread.sleep(10000);
			System.out.println("创建SDN网络");

		} else {
			
			pubMeth.rwFile("网络为", "公网", "");
		}

		// 输入服务器名称
		WebElement servername = driver.findElement(By.xpath("//input[@class = 'input-text input-long']"));
		servername.sendKeys("autoserver");
		Thread.sleep(3000);
		

		// 输入pwd
		WebElement pwd = driver.findElement(By.xpath("//input[@class = 'input-text input-long mr10']"));
		pwd.sendKeys("Anchang123");
		Thread.sleep(3000);
		

		// 再次输入pwd
		java.util.List<WebElement> repwd = driver.findElements(By.xpath("//input[@class = 'input-text input-long']"));
		WebElement repwdvalue = repwd.get(2);
		repwdvalue.sendKeys("Anchang123");
		// int num = repwd.size();
		// System.out.println(num);
		Thread.sleep(3000);
		

		// 提交
		WebElement submit = driver.findElement(By.xpath("//div[@class = 'btn-big']"));
		submit.click();
		Thread.sleep(30000);
		System.out.println("立即开通");
	}

	/**
	 * 计算快照备份价格
	 * 
	 * @author yangw
	 * @version 1.00
	 * @throws Exception 
	 */
	private void calculateSum() throws Exception {

		double sum = 0;
		if (osWindows == 1) {// 当选择多个操作系统时
			if (intRandomOs == 0) {// 当选择第0个操作系统，即windows时
				sum = 0.004 * 5;
			} else {
				sum = 0.004 * 2;
			}
		} else {// 当不选时为ubuntu
			sum = 0.004 * 2;
		}

		// 取小数点后四位
		//String sumTo = String.format("%.4f", sum);
		String sumTo = String.valueOf(sum);
		System.out.println("计算值=" + sumTo); 
		pubMeth.rwFile("计算值=", sumTo, ""); 
		//算出的值与页面取值比较是否相等
		valueOk = sumTo.equals(priceValueQu); 
		if (valueOk) {
			System.out.println("price sum is correctly"); 
			pubMeth.rwFile("结果 = ", "price sum is correctly", ""); 
		} else {
			System.out.println("no correctly"); 
			pubMeth.rwFile("结果 = ", "not correctly", ""); 
		}	

	}

}

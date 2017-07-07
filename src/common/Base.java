package common;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import common.Login;

public class Base {

	/**
	 * 登录安畅云
	 * 
	 * @author yangw
	 * @version 1.00
	 */

	@Test
	public WebDriver beforeTest(WebDriver driver) throws Exception {
		//Login userLogin = new Login();
		//String[] value = userLogin.loginSeting();
		System.setProperty("webdriver.chrome.driver", "D:\\Tools\\selenium + Firefox 33.0\\chromedriver.exe"); 
		driver= new ChromeDriver();

		//driver = new FirefoxDriver();
		driver.manage().window().maximize();
		//driver.get(value[4]);
		driver.get("https://testconsole.anchnet.com");
		Thread.sleep(2000);
		WebElement searchInput = driver.findElement(By.name("email"));
		//searchInput.sendKeys(value[0]);
		searchInput.sendKeys("test4@51idc.com");
		Thread.sleep(2000);
		WebElement searchInput2 = driver.findElement(By.name("password"));
		//searchInput2.sendKeys(value[1]);
		searchInput2.sendKeys("123456");
		Thread.sleep(2000);
		WebElement loginButton = driver.findElement(By.xpath("//input[@onclick='onLogin()']"));
		loginButton.click();
		Thread.sleep(2000);
		/*WebElement consoleButton = driver.findElement(By.xpath("//a[@href='http://testconsole.anchnet.com']"));
		consoleButton.click();
		Thread.sleep(3000);*/
		return driver;

	}
	
	
	@Test
	public WebDriver seleAreaall(WebDriver driver,int seleArea )throws Exception{

		switch (seleArea) {
		case 0:
			WebElement huadong1 = driver.findElement(By.xpath("//span[@data-testid='region-select-ac1']"));
			huadong1.click();
			Thread.sleep(3000);
			System.out.println("华东一区");
			rwFile("华东一区", "", "");
			break;
		case 1:
			WebElement yatai = driver.findElement(By.xpath("//span[@data-testid='region-select-ac2']"));
			yatai.click();
			Thread.sleep(3000);
			System.out.println("亚太一区");
			rwFile("亚太一区", "", "");
			break;
		case 2:
			WebElement huadong2 = driver.findElement(By.xpath("//span[@data-testid='region-select-ac3']"));
			huadong2.click();
			Thread.sleep(3000);
			System.out.println("华东二区");
			rwFile("华东二区", "", "");
			break;
		}
		
		return driver;
	}
	
	//新建
	@Test
		public WebDriver newOne(WebDriver driver) throws Exception {
				
			//WebElement creat = driver.findElement(By.xpath("//a[@data-testid = 'create']"));
			////creat.click();
			//Thread.sleep(3000);
			
			WebElement creat = driver.findElement(By.xpath("//span[@class='color-primary']"));
			creat.click();
			Thread.sleep(2000);
			
			return driver;
		}
	    @Test
		public WebDriver newOnecreat(WebDriver driver) throws Exception {
		
			WebElement newnet = driver.findElement(By.xpath("//span[@data-testid='create']"));
			newnet.click();
			Thread.sleep(2000);
			return driver;
		}
	//按需
	@Test
	public WebDriver anXu(WebDriver driver)throws Exception{
		
		WebElement anxu1 = driver.findElement(By.xpath("//li[@data-testid = 'tabs-1']"));
		anxu1.click();
		Thread.sleep(1000);
		System.out.println("按需");
		return driver;
		
	}
	
	//输入服务器名称
	public void inputName(WebDriver driver,String name)throws Exception{
		
		WebElement serverName = driver.findElement(By.xpath("//input[@class='input-text input-long']"));
		serverName.clear();
		serverName.sendKeys(name);
		Thread.sleep(3000);
	}
	
	
	
	//取得页面价格	
	String priceValue = "";	
	@Test
	public WebDriver UIprice(WebDriver driver)throws Exception{	
		WebElement pricelast = driver.findElement(By.xpath("//span[@class='price']"));
		String priceValue1 = pricelast.getText();
		priceValue = priceValue1;
		//Thread.sleep(2000);
		System.out.println("第一次页面取得的价格为=" + priceValue1);
		rwFile("第一次页面价格=", priceValue1, "");
		return driver;
	}
	
	boolean valueOk = false;
    //结果核对
	@Test
	public void calcuCheck(String sumTo)throws Exception{	
		
		System.out.println("计算值=" + sumTo); 
		System.out.println("priceValue=" + priceValue); 
		rwFile("计算值=", sumTo, ""); 
		//算出的值与页面取值比较是否相等
		valueOk = sumTo.equals(priceValue);
		System.out.println("valueOk=" + valueOk); 
		if (valueOk) {
			System.out.println("price sum is correctly"); 
			rwFile("结果 = ", "price sum is correctly", ""); 
		} else{
			System.out.println("no correctly"); 
			rwFile("结果 = ", "not correctly", ""); }	
		//return valueOk;
	}
	
	//有关创建的用例执行结果写入文件
	@Test
	public void writeLog(String cases,boolean reValue)throws Exception{	
		System.out.println("valueOkBase=" + valueOk); 
		// 取得当前日期
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置日期格式

		// 如果已经建出来并且核对的价格正确，Pass的结果写入文件

		if (reValue && valueOk) {
			rwFile(cases, df.format(new Date()), "Pass");
		} else {
			rwFile(cases, df.format(new Date()), "Fail");
		}	
	}
	
	//有关删除的用例执行结果写入文件
	@Test
	public void writeLogDel(String cases,boolean reValue)throws Exception{	
		
		// 取得当前日期
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 设置日期格式

		// 如果已经建出来并且核对的价格正确，Pass的结果写入文件

		if (!reValue) {
			rwFile(cases, df.format(new Date()), "Pass");
		} else {
			rwFile(cases, df.format(new Date()), "Fail");
		}	
	}
	
	//删除操作部分
	public WebDriver moreOptsDel(WebDriver driver)throws Exception{	

		WebElement more = driver.findElement(By.xpath("//span[@data-testid = 'moreOpts']"));
		more.click();
		Thread.sleep(2000);
		
		WebElement moreDel = driver.findElement(By.xpath("//li[@data-testid = 'moreOpts-delete']"));
		moreDel.click();
		Thread.sleep(2000);
		
		WebElement checkNum = driver.findElement(By.xpath("//input[@placeholder = '请输入验证码']"));
		checkNum.clear();
		checkNum.sendKeys("51idc");
		Thread.sleep(2000);
		return driver;
	}
	//删除操作不需输入验证码
		public WebDriver DelNoCheckNum(WebDriver driver)throws Exception{	
			WebElement more = driver.findElement(By.xpath("//span[@data-testid = 'moreOpts']"));
			more.click();
			Thread.sleep(2000);
			
			WebElement moreDel = driver.findElement(By.xpath("//li[@data-testid = 'moreOpts-delete']"));
			moreDel.click();
			Thread.sleep(2000);
			return driver;
		}

		//提交
		@Test
		public WebDriver submit(WebDriver driver)throws Exception{
			
			WebElement submit = driver.findElement(By.xpath("//span[@class='button button-primary']"));
			submit.click();
			Thread.sleep(20000);
			System.out.println("提交");
			return driver;
			
		}
		
		//应用修改
		@Test
		public WebDriver appMdf(WebDriver driver)throws Exception{
			
			WebElement confirm = driver.findElement(By.xpath("//span[@data-testid='modifyTips']"));
			confirm.click();
			Thread.sleep(10000);
			System.out.println("应用修改");
			return driver;
			
		}
			
		// 确认提交
		@Test
		public WebDriver subcfm(WebDriver driver)throws Exception{
				
			WebElement subcfm = driver.findElement(By.xpath("//span[@data-testid='clickEvent']"));
			subcfm.click();
			Thread.sleep(2000);
			System.out.println("确认提交");
			return driver;
		}
		// 确认付款
		@Test
		public WebDriver paycfm(WebDriver driver)throws Exception{
				
			WebElement paycfm = driver.findElement(By.xpath("//span[@data-testid='confirmPay']"));
			paycfm.click();
			Thread.sleep(10000);
			System.out.println("确认付款");
			return driver;
		}
		
	/**
	 * 检查元素是否存在
	 * 
	 * @author yangw
	 * @version 1.00
	 */
    @Test
	public static boolean isElementExsit(WebDriver driver, By locator) {
		boolean flag = false;
		try {
			WebElement element = driver.findElement(locator);
			flag = null != element;
		} catch (Exception e) {
			System.out.println("Element:" + locator.toString() + " is not exsit!");
		}
		return flag;
	}

	/**
	 * 保存LOG
	 * 
	 * @author yangw
	 * @version 1.00
	 */
    @Test
	public void rwFile(String cases, String message, String value) {
		FileWriter fw = null;
		try {
			fw = new FileWriter("E:\\gitTest\\cloud2log.txt", true);
			// 布尔转字符串
			String valuestring = String.valueOf(value);
			fw.write(cases + "" + message + "" + valuestring + "\r\n");// 这里向文件中输入结果
			fw.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}// 写LOG函数结束

}

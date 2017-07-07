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
	 * ��¼������
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
			System.out.println("����һ��");
			rwFile("����һ��", "", "");
			break;
		case 1:
			WebElement yatai = driver.findElement(By.xpath("//span[@data-testid='region-select-ac2']"));
			yatai.click();
			Thread.sleep(3000);
			System.out.println("��̫һ��");
			rwFile("��̫һ��", "", "");
			break;
		case 2:
			WebElement huadong2 = driver.findElement(By.xpath("//span[@data-testid='region-select-ac3']"));
			huadong2.click();
			Thread.sleep(3000);
			System.out.println("��������");
			rwFile("��������", "", "");
			break;
		}
		
		return driver;
	}
	
	//�½�
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
	//����
	@Test
	public WebDriver anXu(WebDriver driver)throws Exception{
		
		WebElement anxu1 = driver.findElement(By.xpath("//li[@data-testid = 'tabs-1']"));
		anxu1.click();
		Thread.sleep(1000);
		System.out.println("����");
		return driver;
		
	}
	
	//�������������
	public void inputName(WebDriver driver,String name)throws Exception{
		
		WebElement serverName = driver.findElement(By.xpath("//input[@class='input-text input-long']"));
		serverName.clear();
		serverName.sendKeys(name);
		Thread.sleep(3000);
	}
	
	
	
	//ȡ��ҳ��۸�	
	String priceValue = "";	
	@Test
	public WebDriver UIprice(WebDriver driver)throws Exception{	
		WebElement pricelast = driver.findElement(By.xpath("//span[@class='price']"));
		String priceValue1 = pricelast.getText();
		priceValue = priceValue1;
		//Thread.sleep(2000);
		System.out.println("��һ��ҳ��ȡ�õļ۸�Ϊ=" + priceValue1);
		rwFile("��һ��ҳ��۸�=", priceValue1, "");
		return driver;
	}
	
	boolean valueOk = false;
    //����˶�
	@Test
	public void calcuCheck(String sumTo)throws Exception{	
		
		System.out.println("����ֵ=" + sumTo); 
		System.out.println("priceValue=" + priceValue); 
		rwFile("����ֵ=", sumTo, ""); 
		//�����ֵ��ҳ��ȡֵ�Ƚ��Ƿ����
		valueOk = sumTo.equals(priceValue);
		System.out.println("valueOk=" + valueOk); 
		if (valueOk) {
			System.out.println("price sum is correctly"); 
			rwFile("��� = ", "price sum is correctly", ""); 
		} else{
			System.out.println("no correctly"); 
			rwFile("��� = ", "not correctly", ""); }	
		//return valueOk;
	}
	
	//�йش���������ִ�н��д���ļ�
	@Test
	public void writeLog(String cases,boolean reValue)throws Exception{	
		System.out.println("valueOkBase=" + valueOk); 
		// ȡ�õ�ǰ����
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // �������ڸ�ʽ

		// ����Ѿ����������Һ˶Եļ۸���ȷ��Pass�Ľ��д���ļ�

		if (reValue && valueOk) {
			rwFile(cases, df.format(new Date()), "Pass");
		} else {
			rwFile(cases, df.format(new Date()), "Fail");
		}	
	}
	
	//�й�ɾ��������ִ�н��д���ļ�
	@Test
	public void writeLogDel(String cases,boolean reValue)throws Exception{	
		
		// ȡ�õ�ǰ����
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // �������ڸ�ʽ

		// ����Ѿ����������Һ˶Եļ۸���ȷ��Pass�Ľ��д���ļ�

		if (!reValue) {
			rwFile(cases, df.format(new Date()), "Pass");
		} else {
			rwFile(cases, df.format(new Date()), "Fail");
		}	
	}
	
	//ɾ����������
	public WebDriver moreOptsDel(WebDriver driver)throws Exception{	

		WebElement more = driver.findElement(By.xpath("//span[@data-testid = 'moreOpts']"));
		more.click();
		Thread.sleep(2000);
		
		WebElement moreDel = driver.findElement(By.xpath("//li[@data-testid = 'moreOpts-delete']"));
		moreDel.click();
		Thread.sleep(2000);
		
		WebElement checkNum = driver.findElement(By.xpath("//input[@placeholder = '��������֤��']"));
		checkNum.clear();
		checkNum.sendKeys("51idc");
		Thread.sleep(2000);
		return driver;
	}
	//ɾ����������������֤��
		public WebDriver DelNoCheckNum(WebDriver driver)throws Exception{	
			WebElement more = driver.findElement(By.xpath("//span[@data-testid = 'moreOpts']"));
			more.click();
			Thread.sleep(2000);
			
			WebElement moreDel = driver.findElement(By.xpath("//li[@data-testid = 'moreOpts-delete']"));
			moreDel.click();
			Thread.sleep(2000);
			return driver;
		}

		//�ύ
		@Test
		public WebDriver submit(WebDriver driver)throws Exception{
			
			WebElement submit = driver.findElement(By.xpath("//span[@class='button button-primary']"));
			submit.click();
			Thread.sleep(20000);
			System.out.println("�ύ");
			return driver;
			
		}
		
		//Ӧ���޸�
		@Test
		public WebDriver appMdf(WebDriver driver)throws Exception{
			
			WebElement confirm = driver.findElement(By.xpath("//span[@data-testid='modifyTips']"));
			confirm.click();
			Thread.sleep(10000);
			System.out.println("Ӧ���޸�");
			return driver;
			
		}
			
		// ȷ���ύ
		@Test
		public WebDriver subcfm(WebDriver driver)throws Exception{
				
			WebElement subcfm = driver.findElement(By.xpath("//span[@data-testid='clickEvent']"));
			subcfm.click();
			Thread.sleep(2000);
			System.out.println("ȷ���ύ");
			return driver;
		}
		// ȷ�ϸ���
		@Test
		public WebDriver paycfm(WebDriver driver)throws Exception{
				
			WebElement paycfm = driver.findElement(By.xpath("//span[@data-testid='confirmPay']"));
			paycfm.click();
			Thread.sleep(10000);
			System.out.println("ȷ�ϸ���");
			return driver;
		}
		
	/**
	 * ���Ԫ���Ƿ����
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
	 * ����LOG
	 * 
	 * @author yangw
	 * @version 1.00
	 */
    @Test
	public void rwFile(String cases, String message, String value) {
		FileWriter fw = null;
		try {
			fw = new FileWriter("E:\\gitTest\\cloud2log.txt", true);
			// ����ת�ַ���
			String valuestring = String.valueOf(value);
			fw.write(cases + "" + message + "" + valuestring + "\r\n");// �������ļ���������
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

	}// дLOG��������

}

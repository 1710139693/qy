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
	 * �����Ʒ��������ձ���
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	int intRandomCpu = 0;
	int intRandomMem = 0;
	int intRandomOs = 0;// ѡ�����ϵͳ��Ҫ�������
	String priceValueSnapshot;
	String priceValueQu;
	boolean valueOk = false;
	int oneKernel = 0; // oneKernel = 0ʱΪ1�ˣ�oneKernel = 1ʱѡ����������intRandomCpu
	int memOneGi = 0; // oneKernel = 0ʱ��memOneGi = 0ʱΪ1G, memOneGi = 1ʱΪ�����ڴ�twoGiOrFourGi��
	int otherKernelOneGi = 0; // oneKernel = 1ʱ��otherKernelOneGi = 0ʱΪ1G��otherKernelOneGi = 1ʱѡ�������ڴ�intRandomMem��
	int twoGiOrFourGi = 0; // oneKernel = 0��CPUΪ1�ˣ�memOneGi = 1 ʱ��twoGiOrFourGiΪ2G����4G
	int osWindows = 0; // osWindows = 0��windows����ϵͳ
	int seleNet = 0; // seleNet = 1ʱΪ����
	int seleArea = 0; // seleArea = 1ʱ��̫һ��
	WebDriver driver;
	Base pubMeth = new Base();

	@Test
	public void backupServer() throws Exception {
		driver = pubMeth.beforeTest(driver);
		cloud2Instance();

		// ����Ʒ�����
		WebElement cloudserver = driver.findElement(By.xpath("//a[@data-testid = 'sidebarNav-cloud-instance']"));
		cloudserver.click();
		Thread.sleep(3000);
		

		// ѡ�񻪶�һ��
		WebElement yatai = driver.findElement(By.xpath("//span[@data-testid = 'region-select-ac1']"));
		yatai.click();
		Thread.sleep(3000);
		System.out.println("����һ��");
		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");
		pubMeth.rwFile("����Ϊ", "����һ��", "");
		
		// �жϷ�������û�н�������ȡ�÷���ֵre
		Base pubMeth = new Base();
		By locator = By.xpath("//i[@data-testid = 'table-row-0-checkbox']");
		boolean reValue = pubMeth.isElementExsit(driver, locator);
		System.out.println("reValue = " + reValue);

		if (pubMeth.isElementExsit(driver, locator)) {
			// if (re = false) {break;}

			// ѡ�񽨺õķ�����
			WebElement serverok = driver.findElement(locator);
			serverok.click();
			Thread.sleep(2000);
			
			// �������
			WebElement more = driver.findElement(By.xpath("//span[@data-testid = 'moreOpts']"));
			more.click();
			Thread.sleep(2000);
			
			// �������ձ���
			WebElement createbk = driver.findElement(By.xpath("//span[@data-testid = 'moreOpts-createSnapshot']"));
			createbk.click();
			Thread.sleep(2000);
			System.out.println("�������ձ���");

			// ������ձ�������
			WebElement servername = driver.findElement(By.xpath("//input[@data-testid = 'createSnapShot-name']"));
			servername.sendKeys("serverbk");
			Thread.sleep(3000);
			

			// �ύ
			WebElement submit = driver.findElement(By.xpath("//span[@data-testid = 'pop-model-confirm']"));
			submit.click();
			Thread.sleep(10000);
			System.out.println("�ύ");

			// �����ձ���
			WebElement bk = driver.findElement(By.xpath("//a[@data-testid = 'sidebarNav-cloud-snapshot']"));
			bk.click();
			Thread.sleep(3000);
			System.out.println("�����ձ���");

			// �жϿ��ձ�����û�н�������ȡ�÷���ֵrebk

			By locatorbk = By.xpath("//tbody[@data-testid = 'table-row-0']");
			boolean reValue1 = Base.isElementExsit(driver, locatorbk);
			System.out.println("reValue1 =" + reValue1);

			// ѡ��������ձ���
			WebElement bkid = driver.findElement(By.xpath("//span[@data-testid = 'table-row-0-id']"));
			bkid.click();
			Thread.sleep(5000);

			// ȡҳ����ʾ�ļ۸�
			WebElement pricelast = driver.findElement(By.xpath(
					"/html/body/div/div/div[1]/div/div[1]/div[2]/div[3]/div[2]/div[1]/div/div[1]/div[2]/dl[1]/twoGiOrFourGi[3]"));
			priceValueSnapshot = pricelast.getText();
			priceValueQu = priceValueSnapshot.substring(0, priceValueSnapshot.length() - 4);
			Thread.sleep(3000);
			System.out.println("ҳ��ȡ�õļ۸�Ϊ = " + priceValueQu);
			pubMeth.rwFile("ҳ��۸� = ", priceValueQu, "");

			// ѡ��������
			WebElement atwoGiOrFourGibk = driver.findElement(By.xpath("//span[@data-testid = 'createSnapshot-btn']"));
			atwoGiOrFourGibk.click();
			Thread.sleep(3000);

			// ��������������
			WebElement bkname = driver.findElement(By.xpath("//input[@data-testid = 'createSnapShot-name']"));
			bkname.sendKeys("serverbkatwoGiOrFourGi");
			Thread.sleep(3000);
			
			// �ύ
			WebElement submitbk = driver.findElement(By.xpath("//span[@data-testid = 'pop-model-confirm']"));
			submitbk.click();
			Thread.sleep(10000);
			System.out.println("�ύ");

			calculateSum();

			// �������н��д��LOG�ļ�
			String cases = "SnapshotInstance";
			
			//ȡ�õ�ǰ����
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
			
			//����Ѿ����������Һ˶Եļ۸���ȷ��Pass�Ľ��д���ļ�
			if (reValue1 && valueOk){
				
				pubMeth.rwFile(cases,df.format(new Date()),"Pass");
			
			} else { 
				pubMeth.rwFile(cases,df.format(new Date()),"Fail");
			}

		} else {
			System.out.println("û�з�����");
		}

		driver.close();
	}

	/**
	 * �����Ʒ�����
	 * 
	 * @author yangw
	 * @version 1.00
	 */
	private void cloud2Instance() throws InterruptedException {

		// ����Ʒ�����
		WebElement cloudserver = driver.findElement(By.xpath("//a[@data-testid = 'sidebarNav-cloud-instance']"));
		cloudserver.click();
		Thread.sleep(3000);
		

		// ����һ��������̫һ��

		Random RandomseleArea = new Random();
		seleArea = RandomseleArea.nextInt(1);// ֻ��һ�������0
		if (seleArea == 0) {
			WebElement yatai = driver.findElement(By.xpath("//span[@data-testid = 'region-select-ac1']"));
			yatai.click();
			Thread.sleep(3000);
			System.out.println("����һ��");
		} else {
			WebElement huadong = driver.findElement(By.xpath("//span[@data-testid = 'region-select-ac2']"));
			huadong.click();
			Thread.sleep(3000);
			System.out.println("��̫һ��");
		}
	

		// �½�
		WebElement creat = driver.findElement(By.xpath("//span[@class = 'color-primary']"));
		creat.click();
		Thread.sleep(3000);
		

		// ����
		// �ȶ�λdiv ul
		WebElement anxu = driver.findElement(By.xpath("//div[@class = 'mt-20 tabs-item']"));
		WebElement anxu1 = anxu.findElement(By.xpath("//li[@class = 'main-item']"));
		anxu1.click();
		Thread.sleep(3000);
		
		pubMeth.rwFile("+++++++++++++++++", "+++++++++++++++++", "+++++++++++++++++");

		// ϵͳ
		// �ȵõ�һ��classд��list, get(0)��Ҫ�ڼ���, ʹ��value�ķ���click
		java.util.List<WebElement> element = driver
				.findElements(By.xpath("//span[@class = 'tab-switch tab-switch-111 w90']"));

		WebElement value = element.get(0);
		value.click();
		Thread.sleep(3000);
		System.out.println(value.getText());

		// ѡȡ�����б��еĲ���ϵͳ

		Random randomosWindows = new Random();
		osWindows = randomosWindows.nextInt(2);
		System.out.println("osWindows = " + osWindows);
		if (osWindows == 1) {

			// ��ϵͳ�����б�
			WebElement oneKernela = driver.findElement(By.xpath("//i[@class = 'triangle-down abs-right8 abs-mitwoGiOrFourGile']"));
			oneKernela.click();
			Thread.sleep(3000);
			

			// ���������
			Random randomos = new Random();
			intRandomOs = randomos.nextInt(7);// Ϊ0-6����

			java.util.List<WebElement> os = driver.findElements(By.xpath("//li[@class = 'dropdown-item single-line']"));
			WebElement indexos = os.get(intRandomOs);
			indexos.click();
			Thread.sleep(3000);
			System.out.println("ѡ���" + intRandomOs + "������ϵͳ");

		} else {

			System.out.println("ubuntu����ϵͳ");
			pubMeth.rwFile("OS = ", "ubuntu����ϵͳ", "");

		}

		Random randomoneKernel = new Random();
		oneKernel = randomoneKernel.nextInt(2);
		System.out.println("oneKernel = " + oneKernel);
		// oneKernel = 0ʱΪ1�ˣ�oneKernel = 1ʱѡ����������intRandomCpu
		if (oneKernel == 1) {
			// ����4-6�����
			int max = 6;
			int min = 4;
			Random randomcpu = new Random();
			intRandomCpu = randomcpu.nextInt(max) % (max - min + 1) + min;
			System.out.println("CPU�����intRandomCpuΪ��" + intRandomCpu);
			// ѡȡ2.4.8���е�һ��
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
			// ѡȡ�ڴ�
			Random randomotherKernelOneGi = new Random();
			otherKernelOneGi = randomotherKernelOneGi.nextInt(2);
			System.out.println("otherKernelOneGi = " + otherKernelOneGi);
			if (otherKernelOneGi == 1) {
				// ����7-9�����
				int max1 = 9;
				int min1 = 7;
				Random randommem = new Random();
				intRandomMem = randommem.nextInt(max1) % (max1 - min1 + 1) + min1;
				System.out.println("�ڴ������intRandomMemΪ��" + intRandomMem);
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
				// pubMeth.rwFile("mem = ", "��һ��λ��G", "");
			}

		} else {
			// ��ѡ��Ϊ1�ˣ���ʱ�ڴ�Ϊ1��2��4G�������
			System.out.println("1��");
			// pubMeth.rwFile("CPU = ", "1��", "");

			Random randommemOneGi = new Random();
			memOneGi = randommemOneGi.nextInt(2);
			if (memOneGi == 1) {
				// ����7-8�����
				int max1 = 8;
				int min1 = 7;
				Random randommem1 = new Random();
				twoGiOrFourGi = randommem1.nextInt(max1) % (max1 - min1 + 1) + min1;
				System.out.println("�ڴ������Ϊ��" + twoGiOrFourGi);
				// ѡȡ2��4G�е�һ��
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
				pubMeth.rwFile("mem = ", "��һ��λ��G", "");
				// ��ѡ��Ϊ1G
			}
		}

		// �����������ѡ
		Random randomseleNet = new Random();
		seleNet = randomseleNet.nextInt(2);
		System.out.println("seleNet = " + seleNet);
		if (seleNet == 1) {
			// ѡ������������java.lang.IndexOutOfBoundsException
			System.out.println("����");
			// driver
			if (oneKernel == 1) {
				java.util.List<WebElement> netlist = driver
						.findElements(By.xpath("//span[@class = 'tab-switch tab-switch-111 w90']"));
				int num = netlist.size();
				System.out.println("����" + num);
				WebElement netvalue = netlist.get(10);
				netvalue.click();
				Thread.sleep(3000);
				System.out.println(netvalue.getText());
			} else {
				java.util.List<WebElement> netlist = driver
						.findElements(By.xpath("//span[@class = 'tab-switch tab-switch-111 w90']"));
				int num1 = netlist.size();
				System.out.println("����" + num1);
				WebElement netvalue = netlist.get(9);
				netvalue.click();
				Thread.sleep(3000);
				System.out.println(netvalue.getText());

			}

			pubMeth.rwFile("����Ϊ", "����", "");
			// �½�SDN����
			WebElement createsdn = driver.findElement(By.xpath(
					"/html/body/div/div/div[1]/div/div[1]/div[2]/div[3]/div[6]/div/div[3]/div[1]/div[3]/div[2]/div[2]/div[5]/span[2]"));
			createsdn.click();
			Thread.sleep(3000);
			WebElement sdnname = driver.findElement(By.xpath("//input[@class = 'input-text input-long']"));
			
			Thread.sleep(3000);
			WebElement sdnsub = driver.findElement(By.xpath("//span[@class = 'button button-primary']"));
			sdnsub.click();
			Thread.sleep(10000);
			System.out.println("����SDN����");

		} else {
			
			pubMeth.rwFile("����Ϊ", "����", "");
		}

		// �������������
		WebElement servername = driver.findElement(By.xpath("//input[@class = 'input-text input-long']"));
		servername.sendKeys("autoserver");
		Thread.sleep(3000);
		

		// ����pwd
		WebElement pwd = driver.findElement(By.xpath("//input[@class = 'input-text input-long mr10']"));
		pwd.sendKeys("Anchang123");
		Thread.sleep(3000);
		

		// �ٴ�����pwd
		java.util.List<WebElement> repwd = driver.findElements(By.xpath("//input[@class = 'input-text input-long']"));
		WebElement repwdvalue = repwd.get(2);
		repwdvalue.sendKeys("Anchang123");
		// int num = repwd.size();
		// System.out.println(num);
		Thread.sleep(3000);
		

		// �ύ
		WebElement submit = driver.findElement(By.xpath("//div[@class = 'btn-big']"));
		submit.click();
		Thread.sleep(30000);
		System.out.println("������ͨ");
	}

	/**
	 * ������ձ��ݼ۸�
	 * 
	 * @author yangw
	 * @version 1.00
	 * @throws Exception 
	 */
	private void calculateSum() throws Exception {

		double sum = 0;
		if (osWindows == 1) {// ��ѡ��������ϵͳʱ
			if (intRandomOs == 0) {// ��ѡ���0������ϵͳ����windowsʱ
				sum = 0.004 * 5;
			} else {
				sum = 0.004 * 2;
			}
		} else {// ����ѡʱΪubuntu
			sum = 0.004 * 2;
		}

		// ȡС�������λ
		//String sumTo = String.format("%.4f", sum);
		String sumTo = String.valueOf(sum);
		System.out.println("����ֵ=" + sumTo); 
		pubMeth.rwFile("����ֵ=", sumTo, ""); 
		//�����ֵ��ҳ��ȡֵ�Ƚ��Ƿ����
		valueOk = sumTo.equals(priceValueQu); 
		if (valueOk) {
			System.out.println("price sum is correctly"); 
			pubMeth.rwFile("��� = ", "price sum is correctly", ""); 
		} else {
			System.out.println("no correctly"); 
			pubMeth.rwFile("��� = ", "not correctly", ""); 
		}	

	}

}

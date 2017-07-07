
package common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.testng.annotations.Test;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Login {
	/**
	 * 打开浏览器输入用户名密码，用户名密码是xls配置文件中的
	 * 
	 * @author yangw
	 * @version 1.00
	 * @throws IOException 
	 * @throws BiffException 
	 */
	
	public String[] loginSeting() throws BiffException, IOException {

		jxl.Workbook readwb = null;
		InputStream instream = new FileInputStream("E:\\gitTest\\selenium_cfg.xls");
		readwb = Workbook.getWorkbook(instream);

		// 获取第一张Sheet表
		Sheet readsheet = readwb.getSheet(0);
		int rsRows = 5;
		int rsColumns = 7;
		String user = null;
		String pwd = null;
		String group = null;
		String path = null;
		String url = null;
		for (int i = 0; i < rsRows; i++) {
			for (int j = 6; j < rsColumns; j++) {
				Cell cell = readsheet.getCell(j, i);
				if (i == 0) {
					user = cell.getContents();
					System.out.println("user1=" + user);
				}

				if (i == 1) {
					pwd = cell.getContents();
					// System.out.println("pwd1=" + pwd);
				}
				if (i == 2) {
					group = cell.getContents();
					// System.out.println("group1=" + group);
				}
				if (i == 3) {
					path = cell.getContents();
					// System.out.println("path1=" + path);
				}
				if (i == 4) {
					url = cell.getContents();
					System.out.println("url1=" + url);
				}
				if ("".equals(cell.getContents()) == true) // 如果读取的数据为空
					break;
			}
			 System.out.println("第"+i+"行");

		}
		readwb.close();
		String[] str = { user, pwd, group, path, url };
		return str;

	}
}
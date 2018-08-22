package scripts;

import static org.testng.Assert.fail;

import java.io.File;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class AddProduct {
	private WebDriver driver;
	private String baseUrl;
	
	
	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"test\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "https://www.flipkart.com/";
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@DataProvider(name = "AnyID")
	public Object[][] createData1() {
		Object[][] retObjArr = getTableArray("test\\resources\\data\\LiveTextileSample.xls",
				"AddProductCart", "AddToCartDelimiter");//method is defined below
		return (retObjArr);
	}
	
	@Test
	public void AddToCartTest(String cat,String subcat,String method,String mid,String qty,String transport,String freight,String transport1,String name ,String mobile,String email,String msg){
		
		
		
		
		
	}
	
	@AfterTest
	public void turnDown() {
		driver.quit();

		java.util.Date date = new java.util.Date();
		System.out.println("\n\nExecution Log - End Time - "
				+ new Timestamp(date.getTime()));
	}
	
	public boolean verifyTextPresent(String value) {
		boolean x = driver.getPageSource().contains(value);
		return x;
	}
	
	public String[][] getTableArray(String xlFilePath, String sheetName,
			String tableName) {
		String[][] tabArray = null;
		try {
			Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
			Sheet sheet = workbook.getSheet(sheetName);

			int startRow, startCol, endRow, endCol, ci, cj;

			Cell tableStart = sheet.findCell(tableName);
			startRow = tableStart.getRow();
			startCol = tableStart.getColumn();

			Cell tableEnd = sheet.findCell(tableName, startCol + 1,
					startRow + 1, 100, 64000, false);

			endRow = tableEnd.getRow();
			endCol = tableEnd.getColumn();

			System.out.println("startRow=" + startRow + ", endRow=" + endRow
					+ ", " + "startCol=" + startCol + ", endCol=" + endCol);
			tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];
			ci = 0;

			for (int i = startRow + 1; i < endRow; i++, ci++) {
				cj = 0;
				for (int j = startCol + 1; j < endCol; j++, cj++) {
					tabArray[ci][cj] = sheet.getCell(j, i).getContents();
				}
			}
		} catch (Exception e) {
			System.out.println("error in getTableArray()");

		}

		return (tabArray);
	}
	
	
	
	/*public static void main(String[] args) {
		//id:-menuwrapper  Categories
			
	}*/

}

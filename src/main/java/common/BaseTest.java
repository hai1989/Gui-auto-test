package common;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.*;
import util.Tool;

import java.io.File;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhaowei
 * @Date: 2019/4/10 1:03 PM
 * @Version 1.0
 */
@Slf4j
public class BaseTest {
    private String relativelyPath = System.getProperty("user.dir");
    private String testDataPath = relativelyPath + "/test_data/";

    protected BaseDriver driver;

    @BeforeClass(groups = "smoke")
    @Parameters("browser")
    public void aBeforeClass(String browser){
        driver = new BaseDriver(browser);
        driver.getDriver().manage().window().maximize();
        driver.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.getDriver().manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
        driver.getDriver().manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
    }

    public BaseDriver getDriver() {
        return driver;
    }

    public BaseDriver initDriver(String browser){
        return new BaseDriver(browser);
    }

    @AfterSuite(groups = {"smoke", "function","check"})
    public void allAfterSuite() throws Exception {
        String filename = Tool.strTime("_yyyy-MM-dd_HH_mm_ss", 0);
        File src_all = new File("logs/all.html");
        File desc_all = new File("logs/all" + filename + ".html");
        System.out.println(src_all.getAbsolutePath()+"\t"+desc_all.getAbsolutePath());
        System.out.println("\t"+desc_all.getAbsolutePath());
//        boolean b = src_all.renameTo(desc_all);
        FileUtils.copyFile(src_all,desc_all);

        System.out.println(src_all.getName());
        File src_error = new File("logs/error.html");
        File desc_error = new File("logs/error" + filename + ".html");
//        src_error.renameTo(desc_error);
         FileUtils.copyFile(src_error,desc_error);
    }

    @DataProvider
    public Object[][] testData(Method method) {
        Object[][] data = null;
        Workbook book = null;
        Sheet sheet = null;
        int rowNum = 0;
        int columnNum = 0;
        String clsname = this.getClass().getName();  //调用者的包名+类名：例如：chandao.test.LoginTest  （chandao.test.LoginTest的testLogin）
        log.info("test-data-path:"+testDataPath);
        log.info("excel-className:"+clsname);
        String[] arrName = clsname.split("\\.");
        String project = arrName[arrName.length - 3];//chandao.test.LoginTest的chadao
        String model = arrName[arrName.length - 2];
        String interfaceName = arrName[arrName.length - 1];//chandao.test.LoginTest的LoginTest
        String methodname = method.getName();
        //testData(Method method)  含参数的DataProvider类_参数为调用dataprovider的方法名;例如：chandao.test.LoginTest中的testLogin
        try {
            String casename = this.testDataPath + "/" + project + "/" + model + "/" + interfaceName + ".xls";
            book = Workbook.getWorkbook(new File(casename));
            sheet = book.getSheet(methodname);
            rowNum = sheet.getRows();
            columnNum = sheet.getColumns();
            data = new Object[rowNum - 1][];//rowNum - 1指的是不算excel的表头行
            int paramSize = method.getParameterTypes().length;
            log.debug("example 参数=" + paramSize + " excel参数" + sheet.getRow(0).length);
            if (sheet.getRow(0).length != paramSize) {
                log.error("数据驱动文件与测试脚本参数个数不一致,驱动文件参数个数:" + sheet.getRow(0).length
                        + ";测试脚本参数个数:" + paramSize);
                return null;
            }
            Class[] params = method.getParameterTypes();
            for (int i = 1; i < rowNum; i++) {//不算表头字段，故1开始
                Cell[] rowsCell = sheet.getRow(i);
                Object[] rows = new Object[columnNum];
                for (int j = 0; j < columnNum; j++) {
                    rows[j] = Tool.converter(params[j], rowsCell[j].getContents());
                }
                data[i - 1] = rows;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}

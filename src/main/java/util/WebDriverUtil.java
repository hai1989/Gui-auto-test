package util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: zhaowei
 * @Date: 2019/3/22 7:15 PM
 * @Version 1.0
 */
@Slf4j
public class WebDriverUtil {
    public static final String SCREEN_PATH;  
    static{
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
        String timeStr = format.format(new Date());
        SCREEN_PATH = "screen/test_"+timeStr;
    }

    /**
     * 传入参数截图
     * @param driver
     * @param fileName
     */
    public static void takeScreenShot(TakesScreenshot driver,String fileName) {
        takeScreenShot(driver,SCREEN_PATH,fileName);
    }
    /**
     * 传入参数截图
     * @param driver
     * @param filePath
     * @param fileName
     */
    public static void takeScreenShot(TakesScreenshot driver, String filePath,String fileName) {
        String currentPath = System.getProperty("user.dir");
        File scrFile = driver.getScreenshotAs(OutputType.FILE);
        File desFile = new File(currentPath + "/" + filePath+"/"+fileName);
        try {
            FileUtils.copyFile(scrFile, desFile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info("截图成功:"+desFile.getAbsolutePath());
        }
    }

}

package common;

import exception.WebUIAutoTestException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import util.Tool;

/**
 * @Author: zhaowei
 * @Date: 2019/4/2 1:31 PM
 * @Version 1.0
 */
public class SelectDriver {
    public static final String FIREFOX = "firefox";
    public static final String IE = "ie";
    public static final String CHROME = "chrome";
    public static WebDriver getDriver(String browser){
        WebDriver driver = null;
        if(FIREFOX.equalsIgnoreCase(browser)){
            if(Tool.isMac()) {
                System.setProperty("webdriver.gecko.driver", "src/main/resources/driver/geckodriver");
            }else{
                System.setProperty("webdriver.gecko.driver", "src/main/resources/driver/geckodriver.exe");
            }
            driver = new FirefoxDriver();
        }else if(CHROME.equalsIgnoreCase(browser)){
            if(Tool.isMac()) {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver");
            }else {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
            }
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            driver = new ChromeDriver(options);
        }else if(IE.equalsIgnoreCase(browser)){
            if(Tool.isMac()){
                throw new WebUIAutoTestException("IE浏览器只支持windows系统");
            }else {
                System.setProperty("webdriver.ie.driver", "src/main/resources/driver/IEDriverServer.exe");
            }
            driver = new InternetExplorerDriver();
        }
        return driver;
    }
}

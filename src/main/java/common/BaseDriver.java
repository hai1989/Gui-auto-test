package common;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import util.WebDriverUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author: zhaowei
 * @Date: 2019/4/2 1:46 PM
 * @Version 1.0
 */
@Slf4j
public class BaseDriver{
    private WebDriver driver;
    public BaseDriver(String browser){
        this.driver = SelectDriver.getDriver(browser);
    }

    public WebDriver getDriver() {
        return driver;
    }

    /**
     * 关闭WebDriver
     */
    public void stop() {
        if(driver!=null){
            log.info("stop webdriver");
            driver.close();
        }
    }
    public WebElement findElement(By by){
        return driver.findElement(by);
    }
    public List<WebElement> findElements(By by){
        return driver.findElements(by);
    }

    /**
     * 自动截图
     * */
    public void takeScreenShot() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH_mm_ss");
        String dateStr = format.format(new Date());
        String className = Thread.currentThread().getStackTrace()[2].getClassName();//调用者的类名和方法名赋值给变量
        log.info("getStackTrace:"+Thread.currentThread().getStackTrace());
        log.info("getStackTrace()[1]:"+Thread.currentThread().getStackTrace()[1]);
        log.info("getStackTrace()[2]:"+Thread.currentThread().getStackTrace()[2]);
        log.info("getStackTrace()[2].getClassName();:"+Thread.currentThread().getStackTrace()[2].getClassName());
        String fileName = className + "_" + dateStr + ".png";
        WebDriverUtil.takeScreenShot((TakesScreenshot) this.getDriver(),fileName);
    }

    public void get(String url){
        driver.get(url);
    }
    public void back(){
        driver.navigate().back();
    }
    /**
     * 获取当前url
     * */
    public String getUrl(){
        return driver.getCurrentUrl();
    }
    /**
     * 获取title
     * */
    public String getTitle(){
        return driver.getTitle();
    }
    /**
     * 获取当前系统窗口list
     * */
    public List<String> getWindowsHandles(){
        Set<String> winHandels = driver.getWindowHandles();
        List<String> handles = new ArrayList<String>(winHandels);
        return handles;
    }

    /*
     * 获取当前窗口
     * **/
    public String getWindowHandle(){
        return driver.getWindowHandle();
    }

    /**
     * 切换windows窗口
     * */
    public void switchWindows(String name){
        driver.switchTo().window(name);
    }
    /**
     * 切换alert窗口
     */
    public void switchAlert(){
        driver.switchTo().alert();
    }

    /**
     * 模态框切换
     */
    public void switchMode(){
        driver.switchTo().activeElement();
    }
    /**
     * actionMoveElement
     * */
    public void moveToElement(WebElement element){
        Actions action =new Actions(driver);
        action.moveToElement(element).perform();
    }

    /**
     * 获取cookcie
     * @return
     * */
    public Set<Cookie> getCookie(){
        Set<Cookie> cookies = driver.manage().getCookies();
        return cookies;
    }

    /**
     * 删除cookie
     * */
    public void deleteCookie(){
        driver.manage().deleteAllCookies();
    }
    /**
     * 设置cookie
     * */
    public void setCookie(Cookie cookie){
        driver.manage().addCookie(cookie);
    }
}

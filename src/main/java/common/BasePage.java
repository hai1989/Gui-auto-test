package common;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebElement;
import util.ByLocation;
import util.PropertiesUtil;

import java.util.List;

/**
 * @Author: zhaowei
 * @Date: 2019/4/2 1:50 PM
 * @Version 1.0
 */
@Slf4j
public class BasePage {
    private BaseDriver driver;
    protected PropertiesUtil prop;
    public BasePage(BaseDriver driver){
        this.driver = driver;
    }

    /**
     * 定位元素
     * @param by
     * @return WebElement
     */
    public WebElement element(By by){
        return driver.findElement(by);
    }
    public WebElement element(String key){
        return element(ByLocation.by(prop,key));
    }

    /**
     * 根据父元素获取子元素
     * @param element
     * @param by
     * @return
     */
    public WebElement element(WebElement element,By by){
        return element.findElement(by);
    }
    public WebElement element(WebElement element,String key){
        return element(element,ByLocation.by(prop,key));
    }

    /**
     * 根据父元素的定位获取子元素
     * @param parent
     * @param by
     * @return
     */
    public WebElement element(By parent,By by){
        return element(element(by),by);
    }
    public WebElement element(String parent,String key){
        return element(ByLocation.by(prop,parent),ByLocation.by(prop,key));
    }
    /**
     * 定位一组elements
     * */
    public List<WebElement> elements(By by){
        return driver.findElements(by);
    }
    public List<WebElement> elements(String key){
        return elements(ByLocation.by(prop,key));
    }
    /**
     * 通过父节点定位一组elements
     * */
    public List<WebElement> elements(WebElement element,By by){
        return element.findElements(by);
    }
    public List<WebElement> elements(WebElement element,String key){
        return elements(element,ByLocation.by(prop,key));
    }
    /**
     * 封装点击
     * @param element
     */
    public void click(WebElement element){
        if(element!=null){
            element.click();
        }else{
            log.info("{}没有定位到，点击失败",element);
        }
    }

    /**
     * 封装输入
     * @param element
     */
    public void sendKeys(WebElement element,String value){
        if(element!=null){
            element.clear();
            element.sendKeys(value);
        }else{
            log.info("{}没有定位到，输入失败:{}",element,value);
        }
    }

    /**
     * 判断元素是否存在
     * @param element
     * @return
     */
    public boolean assertElementIsExist(WebElement element){
        if(element!=null){
            return element.isDisplayed();
        }else{
            return false;
        }
    }

    /**
     * 获取元素的文本
     * @param element
     * @return
     */
    public String getText(WebElement element){
        return element.getText();
    }
    /**
     * 根据title切换新窗口
     * */
    public boolean switchToWindowByTitle(String windowTitle) {
        boolean flag = false;
        try {
            String currentHandle = driver.getWindowHandle();
            List<String> handles = driver.getWindowsHandles();
            for (String s : handles) {
                if (s.equals(currentHandle))
                    continue;
                else {
                    driver.switchWindows(s);
                    if (driver.getTitle().contains(windowTitle)) {
                        flag = true;
                        log.info("切换windows成功: " + windowTitle);
                        break;
                    }
                }
            }
        } catch (NoSuchWindowException e) {
            System.out.println("Window: " + windowTitle + " 没找到!!!"
                    + e.fillInStackTrace());
            flag = false;
        }
        return flag;
    }

}

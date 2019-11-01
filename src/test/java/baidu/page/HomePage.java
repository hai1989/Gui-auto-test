package baidu.page;

import common.BaseDriver;
import common.BasePage;
import org.openqa.selenium.WebElement;
import util.PropertiesUtil;

/**
 * @Author: zhaowei
 * @Date: 2019/4/17 3:15 PM
 * @Version 1.0
 */
public class HomePage extends BasePage {

    public HomePage(BaseDriver driver) {
        super(driver);
        prop = new PropertiesUtil();
    }
    public WebElement getLoginElement(){
        return element("login");
    }

}



package baidu.page;

import common.BaseDriver;
import common.BasePage;
import org.openqa.selenium.WebElement;
import util.PropertiesUtil;

/**
 * @Author: zhaowei
 * @Date: 2019/4/17 3:12 PM
 * @Version 1.0
 */
public class LoginPage extends BasePage {
    public LoginPage(BaseDriver driver){
        super(driver);
        prop = new PropertiesUtil();
    }
    public WebElement getUserName(){
        return element("username");
    }
    public WebElement getPassword(){
        return element("password");
    }
    public WebElement getLoginButton(){
        return element("login");
    }
    public WebElement getUserNameLogin(){
        return element("userNameLogin");
    }
    public WebElement getSkip(){
        return element("skip");
    }
}

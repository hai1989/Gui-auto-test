package baidu.handle;

import common.BaseDriver;
import baidu.page.LoginPage;

/**
 * @Author: zhaowei
 * @Date: 2019/4/17 5:11 PM
 * @Version 1.0
 */
public class LoginHandle {
    private LoginPage loginPage;
    public LoginHandle(BaseDriver driver){
        loginPage = new LoginPage(driver);
    }

    public void sendUserName(String userName){
        loginPage.sendKeys(loginPage.getUserName(),userName);
    }
    public void sendPassword(String password){
        loginPage.sendKeys(loginPage.getPassword(),password);
    }
    public void clickLogin(){
        loginPage.click(loginPage.getLoginButton());
    }
    public void clickUserNameLogin(){
        loginPage.click(loginPage.getUserNameLogin());
    }
    public void clickSkip(){
        loginPage.click(loginPage.getSkip());
    }
}

package chandao.handle;

import chandao.page.LoginPage;
import common.BaseDriver;

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
        loginPage.click(loginPage.getLogin());
    }
}

package chandao.service;

import chandao.handle.HomeHandle;
import chandao.handle.LoginHandle;
import common.BaseDriver;
import org.testng.Assert;

public class LoginService {
    private LoginHandle loginHandle;
    private HomeHandle homeHandle;
    public LoginService(BaseDriver driver){
        loginHandle = new LoginHandle(driver);
        homeHandle = new HomeHandle(driver);
    }
    public void login(String userName,String password){
        loginHandle.sendUserName(userName);
        loginHandle.sendPassword(password);
        loginHandle.clickLogin();
        String name = homeHandle.getUserName();
        Assert.assertEquals(name,"Demo");
    }
}

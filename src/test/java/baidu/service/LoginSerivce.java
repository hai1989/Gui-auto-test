package baidu.service;

import common.BaseDriver;
import baidu.handle.HomeHandle;
import baidu.handle.LoginHandle;
import util.Tool;

/**
 * @Author: zhaowei
 * @Date: 2019/4/17 5:22 PM
 * @Version 1.0
 */
public class LoginSerivce {
    private LoginHandle loginHandle;
    private HomeHandle homeHandle;
    public LoginSerivce(BaseDriver driver){
        loginHandle = new LoginHandle(driver);
        homeHandle = new HomeHandle(driver);
    }
    public void login(String userName,String password){
        homeHandle.clickLogin();
        loginHandle.clickUserNameLogin();
        loginHandle.sendUserName(userName);
        loginHandle.sendPassword(password);
        loginHandle.clickLogin();
        Tool.sleep(3000);
        loginHandle.clickLogin();
        loginHandle.clickSkip();
    }
}

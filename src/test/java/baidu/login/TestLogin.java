package baidu.login;

import common.BaseTest;
import baidu.constant.Account;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import baidu.service.LoginSerivce;
import util.CookieUtil;

/**
 * @Author: zhaowei
 * @Date: 2019/4/17 5:28 PM
 * @Version 1.0
 */
public class TestLogin extends BaseTest {
    private LoginSerivce loginSerivce;

    @BeforeClass
    public void init(){
        loginSerivce = new LoginSerivce(driver);
        driver.get("https://www.baidu.com/");
    }
    @Test
    public void testLogin(){
        loginSerivce.login(Account.USER_NAME, Account.PASSWORD);
        CookieUtil.getCookie(driver,"BDUSS");
    }
    @Test
    public void testT(){
        CookieUtil.setCookie(driver,"BDUSS");
        driver.get("https://www.baidu.com/");
    }
}

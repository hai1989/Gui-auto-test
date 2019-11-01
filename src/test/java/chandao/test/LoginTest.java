package chandao.test;

import chandao.service.HomeService;
import chandao.service.LoginService;
import common.BaseTest;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.*;

public class LoginTest extends BaseTest {
    private LoginService loginService;
    private HomeService homeService;
    @BeforeClass(groups = "smoke")
    public void init(){
        loginService = new LoginService(driver);
        homeService = new HomeService(driver);
    }
    @AfterClass(groups = "smoke")
    public void stop(){
        driver.stop();
    }
    @Test(dataProvider = "testData",groups = {"smoke"},description = "禅道登录测试")
    public void testLogin(String id,String desc,String userName,String password){
        driver.get("http://demo.zentao.net/user-login.html");
        loginService.login(userName,password);
        homeService.quit();
    }
}

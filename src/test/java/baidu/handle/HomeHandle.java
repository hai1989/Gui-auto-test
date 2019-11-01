package baidu.handle;

import common.BaseDriver;
import baidu.page.HomePage;

/**
 * @Author: zhaowei
 * @Date: 2019/4/17 3:23 PM
 * @Version 1.0
 */
public class HomeHandle {
    private HomePage homePage;
    public HomeHandle(BaseDriver driver){
        homePage = new HomePage(driver);
    }
    public void clickLogin(){
        homePage.click(homePage.getLoginElement());
    }


}

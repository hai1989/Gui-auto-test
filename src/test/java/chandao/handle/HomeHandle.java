package chandao.handle;

import chandao.page.HomePage;
import chandao.page.LoginPage;
import common.BaseDriver;

public class HomeHandle {
    private HomePage homePage;
    public HomeHandle(BaseDriver driver){
        homePage = new HomePage(driver);
    }
    public String getUserName(){
        return homePage.getText(homePage.getUserName());
    }
    public void clickUserName(){
        homePage.click(homePage.getUserName());
    }
    public void clickQuit(){
        homePage.click(homePage.getQuit());
    }
}

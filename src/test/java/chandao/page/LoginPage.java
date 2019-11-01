package chandao.page;
import common.BaseDriver;
import common.BasePage;
import org.openqa.selenium.WebElement;
import util.PropertiesUtil;
public class LoginPage extends BasePage {
    public LoginPage(BaseDriver driver){
        super(driver);
        prop = new PropertiesUtil();//LoginPage.properties
    }

    public WebElement getUserName(){
        return element("username");
    }
    public WebElement getPassword(){
        return element("password");
    }
    public WebElement getLogin(){
        return element("login");
    }
}

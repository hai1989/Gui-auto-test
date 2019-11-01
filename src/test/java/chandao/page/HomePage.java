package chandao.page;
import common.BaseDriver;
import common.BasePage;
import org.openqa.selenium.WebElement;
import util.PropertiesUtil;

public class HomePage extends BasePage {
    public HomePage(BaseDriver driver){
        super(driver);
        prop = new PropertiesUtil();
    }

    public WebElement getUserName(){
        return element("username");
    }
    public WebElement getQuit(){
        return element("quit");
    }
}

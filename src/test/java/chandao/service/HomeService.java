package chandao.service;

import chandao.handle.HomeHandle;
import common.BaseDriver;

public class HomeService {
    private HomeHandle homeHandle;
    public HomeService(BaseDriver driver){
        homeHandle = new HomeHandle(driver);
    }
    public void quit(){
        homeHandle.clickUserName();
        homeHandle.clickQuit();
    }
}

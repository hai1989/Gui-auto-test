package util;

import common.BaseDriver;
import org.openqa.selenium.Cookie;

import java.io.File;
import java.util.Set;

/**
 * @Author: zhaowei
 * @Date: 2019/4/8 4:07 PM
 * @Version 1.0
 */
public class CookieUtil {
    private static PropertiesUtil pro;
  //  private static String domain;
    static{
        File file = new File("cookie.properties");
        pro = new PropertiesUtil(file);
     //   domain = pro.getProperty("domain");
    }
    public static void setCookie(BaseDriver driver, String key){
        String value = pro.getProperty(key);
        Cookie cookie = new Cookie(key,value,"/",null);
        driver.setCookie(cookie);
    }
    public static void setCookie(BaseDriver driver, String domain, String key){
        String value = pro.getProperty(key);
        Cookie cookie = new Cookie(key,value,domain,"/",null);
        driver.setCookie(cookie);
    }
    /**
     * 获取cookie
     * */
    public static void getCookie(BaseDriver driver, String key){
        Set<Cookie> cookies = driver.getCookie();
        for(Cookie cookie:cookies){
            if(cookie.getName().equals(key)){
                pro.writeProperty(cookie.getName(), cookie.getValue());
            }
        }
    }
}

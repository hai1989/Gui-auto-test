package util;

import org.openqa.selenium.By;

/**
 * @Author: zhaowei
 * @Date: 2019/4/1 1:17 PM
 * @Version 1.0
 */
public class ByLocation {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String CLASS_NAME = "className";
    public static final String LINK_TEXT = "linkText";
    public static final String PARTIAL_LINK_TEXT = "partialLinkText";
    public static final String TAG_NAME = "tagName";
    public static final String XPATH = "xpath";
    public static final String CSS_SELECTOR = "cssSelector";
    public static final String SEPARATE = ">";
    public static By by(String type, String location){
        if(ID.equals(type)){
            return By.id(location);
        }else if(NAME.equals(type)){
            return By.name(location);
        }else if(XPATH.equals(type)){
            return By.xpath(location);
        }else if(TAG_NAME.equals(type)){
            return By.tagName(location);
        }else if(CLASS_NAME.equals(type)){
            return By.className(location);
        }else if(LINK_TEXT.equals(type)){
            return By.linkText(location);
        }else if(PARTIAL_LINK_TEXT.equals(type)){
            return By.partialLinkText(location);
        }else if(CSS_SELECTOR.equals(type)){
            return By.cssSelector(location);
        }else{
            return By.xpath(location);
        }
    }
    public static By by(PropertiesUtil prop,String key){
        String type,location;
        String locator = prop.getProperty(key);
        if(locator.contains(SEPARATE)){
            String[] ss = locator.split(SEPARATE);
            type = ss[0];
            location = ss[1];
        }else{
            type = XPATH;
            location = locator;
        }
        return by(type,location);
    }
    public static By by(String location){
        return By.xpath(location);
    }
}

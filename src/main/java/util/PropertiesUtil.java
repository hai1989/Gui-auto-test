package util;

import java.io.*;
import java.util.Arrays;
import java.util.Properties;

/**
 * @Author: zhaowei
 * @Date: 2019/4/1 1:33 PM
 * @Version 1.0
 */
public class PropertiesUtil {
    private Properties properties;
    private File file;
    public static final String ELEMENT = "element/";
    public PropertiesUtil(String fileName){
        properties = new Properties();
        try {
            String className = Thread.currentThread().getStackTrace()[2].getClassName();
            Class cls = Class.forName(className);
            properties.load(cls.getClassLoader().getResourceAsStream(ELEMENT+fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public PropertiesUtil(){
        try {
            String className = Thread.currentThread().getStackTrace()[2].getClassName();
            System.out.println(Arrays.toString(Thread.currentThread().getStackTrace()));
            System.out.println(className);
            String fileName = className.substring(className.lastIndexOf(".")+1)+".properties";
            Class cls = Class.forName(className);
            properties = new Properties();
            properties.load(cls.getResourceAsStream(fileName));//与加载的类模块、包一致
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public PropertiesUtil(File file) {
        this.file = file;
        properties = new Properties();
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            properties.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getProperty(String key){
        return properties.getProperty(key);
    }

    /**
     * 写入内容
     * */
    public void writeProperty(String key,String value){
        Properties pro = new Properties();
        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            pro.setProperty(key, value);
            pro.store(outputStream, key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PropertiesUtil p =  new PropertiesUtil();
        System.out.println(p.toString());
    }
}

package util;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Encoder;

import java.net.MalformedURLException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Slf4j
public class Tool {

    public static void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean isMac(){
        String osName = System.getProperty("os.name").toLowerCase();
        log.info("操作系统为：{}",osName);
        return osName.contains("mac");
    }
    public static String strTime(String format,long time){
        SimpleDateFormat strFormat = new SimpleDateFormat(format);
        if(time == 0){
            time = new Date().getTime();
        }
        return strFormat.format(time);
    }
    /**
     * 根据 参数C，返回对应类型的 in对象，
     *
     * @param c
     * @param in
     * @return
     */
    public static Object  converter(Class c, String in) {
        if(in.equals("null")){
            return null;
        }
        if (c == String.class) {
            return in;
        }
        if (c == int.class) {
            int index = in.lastIndexOf('.');
            if (index != -1)
                return Integer.parseInt(in.substring(0, index));
            return Integer.parseInt(in);
        }
        if (c == long.class) {
            return Long.parseLong(in);
        }
        if (c == double.class) {
            return Double.parseDouble(in);
        }
        if (c == float.class) {
            return Float.parseFloat(in);
        }
        if (c == Boolean.class) {
            return Boolean.parseBoolean(in);
        }
        if (c == boolean.class) {
            return Boolean.parseBoolean(in);
        }
        if (c == int[].class) {
            if(in.equals("null")) {
                return null;
            } else {
                return StringArrayToIntArray(in.split(",", -1));
            }
        }

        if (c == String[].class) {
            if(in.equals("null")) {
                return null;
            } else {
                return in.split(",", -1);
            }
        }
        return null;
    }

    public static <T>String ArrToString(T[] arr){
        String str = "";
        for (int i=0;i<arr.length;i++) {
            str += arr[i]+",";
        }
        return str;
    }
    public  static int [] StringArrayToIntArray(String[] src){
        int len = src.length;
        System.out.print(src);

        int [] result = new int[len];
        for (int i=0;i<len;i++){

            result[i] = Integer.valueOf(src[i]);
        }
        System.out.print(result);
        return result;
    }
    public static String uuid(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

    public static void main(String []  args) throws MalformedURLException {
     //   System.out.println(isJSONValid("{\"x\":\"b\"}"));
        System.out.println(isMac());
    }
}













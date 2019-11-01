package util;



import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExtentReporterNG implements IReporter {
    private ExtentReports extent;
    String relativelyPath = System.getProperty("user.dir");
    String testDataPath = System.getProperty("user.dir") + "/reports";
 //   private static final Logger logger = LoggerFactory.getLogger(ExtentReporterNG.class);

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

        extent = new ExtentReports(testDataPath + File.separator + suites.get(0).getName() + Tool.strTime("_yyyy-MM-dd_HH_mm_ss", 0) + "_report.html", true);
//        suites.get(0).getName()---取得xml的suite名称
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
                buildTestNodes(context.getPassedTests(), LogStatus.PASS);
                buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
                buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
            }
        }
        extent.flush();
        extent.close();
    }

    private void buildTestNodes(IResultMap tests, LogStatus status)  {
        ExtentTest test;

        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                Object[] params = result.getParameters();
                ITestNGMethod method = result.getMethod();
                test = extent.startTest(getTestDescripe(result));
                test.assignCategory(result.getMethod().getTestClass().getName()); //CATEGORIES
                test.assignAuthor("zhaowei");
//                test.setDescription("GUI自动化测试");
                test.setStartedTime(getTime(result.getStartMillis()));//每个@Test开始时间
                test.setEndedTime(getTime(result.getEndMillis()));//每个@Test结束时间
                String clsname = result.getClass().getName();
                String paramStr = "参数列表:[";
                for (Object param : params) {
                    if (param instanceof String[]){
                        paramStr += Tool.ArrToString((String[])param);//Tool--工具类
                    }else if (param instanceof Integer []){
                        paramStr += Tool.ArrToString((Integer[])param);
                    }else {
                        paramStr += param + ",";
                    }
                }
                paramStr += "]";
                //优化：参数列表:[2,登录-错误,demo,123457,]--去掉最后的，如下：
                paramStr = paramStr.replace(",]","]");
                System.out.println("---------------"+paramStr);
                test.log(status, paramStr);//test.log为ExtentReports的详情内容输出
                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);

                if (result.getThrowable() != null) {  //失败的用例
                    test.log(status, result.getThrowable());
                    test.log(status,result.getTestClass().getRealClass().toString());
//                    test.log(status,result.getMethod().getFailedInvocationNumbers().toString());//失败调用的个数
                    test.log(status,"用例id(excel中)："+result.getParameters()[0].toString());
                    System.out.println("********************"+result.getMethod().getFailedInvocationNumbers().toString());
                    System.out.println("********************"+result.getParameters()[0].toString());
                } else {
                    if (status.toString().toLowerCase().equals("skip")){//跳过的用例
                        test.log(status,result.getMethod().getInvocationNumbers().toString());
//                        example.log(status,result.getMethod().get);
                    }
                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");//通过的，例如：Test passed
                }
                extent.endTest(test);
            }
        }
    }

    private String getTestDescripe(ITestResult result) {
        String desc = result.getMethod().getRealClass().getSimpleName()+"."+ result.getMethod().getMethodName()+ "(" + result.getMethod().getDescription();//LoginTest.testLogin(
       //result.getMethod().getRealClass().getSimpleName()---方法所在类的简单类名（不含包名）
        Object[] params = result.getParameters();
        if (params.length > 0) {
            desc += ","+params[1].toString() + ")"; //public void testLogin(String id,String desc,String userName,String password){ 而params[1].toString()就是String desc,也就是excel中的第二列的desc
            // LoginTest.testLogin(禅道登录测试,登录-正确)
        } else {
            desc += ")";
        }
        return desc;
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}
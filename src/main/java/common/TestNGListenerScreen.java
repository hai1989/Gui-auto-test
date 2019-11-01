package common;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.util.*;

/**
 * @Author: zhaowei
 * @Date: 2019/4/2 12:56 PM
 * @Version 1.0
 */
public class TestNGListenerScreen extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);
        BaseDriver driver = ((BaseTest)tr.getInstance()).getDriver();
        driver.takeScreenShot();
    }
}

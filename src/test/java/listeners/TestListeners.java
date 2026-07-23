package listeners;

import com.aventstack.extentreports.*;
import org.testng.*;
import reports.ExtentManager;

public class TestListeners
        implements ITestListener {

    private static final ExtentReports extent =
            ExtentManager.getInstance();

    private static final ThreadLocal<ExtentTest>
            test = new ThreadLocal<>();

    @Override
    public void onTestStart(
            ITestResult result) {

        ExtentTest extentTest =
                extent.createTest(
                        result.getName());

        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(
            ITestResult result) {

        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(
            ITestResult result) {

        test.get().fail(result.getThrowable());
    }

    @Override
    public void onFinish(
            ITestContext context) {

        extent.flush();
    }
}
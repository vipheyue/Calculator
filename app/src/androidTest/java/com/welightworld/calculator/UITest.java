package com.welightworld.calculator;

import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by heyue on 2017/12/19.
 */
@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class UITest {
    /**
     * 运行这个单元测试就行了
     */
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
 /*       Context appContext = InstrumentationRegistry.getTargetContext();

//        assertEquals("com.vipheyue.calculator", appContext.getPackageName());
        UiObject button1 = mDevice.findObject(new UiSelector()
                .text("1"));
        UiObject buttonPlus = mDevice.findObject(new UiSelector()
                .text("+"));
        UiObject buttonEqual = mDevice.findObject(new UiSelector()
                .text("="));
//        UiObject tv_equation_panel = mDevice.findObject(new UiSelector()
//                .description("tv_equation_panel"));
        UiObject tv_equation_panel = mDevice.findObject(new UiSelector().resourceId("com.vipheyue.calculator:id/tv_equation_panel"));
        button1.click();
        buttonPlus.click();
        button1.click();
        buttonEqual.click();

        tv_equation_panel.click();
        assertEquals("1+1=2", tv_equation_panel.getText());*/
    }

    private static final String BASIC_SAMPLE_PACKAGE
            = "com.vipheyue.calculator";
    private static final int LAUNCH_TIMEOUT = 5000;
    private static final String STRING_TO_BE_TYPED = "UiAutomator";
    private UiDevice mDevice;

   /* @Before
    public void startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Start from the home screen
        mDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = mDevice.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT);

        // Launch the app
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT);
    }*/
}

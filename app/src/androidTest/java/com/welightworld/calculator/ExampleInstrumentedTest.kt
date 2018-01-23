package com.welightworld.calculator

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.UiDevice
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.vipheyue.calculator", appContext.packageName)
    }

    private val BASIC_SAMPLE_PACKAGE = "com.vipheyue.calculator"
    private val LAUNCH_TIMEOUT = 5000
    private val STRING_TO_BE_TYPED = "UiAutomator"
    private var mDevice: UiDevice? = null

   /* @Before
    fun startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Start from the home screen
        mDevice!!.pressHome()

        // Wait for launcher
        val launcherPackage = mDevice!!.launcherPackageName
        assertThat(launcherPackage, notNullValue())
        mDevice!!.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT.toLong())

        // Launch the app
        val context = InstrumentationRegistry.getContext()
        val intent = context.packageManager
                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE)
        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)

        // Wait for the app to appear
        mDevice!!.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT.toLong())
    }*/
}

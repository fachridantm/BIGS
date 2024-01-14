package id.belitong.bigs.compose.benchmark

import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiScrollable
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class StartupBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    private val instrumentation = InstrumentationRegistry.getInstrumentation()
    private val targetContext = instrumentation.targetContext

    @Test
    fun startupCold() = benchmarkRule.measureRepeated(
        packageName = "id.belitong.bigs.compose",
        metrics = listOf(StartupTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.COLD,
    ) {
        pressHome()
        startActivityAndWait()

        // If the app start from Splash Screen
        if (device.hasObject(By.textContains("Explore Now"))) {
            // Click the "Explore Now" button
            val exploreButton = device.findObject(By.textContains("Explore Now"))
            exploreButton.click()
        }

        // Wait for the MainScreen to be displayed
        device.wait(Until.hasObject(By.desc("MainScreen")), 3_000)
            ?: throw AssertionError("MainScreen not displayed")

        // Handle the permission request in the MainScreen
        handleCommonPermissionRequest(device)

        // Continue with the rest of the test
        continueTest(device)

        pressHome()
    }

    @Test
    fun startupWarm() = benchmarkRule.measureRepeated(
        packageName = "id.belitong.bigs.compose",
        metrics = listOf(StartupTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.WARM,
    ) {
        pressHome()
        startActivityAndWait()

        // If the app start from Splash Screen
        if (device.hasObject(By.textContains("Explore Now"))) {
            // Click the "Explore Now" button
            val exploreButton = device.findObject(By.textContains("Explore Now"))
            exploreButton.click()
        }

        // Wait for the MainScreen to be displayed
        device.wait(Until.hasObject(By.desc("MainScreen")), 3_000)
            ?: throw AssertionError("MainScreen not displayed")

        // Handle the permission request in the MainScreen
        handleCommonPermissionRequest(device)

        // Continue with the rest of the test
        continueTest(device)

        pressHome()
    }

    @Test
    fun startupHot() = benchmarkRule.measureRepeated(
        packageName = "id.belitong.bigs.compose",
        metrics = listOf(StartupTimingMetric()),
        iterations = 5,
        startupMode = StartupMode.HOT,
    ) {
        pressHome()
        startActivityAndWait()

        // If the app start from Splash Screen
        if (device.hasObject(By.textContains("Explore Now"))) {
            // Click the "Explore Now" button
            val exploreButton = device.findObject(By.textContains("Explore Now"))
            exploreButton.click()
        }

        // Wait for the MainScreen to be displayed
        device.wait(Until.hasObject(By.desc("MainScreen")), 3_000)
            ?: throw AssertionError("MainScreen not displayed")

        // Handle the permission request in the MainScreen
        handleCommonPermissionRequest(device)

        // Continue with the rest of the test
        continueTest(device)

        pressHome()
    }

    private fun handleCommonPermissionRequest(device: UiDevice) {
        // Wait for the permission dialog to be displayed
        val wait = device.wait(Until.hasObject(By.textContains("Allow")), 3_000)
        if (wait != null) {
            if (device.hasObject(By.textContains("Allow"))) {
                // Grant the permission
                val allowButton = device.findObject(By.text("Allow"))
                allowButton.click()
            }
        } else {
            throw AssertionError("Permission dialog not displayed")
        }
    }

    private fun handleCameraAndMediaPermissionRequest(device: UiDevice) {
        // Wait for the permission dialog to be displayed
        val waitDialog1 =
            device.wait(Until.hasObject(By.textContains("While using the app")), 3_000)
        if (waitDialog1 != null) {
            if (device.hasObject(By.textContains("While using the app"))) {
                // Grant the permission
                val whileUsingButton = device.findObject(By.text("While using the app"))
                whileUsingButton.click()
            }
        } else {
            throw AssertionError("Permission dialog not displayed")
        }

        // Wait for the permission dialog to be displayed
        val waitDialog2 = device.wait(Until.hasObject(By.textContains("Allow")), 3_000)
        if (waitDialog2 != null) {
            if (device.hasObject(By.textContains("Allow"))) {
                // Grant the permission
                val allowButton = device.findObject(By.text("Allow"))
                allowButton.click()
            }
        } else {
            throw AssertionError("Permission dialog not displayed")
        }

    }

    private fun continueTest(device: UiDevice) {
        // Wait for the HomeScreen to be displayed
        device.wait(Until.hasObject(By.textContains("Home")), 3_000)
            ?: throw AssertionError("Home Screen not displayed")

        // Scroll the HomeScreen
        val homeScreen = UiScrollable(UiSelector().scrollable(true))
        homeScreen.scrollForward()

        // Switch to the AddScreen
        val addButton = device.findObject(By.textContains("Scan & Add"))
        addButton.click()

        // Handle the permission request in the AddScreen
        handleCameraAndMediaPermissionRequest(device)

        // Wait for the AddScreen to be displayed
        device.wait(Until.hasObject(By.textContains("Scan & Add")), 3_000)
            ?: throw AssertionError("Add Screen not displayed")

        // Switch to the HistoryScreen
        val historyButton = device.findObject(By.textContains("History"))
        historyButton.click()

        // Wait for the HistoryScreen to be displayed
        device.wait(Until.hasObject(By.textContains("History")), 3_000)
            ?: throw AssertionError("History Screen not displayed")

        // Scroll My Order
        val myOrder = UiScrollable(UiSelector().scrollable(true))
        device.performActionAndWait(
            { myOrder.scrollForward(5) },
            Until.scrollFinished(Direction.DOWN),
            5_000
        )

        // Switch My Report
        val myReportTab = device.findObject(By.textContains("My Report"))
        myReportTab.swipe(Direction.LEFT, 1.0f)

        // Wait for the My Report to be displayed
        val wait = device.wait(Until.hasObject(By.textContains("Place")), 3_000)
        if (wait != null) {
            // Scroll My Report
            val myReport = UiScrollable(UiSelector().scrollable(true))
            myReport.scrollForward()
        } else {
            throw AssertionError("My Report Screen not displayed")
        }

        // Go back to the HomeScreen
        device.pressBack()
    }
}
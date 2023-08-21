package com.webage.lab11testing

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.webage.lab11testing", appContext.packageName)
    }


    @Test
    fun additionTest() {
        composeTestRule.onNodeWithTag("text-a")
            .performTextInput("100")
        composeTestRule.onNodeWithTag("text-b")
            .performTextInput("10")
        composeTestRule.onNodeWithTag("button-add")
            .performClick()
        composeTestRule.onNodeWithTag("text-c")
            .assertTextEquals("110")
    }

    @Test
    fun addition2Test() {
        composeTestRule.onNodeWithTag("text-a")
            .performTextInput("-19")
        composeTestRule.onNodeWithTag("text-b")
            .performTextInput("10")
        composeTestRule.onNodeWithTag("button-add")
            .performClick()
        composeTestRule.onNodeWithTag("text-c")
            .assertTextEquals("-9")
    }


}
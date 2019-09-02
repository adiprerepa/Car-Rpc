package com.car_rpc.app.home

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.car_rpc.app.R
import kotlinx.android.synthetic.main.activity_main.view.*
import org.hamcrest.CoreMatchers.allOf

internal class HomeRobot(
    private val activityTestRule: ActivityTestRule<*>
) {

    fun launchMainScreen() {
        activityTestRule.launchActivity(Intent())
    }


    fun verifyHomeScreenIsDisplayed() {
        onView(allOf(withId(R.id.R_id_welcome))).check(matches(isDisplayed()))
    }
}
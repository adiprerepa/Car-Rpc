package com.car_rpc.app.home

import androidx.test.rule.ActivityTestRule
import com.car_rpc.app.MainActivity
import org.junit.Rule
import org.junit.Test

class HomeTest {

    @get:Rule
    val activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java, true, false)

    private val homeRobot = HomeRobot(activityTestRule)

    @Test fun launchHomeScreen() {
        with (homeRobot) {
            launchMainScreen()
            verifyHomeScreenIsDisplayed()
        }
    }
}
package com.example.espressorobot

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.espressorobot.robots.BaseTestRobot
import com.example.espressorobot.robots.loginAction
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginTest : BaseTestRobot() {

    @get:Rule
    val activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun loginMissingFields() {
        loginAction {
            onClickLoginButton()
            matchError("Please fill email and password fieds")
        }
    }

    @Test
    fun loginSuccess() {
        loginAction {
            inputEmail()
            inputPassword()
            onClickLoginButton()
        }
    }

}

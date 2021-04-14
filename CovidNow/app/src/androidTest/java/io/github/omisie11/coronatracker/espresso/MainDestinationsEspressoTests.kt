package io.github.omisie11.coronatracker.espresso

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.omisie11.coronatracker.R
import io.github.omisie11.coronatracker.ui.MainActivity
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainDestinationsEspressoTests {

    private lateinit var activityScenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch<MainActivity>(MainActivity::class.java)
    }

    // TODO 1: Check data from Global Screen
    @Test
    fun verifyDataInGlobalScreen() {
        Espresso.onView(
            ViewMatchers.withId(R.id.global_dest)
        ).perform(
            ViewActions.click()
        ).check(
            ViewAssertions.matches(
                isDisplayed()
            )
        )
    }

    // TODO 2: Check data from Local Screen
    @Test
    fun verifyDataInLocalScreen() {
        Espresso.onView(
            ViewMatchers.withId(R.id.local_dest)
        ).perform(
            ViewActions.click()
        ).check(
            ViewAssertions.matches(
                isDisplayed()
            )
        )

        Espresso.onView(
            ViewMatchers.withId(R.id.stat_recovered)
        ).check(
            ViewAssertions.matches(
                isDisplayed()
            )
        )

        Espresso.onView(
            ViewMatchers.withId(R.id.stat_confirmed)
        ).check(
            ViewAssertions.matches(
                isDisplayed()
            )
        )

        Espresso.onView(
            ViewMatchers.withId(R.id.stat_deaths)
        ).check(
            ViewAssertions.matches(
                isDisplayed()
            )
        )
    }

    @After
    fun teardown() {
        activityScenario.close()
    }
}

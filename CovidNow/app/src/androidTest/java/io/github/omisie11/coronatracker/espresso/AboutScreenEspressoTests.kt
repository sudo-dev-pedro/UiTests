package io.github.omisie11.coronatracker.espresso

import android.app.Instrumentation
import android.content.Intent
import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import io.github.omisie11.coronatracker.R
import io.github.omisie11.coronatracker.ui.MainActivity
import io.github.omisie11.coronatracker.ui.about.used_libraries.UsedLibrariesAdapter
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AboutScreenEspressoTests {

    private lateinit var activityScenario: ActivityScenario<MainActivity>
    private val resources: Resources by lazy {
        InstrumentationRegistry.getInstrumentation().targetContext.resources
    }

    @Before
    fun setUp() {
        Intents.init()
        activityScenario = ActivityScenario.launch<MainActivity>(MainActivity::class.java)
    }

    // TODO 1: Test Github Link
    @Test
    fun verifyIfTheUserIsRedirectedToGitHub() {

        // Resultado esperado
        val expected = CoreMatchers.allOf(
            IntentMatchers.hasAction(Intent.ACTION_VIEW),
            IntentMatchers.hasData(resources.getString(R.string.github_url_omisie11))
        )

        // Precisamos mocckar a resposta esperada para evitar que o navegador seja aberto
        // Esse é o dublê de uma Intent
        Intents.intending(expected)
            .respondWith(Instrumentation.ActivityResult(0, null))

        // Realizando o click no meu id do NavGraph
        Espresso.onView(
            ViewMatchers.withId(R.id.about_dest)
        ).perform(click())

        Espresso.onView(
            ViewMatchers.withId(R.id.chip_github)
        ).perform(click())

        Intents.intended(expected)

    }

    // TODO 2: Test Twitter Link
    @Test
    fun verifyIfTheUserIsRedirectedToTwitter() {

        // Resultado esperado
        val expected = CoreMatchers.allOf(
            IntentMatchers.hasAction(Intent.ACTION_VIEW),
            IntentMatchers.hasData(resources.getString(R.string.twitter_url_omisie11))
        )

        // Precisamos mocckar a resposta esperada para evitar que o navegador seja aberto
        Intents.intending(expected)
            .respondWith(Instrumentation.ActivityResult(0, null))

        // Realizando o click no meu id do NavGraph
        Espresso.onView(
            ViewMatchers.withId(R.id.about_dest)
        ).perform(
            click()
        )

        Espresso.onView(
            ViewMatchers.withId(R.id.chip_twitter)
        ).perform(
            click()
        )

        Intents.intended(expected)

    }

    // TODO 3: Test Website Link

    @Test
    fun verifyIfTheUserIsRedirectedToWebsite() {

        // Resultado esperado
        val expected = CoreMatchers.allOf(
            IntentMatchers.hasAction(Intent.ACTION_VIEW),
            IntentMatchers.hasData(resources.getString(R.string.website_url_omisie11))
        )

        // Precisamos mocckar a resposta esperada para evitar que o navegador seja aberto
        Intents.intending(expected)
            .respondWith(Instrumentation.ActivityResult(0, null))

        // Realizando o click no meu id do NavGraph
        Espresso.onView(
            ViewMatchers.withId(R.id.about_dest)
        ).perform(click())

        Espresso.onView(
            ViewMatchers.withId(R.id.chip_website_omisie11)
        ).perform(click())

        Intents.intended(expected)
    }

    // TODO 4: Test Awesome Libraries Link and Intent from an item
    @Test
    fun verifyIfTheAwesomeItemsOpenBrowserWhenClicked() {

        val expected = CoreMatchers.allOf(
            IntentMatchers.hasAction(Intent.ACTION_VIEW),
            IntentMatchers.hasData("https://android.googlesource.com/platform/frameworks/support/+/androidx-master-dev")
        )

        // Precisamos mocckar a resposta esperada para evitar que o navegador seja aberto
        Intents.intending(expected)
            .respondWith(Instrumentation.ActivityResult(0, null))

        // Realizando o click no meu id do NavGraph
        Espresso.onView(
            ViewMatchers.withId(R.id.about_dest)
        ).perform(click())

        Espresso.onView(
            ViewMatchers.withId(R.id.chip_used_libs)
        ).perform(click())

        Espresso.onView(
            ViewMatchers.withId(R.id.recyclerView_libs)
        ).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )

        Intents.intended(expected)
    }


    @After
    fun teardown() {
        Intents.release()
        activityScenario.close()
    }
}

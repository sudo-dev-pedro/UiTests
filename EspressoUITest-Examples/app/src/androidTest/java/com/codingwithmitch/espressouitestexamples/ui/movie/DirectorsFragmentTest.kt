package com.codingwithmitch.espressouitestexamples.ui.movie

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.codingwithmitch.espressouitestexamples.R
import com.codingwithmitch.espressouitestexamples.factory.MovieFragmentFactory
import org.junit.Test

import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DirectorsFragmentTest {

    private lateinit var fragmentScenario: FragmentScenario<DirectorsFragment>

    //    TODO 1: Verify list of directors
    @Test
    fun verifyDirectorsList() {

        val directorsList = arrayListOf(
            "Quentin Tarantino",
            "David Fincher",
            "Spike Lee",
            "Greta Gerwig",
            "Denis Villeneuve"
        )

        val directorsValue = DirectorsFragment.stringBuilderForDirectors(directors = directorsList)
        val fragmentFactory = MovieFragmentFactory(null, null)

        // Construindo um Bundle com o nome dos diretores.
        val bundle = Bundle().apply {
            putStringArrayList("args_directors", directorsList)
        }

        fragmentScenario = launchFragmentInContainer<DirectorsFragment>(
            fragmentArgs = bundle,
            factory = fragmentFactory
        )

        Espresso.onView(
            withId(R.id.directors_text)
        ).check(
            matches(
                withText(
                    directorsValue
                )
            )
        )
    }
}






















package com.codingwithmitch.espressouitestexamples.ui.movie

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.codingwithmitch.espressouitestexamples.R
import com.codingwithmitch.espressouitestexamples.factory.MovieFragmentFactory
import org.junit.Test

import org.junit.runner.RunWith
import java.lang.StringBuilder

@RunWith(AndroidJUnit4ClassRunner::class)
class StarActorsFragmentTest {

    private lateinit var fragmentScenario: FragmentScenario<StarActorsFragment>

    @Test
    fun verifyFragmentTitle() {
        val fragmentFactory = MovieFragmentFactory(null, null)

        fragmentScenario = launchFragmentInContainer<StarActorsFragment>(
            fragmentArgs = null,
            factory = fragmentFactory
        )

        Espresso.onView(
            withId(R.id.star_actors_title)
        ).check(
            matches(
                withText(
                    R.string.text_star_actors
                )
            )
        )
    }

    // TODO 1: Verify list of actors
    @Test
    fun verifyActorsList() {

        val actorsList = arrayListOf(
            "Robert Downey Jr.",
            "Chris Evans",
            "Scarlett Johansson",
            "Zoe Saldana"
        )

        val actorsValue = StarActorsFragment.stringBuilderForStarActors(actors = actorsList)
        val fragmentFactory = MovieFragmentFactory(null, null)

        // Construindo um Bundle com o nome dos atores.
        val bundle = Bundle().apply {
            putStringArrayList("args_actors", actorsList)
        }

        fragmentScenario = launchFragmentInContainer<StarActorsFragment>(
            fragmentArgs = bundle,
            factory = fragmentFactory
        )

        Espresso.onView(
            withId(R.id.star_actors_text)
        ).check(
            matches(
                withText(
                    actorsValue
                )
            )
        )
    }
}






















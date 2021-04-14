package com.codingwithmitch.espressouitestexamples.ui.movie

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.codingwithmitch.espressouitestexamples.R
import com.codingwithmitch.espressouitestexamples.data.FakeMovieData
import com.codingwithmitch.espressouitestexamples.factory.MovieFragmentFactory
import com.codingwithmitch.espressouitestexamples.util.EspressoIdlingResourceRule
import com.codingwithmitch.espressouitestingexamples.util.EspressoIdlingResource
import kotlinx.android.synthetic.main.fragment_movie_list.view.*
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieListFragmentTest {

    /**
     * Não usamos FragmentScenario aqui, pois já temos ele setado no OnCreate da MainActivity, então
     * podemos usar o ActivityScenario<MainActivity>
     */

    private lateinit var activityScenario: ActivityScenario<MainActivity>
    private lateinit var fragmentScenario: FragmentScenario<DirectorsFragment>

    /**
     * Há uma classe base chamada TestWatcher, essa classe é implementado pelo
     * EspressoIdlingResourceRule.
     * O IdlingResource serve para que nõa tenhamos uma interferência entre as interaçõs.
     * Ele existe
     */
    @get:Rule
    val idlingResource = EspressoIdlingResourceRule()

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    //    TODO 1: Check the list is displayed
    @Test
    fun verifyIfMovieListIsDisplayed() {

        // Verificar se a Progress Bar não está visivel
        Espresso.onView(
            withId(R.id.progress_bar)
        ).check(
            matches(
                not(isDisplayed())
            )
        )

        // Checar se a RecyclerView é renderizada
        Espresso.onView(
            withId(R.id.recycler_view)
        ).check(
            matches(
                isDisplayed()
            )
        )
    }

    //    TODO 2: Check Movie Clicked is Opened with correct data
    @Test
    fun verifyIfMovieDataIsShowed() {

        // Verificar se a Progress Bar não está visivel
//        Espresso.onView(
//            withId(R.id.progress_bar)
//        ).check(
//            matches(
//                not(isDisplayed())
//            )
//        )

        // Clicar no item da RecyclerView
        Espresso.onView(
            withId(R.id.recycler_view)
        ).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )

        Espresso.onView(
            withId(R.id.movie_title)
        ).check(
            matches(
                withText(MOVIE.title)
            )
        )

        Espresso.onView(
            withId(R.id.movie_description)
        ).check(
            matches(
                withText(MOVIE.description)
            )
        )
    }

    //    TODO 3: Check Movie Clicked is Opened with correct data and when press back should display recycler view
    @Test
    fun verifyIfDataIsDisplayed_showRecyclerViewWhenClickOnBack() {
        verifyIfMovieDataIsShowed()

//        Espresso.onView(
//            isRoot()
//        ).perform(
//            pressBack()
//        )

        // Fazendo uso do pressBack do sistema e não o do ViewActions!
        Espresso.pressBack()

        Espresso.onView(
            withId(R.id.recycler_view)
        ).check(
            matches(
                isDisplayed()
            )
        )
    }

    //    TODO 4: Check list of directors is correct
    @Test
    fun verifyIsDirectorsListIsShowed() {
        Espresso.onView(
            withId(R.id.recycler_view)
        ).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )

        Espresso.onView(
            withId(R.id.movie_directiors)
        ).perform(
            click()
        )

        val directorsList = DIRECTORS_LIST

        val directorsValue = DirectorsFragment.stringBuilderForDirectors(directors = directorsList)

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

    @After
    fun teardown() {
        activityScenario.close()
    }

    companion object {
        private const val MOVIE_INDEX = 0
        val MOVIE = FakeMovieData.movies[MOVIE_INDEX]

        val DIRECTORS_LIST = arrayListOf("Anthony Russo", "Joe Russo")
    }
}










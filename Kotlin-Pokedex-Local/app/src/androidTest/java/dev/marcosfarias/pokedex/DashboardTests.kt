package dev.marcosfarias.pokedex

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import dev.marcosfarias.pokedex.database.AppDatabase
import dev.marcosfarias.pokedex.database.dao.PokemonDAO
import dev.marcosfarias.pokedex.model.Pokemon
import dev.marcosfarias.pokedex.robots.BaseRobot
import dev.marcosfarias.pokedex.ui.dashboard.DashboardFragment
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.util.concurrent.Executors

class DashboardTests : BaseRobot() {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val context: Context = ApplicationProvider.getApplicationContext()

    lateinit var bundle: Bundle
    lateinit var navHost: TestNavHostController
    lateinit var database: AppDatabase
    private val pokemon = Pokemon()

    @Before
    fun setup() {
        pokemon.apply {
            id = "001"
            name = "Bulbasaur"
        }

        bundle = bundleOf("id" to pokemon.id)

        // Create a TestNavHostController
        navHost = TestNavHostController(ApplicationProvider.getApplicationContext())

        try {
            // Criação da base de dados em memória.
            database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                // Sem essa permissão o Room lança um erro!
                .setTransactionExecutor(Executors.newSingleThreadExecutor())
                .allowMainThreadQueries()
                .build()

        } catch (e: Exception) {
            e.message?.let { errorMessage ->
                Log.i(this.javaClass.simpleName, errorMessage)
            }
        }

        launchFragmentInContainer(themeResId = R.style.AppTheme, fragmentArgs = bundle) {
            DashboardFragment().also { dashboardFragment ->
                // In addition to returning a new instance of our Fragment,
                // get a callback whenever the fragment’s view is created
                // or destroyed so that we can set the NavController
                dashboardFragment.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        // The fragment’s view has just been created
                        navHost.setGraph(R.navigation.mobile_navigation)
                        navHost.setCurrentDestination(R.id.navigation_dashboard)
                        Navigation.setViewNavController(
                            dashboardFragment.requireView(),
                            navHost
                        )
                    }
                }
            }
        }
    }

    @Test
    fun verifyPokemonData() {
        runBlocking {
            database.pokemonDAO().add(listOf(pokemon))
        }

        onView(
            withId(R.id.textViewName)
        ).check(
            matches(
                withText(pokemon.name)
            )
        )
    }

    @After
    fun teardown() {
        database.close()
    }

}
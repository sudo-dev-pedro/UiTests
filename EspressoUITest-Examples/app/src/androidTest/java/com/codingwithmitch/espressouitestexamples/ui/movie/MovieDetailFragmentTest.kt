package com.codingwithmitch.espressouitestexamples.ui.movie

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.bumptech.glide.request.RequestOptions
import com.codingwithmitch.espressouitestexamples.R
import com.codingwithmitch.espressouitestexamples.data.Movie
import com.codingwithmitch.espressouitestexamples.data.source.MoviesDataSource
import com.codingwithmitch.espressouitestexamples.factory.MovieFragmentFactory
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class MovieDetailFragmentTest {

    private lateinit var fragmentScenario: FragmentScenario<MovieDetailFragment>

    //    TODO 1: Check movie details data
    @Test
    fun verifyDetailsData() {

        val movieId = 3
        val movieTitle = "The Dark Knight"
        val movieImage =
            "https://nyc3.digitaloceanspaces.com/open-api-spaces/open-api-static/blog/1/The_Dark_Knight-fragment_factory_1.png"
        val movieDescription =
            "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice."

        val movie = Movie(
            3,
            "The Dark Knight",
            "https://nyc3.digitaloceanspaces.com/open-api-spaces/open-api-static/blog/1/The_Dark_Knight-fragment_factory_1.png",
            "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.",
            arrayListOf(
                "Christopher Nolan"
            ),
            arrayListOf(
                "Christian Bale",
                "Heath Ledger",
                "Aaron Eckhart"
            )
        )


        val requestOptions = RequestOptions
            .placeholderOf(R.drawable.default_image)
            .error(R.drawable.default_image)

        // Por que precisamos mockkar?
        // R: Por não estarmos no contexto da Main Activity e estarmos só testando a Fragment isolado
        // não temos a instância do MoviesDataSource, sendo assim, precisamos mockka-la
        val moviesDataSource = mockk<MoviesDataSource>()
        every { moviesDataSource.getMovie(movieId) } returns movie

        val fragmentFactory = MovieFragmentFactory(
            requestOptions,
            moviesDataSource
        )

        // Construindo um Bundle com o nome dos diretores.
        val bundle = Bundle().apply {
            putInt("movie_id", movie.id)
        }

        launchFragmentInContainer<MovieDetailFragment>(
            fragmentArgs = bundle,
            factory = fragmentFactory
        )
    }
}




















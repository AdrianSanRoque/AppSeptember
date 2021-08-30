package es.usj.section_1.adrian.appseptember.Movies.helpers

import es.usj.section_1.adrian.appseptember.Models.Movie

class MoviesListener(val clickListener: (movieId: String) -> Unit) {
    fun onclick(movieDetails: Movie) = clickListener(movieDetails.rank.toString())
}
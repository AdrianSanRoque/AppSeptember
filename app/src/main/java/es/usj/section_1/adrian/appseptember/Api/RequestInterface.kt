package es.usj.section_1.adrian.appseptember.Api


//revisar
import es.usj.section_1.adrian.appseptember.Models.MoviePoster
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface RequestInterface {


    @GET("/")
    fun getPoster(
        @Query("apikey") apiKey: String?,
        @Query("t") t: String?
    ):Call<MoviePoster?>

}
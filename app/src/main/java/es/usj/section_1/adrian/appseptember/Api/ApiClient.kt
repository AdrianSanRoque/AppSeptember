package es.usj.section_1.adrian.appseptember.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    //http://www.omdbapi.com/?apikey=5ff62e7d&t=Guardians%20of%20the%20Galaxy
    companion object {
        fun getApiClient(): Retrofit? {
            val BASE_URL = "http://www.omdbapi.com/"
            var retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit
        }
    }
}
package es.usj.section_1.adrian.appseptember.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MoviePoster {
    @SerializedName("Poster")
    @Expose
    var poster: String? = null
}
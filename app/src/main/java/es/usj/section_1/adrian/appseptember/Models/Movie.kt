package es.usj.section_1.adrian.appseptember.Models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Movie {
    @SerializedName("rank")
    @Expose
    var rank: Int? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("genre")
    @Expose
    var genre: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("director")
    @Expose
    var director: String? = null

    @SerializedName("actors")
    @Expose
    var actors: String? = null

    @SerializedName("year")
    @Expose
    var year: Int? = null

    @SerializedName("runtimeminutes")
    @Expose
    var runtimeminutes: Int? = null

    @SerializedName("rating")
    @Expose
    var rating: Double? = null

    @SerializedName("votes")
    @Expose
    var votes: Int? = null

    @SerializedName("revenuemillions")
    @Expose
    var revenuemillions: Double? = null

    @SerializedName("metascore")
    @Expose
    var metascore: Int? = null
}
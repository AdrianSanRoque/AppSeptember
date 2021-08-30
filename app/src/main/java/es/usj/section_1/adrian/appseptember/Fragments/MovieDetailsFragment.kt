package es.usj.section_1.adrian.appseptember.Fragments

import android.app.ProgressDialog
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.bands.hambands.sqlite.Sqlitehelper
import com.bumptech.glide.Glide
import es.usj.section_1.adrian.appseptember.Api.ApiClient
import es.usj.section_1.adrian.appseptember.Api.RequestInterface
import es.usj.section_1.adrian.appseptember.Constants
import es.usj.section_1.adrian.appseptember.Models.Movie
import es.usj.section_1.adrian.appseptember.Models.MoviePoster
import es.usj.section_1.adrian.appseptember.viewModels.MovieDetailsViewModel
import es.usj.section_1.adrian.appseptember.R
import es.usj.section_1.adrian.appseptember.databinding.MovieDetailsFragmentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailsFragment : Fragment() {


    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var binding: MovieDetailsFragmentBinding


    lateinit var image: ImageView
    lateinit var title: TextView
    lateinit var yearTv: TextView
    lateinit var runtime: TextView
    lateinit var ratingTv: TextView
    lateinit var desc: TextView
    lateinit var director: TextView
    lateinit var genre: TextView
    lateinit var actors: TextView
    lateinit var rank: String
    lateinit var genresLayout: LinearLayout
    lateinit var actorsLayout: LinearLayout
    lateinit var pd: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.movie_details_fragment, container, false)
        viewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
        binding.viewmodel = viewModel


        //rank = Intent.getStringExtra("rank").toString()
        val rank = "rank"


        //title = findViewById(R.id.title)
        title = binding.title
        //yearTv = findViewById(R.id.year)
        yearTv = binding.year
        //runtime = findViewById(R.id.runTime)
        runtime= binding.runTime
        //ratingTv = findViewById(R.id.rating)
        ratingTv= binding.rating
        //desc = findViewById(R.id.desc)
        desc = binding.desc
        //director = findViewById(R.id.director)
        director=binding.director
        //genre = findViewById(R.id.genre)
        genre= binding.genre
        //actors = findViewById(R.id.actors)
        actors= binding.actors
        //genresLayout = findViewById(R.id.genreLayout)
        genresLayout= binding.genreLayout
        //actorsLayout = findViewById(R.id.actorsLayout)
        actorsLayout= binding.actorsLayout
        //image = findViewById(R.id.thumbImage)
        image= binding.thumbImage
        pd = ProgressDialog(requireActivity())
        pd.setMessage("Loading...")
        pd.show()
        getCurrentData(rank)



        return binding.root
    }

    internal fun getCurrentData(s: String) {
        val sqlitehelper = Sqlitehelper(requireActivity())
        val allmovies: ArrayList<Movie> =sqlitehelper.readdata() as ArrayList<Movie>
        var movie = Movie()
        for (item in allmovies){
            if (item.rank.toString()?.equals(rank) == true){
                movie = item
                break
            }
        }

        title.text = movie.title
        getMoviePoster(movie.title.toString(), image)
        yearTv.text = movie.year.toString()
        runtime.text = ", " + movie.runtimeminutes + " min"
        ratingTv.text = movie.rating.toString()
        desc.text = movie.description
        director.text = movie.director


        if (!movie.genre.isNullOrEmpty()){
            genresLayout.removeAllViews()
            val str: String = movie.genre!!
            val genresSeparated = str.split(",").toTypedArray()

            for (item in genresSeparated){
                val dynaText = TextView(requireActivity())
                dynaText.setText(item)
                dynaText.setTextSize(16F)
                dynaText.setTextColor(ContextCompat.getColor(requireActivity(), R.color.blue_500))
                val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(10, 10, 10, 0)
                dynaText.setLayoutParams(params)
                dynaText.setClickable(true);//make your TextView Clickable
                dynaText.setOnClickListener(View.OnClickListener {
                    val it = Intent(requireActivity(), GenreFragment::class.java)
                    it.putExtra("name", item)
                    startActivity(it)
                });
                genresLayout.addView(dynaText);
            }
        }


        if (!movie.actors.isNullOrEmpty()){
            actorsLayout.removeAllViews()
            val str: String = movie.actors!!
            val actorsSeparated = str.split(",").toTypedArray()

            for (item in actorsSeparated){
                val dynaText = TextView(requireActivity())

                dynaText.setText(item);
                dynaText.setTextSize(16F);
                dynaText.setTextColor(ContextCompat.getColor(requireActivity(), R.color.blue_500))
                val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(10, 10, 10, 0)
                dynaText.setLayoutParams(params)
                dynaText.setClickable(true);//make your TextView Clickable
                dynaText.setOnClickListener(View.OnClickListener {
                    val it =  Intent(requireActivity(), ActorsFragment::class.java)
                    it.putExtra("name", item)
                    startActivity(it)
                });
                actorsLayout.addView(dynaText);
            }
        }
        pd.dismiss()
    }

    internal fun getMoviePoster(t: String, img: ImageView) {
        val service = ApiClient.getApiClient()?.create(RequestInterface::class.java)
        val call = service?.getPoster(Constants.getApiKey(), t)
        call?.enqueue(object : Callback<MoviePoster?> {
            override fun onResponse(call: Call<MoviePoster?>, response: Response<MoviePoster?>) {
                if (response.isSuccessful){
                    Glide.with(requireActivity())
                        .load(response.body()?.poster)
                        .centerCrop()
                        .placeholder(R.drawable.movies_icon)
                        .into(img)
                }else{
                    Toast.makeText(requireActivity(), "Response Error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MoviePoster?>, t: Throwable) {
                Toast.makeText(requireContext(), "An Error Occurred: " + t.message, Toast.LENGTH_SHORT).show()
            }

        })

    }









        //viewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)

}
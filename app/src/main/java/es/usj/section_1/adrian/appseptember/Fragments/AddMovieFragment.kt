package es.usj.section_1.adrian.appseptember.Fragments

import android.app.ProgressDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bands.hambands.sqlite.Sqlitehelper
import es.usj.section_1.adrian.appseptember.viewModels.AddMovieViewModel
import es.usj.section_1.adrian.appseptember.databinding.AddMovieFragmentBinding
import es.usj.section_1.adrian.appseptember.Models.Movie
import es.usj.section_1.adrian.appseptember.R
import kotlinx.android.synthetic.main.add_movie_fragment.*

class AddMovieFragment : Fragment() {


    private lateinit var viewModel: AddMovieViewModel
    private lateinit var binding: AddMovieFragmentBinding


    lateinit var title: EditText
    lateinit var genre: EditText
    lateinit var desc: EditText
    lateinit var director: EditText
    lateinit var actors: EditText
    lateinit var yearEt: EditText
    lateinit var runtimeEt: EditText
    lateinit var ratingEt: EditText
    lateinit var votesEt: EditText
    lateinit var revenueEt: EditText
    lateinit var metaScoreEt: EditText
    lateinit var AddBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= DataBindingUtil.inflate(inflater, R.layout.add_movie_fragment,container,false)
        viewModel=ViewModelProvider(this).get(AddMovieViewModel::class.java)



        //val id = Intent.getStringExtra("_rank")
        val id = "_rank"

        //title = findViewById(R.id.title)
        title = binding.etTitle

        actors= binding.etActors

        genre= binding.etGenres

        //desc = findViewById(R.id.desc)
        desc = binding.etDescription

        //director = findViewById(R.id.etDirector)
        director = binding.etDirector

        //actors = findViewById(R.id.actors)
        director = binding.etDirector

        //yearEt = findViewById(R.id.etYear)
        yearEt = binding.etYear

        //runtimeEt = findViewById(R.id.etRuntime)
        runtimeEt = binding.etRuntime

        //ratingEt = findViewById(R.id.etRating)
        ratingEt = binding.etRating

        //votesEt = findViewById(R.id.etVotes)
        votesEt = binding.etVotes


        //revenueEt = findViewById(R.id.etRevenue)
        revenueEt = binding.etRevenue

        //metaScore = findViewById(R.id.metascore)
        metaScoreEt= binding.etMetascore


        //AddBtn = findViewById(R.id.btnAdd)
        AddBtn = binding.btnAdd

        /*if (!id.isNullOrEmpty()) {
            toolbar.setTitle("Edit Movie")
            populateViews(id)
        }

         */




        AddBtn .setOnClickListener {
            var er = "";
            if (title.text.toString().trim().isNullOrEmpty()) {
                er = "Title";
            }
            if (genre.text.toString().trim().isNullOrEmpty()) {
                er = "Genre";
            }
            if (desc.text.toString().trim().isNullOrEmpty()) {
                er = "Description";
            }
            if (director.text.toString().trim().isNullOrEmpty()) {
                er = "Director";
            }
            if (actors.text.toString().trim().isNullOrEmpty()) {
                er = "Actors";
            }

            if (!er.isEmpty()) {
                Toast.makeText(requireActivity(), er + " is required", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else {
                val movie = Movie()

                movie.title = title.text.toString()
                movie.genre = genre.text.toString()
                movie.description = desc.text.toString()
                movie.director = director.text.toString()
                movie.actors = actors.text.toString()
                movie.year = Integer.parseInt(yearEt.text.toString())

                if (runtimeEt.text.isNullOrEmpty()){
                    movie.runtimeminutes = 0
                }else{
                    movie.runtimeminutes = Integer.parseInt(runtimeEt.text.toString())
                }
                if (ratingEt.text.isNullOrEmpty()){
                    movie.rating = 0.0
                }else{
                    movie.rating = ratingEt.text.toString().toDouble()
                }
                if (votesEt.text.isNullOrEmpty()){
                    movie.votes = 0
                }else{
                    movie.votes = Integer.parseInt(votesEt.text.toString())
                }
                if (revenueEt.text.isNullOrEmpty()){
                    movie.revenuemillions = 0.0
                }else{
                    movie.revenuemillions = revenueEt.text.toString().toDouble()
                }

                if (metaScoreEt.text.isNullOrEmpty()){
                    movie.metascore = 0
                }/*else{
                    movie.metascore = Integer.parseInt(metaScore.text.toString())
                }*/

                val sqlitehelper: Sqlitehelper = Sqlitehelper(requireActivity())

                if (id.isNullOrEmpty()){
                    sqlitehelper.insertOne(movie)
                    Toast.makeText(requireActivity(), "Movie Inserted Successfully.", Toast.LENGTH_SHORT).show()
                }else{
                    movie.rank = Integer.parseInt(id)
                    sqlitehelper.Updateitem(movie, Integer.parseInt(id))
                    Toast.makeText(requireActivity(), "Movie Updated Successfully.", Toast.LENGTH_SHORT).show()
                }
            }
        }
        //viewModel = ViewModelProvider(this).get(AddMovieViewModel::class.java)
        //return inflater.inflate(R.layout.add_movie_fragment, container, false)
        return binding.root
    }

    fun populateViews(s: String){

        val pd: ProgressDialog = ProgressDialog(requireActivity())
        pd.setMessage("Loading...")
        pd.show()

        val sqlitehelper: Sqlitehelper = Sqlitehelper(requireActivity())

        val allmovies: ArrayList<Movie> = sqlitehelper.readdata() as ArrayList<Movie>
        var movie = Movie()
        for (item in allmovies){
            if (item.rank.toString().equals(s)){
                movie = item
                break
            }
        }

        title.setText(movie.title)
        genre.setText(movie.genre)
        desc.setText(movie.description)
        director.setText(movie.director)
        actors.setText(movie.actors)
        yearEt.setText( movie.year.toString())
        runtimeEt.setText(movie.runtimeminutes.toString())
        ratingEt.setText(movie.rating.toString())
        votesEt.setText(movie.votes.toString())
        revenueEt.setText(movie.revenuemillions.toString())
        metaScoreEt.setText(movie.metascore.toString())

        pd.dismiss()
    }



}
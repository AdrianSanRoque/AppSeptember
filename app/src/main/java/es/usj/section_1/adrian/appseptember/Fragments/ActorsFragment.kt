package es.usj.section_1.adrian.appseptember.Fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bands.hambands.sqlite.Sqlitehelper
import es.usj.section_1.adrian.appseptember.Movies.helpers.MoviesAdapter
import es.usj.section_1.adrian.appseptember.Models.Movie
import es.usj.section_1.adrian.appseptember.viewModels.ActorsViewModel
import es.usj.section_1.adrian.appseptember.R
import es.usj.section_1.adrian.appseptember.databinding.ActorsFragmentBinding
import java.util.*
import kotlin.collections.ArrayList

class ActorsFragment : Fragment() {


    private lateinit var viewModel: ActorsViewModel
    private lateinit var binding: ActorsFragmentBinding
    lateinit var adapter: MoviesAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.actors_fragment, container, false)
        viewModel = ViewModelProvider(this).get(ActorsViewModel::class.java)
        binding.viewmodel=viewModel

        //val name: String = Intent.getStringExtra("name")!!
        val name = "name"!!

        //recyclerView = findViewById<RecyclerView>(R.id.localhostapimoviesList)
        recyclerView = binding.localhostapimoviesList

        /*val toolbar: Toolbar = findViewById(R.id.toolbar)*/
        val toolbar: Toolbar= binding.toolbar
        toolbar.setTitle("Showing Movies ofActor")
        toolbar.setSubtitle(name)
        getCurrentData(name)



        return binding.root

    }
    internal fun getCurrentData(s: String) {

        val sqlitehelper = Sqlitehelper(requireActivity())
        val allmovies: ArrayList<Movie> = sqlitehelper.readdata() as ArrayList<Movie>
        var fmovies: ArrayList<Movie> = ArrayList();

        for (item in allmovies) {
            if (item.actors?.contains(s) == true) {
                fmovies.add(item)
            }
        }

        Collections.reverse(fmovies)
        adapter = MoviesAdapter(fmovies, requireActivity())
        val glm = GridLayoutManager(requireActivity(), 2)
        recyclerView.setLayoutManager(glm)
        recyclerView.setAdapter(adapter)
    }





}
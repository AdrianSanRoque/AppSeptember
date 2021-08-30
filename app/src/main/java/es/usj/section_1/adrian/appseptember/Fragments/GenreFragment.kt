package es.usj.section_1.adrian.appseptember.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bands.hambands.sqlite.Sqlitehelper
import es.usj.section_1.adrian.appseptember.Movies.helpers.MoviesAdapter
import es.usj.section_1.adrian.appseptember.Models.Movie
import es.usj.section_1.adrian.appseptember.R
import es.usj.section_1.adrian.appseptember.viewModels.GenreViewModel
import es.usj.section_1.adrian.appseptember.databinding.GenreFragmentBinding
import java.util.*
import kotlin.collections.ArrayList

class GenreFragment : Fragment() {


    private lateinit var viewModel: GenreViewModel
    private lateinit var binding: GenreFragmentBinding
    lateinit var adapter: MoviesAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.genre_fragment, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(GenreViewModel::class.java)
        binding.viewmodel=viewModel


        //val name: String = Intent.getStringExtra("name")!!
        val  name = "name"


        //recyclerView = findViewById<RecyclerView>(R.id.localhostapimoviesList)
        recyclerView = binding.localhostapimoviesList

        //val toolbar: Toolbar = findViewById(R.id.toolbar)
        val toolbar1:Toolbar=binding.toolbar
        toolbar1.setTitle("Showing Movies of Genre")
        toolbar1.setSubtitle(name)
        getCurrentData(name)



        fun navigateToActorFragment(){
            if (this.findNavController().currentDestination?.id == R.id.genreFragment) {
                this.findNavController().navigate(R.id.action_genreFragment_to_actorsFragment)
                viewModel.onNavigatedToActorFragment()
            }
        }
        /*viewModel.navigateToActor.observe(viewLifecycleOwner, Observer {

            it.let {
                if (it == true) {
                    navigateToActorFragment()
                }
            }



        })

         */



        return binding.root

    }


    internal fun getCurrentData(s: String) {

        val sqlitehelper = Sqlitehelper(requireActivity())
        val allmovies: ArrayList<Movie> = sqlitehelper.readdata() as ArrayList<Movie>
        var fmovies: ArrayList<Movie> = ArrayList();

        for (item in allmovies) {
            if (item.genre?.contains(s) == true) {
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
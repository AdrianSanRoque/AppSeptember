package es.usj.section_1.adrian.appseptember.Movies

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bands.hambands.sqlite.Sqlitehelper
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.usj.section_1.adrian.appseptember.Movies.helpers.MoviesAdapter
import es.usj.section_1.adrian.appseptember.Models.Movie
import es.usj.section_1.adrian.appseptember.R
import es.usj.section_1.adrian.appseptember.viewModels.MoviesViewModel
import es.usj.section_1.adrian.appseptember.databinding.MoviesFragmentBinding
import java.util.*
import kotlin.collections.ArrayList
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

class MoviesFragment : Fragment() {


    private lateinit var viewModel: MoviesViewModel
    private lateinit var binding: MoviesFragmentBinding


    private val ARG_PARAM1 = "param1"
    private val ARG_PARAM2 = "param2"

    lateinit var search: EditText
    lateinit var adapter: MoviesAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var fab: FloatingActionButton
    lateinit var swp: SwipeRefreshLayout

    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.movies_fragment, container, false)
        viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
        binding.viewmodel = viewModel

        //recyclerView = v.findViewById<RecyclerView>(R.id.localhostapimoviesList)
        recyclerView = binding.localhostapimoviesList
        //search = v.findViewById(R.id.searchEt)
        search = binding.searchEt
        //fab = v.findViewById(R.id.addmoviesfab)
        fab = binding.addmoviesfab
        //swp = v.findViewById(R.id.swipeContainer)
        swp = binding.swipeContainer
        val activity = activity as Context

        //val viewPager= binding2.viewPager


        /*var adapter = MoviesAdapter(MoviesListener { Movie ->
            viewModel.onMovieDetailsclicked(Movie)
        })
        binding.localhostapimoviesList.adapter=adapter
        */



        fun getCurrentData(toString: String, context: Context) {
            val sqlitehelper = context?.let { Sqlitehelper(it) }
            var fullList = sqlitehelper.readdata() as ArrayList<Movie>
            var slist = ArrayList<Movie>()
            if (toString.isNullOrEmpty()) {
                slist = fullList
            } else {
                for (item in fullList) {
                    val searchtitle = toString.toLowerCase()
                    val movietitle = item.title.toString().toLowerCase()
                    if (movietitle.contains(searchtitle)) {
                        slist.add(item)
                    }
                }
            }

            Collections.reverse(slist)
            adapter = MoviesAdapter(slist, context as FragmentActivity)
            val glm = GridLayoutManager(context, 2)
            recyclerView.setLayoutManager(glm)
            recyclerView.setAdapter(adapter)
            swp.isRefreshing = false
        }

        swp.setOnRefreshListener({ // Your code to refresh the list here.
            // Make sure you call swipeContainer.setRefreshing(false)
            // once the network request has completed successfully.
            swp.isRefreshing = true
            getCurrentData(search.text.toString(), activity)
        })

        getCurrentData(search.text.toString(), activity)
        search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                context?.let { getCurrentData(s.toString(), it) }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        /*fab.setOnClickListener{
            val it = Intent(context, AddMovie::class.java)
            startActivity(it)
        }*/
        /*
        * viewModel.navigateToScanProductFragment.observe(viewLifecycleOwner, Observer {
            if (it != null){
                navigateToScanProductFragment()
            }
        })*/


        /*viewModel.navigateToAddMovie.observe(viewLifecycleOwner, Observer {
            it.let {
                if (it == true) {
                    navigateToAddCommentFragment()
                }
            }
        })*/
        fun navigateToAddMovieFragment() {
            if (this.findNavController().currentDestination?.id == R.id.moviesFragment) {
                this.findNavController().navigate(R.id.action_moviesFragment_to_addMovieFragment)
                viewModel.onNavigatedToAddMovieFragment()
            }
        }

        viewModel.navigateToAddMovie.observe(viewLifecycleOwner, Observer{
            it.let {
                if (it == true) {
                    navigateToAddMovieFragment()
                }
            }

        })



        fun navigateToGenreFragment(){
            if (this.findNavController().currentDestination?.id == R.id.moviesFragment) {
                this.findNavController().navigate(R.id.action_moviesFragment_to_genreFragment)
                viewModel.onNavigatedToGenreFragment()
            }
        }
        viewModel.navigateToGenre.observe(viewLifecycleOwner, Observer {
            it.let {
                if (it == true) {
                    navigateToGenreFragment()
                }
            }

        })



        //Temporal


        /*fun navigateToContactFragment(){
            if (this.findNavController().currentDestination?.id == R.id.moviesFragment) {
                this.findNavController().navigate(R.id.action_moviesFragment_to_genreFragment)
                viewModel.onNavigatedToGenreFragment()
            }
        }*/

        viewModel.navigateToContact.observe(viewLifecycleOwner, Observer {
            it.let {
                if(it == true){
                    //navigateToContactFragment()
                }
            }
        })






        return binding.root
    }



}
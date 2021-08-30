package es.usj.section_1.adrian.appseptember.Fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import es.usj.section_1.adrian.appseptember.viewModels.MovieListViewModel
import es.usj.section_1.adrian.appseptember.R
import es.usj.section_1.adrian.appseptember.databinding.MovieListFragmentBinding


class MovieListFragment : Fragment() {


    private lateinit var viewModel: MovieListViewModel
    private lateinit var binding: MovieListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.movie_list_fragment, container, false)
        viewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)
        binding.viewmodel=viewModel

        //val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val toolbar= binding.toolbar
        toolbar.setTitle(getString(R.string.app_name))
        /*setSupportActionBar(toolbar)*/
        (activity as AppCompatActivity).supportActionBar


        val tabLayout = binding.tabLayout
        //val viewPager = binding.viewPager

        tabLayout!!.addTab(tabLayout!!.newTab().setText("Movies"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Genre"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Actors"))
        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        val fragmentManager = (activity as FragmentActivity).supportFragmentManager

        //val adapter = ViewPagerAdapter(requireActivity(), fragmentManager, tabLayout!!.tabCount)

        //viewPager!!.adapter = adapter

        //viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                //viewPager!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
        return binding.root


    }

    /*
    //ITS IN MAIN ACTIVITY NOW

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.contact -> {
                //val it = Intent(requireActivity(), ContactFragment::class.java)
                //startActivity(it)
                //true

                //navigate to contact fragment


            }

            else -> super.onOptionsItemSelected(item)
        }
    }


     */




}


//viewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)

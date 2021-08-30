package es.usj.section_1.adrian.appseptember.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import es.usj.section_1.adrian.appseptember.Adapters.ViewPagerAdapter
import es.usj.section_1.adrian.appseptember.Movies.MoviesFragment
import es.usj.section_1.adrian.appseptember.R
import es.usj.section_1.adrian.appseptember.viewModels.ViewPagerViewModel
import es.usj.section_1.adrian.appseptember.databinding.ViewPagerFragmentBinding
import kotlinx.android.synthetic.main.view_pager_fragment.view.*

class ViewPagerFragment : Fragment() {

    private lateinit var viewModel: ViewPagerViewModel
    private lateinit var binding: ViewPagerFragmentBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= DataBindingUtil.inflate(inflater, R.layout.view_pager_fragment,container,false)
        viewModel = ViewModelProvider(this).get(ViewPagerViewModel::class.java)
        binding.viewmodel=viewModel

        val view= inflater.inflate(R.layout.view_pager_fragment,container, false)

        val fragmentList= arrayListOf<Fragment>(

            MoviesFragment(),
            GenreFragment(),
            ActorsFragment()


        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )


        view.viewPager.adapter=adapter

        viewModel.navigateToContact.observe(viewLifecycleOwner, Observer {
            it.let {
                if(it == true){
                    //navigateToContactFragment()
                }
            }
        })



        return binding.root

    }

    /*private fun navigateToContactFragment() {
        if(this.findNavController().currentDestination?.id == R.id.viewPagerFragment){
            this.findNavController().navigate(R.id.action_viewPagerFragment_to_contactFragment)
            viewModel.onNavigatedToContact()
        }
    }

     */




}
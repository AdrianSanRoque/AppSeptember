package es.usj.section_1.adrian.appseptember.Fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import es.usj.section_1.adrian.appseptember.R
import es.usj.section_1.adrian.appseptember.viewModels.SplashViewModel
import es.usj.section_1.adrian.appseptember.databinding.SplashFragmentBinding


class SplashFragment : Fragment() {


    private lateinit var viewModel: SplashViewModel
    private lateinit var binding: SplashFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding= DataBindingUtil.inflate(inflater, R.layout.splash_fragment,container,false)
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        binding.viewmodel=viewModel

        /*val background: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(1500)
                    //below is the intent to load movies list activity. which has tabs of movies, genre, and actors.
                    val it = Intent(requireActivity(), MovieListFragment::class.java)
                    startActivity(it)
                    finish()
                } catch (e: Exception) {
                    Toast.makeText(requireActivity(), "" + e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        background.start()

         */

        Handler().postDelayed({

            findNavController().navigate(R.id.action_splashFragment_to_moviesFragment)
        },3000)



        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

}
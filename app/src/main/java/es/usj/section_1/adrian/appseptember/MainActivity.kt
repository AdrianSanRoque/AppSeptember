package es.usj.section_1.adrian.appseptember

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import es.usj.section_1.adrian.appseptember.Fragments.ContactFragment
import es.usj.section_1.adrian.appseptember.databinding.ActivityMainBinding
import es.usj.section_1.adrian.appseptember.viewModels.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    public lateinit var viewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.mainActivityViewModel = viewModel
        binding.lifecycleOwner = this

        /*binding= DataBindingUtil.inflate(inflater, R.layout.splash_fragment,container,false)
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        binding.viewmodel=viewModel*/

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        //setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.title != null && item.title.equals("contact")) {
            return false
        }

        return item.onNavDestinationSelected(navController) ||
                return super.onOptionsItemSelected(item)
    }
}




/*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.title != null &&  item.title.equals("settings")){
            return false
        }


        //navigate to clicked destination if the id in menu matches a layout file
        return item.onNavDestinationSelected(navController) || return super.onOptionsItemSelected(item)
    }*/
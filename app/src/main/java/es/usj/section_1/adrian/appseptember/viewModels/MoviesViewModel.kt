package es.usj.section_1.adrian.appseptember.viewModels

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.usj.section_1.adrian.appseptember.Models.Movie

class MoviesViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val _navigateToAddMovieFragment = MutableLiveData<Boolean?>()
    val navigateToAddMovie : LiveData<Boolean?>
        get() = _navigateToAddMovieFragment



    fun onNavigatedToAddMovieFragment() {
        _navigateToAddMovieFragment.value = false
    }

    fun btnNavigatetoAddMovieClicked(){
        _navigateToAddMovieFragment.value=true

    }


    private val _navigateToGenreFragment = MutableLiveData<Boolean?>()
    val navigateToGenre : LiveData<Boolean?>
        get() = _navigateToGenreFragment


    fun onNavigatedToGenreFragment() {
        _navigateToGenreFragment.value = false
    }

    fun btnNavigatetoGenreClicked(){
        _navigateToGenreFragment.value=true

    }




    private val _navigateToMovieDetails = MutableLiveData<String>()
    val navigateToMovieDetails : LiveData<String>
        get() = _navigateToMovieDetails


    fun onMovieDetailsclicked(productId: String){
        _navigateToMovieDetails.value = productId
    }

    @SuppressLint("NullSafeMutableLiveData")
    fun onNavigatedToMovieDetails(){
        _navigateToMovieDetails.value = null
    }

    //var user: MutableLiveData<User?> = MutableLiveData()
    var movieItems: MutableLiveData<List<Movie>> = MutableLiveData()


    /*fun getMovieItems(){
        movieItems = .getUsersHistory(user.value?.id!!)
        _refreshListener.postValue(true)

    }

    fun onListenerRefreshed(){
        _refreshListener.postValue(false)
    }

     */



    //TEMPORAL

    private val _navigateToContact = MutableLiveData<Boolean?>()
    val navigateToContact: LiveData<Boolean?>
        get() = _navigateToContact


    fun onNavigatedToContact(){
        _navigateToContact.value = false
    }



}
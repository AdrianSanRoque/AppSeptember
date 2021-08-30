package es.usj.section_1.adrian.appseptember.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GenreViewModel : ViewModel() {


    private val _navigateToActorFragment = MutableLiveData<Boolean?>()
    val navigateToActor : LiveData<Boolean?>
        get() = _navigateToActorFragment


    fun onNavigatedToActorFragment() {
        _navigateToActorFragment.value = false
    }

    fun btnNavigatetoActorClicked(){
        _navigateToActorFragment.value=true

    }
}
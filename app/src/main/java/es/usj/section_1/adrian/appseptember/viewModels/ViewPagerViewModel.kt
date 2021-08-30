package es.usj.section_1.adrian.appseptember.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewPagerViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _navigateToContact = MutableLiveData<Boolean?>()
    val navigateToContact: LiveData<Boolean?>
        get() = _navigateToContact


    fun onNavigatedToContact(){
        _navigateToContact.value = false
    }

}
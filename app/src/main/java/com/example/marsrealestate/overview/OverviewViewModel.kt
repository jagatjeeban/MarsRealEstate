
package com.example.marsrealestate.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsrealestate.network.MarsApi
import com.example.marsrealestate.network.MarsProperty
import kotlinx.coroutines.launch

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    //The internal MutableLiveData for a single MarsProperty object
    private val _properties = MutableLiveData<List<MarsProperty>>()

    //The external immutableLiveData for a single MarsProperty object
    val properties: LiveData<List<MarsProperty>>
    get() = _properties

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getMarsRealEstateProperties()
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getMarsRealEstateProperties() {

        /*
        The MarsApi.retrofitService.getProperties() method returns a Call object.
        Then you can call enqueue() on that object to start the network request on a background thread.
         */
        viewModelScope.launch {
            try {
                _properties.value = MarsApi.retrofitService.getProperties()
                _response.value = "Success: Mars properties retrieved"
            } catch (e:Exception){
                _response.value = "Failure: ${e.message}"
            }
        }
    }
}

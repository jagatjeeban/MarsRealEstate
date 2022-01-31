
package com.example.marsrealestate.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marsrealestate.network.MarsApi
import com.example.marsrealestate.network.MarsApiFilter
import com.example.marsrealestate.network.MarsProperty
import kotlinx.coroutines.launch

enum class MarsApiStatus{LOADING, ERROR, DONE}  //an enum to represent all the available statuses:
/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<MarsApiStatus>()

    val status: LiveData<MarsApiStatus>
        get() = _status

    //The internal MutableLiveData for a single MarsProperty object
    private val _properties = MutableLiveData<List<MarsProperty>>()

    //The external immutableLiveData for a single MarsProperty object
    val properties: LiveData<List<MarsProperty>>
    get() = _properties

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getMarsRealEstateProperties(MarsApiFilter.SHOW_ALL)
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getMarsRealEstateProperties(filter: MarsApiFilter) {

        viewModelScope.launch {
            _status.value = MarsApiStatus.LOADING
            try {
                _properties.value = MarsApi.retrofitService.getProperties(filter.value)
                _status.value = MarsApiStatus.DONE
            } catch (e:Exception){
                _status.value = MarsApiStatus.ERROR
                _properties.value = ArrayList()    //setting it to an empty list clears the recyclerView
            }
        }
    }
    fun updateFilter(filter: MarsApiFilter){
        getMarsRealEstateProperties(filter)
    }
}

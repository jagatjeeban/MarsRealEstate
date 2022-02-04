package com.example.marsrealestate.detail

import android.app.Application
import androidx.lifecycle.*
import com.example.marsrealestate.R
import com.example.marsrealestate.network.MarsProperty

/**
 * The [ViewModel] that is associated with the [DetailFragment].
 */
class DetailViewModel(marsProperty: MarsProperty, app: Application) : AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<MarsProperty>()
    val selectedProperty : LiveData<MarsProperty>
    get() = _selectedProperty

    init {
        _selectedProperty.value = marsProperty
    }

    /**
     * This transformation tests whether the selected property is a rental.If the property is a rental,
     * the transformation chooses the appropriate string from the resources with a Kotlin when {} switch.
     */
    val displayPropertyPrice = Transformations.map(selectedProperty){
        app.applicationContext.getString(
            when(it.isRental){
                true -> R.string.display_price_monthly_rental
                false -> R.string.display_price
            }, it.price )
    }

    /**
     * This transformation concatenates multiple string resources,
     * based on whether the property type is a rental.
     */
    val displayPropertyType = Transformations.map(selectedProperty){
        app.applicationContext.getString(R.string.display_type,
        app.applicationContext.getString(
            when(it.isRental){
                true -> R.string.type_rent
                false -> R.string.type_sale
            }))
    }
}

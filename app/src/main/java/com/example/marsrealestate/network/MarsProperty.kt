package com.example.marsrealestate.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * The Parcelable interface enables objects to be serialized,
 * so that the objects' data can be passed around between fragments or activities.
 *
 * The @Parcelize annotation uses the Kotlin Android extensions to
 * automatically implement the methods in the Parcelable interface for this class.
 */
@Parcelize
data class MarsProperty(val price:Double,
                        val id:String,
                   private val type:String,
                   @Json(name = "img_src")val imgSrcUrl:String): Parcelable {
    val isRental
    get() = type == "rent"  //a custom getter for isRental that returns true if the object is of type "rent".
}

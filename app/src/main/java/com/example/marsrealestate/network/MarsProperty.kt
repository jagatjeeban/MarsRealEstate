
package com.example.marsrealestate.network

import com.squareup.moshi.Json

class MarsProperty(val price:Double,
                   val id:String,
                   private val type:String,
                   @Json(name = "img_src")val imgSrcUrl:String){
    val isRental
    get() = type == "rent"  //a custom getter for isRental that returns true if the object is of type "rent".
}

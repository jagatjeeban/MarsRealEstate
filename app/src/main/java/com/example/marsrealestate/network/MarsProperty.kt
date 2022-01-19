
package com.example.marsrealestate.network

import com.squareup.moshi.Json

class MarsProperty(val price:Double,
                   val id:String,
                   val type:String,
                   @Json(name = "img_src")val imgSrcUrl:String)

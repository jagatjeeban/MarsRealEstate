
package com.example.marsrealestate.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//define constants that match the query values the web service expects.
enum class MarsApiFilter(val value:String){
    SHOW_RENT("rent"),
    SHOW_BUY("buy"),
    SHOW_ALL("all")
}

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

//moshi object
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())  //added for moshi annotations to work properly with kotlin.
    .build()

//retrofit object
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

//interface that defines how retrofit talks to the web server using http requests.
interface MarsApiService{
    @GET("realestate")
    suspend fun getProperties(@Query("filter") type:String):List<MarsProperty>
}
//initializing a retrofit service object
object MarsApi{
    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}

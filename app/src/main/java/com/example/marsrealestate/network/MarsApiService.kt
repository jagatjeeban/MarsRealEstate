
package com.example.android.marsrealestate.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com/"

//retrofit object
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

//interface that defines how retrofit talks to the web server using http requests.
interface MarsApiService{
    @GET("realestate")
    fun getProperties():Call<String>
}
//initializing a retrofit service object
object MarsApi{
    val retrofitService:MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}

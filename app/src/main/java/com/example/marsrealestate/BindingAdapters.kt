package com.example.marsrealestate

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.marsrealestate.network.MarsProperty
import com.example.marsrealestate.overview.MarsApiStatus
import com.example.marsrealestate.overview.PhotoGridAdapter

class BindingAdapters {

    /**
     * The @BindingAdapter annotation tells data binding that you want this binding adapter executed
     * when an XML item has the imageUrl attribute.
     */
    companion object {
        @JvmStatic
        @BindingAdapter("imgUrl")
        fun bindImage(imgView:ImageView, imgUrl:String?){
            imgUrl?.let {
                val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
                Glide.with(imgView.context)
                    .load(imgUri)
                    .apply(RequestOptions().placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                    .into(imgView)
            }
        }
        @JvmStatic
        @BindingAdapter("listData")
        fun bindRecyclerView(recyclerView: RecyclerView, data:List<MarsProperty>?){
            val adapter = recyclerView.adapter as PhotoGridAdapter  //casting
            adapter.submitList(data)  //tells the RecyclerView when a new list is available.
        }
        @JvmStatic
        @BindingAdapter("marsApiStatus")
        fun bindStatus(statusImgView: ImageView, status: MarsApiStatus?){
            when(status){
                MarsApiStatus.LOADING -> {
                    statusImgView.visibility = View.VISIBLE
                    statusImgView.setImageResource(R.drawable.loading_animation)
                }
                MarsApiStatus.DONE -> {
                        statusImgView.visibility = View.GONE
                }
                MarsApiStatus.ERROR -> {
                    statusImgView.visibility = View.VISIBLE
                    statusImgView.setImageResource(R.drawable.ic_connection_error)
                }
            }
        }
    }
}

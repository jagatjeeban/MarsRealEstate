package com.example.marsrealestate

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

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
    }
}

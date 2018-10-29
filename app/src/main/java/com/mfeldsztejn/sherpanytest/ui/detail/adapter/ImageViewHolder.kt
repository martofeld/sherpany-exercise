package com.mfeldsztejn.sherpanytest.ui.detail.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class ImageViewHolder(itemView: View, private val requestBuilder: RequestManager) : AlbumViewHolder(itemView) {

    override fun bind(item: Item, position: Int) {
        val image = item as Image
        requestBuilder
                .load(image.url)
                .apply(
                        RequestOptions.centerCropTransform()
                                .placeholder(CircularProgressDrawable(itemView.context))
                                .error(ColorDrawable(Color.WHITE))
                                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                )
                .into(itemView as ImageView)
    }

    class Image(val url: String, val title: String) : Item() {
        override val viewType: Int
            get() = 3
    }
}
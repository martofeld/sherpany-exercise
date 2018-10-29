package com.mfeldsztejn.sherpanytest.ui.detail.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    internal abstract fun bind(item: Item, position: Int)

    abstract class Item {
        abstract val viewType: Int
    }
}
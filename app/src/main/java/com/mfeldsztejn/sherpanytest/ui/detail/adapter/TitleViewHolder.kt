package com.mfeldsztejn.sherpanytest.ui.detail.adapter

import android.view.View
import android.widget.TextView

class TitleViewHolder(itemView: View) : AlbumViewHolder(itemView) {
    override fun bind(item: Item, position: Int) {
        (itemView as TextView).text = (item as Title).title
    }

    class Title(val title: String) : Item() {
        override val viewType: Int
            get() = 0
    }
}
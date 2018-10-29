package com.mfeldsztejn.sherpanytest.ui.detail.adapter

import android.view.View
import android.widget.TextView

class BodyViewHolder(itemView: View) : AlbumViewHolder(itemView) {
    override fun bind(item: Item, position: Int) {
        (itemView as TextView).text = (item as Body).body
    }

    class Body(val body: String) : Item() {
        override val viewType: Int
            get() = 1
    }
}
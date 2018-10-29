package com.mfeldsztejn.sherpanytest.ui.detail.adapter

import android.view.View
import android.widget.TextView

class AlbumTitleViewHolder(itemView: View, val onTitleClickListener: OnTitleClickListener) : AlbumViewHolder(itemView) {

    override fun bind(item: Item, position: Int) {
        val title = item as AlbumTitle
        (itemView as TextView).text = title.title
        itemView.setOnClickListener {
            title.isCollapsed = !title.isCollapsed
            onTitleClickListener.onTitleClick(title, title.isCollapsed, adapterPosition)
        }
    }

    class AlbumTitle(val title: String, var isCollapsed: Boolean) : Item() {
        override val viewType: Int
            get() = 2
    }
}
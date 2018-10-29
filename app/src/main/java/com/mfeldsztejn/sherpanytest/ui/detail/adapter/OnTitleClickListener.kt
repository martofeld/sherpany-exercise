package com.mfeldsztejn.sherpanytest.ui.detail.adapter

interface OnTitleClickListener {
    fun onTitleClick(albumTitle: AlbumTitleViewHolder.AlbumTitle, shouldCollapse: Boolean, adapterPosition: Int)
}
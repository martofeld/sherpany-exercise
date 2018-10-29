package com.mfeldsztejn.sherpanytest.ui.detail.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mfeldsztejn.sherpanytest.R
import com.mfeldsztejn.sherpanytest.models.AlbumWithPhotos
import com.mfeldsztejn.sherpanytest.ui.detail.StickyHeaderDecoration


class AlbumsAdapter(albums: List<AlbumWithPhotos>, title: String, body: String, context: Context) : RecyclerView.Adapter<AlbumViewHolder>(), OnTitleClickListener, StickyHeaderDecoration.StickyHeaderInterface {

    private val displayItems: MutableList<AlbumViewHolder.Item>
    private val map: MutableMap<String, List<ImageViewHolder.Image>>
    private var lastTitlePosition = 0
    private val requestBuilder = Glide.with(context.applicationContext)

    init {
        map = HashMap()
        displayItems = arrayListOf(
                TitleViewHolder.Title(title),
                BodyViewHolder.Body(body)
        )
        for (album in albums) {
            map[album.album!!.title] = album.photos.map { ImageViewHolder.Image(it.url, album.album!!.title) }
            displayItems.add(AlbumTitleViewHolder.AlbumTitle(album.album!!.title, true))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            0 -> TitleViewHolder(inflater.inflate(R.layout.title_view_holder, parent, false))
            1 -> BodyViewHolder(inflater.inflate(R.layout.body_view_holder, parent, false))
            2 -> AlbumTitleViewHolder(inflater.inflate(R.layout.album_title_view_holder, parent, false), this)
            else -> ImageViewHolder(inflater.inflate(R.layout.image_view_holder, parent, false), requestBuilder)
        }
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(displayItems[position], position)
    }

    override fun getItemCount(): Int {
        return displayItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return displayItems[position].viewType
    }

    override fun onTitleClick(albumTitle: AlbumTitleViewHolder.AlbumTitle, shouldCollapse: Boolean, adapterPosition: Int) {
        if (shouldCollapse) {
            collapse(albumTitle, adapterPosition)
        } else {
            expand(albumTitle, adapterPosition)
        }
    }

    private fun expand(albumTitle: AlbumTitleViewHolder.AlbumTitle, adapterPosition: Int) {
        val newItems = ArrayList(displayItems)
        val images = map[albumTitle.title]!!
        newItems.addAll(adapterPosition + 1, images)
        displayItems.clear()
        displayItems.addAll(newItems)
        notifyItemRangeInserted(adapterPosition + 1, images.size)
    }

    private fun collapse(albumTitle: AlbumTitleViewHolder.AlbumTitle, adapterPosition: Int) {
        val images = map[albumTitle.title]!!
        val newItems = displayItems.filterIndexed { index, _ -> index !in (adapterPosition + 1)..(adapterPosition + images.size) }
        displayItems.clear()
        displayItems.addAll(newItems)
        notifyItemRangeRemoved(adapterPosition + 1, images.size)
    }

    override fun getHeaderForItem(itemPosition: Int): String? {
        if (displayItems[itemPosition] is AlbumTitleViewHolder.AlbumTitle) {
            return (displayItems[itemPosition] as AlbumTitleViewHolder.AlbumTitle).title
        }
        if (displayItems[itemPosition] is ImageViewHolder.Image) {
            return (displayItems[itemPosition] as ImageViewHolder.Image).title
        }
        return null
    }

    override fun getHeaderLayout() = R.layout.album_title_view_holder

    override fun bindHeaderData(header: View, headerValue: String) {
        (header as TextView).text = headerValue
    }

    override fun isHeader(itemPosition: Int) = displayItems[itemPosition] is AlbumTitleViewHolder.AlbumTitle

    override fun onTitleClicked() {
        if (displayItems[lastTitlePosition] is AlbumTitleViewHolder.AlbumTitle) {
            val albumTitle = displayItems[lastTitlePosition] as AlbumTitleViewHolder.AlbumTitle
            albumTitle.isCollapsed = !albumTitle.isCollapsed
            onTitleClick(albumTitle, albumTitle.isCollapsed, lastTitlePosition)
        }
    }
}


package com.mfeldsztejn.sherpanytest.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mfeldsztejn.sherpanytest.R
import com.mfeldsztejn.sherpanytest.dtos.Post
import com.mfeldsztejn.sherpanytest.models.PostUserModel
import kotlinx.android.synthetic.main.post_view_holder.view.*

class PostsAdapter(private val onPostSelectedListener: OnPostSelectedListener, private val onPostDeletedListener: OnPostDeletedListener)
    : PagedListAdapter<PostUserModel, PostViewHolder>(PostDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.post_view_holder, parent, false)
        return PostViewHolder(itemView, onPostSelectedListener, onPostDeletedListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }
}

internal class PostDiffUtil : DiffUtil.ItemCallback<PostUserModel>() {
    override fun areItemsTheSame(oldItem: PostUserModel, newItem: PostUserModel) = oldItem.post.id == newItem.post.id

    override fun areContentsTheSame(oldItem: PostUserModel, newItem: PostUserModel) = oldItem == newItem
}

interface OnPostSelectedListener {
    fun onPostSelected(post: Post)
}

interface OnPostDeletedListener {
    fun onPostDeleted(post: Post)
}

class PostViewHolder(itemView: View, private val selectedListener: OnPostSelectedListener, private val deletedListener: OnPostDeletedListener)
    : RecyclerView.ViewHolder(itemView) {

    fun bind(item: PostUserModel) {
        itemView.postTitle.text = item.post.title
        itemView.postAuthor.text = item.user.email
        itemView.deletePost.setOnClickListener {
            deletedListener.onPostDeleted(item.post)
        }
        itemView.setOnClickListener {
            selectedListener.onPostSelected(item.post)
        }
    }

}
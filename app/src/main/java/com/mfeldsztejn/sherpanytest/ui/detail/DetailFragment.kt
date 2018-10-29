package com.mfeldsztejn.sherpanytest.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.mfeldsztejn.sherpanytest.R
import com.mfeldsztejn.sherpanytest.dtos.Post
import com.mfeldsztejn.sherpanytest.ui.detail.adapter.AlbumsAdapter
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : Fragment() {

    companion object {
        private const val POST_KEY = "POST_KEY"

        fun newInstance(post: Post): DetailFragment {
            return DetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(POST_KEY, post)
                }
            }
        }
    }

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.withPost(arguments!!.getSerializable(POST_KEY) as Post)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = GridLayoutManager(context, 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (postAlbums.adapter!!.getItemViewType(position)) {
                    in 0..2 -> 2
                    else -> 1
                }
            }
        }
        postAlbums.layoutManager = layoutManager
        viewModel.albumsLiveData.observe(this, Observer {
            val adapter = AlbumsAdapter(it, viewModel.post!!.title, viewModel.post!!.body, context!!)
            postAlbums.adapter = adapter
            postAlbums.addItemDecoration(StickyHeaderDecoration(postAlbums, adapter))
            postAlbums.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
        })
    }
}
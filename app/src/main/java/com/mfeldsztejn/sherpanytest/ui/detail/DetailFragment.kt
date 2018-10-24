package com.mfeldsztejn.sherpanytest.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mfeldsztejn.sherpanytest.R
import com.mfeldsztejn.sherpanytest.dtos.Post
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

    lateinit var post: Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        post = arguments!!.getSerializable(POST_KEY) as Post
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postDetail.text = post.toString()
    }
}
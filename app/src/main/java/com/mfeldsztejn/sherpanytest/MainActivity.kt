package com.mfeldsztejn.sherpanytest

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mfeldsztejn.sherpanytest.dtos.Post
import com.mfeldsztejn.sherpanytest.ui.detail.DetailFragment
import com.mfeldsztejn.sherpanytest.ui.detail.EmptyFragment
import com.mfeldsztejn.sherpanytest.ui.list.ListFragment
import com.mfeldsztejn.sherpanytest.ui.list.OnPostSelectedListener

class MainActivity : AppCompatActivity(), OnPostSelectedListener {
    companion object {
        const val LAST_POST_KEY = "LAST_POST"
    }

    private var isTwoPane: Boolean = false
    private var lastPost: Post? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        isTwoPane = findViewById<View>(R.id.detail_fragment) != null
        if (savedInstanceState != null && savedInstanceState.containsKey(LAST_POST_KEY)) {
            lastPost = savedInstanceState.getSerializable(LAST_POST_KEY) as Post?
        }

        showListFragment()
        if (lastPost != null) {
            showDetailFragment()
        } else {
            showEmptyFragment()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putSerializable(LAST_POST_KEY, lastPost)
        super.onSaveInstanceState(outState)
    }

    override fun onPostSelected(post: Post) {
        lastPost = post
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = DetailFragment.newInstance(post)
        val tag = DetailFragment.createTag(post)
        if (isTwoPane) {
            transaction.replace(R.id.detail_fragment, fragment, tag)
        } else {
            transaction
                    .replace(R.id.master_fragment, fragment, tag)
        }
        transaction.commit()
    }

    override fun onBackPressed() {
        if (lastPost != null && !isTwoPane) {
            lastPost = null
            showListFragment()
        } else {
            super.onBackPressed()
        }
    }

    private fun showListFragment() {
        val listFragment = supportFragmentManager.findFragmentByTag(ListFragment.TAG)
                ?: ListFragment.newInstance()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.master_fragment, listFragment, ListFragment.TAG)
                .commit()
    }

    private fun showDetailFragment() {
        val id = if (isTwoPane) R.id.detail_fragment else R.id.master_fragment
        val tag = DetailFragment.createTag(lastPost!!)
        val detailFragment = supportFragmentManager.findFragmentByTag(tag)
        val transaction = supportFragmentManager.beginTransaction()
        if (detailFragment != null) {
            transaction.remove(detailFragment)
        }
        transaction.replace(id, DetailFragment.newInstance(lastPost!!), tag)
        transaction.commit()
    }

    private fun showEmptyFragment() {
        if (!isTwoPane) return

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.detail_fragment, EmptyFragment.newInstance())
                .commit()
    }
}

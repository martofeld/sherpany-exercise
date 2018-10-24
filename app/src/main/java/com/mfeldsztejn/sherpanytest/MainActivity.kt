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

    var isTwoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        isTwoPane = findViewById<View>(R.id.detail_fragment) != null

        showInitialFragment()
    }

    private fun showInitialFragment() {
        if (isTwoPane) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.list_fragment, ListFragment.newInstance())
                    .replace(R.id.detail_fragment, EmptyFragment.newInstance())
                    .commitNow()
        } else {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.content, ListFragment.newInstance())
                    .commitNow()
        }
    }

    override fun onPostSelected(post: Post) {
        val transaction = supportFragmentManager.beginTransaction()
        val fragment = DetailFragment.newInstance(post)
        if (isTwoPane) {
            transaction.replace(R.id.detail_fragment, fragment)
        } else {
            transaction
                    .addToBackStack(null)
                    .replace(R.id.content, fragment)
        }
        transaction.commit()
    }
}

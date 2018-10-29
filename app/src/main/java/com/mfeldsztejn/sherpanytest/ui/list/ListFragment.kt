package com.mfeldsztejn.sherpanytest.ui.list

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mfeldsztejn.sherpanytest.R
import com.mfeldsztejn.sherpanytest.dtos.Post
import com.mfeldsztejn.sherpanytest.persitence.Database
import kotlinx.android.synthetic.main.list_fragment.*

class ListFragment : androidx.fragment.app.Fragment(), OnPostDeletedListener {

    companion object {
        const val TAG: String = "LIST_FRAGMENT"

        fun newInstance() = ListFragment()
    }

    private lateinit var viewModel: ListViewModel
    private lateinit var onPostSelectedListener: OnPostSelectedListener
    private lateinit var adapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PostsAdapter(onPostSelectedListener, this)
        postsRecyclerView.adapter = adapter
        postsRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        postsRecyclerView.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
        subscribe()
    }

    private fun subscribe() {
        viewModel.postsLiveData.observe(this, Observer {
            emptyView.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            adapter.submitList(it)
        })
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        onPostSelectedListener = context as OnPostSelectedListener
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.list_fragment_menu, menu) ?: return
        val actionView = menu?.findItem(R.id.action_search)?.actionView as SearchView

        actionView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.filter(this@ListFragment, query)
                subscribe()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.filter(this@ListFragment, newText)
                subscribe()
                return true
            }

        })
    }

    override fun onPostDeleted(post: Post) {
        AsyncTask.execute {
            Database
                    .getInstance()
                    .postsDao()
                    .delete(post)
        }
    }
}

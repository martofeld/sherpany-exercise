package com.mfeldsztejn.sherpanytest.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mfeldsztejn.sherpanytest.R

class EmptyFragment : Fragment() {

    companion object {
        fun newInstance() = EmptyFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.empty_fragment, container, false)
    }
}
package com.dicoding.capspro.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.capspro.utils.DataDummy
import com.dicoding.capspro.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var fragmentHomeBinding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val articles = DataDummy.generateDummyCourses()
            val homeAdapter = HomeAdapter()
            homeAdapter.setArticles(articles)

            with(fragmentHomeBinding.rvHome) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = homeAdapter
            }
        }
    }
}
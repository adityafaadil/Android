package com.dicoding.capspro.ui.forum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.capspro.data.remote.forum.thread.Thread
import com.dicoding.capspro.data.remote.forum.thread.ThreadList
import com.dicoding.capspro.databinding.FragmentForumBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class ForumFragment : Fragment() {

    private val forumViewModel: ForumViewModel by viewModel()
    private var _binding: FragmentForumBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        forumViewModel.getThread().observe(viewLifecycleOwner, {
                binding.rvForum.adapter = ForumAdapter(it)
                binding.rvForum.layoutManager = LinearLayoutManager(context)
                binding.rvForum.visibility = View.VISIBLE
                binding.forumProgressBar.visibility = View.GONE
        })
        _binding = FragmentForumBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
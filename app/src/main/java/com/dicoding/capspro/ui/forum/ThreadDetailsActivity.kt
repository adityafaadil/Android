package com.dicoding.capspro.ui.forum

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dicoding.capspro.R
import com.dicoding.capspro.data.remote.forum.thread.Thread
import com.dicoding.capspro.data.remote.user.User
import com.dicoding.capspro.databinding.ActivityThreadDetailsBinding
import com.dicoding.capspro.ui.forum.ForumAdapter.Companion.THREAD
import com.dicoding.capspro.ui.forum.ForumAdapter.Companion.USER
import com.dicoding.capspro.utils.Constants.Companion.TEST_USER_EMAIL
import com.dicoding.capspro.utils.TimeFormat.Companion.parser
import org.koin.androidx.viewmodel.ext.android.viewModel

class ThreadDetailsActivity : AppCompatActivity() {
    private val viewModel: ThreadDetailsViewModel by viewModel()
    private lateinit var binding: ActivityThreadDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThreadDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val thread = intent.getSerializableExtra(THREAD) as Thread
        val user = intent.getSerializableExtra(USER) as User
        binding.threadVote.text = "${thread.vote}\nvotes"
        binding.threadJudul.text = "Judul Thread"
        Glide.with(this).load(user.profilePic)
            .into(binding.threadProfilePic)
        binding.threadNama.text = user.name
        binding.threadEmail.text = "<${user.email}>"
        binding.threadContent.text = thread.content
        binding.threadDate.text = parser(thread.date).toLocaleString()
        binding.threadCommentCount.text = "${thread.numComment} Comments"

        binding.threadVote.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.threadVote.setTextColor(getColor(R.color.purple_500))
                binding.threadVote.setCompoundDrawablesWithIntrinsicBounds(
                    0,
                    R.drawable.up_vote_active,
                    0,
                    0
                )
                binding.threadVote.text = "${thread.vote + 1}\nvotes"
            }
            viewModel.upvoteThread(thread._id)
        }

        binding.threadCommentInput.setEndIconOnClickListener {
            val inputText = binding.threadCommentInput.editText?.text
            viewModel.addComment(thread._id, TEST_USER_EMAIL, inputText.toString())
        }

        viewModel.getComment(thread._id).observe(this, {
            binding.rvComment.adapter = CommentAdapter(it)
            binding.rvComment.layoutManager = LinearLayoutManager(this)
        })
    }
}
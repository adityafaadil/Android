package com.dicoding.capspro.ui.forum

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dicoding.capspro.R
import com.dicoding.capspro.data.remote.forum.comment.Comment
import com.dicoding.capspro.data.remote.forum.comment.CommentList
import com.dicoding.capspro.data.remote.forum.thread.Thread
import com.dicoding.capspro.data.remote.user.User
import com.dicoding.capspro.databinding.ActivityThreadDetailsBinding
import com.dicoding.capspro.utils.Constants.Companion.TEST_USER_EMAIL
import com.dicoding.capspro.utils.Constants.Companion.THREAD
import com.dicoding.capspro.utils.Constants.Companion.USER
import com.dicoding.capspro.utils.TimeFormat.Companion.getNow
import com.dicoding.capspro.utils.TimeFormat.Companion.toLocaleDate
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class ThreadDetailsActivity : AppCompatActivity() {
    private val viewModel: ThreadDetailsViewModel by viewModel()
    private lateinit var binding: ActivityThreadDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var adapterItem = arrayListOf<CommentList>()

        binding = ActivityThreadDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val thread = intent.getSerializableExtra(THREAD) as Thread
        val user = intent.getSerializableExtra(USER) as User
        binding.threadVote.text = "${thread.vote}\nvotes"
        binding.threadJudul.text = thread.threadTitle
        Glide.with(this).load(user.profilePic)
            .into(binding.threadProfilePic)
        binding.threadNama.text = user.name
        binding.threadEmail.text = "<${user.email}>"
        binding.threadContent.text = thread.content
        binding.threadDate.text = thread.date.toLocaleDate()
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
            if (inputText.toString() != "") {
                val posting = makeSnackbar("Posting Comment...", Snackbar.LENGTH_INDEFINITE)
                posting.show()
                viewModel.addComment(thread._id, TEST_USER_EMAIL, inputText.toString())
                    .observe(this, {
                        posting.dismiss()
                        if (it as Boolean) {
                            makeSnackbar(
                                "Komentar anda mengandung unsur negatif...",
                                Snackbar.LENGTH_LONG
                            ).show()
                        } else {
                            val comment = Comment(
                                "",
                                thread._id,
                                TEST_USER_EMAIL,
                                inputText.toString(),
                                getNow()
                            )
                            val user = User(
                                "",
                                "https://www.gravatar.com/avatar/4c803c48f843c56fa80f0bd96c568cc7",
                                "John Cena",
                                "john@email.com",
                                ""
                            )
                            val commentList = CommentList(user, comment)
                            adapterItem.add(commentList)
                            binding.rvComment.adapter?.notifyDataSetChanged()
                        }
                        binding.threadCommentInput.editText?.isEnabled = true
                    })
                binding.threadCommentInput.editText?.setText("")
                binding.threadCommentInput.editText?.isEnabled = false
            }
        }

        viewModel.getComment(thread._id).observe(this, {
            adapterItem = it
            binding.rvComment.adapter = CommentAdapter(adapterItem)
            binding.rvComment.layoutManager = LinearLayoutManager(this)
        })
    }

    private fun makeSnackbar(text: String, length: Int): Snackbar {
        return Snackbar.make(
            binding.threadConstraint,
            text,
            length
        ).setAnchorView(binding.threadCommentInput)
    }
}
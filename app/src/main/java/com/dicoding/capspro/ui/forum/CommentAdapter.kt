package com.dicoding.capspro.ui.forum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.capspro.R
import com.dicoding.capspro.data.remote.forum.comment.Comment
import com.dicoding.capspro.data.remote.forum.comment.CommentList
import com.dicoding.capspro.data.remote.user.User
import com.dicoding.capspro.databinding.ItemCommentBinding
import com.dicoding.capspro.utils.TimeFormat.Companion.timeAgo

class CommentAdapter(private val listItem: ArrayList<CommentList>) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCommentBinding.bind(itemView)
        fun bind(user: User, comment: Comment) {
            Glide.with(itemView.context)
                .load(user.profilePic).into(
                    binding.commentProfilePic
                )
            binding.commentUser.text = user.name
            binding.commentEmail.text = "<${user.email}>"
            binding.commentText.text = comment.comment
            binding.commentWaktu.text = timeAgo(comment.date)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItem[position].user, listItem[position].comment)
    }

    override fun getItemCount(): Int = listItem.size
}
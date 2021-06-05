package com.dicoding.capspro.ui.forum

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.capspro.R
import com.dicoding.capspro.data.remote.forum.thread.Thread
import com.dicoding.capspro.data.remote.forum.thread.ThreadList
import com.dicoding.capspro.data.remote.user.User
import com.dicoding.capspro.databinding.ItemThreadBinding
import java.io.Serializable
import com.dicoding.capspro.utils.TimeFormat.Companion.timeAgo

class ForumAdapter(private val listItem: ArrayList<ThreadList>) :
    RecyclerView.Adapter<ForumAdapter.ForumViewHolder>() {
    companion object {
        val THREAD = "THREAD"
        val USER = "USER"
    }

    inner class ForumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemThreadBinding.bind(itemView)
        fun bind(user: User, thread: Thread) {
            binding.forumVote.text = "${thread.vote}\nvotes"
            binding.forumJudul.text = "Judul Thread"
            Glide.with(itemView.context)
                .load(user.profilePic)
                .into(binding.forumProfilePic)
            binding.forumNama.text = user.name
            binding.forumEmail.text = "<${user.email}>"
            binding.forumWaktu.text = timeAgo(thread.date)
            binding.forumComment.text = "${thread.numComment} Comment"
            binding.forumCard.setOnClickListener {
                val intent = Intent(itemView.context, ThreadDetailsActivity::class.java)
                intent.putExtra(USER, user as Serializable)
                intent.putExtra(THREAD, thread as Serializable)
                startActivity(itemView.context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_thread, parent, false)
        return ForumViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForumViewHolder, position: Int) {
        holder.bind(listItem[position].user, listItem[position].thread)
    }

    override fun getItemCount(): Int = listItem.size

}
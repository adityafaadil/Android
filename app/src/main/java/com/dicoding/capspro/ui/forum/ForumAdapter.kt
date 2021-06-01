package com.dicoding.capspro.ui.forum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.capspro.R
import com.dicoding.capspro.data.remote.forum.Thread
import com.dicoding.capspro.databinding.ItemForumBinding

class ForumAdapter(private val listItem: ArrayList<Thread>): RecyclerView.Adapter<ForumAdapter.ForumViewHolder>() {
    inner class ForumViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        private val binding = ItemForumBinding.bind(itemView)
        fun bind(thread: Thread){
            binding.forumVote.text = "${thread.vote}\nVote"
            binding.forumJudul.text = thread.content
            binding.forumNama.text = "Coba Nama"
            binding.forumEmail.text = "<Coba Email>"
            binding.forumWaktu.text = thread.date
            binding.forumComment.text = "${thread.numComment} Comment"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_forum,parent,false)
        return ForumViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForumViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount(): Int = listItem.size
}
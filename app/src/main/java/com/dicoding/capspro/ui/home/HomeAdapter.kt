package com.dicoding.capspro.ui.home

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.capspro.data.Artikel
import com.dicoding.capspro.databinding.ItemHomeBinding
import java.util.*

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private var listArticle = ArrayList<Artikel>()

    fun setArticles(articles: List<Artikel>?) {
        if (articles == null) return
        this.listArticle.clear()
        this.listArticle.addAll(articles)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemHomeBinding =
            ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemHomeBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = listArticle[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int = listArticle.size


    class ViewHolder(private val binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Artikel) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(article.photo)
                    .apply(RequestOptions().override(350, 350).centerCrop())
                    .into(imgPoster)

                tvItemTitle.text = article.title
                homeContent.text = article.date

                cvItemCourse.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(article.link)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}


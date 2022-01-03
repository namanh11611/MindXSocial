package com.namanh.mindx.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.namanh.mindx.R
import com.namanh.mindx.data.model.News
import com.namanh.mindx.databinding.ItemNewsBinding
import com.namanh.mindx.view.list.NewsListFragmentDirections

class NewsAdapter(var dataSet: List<News>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        private lateinit var mNews: News

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(news: News) {
            mNews = news
            binding.txtDescription.text = news.description
            binding.txtAuthor.text = if (news.author.isNullOrBlank()) "Henry Nguyen" else news.author
            binding.imgCele.setOnClickListener {
                (it as ImageView).setColorFilter(binding.root.resources.getColor(R.color.colorPrimary))
            }
            binding.imgComment.setOnClickListener {
                binding.root.findNavController()
                    .navigate(NewsListFragmentDirections.actionNewsListFragmentToNewsDetailFragment())
            }
        }

        override fun onClick(v: View?) {
            binding.root.findNavController()
                .navigate(NewsListFragmentDirections.actionNewsListFragmentToNewsDetailFragment())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size
}
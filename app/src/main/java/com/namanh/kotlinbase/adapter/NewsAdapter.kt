package com.namanh.kotlinbase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.namanh.kotlinbase.databinding.ItemNewsBinding
import com.namanh.kotlinbase.helper.GlideHelper
import com.namanh.kotlinbase.model.News

class NewsAdapter(var dataSet: List<News>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        private lateinit var mNews: News

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(news: News) {
            mNews = news
            binding.txtTitle.text = news.title
            binding.txtDescription.text = news.description
            binding.txtAuthor.text = news.author
            binding.txtTime.text = news.publishedAt
            GlideHelper.loadImage(binding.root.context, binding.imageNews, news.urlToImage)
        }

        override fun onClick(v: View?) {
//            val bundle = bundleOf(AppUtils.PRODUCT_KEY to Gson().toJson(mNews))
//            binding.root.findNavController()
//                .navigate(R.id.action_newsListFragment_to_newsDetailFragment, bundle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount() = dataSet.size
}
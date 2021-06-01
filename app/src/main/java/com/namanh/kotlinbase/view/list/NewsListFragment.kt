package com.namanh.kotlinbase.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.namanh.kotlinbase.databinding.FragmentNewsListBinding
import com.namanh.kotlinbase.view.main.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListFragment : BaseFragment<FragmentNewsListBinding>(), View.OnClickListener {

    companion object {
        fun newInstance() = NewsListFragment()
    }

    private val viewModel: NewsListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        val newsList = emptyList<News>()
//        val newsAdapter = NewsAdapter(newsList)

//        binding.listNews.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//        binding.listNews.adapter = newsAdapter
//
//        viewModel.getNews().observe(viewLifecycleOwner, { result ->
//            if (result == null) return@observe
//            newsAdapter.dataSet = result
//            newsAdapter.notifyDataSetChanged()
//
//        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }

}
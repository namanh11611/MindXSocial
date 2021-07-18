package com.namanh.kotlinbase.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.namanh.kotlinbase.R
import com.namanh.kotlinbase.adapter.NewsAdapter
import com.namanh.kotlinbase.data.model.News
import com.namanh.kotlinbase.databinding.FragmentNewsListBinding
import com.namanh.kotlinbase.utils.NetworkUtils
import com.namanh.kotlinbase.view.main.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListFragment : BaseFragment<FragmentNewsListBinding>(), View.OnClickListener {

    companion object {
        fun newInstance() = NewsListFragment()
    }

    enum class State {
        NO_INTERNET, LOADING, LOADED
    }

    private val viewModel: NewsListViewModel by viewModels()
    private val mNewsAdapter = NewsAdapter(emptyList<News>())

    override fun viewBindingInflate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentNewsListBinding.inflate(inflater, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.btReconnect.setOnClickListener(this)
        binding.listNews.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.listNews.adapter = mNewsAdapter

        observeNews()

        if (context?.let { NetworkUtils.isNetworkConnected(it) } == false) {
            setState(State.NO_INTERNET)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt_reconnect -> {
                setState(State.LOADING)
                viewModel.forceUpdate()
            }
        }
    }

    private fun observeNews() {
        viewModel.items.observe(viewLifecycleOwner, { result ->
            if (result == null || result.isEmpty()) {
                setState(State.NO_INTERNET)
                return@observe
            }
            setState(State.LOADED)
            mNewsAdapter.dataSet = result
            mNewsAdapter.notifyDataSetChanged()
        })
    }

    private fun setState(state: State) {
        binding.listNews.visibility = if (state == State.LOADED) View.VISIBLE else View.GONE
        binding.pbLoading.visibility = if (state == State.LOADING) View.VISIBLE else View.GONE
        binding.txtNoConnect.visibility = if (state == State.NO_INTERNET) View.VISIBLE else View.GONE
        binding.btReconnect.visibility = if (state == State.NO_INTERNET) View.VISIBLE else View.GONE
    }

}
package com.namanh.mindx.view.list

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.namanh.mindx.R
import com.namanh.mindx.adapter.NewsAdapter
import com.namanh.mindx.data.model.News
import com.namanh.mindx.data.repository.ResourceState
import com.namanh.mindx.databinding.FragmentNewsListBinding
import com.namanh.mindx.utils.NetworkUtils
import com.namanh.mindx.view.main.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListFragment : BaseFragment<FragmentNewsListBinding>(), View.OnClickListener {

    enum class UiState {
        SUCCESS, LOADING, ERROR
    }

    companion object {
        fun newInstance() = NewsListFragment()
    }

    private val viewModel: NewsListViewModel by viewModels()
    private val mNewsAdapter = NewsAdapter(emptyList())

    override fun viewBindingInflate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentNewsListBinding.inflate(inflater, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.btReconnect.setOnClickListener(this)
        binding.fab.setOnClickListener(this)
        binding.listNews.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.listNews.adapter = mNewsAdapter

        observeNews()

        showWarningNoConnectionIfNeeded()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bt_reconnect -> {
                setUiState(UiState.LOADING)
                Handler(Looper.getMainLooper()).postDelayed({
                    viewModel.forceUpdate()
                    showWarningNoConnectionIfNeeded()
                }, 2000)
            }
            R.id.fab -> {
                findNavController().navigate(
                    NewsListFragmentDirections.actionNewsListFragmentToAddNewsFragment())
            }
        }
    }

    private fun observeNews() {
        viewModel.items.observe(viewLifecycleOwner, { result ->
            when {
                result is ResourceState.Success && result.data.isNotEmpty() -> {
                    setSuccessState(result.data)
                }
                result is ResourceState.Error -> setErrorState(result.message)
                else -> setErrorState(getString(R.string.other_error))
            }
        })
    }

    private fun setSuccessState(result: List<News>) {
        setUiState(UiState.SUCCESS)
        mNewsAdapter.dataSet = result.reversed()
        mNewsAdapter.notifyDataSetChanged()
    }

    private fun setErrorState(errorMessage: String) {
        setUiState(UiState.ERROR)
        binding.txtError.text = errorMessage
    }

    private fun setUiState(uiState: UiState) {
        binding.listNews.visibility = if (uiState == UiState.SUCCESS) View.VISIBLE else View.GONE
        binding.pbLoading.visibility = if (uiState == UiState.LOADING) View.VISIBLE else View.GONE
        binding.txtError.visibility = if (uiState == UiState.ERROR) View.VISIBLE else View.GONE
        binding.btReconnect.visibility = if (uiState == UiState.ERROR) View.VISIBLE else View.GONE
    }

    private fun showWarningNoConnectionIfNeeded() {
        if (!NetworkUtils.isNetworkConnected(context)) {
            Toast.makeText(
                context,
                getString(R.string.please_check_internet_connect),
                Toast.LENGTH_LONG
            ).show()
        }
    }

}
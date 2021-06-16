package com.namanh.kotlinbase.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.namanh.kotlinbase.databinding.FragmentNewsDetailBinding
import com.namanh.kotlinbase.view.main.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailFragment : BaseFragment<FragmentNewsDetailBinding>() {

    companion object {
        fun newInstance() = NewsDetailFragment()
    }

    private val viewModel: NewsDetailViewModel by viewModels()

    override fun viewBindingInflate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentNewsDetailBinding.inflate(inflater, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
package com.namanh.kotlinbase.view.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.namanh.kotlinbase.R
import com.namanh.kotlinbase.databinding.FragmentAddNewsBinding
import com.namanh.kotlinbase.utils.AppUtils
import com.namanh.kotlinbase.view.main.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNewsFragment : BaseFragment<FragmentAddNewsBinding>(), View.OnClickListener {

    companion object {
        fun newInstance() = AddNewsFragment()
    }

    private val viewModel: AddNewsViewModel by viewModels()
    private lateinit var author: String

    override fun viewBindingInflate(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAddNewsBinding.inflate(inflater, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        author = AppUtils.getPrefString(context, AppUtils.PREF_USER_NAME)
        binding.btnCreate.setOnClickListener(this)
        binding.txtAuthor.text = "Author: " + author
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_create -> {
                viewModel.createNews(author, binding.edtContent.text.toString())
                findNavController().navigate(AddNewsFragmentDirections.actionAddNewsFragmentToNewsListFragment())
            }
        }
    }

}